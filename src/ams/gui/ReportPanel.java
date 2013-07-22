/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.gui;

import ams.dao.TblDosenDao;
import ams.dao.TblFakultasDao;
import ams.dao.TblJurusanDao;
import ams.entity.TblDosen;
import ams.entity.TblFakultas;
import ams.entity.TblJurusan;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author Bayu
 */
public class ReportPanel extends javax.swing.JPanel {

    /**
     * Creates new form ReportPanel
     */
    TblFakultasDao fakdao = new TblFakultasDao();
    TblJurusanDao jurdao = new TblJurusanDao();
    TblDosenDao dosendao = new TblDosenDao();

    public ReportPanel() {
        initComponents();
        initCombo();
        initBulan();
        initMinggu();
        initReporting();
        initFakultas();
    }

    public void initCombo() {
        cmbBulan.setEnabled(false);
        cmbMinggu.setEnabled(false);
        tglAwal.setEnabled(false);
        tglAkhir.setEnabled(false);
    }

    public void initReporting() {
        cmbReport.removeAllItems();
        cmbReport.addItem("(Semua)");
        cmbReport.addItem("Bulanan");
        cmbReport.addItem("Mingguan");
        cmbReport.addItem("Harian");
        cmbReport.addItem("Custom");
    }

    public void initBulan() {
        cmbBulan.removeAllItems();
        cmbBulan.addItem("Januari");
        cmbBulan.addItem("Februari");
        cmbBulan.addItem("Maret");
        cmbBulan.addItem("April");
        cmbBulan.addItem("Mei");
        cmbBulan.addItem("Juni");
        cmbBulan.addItem("Juli");
        cmbBulan.addItem("Agustus");
        cmbBulan.addItem("September");
        cmbBulan.addItem("Oktober");
        cmbBulan.addItem("November");
        cmbBulan.addItem("Desember");
    }

    public void initMinggu() {
        cmbMinggu.removeAllItems();
        cmbMinggu.addItem("Minggu Ke-1");
        cmbMinggu.addItem("Minggu Ke-2");
        cmbMinggu.addItem("Minggu Ke-3");
        cmbMinggu.addItem("Minggu Ke-4");
        cmbMinggu.addItem("Minggu Ke-5");
    }

    public void initFakultas() {
        cmbFakultas.removeAllItems();
        Iterator it = fakdao.getAllFakultas().iterator();
        cmbFakultas.addItem("(Semua)");
        while (it.hasNext()) {
            TblFakultas data = (TblFakultas) it.next();
            cmbFakultas.addItem(data.getFakultas());
        }
    }

    public void initJurusan(String fak) {
        cmbJurusan.removeAllItems();
        Iterator it = jurdao.getJurusanByFak(fak).iterator();
        cmbJurusan.addItem("(Semua)");
        while (it.hasNext()) {
            TblJurusan data = (TblJurusan) it.next();
            cmbJurusan.addItem(data.getJurusan());
        }
    }

    public void initDosen(String jur) {
        cmbDosen.removeAllItems();
        Iterator it = dosendao.getDosenByJur(jur).iterator();
        cmbDosen.addItem("(Semua)");
        while (it.hasNext()) {
            TblDosen data = (TblDosen) it.next();
            cmbDosen.addItem(data.getNamaDosen());
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

        jLabel1 = new javax.swing.JLabel();
        cmbReport = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cmbFakultas = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cmbJurusan = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cmbDosen = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        tglAwal = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        tglAkhir = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbMinggu = new javax.swing.JComboBox();
        cmbBulan = new javax.swing.JComboBox();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();

        jLabel1.setText("Reporting");

        cmbReport.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbReport.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbReportItemStateChanged(evt);
            }
        });

        jLabel2.setText("Fakultas");

        cmbFakultas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbFakultas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFakultasItemStateChanged(evt);
            }
        });

        jLabel3.setText("Jurusan");

        cmbJurusan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbJurusan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJurusanItemStateChanged(evt);
            }
        });

        jLabel4.setText("Dosen");

        cmbDosen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Tanggal");

        jLabel6.setText("s.d.");

        jButton1.setText("View Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel7.setText("Bulan");

        jLabel8.setText("Minggu");

        cmbMinggu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbBulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(cmbReport, 0, 161, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(cmbFakultas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbJurusan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbDosen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(tglAkhir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbMinggu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tglAwal, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(cmbBulan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, 89, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbReport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cmbFakultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbMinggu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tglAwal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tglAkhir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDosen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(135, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int er = 0;
        if (tglAwal.isEnabled() == true) {
            if (tglAwal.getDate() == null) {
                er = er + 1;
                JOptionPane.showMessageDialog(this, "Tanggal Harus Diisi", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (tglAkhir.isEnabled() == true) {
            if (tglAkhir.getDate() == null) {
                er = er + 1;
                JOptionPane.showMessageDialog(this, "Tanggal Harus Diisi", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (er == 0) {
            //Action Cetak Report
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2013,0,1);
        cal.set(Calendar.WEEK_OF_MONTH, 3);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        System.out.println(sdf.format(cal.getTime()));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbFakultasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFakultasItemStateChanged
        // TODO add your handling code here:
        if (cmbFakultas.getSelectedIndex() != -1) {
            initJurusan(cmbFakultas.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cmbFakultasItemStateChanged

    private void cmbJurusanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJurusanItemStateChanged
        // TODO add your handling code here:
        if (cmbJurusan.getSelectedIndex() != -1) {
            initDosen(cmbJurusan.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cmbJurusanItemStateChanged

    private void cmbReportItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbReportItemStateChanged
        // TODO add your handling code here:
        initCombo();
        if (cmbReport.getSelectedIndex() == 1) {
            cmbBulan.setEnabled(true);
        } else if (cmbReport.getSelectedIndex() == 2) {
            cmbMinggu.setEnabled(true);
        } else if (cmbReport.getSelectedIndex() == 3) {
            tglAwal.setEnabled(true);
        } else if (cmbReport.getSelectedIndex() == 4) {
            tglAwal.setEnabled(true);
            tglAkhir.setEnabled(true);
        }
    }//GEN-LAST:event_cmbReportItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbBulan;
    private javax.swing.JComboBox cmbDosen;
    private javax.swing.JComboBox cmbFakultas;
    private javax.swing.JComboBox cmbJurusan;
    private javax.swing.JComboBox cmbMinggu;
    private javax.swing.JComboBox cmbReport;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private com.toedter.calendar.JDateChooser tglAkhir;
    private com.toedter.calendar.JDateChooser tglAwal;
    // End of variables declaration//GEN-END:variables
}
