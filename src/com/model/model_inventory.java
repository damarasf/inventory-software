/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controller.controller_inventory;
import com.koneksi.koneksi;
import static com.koneksi.koneksi.conn;
import static com.koneksi.koneksi.pst;
import static com.koneksi.koneksi.res;
import com.view.DetailOrderSales;
import com.view.FrameSales;
import static com.view.DetailOrderSales.dataCekDetailOrder;
import com.view.FrameGudang;
import static com.view.FrameGudang.readDataBarang;
import static com.view.LoginForm.id_user;
import com.view.TambahCustomer;
import com.view.TambahOrder;
import static com.view.FrameSales.ambil_no_order_detail;
import com.view.TambahBarang;
import static com.view.TambahOrder.auto_num_header;
import static com.view.TambahOrder.auto_num;
import static com.view.TambahOrder.dataOrder;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class model_inventory implements controller_inventory {

    @Override
    public void TambahCustomer(TambahCustomer tc) throws SQLException {
        try {
            Connection con = koneksi.koneksi();
            String sql = "insert into customer values(?,?,?,?)";
            try (PreparedStatement prepare = con.prepareStatement(sql)) {
                prepare.setString(1, tc.auto_num);
                prepare.setString(2, tc.nama_customer.getText());
                prepare.setString(3, tc.alamat.getText());
                prepare.setString(4, tc.telp.getText());
                prepare.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data Berhasil di Simpan");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void TambahBarang(TambahBarang tb) throws SQLException {
        String ambil_kd_barang_tambah = tb.kd_barang.getText();
        String ambil_nama_barang_tambah = tb.nama_barang.getText();
        String query_cek_barang = "select * from barang where kd_barang ='"+ambil_kd_barang_tambah+"'  or nama_barang='"+ambil_nama_barang_tambah+"'  ";

        PreparedStatement stt = conn.prepareStatement(query_cek_barang);
        ResultSet res = stt.executeQuery();
        if (res.next()) {
            JOptionPane.showMessageDialog(dataOrder, "Kode Barang atau Nama Barang sudah ada");
        } else {
            try {
                Connection con = koneksi.koneksi();
                String sql = "insert into barang values(?,?,?,?,?)";
                try (PreparedStatement prepare = con.prepareStatement(sql)) {
                    prepare.setString(1, tb.kd_barang.getText());
                    prepare.setString(2, tb.nama_barang.getText());
                    prepare.setString(3, tb.harga_barang.getText());
                    prepare.setString(4, tb.rate_barang.getText());
                    prepare.setString(5, tb.stok_barang.getText());
                    prepare.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Data Berhasil di Simpan");
                }
            } catch (HeadlessException | SQLException e) {
                System.out.println(e);
            }
        }

    }
    
    @Override
    public void HapusBarang(FrameGudang fg) throws SQLException {
        try {
            int selectedOption = JOptionPane.showConfirmDialog(null, "Data ingin dihapus? ?",
                    "Hapus Data", JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {

                int getCell = readDataBarang.getSelectedRow();
                String kirim_kd_barang = (String) readDataBarang.getValueAt(getCell, 1);

                String queryDelete = "DELETE FROM barang where kd_barang=?";
                pst = conn.prepareStatement(queryDelete);
                pst.setString(1, kirim_kd_barang);
                pst.execute();

                FrameGudang.dataTableBarang();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @Override
    public void TambahOrder(TambahOrder to) throws SQLException {
        String ambil_kd_barang_stok;
        String ambil_kuantitas_stok;
        int ambil_stock_barang = 0, stok_ambil = 0, sisa_stok;
        String id_customer;
        try {
            int barisan_stok = dataOrder.getRowCount();
            for (int baris_stok = 0; baris_stok < barisan_stok; baris_stok++) {
                ambil_kd_barang_stok = (String) dataOrder.getValueAt(baris_stok, 0);
                ambil_kuantitas_stok = (String) dataOrder.getValueAt(baris_stok, 3);

                Integer kuantitas_barang_stok = Integer.parseInt(ambil_kuantitas_stok);
                String ambil_stok = "select stok_barang from barang where kd_barang='" + ambil_kd_barang_stok + "'";
                Statement stt = conn.createStatement();
                ResultSet res = stt.executeQuery(ambil_stok);

                while (res.next()) {
                    ambil_stock_barang = res.getInt("stok_barang");
                }
                stok_ambil = kuantitas_barang_stok;
            }

            if (ambil_stock_barang >= stok_ambil) {
                int rows_stok = dataOrder.getRowCount();
                for (int row_stok = 0; row_stok < rows_stok; row_stok++) {
                    ambil_kd_barang_stok = (String) dataOrder.getValueAt(row_stok, 0);
                    ambil_kuantitas_stok = (String) dataOrder.getValueAt(row_stok, 3);

                    Integer kuantitas_barang_stok = Integer.parseInt(ambil_kuantitas_stok);
                    String ambil_stok = "select stok_barang from barang where kd_barang='" + ambil_kd_barang_stok + "'";
                    Statement stt = conn.createStatement();
                    ResultSet res = stt.executeQuery(ambil_stok);

                    while (res.next()) {
                        ambil_stock_barang = res.getInt("stok_barang");
                    }
                    stok_ambil = kuantitas_barang_stok;
                    sisa_stok = ambil_stock_barang - stok_ambil;

                    Connection con = koneksi.koneksi();
                    String query_ubah_stock = "UPDATE barang SET stok_barang=? WHERE kd_barang='" + ambil_kd_barang_stok + "'";
                    pst = conn.prepareStatement(query_ubah_stock);
                    pst.setInt(1, sisa_stok);
                    pst.executeUpdate();
                }

                id_customer = TambahOrder.id_customer;

                String ambil_id_customer = "SELECT id_customer FROM customer where id_customer='" + id_customer + "'";
                pst = conn.prepareStatement(ambil_id_customer);
                res = pst.executeQuery();

                if (res.next()) {
                    id_customer = res.getString("id_customer");
                }

                String an2 = "SELECT MAX(no_order) FROM detail_order";
                pst = conn.prepareStatement(an2);
                res = pst.executeQuery();
                if (res.next()) {
                    int a = res.getInt(1);
                    auto_num = (Integer.toString(a + 1));
                }

                String an = "SELECT MAX(no_order) FROM header_order";
                pst = conn.prepareStatement(an);
                res = pst.executeQuery();
                if (res.next()) {
                    int a = res.getInt(1);
                    auto_num_header = (Integer.toString(a + 1));
                }

                //String asd="1";
                int total = 0, Amount;
                for (int i = 0; i < dataOrder.getRowCount(); i++) {
                    Amount = Integer.parseInt(dataOrder.getValueAt(i, 4) + "");
                    total = Amount + total;
                }

                String queryco2 = "Insert into header_order(no_order, id_customer,  id_user_sales, id_user_gudang, total_penjualan, "
                        + "tanggal_bayar, status_order) values (?,?,?,?,?,?,?)";
                pst = conn.prepareStatement(queryco2);
                pst.setString(1, auto_num_header);
                pst.setString(2, id_customer);
                pst.setString(3, id_user);
                pst.setInt(4, 0);
                pst.setInt(5, total);
                pst.setString(6, null);
                pst.setString(7, "New");
                pst.executeUpdate();

                int rows = dataOrder.getRowCount();
                for (int row = 0; row < rows; row++) {
                    //untuk challange

                    //akhir untuk challange(hilangkan if ambil_stock >= stok_ambil untuk tidak challange)       
                    String kirim_kd_barang_detail = (String) dataOrder.getValueAt(row, 0);
                    String kirim_kuantitas_detail = (String) dataOrder.getValueAt(row, 3);
                    String kirim_total_harga = (String) dataOrder.getValueAt(row, 4);
                    Timestamp date = new Timestamp(new java.util.Date().getTime());

                    String queryco = "Insert into detail_order(no_order, id_customer, tanggal_order, kd_barang, jumlah_barang, total_harga)"
                            + " values (?,?,?,?,?,?)";
                    pst = conn.prepareStatement(queryco);
                    pst.setString(1, auto_num);
                    pst.setString(2, id_customer);
                    pst.setTimestamp(3, date);
                    pst.setString(4, kirim_kd_barang_detail);
                    pst.setString(5, kirim_kuantitas_detail);
                    pst.setString(6, kirim_total_harga);
                    pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(dataOrder, "Order berhasil dimasukan");
            } else {
                JOptionPane.showMessageDialog(dataOrder, "Stok Barang Kurang");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(dataOrder, e);
        }
    }

    @Override
    public void KirimInvoice(FrameSales fs) throws SQLException {
        try {
            String query_invoice = "UPDATE header_order SET status_order=? WHERE no_order=?";

            pst = conn.prepareStatement(query_invoice);
            String status_order = "Invoice";
            String ambil_no_order = FrameSales.ambil_no_order;

            pst.setString(1, status_order);
            pst.setString(2, ambil_no_order);

            //pst.execute();
            if (pst.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Invoice sukses!!!");
                FrameSales.NoUrutOrder();
                FrameSales.dataTableOrder();
            } else {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            Logger.getLogger(FrameSales.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void HapusOrder(DetailOrderSales dor) {
        try {
            int selectedOption = JOptionPane.showConfirmDialog(null, "Data ingin dihapus? ?",
                    "Hapus Data", JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                //challange
                int getCell = dataCekDetailOrder.getRowCount();
                for (int cellHapus = 0; cellHapus < getCell; cellHapus++) {
                    String ambil_no_order_hapus = dataCekDetailOrder.getValueAt(cellHapus, 0).toString();
                    String kirim_kd_barang = (String) dataCekDetailOrder.getValueAt(cellHapus, 2);
                    String kirim_kuantitas = (String) dataCekDetailOrder.getValueAt(cellHapus, 4);

                    String ambil_stok = "select stok_barang from barang where kd_barang='" + kirim_kd_barang + "'";
                    Statement stt = conn.createStatement();
                    ResultSet res = stt.executeQuery(ambil_stok);
                    int ambil_stock_barang = 0;
                    while (res.next()) {
                        ambil_stock_barang = res.getInt("stok_barang");
                    }

                    int stok_ambil = Integer.parseInt(kirim_kuantitas);
                    int sisa_stok = ambil_stock_barang + stok_ambil;

                    String queryStok = "UPDATE barang SET stok_barang=? WHERE kd_barang=?";
                    pst = conn.prepareStatement(queryStok);
                    pst.setInt(1, sisa_stok);
                    pst.setString(2, kirim_kd_barang);
                    pst.execute();
                //akhir challange

                    //String ambil_no_order_hapus = FrameSales.ambil_no_order_hapus;
                    String queryDelete = "DELETE detail_order.*, header_order.* FROM detail_order INNER JOIN header_order WHERE "
                            + "detail_order.no_order=header_order.no_order and detail_order.no_order=?";
                    pst = conn.prepareStatement(queryDelete);
                    pst.setString(1, ambil_no_order_hapus);
                    pst.execute();

                }
                DetailOrderSales.dataTableCekDetailOrder();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void CekDetailOrder(FrameSales fs) throws SQLException {
        //String ambil_no_order_detail = FrameSales.ambil_no_order_detail;               
        String query_cek_detail_order = "select * from detail_order where no_order='" + ambil_no_order_detail + "'";
        Statement stt = conn.createStatement();
        ResultSet res = stt.executeQuery(query_cek_detail_order);
        while (res.next()) {
            String id_customer_cek_detail = res.getString("id_customer");
            String tanggal_order_cek_detail = res.getString("tanggal_order");
            String kd_barang_cek_detail = res.getString("kd_barang");
            String jumlah_barang_cek_detail = res.getString("jumlah_barang");
            String total_harga_cek_detail = res.getString("total_harga");
            DetailOrderSales detailor = new DetailOrderSales();
            detailor.setVisible(true);
        }
    }

  

    @Override
    public void BayarOrder(FrameSales fs) throws SQLException {
        try {
            String query_bayar = "UPDATE header_order SET tanggal_bayar=?, status_order=? WHERE no_order=?";
            pst = conn.prepareStatement(query_bayar);
            String status_order = "Completed";
            Timestamp date = new Timestamp(new java.util.Date().getTime());
            String ambil_no_order = FrameSales.ambil_no_order;

            pst.setTimestamp(1, date);
            pst.setString(2, status_order);
            pst.setString(3, ambil_no_order);

            if (pst.executeUpdate() >= 1) {
                JOptionPane.showMessageDialog(null, "Order berhasil dibayar");
                FrameSales.NoUrutOrder();
                FrameSales.dataTableOrder();
            } else {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            Logger.getLogger(FrameSales.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
