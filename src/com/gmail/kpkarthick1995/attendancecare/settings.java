package com.gmail.kpkarthick1995.attendancecare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class settings extends Activity implements OnClickListener{
private Button reset,fb,rateUs,feedback,notification,howTo;
AdView adView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		declaration();
		adView = (AdView) this.findViewById(R.id.adView);
		setAd();
		setListener();
		getActionBar().setDisplayHomeAsUpEnabled(true);
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
			in = openFileInput("settingsAd.txt");
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
		File old = new File(dir, "settingsAd.txt");
		boolean deleted = old.delete();
		Log.d("filter","delete ad file:"+deleted);
	}
	public void writeAd(){
		 Calendar cal1 = Calendar.getInstance();
			try {
				OutputStreamWriter os = new OutputStreamWriter(openFileOutput("settingsAd.txt", 0));
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
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
		NavUtils.navigateUpFromSameTask(this);
		return true;
		}
		  return super.onOptionsItemSelected(item);	
		}
	public void setListener(){
		fb.setOnClickListener(this);
		reset.setOnClickListener(this);
		rateUs.setOnClickListener(this);
		feedback.setOnClickListener(this);
		notification.setOnClickListener(this);
		howTo.setOnClickListener(this);
	
	}
	public void declaration(){
		fb = (Button) findViewById(R.id.fb);
		reset = (Button) findViewById(R.id.reset);
		rateUs = (Button) findViewById(R.id.rate);
		notification =(Button) findViewById(R.id.notification);
		feedback =(Button) findViewById(R.id.feedback);
	  howTo=(Button) findViewById(R.id.block);
	}
	  public void linkEmailClicked(View v)
	    {
	 
	    }
	@Override
	public void onClick(View v) {
if(v==reset){
	Intent in = new Intent(settings.this,reset.class);
	startActivity(in);
}
else if(v==feedback){
    Intent it = new Intent(Intent.ACTION_SEND);
    String[] tos = {getString(R.string.email)};
    it.putExtra(Intent.EXTRA_EMAIL, tos);
   it.putExtra(Intent.EXTRA_SUBJECT, "please send us your feedback to karthickindian2011@gmail.com");
   it.setType("text/plain");
    startActivity(it);
}
		
else if(v==notification){
	Intent in = new Intent(settings.this,Notification.class);
	startActivity(in);
	
}
else if(v==rateUs){
	

	  try {
		  Uri uri = Uri.parse("market://details?id=com.gmail.kpkarthick1995.attendancecare" );
			Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
	        startActivity(myAppLinkToMarket);

	      } catch (ActivityNotFoundException e) {
	    	  Uri uri = Uri.parse("http://play.google.com/store/apps/details?id=com.gmail.kpkarthick1995.attendancecare" );
				Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
		        startActivity(myAppLinkToMarket);
	      //  Toast.makeText(settings.this, "You don't have Google Play installed", Toast.LENGTH_LONG).show();
	              }
}
else if(v==fb){
    Intent it = new Intent(Intent.ACTION_SEND);
   it.putExtra(Intent.EXTRA_SUBJECT, "Hi!! try this app Attendance Care.Its really cool and helps maintain my attendance efficiently."+"\n"+"http://play.google.com/store/apps/details?id=com.gmail.kpkarthick1995.attendancecare");
   it.setType("text/plain");
    startActivity(it);
}
else if(v==howTo){
	Intent in =new Intent(settings.this,BlockAds.class);
	startActivity(in);
}
	}
/*	@Override
	public void onBackPressed() {
		moveTaskToBack(true);
		settings.this.finish();
	//	Intent in =new Intent(settings.this,MainScreen.class);
		//startActivity(in);
		

	}*/
}
