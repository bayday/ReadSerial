/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.gui;

import ams.dao.TblKelasDao;
import ams.entity.TblKelas;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Bayu
 */
public class KelasOperation extends javax.swing.JPanel {

    /**
     * Creates new form KelasOperatiion
     */
    TblKelas kelas;
    JDialog a = new JDialog(new FormUtama(),"Manage Kelas",JDialog.ModalityType.APPLICATION_MODAL);
    public KelasOperation(TblKelas kelas) {
        initComponents();
        initValue(kelas);
        
        a.add(this);
        a.validate();
        a.pack();
        a.setVisible(true);
    }
    
    private void initValue(TblKelas kelas){
        this.kelas=kelas;
        if (kelas != null) {
            txtKelas.setText(kelas.getKelas());
            txtAngkatan.setText(new Integer(kelas.getAngkatan()).toString());
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

        jLabel2 = new javax.swing.JLabel();
        txtKelas = new javax.swing.JTextField();
        txtAngkatan = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setText("Angkatan");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Kelas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAngkatan, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(txtKelas))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAngkatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        TblKelas kelas = new TblKelas();
        kelas.setKelas(txtKelas.getText());
        kelas.setAngkatan(Integer.parseInt(txtAngkatan.getText()));

        TblKelasDao klsdao = new TblKelasDao();
        if (this.kelas != null) {
            kelas.setIdKelas(this.kelas.getIdKelas());
            klsdao.updateKelas(kelas);
            JOptionPane.showMessageDialog(this, "Data Telah Berhasil Diupdate", "Update", JOptionPane.INFORMATION_MESSAGE);
        } else {
            klsdao.addKelas(kelas);
            JOptionPane.showMessageDialog(this, "Data Telah Berhasil Diinput", "Insert", JOptionPane.INFORMATION_MESSAGE);
        }
        //makuldao.addMakul(makul);
        //JOptionPane.showMessageDialog(this, "Data Telah Berhasil Diinput", "Insert Makul", JOptionPane.INFORMATION_MESSAGE);
        a.dispose();
//        ManageKelasGui klsgui = new ManageKelasGui();
//        klsgui.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtAngkatan;
    private javax.swing.JTextField txtKelas;
    // End of variables declaration//GEN-END:variables
}