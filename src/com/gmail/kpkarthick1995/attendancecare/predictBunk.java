package com.gmail.kpkarthick1995.attendancecare;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class predictBunk extends Activity {

	private int maxDays;
	private int setview=4;
    private Button pickNum;
    private TextView preWeek;
    private Button  mon, tue, fri, wed, thu, sat;
	private Button mon1, mon2, mon3, mon4, mon5, mon6, mon7, mon8, mon9, mon10;
	private Button tue1, tue2, tue3, tue4, tue5, tue6, tue7, tue8, tue9, tue10;
	private Button wed1, wed2, wed3, wed4, wed5, wed6, wed7, wed8, wed9, wed10;
	private Button thu1, thu2, thu3, thu4, thu5, thu6, thu7, thu8, thu9, thu10;
	private Button fri1, fri2, fri3, fri4, fri5, fri6, fri7, fri8, fri9, fri10;
	private Button sat1, sat2, sat3, sat4, sat5, sat6, sat7, sat8, sat9, sat10;
	private List<String> mondayNames;
	private List<String> tuesdayNames;
	private List<String> wednesdayNames;
	private List<String> thursdayNames;
	private List<String> fridayNames;
	private List<String> saturdayNames;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.markattendence);
		SetView();
		//declaration();
	
		getActionBar().setDisplayHomeAsUpEnabled(true);
		

	}
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
		NavUtils.navigateUpFromSameTask(this);
		return true;
		}
		  return super.onOptionsItemSelected(item);	
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
						mondayNames.add("free1111");
					} else if (str1[0].equals("tue")) {
						tuesdayNames.add("free1111");
					} else if (str1[0].equals("wed")) {
						wednesdayNames.add("free1111");
					} else if (str1[0].equals("thu")) {
						thursdayNames.add("free1111");
					} else if (str1[0].equals("fri")) {
						fridayNames.add("free1111");
					} else if (str1[0].equals("sat")) {
						saturdayNames.add("free1111");
					}
				}

				else {
					if (str1[0].equals("mon")) {
						mondayNames.add(str1[3]);

					} else if (str1[0].equals("tue")) {
						tuesdayNames.add(str1[3]);
					} else if (str1[0].equals("wed")) {
						wednesdayNames.add(str1[3]);
					} else if (str1[0].equals("thu")) {
						thursdayNames.add(str1[3]);
					} else if (str1[0].equals("fri")) {
						fridayNames.add(str1[3]);
					} else if (str1[0].equals("sat")) {
						saturdayNames.add(str1[3]);
					}
				}
			}
			in.close();
              
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
public void declaration(){
	mon = (Button) findViewById(R.id.mon);
	mon1 = (Button) findViewById(R.id.mon1);
	mon2 = (Button) findViewById(R.id.mon2);
	mon3 = (Button) findViewById(R.id.mon3);
	mon4 = (Button) findViewById(R.id.mon4);
	mon5 = (Button) findViewById(R.id.mon5);
	mon6 = (Button) findViewById(R.id.mon6);
	mon7 = (Button) findViewById(R.id.mon7);
	mon8 = (Button) findViewById(R.id.mon8);
	mon9 = (Button) findViewById(R.id.mon9);
	mon10 = (Button) findViewById(R.id.mon10);

	tue = (Button) findViewById(R.id.tue);
	tue1 = (Button) findViewById(R.id.tue1);
	tue2 = (Button) findViewById(R.id.tue2);
	tue3 = (Button) findViewById(R.id.tue3);
	tue4 = (Button) findViewById(R.id.tue4);
	tue5 = (Button) findViewById(R.id.tue5);
	tue6 = (Button) findViewById(R.id.tue6);
	tue7 = (Button) findViewById(R.id.tue7);
	tue8 = (Button) findViewById(R.id.tue8);
	tue9 = (Button) findViewById(R.id.tue9);
	tue10 = (Button) findViewById(R.id.tue10);

	wed = (Button) findViewById(R.id.wed);
	wed1 = (Button) findViewById(R.id.wed1);
	wed2 = (Button) findViewById(R.id.wed2);
	wed3 = (Button) findViewById(R.id.wed3);
	wed4 = (Button) findViewById(R.id.wed4);
	wed5 = (Button) findViewById(R.id.wed5);
	wed6 = (Button) findViewById(R.id.wed6);
	wed7 = (Button) findViewById(R.id.wed7);
	wed8 = (Button) findViewById(R.id.wed8);
	wed9 = (Button) findViewById(R.id.wed9);
	wed10 = (Button) findViewById(R.id.wed10);

	thu = (Button) findViewById(R.id.thu);
	thu1 = (Button) findViewById(R.id.thu1);
	thu2 = (Button) findViewById(R.id.thu2);
	thu3 = (Button) findViewById(R.id.thu3);
	thu4 = (Button) findViewById(R.id.thu4);
	thu5 = (Button) findViewById(R.id.thu5);
	thu6 = (Button) findViewById(R.id.thu6);
	thu7 = (Button) findViewById(R.id.thu7);
	thu8 = (Button) findViewById(R.id.thu8);
	thu9 = (Button) findViewById(R.id.thu9);
	thu10 = (Button) findViewById(R.id.thu10);

	fri = (Button) findViewById(R.id.fri);
	fri1 = (Button) findViewById(R.id.fri1);
	fri2 = (Button) findViewById(R.id.fri2);
	fri3 = (Button) findViewById(R.id.fri3);
	fri4 = (Button) findViewById(R.id.fri4);
	fri5 = (Button) findViewById(R.id.fri5);
	fri6 = (Button) findViewById(R.id.fri6);
	fri7 = (Button) findViewById(R.id.fri7);
	fri8 = (Button) findViewById(R.id.fri8);
	fri9 = (Button) findViewById(R.id.fri9);
	fri10 = (Button) findViewById(R.id.fri10);

	sat = (Button) findViewById(R.id.satt);
	sat1 = (Button) findViewById(R.id.sat1);
	sat2 = (Button) findViewById(R.id.sat2);
	sat3 = (Button) findViewById(R.id.sat3);
	sat4 = (Button) findViewById(R.id.sat4);
	sat5 = (Button) findViewById(R.id.sat5);
	sat6 = (Button) findViewById(R.id.sat6);
	sat7 = (Button) findViewById(R.id.sat7);
	sat8 = (Button) findViewById(R.id.sat8);
	sat9 = (Button) findViewById(R.id.sat9);
	sat10 = (Button) findViewById(R.id.sat10);
	
	pickNum = (Button) findViewById(R.id.cal);
	preWeek = (TextView) findViewById(R.id.weekText);
	mondayNames = new LinkedList<String>();
	tuesdayNames = new LinkedList<String>();
	wednesdayNames = new LinkedList<String>();
	thursdayNames = new LinkedList<String>();	
	fridayNames = new LinkedList<String>();	
	saturdayNames = new LinkedList<String>();
}

/*public void setUpButtons() {
	fileName = "week" + weekBet + ".txt";
	try {
		in = openFileInput(fileName);
		if (in != null) {
			BufferedReader buffRead = new BufferedReader(
					new InputStreamReader(in));

			String[] splitRead = new String[4];
			String str;
			while ((str = buffRead.readLine()) != null) {
				splitRead = str.toString().split(" ");
				Log.d("filter", "splitRead:" + splitRead[0] + splitRead[1]
						+ splitRead[2] + splitRead[3]);

				Log.d("filter", "check:" + splitRead[4]);

				for (int i = 0; i < 4; i++) {
					if (splitRead[2].equals("mon" + i)) {
						mon(i, splitRead[4]);
					} else if (splitRead[2].equals("tue" + i)) {
						tue(i, splitRead[4]);
					} else if (splitRead[2].equals("wed" + i)) {
						wed(i, splitRead[4]);
					}

					else if (splitRead[2].equals("thu" + i)) {
						thu(i, splitRead[4]);
					} else if (splitRead[2].equals("fri" + i)) {
						fri(i, splitRead[4]);
					} else if (splitRead[2].equals("sat" + i)) {
						sat(i, splitRead[4]);
					}
				}
			}
		}

	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}*/
	public void SetView() {
		if (setview == 4) {
			setContentView(R.layout.markattendence);
			MarkAttendence4 m4 = new MarkAttendence4();
			m4.declaration();
			m4.readPeriodd();
			m4.goSetButtonText();
		} else if (setview == 5) {
			setContentView(R.layout.markattendence5);
		} else if (setview == 6) {
			setContentView(R.layout.markattendence6);
		} else if (setview == 7) {
			setContentView(R.layout.markattendence7);
		} else if (setview == 8) {
			setContentView(R.layout.markattendence8);
		} else if (setview == 9) {
			setContentView(R.layout.markattendence9);
		} else if (setview == 10) {
			setContentView(R.layout.markattendence10);
		}
	}
}
