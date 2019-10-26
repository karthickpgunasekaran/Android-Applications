package com.gmail.kpkarthick1995.attendancecare;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

public class Calender extends Activity implements OnClickListener {
	private CalendarView calview;
	private Button next;
	private int date, month, year, maxDays;
	private Calendar calNew, calOld;
	private int weekBet;
	private String[] daysOfWeekNo = new String[7];
	private SimpleDateFormat df;
	private int dateNew;
	private int monthNew;
	private int yearNew;
	private List<String> mon;
	private List<String> tue;
	private List<String> wed;
	private List<String> thu ;
	private List<String> fri;
	private List<String> sat;
	private int i;
	private int retValue;
	private int variety;
    public static final int SHORT_DELAY=1000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calender);
		getIntentt();
		declaration();
		makeToastt();
		readPeriodd();
		manualCalOldSetup();

		calview.setOnDateChangeListener(new OnDateChangeListener() {

			@Override
			public void onSelectedDayChange(CalendarView view, int yyear,
					int mmonth, int dayOfMonth) {
			
				date = dayOfMonth;
				month = mmonth;
				year = yyear;
				setCalOld();
				Toast.makeText(getBaseContext(), "Week selected!!",
						SHORT_DELAY).show();

			}
		});
		next.setOnClickListener(this);
	}

	public void manualCalOldSetup() {
		Calendar calCheck = Calendar.getInstance();
		year = calCheck.get(Calendar.YEAR);
		month = calCheck.get(Calendar.MONTH);
		date = calCheck.get(Calendar.DAY_OF_MONTH);
		setCalOld();

	}

	public void setCalOld() {
		calOld = new GregorianCalendar(year, month, date);
	}

	public void readPeriodd() {

		try {
			InputStream in = openFileInput("Periods.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String str;
			String[] str1 = new String[4];
			Log.d("filter", "in  read period try block");
			while ((str = br.readLine()) != null) {

				Log.d("filter", "in  read period while block");
				str1 = str.toString().split("-");
				int parsingLen = Integer.parseInt(str1[2]);
				// int parsingPer = Integer.parseInt(str1[1]);
				Log.d("filter", "read [period]" + str1[0]);
				if (parsingLen == 0) {
					if (str1[0].equals("mon")) {
						mon.add("free1111");
					} else if (str1[0].equals("tue")) {
						tue.add("free1111");
					} else if (str1[0].equals("wed")) {
						wed.add("free1111");
					} else if (str1[0].equals("thu")) {
						thu.add("free1111");
					} else if (str1[0].equals("fri")) {
						fri.add("free1111");
					} else if (str1[0].equals("sat")) {
						sat.add("free1111");
					}
				}

				else {
					
					if (str1[0].equals("mon")) {
						mon.add(str1[3]);

					} else if (str1[0].equals("tue")) {
						tue.add(str1[3]);
					} else if (str1[0].equals("wed")) {
						wed.add(str1[3]);
					} else if (str1[0].equals("thu")) {
						thu.add(str1[3]);
					} else if (str1[0].equals("fri")) {
						fri.add(str1[3]);
					} else if (str1[0].equals("sat")) {
						sat.add(str1[3]);
					}
				}
			}
			in.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void makeToastt() {
		Toast toast = Toast.makeText(this, getString(R.string.selectWeek),
				SHORT_DELAY);
		toast.show();

	}

	public void declaration() {
		calview = (CalendarView) findViewById(R.id.calendarView1);
		next = (Button) findViewById(R.id.next);
	 mon = new LinkedList<String>();
		 tue = new LinkedList<String>();
		 wed = new LinkedList<String>();
		 thu = new LinkedList<String>();
		 fri = new LinkedList<String>();
	 sat = new LinkedList<String>();
	}

	public void getIntentt() {
		Intent intent = getIntent();
		maxDays = intent.getIntExtra("maxDays", 5);
		variety = intent.getIntExtra("variety", 7);

	}

	public void calSetup() {
		Calendar calCheck = Calendar.getInstance();
		yearNew = calCheck.get(Calendar.YEAR);
		monthNew = calCheck.get(Calendar.MONTH);
		dateNew = calCheck.get(Calendar.DAY_OF_MONTH);
		Log.d("filter", "monthReal" + monthNew + "dateReal:" + dateNew
				+ "yearReal" + yearNew);
		
		setCalNew();
	}

	public void setCalNew() {
		calNew = new GregorianCalendar(yearNew, monthNew, dateNew);
	}

	public void findWeekBet() {

		weekBet = calNew.get(calNew.WEEK_OF_YEAR)
				- calOld.get(calOld.WEEK_OF_YEAR);
		Log.d("filter", "weekbet:" + weekBet);
		if (weekBet >= 0) {
			goToSetWeek();
		}//
		else if (weekBet < 0) {
			int yearPresent = Calendar.getInstance().get(Calendar.YEAR);
			int yearSet = calOld.get(calOld.YEAR);
			if (yearSet == yearPresent || yearSet > yearPresent) {
				Toast toast = Toast.makeText(this,
						getString(R.string.dateNotSet), Toast.LENGTH_SHORT);
				toast.show();
				retValue=5;
			} else if (yearSet < yearPresent) {
				Calendar cal = Calendar.getInstance();
				cal.set(yearSet, 11, 31);
				int val = cal.get(Calendar.DAY_OF_WEEK);
				Log.d("filter", "day of week:" + val);
				if (val == 7) {
					int weekBet = 53 - calOld.get(calOld.WEEK_OF_YEAR)
							+ calNew.get(calNew.WEEK_OF_YEAR);
					this.weekBet = weekBet;
					goToSetWeek();
				} else if (val < 7) {
					int weekBet = 52 - calOld.get(calOld.WEEK_OF_YEAR)
							+ calNew.get(calNew.WEEK_OF_YEAR);
					this.weekBet = weekBet;
					goToSetWeek();
				}
			}

		}//

	}

	public void goToSetWeek() {
		retValue=0;
		for (i = weekBet; i >= 0; i--) {
			Log.d("filter", "weekbetNew:" + weekBet);
			
			if (weekBet == i) {
				findSun();
				writeData();
			} else {
				setupWeek();
				setCalNew();
				findSun();
				writeData();
			}
		}
	}

	public void setupWeek() {
		int[] doSetLast = new int[3];
		Calendar sun = (Calendar) calNew.clone();
		sun.add(Calendar.DAY_OF_WEEK,
				sun.getFirstDayOfWeek() - sun.get(Calendar.DAY_OF_WEEK));
		df = new SimpleDateFormat("dd-MM-yyyy");
		Calendar find = (Calendar) sun.clone();
		find.add(Calendar.DAY_OF_YEAR, -7);
		String var = df.format(find.getTime());
		String[] doSetLastWeek = var.split("-");
		for (int j = 0; j < 3; j++) {
			doSetLast[j] = Integer.parseInt(doSetLastWeek[j]);
			Log.d("filter", "last week is " + doSetLast[j]);
		}
		dateNew = doSetLast[0];
		monthNew = doSetLast[1] - 1;
		yearNew = doSetLast[2];

	}

	public void writeData() {
		String filename = "week" + i + ".txt";
		Log.d("filter", "file name week:" + i);
		OutputStreamWriter outputstream;
		try {
			outputstream = new OutputStreamWriter(openFileOutput(filename, 0));

			for (int i = 0; i < variety; i++) {
				outputstream.write(daysOfWeekNo[0] + " " + mon.get(i) + " "
						+ "mon" + i + " " + "PR" + " " + 1 + '\n');
				Log.d("filter", "writing:" + daysOfWeekNo[0] + " " + mon.get(i)
						+ " " + "mon" + i + " " + "PR" + " " + 1 + '\n');

				outputstream.write(daysOfWeekNo[1] + " " + tue.get(i) + " "
						+ "tue" + i + " " + "PR" + " " + 1 + '\n');
				Log.d("filter", "writing:" + daysOfWeekNo[1] + " " + tue.get(i)
						+ " " + "tue" + i + " " + "PR" + " " + 1 + '\n');
				outputstream.write(daysOfWeekNo[2] + " " + wed.get(i) + " "
						+ "wed" + i + " " + "PR" + " " + 1 + '\n');
				Log.d("filter", "writing:" + daysOfWeekNo[2] + " " + wed.get(i)
						+ " " + "wed" + i + " " + "PR" + " " + 1 + '\n');
				outputstream.write(daysOfWeekNo[3] + " " + thu.get(i) + " "
						+ "thu" + i + " " + "PR" + " " + 1 + '\n');
				Log.d("filter", "writing:" + daysOfWeekNo[3] + " " + thu.get(i)
						+ " " + "thu" + i + " " + "PR" + " " + 1 + '\n');
				outputstream.write(daysOfWeekNo[4] + " " + fri.get(i) + " "
						+ "fri" + i + " " + "PR" + " " + 1 + '\n');
				Log.d("filter", "writing:" + daysOfWeekNo[4] + " " + fri.get(i)
						+ " " + "fri" + i + " " + "PR" + " " + 1 + '\n');
				if (maxDays == 6) {
					outputstream.write(daysOfWeekNo[5] + " " + sat.get(i) + " "
							+ "sat" + i + " " + "PR" + " " + 1 + '\n');
				}
				Log.d("filter", "im in file create check up loop");

			}
			outputstream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void findSun() {
		Calendar sun = (Calendar) calNew.clone();
		sun.add(Calendar.DAY_OF_WEEK,
				sun.getFirstDayOfWeek() - sun.get(Calendar.DAY_OF_WEEK));

		df = new SimpleDateFormat("dd-MM-yyyy");
		for (int i = 1; i < 7; i++) {
			Calendar find = (Calendar) sun.clone();
			find.add(Calendar.DAY_OF_YEAR, i);
			daysOfWeekNo[i - 1] = df.format(find.getTime());

		}
	}
	public void readAndWrite() {
             Set<String> allPeriods = new HashSet<String>();
		try {
			InputStream in = openFileInput("Periods.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String str;
			String[] str1 = new String[4];
			Log.d("filter", "in  read period try block");
			while ((str = br.readLine()) != null) {

				Log.d("filter", "in  read period while block");
				str1 = str.toString().split("-");
				int parsingLen = Integer.parseInt(str1[2]);

				Log.d("filter", "read [period]" + str1[0]);
				if (parsingLen != 0) {
					if(str1[3].equals("free1111")==false){
						allPeriods.add(str1[3]);
						Log.d("filter","adding to set:"+str1[3]);
					
				}
				}
			}
			in.close();
			String[] tempArr = allPeriods.toArray(new String[allPeriods.size()]);
			OutputStreamWriter outputstream = new OutputStreamWriter(openFileOutput("PeriodIncrement.txt",
					0));
			for(int i=0;i<tempArr.length;i++){
             outputstream.write(tempArr[i] +" "+0+" "+0+'\n');             
			}
			outputstream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onClick(View v) {
		calSetup();
		Log.d("filter", "old cal:" + calOld.get(calOld.WEEK_OF_YEAR));
		Log.d("filter", "new cal:" + calNew.get(calNew.WEEK_OF_YEAR));
		findWeekBet();

		if (retValue != 5) {
			readAndWrite();
		//	((allData) this.getApplication()).setVariety(variety);
			//((allData) this.getApplication()).setData(date,month,year,maxDays);
			try {
				OutputStreamWriter out = new OutputStreamWriter(openFileOutput("setAllData.txt",0));
				out.write(variety+" "+date+" "+month+" "+year+" "+maxDays+'\n');
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Intent intent = new Intent(Calender.this, MainScreen.class);;	
			intent.putExtra("shareNot",1);
			startActivity(intent);			
		}



	}
    
}
