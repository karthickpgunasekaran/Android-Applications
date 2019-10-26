package com.gmail.kpkarthick1995.attendancecare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.UiLifecycleHelper;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;
import com.google.android.gms.ads.*;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.text.InputFilter.LengthFilter;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainScreen extends Activity implements OnClickListener {

	private ImageButton fb,markAttendence, percentCal, predictAt,setting;
	
	private int dayy;
	private allData model;
	private LinearLayout linear;
	private MyTask objMyTask;
	private int variety, date, month, year, maxDays;
	private TextView markAtt, percentCa, predictA, settin;
	private PendingIntent pendingIntent;
	private int shareNot,opinion;
	private AdView adView;
	  private static final String AD_UNIT_ID = "ca-app-pub-5293147320473871/7772420545";


	  private InterstitialAd interstitialAd;
private SharedPreferences share ;
private UiLifecycleHelper uiHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainscreen);
		 interstitialAd = new InterstitialAd(this);
		    interstitialAd.setAdUnitId(AD_UNIT_ID);
		    AdRequest adRequest = new AdRequest.Builder().build();
		    interstitialAd.loadAd(adRequest);
			adView = (AdView) this.findViewById(R.id.adView);
		String check = "setAllData.txt";
	share=getPreferences(MODE_PRIVATE);
	SharedPreferences sp = getApplicationContext().getSharedPreferences("notify", MODE_PRIVATE); 
	opinion=sp.getInt("opinion",1);
	Log.d("filter","in opinion main:"+opinion);
		File file = getBaseContext().getFileStreamPath(check);
		Log.d("filter", "file exist:" + file.exists());
		if (file.exists() == true) {
			getIntentt();
			Log.d("filter", "dayy:" + dayy);
			declare();
			
				setNotify();
		
			markAttendence.setOnClickListener(this);
			percentCal.setOnClickListener(this);
			predictAt.setOnClickListener(this);
			setting.setOnClickListener(this);
	fb.setOnClickListener(this);
	setAd();
			
		} else {
			Intent intent = new Intent(MainScreen.this, GetData.class);
			startActivity(intent);
		}

	}

	public void setAd(){
		if(checkAd()){

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
			in = openFileInput("mainAd.txt");
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
		File old = new File(dir, "mainAd.txt");
		boolean deleted = old.delete();
		Log.d("filter","delete ad file:"+deleted);
	}
	public void writeAd(){
		 Calendar cal1 = Calendar.getInstance();
			try {
				OutputStreamWriter os = new OutputStreamWriter(openFileOutput("mainAd.txt", 0));
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
	
		public void declare() {
		markAttendence = (ImageButton) findViewById(R.id.markAttendence);
		;
		percentCal = (ImageButton) findViewById(R.id.percentCal);
		predictAt = (ImageButton) findViewById(R.id.predictAtt);
		setting = (ImageButton) findViewById(R.id.setting);
		fb = (ImageButton) findViewById(R.id.facebook);
}

	public void setNotify() {
	   NotificationManager mManager = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
     mManager.cancelAll();
		Calendar calendar = Calendar.getInstance();
if(opinion==1){
	  Log.d("filter","calender:"+calendar.get(calendar.DAY_OF_MONTH)+" "+calendar.get(calendar.MONTH)+" "+calendar.get(calendar.YEAR));
     calendar.add(Calendar.DATE, 1);
}
else if(opinion==7){
	
	calendar.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
}
else if(opinion==911){
	
	calendar.set(Calendar.YEAR, 2100);
}
		calendar.set(Calendar.HOUR_OF_DAY, 18);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		
		  calendar.setTimeZone(TimeZone.getDefault());
		  Log.d("filter","calender:"+calendar.get(calendar.DAY_OF_MONTH)+" "+calendar.get(calendar.MONTH)+" "+calendar.get(calendar.YEAR));
		Intent myIntent = new Intent(MainScreen.this, Receiver.class);
		pendingIntent = PendingIntent.getBroadcast(MainScreen.this, 34430,
				myIntent, 34340);
		
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
	
		alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),pendingIntent);

	
	}

	public void getIntentt() {
		InputStreamReader in;
		try {
			in = new InputStreamReader(openFileInput("setAllData.txt"));
			BufferedReader br = new BufferedReader(in);
			String str;
			String[] splitRead = new String[5];
			while ((str = br.readLine()) != null) {
				splitRead = str.toString().split(" ");
				// variety=splitRead[0];
			}
		
			variety = Integer.parseInt(splitRead[0]);
			date = Integer.parseInt(splitRead[1]);
			month = Integer.parseInt(splitRead[2]);
			year = Integer.parseInt(splitRead[3]);
			maxDays = Integer.parseInt(splitRead[4]);
			Log.d("filter","mainscreen maxdays:"+maxDays);
			((allData) this.getApplication()).setVariety(variety);
			((allData) this.getApplication()).setData(date, month, year,
					maxDays);
			dayy = ((allData) this.getApplication()).getVariety();
          
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent inp = getIntent();
		  shareNot=inp.getIntExtra("shareNot",0);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		if (v == markAttendence) {
			switch (dayy) {
			case 4:
				Intent intent4 = new Intent(MainScreen.this,
						MarkAttendence4.class);
				;
				startActivity(intent4);
				break;
			case 5:
				Intent intent5 = new Intent(MainScreen.this,
						MarkAttendence5.class);
				;
				startActivity(intent5);
				break;
			case 6:
				Intent intent6 = new Intent(MainScreen.this,
						MarkAttendence6.class);
				;
				startActivity(intent6);
				break;

			case 7:
				Intent intent7 = new Intent(MainScreen.this,
						MarkAttendence7.class);
				;
				startActivity(intent7);
				break;

			case 8:
				Intent intent8 = new Intent(MainScreen.this,
						MarkAttendence8.class);
				;
				startActivity(intent8);
				break;
			case 9:
				Intent intent9 = new Intent(MainScreen.this,
						MarkAttendence9.class);
				;
				startActivity(intent9);
				break;
			case 10:
				Intent intent10 = new Intent(MainScreen.this,
						MarkAttendence10.class);
				;
				startActivity(intent10);
				break;
			default:
				Log.d("filter", "cons not working!!");
				Log.d("filter", "day" + dayy);
			}
		} else if (v == percentCal) {
			Intent intent = new Intent(MainScreen.this, percentCal.class);
			;
			startActivity(intent);
		} else if (v == predictAt) {
			switch (dayy) {
			case 4:
				Intent intent4 = new Intent(MainScreen.this, predict4.class);
				;
				startActivity(intent4);
				break;
			case 5:
				Intent intent5 = new Intent(MainScreen.this, predict5.class);
				;
				startActivity(intent5);
				break;
			case 6:
				Intent intent6 = new Intent(MainScreen.this, predict6.class);
				;
				startActivity(intent6);
				break;

			case 7:
				Intent intent7 = new Intent(MainScreen.this, predict7.class);
				;
				startActivity(intent7);
				break;

			case 8:
				Intent intent8 = new Intent(MainScreen.this, predict8.class);
				;
				startActivity(intent8);
				break;
			case 9:
				Intent intent9 = new Intent(MainScreen.this, predict9.class);
				;
				startActivity(intent9);
				break;
			case 10:
				Intent intent10 = new Intent(MainScreen.this, predict10.class);
				;
				startActivity(intent10);
				break;
			}
		} else if (v == setting) {
			Intent in = new Intent(MainScreen.this, settings.class);
			;
			startActivity(in);
		}
		else if(v==fb){
			Intent in = new Intent(MainScreen.this,FBActivity.class);
			startActivity(in);
			
		/*
			if(!face.isSessionValid()){
				face.authorize(MainScreen.this,new DialogListener(){

			    	@Override
					public void onComplete(Bundle values) {
						// TODO Auto-generated method stub
						Editor edit = share.edit();
						edit.putString("access_token",face.getAccessToken());
						edit.putLong("expiry",face.getAccessExpires());
						edit.commit();
			
					}

					@Override
					public void onFacebookError(FacebookError e) {
						Toast.makeText(MainScreen.this,"Error loading facebook!!",Toast.LENGTH_SHORT).show();
						
					}

					@Override
					public void onError(DialogError e) {
						// TODO Auto-generated method stub
						Toast.makeText(MainScreen.this,"Problem connecting to facebook!!check your data connection!!",Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onCancel() {
						// TODO Auto-generated method stub
						Toast.makeText(MainScreen.this,"cancelled!!",Toast.LENGTH_SHORT).show();	
					}
					
				});
			}
				

			FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(this)
	        .setLink("https://developers.facebook.com/android")
	        .build();
	uiHelper.trackPendingDialogCall(shareDialog.present());
	
	if (FacebookDialog.canPresentShareDialog(getApplicationContext(), 
            FacebookDialog.ShareDialogFeature.SHARE_DIALOG))
	{
// Publish the post using the Share Dialog
FacebookDialog shareDialog1 = new FacebookDialog.ShareDialogBuilder(this)
.setLink("https://developers.facebook.com/android")
.build();
uiHelper.trackPendingDialogCall(shareDialog1.present());

} */
	
		}
	
	}
	/*private void publishFeedDialog() {
	    Bundle params = new Bundle();
	    params.putString("name", "Facebook SDK for Android");
	    params.putString("caption", "Build great social apps and get more installs.");
	    params.putString("description", "The Facebook SDK for Android makes it easier and faster to develop Facebook integrated Android apps.");
	    params.putString("link", "https://developers.facebook.com/android");
	    params.putString("picture", "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");

	    WebDialog feedDialog = (
	        new WebDialog.FeedDialogBuilder(getActivity(),
	            Session.getActiveSession(),
	            params))
	        .setOnCompleteListener(new OnCompleteListener() {

	            @Override
	            public void onComplete(Bundle values,
	                FacebookException error) {
	                if (error == null) {
	                    // When the story is posted, echo the success
	                    // and the post Id.
	                    final String postId = values.getString("post_id");
	                    if (postId != null) {
	                        Toast.makeText(getActivity(),
	                            "Posted story, id: "+postId,
	                            Toast.LENGTH_SHORT).show();
	                    } else {
	                        // User clicked the Cancel button
	                        Toast.makeText(getActivity().getApplicationContext(), 
	                            "Publish cancelled", 
	                            Toast.LENGTH_SHORT).show();
	                    }
	                } else if (error instanceof FacebookOperationCanceledException) {
	                    // User clicked the "x" button
	                    Toast.makeText(getActivity().getApplicationContext(), 
	                        "Publish cancelled", 
	                        Toast.LENGTH_SHORT).show();
	                } else {
	                    // Generic, ex: network error
	                    Toast.makeText(getActivity().getApplicationContext(), 
	                        "Error posting story", 
	                        Toast.LENGTH_SHORT).show();
	                }
	            }

	        })
	        .build();
	    feedDialog.show();
	}*/

	private Context getActivity() {
		// TODO Auto-generated method stub
		return null;
	}
	/*
public void getFb(){
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

	StrictMode.setThreadPolicy(policy); 
	JSONObject obj=null;
	URL img = null;
try {
	String JsonUser =face.request("me");
	obj =Util.parseJson(JsonUser);
	String id = obj.optString("id");
	String name = obj.optString("name");
	Log.d("filter", "hello oooaodsdvdssv ");
	Log.d("filter","name is:"+name);
	
} catch (MalformedURLException e) {
	// TODO Auto-generated catch block
	Log.d("filter", "malformed ");
	e.printStackTrace();
} catch (IOException e) {
	Log.d("filter", "io exception");
	e.printStackTrace();
} catch (FacebookError e) {
	Log.d("filter", "fb eerror");
	e.printStackTrace();
} catch (JSONException e) {
	Log.d("filter", "json ex");
	e.printStackTrace();
}
}

public void fbPost()
{	
	Bundle params =new Bundle();
	params.putString("name","Attendence Care");
	params.putString("caption","karthick's attendence percentage");
	//for(int i=0;i<5;i++){
//	b.putString("description","hello"+'\n');
	//}
	face.dialog(MainScreen.this,"feed",params,new DialogListener() {
		
		@Override
		public void onFacebookError(final FacebookError e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onError(final DialogError e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onComplete(final Bundle values) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			
		}
	});
	
}
*/ public void displayInterstitial() {
    if (interstitialAd.isLoaded()) {
	      interstitialAd.show();
	    }
	  }
	@Override
	public void onBackPressed() {
		/* interstitialAd.setAdListener(new AdListener(){
	          public void onAdLoaded(){
	               displayInterstitial();
	          }
	});*/
		moveTaskToBack(true);
		//MainScreen.this.finish();

	}

	class MyTask extends AsyncTask<Void, Integer, Void> {

		private Set<String> allPeriods = new HashSet<String>();;
		private int noOfPer;
		private allData ada;
		Dialog dialog;
		ProgressBar progressBar;
		TextView tvLoading, tvPer;
		Button btnCancel;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new Dialog(MainScreen.this);
			dialog.setCancelable(false);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.progressdialog);

			progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar1);
			tvLoading = (TextView) dialog.findViewById(R.id.tv1);
			tvPer = (TextView) dialog.findViewById(R.id.tvper);
			btnCancel = (Button) dialog.findViewById(R.id.btncancel);

			btnCancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					objMyTask.cancel(true);
					dialog.dismiss();
				}
			});

			dialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {

			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			progressBar.setProgress(values[0]);
			tvLoading.setText("Loading...  " + values[0] + " %");
			tvPer.setText(values[0] + " %");
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			dialog.dismiss();

			Intent intent_name = new Intent();
			intent_name.setClass(getApplicationContext(), percentCal.class);
			startActivity(intent_name);
		}

	}
/*@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	face.authorizeCallback(requestCode, resultCode, data);
}*/
}
