package com.gmail.kpkarthick1995.attendancecare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class predict5 extends Activity implements OnClickListener {
	private Button mon, tue, fri, wed, thu, sat;
	private Button mon1, mon2, mon3, mon4,mon5;
	private Button tue1, tue2, tue3, tue4,tue5;
	private Button wed1, wed2, wed3, wed4,wed5;
	private Button thu1, thu2, thu3, thu4, thu5;
	private Button fri1, fri2, fri3, fri4,fri5;
	private Button sat1, sat2, sat3, sat4,sat5;
	private ImageButton nextRight;
	private ImageButton nextLeft,cal;
	private AdView adView;
	private TextView weekBox;
	private List<String> mondayNames;
	private List<String> tuesdayNames;
	private List<String> wednesdayNames;
	private List<String> thursdayNames;
	private List<String> fridayNames;
	private List<String> saturdayNames;
	private Set<Integer> weeksColl;
	private InputStream in;
	private SimpleDateFormat df;
	private String fileName;
	private int addPredict = 1;
	private OutputStreamWriter outputstream;
	private int maxDays;
	private int noOfWeeks=10;
	private int pass=1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.predict5);
		getIntentt();
		declaration();
		adView = (AdView) this.findViewById(R.id.adView);
		setVisible();
		readPeriodd();
		goSetButtonText();
		fileCreateCheckUp();
		setUpButtons();
		weekBox.setText("Predict:" + addPredict);
		setAd();
		setListener();
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
	public void setAd(){
		if(checkAd()){

		  AdRequest adRequest = new AdRequest.Builder().addTestDevice("BBFB6DFD9747D2D8B7E54250AEF6A36D").build();
		    adView.loadAd(adRequest);
		  
		    adView.setAdListener(new AdListener() {
		    	 
		    	  @Override
		    	  public void onAdOpened() {
		    		  delAd();
		    			    		 
		    		  writeAd();
		    		  setVisNul();
		    	  }
		    	});
		  
		}
		else{
		
	setVisNul();	
		}
	}
	public void setVisNul(){
		adView.setVisibility(View.GONE);
		LinearLayout.LayoutParams params = (LayoutParams) nextLeft.getLayoutParams();
		params.setMargins(0, 0, 450, 0) ;//left, top, right, bottom
		nextLeft.setLayoutParams(params);
	}
	public boolean checkAd(){
		 Calendar cal2 = Calendar.getInstance();
int retint=1;
		 Log.d("filter","cal2 day of month:"+cal2.get(Calendar.DAY_OF_MONTH));
		 boolean ret=true;
		InputStream in;
		try {
			in = openFileInput("predictAd.txt");
			if (in != null) {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
			String str;
			String[] strNew = new String[2];
			while ((str = br.readLine()) != null) {
				strNew = str.toString().split(" ");
				Log.d("filter","str new:"+strNew[0]+strNew[1]);
				boolean s =Integer.parseInt(strNew[0])==cal2.get(Calendar.DAY_OF_MONTH);
				Log.d("filter","day check ad:"+s);
				if(Integer.parseInt(strNew[0])==cal2.get(Calendar.DAY_OF_MONTH)){
				 retint =0;
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
		
	
	Log.d("filter","I returned:"+retint);
	if(retint==1){
	 return true;
	}
	else{
		return false;
		
	}
	
}
	public void delAd(){
		File dir = getFilesDir();
		File old = new File(dir, "predictAd.txt");
		boolean deleted = old.delete();
		Log.d("filter","delete ad file:"+deleted);
	}
	public void writeAd(){
		 Calendar cal1 = Calendar.getInstance();
			try {
				OutputStreamWriter os = new OutputStreamWriter(openFileOutput("predictAd.txt", 0));
				os.write(cal1.get(Calendar.DAY_OF_MONTH)+" "+cal1.get(Calendar.YEAR));
				Log.d("filter","wrote ad details:"+cal1.get(Calendar.DAY_OF_MONTH)+cal1.get(Calendar.YEAR));
				os.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == nextRight) {
			if(addPredict<noOfWeeks){
			addPredict++;
			}
			goSetButtonText();
			fileCreateCheckUp();
			setUpButtons();
			weekBox.setText("Predict:" + addPredict);
			if(addPredict>=pass){
				pass=addPredict;
			}
		} else if (v == nextLeft) {
			if(addPredict>1){
				addPredict--;
				}
				goSetButtonText();
				fileCreateCheckUp();
				setUpButtons();
				weekBox.setText("Predict:" + addPredict);
		}
		
	else if (v == cal) {
		Intent in =new Intent(predict5.this,percentCal.class);
		in.putExtra("extra",pass);
		startActivity(in);


		} else if (v == mon1) {
			fileIncrementor("mon0");

		}

		else if (v == mon2) {
			fileIncrementor("mon1");
		}

		else if (v == mon3) {
			fileIncrementor("mon2");
		} else if (v == mon4) {
			fileIncrementor("mon3");
		}
	else if (v == mon5) {
		fileIncrementor("mon4");

	}

		else if (v == tue1) {
			fileIncrementor("tue0");
		} else if (v == tue2) {
			fileIncrementor("tue1");
		} else if (v == tue3) {
			fileIncrementor("tue2");
		} else if (v == tue4) {
			fileIncrementor("tue3");
		}

		else if (v == tue5) {
			fileIncrementor("tue4");
		} 
		else if (v == wed1) {
			fileIncrementor("wed0");
		} else if (v == wed2) {
			fileIncrementor("wed1");
		} else if (v == wed3) {
			fileIncrementor("wed2");
		} else if (v == wed4) {
			fileIncrementor("wed3");
		}		else if (v == wed5) {
			fileIncrementor("wed4");
		}
		else if (v == thu1) {
			fileIncrementor("thu0");
		} else if (v == thu2) {
			fileIncrementor("thu1");
		} else if (v == thu3) {
			fileIncrementor("thu2");
		} else if (v == thu4) {
			fileIncrementor("thu3");
		} 	else if (v == thu5) {
			fileIncrementor("thu4");
		} 
		
		
		else if (v == fri1) {
			fileIncrementor("fri0");
		} else if (v == fri2) {
			fileIncrementor("fri1");
		} else if (v == fri3) {
			fileIncrementor("fri2");
		}
		else if (v == fri4) {
			fileIncrementor("fri3");
		}	else if (v == fri5) {
			fileIncrementor("fri4");
		} 
		
		else if (v == sat1) {
			fileIncrementor("sat0");
		}

		else if (v == sat2) {
			fileIncrementor("sat1");
		} else if (v == sat3) {
			fileIncrementor("sat2");
		} else if (v == sat4) {
			fileIncrementor("sat3");
		}	else if (v == sat5) {
			fileIncrementor("sat4");
		}

		else if (v == mon) {
			fileIncrementor("mon0");
			fileIncrementor("mon1");
			fileIncrementor("mon2");
			fileIncrementor("mon3");
			fileIncrementor("mon4");

			
		}

		else if (v == tue) {
			fileIncrementor("tue0");
			fileIncrementor("tue1");
			fileIncrementor("tue2");
			fileIncrementor("tue3");
			fileIncrementor("tue4");

		} else if (v == wed) {
			fileIncrementor("wed0");
			fileIncrementor("wed1");
			fileIncrementor("wed2");
			fileIncrementor("wed3");
			fileIncrementor("wed4");
	
			
		} else if (v == thu) {
			fileIncrementor("thu0");
			fileIncrementor("thu1");
			fileIncrementor("thu2");
			fileIncrementor("thu3");
			fileIncrementor("thu4");

		} else if (v == fri) {
			fileIncrementor("fri0");
			fileIncrementor("fri1");
			fileIncrementor("fri2");
			fileIncrementor("fri3");
			fileIncrementor("fri4");

		} else if (v == sat) {
			fileIncrementor("sat0");
			fileIncrementor("sat1");
			fileIncrementor("sat2");
			fileIncrementor("sat3");
			fileIncrementor("sat4");

		
		}

	}

	public void fileIncrementor(String arg) {
		fileName = "predictweek" + addPredict + ".txt";
		String fileNameTemp = "predictweek" + addPredict + "Temp" + ".txt";
		int add;
		try {
			in = openFileInput(fileName);

			outputstream = new OutputStreamWriter(openFileOutput(fileNameTemp,
					0));
			if (in != null) {
				BufferedReader buffRead = new BufferedReader(
						new InputStreamReader(in));
				String[] splitRead = new String[4];

				String line = "";
				while ((line = buffRead.readLine()) != null) {
					splitRead = line.toString().split(" ");
					if (splitRead[2].equals(arg)) {
						add = Integer.parseInt(splitRead[4]);
						add = add + 1;
						splitRead[4] = Integer.toString(add);

					}
					outputstream.write(splitRead[0] + " " + splitRead[1] + " "
							+ splitRead[2] + " " + splitRead[3] + " "
							+ splitRead[4] + '\n');

					Log.e("filter", "monitor" + splitRead[4]);

				}

			}
			in.close();
			outputstream.close();
			File dir = getFilesDir();
			File old = new File(dir, fileName);
			File neww = new File(dir, fileNameTemp);

			boolean deleted = old.delete();
			boolean yupp = neww.renameTo(old);
			Log.d("filter", "delete:" + deleted);
			Log.d("filter", "rename:" + yupp);
			setUpButtons();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
	public void declaration() {
		mon = (Button) findViewById(R.id.mon);
		mon1 = (Button) findViewById(R.id.mon1);
		mon2 = (Button) findViewById(R.id.mon2);
		mon3 = (Button) findViewById(R.id.mon3);
		mon4 = (Button) findViewById(R.id.mon4);
		mon5 = (Button) findViewById(R.id.mon5);		
		
		tue = (Button) findViewById(R.id.tue);
		tue1 = (Button) findViewById(R.id.tue1);
		tue2 = (Button) findViewById(R.id.tue2);
		tue3 = (Button) findViewById(R.id.tue3);
		tue4 = (Button) findViewById(R.id.tue4);
		tue5 = (Button) findViewById(R.id.tue5);

		wed = (Button) findViewById(R.id.wed);
		wed1 = (Button) findViewById(R.id.wed1);
		wed2 = (Button) findViewById(R.id.wed2);
		wed3 = (Button) findViewById(R.id.wed3);
		wed4 = (Button) findViewById(R.id.wed4);
		wed5 = (Button) findViewById(R.id.wed5);
		
		thu = (Button) findViewById(R.id.thu);
		thu1 = (Button) findViewById(R.id.thu1);
		thu2 = (Button) findViewById(R.id.thu2);
		thu3 = (Button) findViewById(R.id.thu3);
		thu4 = (Button) findViewById(R.id.thu4);
		thu5 = (Button) findViewById(R.id.thu5);
	
		fri = (Button) findViewById(R.id.fri);
		fri1 = (Button) findViewById(R.id.fri1);
		fri2 = (Button) findViewById(R.id.fri2);
		fri3 = (Button) findViewById(R.id.fri3);
		fri4 = (Button) findViewById(R.id.fri4);
		fri5 = (Button) findViewById(R.id.fri5);

		sat = (Button) findViewById(R.id.satt);
		sat1 = (Button) findViewById(R.id.sat1);
		sat2 = (Button) findViewById(R.id.sat2);
		sat3 = (Button) findViewById(R.id.sat3);
		sat4 = (Button) findViewById(R.id.sat4);
		sat5 = (Button) findViewById(R.id.sat5);

		cal = (ImageButton) findViewById(R.id.cal);
		weekBox = (TextView) findViewById(R.id.weekText);
		nextLeft = (ImageButton) findViewById(R.id.imageLeft);
		nextRight = (ImageButton) findViewById(R.id.imageRight);
		mondayNames = new LinkedList<String>();
		tuesdayNames = new LinkedList<String>();
		wednesdayNames = new LinkedList<String>();
		thursdayNames = new LinkedList<String>();
		fridayNames = new LinkedList<String>();
		saturdayNames = new LinkedList<String>();
		weeksColl = new HashSet<Integer>();		


	}
	public void tue(int i, String col) {
		int colour = Integer.parseInt(col);
		if (i == 0) {
			if (colour % 3 == 1) {
				tue1.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				tue1.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				tue1.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 1) {
			if (colour % 3 == 1) {
				tue2.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				tue2.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				tue2.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 2) {
			if (colour % 3 == 1) {
				tue3.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				tue3.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				tue3.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 3) {
			if (colour % 3 == 1) {
				tue4.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				tue4.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				tue4.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 4) {
			if (colour % 3 == 1) {
				tue5.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				tue5.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				tue5.setBackgroundResource(R.drawable.whiteback2);
			}
		} 
	}

	public void mon(int i, String col) {
		int colour = Integer.parseInt(col);
		if (i == 0) {
			if (colour % 3 == 1) {
				mon1.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				mon1.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				mon1.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 1) {
			if (colour % 3 == 1) {
				mon2.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				mon2.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				mon2.setBackgroundResource(R.drawable.tile4markbunk);
			}
		} else if (i == 2) {
			if (colour % 3 == 1) {
				mon3.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				mon3.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				mon3.setBackgroundResource(R.drawable.tile4markbunk);
			}
		} else if (i == 3) {
			if (colour % 3 == 1) {
				mon4.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				mon4.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				mon4.setBackgroundResource(R.drawable.tile4markbunk);
			}
		} else if (i == 4) {
			if (colour % 3 == 1) {
				mon5.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				mon5.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				mon5.setBackgroundResource(R.drawable.tile4markbunk);
			}
		}
	}

	public void wed(int i, String col) {
		int colour = Integer.parseInt(col);
		if (i == 0) {
			if (colour % 3 == 1) {
				wed1.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				wed1.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				wed1.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 1) {
			if (colour % 3 == 1) {
				wed2.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				wed2.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				wed2.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 2) {
			if (colour % 3 == 1) {
				wed3.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				wed3.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				wed3.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 3) {
			if (colour % 3 == 1) {
				wed4.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				wed4.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				wed4.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 4) {
			if (colour % 3 == 1) {
				wed5.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				wed5.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				wed5.setBackgroundResource(R.drawable.whiteback2);
			}
		} 
	}

	public void thu(int i, String col) {
		int colour = Integer.parseInt(col);
		if (i == 0) {
			if (colour % 3 == 1) {
				thu1.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				thu1.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				thu1.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 1) {
			if (colour % 3 == 1) {
				thu2.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				thu2.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				thu2.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 2) {
			if (colour % 3 == 1) {
				thu3.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				thu3.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				thu3.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 3) {
			if (colour % 3 == 1) {
				thu4.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				thu4.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				thu4.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 4) {
			if (colour % 3 == 1) {
				thu5.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				thu5.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				thu5.setBackgroundResource(R.drawable.whiteback2);
			}
		} 
	}

	public void fri(int i, String col) {
		int colour = Integer.parseInt(col);
		if (i == 0) {
			if (colour % 3 == 1) {
				fri1.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				fri1.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				fri1.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 1) {
			if (colour % 3 == 1) {
				fri2.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				fri2.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				fri2.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 2) {
			if (colour % 3 == 1) {
				fri3.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				fri3.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				fri3.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 3) {
			if (colour % 3 == 1) {
				fri4.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				fri4.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				fri4.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 4) {
			if (colour % 3 == 1) {
				fri5.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				fri5.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				fri5.setBackgroundResource(R.drawable.whiteback2);
			}
		} 
	}

	public void sat(int i, String col) {
		int colour = Integer.parseInt(col);
		if (i == 0) {
			if (colour % 3 == 1) {
				sat1.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				sat1.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				sat1.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 1) {
			if (colour % 3 == 1) {
				sat2.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				sat2.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				sat2.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 2) {
			if (colour % 3 == 1) {
				sat3.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				sat3.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				sat3.setBackgroundResource(R.drawable.whiteback2);
			}
		} else if (i == 3) {
			if (colour % 3 == 1) {
				sat4.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				sat4.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				sat4.setBackgroundResource(R.drawable.whiteback2);
			}
		}

		else if (i == 4) {
			if (colour % 3 == 1) {
				sat5.setBackgroundResource(R.drawable.greenback);
			} else if (colour % 3 == 2) {
				sat5.setBackgroundResource(R.drawable.redback);
			} else if (colour % 3 == 0) {
				sat5.setBackgroundResource(R.drawable.whiteback2);
			}
		} 
	}
	
	



	public void goSetButtonText() {

		for (int i = 0; i <5; i++) {
			if ((mondayNames.get(i).equals("free1111")) == false) {
				switch (i) {
				case 0:
					mon1.setText(mondayNames.get(0));
					break;
				case 1:
					mon2.setText(mondayNames.get(1));
					break;
				case 2:
					mon3.setText(mondayNames.get(2));
					break;
				case 3:
					mon4.setText(mondayNames.get(3));
					break;
				case 4:
					mon5.setText(mondayNames.get(4));
					break;
		

				}
			} else if (mondayNames.get(i).equals("free1111")) {
				switch (i) {
				case 0:
					mon1.setVisibility(View.INVISIBLE);

					break;
				case 1:
					mon2.setVisibility(View.INVISIBLE);

					break;
				case 2:
					mon3.setVisibility(View.INVISIBLE);
					break;
				case 3:
					mon4.setVisibility(View.INVISIBLE);
					break;
				case 4:
					mon5.setVisibility(View.INVISIBLE);

					break;
		

				}
			}
		}
		for (int i = 0; i <5; i++) {
			if ((tuesdayNames.get(i).equals("free1111")) == false) {
				switch (i) {
				case 0:
					tue1.setText(tuesdayNames.get(0));
					break;
				case 1:
					tue2.setText(tuesdayNames.get(1));
					break;
				case 2:
					tue3.setText(tuesdayNames.get(2));
					break;
				case 3:
					tue4.setText(tuesdayNames.get(3));
					break;
				case 4:
					tue5.setText(tuesdayNames.get(4));
					break;
		

				}
			} else if (tuesdayNames.get(i).equals("free1111")) {
				switch (i) {
				case 0:
					tue1.setVisibility(View.INVISIBLE);
					break;
				case 1:
					tue2.setVisibility(View.INVISIBLE);
					break;
				case 2:
					tue3.setVisibility(View.INVISIBLE);
					break;
				case 3:
					tue4.setVisibility(View.INVISIBLE);
					break;
				case 4:
					tue5.setVisibility(View.INVISIBLE);
					break;
				

				}
			}
		}
		for (int i = 0; i < 5; i++) {
			if ((wednesdayNames.get(i).equals("free1111")) == false) {
				switch (i) {
				case 0:
					wed1.setText(wednesdayNames.get(0));
					break;
				case 1:
					wed2.setText(wednesdayNames.get(1));
					break;
				case 2:
					wed3.setText(wednesdayNames.get(2));
					break;
				case 3:
					wed4.setText(wednesdayNames.get(3));
					break;
				case 4:
					wed5.setText(wednesdayNames.get(4));
					break;
		

				}
			} else if (wednesdayNames.get(i).equals("free1111")) {
				switch (i) {
				case 0:
					wed1.setVisibility(View.INVISIBLE);
					break;
				case 1:
					wed2.setVisibility(View.INVISIBLE);
					break;
				case 2:
					wed3.setVisibility(View.INVISIBLE);
					break;
				case 3:
					wed4.setVisibility(View.INVISIBLE);
					break;
				case 4:
					wed5.setVisibility(View.INVISIBLE);
					break;
			
				}
			}
		}
		for (int i = 0; i <5; i++) {
			if ((thursdayNames.get(i).equals("free1111")) == false) {
				switch (i) {
				case 0:
					thu1.setText(thursdayNames.get(0));
					break;
				case 1:
					thu2.setText(thursdayNames.get(1));
					break;
				case 2:
					thu3.setText(thursdayNames.get(2));
					break;
				case 3:
					thu4.setText(thursdayNames.get(3));
					break;
				case 4:
					thu5.setText(thursdayNames.get(4));
					break;
		
				}
			} else if (thursdayNames.get(i).equals("free1111")) {
				switch (i) {
				case 0:
					thu1.setVisibility(View.INVISIBLE);
					break;
				case 1:
					thu2.setVisibility(View.INVISIBLE);
					break;
				case 2:
					thu3.setVisibility(View.INVISIBLE);
					break;
				case 3:
					thu4.setVisibility(View.INVISIBLE);
					break;
				case 4:
					thu5.setVisibility(View.INVISIBLE);
					break;
		
	
				}
			}
		}
		for (int i = 0; i <5; i++) {
			if ((fridayNames.get(i).equals("free1111")) == false) {
				switch (i) {
				case 0:
					fri1.setText(fridayNames.get(0));
					break;
				case 1:
					fri2.setText(fridayNames.get(1));
					break;
				case 2:
					fri3.setText(fridayNames.get(2));
					break;
				case 3:
					fri4.setText(fridayNames.get(3));
					break;
				case 4:
					fri5.setText(fridayNames.get(4));
					break;
			

				}
			} else if (fridayNames.get(i).equals("free1111")) {
				switch (i) {
				case 0:
					fri1.setVisibility(View.INVISIBLE);
					break;
				case 1:
					fri2.setVisibility(View.INVISIBLE);
					break;
				case 2:
					fri3.setVisibility(View.INVISIBLE);
					break;
				case 3:
					fri4.setVisibility(View.INVISIBLE);
					break;
				case 4:
					fri5.setVisibility(View.INVISIBLE);
					break;
			
				}
			}
		}
		if (maxDays == 6) {
			for (int i = 0; i < 5; i++) {
				if ((saturdayNames.get(i).equals("free1111")) == false) {
					switch (i) {
					case 0:
						sat1.setText(saturdayNames.get(0));
						break;
					case 1:
						sat2.setText(saturdayNames.get(1));
						break;
					case 2:
						sat3.setText(saturdayNames.get(2));
						break;
					case 3:
						sat4.setText(saturdayNames.get(3));
						break;
					case 4:
						sat5.setText(saturdayNames.get(4));
						break;
				

					}
				} else if (saturdayNames.get(i).equals("free1111")) {
					switch (i) {
					case 0:
						sat1.setVisibility(View.INVISIBLE);
						break;
					case 1:
						sat2.setVisibility(View.INVISIBLE);
						break;
					case 2:
						sat3.setVisibility(View.INVISIBLE);
						break;
					case 3:
						sat4.setVisibility(View.INVISIBLE);
						break;
					case 4:
						sat5.setVisibility(View.INVISIBLE);
						break;
				
					}
				}
			}
		}

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
	public void setUpButtons() {
		fileName = "predictweek" + addPredict + ".txt";
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

					for (int i = 0; i <5; i++) {
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
	}
	public void fileCreateCheckUp() {
		fileName = "predictweek" + addPredict + ".txt";

		Log.d("filter", "weeksColl" + !weeksColl.contains(addPredict));
		File file = getBaseContext().getFileStreamPath(fileName);
		Log.d("filter", "file exist:" + file.exists());
	    	  if( file.exists()==true){
	    		  Log.d("filter","file exists true");
	    	  }
	    	  else{
			try {
				outputstream = new OutputStreamWriter(openFileOutput(fileName, 0));
		
				for (int i = 0; i < 5; i++) {
					outputstream.write("predict" + " "
							+ mondayNames.get(i) + " " + "mon" + i + " " + "PR"
							+ " " + 1 + '\n');

					outputstream.write("predict" + " "
							+ tuesdayNames.get(i) + " " + "tue" + i + " "
							+ "PR" + " " + 1 + '\n');
					outputstream.write("predict" + " "
							+ wednesdayNames.get(i) + " " + "wed" + i + " "
							+ "PR" + " " + 1 + '\n');
					outputstream.write("predict" + " "
							+ thursdayNames.get(i) + " " + "thu" + i + " "
							+ "PR" + " " + 1 + '\n');
					outputstream.write("predict" + " "
							+ fridayNames.get(i) + " " + "fri" + i + " " + "PR"
							+ " " + 1 + '\n');
					if (maxDays == 6) {
						outputstream.write("predict" + " "
								+ saturdayNames.get(i) + " " + "sat" + i + " "
								+ "PR" + " " + 1 + '\n');
					}
					Log.d("filter", "im in file create check up loop");

				}
				outputstream.close();
				weeksColl.add(addPredict);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				Log.d("filter", "exception");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("filter", "exception");
			}
	    	  }
		}
	public void getIntentt() {

		maxDays = ((allData) this.getApplication()).getMaxDays();

	}
	public void setListener() {

		cal.setOnClickListener(this);

		mon.setOnClickListener(this);
		mon1.setOnClickListener(this);
		mon2.setOnClickListener(this);
		mon3.setOnClickListener(this);
		mon4.setOnClickListener(this);
		mon5.setOnClickListener(this);

		tue.setOnClickListener(this);
		tue1.setOnClickListener(this);
		tue2.setOnClickListener(this);
		tue3.setOnClickListener(this);
		tue4.setOnClickListener(this);
		tue5.setOnClickListener(this);

		wed.setOnClickListener(this);
		wed1.setOnClickListener(this);
		wed2.setOnClickListener(this);
		wed3.setOnClickListener(this);
		wed4.setOnClickListener(this);
		wed5.setOnClickListener(this);

		thu.setOnClickListener(this);
		thu1.setOnClickListener(this);
		thu2.setOnClickListener(this);
		thu3.setOnClickListener(this);
		thu4.setOnClickListener(this);
		thu5.setOnClickListener(this);

		fri.setOnClickListener(this);
		fri1.setOnClickListener(this);
		fri2.setOnClickListener(this);
		fri3.setOnClickListener(this);
		fri4.setOnClickListener(this);
		fri5.setOnClickListener(this);

		sat.setOnClickListener(this);
		sat1.setOnClickListener(this);
		sat2.setOnClickListener(this);
		sat3.setOnClickListener(this);
		sat4.setOnClickListener(this);
		sat5.setOnClickListener(this);
	

		nextLeft.setOnClickListener(this);
		nextRight.setOnClickListener(this);
	}
public void setVisible(){
		
		if(maxDays==5){
			sat1.setVisibility(View.GONE);
			sat2.setVisibility(View.GONE);
			sat3.setVisibility(View.GONE);
			sat4.setVisibility(View.GONE);
			sat.setVisibility(View.GONE);
			sat5.setVisibility(View.GONE);

		
		}
	
}
}
