/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;
import com.koneksi.koneksi;
import static com.koneksi.koneksi.conn;
import static com.koneksi.koneksi.pst;
import static com.koneksi.koneksi.res;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ambilCustomercomboItem {
    
   public HashMap<String, Integer> populateCombo(){
      Connection con = koneksi.koneksi();
      HashMap<String, Integer> map = new HashMap<String, Integer>();
      comboItem cmi;
       try {
           String query_customer = ("SELECT `id_customer`, `nama_customer` FROM `customer` ORDER BY id_customer ASC");
           PreparedStatement stt = conn.prepareStatement(query_customer);
           ResultSet res = stt.executeQuery();
           while(res.next()){
               cmi = new comboItem(res.getInt(1), res.getString(2));
               map.put(cmi.getCatName(), cmi.getCatId());
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(ambilCustomercomboItem.class.getName()).log(Level.SEVERE, null, ex);
       }
      
       return map;
   }
}