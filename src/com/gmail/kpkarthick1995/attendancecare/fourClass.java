package com.gmail.kpkarthick1995.attendancecare;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class fourClass extends Activity implements OnClickListener,
		OnFocusChangeListener {
	private AutoCompleteTextView mon1;
	private AutoCompleteTextView mon2, mon3, mon4;
	private AutoCompleteTextView tue1, tue2, tue3, tue4;
	private AutoCompleteTextView wed1, wed2, wed3, wed4;
	private AutoCompleteTextView thu1, thu2, thu3, thu4;
	private AutoCompleteTextView fri1, fri2, fri3, fri4;
	private AutoCompleteTextView sat1, sat2, sat3, sat4;
	private Button sat;
	private Button check;
	private int maxDays;
	private InputFilter filter;
	private Set<String> addTo = new HashSet<String>();
	private ArrayAdapter<String> auto;
	private String[] weekly;
	private int goToNew=0;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getclass_4);
	
			goToNew();
		
		getIntentt();
		declaration();
		filter();
		maxCheck();
		setListener();
		
	}
	public void goToNew(){
		AlertDialog alertDialog = new AlertDialog.Builder(fourClass.this).create();

		

		alertDialog.setTitle("Caution:");

		alertDialog.setMessage(" Please leave the boxes empty if " +
				"you don't have classes at that time!!!");
		
		

		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int which) {

			dialog.cancel();

		} });
		//And you can change the icon of your AlertDialog using this line: alertDialog.setIcon(R.drawable.icon);

	

		alertDialog.show();
	}
public void filter(){
	filter = new InputFilter() { 
		   

		@Override
		public CharSequence filter(CharSequence source, int start, int end,
				Spanned dest, int dstart, int dend) {
			// TODO Auto-generated method stub
			  for (int i = start; i < end; i++) { 
	              if (!Character.isLetterOrDigit(source.charAt(i))) { 
	                  return ""; 
	              }
	          }
			return null;
		} 
		        }; 
}
	public void getIntentt() {

		Intent intent = getIntent();
		maxDays = intent.getIntExtra("withSat", 5);
	}

	public void declaration() {
		
		mon1 = (AutoCompleteTextView) findViewById(R.id.mon1);
	
		mon2 = (AutoCompleteTextView) findViewById(R.id.mon2);
		mon3 = (AutoCompleteTextView) findViewById(R.id.mon3);
		mon4 = (AutoCompleteTextView) findViewById(R.id.mon4);

		tue1 = (AutoCompleteTextView) findViewById(R.id.tue1);
		tue2 = (AutoCompleteTextView) findViewById(R.id.tue2);
		tue3 = (AutoCompleteTextView) findViewById(R.id.tue3);
		tue4 = (AutoCompleteTextView) findViewById(R.id.tue4);

		wed1 = (AutoCompleteTextView) findViewById(R.id.wed1);

		wed2 = (AutoCompleteTextView) findViewById(R.id.wed2);

		wed3 = (AutoCompleteTextView) findViewById(R.id.wed3);
		wed4 = (AutoCompleteTextView) findViewById(R.id.wed4);

		thu1 = (AutoCompleteTextView) findViewById(R.id.thu1);
		thu2 = (AutoCompleteTextView) findViewById(R.id.thu2);
		thu3 = (AutoCompleteTextView) findViewById(R.id.thu3);
		thu4 = (AutoCompleteTextView) findViewById(R.id.thu4);

		fri1 = (AutoCompleteTextView) findViewById(R.id.fri1);
		fri2 = (AutoCompleteTextView) findViewById(R.id.fri2);
		fri3 = (AutoCompleteTextView) findViewById(R.id.fri3);
		fri4 = (AutoCompleteTextView) findViewById(R.id.fri4);

		sat1 = (AutoCompleteTextView) findViewById(R.id.sat1);
		sat2 = (AutoCompleteTextView) findViewById(R.id.sat2);
		sat3 = (AutoCompleteTextView) findViewById(R.id.sat3);
		sat4 = (AutoCompleteTextView) findViewById(R.id.sat4);
		sat = (Button) findViewById(R.id.satt);
       check = (Button) findViewById(R.id.check);
	}

	public void setListener() {
		mon1.setOnFocusChangeListener(this);
		mon2.setOnFocusChangeListener(this);
		mon3.setOnFocusChangeListener(this);
		mon4.setOnFocusChangeListener(this);

		tue1.setOnFocusChangeListener(this);
		tue2.setOnFocusChangeListener(this);
		tue3.setOnFocusChangeListener(this);
		tue4.setOnFocusChangeListener(this);

		wed1.setOnFocusChangeListener(this);
		wed2.setOnFocusChangeListener(this);
		wed3.setOnFocusChangeListener(this);
		wed4.setOnFocusChangeListener(this);

		thu1.setOnFocusChangeListener(this);
		thu2.setOnFocusChangeListener(this);
		thu3.setOnFocusChangeListener(this);
		thu4.setOnFocusChangeListener(this);

		fri1.setOnFocusChangeListener(this);
		fri2.setOnFocusChangeListener(this);
		fri3.setOnFocusChangeListener(this);
		fri4.setOnFocusChangeListener(this);

		sat1.setOnFocusChangeListener(this);
		sat2.setOnFocusChangeListener(this);
		sat3.setOnFocusChangeListener(this);
		sat4.setOnFocusChangeListener(this);

        check.setOnClickListener(this);
	}

	public void goAdd() {
		if (mon1.getText().toString().length() > 0) {
			addTo.add(mon1.getText().toString());
		}
		if (mon2.getText().toString().length() > 0) {
			addTo.add(mon2.getText().toString());
		}
		if (mon3.getText().toString().length() > 0) {
			addTo.add(mon3.getText().toString());
		}
		if (mon4.getText().toString().length() > 0) {
			addTo.add(mon4.getText().toString());
		}
		if (tue1.getText().toString().length() > 0) {
			addTo.add(tue1.getText().toString());
		}
		if (tue2.getText().toString().length() > 0) {
			addTo.add(tue2.getText().toString());
		}
		if (tue3.getText().toString().length() > 0) {
			addTo.add(tue3.getText().toString());
		}
		if (tue4.getText().toString().length() > 0) {
			addTo.add(tue4.getText().toString());
		}
		if (wed1.getText().toString().length() > 0) {
			addTo.add(wed1.getText().toString());
		}
		if (wed2.getText().toString().length() > 0) {
			addTo.add(wed2.getText().toString());
		}
		if (wed3.getText().toString().length() > 0) {
			addTo.add(wed3.getText().toString());
		}
		if (wed4.getText().toString().length() > 0) {
			addTo.add(wed4.getText().toString());
		}
		if (thu1.getText().toString().length() > 0) {
			addTo.add(thu1.getText().toString());
		}
		if (thu2.getText().toString().length() > 0) {
			addTo.add(thu2.getText().toString());
		}
		if (thu3.getText().toString().length() > 0) {
			addTo.add(thu3.getText().toString());
		}
		if (thu4.getText().toString().length() > 0) {
			addTo.add(thu4.getText().toString());
		}
		if (fri1.getText().toString().length() > 0) {
			addTo.add(fri1.getText().toString());
		}
		if (fri2.getText().toString().length() > 0) {
			addTo.add(fri2.getText().toString());
		}
		if (fri3.getText().toString().length() > 0) {
			addTo.add(fri3.getText().toString());
		}
		if (fri4.getText().toString().length() > 0) {
			addTo.add(fri4.getText().toString());
		}
		if (sat1.getText().toString().length() > 0) {
			addTo.add(sat1.getText().toString());
		}
		if (sat2.getText().toString().length() > 0) {
			addTo.add(sat2.getText().toString());
		}
		if (sat3.getText().toString().length() > 0) {
			addTo.add(sat3.getText().toString());
		}
		if (sat4.getText().toString().length() > 0) {
			addTo.add(sat4.getText().toString());
		}

	}

	public void maxCheck() {
		if (maxDays == 5) {
			sat1.setVisibility(View.GONE);
			sat2.setVisibility(View.GONE);
			sat3.setVisibility(View.GONE);
			sat4.setVisibility(View.GONE);
			sat.setVisibility(View.GONE);
		}
	}
public boolean checkFilter(){
	boolean tOrF=true;
	if(mon1.getText().toString().contains(" ")||mon1.getText().toString().contains("  ")){
		tOrF=false;	
	}
	else if(mon2.getText().toString().contains(" ")||mon2.getText().toString().contains("  ")){
		
		tOrF=false;	
	}
	else if(mon3.getText().toString().contains(" ")||mon3.getText().toString().contains("  ")){
	tOrF=false;	
	}
	else if(mon4.getText().toString().contains(" ")||mon4.getText().toString().contains("  ")){
		tOrF=false;		
	}
	else if(tue1.getText().toString().contains(" ")||tue1.getText().toString().contains("  ")){
		tOrF=false;		
	}
	else if(tue2.getText().toString().contains(" ")||tue2.getText().toString().contains("  ")){
		tOrF=false;		
	}
	else if(tue3.getText().toString().contains(" ")||tue3.getText().toString().contains("  ")){
		tOrF=false;		
	}
	else if(tue4.getText().toString().contains(" ")||tue4.getText().toString().contains("  ")){
		tOrF=false;		
	}
	else if(wed1.getText().toString().contains(" ")||wed1.getText().toString().contains("  ")){
		tOrF=false;		
	}
	else if(wed2.getText().toString().contains(" ")||wed2.getText().toString().contains("  ")){
		tOrF=false;		
	}
	else if(wed3.getText().toString().contains(" ")||wed3.getText().toString().contains("  ")){
		tOrF=false;		
	}
	else if(wed4.getText().toString().contains(" ")||wed4.getText().toString().contains("	")){
		tOrF=false;		
	}
	else if(thu1.getText().toString().contains(" ")||thu1.getText().toString().contains("  ")){
		tOrF=false;		
	}
	else if(thu2.getText().toString().contains(" ")||thu2.getText().toString().contains("  ")){
		tOrF=false;		
	}
	else if(thu3.getText().toString().contains(" ")||thu3.getText().toString().contains("  ")){
		tOrF=false;		
	}
	else if(thu4.getText().toString().contains(" ")||thu4.getText().toString().contains("  ")){
		tOrF=false;		
	}
	else if(fri1.getText().toString().contains(" ")||fri1.getText().toString().contains("  ")){
		tOrF=false;		
	}
	else if(fri2.getText().toString().contains(" ")||fri2.getText().toString().contains("  ")){
		tOrF=false;		
	}
	else if(fri3.getText().toString().contains(" ")||fri3.getText().toString().contains("  ")){
		tOrF=false;		
	}
	else if(fri4.getText().toString().contains(" ")||fri4.getText().toString().contains("  ")){
		tOrF=false;		
	}
	if(maxDays==6){
		 if(sat1.getText().toString().contains(" ")||sat1.getText().toString().contains("  ")){
			tOrF=false;		
		}
		 else if(sat1.getText().toString().contains(" ")||sat1.getText().toString().contains("  ")){
				tOrF=false;		
			}
		 else if(sat2.getText().toString().contains(" ")||sat2.getText().toString().contains("  ")){
				tOrF=false;		
			}
		 else if(sat3.getText().toString().contains(" ")||sat3.getText().toString().contains("  ")){
				tOrF=false;		
			}
		 else if(sat4.getText().toString().contains(" ")||sat4.getText().toString().contains("  ")){
				tOrF=false;		
			}
	}
	return tOrF;
}
	@Override
	public void onClick(View v) {
	
		if(checkFilter()){
if(v==check){
	try {
		OutputStreamWriter os = new OutputStreamWriter(openFileOutput("Periods.txt",0));
		
		os.write("mon"+"-"+1+"-"+mon1.getText().toString().length()+"-"+mon1.getText().toString()+'\n');
		Log.d("filter","wrote:"+"mon1"+"-"+mon1.getText().toString().length()+"-"+mon1.getText().toString());
		os.write("mon"+"-"+2+"-"+mon2.getText().toString().length()+"-"+mon2.getText().toString()+'\n');
		Log.d("filter","mon2"+" "+mon2.getText().toString().length()+" "+mon2.getText().toString()+'\n');
		os.write("mon"+"-"+3+"-"+mon3.getText().toString().length()+"-"+mon3.getText().toString()+'\n');
		os.write("mon"+"-"+4+"-"+mon4.getText().toString().length()+"-"+mon4.getText().toString()+'\n');
		os.write("tue"+"-"+1+"-"+tue1.getText().toString().length()+"-"+tue1.getText().toString()+'\n');
		os.write("tue"+"-"+2+"-"+tue2.getText().toString().length()+"-"+tue2.getText().toString()+'\n');
		os.write("tue"+"-"+3+"-"+tue3.getText().toString().length()+"-"+tue3.getText().toString()+'\n');
		os.write("tue"+"-"+4+"-"+tue4.getText().toString().length()+"-"+tue4.getText().toString()+'\n');
		os.write("wed"+"-"+1+"-"+wed1.getText().toString().length()+"-"+wed1.getText().toString()+'\n');
		os.write("wed"+"-"+2+"-"+wed2.getText().toString().length()+"-"+wed2.getText().toString()+'\n');
		os.write("wed"+"-"+3+"-"+wed3.getText().toString().length()+"-"+wed3.getText().toString()+'\n');
		os.write("wed"+"-"+4+"-"+wed4.getText().toString().length()+"-"+wed4.getText().toString()+'\n');
		os.write("thu"+"-"+1+"-"+thu1.getText().toString().length()+"-"+thu1.getText().toString()+'\n');
		os.write("thu"+"-"+2+"-"+thu2.getText().toString().length()+"-"+thu2.getText().toString()+'\n');
		os.write("thu"+"-"+3+"-"+thu3.getText().toString().length()+"-"+thu3.getText().toString()+'\n');
		os.write("thu"+"-"+4+"-"+thu4.getText().toString().length()+"-"+thu4.getText().toString()+'\n');
		os.write("fri"+"-"+1+"-"+fri1.getText().toString().length()+"-"+fri1.getText().toString()+'\n');
		os.write("fri"+"-"+2+"-"+fri2.getText().toString().length()+"-"+fri2.getText().toString()+'\n');
		os.write("fri"+"-"+3+"-"+fri3.getText().toString().length()+"-"+fri3.getText().toString()+'\n');
		os.write("fri"+"-"+4+"-"+fri4.getText().toString().length()+"-"+fri4.getText().toString()+'\n');
		if(maxDays==6){
			os.write("sat"+"-"+1+"-"+sat1.getText().toString().length()+"-"+sat1.getText().toString()+'\n');		
			os.write("sat"+"-"+2+"-"+sat2.getText().toString().length()+"-"+sat2.getText().toString()+'\n');
			os.write("sat"+"-"+3+"-"+sat3.getText().toString().length()+"-"+sat3.getText().toString()+'\n');	
			os.write("sat"+"-"+4+"-"+sat4.getText().toString().length()+"-"+sat4.getText().toString()+'\n');	
		}
		
		os.close();
		Intent intent = new Intent(fourClass.this, Calender.class);
		intent.putExtra("maxDays",maxDays);
		intent.putExtra("variety",4);
		startActivity(intent);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		Log.e("filter","file not found");
	} catch (IOException e) {
		// TODO Auto-generated catch block
	//	e.printStackTrace();
		Log.e("filter","io exception");
	}
	
}
		}
		else{
			Toast toast = Toast.makeText(this,
					getString(R.string.spaceError),
					Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	public void adap() {
		weekly = addTo.toArray(new String[addTo.size()]);
		auto = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, weekly);
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		
		if (v == mon1) {
			if (hasFocus) {
			
			
				goAdd();
				adap();
				mon1.setThreshold(1);
				mon1.setAdapter(auto);
			
				
			}
		} else if (v == mon2) {
	
			goAdd();
			adap();
			mon2.setThreshold(1);
			mon2.setAdapter(auto);
	
		} else if (v == mon3) {
		
			goAdd();
			adap();
			// mon3.showDropDown();
			mon3.setThreshold(1);
			mon3.setAdapter(auto);
	
		} else if (v == mon4) {

			goAdd();
			adap();
			mon4.setThreshold(1);
			mon4.setAdapter(auto);
		
		} else if (v == tue1) {

			goAdd();
			adap();
			tue1.setThreshold(1);
			tue1.setAdapter(auto);
		
		} else if (v == tue2) {

			goAdd();
			adap();
			tue2.setThreshold(1);
			tue2.setAdapter(auto);
		
		}
	else if(v==tue3){			
			goAdd();
			adap();
			tue3.setThreshold(1);
			tue3.setAdapter(auto);	
		
		}
	else if(v==tue4){			
		goAdd();
		adap();
		tue4.setThreshold(1);
		tue4.setAdapter(auto);	

	}
	else if(v==wed1){			
		goAdd();
		adap();
		wed1.setThreshold(1);
		wed1.setAdapter(auto);

	}
	else if(v==wed2){			
		goAdd();
		adap();
		wed2.setThreshold(1);
		wed2.setAdapter(auto);

	}
	else if(v==wed3){			
		goAdd();
		adap();
		wed3.setThreshold(1);
		wed3.setAdapter(auto);	
	
	}
	else if(v==wed4){			
		goAdd();
		adap();
		wed4.setThreshold(1);
		wed4.setAdapter(auto);	

	}
	else if(v==thu1){			
		goAdd();
		adap();
		thu1.setThreshold(1);
		thu1.setAdapter(auto);			
	}
	else if(v==thu2){			
		goAdd();
		adap();
		thu2.setThreshold(1);
		thu2.setAdapter(auto);

	}
	else if(v==thu3){			
		goAdd();
		adap();
		thu3.setThreshold(1);
		thu3.setAdapter(auto);		
	
	}
	else if(v==thu4){			
		goAdd();
		adap();
		thu4.setThreshold(1);
		thu4.setAdapter(auto);	
	
	}
	else if(v==fri1){			
		goAdd();
		adap();
		fri1.setThreshold(1);
		fri1.setAdapter(auto);
	
	}
	else if(v==fri2){			
		goAdd();
		adap();
		fri2.setThreshold(1);
		fri2.setAdapter(auto);	
		//fri2.setFilters(new InputFilter[]{filter});
	}
	else if(v==fri3){			
		goAdd();
		adap();
		fri3.setThreshold(1);
		fri3.setAdapter(auto);	
		//fri3.setFilters(new InputFilter[]{filter});
	}
	else if(v==fri4){			
		goAdd();
		adap();
		fri4.setThreshold(1);
		fri4.setAdapter(auto);		
		//fri4.setFilters(new InputFilter[]{filter});
	}
		
	else if(v==sat1){			
		goAdd();
		adap();
		sat1.setThreshold(1);
		sat1.setAdapter(auto);	
		//sat1.setFilters(new InputFilter[]{filter});
	}
	else if(v==sat2){			
		goAdd();
		adap();
		sat2.setThreshold(1);
		sat2.setAdapter(auto);	
		//sat2.setFilters(new InputFilter[]{filter});
	}
		
	else if(v==sat3){			
		goAdd();
		adap();
		sat3.setThreshold(1);
		sat3.setAdapter(auto);	
		//sat3.setFilters(new InputFilter[]{filter});
	}		
	else if(v==sat4){			
		goAdd();
		adap();
		sat4.setThreshold(1);
		sat4.setAdapter(auto);
		//sat4.setFilters(new InputFilter[]{filter});
	}
		         
	}
	 public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
         if (actionId == EditorInfo.IME_ACTION_DONE) {
             InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
             imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
             return true;    
         }
         return false;
     }  

}