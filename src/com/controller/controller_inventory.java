/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.view.DetailOrderSales;
import com.view.FrameGudang;
import com.view.FrameSales;
import com.view.TambahBarang;
import com.view.TambahCustomer;
import com.view.TambahOrder;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public interface controller_inventory {

    public void KirimInvoice(FrameSales fs) throws SQLException;
    public void CekDetailOrder(FrameSales fs) throws SQLException;
    public void TambahBarang(TambahBarang tb) throws SQLException;
    public void TambahOrder(TambahOrder to) throws SQLException;
    public void TambahCustomer(TambahCustomer tc) throws SQLException;
    public void HapusOrder(DetailOrderSales dor) throws SQLException;
    public void HapusBarang(FrameGudang fg) throws SQLException;
    public void BayarOrder(FrameSales fs) throws SQLException;

}
