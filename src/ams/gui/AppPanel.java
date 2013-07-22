/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.gui;

import ams.dao.TblAbsenDao;
import ams.dao.TblDosenDao;
import ams.dao.TblJadwalDao;
import ams.entity.TblAbsen;
import ams.entity.TblDosen;
import ams.entity.TblJadwal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import readserial.FrontEnd;

/**
 *
 * @author Bayu
 */
public class AppPanel extends javax.swing.JPanel {

    /**
     * Creates new form App
     */
    static TblJadwalDao jadwaldao = new TblJadwalDao();
    static TblDosenDao dosendao = new TblDosenDao();
    static TblAbsenDao absendao = new TblAbsenDao();
    static TblAbsen absenmulai = new TblAbsen();
    static TblAbsen absenselesai = new TblAbsen();
    static TblDosen dosen = new TblDosen();
    static TblJadwal jadwal = new TblJadwal();
    static SerialPort serialPort;
    static int counter = 0;
    static String nomor = "";
   
    public AppPanel() {
        initComponents();
        //this.getContentPane().setBackground(Color.WHITE);
        absendao.getAllAbsen();
        btnAbsenMasuk.setEnabled(false);
        btnAbsenKeluar.setEnabled(false);
        open();
        //makeFrameFullSize(this);
    }
//    private void makeFrameFullSize(JFrame aFrame) {
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        //aFrame.setSize(dim.width, dim.height);
//        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
//    }

    public static void open() {
        serialPort = new SerialPort("COM1");
        try {
            serialPort.openPort();//Open port
            serialPort.setParams(9600, 8, 1, 0);//Set params
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
            serialPort.setEventsMask(mask);//Set mask
            serialPort.addEventListener(new AppPanel.SerialPortReader());//Add SerialPortEventListener

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

                                                //FrontEnd root = new FrontEnd();
                                                String hari[] = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};
                                                int hr = new Date().getDay();
                                                dosen = dosendao.getDosenByNo(nomor);
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
                                                        jadwal = jadwaldao.getJadwalAbsen(dosen, hr);
                                                        if (jadwal == null) {
                                                            System.out.println("Anda Tidak Terjadwal Untuk Saat Ini");
                                                            JOptionPane.showMessageDialog(null, "Anda Tidak Terjadwal Saat Ini");
                                                        } else {
                                                            System.out.println("Insert Absen Mulai");
                                                            txtNamaDosen.setText(jadwal.getTblDosen().getNamaDosen());
                                                            txtMakul.setText(jadwal.getTblMakul().getMataKuliah());
                                                            txtRuangan.setText(jadwal.getTblRuangan().getNamaRuangan());
                                                            txtKelas.setText(jadwal.getTblKelas().getKelas());
                                                            txtJurusan.setText(jadwal.getTblJurusan().getJurusan());
                                                            txtJam.setText(jadwal.getJamMulai()+" - "+jadwal.getJamSelesai());
                                                            btnAbsenMasuk.setEnabled(true);
                                                        }

                                                    } else {
                                                        //Jika Ada Maka Update Data Absen Selesai
                                                        txtNamaDosen.setText(absenselesai.getTblJadwal().getTblDosen().getNamaDosen());
                                                        txtMakul.setText(absenselesai.getTblJadwal().getTblMakul().getMataKuliah());
                                                        txtRuangan.setText(absenselesai.getTblJadwal().getTblRuangan().getNamaRuangan());
                                                        txtKelas.setText(absenselesai.getTblJadwal().getTblKelas().getKelas());
                                                        txtJurusan.setText(absenselesai.getTblJadwal().getTblJurusan().getJurusan());
                                                        txtJam.setText(absenselesai.getTblJadwal().getJamMulai()+" - "+absenselesai.getTblJadwal().getJamSelesai());
                                                        btnAbsenKeluar.setEnabled(true);
                                                    }
                                                }
                                                open();
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAbsenKeluar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNamaDosen = new javax.swing.JTextField();
        btnAbsenMasuk = new javax.swing.JButton();
        txtKelas = new javax.swing.JTextField();
        txtMakul = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtJurusan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtRuangan = new javax.swing.JTextField();
        txtJam = new javax.swing.JTextField();

        btnAbsenKeluar.setText("Absen Keluar");
        btnAbsenKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbsenKeluarActionPerformed(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\Bayu\\Documents\\NetBeansProjects\\ReadSerial\\src\\ams\\gui\\images\\logo.png")); // NOI18N
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Tempelkan Kartu Pada Reader");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Untuk Melakukan Absensi");

        txtNamaDosen.setEditable(false);

        btnAbsenMasuk.setText("Absen Masuk");
        btnAbsenMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbsenMasukActionPerformed(evt);
            }
        });

        txtKelas.setEditable(false);

        txtMakul.setEditable(false);

        jLabel2.setText("Mata Kuliah");

        jLabel1.setText("Nama Dosen");

        txtJurusan.setEditable(false);

        jLabel6.setText("Jam");

        jLabel5.setText("Ruangan");

        jLabel4.setText("Jurusan");

        jLabel3.setText("Kelas");

        txtRuangan.setEditable(false);

        txtJam.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtNamaDosen, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMakul, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJam, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(btnAbsenMasuk)
                        .addGap(63, 63, 63)
                        .addComponent(btnAbsenKeluar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtNamaDosen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtMakul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(txtRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(txtJam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAbsenMasuk)
                    .addComponent(btnAbsenKeluar)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbsenKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbsenKeluarActionPerformed
        // TODO add your handling code here:
        absenselesai.setAbsenSelesai(new Date());
        absendao.updateAbsen(absenselesai);
        JOptionPane.showMessageDialog(this, "Anda Telah Absen Keluar");
        btnAbsenMasuk.setEnabled(false);
        btnAbsenKeluar.setEnabled(false);
        txtNamaDosen.setText(null);
        txtMakul.setText(null);
        txtRuangan.setText(null);
        txtKelas.setText(null);
        txtJurusan.setText(null);
        txtJam.setText(null);
    }//GEN-LAST:event_btnAbsenKeluarActionPerformed

    private void btnAbsenMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbsenMasukActionPerformed
        // TODO add your handling code here:

        absenmulai.setAbsenMulai(new Date());
        absenmulai.setTblJadwal(jadwal);
        absendao.addAbsen(absenmulai);
        JOptionPane.showMessageDialog(this, "Anda Telah Absen Masuk");

        btnAbsenMasuk.setEnabled(false);
        btnAbsenKeluar.setEnabled(false);
        txtNamaDosen.setText(null);
        txtMakul.setText(null);
        txtRuangan.setText(null);
        txtKelas.setText(null);
        txtJurusan.setText(null);
        txtJam.setText(null);
    }//GEN-LAST:event_btnAbsenMasukActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton btnAbsenKeluar;
    private static javax.swing.JButton btnAbsenMasuk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private static javax.swing.JTextField txtJam;
    private static javax.swing.JTextField txtJurusan;
    private static javax.swing.JTextField txtKelas;
    private static javax.swing.JTextField txtMakul;
    private static javax.swing.JTextField txtNamaDosen;
    private static javax.swing.JTextField txtRuangan;
    // End of variables declaration//GEN-END:variables
}
