package com.gmail.kpkarthick1995.attendancecare;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class percentCal extends ListActivity {
	private int weekBet1, dateCal, monthCal, yearCal;
	private Calendar callOld;
	private Set<String> allPeriods = new HashSet<String>();;
	private int noOfPer;
	private allData ada;
	private String[] allPeriod;
	private int extra;
	private float percent;
	public int total = 0;
	public int present = 0;
	TextView textstyle1;
	TextView textstyle2;
	TextView textstyle3;
	TextView textstyle4;
	boolean in;
	AdView adView;
	TextView fire10;
	TextView fire20;
	TextView fire30;
	TextView fire40;

	 InterstitialAd interstitialAd;
	OutputStreamWriter os;
	  private static final String AD_UNIT_ID = "ca-app-pub-5293147320473871/7133292148";
	private ProgressDialog progressDialog;
	private List<Overall> adSub = new ArrayList<Overall>();;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		interstitialAd = new InterstitialAd(this);

	    interstitialAd.setAdUnitId(AD_UNIT_ID);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    interstitialAd.loadAd(adRequest);
	   
		new LoadViewTask().execute();
		 
	  	interstitialAd.setAdListener(new AdListener(){
	          public void onAdLoaded(){
	               displayInterstitial();
	          }
	  	});
		Log.d("filter", "the value of total is" + total);

		setListAdapter(new AttendenceAdapter(this, R.layout.percentcal,
				returnSome()));
		
	
	
			
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	  public void displayInterstitial() {
		    if (interstitialAd.isLoaded()) {
		      interstitialAd.show();
		    }
		  }
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
		NavUtils.navigateUpFromSameTask(this);
		return true;
		}
		  return super.onOptionsItemSelected(item);	
		}
	public void decclaration() {
		textstyle1 = (TextView) findViewById(R.id.textstyle1);
		textstyle2 = (TextView) findViewById(R.id.textstyle2);
		textstyle3 = (TextView) findViewById(R.id.textstyle3);
		textstyle4 = (TextView) findViewById(R.id.textstyle4);
		fire10 = (TextView) findViewById(R.id.fire10);
		fire20 = (TextView) findViewById(R.id.fire20);
		fire30 = (TextView) findViewById(R.id.fire40);
		fire40 = (TextView) findViewById(R.id.fire30);

	}

	public List<Overall> returnSome() {

		return adSub;
	}

	public void ReadFromIt() {
		InputStream inP;

		try {
			int tot = 0, pres = 0;
			inP = openFileInput("PeriodIncrement.txt");
			BufferedReader buffReadPeriod = new BufferedReader(
					new InputStreamReader(inP));
			String str1;
			String[] splitRead = new String[3];
			while ((str1 = buffReadPeriod.readLine()) != null) {
				splitRead = str1.toString().split(" ");
				int present1 = Integer.parseInt(splitRead[1]);
				pres = pres + present1;
				int total1 = Integer.parseInt(splitRead[2]);
				tot = tot + total1;
				// Log.d("filter","name:"+splitRead[0]+"present:"+present1+"total:"+total1);
				adSub.add(new Overall(splitRead[0], present1, total1));
			}
			total = tot;
			present = pres;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.d("filter", "file not found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d("filter", "file not found");
			e.printStackTrace();
		}

	}

	class AttendenceAdapter extends ArrayAdapter<Overall> {
		private LayoutInflater layoutinflater;

		public AttendenceAdapter(Context context, int resource,
				List<Overall> overTotal) {
			super(context, resource, overTotal);
			layoutinflater = LayoutInflater.from(context);

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View view = layoutinflater.inflate(R.layout.listpercent, null);
			Overall over = getItem(position);
		
			TextView subName = (TextView) view.findViewById(R.id.fire1);
			TextView percentage = (TextView) view.findViewById(R.id.fire2);
			TextView percentVal = (TextView) view.findViewById(R.id.fire4);
			TextView overallVal = (TextView) view.findViewById(R.id.fire3);
		//	Log.d("filter", "percent" + over.getPercent());
			subName.setText(over.getName());
			percentage.setText(String.valueOf(over.getPercent())+"%");
			percentVal.setText(String.valueOf(over.getPresent()));
			overallVal.setText(String.valueOf(over.getTotal()));
			OutputStreamWriter output;
			/*try {
				output =new OutputStreamWriter(
				        new FileOutputStream(
				        		"forFB.txt",0));
				output.write(over.getName()+" "+String.valueOf(over.getPercent())+"%"+'\n');
				Log.d("gh","wrote overall:"+over.getName()+" "+String.valueOf(over.getPercent())+"%"+'\n');
				output.close();
	
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		*/	
			/*try {
				OutputStreamWriter os=new OutputStreamWriter(openFileOutput("forFB.txt", 0));
				os.write(over.getName()+" "+String.valueOf(over.getPercent())+"%"+'\n');
				Log.d("gh","wrote:"+String.valueOf(over.getName()+" "+over.getPercent())+"%"+'\n');
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
			return view;

		}

	}

	public void getIntentt() {
		dateCal = ((allData) this.getApplication()).getDate();
		yearCal = ((allData) this.getApplication()).getYear();
		monthCal = ((allData) this.getApplication()).getMonth();
		callOld = new GregorianCalendar(yearCal, monthCal, dateCal);
		Intent in = getIntent();
		extra = in.getIntExtra("extra", 0);
	}

	public void setWeekBet1() {
		Calendar weekPres = Calendar.getInstance();
		int weekNow = weekPres.get(Calendar.WEEK_OF_YEAR);
		weekBet1 = weekNow - callOld.get(callOld.WEEK_OF_YEAR);
		Log.d("filter", "weekbet:" + weekBet1);
		if (weekBet1 < 0) {
			int yearPresent = Calendar.getInstance().get(Calendar.YEAR);
			int yearSet = callOld.get(callOld.YEAR);

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
		Log.d("filter", "week now is :" + weekBet1);
	}

	public void deleteOldF() {
		InputStream inP;
		try {
			inP = openFileInput("PeriodIncrement.txt");
			BufferedReader buffReadPeriod = new BufferedReader(
					new InputStreamReader(inP));
			OutputStreamWriter outputstream = new OutputStreamWriter(
					openFileOutput("PeriodIncrementTemp.txt", 0));
			String str1;
			String[] splitRead1 = new String[3];
			while ((str1 = buffReadPeriod.readLine()) != null) {
				splitRead1 = str1.toString().split(" ");

				outputstream.write(splitRead1[0] + " " + 0 + " " + 0 + '\n');
				Log.d("filter", "name:" + splitRead1[0] + "present:"
						+ splitRead1[1] + "overall:" + splitRead1[2]);
			}
			inP.close();
			outputstream.close();
			File dir = getFilesDir();
			File old = new File(dir, "PeriodIncrement.txt");
			File neww = new File(dir, "PeriodIncrementTemp.txt");

			boolean deleted = old.delete();
			boolean yupp = neww.renameTo(old);
			Log.d("filter", "first delete:" + deleted);
			Log.d("filter", "first rename:" + yupp);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setUp() {

		for (int var = 0; var <= weekBet1; var++) {
			String fileName = "week" + var + ".txt";
			try {
				InputStream inW = openFileInput(fileName);

				if (inW != null) {
					BufferedReader buffReadWeek = new BufferedReader(
							new InputStreamReader(inW));

					String[] splitRead = new String[4];
					String[] splitRead1 = new String[3];
					String str, str1;
					while ((str = buffReadWeek.readLine()) != null) {
						splitRead = str.toString().split(" ");
						InputStream inP = openFileInput("PeriodIncrement.txt");
						BufferedReader buffReadPeriod = new BufferedReader(
								new InputStreamReader(inP));
						OutputStreamWriter outputstream = new OutputStreamWriter(
								openFileOutput("PeriodIncrementTemp.txt", 0));

						while ((str1 = buffReadPeriod.readLine()) != null) {

							splitRead1 = str1.toString().split(" ");
							if (splitRead1[0].equals(splitRead[1])) {
								int temp = Integer.parseInt(splitRead[4]);
								if (temp % 3 == 1) {
									int tempInt1 = Integer
											.parseInt(splitRead1[1]);
									int tempInt2 = Integer
											.parseInt(splitRead1[2]);
									tempInt1 += 1;
									tempInt2 += 1;

									splitRead1[1] = Integer.toString(tempInt1);
									splitRead1[2] = Integer.toString(tempInt2);

								} else if (temp % 3 == 2) {
									int tempInt2 = Integer
											.parseInt(splitRead1[2]);
									tempInt2 += 1;

									splitRead1[2] = Integer.toString(tempInt2);
								}
							}
							outputstream.write(splitRead1[0] + " "
									+ splitRead1[1] + " " + splitRead1[2]
									+ '\n');
							// Log.d("filter","name:"+splitRead1[0]+"present:"+splitRead1[1]+"overall:"+splitRead1[2]);
						}

						inP.close();
						outputstream.close();
						File dir = getFilesDir();
						File old = new File(dir, "PeriodIncrement.txt");
						File neww = new File(dir, "PeriodIncrementTemp.txt");

						boolean deleted = old.delete();
						boolean yupp = neww.renameTo(old);
						// Log.d("filter", "delete:" + deleted);
						// Log.d("filter", "rename:" + yupp);
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

	}
	public void setAd(){
		if(checkAd()){
			 adView = (AdView) this.findViewById(R.id.adView);
		  AdRequest adRequest = new AdRequest.Builder().build();
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
			 adView = (AdView) this.findViewById(R.id.adView);
	setVisNul();	
		}
	}
	public void setVisNul(){
		adView.setVisibility(View.GONE);	
	}
	public boolean checkAd(){
		 Calendar cal2 = Calendar.getInstance();
int retint=1;
		 Log.d("filter","cal2 day of month:"+cal2.get(Calendar.DAY_OF_MONTH));
		 boolean ret=true;
		InputStream in;
		try {
			in = openFileInput("percentAd.txt");
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
		File old = new File(dir, "percentAd.txt");
		boolean deleted = old.delete();
		Log.d("filter","delete ad file:"+deleted);
	}
	public void writeAd(){
		 Calendar cal1 = Calendar.getInstance();
			try {
				OutputStreamWriter os = new OutputStreamWriter(openFileOutput("percentAd.txt", 0));
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
	public void setUpExtra() {

		for (int var = 1; var <= extra; var++) {
			String fileName = "predictweek" + var + ".txt";
			try {
				InputStream inW = openFileInput(fileName);

				if (inW != null) {
					BufferedReader buffReadWeek = new BufferedReader(
							new InputStreamReader(inW));

					String[] splitRead = new String[4];
					String[] splitRead1 = new String[3];
					String str, str1;
					while ((str = buffReadWeek.readLine()) != null) {
						splitRead = str.toString().split(" ");
						InputStream inP = openFileInput("PeriodIncrement.txt");
						BufferedReader buffReadPeriod = new BufferedReader(
								new InputStreamReader(inP));
						OutputStreamWriter outputstream = new OutputStreamWriter(
								openFileOutput("PeriodIncrementTemp.txt", 0));
						while ((str1 = buffReadPeriod.readLine()) != null) {
							splitRead1 = str1.toString().split(" ");
							if (splitRead1[0].equals(splitRead[1])) {
								int temp = Integer.parseInt(splitRead[4]);
								if (temp % 3 == 1) {
									int tempInt1 = Integer
											.parseInt(splitRead1[1]);
									int tempInt2 = Integer
											.parseInt(splitRead1[2]);
									tempInt1 += 1;
									tempInt2 += 1;
									splitRead1[1] = Integer.toString(tempInt1);
									splitRead1[2] = Integer.toString(tempInt2);

								} else if (temp % 3 == 2) {
									int tempInt2 = Integer
											.parseInt(splitRead1[2]);
									tempInt2 += 1;
									splitRead1[2] = Integer.toString(tempInt2);
								}
							}
							outputstream.write(splitRead1[0] + " "
									+ splitRead1[1] + " " + splitRead1[2]
									+ '\n');
							Log.d("filter", "name:" + splitRead1[0]
									+ "present:" + splitRead1[1] + "overall:"
									+ splitRead1[2]);
						}//
						inP.close();
						outputstream.close();
						File dir = getFilesDir();
						File old = new File(dir, "PeriodIncrement.txt");
						File neww = new File(dir, "PeriodIncrementTemp.txt");

						boolean deleted = old.delete();
						boolean yupp = neww.renameTo(old);
						Log.d("filter", "delete:" + deleted);
						Log.d("filter", "rename:" + yupp);
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

	}

	private class LoadViewTask extends AsyncTask<Void, Integer, Void> {
		// Before running code in the separate thread
	
		@Override
		protected void onPreExecute() {
			

			progressDialog = ProgressDialog.show(percentCal.this, "Loading...",
					"Loading data, please wait...", false, false);
		}
		  
		@Override
		protected Void doInBackground(Void... params) {
			
			deleteOldF();
			getIntentt();
			setWeekBet1();
			setUp();
			if (extra > 0) {
				setUpExtra();
			}

			ReadFromIt();
			decclaration();
			 //  displayInterstitial();
		
				
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {

			progressDialog.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(Void result) {
			
				
			progressDialog.dismiss();
			  progressDialog=null;
			setContentView(R.layout.percentcal);
		

			decclaration();
			try{
			percent = ((float)present * 100) / (float)total;
			percent = (float) Math.round(percent * 10) / 10;
			fire20.setText(String.valueOf(percent) + "%");
			fire30.setText(String.valueOf(total));
			fire40.setText(String.valueOf(present));
			}
			catch(Exception e){
			int LENGTH_SHORT=1000;
			Log.d("filter","Sorry there is an error");
			Toast toast = Toast.makeText(percentCal.this,
					"Sorry there are no data's to show!! ",Toast.LENGTH_SHORT);
			toast.show();
			}
			setAd();
			File dir = getFilesDir();
			File del = new File(dir, "forFB.txt");
			boolean an=del.delete();
			Log.d("filter","deleted an:"+an);
			try {
				
				OutputStreamWriter os=new OutputStreamWriter(openFileOutput("forFB.txt", 0));
				//BufferedWriter output = new BufferedWriter(new FileWriter("forFB.txt", true));
				os.write("Overall"+" "+String.valueOf(percent)+"%"+" "+String.valueOf(total)+" "+String.valueOf(present)+'\n');
				Log.d("gh","wrote overall:"+String.valueOf(percent)+"%");
				os.close();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}
	}
}
