package com.example.aditios;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ListView;

public class contactlist extends Activity  {
	ListView listView;
	public String[] Name =new String[1000];;
	public String[] Phone=new String[1000];;
	int i=0;
	DatabaseHandler db;
	CheckBox cb;
	String phone;
	static final int PICK_CONTACT=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact);
		 listView	=(ListView)findViewById(R.id.listView);
		 Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
		  startActivityForResult(intent, PICK_CONTACT);
		 db = new DatabaseHandler(this);
	//	displayContacts();
		
		
	}
	// Declare
	  

	  public void change(){
		  Intent myintent =new Intent(this,MainActivity.class);
		  startActivity(myintent);
	  }

	  //code 
	  @Override
	 public void onActivityResult(int reqCode, int resultCode, Intent data) {
	 super.onActivityResult(reqCode, resultCode, data);

	 switch (reqCode) {
	 case (PICK_CONTACT) :
	   if (resultCode == Activity.RESULT_OK) {

	     Uri contactData = data.getData();
	     Cursor c =  managedQuery(contactData, null, null, null, null);
	     if (c.moveToFirst()) {


	         String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

	         String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

	           if (hasPhone.equalsIgnoreCase("1")) {
	          Cursor phones = getContentResolver().query( 
	                       ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, 
	                       ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id, 
	                       null, null);
	             phones.moveToFirst();
	              phone = phones.getString(phones.getColumnIndex("data1"));
	             //System.out.println("number is:"+phone);
	             Log.d("phone",""+phone);
	           }
	         String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

	         db.addContact(new Contact(name, phone,1));
	     }
	   }
	   break;
	 }
change();
	 }
	
}
