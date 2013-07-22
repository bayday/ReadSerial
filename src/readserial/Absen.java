/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package readserial;

import ams.dao.TblAbsenDao;
import ams.dao.TblDosenDao;
import ams.dao.TblJadwalDao;
import ams.entity.TblAbsen;
import ams.entity.TblDosen;
import ams.entity.TblJadwal;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
//import ams.gui.ManageDosenOperation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author Bayu
 */
public class Absen extends javax.swing.JFrame {

    /**
     * Creates new form Absen
     */
    static TblJadwalDao jadwaldao = new TblJadwalDao();
    static TblDosenDao dosendao = new TblDosenDao();
    static TblAbsenDao absendao = new TblAbsenDao();
    static SerialPort serialPort;
    static int counter = 0;
    static String nomor = "";
    static DefaultTableModel model;

    public static void main(String[] args) {
        Absen reader = new Absen();
        reader.open();
        reader.setVisible(true);
    }

    public Absen() {
        initComponents();
        makeFrameFullSize(this);

    }

    private void makeFrameFullSize(JFrame aFrame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        aFrame.setSize(screenSize.width, screenSize.height);
    }

    private void displayResult(List resultList) {
        Vector<String> tableHeaders = new Vector<String>();
        Vector tableData = new Vector();
//        tableHeaders.add("ID Absen");
//        tableHeaders.add("Absen Mulai");
//        tableHeaders.add("Absen Selesai");
        tableHeaders.add("Dosen");
        tableHeaders.add("Mata Kuliah");
        tableHeaders.add("Kelas");
        tableHeaders.add("Ruangan");
        tableHeaders.add("Jam Mulai");
        tableHeaders.add("Jam Selesai");
        tableHeaders.add("Absen Masuk");
        tableHeaders.add("Absen Keluar");
        //tableHeaders.add("Jurusan");
        //tableHeaders.add("Operation");

        Iterator its = resultList.iterator();
        while (its.hasNext()) {
            TblAbsen data = (TblAbsen) its.next();
            Vector<Object> oneRow = new Vector<Object>();
//            oneRow.add(data.getIdAbsen());
//            oneRow.add(data.getAbsenMulai());
//            oneRow.add(data.getAbsenSelesai());
            oneRow.add(data.getTblJadwal().getTblDosen().getNamaDosen());
            oneRow.add(data.getTblJadwal().getTblMakul().getMataKuliah());
            oneRow.add(data.getTblJadwal().getTblKelas().getKelas());
            oneRow.add(data.getTblJadwal().getTblRuangan().getNamaRuangan());
            oneRow.add(data.getTblJadwal().getJamMulai());
            oneRow.add(data.getTblJadwal().getJamSelesai());
            oneRow.add(data.getAbsenMulai());
            oneRow.add(data.getAbsenSelesai());
            //System.out.println(str[0] + str[1] + str[2]);
            tableData.add(oneRow);
        }
        model = new DefaultTableModel(tableData, tableHeaders) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        resultTable.setModel(model);
        System.out.println("Table Refresh");
    }

    public void Refresh() {
        displayResult(absendao.getAbsen());
//        resultTable.revalidate();
//        resultTable.repaint();
//        resultTable.setModel(model);
    }

    public void open() {
        serialPort = new SerialPort("COM1");
        try {
            serialPort.openPort();//Open port
            serialPort.setParams(9600, 8, 1, 0);//Set params
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
            serialPort.setEventsMask(mask);//Set mask
            serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener

        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
//        displayResult(absendao.getAbsen());
    }

   

        static class SerialPortReader implements SerialPortEventListener {

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.isRXCHAR()) {//If data is available
                    try {
                        int buffer[] = serialPort.readIntArray();
                        //System.out.print(buffer + " ");
                        if (buffer != null) {
                            for (int i = 0; i < buffer.length; i++) {
                                if (buffer[i] > 0) {
                                    System.out.println(buffer[i]);
                                    int data = buffer[i];
                                    counter = counter + 1;
                                    if (counter == 1) {
                                        if (data == 2) {
                                        } else {
                                            counter = 0;
                                            nomor = "";
                                        }
                                    } else {
                                        if (counter >= 4 && counter <= 11) {
                                            char a = (char) data;
                                            nomor = nomor + a;
                                        } else {
                                            if (counter == 16) {
                                                if (data == 3) {
                                                    System.out.println(nomor);
                                                    serialPort.closePort();

                                                    Absen root = new Absen();
                                                    String hari[] = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};
                                                    int hr = new Date().getDay();
                                                    TblDosen dosen = dosendao.getDosenByNo(nomor);

                                                    TblAbsen absenmulai = new TblAbsen();
                                                    TblAbsen absenselesai = new TblAbsen();

                                                    //Cek Nomor Kartu Tersedia
                                                    if (dosen == null) {
                                                        //Tampilkan Pesan Jika Kartu Tidak Terdaftar
                                                        System.out.println("Kartu Ini Tidak Terdaftar");
                                                        JOptionPane.showMessageDialog(null, "Kartu Tidak Terdaftar");
                                                    } else {
                                                        //Jika Kartu Terdaftar
                                                        //Cari Jadwal Sesuai dengan Kartu Dosen Dan Hari
                                                        System.out.println("Kartu Terdaftar Dengan Nama :" + dosen.getNamaDosen());
                                                        absenselesai = absendao.getCekAbsenAda(dosen, hr);
                                                        if (absenselesai == null) {
                                                            //Jika Tidak Ada Tambah Data Absen
                                                            TblJadwal jadwal = jadwaldao.getJadwalAbsen(dosen, hr);
                                                            if (jadwal == null) {
                                                                System.out.println("Anda Tidak Terjadwal Untuk Saat Ini");
                                                                JOptionPane.showMessageDialog(null, "Anda Tidak Terjadwal Saat Ini");
                                                            } else {
                                                                System.out.println("Insert Absen Mulai");
                                                                absenmulai.setAbsenMulai(new Date());
                                                                absenmulai.setTblJadwal(jadwal);
                                                                absendao.addAbsen(absenmulai);
                                                                JOptionPane.showMessageDialog(null, "Anda Telah Absen Masuk");
                                                            }

                                                        } else {
                                                            //Jika Ada Maka Update Data Absen Selesai
                                                            absenselesai.setAbsenSelesai(new Date());
                                                            absendao.updateAbsen(absenselesai);
                                                            JOptionPane.showMessageDialog(null, "Anda Telah Absen Selesai");
                                                        }
                                                    }
                                                    root.open();
                                                    counter = 0;
                                                    nomor = "";
                                                } else {
                                                    counter = 0;
                                                    nomor = "";
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }

                    } catch (SerialPortException ex) {
                        System.out.println(ex);

                    }
                } else if (event.isCTS()) {//If CTS line has changed state
                    if (event.getEventValue() == 1) {//If line is ON
                        System.out.println("CTS - ON");
                    } else {
                        System.out.println("CTS - OFF");
                    }
                } else if (event.isDSR()) {///If DSR line has changed state
                    if (event.getEventValue() == 1) {//If line is ON
                        System.out.println("DSR - ON");
                    } else {
                        System.out.println("DSR - OFF");
                    }
                }
            }
        }

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable(){
            public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
                // get the current row
                Object Value = getValueAt(Index_row,7);
                Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
                // even index, not selected
                if (Value == null && !isCellSelected(Index_row, Index_col)) {
                    comp.setBackground(Color.red);
                } else {
                    comp.setBackground(Color.white);
                }
                return comp;
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Attendance Management System");
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Silakan Tempelkan Kartu Pada Reader Untuk Melakukan Absensi Dan Tunggu Hingga Pesan Konfirmasi Muncul");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(resultTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 889, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(55, 55, 55)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Refresh();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        Refresh();
    }//GEN-LAST:event_formWindowGainedFocus
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable resultTable;
    // End of variables declaration//GEN-END:variables
    }
