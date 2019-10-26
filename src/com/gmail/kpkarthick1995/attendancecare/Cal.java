package com.gmail.kpkarthick1995.attendancecare;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;
import android.widget.CalendarView.OnDateChangeListener;

public class Cal extends Activity implements OnDateChangeListener,
		OnClickListener {

	private CalendarView calView;
	private String dateYear;
	private Button set;
	private Button cancel;
	private Calendar callOld,callNew ;
	private int weekBet,weekBet1;
	private int yearCal;
	private int monthCal;
	private int dateCal;
	private int yearNew;
	private int monthNew;
	private int dateNew;
	private Date d;
	private allData ad;
	private int screen;
	private boolean check=false;
	public static final int SHORT_DELAY=1000;
  private Set<String> allPeriods = new HashSet<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cal);
		getIntentt();
		
		
		declare();	
	
		setListenerr();
		
	}
	
	public void declare(){
		callOld = new GregorianCalendar(yearCal, monthCal, dateCal);
		set = (Button) findViewById(R.id.set);
		cancel = (Button) findViewById(R.id.cancel);
		calView = (CalendarView) findViewById(R.id.calPerm);
	}
public void setListenerr(){
	calView.setOnDateChangeListener(this);
	set.setOnClickListener(this);
	cancel.setOnClickListener(this);

}
	public void getIntentt() {
	
		dateCal =((allData) this.getApplication()).getDate();
		yearCal = ((allData) this.getApplication()).getYear();
		monthCal =((allData) this.getApplication()).getMonth();
		screen = ((allData) this.getApplication()).getVariety();
	}
	public void setWeekBet(){
	  
		weekBet = callNew.get(callNew.WEEK_OF_YEAR)
				- callOld.get(callOld.WEEK_OF_YEAR);
		Log.d("filter", "weekbet:" + weekBet);
		if (weekBet > 0) {
			
		}//
		else if (weekBet < 0) {
			int yearPresent = Calendar.getInstance().get(Calendar.YEAR);
			int yearSet = callOld.get(callOld.YEAR);
			
			/*if (yearSet == yearPresent || yearSet > yearPresent) {
				Toast toast = Toast.makeText(this,
						getString(R.string.dateNotSet), Toast.LENGTH_SHORT);
				toast.show();
				
		
			}*/
			 if (yearSet < yearPresent) {
				Calendar cal = Calendar.getInstance();
				cal.set(yearSet, 11, 31);
				int val = cal.get(Calendar.DAY_OF_WEEK);
				
				if (val == 7) {
					int weekBet = 53 - callOld.get(callOld.WEEK_OF_YEAR)
							+ callNew.get(callNew.WEEK_OF_YEAR);
					this.weekBet = weekBet;
					
				} else if (val < 7) {
					int weekBet = 52 - callOld.get(callOld.WEEK_OF_YEAR)
							+ callNew.get(callNew.WEEK_OF_YEAR);
					this.weekBet = weekBet;
				   
				}
			
			}
		}
	
		
	}
	public void setWeekBet1(int weekNow){
		  
		weekBet1 = weekNow
				- callOld.get(callOld.WEEK_OF_YEAR);
		Log.d("filter", "weekbet:" + weekBet);
		if (weekBet1 > 0) {
			
		}//
		else if (weekBet1 < 0) {
			int yearPresent = Calendar.getInstance().get(Calendar.YEAR);
			int yearSet = callOld.get(callOld.YEAR);
			
			/*if (yearSet == yearPresent || yearSet > yearPresent) {
				Toast toast = Toast.makeText(this,
						getString(R.string.dateNotSet), Toast.LENGTH_SHORT);
				toast.show();
				
		
			}*/
			 if (yearSet < yearPresent) {
				Calendar cal = Calendar.getInstance();
				cal.set(yearSet, 11, 31);
				int val = cal.get(Calendar.DAY_OF_WEEK);
				
				if (val == 7) {
					int weekBet1 = 53 - callOld.get(callOld.WEEK_OF_YEAR)
							+ weekNow;
					this.weekBet1 = weekBet1;
					
				} else if (val < 7) {
					int weekBet1 = 52 - callOld.get(callOld.WEEK_OF_YEAR)
							+ weekNow;
					this.weekBet1 = weekBet1;
				   
				}
			
			}
		}
	
		
	}
	@Override
	public void onSelectedDayChange(CalendarView view, int year, int month,
			int day) {
		callNew = new GregorianCalendar(year, month, day);
           
		month = month + 1;
		//weekBet = call1.get(call1.WEEK_OF_YEAR) - call.get(call.WEEK_OF_YEAR);
		
		setWeekBet();
		
		if(weekBet>=0){
		Calendar weekPres = Calendar.getInstance();
		int weekP = weekPres.get(Calendar.WEEK_OF_YEAR);
		setWeekBet1(weekP);
		Log.d("filter","The present week is "+weekBet1);
		     if(weekBet1<weekBet){
		     	Toast toast = Toast.makeText(this,
					getString(R.string.later), Toast.LENGTH_SHORT);
			    toast.show();
		
	
		                   } 
		    else{
			Toast toast = Toast.makeText(this,"week selected:"+(weekBet+1), SHORT_DELAY);
			toast.show();
			yearNew = year;
			monthNew = month;
			dateNew= day;
			check=true;
		                   }
		}
		else{
			Toast toast = Toast.makeText(this,
					getString(R.string.dateNotSet), SHORT_DELAY);
			toast.show();
		}
		/*if (weekBet <= 0) {

			Toast.makeText(
					getBaseContext(),
					"week cannot be selected since its earlier than default week!!",
					Toast.LENGTH_SHORT).show();
		}

		else {
			weekBet = weekBet + 1;
			Toast.makeText(getBaseContext(), "Selected week is\n\n" + weekBet,
					Toast.LENGTH_SHORT).show();
		}*/

	}

	@Override
	public void onClick(View v) {
		
	
		
             if(screen==4){
				Intent intent4 = new Intent(Cal.this, MarkAttendence4.class);
				;
				if(v==set){
				intent4.putExtra("dateNew", dateNew);
				intent4.putExtra("monthNew", monthNew);
				intent4.putExtra("yearNew", yearNew);				
             }
				if(check || v==cancel){
				startActivity(intent4);
             }
             }
             else if(screen==5){
 				Intent intent5 = new Intent(Cal.this, MarkAttendence5.class);
 				;
 				if(v==set){
 				
 				intent5.putExtra("dateNew", dateNew);
 				intent5.putExtra("monthNew", monthNew);
 				intent5.putExtra("yearNew", yearNew);
 				}
 				if(check|| v==cancel){
 				startActivity(intent5);
              }
             }
             else if(screen==6){
  				Intent intent6 = new Intent(Cal.this, MarkAttendence6.class);
  				;
  				if(v==set){
  				intent6.putExtra("dateNew", dateNew);
  				intent6.putExtra("monthNew", monthNew);
  				intent6.putExtra("yearNew", yearNew);
  				
  				}
  				if(check|| v==cancel){
  				startActivity(intent6);
               }
             }
   
                
             else if(screen==7){
    				Intent intent7 = new Intent(Cal.this, MarkAttendence7.class);
    				;
  
    				if(v==set){
    				intent7.putExtra("dateNew", dateNew);
    				intent7.putExtra("monthNew", monthNew);
    				intent7.putExtra("yearNew", yearNew);
    				}
    				if(check|| v==cancel){
    				startActivity(intent7);
                 }
             }
             else if(screen==8){
    				Intent intent8 = new Intent(Cal.this, MarkAttendence8.class);
    				;
    				if(v==set){
    				intent8.putExtra("dateNew", dateNew);
    				intent8.putExtra("monthNew", monthNew);
    				intent8.putExtra("yearNew", yearNew);
    				}
    				if(check|| v==cancel){
    				startActivity(intent8);
                 }
             }
             else if(screen==9){
    				Intent intent9 = new Intent(Cal.this, MarkAttendence9.class);
    				;
    				if(v==set){
    				intent9.putExtra("dateNew", dateNew);
    				intent9.putExtra("monthNew", monthNew);
    				intent9.putExtra("yearNew", yearNew);
    				}
    				if(check|| v==cancel){
    				startActivity(intent9);
                 }
             }
             else if(screen==10){
    				Intent intent10 = new Intent(Cal.this, MarkAttendence10.class);
    				;
    				if(v==set){
    				intent10.putExtra("dateNew", dateNew);
    				intent10.putExtra("monthNew", monthNew);
    				intent10.putExtra("yearNew", yearNew);
    				}
    				if(check|| v==cancel){
    				startActivity(intent10);
    				}
             }
             if(check==false && v==set){
         	Toast toast = Toast.makeText(this,
    				getString(R.string.sorry), SHORT_DELAY);
    		toast.show();
             }
		}
	
	
	
	
	
	
}
