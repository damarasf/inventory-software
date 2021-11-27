/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.koneksi.koneksi;
import static com.koneksi.koneksi.conn;
import static com.view.FrameGudang.ambil_no_order_bukti;
import static com.view.FrameGudang.dataOrderInvoice;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lepkom4-39
 */
public class DetailOrderGudang extends javax.swing.JFrame {

    /**
     * Creates new form DetailOrderGudang
     */
    public DetailOrderGudang() {
        initComponents();
        dataTableCekDetailOrder();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cetak_bukti_faktur = new javax.swing.JButton();
        nama_shipper = new javax.swing.JTextField();
        i = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataBuktiPembayaran = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cetak_bukti_faktur.setText("Cetak Faktur");
        cetak_bukti_faktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetak_bukti_fakturActionPerformed(evt);
            }
        });

        i.setText("Nama Shipper");

        dataBuktiPembayaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No Order", "Nama Customer", "Kode Barang", "Nama Barang", "Jumlah Barang", "Total Haraga", "Tanggal Order"
            }
        ));
        jScrollPane1.setViewportView(dataBuktiPembayaran);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(i)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nama_shipper, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cetak_bukti_faktur))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nama_shipper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i))
                .addGap(18, 18, 18)
                .addComponent(cetak_bukti_faktur)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cetak_bukti_fakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetak_bukti_fakturActionPerformed
        // TODO add your handling code here:
        int tabelData = dataOrderInvoice.getSelectedRow();
        if(dataOrderInvoice.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Pilih datanya pada tabel !!!");
        }else{
            ambil_no_order_bukti = dataOrderInvoice.getValueAt(tabelData, 1).toString();
            try{
                DetailOrderGudang detaildang = new DetailOrderGudang();
                detaildang.setVisible(true);
            }catch(Exception ex){
                Logger.getLogger(FrameSales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cetak_bukti_fakturActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DetailOrderGudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailOrderGudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailOrderGudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailOrderGudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetailOrderGudang().setVisible(true);
            }
        });
    }
    
    public static void dataTableCekDetailOrder(){
        int jumBaris =0;
        int i=0;
        conn = koneksi.koneksi();
        String query="SELECT * FROM detail_order as a LEFT JOIN customer as b\n" +
                        "ON a.id_customer=b. id_customer\n" +
                        "LEFT JOIN barang as c\n" +       
                        "on a.kd_barang = c.kd_barang\n" +
                        "WHERE a.no_order ='"+ambil_no_order_bukti+"'\n" +
                        "ORDER by a.no_order DESC, a.tanggal_order DESC";

        try{
            Statement stt = conn.createStatement();
            ResultSet res = stt.executeQuery(query);
            
            while(res.next()){
                jumBaris++; 
            }
        }catch(SQLException ex){  
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan");
        } 
        //int rows=dataOrder.getRowCount();
                //for(int row = 0; row<rows; row++){

        String isi[][] = new String[jumBaris][7];
        try{
            Statement st = conn.createStatement();
            ResultSet data = st.executeQuery(query);
            int ad = 0;
            while(data.next()){   
                isi[i][0] = data.getString("no_order");
                isi[i][1] = data.getString("nama_customer"); 
                isi[i][2] = data.getString("kd_barang");         
                isi[i][3] = data.getString("nama_barang");         
                isi[i][4] = data.getString("jumlah_barang"); 
                isi[i][5] = data.getString("total_harga"); 
                isi[i][6] = data.getString("tanggal_order");
                i++; 
            } 
        }catch(SQLException ex){  
            JOptionPane.showMessageDialog(null, ex); 
        } 
        
        String NamaKolom[] = {"Nomor Order","Nama Customer","Kode Barang","Nama Barang","Jumlah Barang","Total Harga","Tanggal Order"}; 
        //DefaultTableModel model = new DefaultTableModel(){}; 
        DefaultTableModel dtms = new DefaultTableModel(isi,NamaKolom){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        dataBuktiPembayaran.setModel(dtms);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cetak_bukti_faktur;
    public static javax.swing.JTable dataBuktiPembayaran;
    private javax.swing.JLabel i;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nama_shipper;
    // End of variables declaration//GEN-END:variables
}
