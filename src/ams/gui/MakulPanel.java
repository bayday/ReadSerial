/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.gui;

import ams.dao.TblMakulDao;
import ams.entity.TblMakul;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bayu
 */
public class MakulPanel extends javax.swing.JPanel {

    /**
     * Creates new form MakulPanel
     */
    TblMakulDao makuldao = new TblMakulDao();

    public MakulPanel() {
        initComponents();
        displayResult(makuldao.getAllMakul());
    }

    private void displayResult(List resultList) {
        Vector<String> tableHeaders = new Vector<String>();
        Vector tableData = new Vector();
        tableHeaders.add("ID Makul");
        tableHeaders.add("Mata Kuliah");
//        tableHeaders.add("Jurusan");

        for (Object o : resultList) {
            TblMakul makul = (TblMakul) o;
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(makul.getIdMakul());
            oneRow.add(makul.getMataKuliah());
//            oneRow.add(makul.getTblJurusan().getJurusan());
            tableData.add(oneRow);
        }
        resultTable.setModel(new DefaultTableModel(tableData, tableHeaders) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    public void Refresh() {
        displayResult(makuldao.getAllMakul());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        txtCari = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLabel2.setText("Cari Mata Kuliah");

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

        txtCari.setToolTipText("Cari Mata Kuliah");
        txtCari.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCariCaretUpdate(evt);
            }
        });

        jButton1.setText("Insert");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCari)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton3)
                                .addComponent(jButton2)
                                .addComponent(jButton1)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCariCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCariCaretUpdate
        // TODO add your handling code here:
        //System.out.println("test");
        displayResult(makuldao.getCariMakul(txtCari.getText()));
        //makuldao.getCariMakul(txtCari.getText());
    }//GEN-LAST:event_txtCariCaretUpdate

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        MakulOperation in = new MakulOperation(null);
//        in.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int rowindex = resultTable.convertRowIndexToModel(resultTable.getSelectedRow());
        //System.out.println(resultTable.getValueAt(rowindex, 0));
        //System.out.println(rowindex);
        if (rowindex != -1) {
            TblMakul test = makuldao.getMakulById(resultTable.getValueAt(rowindex, 0).toString());
            MakulOperation in = new MakulOperation(test);
//            in.setVisible(true);
//            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih Data Yang Ingin Diupdate", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int rowindex = resultTable.convertRowIndexToModel(resultTable.getSelectedRow());
        if (rowindex != -1) {
            int a = JOptionPane.showConfirmDialog(this, "Yakin Data Ini Ingin Dihapus? ", "Delete", JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION) {
                TblMakul test = makuldao.getMakulById(resultTable.getValueAt(rowindex, 0).toString());
                makuldao.deleteMakul(test.getIdMakul());
                Refresh();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih Data Yang Ingin Didelete", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable resultTable;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
