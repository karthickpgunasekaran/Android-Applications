package com.gmail.kpkarthick1995.attendancecare;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Notification extends Activity implements OnClickListener{
private  RadioGroup radBut;
private Button checkRad;
SharedPreferences pre;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notify);
		 pre = getApplicationContext().getSharedPreferences("notify",MODE_PRIVATE);
		declare();
		setRadio();
		setListener();
		
	}
	public void setRadio(){
		/*SharedPreferences sp = getApplicationContext().getSharedPreferences("MyPref", 0); 
		String sopinion=sp.getString("opinion","2");
		*/
	//	SharedPreferences pre = getSharedPreferences("notify",Context.MODE_PRIVATE);
	//	SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this)
		;//SharedPreferences prefs = getPreferences(MODE_PRIVATE); 
		int opinio = pre.getInt("opinion", 1);
		Log.d("filter","opinion in notification:"+opinio);
	//int	opinion = Integer.parseInt(sopinion);
	if(opinio==1)
	{
		RadioButton rd = (RadioButton) findViewById(R.id.daily);
		rd.setChecked(true);
	}
	else if(opinio==7)	
	{
		RadioButton rd = (RadioButton) findViewById(R.id.weekly);
		rd.setChecked(true);
	}
	else
	{
		RadioButton rd = (RadioButton) findViewById(R.id.never);
		rd.setChecked(true);
	}
	}
	
	
	public void setListener(){
		//daily.setOnClickListener(this);
		//weekly.setOnClickListener(this);
		checkRad.setOnClickListener(this);
		
	}
	public void declare(){
		//daily=(RadioButton) findViewById(R.id.daily);
	//	weekly=(RadioButton) findViewById(R.id.weekly);
	//	never = (RadioButton) findViewById(R.id.never);
		radBut = (RadioGroup) findViewById(R.id.radBut);
		checkRad = (Button) findViewById(R.id.checkRad);
	}
	@Override
	public void onClick(View v) {
	
		if(v==checkRad){
			RadioButton radChecked = (RadioButton) findViewById(radBut.getCheckedRadioButtonId());
			String opinion = radChecked.getText().toString();
	//		SharedPreferences sp = getPreferences(MODE_PRIVATE);
		//SharedPreferences sp	= getApplicationContext().getSharedPreferences("MyPref", 0);
			
		Editor edit = pre.edit();
			//Editor edit = sp.edit();
Log.d("filter","choose:"+opinion);
		if(opinion.equals("Weekly")){
			Log.d("filter","i came to weekly");
			edit.putInt("opinion",7);
		}
		else if(opinion.equals("Daily")){
			edit.putInt("opinion",1);
			Log.d("filter","i came to daily");
		}
		else if(opinion.equals("Never")){
			edit.putInt("opinion",911);
			Log.d("filter","i came to never");
		}
		 //edit.apply();
		edit.commit();
		Intent in = new Intent(Notification.this,MainScreen.class);
		startActivity(in);
		}
	}
	@Override
	public void onBackPressed() {
//		moveTaskToBack(true);
Notification.this.finish();
		Intent in =new Intent(Notification.this,MainScreen.class);
		startActivity(in);
		

	}
}
