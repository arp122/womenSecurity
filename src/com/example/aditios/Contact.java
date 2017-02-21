package com.example.aditios;

public class Contact {
	
	//private variables
	int _id;
	String _name;
	String _phone;
	int flag;
	
	// Empty constructor
	public Contact(){
		
	}
	// constructor
	public Contact(int id, String name, String _phone,int flag){
		this._id = id;
		this._name = name;
		this._phone = _phone;
		this.flag=flag;
	}
	
	// constructor
	public Contact(String name, String phoneNo,int flag){
		this._name = name;
		this._phone = phoneNo;
		this.flag=flag;
	}
	// getting ID
	public int getID(){
		return this._id;
	}
	
	// setting id
	public void setID(int id){
		this._id = id;
	}
	
	// getting name
	public String getName(){
		return this._name;
	}
	
	// setting name
	public void setName(String name){
		this._name = name;
	}
	
	// getting phone number
	public String getPhone(){
		return this._phone;
	}
	
	// setting phone number
	public void setPhone(String phone){
		this._phone = phone;
	}
	public int getFlag(){
		return this.flag;
	}
	
	// setting phone number
	public void setFlag(int flag){
		this.flag = flag;
	}
}
