package com.example.aditios;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;




public class CustomBaseAdapter extends BaseAdapter 
{
	Context context;
	List<RowItem> rowItems;

DatabaseHandler db;


	

	public CustomBaseAdapter(Context context, List<RowItem> items) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.rowItems = items;
	}



	/*private view holder class*/
	private class ViewHolder {

		TextView txtName;
		TextView txtNo;
		


	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		 
		LayoutInflater mInflater = (LayoutInflater)
				context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			 db=new DatabaseHandler(context);
			holder.txtName = (TextView) convertView.findViewById(R.id.tvName);
			holder.txtNo = (TextView) convertView.findViewById(R.id.tvPhone);
			//holder.alarm = (ImageView)convertView.findViewById(R.id.ivFav);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		RowItem rowItem = (RowItem) getItem(position);
		holder.txtName.setText(rowItem.getname());
		holder.txtNo.setText(rowItem.getPhoneNo());
		
		// Log.d("details", ""+rowItem.getname()+" "+rowItem.getPhoneNo());
		return convertView;
	}
	
	
		
	
	@Override
	public int getCount() {
		return rowItems.size();
	}

	@Override
	public Object getItem(int position) {
		return rowItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return rowItems.indexOf(getItem(position));
	}
	
}