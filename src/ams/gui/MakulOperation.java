/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.gui;

import ams.dao.TblJurusanDao;
import ams.dao.TblMakulDao;
import ams.entity.TblJurusan;
import ams.entity.TblMakul;
import java.util.Iterator;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Bayu
 */
public class MakulOperation extends javax.swing.JPanel {

    TblMakul makul;
    TblJurusanDao jurdao = new TblJurusanDao();
    JDialog a = new JDialog(new FormUtama(),"Manage Mata Kuliah",JDialog.ModalityType.APPLICATION_MODAL);
    
    public MakulOperation(TblMakul makul) {
        initComponents();
        initValue(makul);
        
        a.add(this);
        a.validate();
        a.pack();
        a.setVisible(true);
    }
    
    private void initValue(TblMakul makul){
        this.makul = makul;
        if (makul != null) {
            txtIdMakul.setText(makul.getIdMakul());
            txtIdMakul.setEditable(false);
            txtNamaMakul.setText(makul.getMataKuliah());
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
        jLabel2 = new javax.swing.JLabel();
        txtIdMakul = new javax.swing.JTextField();
        txtNamaMakul = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jLabel1.setText("Id Mata Kuliah");

        jLabel2.setText("Nama Mata Kuliah");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtNamaMakul, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdMakul, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdMakul))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNamaMakul))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            if (txtIdMakul.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Pastikan Inputan Yang Anda Masukan Benar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
//                TblJurusanDao jurdao = new TblJurusanDao();
//                TblJurusan jurus = jurdao.getTblJurByName(cmbJurusan.getSelectedItem().toString());
                String idMakul;
                String nmMakul;
                idMakul = txtIdMakul.getText();
                nmMakul = txtNamaMakul.getText();

                TblMakul makul = new TblMakul();
                makul.setIdMakul(idMakul);
                makul.setMataKuliah(nmMakul);
//                makul.setTblJurusan(jurus);
                //System.out.println(this.makul);
                int confirm = JOptionPane.showConfirmDialog(this, "Yakin Data Ini Ingin Disimpan?", "Simpan Data", JOptionPane.OK_CANCEL_OPTION);
                if (confirm == JOptionPane.OK_OPTION) {
                    TblMakulDao makuldao = new TblMakulDao();
                    if (this.makul != null) {
                        makuldao.updateMakul(makul);
                        JOptionPane.showMessageDialog(this, "Data Telah Berhasil Diupdate", "Update", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        makuldao.addMakul(makul);
                        JOptionPane.showMessageDialog(this, "Data Telah Berhasil Diinput", "Insert", JOptionPane.INFORMATION_MESSAGE);
                    }
                    //makuldao.addMakul(makul);
                    //JOptionPane.showMessageDialog(this, "Data Telah Berhasil Diinput", "Insert Makul", JOptionPane.INFORMATION_MESSAGE);
                    a.dispose();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Pastikan Inputan Yang Anda Masukan Benar", "Error", JOptionPane.ERROR_MESSAGE);
        }
//        ManageMakulGui makulgui = new ManageMakulGui();
//        makulgui.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtIdMakul;
    private javax.swing.JTextField txtNamaMakul;
    // End of variables declaration//GEN-END:variables
}
