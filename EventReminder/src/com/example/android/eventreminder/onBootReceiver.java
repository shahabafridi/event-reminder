package com.example.android.eventreminder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

public class onBootReceiver extends BroadcastReceiver{
	/* Called when receiver receives an intent */
	@Override
	public void onReceive(Context context, Intent intent) { 
		EventReminderManager reminderMgr = new EventReminderManager(context); 
		eventDB dbHelper = new eventDB(context);
		dbHelper.open();
		
		Cursor cursor = dbHelper.fetchAllEvents(); 
		if(cursor != null) {
			cursor.moveToFirst(); 
			int rowIdColumnIndex = cursor.getColumnIndex(eventDB.KEY_ROWID);
			int dateTimeColumnIndex = cursor.getColumnIndex(eventDB.KEY_DATE_TIME);
			/* check is cursor passed the last record */
			while(cursor.isAfterLast() == false) { 
				Long rowId = cursor.getLong(rowIdColumnIndex);
				Log.d("OnBootReceiver", "Adding alarm from boot.");
				Log.d("OnBootReceiver", "Row Id Column Index - " + rowIdColumnIndex);
				
				String dateTime = cursor.getString(dateTimeColumnIndex);
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat(AddReminder.DATE_TIME_FORMAT);
				
				try {
					java.util.Date date = format.parse(dateTime); 
					cal.setTime(date); 
					
					reminderMgr.setReminder(rowId, cal);
				} catch (java.text.ParseException e) {
					Log.e("OnBootReceiver", e.getMessage(), e); 
				}
				cursor.moveToNext(); 
			}//end of while
			cursor.close() ; 
		}//end of if
		dbHelper.close(); 
	}//end of onReceive()
}