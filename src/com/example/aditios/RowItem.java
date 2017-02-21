package com.example.aditios;

import android.util.Log;

public class RowItem {
   
    private String name;
    private String phoneNo;
    
    public RowItem(String name, String phoneNo) {
        
        this.name = name;
        this.phoneNo= phoneNo;
        
    }
    
    public String getPhoneNo() {
    	Log.d("phone", ""+phoneNo);
        return phoneNo;
    }
    
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getname() {
    	Log.d("name", ""+name);
        return name;
    }
    public void setname(String name) {
        this.name =name;
    }
    @Override
    public String toString() {
        return name + "\n" + "\n" +phoneNo+"\n";
    }
}
