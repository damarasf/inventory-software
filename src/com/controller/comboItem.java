/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

/**
 *
 * @author hamim
 */
public class comboItem {

 
    
    private int CatId;
    private String CatName;
    
    public comboItem(int catId, String catName){
        this.CatId = catId;
        this.CatName = catName;
    }
    
    public int getCatId(){
        return CatId;
    }
    
    public void setCatId(int id){
        this.CatId = id;
    }
    
    public String getCatName(){
        return CatName;
    }
    
    public void setCatName(String catName){
        this.CatName = catName;
    }
}
