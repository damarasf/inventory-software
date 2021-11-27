/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.koneksi.koneksi;
import static com.koneksi.koneksi.conn;
import static com.koneksi.koneksi.pst;
import com.model.model_inventory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class FrameGudang extends javax.swing.JFrame {
    public static String ambil_kd_barang_tambah, ambil_nama_barang_tambah,
            ambil_no_order_bukti, ambil_no_order_detail;
    

    /**
     * Creates new form FrameGudang
     */
    public FrameGudang() {
        initComponents();
        dataTableBarang();
        dataTableOrderInvoice();
        dataTableOrderInvoice();
        NoUrutBarang();
        NoUrutOrderInvoice();
        NoUrutOrderInvoice();
    }

    private void NoUrutBarang(){
        String no;
        int brs = readDataBarang.getRowCount();
            for(int j=0; j<brs;j++){
                no = String.valueOf(j+1);
                readDataBarang.setValueAt(no,j,0);
            }
    }
    
    private void NoUrutOrderInvoice(){
        String no;
        int brs = dataOrderInvoice.getRowCount();
        for(int j=0; j<brs;j++){
            no = String.valueOf(j+1);
            dataOrderInvoice.setValueAt(no,j,0);
        }
    }
    
    private void NoUrutOrderShipped(){
        String no;
        int brs = dataOrderShipped.getRowCount();
        for(int j=0; j<brs;j++){
            no = String.valueOf(j+1);
            dataOrderShipped.setValueAt(no,j,0);
        }
    }
    
    public static void dataTableOrderShipped(){
        int jumBaris =0;
        int i=0;
        conn = koneksi.koneksi();
        String query="SELECT * FROM header_order as a LEFT JOIN customer as b\n" +
                        "ON a.id_customer=b. id_customer\n" +
                        "LEFT JOIN users as c\n" +
                        "on a.id_user_gudang = c.id_users\n" +
                        "LEFT JOIN detail_order as d\n" +
                        "on a.no_order = d.no_order\n" +
                        "LEFT JOIN barang as e\n" +       
                        "on d.kd_barang = e.kd_barang\n" +
                        "WHERE a.status_order='Shipped' \n"+
                        "GROUP BY a.no_order\n" +
                        "ORDER by a.status_order DESC, d.tanggal_order DESC";

        try{
            Statement stt = conn.createStatement();
            ResultSet res = stt.executeQuery(query);
            
            while(res.next()){
                jumBaris++; 
            }
        }catch(SQLException ex){  
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan");
        } 
        
        String isi[][] = new String[jumBaris][8];
        try{
            Statement st = conn.createStatement();
            ResultSet data = st.executeQuery(query);
            while(data.next()){   
                isi[i][1] = data.getString("no_order");
                isi[i][2] = data.getString("nama_customer");
                isi[i][3] = data.getString("total_penjualan"); 
                isi[i][4] = data.getString("tanggal_order");         
                isi[i][5] = data.getString("tanggal_bayar");         
                isi[i][6] = data.getString("status_order"); 
                isi[i][7] = data.getString("nama_users"); 
                i++; 
            }
        }catch(SQLException ex){  
            JOptionPane.showMessageDialog(null, ex); 
        } 
        
        String NamaKolom[] = {"No","Nomor Order","Nama Customer","Total Harga Barang","Tanggal Order","Tanggal Bayar","Status Order","Admin Gudang"}; 
        //DefaultTableModel model = new DefaultTableModel(){}; 
        DefaultTableModel dtms = new DefaultTableModel(isi,NamaKolom){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        dataOrderShipped.setModel(dtms);
    }
    public static void dataTableOrderInvoice(){
        int jumBaris =0;
        int i=0;
        conn = koneksi.koneksi();
        String query="SELECT * FROM header_order as a LEFT JOIN customer as b\n" +
                        "ON a.id_customer=b. id_customer\n" +
                        "LEFT JOIN users as c\n" +
                        "on a.id_user_sales = c.id_users\n" +
                        "LEFT JOIN detail_order as d\n" +
                        "on a.no_order = d.no_order\n" +
                        "LEFT JOIN barang as e\n" +       
                        "on d.kd_barang = e.kd_barang\n" +
                        "WHERE a.status_order='Invoice' \n"+
                        "GROUP BY a.no_order\n" +
                        "ORDER by a.status_order DESC, d.tanggal_order DESC";

        try{
            Statement stt = conn.createStatement();
            ResultSet res = stt.executeQuery(query);
            
            while(res.next()){
                jumBaris++; 
            }
        }catch(SQLException ex){  
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan");
        } 
        
        String isi[][] = new String[jumBaris][8];
        try{
            Statement st = conn.createStatement();
            ResultSet data = st.executeQuery(query);
            while(data.next()){   
                isi[i][1] = data.getString("no_order");
                isi[i][2] = data.getString("nama_customer");
                isi[i][3] = data.getString("total_penjualan"); 
                isi[i][4] = data.getString("tanggal_order");         
                isi[i][5] = data.getString("tanggal_bayar");         
                isi[i][6] = data.getString("status_order"); 
                isi[i][7] = data.getString("nama_users"); 
                i++; 
            }
        }catch(SQLException ex){  
            JOptionPane.showMessageDialog(null, ex); 
        } 
        
        String NamaKolom[] = {"No","Nomor Order","Nama Customer","Total Harga Barang","Tanggal Order","Tanggal Bayar","Status Order","Nama Sales"}; 
        //DefaultTableModel model = new DefaultTableModel(){}; 
        DefaultTableModel dtms = new DefaultTableModel(isi,NamaKolom){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        dataOrderInvoice.setModel(dtms);
    }
    
    private void UbahDataBarang(){
        int input = JOptionPane.showOptionDialog(null, "Apakah ingin mengubah data barang?", "Ubah Data Customer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

            if(input == JOptionPane.OK_OPTION){
                 try{
                    int barisCellCekProduk = readDataBarang.getSelectedRow();
                    //int kolomCellCekProduk = readData.getSelectedColumn();    
                    String kd_barang = (String)readDataBarang.getValueAt(barisCellCekProduk, 1);   
                    String nama_barang = (String)readDataBarang.getValueAt(barisCellCekProduk, 2);
                    String harga_barang = (String)readDataBarang.getValueAt(barisCellCekProduk, 3);
                    String kualitas_barang = (String)readDataBarang.getValueAt(barisCellCekProduk, 4);
                    String stok_barang = (String)readDataBarang.getValueAt(barisCellCekProduk, 5);

                    String query_ubah_produk="UPDATE barang SET nama_barang=?, harga=?, kualitas=?, stok_barang=? WHERE kd_barang=?";
                    //String no = txtNoUbahTG.getText();
                    pst = (PreparedStatement) conn.prepareStatement(query_ubah_produk);

                    pst.setString(1, nama_barang);
                    pst.setString(2, harga_barang);
                    pst.setString(3, kualitas_barang);
                    pst.setString(4, stok_barang);
                    pst.setString(5, kd_barang);
                    pst.executeUpdate();
                    //pst.execute();
                    JOptionPane.showMessageDialog(null, "Data berhasil di update");
                }catch(SQLException | ArrayIndexOutOfBoundsException e){
                        JOptionPane.showMessageDialog(null, e);
                }
            }

    }
 
    public static void dataTableBarang(){
        int jumBaris =0;
        int i=0;
        conn = koneksi.koneksi();
        String query = "SELECT kd_barang, nama_barang, harga, kualitas, stok_barang FROM barang";
        try{
            Statement stt = conn.createStatement();
            ResultSet res = stt.executeQuery(query);
            
            while(res.next()){
                jumBaris++; 
            }
        }catch(SQLException ex){  
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada sistem");
        } 
        
        String isi[][] = new String[jumBaris][6];
        try{
            Statement st = conn.createStatement();
            ResultSet data = st.executeQuery(query);
            while(data.next()){   
                isi[i][1] = data.getString("kd_barang");         
                isi[i][2] = data.getString("nama_barang");
                isi[i][3] = data.getString("harga");
                isi[i][4] = data.getString("kualitas");         
                isi[i][5] = data.getString("stok_barang");                 
                i++;     
            } 
        }catch(SQLException ex){  
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan"); 
        } 
        
        String NamaKolom[] = {"No","Kode Barang","Nama Barang","Harga Barang","Kualitas","Stok Barang"}; 
        //DefaultTableModel model = new DefaultTableModel(){}; 
        DefaultTableModel dtms = new DefaultTableModel(isi,NamaKolom){
            @Override
            public boolean isCellEditable(int row, int column){
                //return false;
                switch(column){
                    case 0:
                        return false;
                    case 1:
                        return false;
                    default:
                        return true;
                 }
            }
        };
        readDataBarang.setModel(dtms);
    }   
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabOrder = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        b_buat_barang = new javax.swing.JButton();
        b_hapus_barang = new javax.swing.JButton();
        cetak_stok_barang = new javax.swing.JButton();
        b_refresh_barang = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        readDataBarang = new javax.swing.JTable();
        shippedOrder = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        cetak_bukti_bayar = new javax.swing.JButton();
        refresh_bukti_bayar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        dataOrderInvoice = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        cetak_detail_order = new javax.swing.JButton();
        refresh_bukti_bayar1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        dataOrderShipped = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TabOrder.setBackground(new java.awt.Color(31, 164, 171));

        jPanel1.setBackground(new java.awt.Color(31, 164, 171));

        b_buat_barang.setText("Tambah");
        b_buat_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_buat_barangActionPerformed(evt);
            }
        });

        b_hapus_barang.setText("Hapus");
        b_hapus_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_hapus_barangActionPerformed(evt);
            }
        });

        cetak_stok_barang.setText("Cetak Stok Barang");
        cetak_stok_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetak_stok_barangActionPerformed(evt);
            }
        });

        b_refresh_barang.setText("Refresh");
        b_refresh_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_refresh_barangActionPerformed(evt);
            }
        });

        readDataBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "Kode Barang", "Nama Barang", "Harga Barang", "Kualitas", "Stok Barang"
            }
        ));
        readDataBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                readDataBarangKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(readDataBarang);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(b_buat_barang)
                        .addGap(18, 18, 18)
                        .addComponent(b_hapus_barang)
                        .addGap(18, 18, 18)
                        .addComponent(cetak_stok_barang)
                        .addGap(318, 318, 318)
                        .addComponent(b_refresh_barang)
                        .addGap(11, 11, 11)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_buat_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_hapus_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cetak_stok_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_refresh_barang))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 35, Short.MAX_VALUE))
        );

        TabOrder.addTab("Produk", jPanel1);

        shippedOrder.setBackground(new java.awt.Color(31, 164, 171));

        jPanel3.setBackground(new java.awt.Color(31, 164, 171));

        cetak_bukti_bayar.setText("Cetak Faktur");
        cetak_bukti_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetak_bukti_bayarActionPerformed(evt);
            }
        });

        refresh_bukti_bayar.setText("Refresh");
        refresh_bukti_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh_bukti_bayarActionPerformed(evt);
            }
        });

        dataOrderInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No. Order", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(dataOrderInvoice);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(cetak_bukti_bayar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refresh_bukti_bayar)
                .addGap(65, 65, 65))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cetak_bukti_bayar, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(refresh_bukti_bayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        shippedOrder.addTab("Order", jPanel3);

        jPanel2.setBackground(new java.awt.Color(31, 164, 171));

        cetak_detail_order.setText("Cek Detail Order");
        cetak_detail_order.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetak_detail_orderActionPerformed(evt);
            }
        });

        refresh_bukti_bayar1.setText("Refresh");
        refresh_bukti_bayar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh_bukti_bayar1ActionPerformed(evt);
            }
        });

        dataOrderShipped.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No Order", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(dataOrderShipped);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(cetak_detail_order)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refresh_bukti_bayar1)
                .addGap(57, 57, 57))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cetak_detail_order, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refresh_bukti_bayar1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        shippedOrder.addTab("Shipped Order", jPanel2);

        TabOrder.addTab("Order", shippedOrder);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabOrder, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabOrder, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void b_refresh_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_refresh_barangActionPerformed
        // TODO add your handling code here:
        NoUrutBarang();
        dataTableBarang();
    }//GEN-LAST:event_b_refresh_barangActionPerformed

    private void cetak_stok_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetak_stok_barangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cetak_stok_barangActionPerformed

    private void b_buat_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_buat_barangActionPerformed
        // TODO add your handling code here:
        TambahBarang tb = new TambahBarang();
        tb.setVisible(true);
    }//GEN-LAST:event_b_buat_barangActionPerformed

    private void refresh_bukti_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh_bukti_bayarActionPerformed
        // TODO add your handling code here:
        dataTableOrderInvoice();
        NoUrutOrderInvoice();
    }//GEN-LAST:event_refresh_bukti_bayarActionPerformed

    private void cetak_detail_orderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetak_detail_orderActionPerformed
        // TODO add your handling code here:
        int tabelData = dataOrderShipped.getSelectedRow();
        if(dataOrderShipped.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Pilih datanya pada tabel !!!");
        }else{
            ambil_no_order_detail = dataOrderShipped.getValueAt(tabelData, 1).toString();
            try{
                DetailOrderShipped detailor = new DetailOrderShipped();
                detailor.setVisible(true);
            }catch(Exception ex){
                Logger.getLogger(FrameSales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cetak_detail_orderActionPerformed

    private void cetak_bukti_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetak_bukti_bayarActionPerformed
        // TODO add your handling code here:
        int tabelData = dataOrderInvoice.getSelectedRow();
        if(dataOrderInvoice.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Pilih Datanya pada tabel!!!");
        }else{
            ambil_no_order_bukti = dataOrderInvoice.getValueAt(tabelData, 1).toString();
            try{
                DetailOrderGudang detaildang = new DetailOrderGudang();
                detaildang.setVisible(true);
            }catch(Exception ex){
                Logger.getLogger(FrameSales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cetak_bukti_bayarActionPerformed

    private void refresh_bukti_bayar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh_bukti_bayar1ActionPerformed
        // TODO add your handling code here:
        dataTableOrderShipped();
        NoUrutOrderShipped();
    }//GEN-LAST:event_refresh_bukti_bayar1ActionPerformed

    private void b_hapus_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_hapus_barangActionPerformed
        // TODO add your handling code here:
        model_inventory mi = new model_inventory();
        try {
            mi.HapusBarang(this);
        } catch (SQLException ex) {
            Logger.getLogger(FrameGudang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_b_hapus_barangActionPerformed

    private void readDataBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_readDataBarangKeyReleased
        // TODO add your handling code here:
        UbahDataBarang();
        dataTableBarang();
        NoUrutBarang();
    }//GEN-LAST:event_readDataBarangKeyReleased

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
            java.util.logging.Logger.getLogger(FrameGudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameGudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameGudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameGudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameGudang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TabOrder;
    private javax.swing.JButton b_buat_barang;
    private javax.swing.JButton b_hapus_barang;
    private javax.swing.JButton b_refresh_barang;
    private javax.swing.JButton cetak_bukti_bayar;
    private javax.swing.JButton cetak_detail_order;
    private javax.swing.JButton cetak_stok_barang;
    public static javax.swing.JTable dataOrderInvoice;
    public static javax.swing.JTable dataOrderShipped;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable readDataBarang;
    private javax.swing.JButton refresh_bukti_bayar;
    private javax.swing.JButton refresh_bukti_bayar1;
    private javax.swing.JTabbedPane shippedOrder;
    // End of variables declaration//GEN-END:variables
}
