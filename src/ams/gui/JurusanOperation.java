/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.gui;

import ams.dao.TblFakultasDao;
import ams.dao.TblJurusanDao;
import ams.entity.TblFakultas;
import ams.entity.TblJurusan;
import java.util.Iterator;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Bayu
 */
public class JurusanOperation extends javax.swing.JPanel {
    
    TblJurusan jurusan;
    TblFakultasDao fakdao = new TblFakultasDao();
    JDialog a = new JDialog(new FormUtama(), "Manage Jurusan", JDialog.ModalityType.APPLICATION_MODAL);

    public JurusanOperation(TblJurusan jurusan) {
        initComponents();
        initCmbFakultas();
        initValueFakultas(jurusan);

        a.add(this);
        a.validate();
        a.pack();
        a.setVisible(true);
    }

    public void initCmbFakultas() {
        cmbFakultas.removeAllItems();
        Iterator it = fakdao.getAllFakultas().iterator();
        while (it.hasNext()) {
            TblFakultas data = (TblFakultas) it.next();
            cmbFakultas.addItem(data.getFakultas());
        }
    }

    public void initValueFakultas(TblJurusan jurusan) {
        this.jurusan = jurusan;
        if (jurusan != null) {
            txtIdJurusan.setText(new Integer(jurusan.getIdJurusan()).toString());
            txtJurusan.setText(jurusan.getJurusan());
            cmbFakultas.setSelectedItem(jurusan.getTblFakultas().getFakultas());
            txtIdJurusan.setEditable(false);
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

        cmbFakultas = new javax.swing.JComboBox();
        txtIdJurusan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtJurusan = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        cmbFakultas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Jurusan");

        jLabel4.setText("Fakultas");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Id Jurusan");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtJurusan, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtIdJurusan, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbFakultas, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdJurusan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtJurusan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbFakultas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            TblFakultasDao fakdao = new TblFakultasDao();
            TblFakultas fakul = fakdao.getTblFakByName(cmbFakultas.getSelectedItem().toString());
            String idjurusan;
            String namajurusan;
            String idfakultas;

            idjurusan = txtIdJurusan.getText();
            namajurusan = txtJurusan.getText();

            TblJurusan jrs = new TblJurusan();
            jrs.setIdJurusan(Integer.parseInt(idjurusan));
            jrs.setJurusan(namajurusan);
            jrs.setTblFakultas(fakul);

            int confirm = JOptionPane.showConfirmDialog(this, "Yakin Data Ini Ingin Disimpan?", "Simpan Data", JOptionPane.OK_CANCEL_OPTION);
            if (confirm == JOptionPane.OK_OPTION) {
                TblJurusanDao jurusandao = new TblJurusanDao();
                if (this.jurusan != null) {
                    jurusan.setIdJurusan(this.jurusan.getIdJurusan());
                    jurusandao.updateJurusan(jrs);
                    JOptionPane.showMessageDialog(this, "Data Telah Berhasil Diupdate", "Update", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    jurusandao.addJurusan(jrs);
                    JOptionPane.showMessageDialog(this, "Data Telah Berhasil Diinput", "Insert", JOptionPane.INFORMATION_MESSAGE);
                }
                //makuldao.addMakul(makul);
                //JOptionPane.showMessageDialog(this, "Data Telah Berhasil Diinput", "Insert Makul", JOptionPane.INFORMATION_MESSAGE);
                a.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Pastikan Inputan Yang Anda Masukan Benar", "Error", JOptionPane.ERROR_MESSAGE);
        }
//        new JurusanPanel().Refresh();
//        ManageJurusanGui jrsgui = new ManageJurusanGui();
//        jrsgui.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbFakultas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtIdJurusan;
    private javax.swing.JTextField txtJurusan;
    // End of variables declaration//GEN-END:variables
}
