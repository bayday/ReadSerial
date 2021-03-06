/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.gui;

import ams.dao.TblUserDao;
import ams.entity.TblUser;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Bayu
 */
public class UserOperation extends javax.swing.JPanel {

    TblUser user;
    JDialog a = new JDialog(new FormUtama(),"Manage User",JDialog.ModalityType.APPLICATION_MODAL);
    
    public UserOperation(TblUser user) {
        initComponents();
        initCmbHakAkses();
        initValue(user);
        
        a.add(this);
        a.validate();
        a.pack();
        a.setVisible(true);
    }
    
    private void initCmbHakAkses(){
        cmbHakAkses.removeAllItems();
        cmbHakAkses.addItem("Admin");
        cmbHakAkses.addItem("User");
    }
    
    private void initValue(TblUser user){
        this.user = user;
        if (user != null) {
            txtUsername.setText(user.getUsername());
            txtPassword.setText(user.getPassword());
            txtNama.setText(user.getNamaUser());
            cmbHakAkses.setSelectedItem(user.getHakAkses());
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
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbHakAkses = new javax.swing.JComboBox();

        jLabel1.setText("Hak Akses");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Nama");

        jLabel3.setText("Password");

        jLabel2.setText("Username");

        cmbHakAkses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel1))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtPassword)
                                .addComponent(txtNama)
                                .addComponent(cmbHakAkses, 0, 210, Short.MAX_VALUE)))))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbHakAkses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            String username;
            String password;
            String nama;
            String hakakses;

            username = txtUsername.getText();
            password = txtPassword.getText();
            nama = txtNama.getText();
            hakakses = cmbHakAkses.getSelectedItem().toString();

            if (username.equals("") || password.equals("") || nama.equals("")) {
                JOptionPane.showMessageDialog(this, "Pastikan Inputan Yang Anda Masukan Benar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                TblUser user = new TblUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setNamaUser(nama);
                user.setHakAkses(hakakses);


                int confirm = JOptionPane.showConfirmDialog(this, "Yakin Data Ini Ingin Disimpan?", "Simpan Data", JOptionPane.OK_CANCEL_OPTION);
                if (confirm == JOptionPane.OK_OPTION) {
                    TblUserDao userdao = new TblUserDao();
                    if (this.user != null) {
                        user.setIdUser(this.user.getIdUser());
                        userdao.updateUser(user);
                        JOptionPane.showMessageDialog(this, "Data Telah Berhasil Diupdate", "Update", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        userdao.addUser(user);
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
//        ManageUserGui usergui = new ManageUserGui();
//        usergui.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbHakAkses;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
