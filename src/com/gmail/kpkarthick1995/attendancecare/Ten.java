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

public class Ten extends Activity implements OnClickListener,
		OnFocusChangeListener {
	
	private AutoCompleteTextView mon1,mon2, mon3, mon4, mon5, mon6, mon7, mon8,
			mon9, mon10;
	private AutoCompleteTextView tue1, tue2, tue3, tue4, tue5, tue6, tue7,
			tue8, tue9, tue10;
	private AutoCompleteTextView wed1, wed2, wed3, wed4, wed5, wed6, wed7,
			wed8, wed9, wed10;
	private AutoCompleteTextView thu1, thu2, thu3, thu4, thu5, thu6, thu7,
			thu8, thu9, thu10;
	private AutoCompleteTextView fri1, fri2, fri3, fri4, fri5, fri6, fri7,
			fri8, fri9, fri10;
	private AutoCompleteTextView sat1, sat2, sat3, sat4, sat5, sat6, sat7,
			sat8, sat9, sat10;
	private Button sat;
	private Button check;
	private int maxDays;
	private Set<String> addTo = new HashSet<String>();
	private ArrayAdapter<String> auto;
	private String[] weekly;
	private InputFilter filter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getclass_10);
		goToNew();
		getIntentt();
	declaration();
	filter();
		maxCheck();
		setListener();

	}
	public void goToNew(){
		AlertDialog alertDialog = new AlertDialog.Builder(Ten.this).create();

		

		alertDialog.setTitle("Caution:");

		alertDialog.setMessage("Please leave the boxes empty if " +
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
		mon5 = (AutoCompleteTextView) findViewById(R.id.mon5);
		mon6 = (AutoCompleteTextView) findViewById(R.id.mon6);
		mon7 = (AutoCompleteTextView) findViewById(R.id.mon7);
		mon8 = (AutoCompleteTextView) findViewById(R.id.mon8);
		mon9 = (AutoCompleteTextView) findViewById(R.id.mon9);
		mon10 = (AutoCompleteTextView) findViewById(R.id.mon10);

		tue1 = (AutoCompleteTextView) findViewById(R.id.tue1);
		tue2 = (AutoCompleteTextView) findViewById(R.id.tue2);
		tue3 = (AutoCompleteTextView) findViewById(R.id.tue3);
		tue4 = (AutoCompleteTextView) findViewById(R.id.tue4);
		tue5 = (AutoCompleteTextView) findViewById(R.id.tue5);
		tue6 = (AutoCompleteTextView) findViewById(R.id.tue6);
		tue7 = (AutoCompleteTextView) findViewById(R.id.tue7);
		tue8 = (AutoCompleteTextView) findViewById(R.id.tue8);
		tue9 = (AutoCompleteTextView) findViewById(R.id.tue9);
		tue10 = (AutoCompleteTextView) findViewById(R.id.tue10);

		wed1 = (AutoCompleteTextView) findViewById(R.id.wed1);

		wed2 = (AutoCompleteTextView) findViewById(R.id.wed2);

		wed3 = (AutoCompleteTextView) findViewById(R.id.wed3);
		wed4 = (AutoCompleteTextView) findViewById(R.id.wed4);
		wed5 = (AutoCompleteTextView) findViewById(R.id.wed5);

		wed6 = (AutoCompleteTextView) findViewById(R.id.wed6);

		wed7 = (AutoCompleteTextView) findViewById(R.id.wed7);
		wed8 = (AutoCompleteTextView) findViewById(R.id.wed8);
		wed9 = (AutoCompleteTextView) findViewById(R.id.wed9);

		wed10 = (AutoCompleteTextView) findViewById(R.id.wed10);


		
		
		thu1 = (AutoCompleteTextView) findViewById(R.id.thu1);
		thu2 = (AutoCompleteTextView) findViewById(R.id.thu2);
		thu3 = (AutoCompleteTextView) findViewById(R.id.thu3);
		thu4 = (AutoCompleteTextView) findViewById(R.id.thu4);
		thu5 = (AutoCompleteTextView) findViewById(R.id.thu5);
		thu6 = (AutoCompleteTextView) findViewById(R.id.thu6);
		thu7 = (AutoCompleteTextView) findViewById(R.id.thu7);
		thu8 = (AutoCompleteTextView) findViewById(R.id.thu8);
		thu9 = (AutoCompleteTextView) findViewById(R.id.thu9);
		thu10 = (AutoCompleteTextView) findViewById(R.id.thu10);

		fri1 = (AutoCompleteTextView) findViewById(R.id.fri1);
		fri2 = (AutoCompleteTextView) findViewById(R.id.fri2);
		fri3 = (AutoCompleteTextView) findViewById(R.id.fri3);
		fri4 = (AutoCompleteTextView) findViewById(R.id.fri4);
		fri5 = (AutoCompleteTextView) findViewById(R.id.fri5);
		fri6 = (AutoCompleteTextView) findViewById(R.id.fri6);
		fri7 = (AutoCompleteTextView) findViewById(R.id.fri7);
		fri8 = (AutoCompleteTextView) findViewById(R.id.fri8);
		fri9 = (AutoCompleteTextView) findViewById(R.id.fri9);
		fri10 = (AutoCompleteTextView) findViewById(R.id.fri10);
		
		sat1 = (AutoCompleteTextView) findViewById(R.id.sat1);
		sat2 = (AutoCompleteTextView) findViewById(R.id.sat2);
		sat3 = (AutoCompleteTextView) findViewById(R.id.sat3);
		sat4 = (AutoCompleteTextView) findViewById(R.id.sat4);
		sat5 = (AutoCompleteTextView) findViewById(R.id.sat5);
		sat6 = (AutoCompleteTextView) findViewById(R.id.sat6);
		sat7 = (AutoCompleteTextView) findViewById(R.id.sat7);
		sat8 = (AutoCompleteTextView) findViewById(R.id.sat8);
		sat9 = (AutoCompleteTextView) findViewById(R.id.sat9);
		sat10 = (AutoCompleteTextView) findViewById(R.id.sat10);
		
		
		sat = (Button) findViewById(R.id.satt);
		check = (Button) findViewById(R.id.check);
	}

	public void setListener() {
		mon1.setOnFocusChangeListener(this);
		mon2.setOnFocusChangeListener(this);
		mon3.setOnFocusChangeListener(this);
		mon4.setOnFocusChangeListener(this);
		mon5.setOnFocusChangeListener(this);
		mon6.setOnFocusChangeListener(this);
		mon7.setOnFocusChangeListener(this);
		mon8.setOnFocusChangeListener(this);
		mon9.setOnFocusChangeListener(this);
		mon10.setOnFocusChangeListener(this);
		
		
		tue1.setOnFocusChangeListener(this);
		tue2.setOnFocusChangeListener(this);
		tue3.setOnFocusChangeListener(this);
		tue4.setOnFocusChangeListener(this);
		tue5.setOnFocusChangeListener(this);
		tue6.setOnFocusChangeListener(this);
		tue7.setOnFocusChangeListener(this);
		tue8.setOnFocusChangeListener(this);
		tue9.setOnFocusChangeListener(this);
		tue10.setOnFocusChangeListener(this);

		
		wed1.setOnFocusChangeListener(this);
		wed2.setOnFocusChangeListener(this);
		wed3.setOnFocusChangeListener(this);
		wed4.setOnFocusChangeListener(this);
		wed5.setOnFocusChangeListener(this);
		wed6.setOnFocusChangeListener(this);
		wed7.setOnFocusChangeListener(this);
		wed8.setOnFocusChangeListener(this);
		wed9.setOnFocusChangeListener(this);
		wed10.setOnFocusChangeListener(this);
	
		thu1.setOnFocusChangeListener(this);
		thu2.setOnFocusChangeListener(this);
		thu3.setOnFocusChangeListener(this);
		thu4.setOnFocusChangeListener(this);
		thu5.setOnFocusChangeListener(this);
		thu6.setOnFocusChangeListener(this);
		thu7.setOnFocusChangeListener(this);
		thu8.setOnFocusChangeListener(this);
		thu9.setOnFocusChangeListener(this);
		thu10.setOnFocusChangeListener(this);

		fri1.setOnFocusChangeListener(this);
		fri2.setOnFocusChangeListener(this);
		fri3.setOnFocusChangeListener(this);
		fri4.setOnFocusChangeListener(this);
		fri5.setOnFocusChangeListener(this);
		fri6.setOnFocusChangeListener(this);
		fri7.setOnFocusChangeListener(this);
		fri8.setOnFocusChangeListener(this);
		fri9.setOnFocusChangeListener(this);
		fri10.setOnFocusChangeListener(this);

		
		sat1.setOnFocusChangeListener(this);
		sat2.setOnFocusChangeListener(this);
		sat3.setOnFocusChangeListener(this);
		sat4.setOnFocusChangeListener(this);
		sat5.setOnFocusChangeListener(this);
		sat6.setOnFocusChangeListener(this);
		sat7.setOnFocusChangeListener(this);
		sat8.setOnFocusChangeListener(this);
		sat9.setOnFocusChangeListener(this);
		sat10.setOnFocusChangeListener(this);
			
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
		if (mon5.getText().toString().length() > 0) {
			addTo.add(mon5.getText().toString());
		}
		if (mon6.getText().toString().length() > 0) {
			addTo.add(mon6.getText().toString());
		}
		if (mon7.getText().toString().length() > 0) {
			addTo.add(mon7.getText().toString());
		}
		if (mon8.getText().toString().length() > 0) {
			addTo.add(mon8.getText().toString());
		}if (mon9.getText().toString().length() > 0) {
			addTo.add(mon9.getText().toString());
		}
		if (mon10.getText().toString().length() > 0) {
			addTo.add(mon10.getText().toString());
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
		}	if (tue5.getText().toString().length() > 0) {
			addTo.add(tue5.getText().toString());
		}
		if (tue6.getText().toString().length() > 0) {
			addTo.add(tue6.getText().toString());
		}
		if (tue7.getText().toString().length() > 0) {
			addTo.add(tue7.getText().toString());
		}
		if (tue8.getText().toString().length() > 0) {
			addTo.add(tue8.getText().toString());
		}	if (tue9.getText().toString().length() > 0) {
			addTo.add(tue9.getText().toString());
		}
		if (tue10.getText().toString().length() > 0) {
			addTo.add(tue10.getText().toString());
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

		if (wed5.getText().toString().length() > 0) {
			addTo.add(wed5.getText().toString());
		}
		if (wed6.getText().toString().length() > 0) {
			addTo.add(wed6.getText().toString());
		}
		if (wed7.getText().toString().length() > 0) {
			addTo.add(wed7.getText().toString());
		}
		if (wed8.getText().toString().length() > 0) {
			addTo.add(wed8.getText().toString());
		}

		if (wed9.getText().toString().length() > 0) {
			addTo.add(wed9.getText().toString());
		}
		if (wed10.getText().toString().length() > 0) {
			addTo.add(wed10.getText().toString());
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
		}if (thu5.getText().toString().length() > 0) {
			addTo.add(thu5.getText().toString());
		}
		if (thu6.getText().toString().length() > 0) {
			addTo.add(thu6.getText().toString());
		}
		if (thu7.getText().toString().length() > 0) {
			addTo.add(thu7.getText().toString());
		}
		if (thu8.getText().toString().length() > 0) {
			addTo.add(thu8.getText().toString());
		}
		if (thu9.getText().toString().length() > 0) {
			addTo.add(thu7.getText().toString());
		}
		if (thu10.getText().toString().length() > 0) {
			addTo.add(thu8.getText().toString());
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
		if (fri5.getText().toString().length() > 0) {
			addTo.add(fri5.getText().toString());
		}
		if (fri6.getText().toString().length() > 0) {
			addTo.add(fri6.getText().toString());
		}
		if (fri7.getText().toString().length() > 0) {
			addTo.add(fri7.getText().toString());
		}
		if (fri8.getText().toString().length() > 0) {
			addTo.add(fri8.getText().toString());
		}
		if (fri9.getText().toString().length() > 0) {
			addTo.add(fri9.getText().toString());
		}
		if (fri10.getText().toString().length() > 0) {
			addTo.add(fri10.getText().toString());
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
		if (sat5.getText().toString().length() > 0) {
			addTo.add(sat5.getText().toString());
		}
		if (sat6.getText().toString().length() > 0) {
			addTo.add(sat6.getText().toString());
		}
		if (sat7.getText().toString().length() > 0) {
			addTo.add(sat7.getText().toString());
		}
		if (sat8.getText().toString().length() > 0) {
			addTo.add(sat8.getText().toString());
		}
		if (sat9.getText().toString().length() > 0) {
			addTo.add(sat9.getText().toString());
		}
		if (sat10.getText().toString().length() > 0) {
			addTo.add(sat10.getText().toString());
		}


	}

	public void maxCheck() {
		if (maxDays == 5) {
			sat1.setVisibility(View.GONE);
			sat2.setVisibility(View.GONE);
			sat3.setVisibility(View.GONE);
			sat4.setVisibility(View.GONE);
			sat5.setVisibility(View.GONE);
			sat6.setVisibility(View.GONE);
			sat7.setVisibility(View.GONE);
			sat8.setVisibility(View.GONE);
			sat9.setVisibility(View.GONE);
			sat10.setVisibility(View.GONE);
	
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
else if(mon5.getText().toString().contains(" ")||mon5.getText().toString().contains("  ")){
			
			tOrF=false;	
		}
		else if(mon6.getText().toString().contains(" ")||mon6.getText().toString().contains("  ")){
		tOrF=false;	
		}
		else if(mon7.getText().toString().contains(" ")||mon7.getText().toString().contains("  ")){
			tOrF=false;		
		}
else if(mon8.getText().toString().contains(" ")||mon8.getText().toString().contains("  ")){
			
			tOrF=false;	
		}
		else if(mon9.getText().toString().contains(" ")){
		tOrF=false;	
		}
		else if(mon10.getText().toString().contains(" ")){
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
		else if(tue5.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(tue6.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(tue7.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(tue8.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(thu9.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(thu10.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(wed1.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(wed2.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(wed3.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(wed4.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(wed5.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(wed6.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(wed7.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(wed8.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(wed9.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(wed10.getText().toString().contains(" ")){
			tOrF=false;		
		}
		
	
		else if(thu1.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(thu2.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(thu3.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(thu4.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(thu5.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(thu6.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(thu7.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(thu8.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(thu9.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(thu10.getText().toString().contains(" ")){
			tOrF=false;		
		}	

		
		else if(fri1.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(fri2.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(fri3.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(fri4.getText().toString().contains(" ")){
			tOrF=false;		
		}
		
		else if(fri5.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(fri6.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(fri7.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(fri8.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(fri9.getText().toString().contains(" ")){
			tOrF=false;		
		}
		else if(fri10.getText().toString().contains(" ")){
			tOrF=false;		
		}
		if(maxDays==6){
			 if(sat1.getText().toString().contains(" ")){
				tOrF=false;		
			}
		
			 else if(sat2.getText().toString().contains(" ")){
					tOrF=false;		
				}
			 else if(sat3.getText().toString().contains(" ")){
					tOrF=false;		
				}
			 else if(sat4.getText().toString().contains(" ")){
					tOrF=false;		
				}
			 else if(sat5.getText().toString().contains(" ")){
					tOrF=false;		
				}
			 else if(sat6.getText().toString().contains(" ")){
					tOrF=false;		
				}
			 else if(sat7.getText().toString().contains(" ")){
					tOrF=false;		
				}
			 else if(sat8.getText().toString().contains(" ")){
					tOrF=false;		
				}
			 else if(sat9.getText().toString().contains(" ")){
					tOrF=false;		
				}
			 else if(sat10.getText().toString().contains(" ")){
					tOrF=false;		
				}
			
		}
		return tOrF;
	}
	@Override
	public void onClick(View v) {
		if(checkFilter()){
		if (v == check) {
			OutputStreamWriter os;
			try {
			 os = new OutputStreamWriter(openFileOutput(
						"Periods.txt", 0));				
				os.write("mon"+"-"+1+"-"+mon1.getText().toString().length()+"-"+mon1.getText().toString()+'\n');			
				os.write("mon"+"-"+2+"-"+mon2.getText().toString().length()+"-"+mon2.getText().toString()+'\n');		
				os.write("mon"+"-"+3+"-"+mon3.getText().toString().length()+"-"+mon3.getText().toString()+'\n');
				os.write("mon"+"-"+4+"-"+mon4.getText().toString().length()+"-"+mon4.getText().toString()+'\n');
				os.write("mon"+"-"+5+"-"+mon5.getText().toString().length()+"-"+mon5.getText().toString()+'\n');
				os.write("mon"+"-"+6+"-"+mon6.getText().toString().length()+"-"+mon6.getText().toString()+'\n');			
				os.write("mon"+"-"+7+"-"+mon7.getText().toString().length()+"-"+mon7.getText().toString()+'\n');		
				os.write("mon"+"-"+8+"-"+mon8.getText().toString().length()+"-"+mon8.getText().toString()+'\n');
				os.write("mon"+"-"+9+"-"+mon9.getText().toString().length()+"-"+mon9.getText().toString()+'\n');
				os.write("mon"+"-"+10+"-"+mon10.getText().toString().length()+"-"+mon10.getText().toString()+'\n');
				
				os.write("tue"+"-"+1+"-"+tue1.getText().toString().length()+"-"+tue1.getText().toString()+'\n');				
				os.write("tue"+"-"+2+"-"+tue2.getText().toString().length()+"-"+tue2.getText().toString()+'\n');
				os.write("tue"+"-"+3+"-"+tue3.getText().toString().length()+"-"+tue3.getText().toString()+'\n');
				os.write("tue"+"-"+4+"-"+tue4.getText().toString().length()+"-"+tue4.getText().toString()+'\n');
				os.write("tue"+"-"+5+"-"+tue5.getText().toString().length()+"-"+tue5.getText().toString()+'\n');
				os.write("tue"+"-"+6+"-"+tue6.getText().toString().length()+"-"+tue6.getText().toString()+'\n');				
				os.write("tue"+"-"+7+"-"+tue7.getText().toString().length()+"-"+tue7.getText().toString()+'\n');
				os.write("tue"+"-"+8+"-"+tue8.getText().toString().length()+"-"+tue8.getText().toString()+'\n');
				os.write("tue"+"-"+9+"-"+tue9.getText().toString().length()+"-"+tue9.getText().toString()+'\n');
				os.write("tue"+"-"+10+"-"+tue10.getText().toString().length()+"-"+tue10.getText().toString()+'\n');
				
				os.write("wed"+"-"+1+"-"+wed1.getText().toString().length()+"-"+wed1.getText().toString()+'\n');
				os.write("wed"+"-"+2+"-"+wed2.getText().toString().length()+"-"+wed2.getText().toString()+'\n');
				os.write("wed"+"-"+3+"-"+wed3.getText().toString().length()+"-"+wed3.getText().toString()+'\n');
				os.write("wed"+"-"+4+"-"+wed4.getText().toString().length()+"-"+wed4.getText().toString()+'\n');
				os.write("wed"+"-"+5+"-"+wed5.getText().toString().length()+"-"+wed5.getText().toString()+'\n');
				os.write("wed"+"-"+6+"-"+wed6.getText().toString().length()+"-"+wed6.getText().toString()+'\n');
				os.write("wed"+"-"+7+"-"+wed7.getText().toString().length()+"-"+wed7.getText().toString()+'\n');
				os.write("wed"+"-"+8+"-"+wed8.getText().toString().length()+"-"+wed8.getText().toString()+'\n');
				os.write("wed"+"-"+9+"-"+wed9.getText().toString().length()+"-"+wed9.getText().toString()+'\n');
				os.write("wed"+"-"+10+"-"+wed10.getText().toString().length()+"-"+wed10.getText().toString()+'\n');
				
				os.write("thu"+"-"+1+"-"+thu1.getText().toString().length()+"-"+thu1.getText().toString()+'\n');
				os.write("thu"+"-"+2+"-"+thu2.getText().toString().length()+"-"+thu2.getText().toString()+'\n');
				os.write("thu"+"-"+3+"-"+thu3.getText().toString().length()+"-"+thu3.getText().toString()+'\n');
				os.write("thu"+"-"+4+"-"+thu4.getText().toString().length()+"-"+thu4.getText().toString()+'\n');
				os.write("thu"+"-"+5+"-"+thu5.getText().toString().length()+"-"+thu5.getText().toString()+'\n');
				os.write("thu"+"-"+6+"-"+thu6.getText().toString().length()+"-"+thu6.getText().toString()+'\n');
				os.write("thu"+"-"+7+"-"+thu7.getText().toString().length()+"-"+thu7.getText().toString()+'\n');
				os.write("thu"+"-"+8+"-"+thu8.getText().toString().length()+"-"+thu8.getText().toString()+'\n');
				os.write("thu"+"-"+9+"-"+thu9.getText().toString().length()+"-"+thu9.getText().toString()+'\n');
				os.write("thu"+"-"+10+"-"+thu10.getText().toString().length()+"-"+thu10.getText().toString()+'\n');
				
				os.write("fri"+"-"+1+"-"+fri1.getText().toString().length()+"-"+fri1.getText().toString()+'\n');
				os.write("fri"+"-"+2+"-"+fri2.getText().toString().length()+"-"+fri2.getText().toString()+'\n');
				os.write("fri"+"-"+3+"-"+fri3.getText().toString().length()+"-"+fri3.getText().toString()+'\n');
				os.write("fri"+"-"+4+"-"+fri4.getText().toString().length()+"-"+fri4.getText().toString()+'\n');
				os.write("fri"+"-"+5+"-"+fri5.getText().toString().length()+"-"+fri5.getText().toString()+'\n');
				os.write("fri"+"-"+6+"-"+fri6.getText().toString().length()+"-"+fri6.getText().toString()+'\n');
				os.write("fri"+"-"+7+"-"+fri7.getText().toString().length()+"-"+fri7.getText().toString()+'\n');
				os.write("fri"+"-"+8+"-"+fri8.getText().toString().length()+"-"+fri8.getText().toString()+'\n');
				os.write("fri"+"-"+9+"-"+fri9.getText().toString().length()+"-"+fri9.getText().toString()+'\n');
				os.write("fri"+"-"+10+"-"+fri10.getText().toString().length()+"-"+fri10.getText().toString()+'\n');
				if(maxDays==6){
					os.write("sat"+"-"+1+"-"+sat1.getText().toString().length()+"-"+sat1.getText().toString()+'\n');		
					os.write("sat"+"-"+2+"-"+sat2.getText().toString().length()+"-"+sat2.getText().toString()+'\n');
					os.write("sat"+"-"+3+"-"+sat3.getText().toString().length()+"-"+sat3.getText().toString()+'\n');	
					os.write("sat"+"-"+4+"-"+sat4.getText().toString().length()+"-"+sat4.getText().toString()+'\n');
					os.write("sat"+"-"+5+"-"+sat5.getText().toString().length()+"-"+sat5.getText().toString()+'\n');
					os.write("sat"+"-"+6+"-"+sat6.getText().toString().length()+"-"+sat6.getText().toString()+'\n');		
					os.write("sat"+"-"+7+"-"+sat7.getText().toString().length()+"-"+sat7.getText().toString()+'\n');
					os.write("sat"+"-"+8+"-"+sat8.getText().toString().length()+"-"+sat8.getText().toString()+'\n');	
					os.write("sat"+"-"+9+"-"+sat9.getText().toString().length()+"-"+sat9.getText().toString()+'\n');
					os.write("sat"+"-"+10+"-"+sat10.getText().toString().length()+"-"+sat10.getText().toString()+'\n');
				}
					os.close();
					Intent intent = new Intent(Ten.this, Calender.class);
					intent.putExtra("maxDays",maxDays);
					intent.putExtra("variety",10);
					startActivity(intent);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				Log.e("filter", "file not found");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				Log.e("filter", "io exception");
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
				//mon1.setFilters(new InputFilter[]{filter});
			}
		} else if (v == mon2) {
			
			goAdd();
			adap();
			mon2.setThreshold(1);
			mon2.setAdapter(auto);
			//mon2.setFilters(new InputFilter[]{filter});
		} else if (v == mon3) {
			Toast toast = Toast.makeText(this, getString(R.string.len0),
					Toast.LENGTH_SHORT);
			toast.show();
			goAdd();
			adap();
			mon3.setThreshold(1);
			mon3.setAdapter(auto);
			//mon3.setFilters(new InputFilter[]{filter});
		} else if (v == mon4) {

			goAdd();
			adap();
			mon4.setThreshold(1);
			mon4.setAdapter(auto);
			//mon4.setFilters(new InputFilter[]{filter});
		} 
else if (v == mon5) {
			
			goAdd();
			adap();
			mon5.setThreshold(1);
			mon5.setAdapter(auto);
			//mon5.setFilters(new InputFilter[]{filter});
		} else if (v == mon6) {
		
			
			goAdd();
			adap();
			mon6.setThreshold(1);
			mon6.setAdapter(auto);
			//mon6.setFilters(new InputFilter[]{filter});
		} else if (v == mon7) {

			goAdd();
			adap();
			mon7.setThreshold(1);
			mon7.setAdapter(auto);
		//	mon7.setFilters(new InputFilter[]{filter});
		}
else if (v == mon8) {
			
			goAdd();
			adap();
			mon8.setThreshold(1);
			mon8.setAdapter(auto);
			//mon8.setFilters(new InputFilter[]{filter});
		} else if (v == mon9) {
		
			
			goAdd();
			adap();
			mon9.setThreshold(1);
			mon9.setAdapter(auto);
			//mon9.setFilters(new InputFilter[]{filter});
		} else if (v == mon10) {

			goAdd();
			adap();
			mon10.setThreshold(1);
			mon10.setAdapter(auto);
			//mon10.setFilters(new InputFilter[]{filter});
		}
		
		else if (v == tue1) {

			goAdd();
			adap();
			tue1.setThreshold(1);
			tue1.setAdapter(auto);
			//tue1.setFilters(new InputFilter[]{filter});
		} else if (v == tue2) {

			goAdd();
			adap();
			tue2.setThreshold(1);
			tue2.setAdapter(auto);
			//tue2.setFilters(new InputFilter[]{filter});
		} else if (v == tue3) {
			goAdd();
			adap();
			tue3.setThreshold(1);
			tue3.setAdapter(auto);
			//tue3.setFilters(new InputFilter[]{filter});
		} else if (v == tue4) {
			goAdd();
			adap();
			tue4.setThreshold(1);
			tue4.setAdapter(auto);
			//tue4.setFilters(new InputFilter[]{filter});
		} 
		else if (v == tue5) {

			goAdd();
			adap();
			tue5.setThreshold(1);
			tue5.setAdapter(auto);
			//tue5.setFilters(new InputFilter[]{filter});
		} else if (v == tue6) {

			goAdd();
			adap();
			tue6.setThreshold(1);
			tue6.setAdapter(auto);
			//tue6.setFilters(new InputFilter[]{filter});
		} else if (v == tue7) {
			goAdd();
			adap();
			tue7.setThreshold(1);
			tue7.setAdapter(auto);
			//tue7.setFilters(new InputFilter[]{filter});
		} else if (v == tue8) {
			goAdd();
			adap();
			tue8.setThreshold(1);
			tue8.setAdapter(auto);
			//tue8.setFilters(new InputFilter[]{filter});
		}else if (v == tue9) {
			goAdd();
			adap();
			tue9.setThreshold(1);
			tue9.setAdapter(auto);
			//tue9.setFilters(new InputFilter[]{filter});
		} else if (v == tue10) {
			goAdd();
			adap();
			tue10.setThreshold(1);
			tue10.setAdapter(auto);
			//tue10.setFilters(new InputFilter[]{filter});
		}
		
		
		
		else if (v == wed1) {
			goAdd();
			adap();
			wed1.setThreshold(1);
			wed1.setAdapter(auto);
			//wed1.setFilters(new InputFilter[]{filter});
		} else if (v == wed2) {
			goAdd();
			adap();
			wed2.setThreshold(1);
			wed2.setAdapter(auto);
			//wed2.setFilters(new InputFilter[]{filter});
		} else if (v == wed3) {
			goAdd();
			adap();
			wed3.setThreshold(1);
			wed3.setAdapter(auto);
		//	wed3.setFilters(new InputFilter[]{filter});
		} else if (v == wed4) {
			goAdd();
			adap();
			wed4.setThreshold(1);
			wed4.setAdapter(auto);
			//wed4.setFilters(new InputFilter[]{filter});
		} 
		else if (v == wed5) {
			goAdd();
			adap();
			wed5.setThreshold(1);
			wed5.setAdapter(auto);
			//wed5.setFilters(new InputFilter[]{filter});
		} else if (v == wed6) {
			goAdd();
			adap();
			wed6.setThreshold(1);
			wed6.setAdapter(auto);
			//wed6.setFilters(new InputFilter[]{filter});
		} else if (v == wed7) {
			goAdd();
			adap();
			wed7.setThreshold(1);
			wed7.setAdapter(auto);
			///wed7.setFilters(new InputFilter[]{filter});
		} else if (v == wed8) {
			goAdd();
			adap();
			wed8.setThreshold(1);
			wed8.setAdapter(auto);
			//wed8.setFilters(new InputFilter[]{filter});
		} 
		else if (v == wed9) {
			goAdd();
			adap();
			wed9.setThreshold(1);
			wed9.setAdapter(auto);
			//wed9.setFilters(new InputFilter[]{filter});
		} else if (v == wed10) {
			goAdd();
			adap();
			wed10.setThreshold(1);
			wed10.setAdapter(auto);
			//wed10.setFilters(new InputFilter[]{filter});
		} 
		
		
		
		
		else if (v == thu1) {
			goAdd();
			adap();
			thu1.setThreshold(1);
			thu1.setAdapter(auto);
			//thu1.setFilters(new InputFilter[]{filter});
		} else if (v == thu2) {
			goAdd();
			adap();
			thu2.setThreshold(1);
			thu2.setAdapter(auto);
			//thu2.setFilters(new InputFilter[]{filter});
		} else if (v == thu3) {
			goAdd();
			adap();
			thu3.setThreshold(1);
			thu3.setAdapter(auto);
			//thu3.setFilters(new InputFilter[]{filter});
		} else if (v == thu4) {
			goAdd();
			adap();
			thu4.setThreshold(1);
			thu4.setAdapter(auto);
			//thu4.setFilters(new InputFilter[]{filter});
		} 
		else if (v == thu5) {
			goAdd();
			adap();
			thu5.setThreshold(1);
			thu5.setAdapter(auto);
			//thu5.setFilters(new InputFilter[]{filter});
		} else if (v == thu6) {
			goAdd();
			adap();
			thu6.setThreshold(1);
			thu6.setAdapter(auto);
			//thu6.setFilters(new InputFilter[]{filter});
		} else if (v == thu7) {
			goAdd();
			adap();
			thu7.setThreshold(1);
			thu7.setAdapter(auto);
			//thu7.setFilters(new InputFilter[]{filter});
		} else if (v == thu8) {
			goAdd();
			adap();
			thu8.setThreshold(1);
			thu8.setAdapter(auto);
			//thu8.setFilters(new InputFilter[]{filter});
		} 
		else if (v == thu9) {
			goAdd();
			adap();
			thu9.setThreshold(1);
			thu9.setAdapter(auto);
			//thu9.setFilters(new InputFilter[]{filter});
		} else if (v == thu10) {
			goAdd();
			adap();
			thu10.setThreshold(1);
			thu10.setAdapter(auto);
			//thu10.setFilters(new InputFilter[]{filter});
		} 
		
		
		
		
		else if (v == fri1) {
			goAdd();
			adap();
			fri1.setThreshold(1);
			fri1.setAdapter(auto);
			//fri1.setFilters(new InputFilter[]{filter});
		} else if (v == fri2) {
			goAdd();
			adap();
			fri2.setThreshold(1);
			fri2.setAdapter(auto);
			//fri2.setFilters(new InputFilter[]{filter});
		} else if (v == fri3) {
			goAdd();
			adap();
			fri3.setThreshold(1);
			fri3.setAdapter(auto);
			//fri3.setFilters(new InputFilter[]{filter});
		} else if (v == fri4) {
			goAdd();
			adap();
			fri4.setThreshold(1);
			fri4.setAdapter(auto);
			//fri4.setFilters(new InputFilter[]{filter});
		}
		else if (v == fri5) {
			goAdd();
			adap();
			fri5.setThreshold(1);
			fri5.setAdapter(auto);
			//fri5.setFilters(new InputFilter[]{filter});
		} else if (v == fri6) {
			goAdd();
			adap();
			fri6.setThreshold(1);
			fri6.setAdapter(auto);
			//fri6.setFilters(new InputFilter[]{filter});
		} else if (v == fri7) {
			goAdd();
			adap();
			fri7.setThreshold(1);
			fri7.setAdapter(auto);
			//fri7.setFilters(new InputFilter[]{filter});
		} else if (v == fri8) {
			goAdd();
			adap();
			fri8.setThreshold(1);
			fri8.setAdapter(auto);
			//fri8.setFilters(new InputFilter[]{filter});
		}
		else if (v == fri9) {
			goAdd();
			adap();
			fri9.setThreshold(1);
			fri9.setAdapter(auto);
			//fri9.setFilters(new InputFilter[]{filter});
		} else if (v == fri10) {
			goAdd();
			adap();
			fri10.setThreshold(1);
			fri10.setAdapter(auto);
			//fri10.setFilters(new InputFilter[]{filter});
		}
		
		
		else if (v == sat1) {
			goAdd();
			adap();
			sat1.setThreshold(1);
			sat1.setAdapter(auto);
			//sat1.setFilters(new InputFilter[]{filter});
		} else if (v == sat2) {
			goAdd();
			adap();
			sat2.setThreshold(1);
			sat2.setAdapter(auto);
			//sat2.setFilters(new InputFilter[]{filter});
		}

		else if (v == sat3) {
			goAdd();
			adap();
			sat3.setThreshold(1);
			sat3.setAdapter(auto);
			//sat3.setFilters(new InputFilter[]{filter});
		} else if (v == sat4) {
			goAdd();
			adap();
			sat4.setThreshold(1);
			sat4.setAdapter(auto);
			//sat4.setFilters(new InputFilter[]{filter});
		}
		else if (v == sat5) {
			goAdd();
			adap();
			sat5.setThreshold(1);
			sat5.setAdapter(auto);
			//sat5.setFilters(new InputFilter[]{filter});
		} else if (v == sat6) {
			goAdd();
			adap();
			sat6.setThreshold(1);
			sat6.setAdapter(auto);
			//sat6.setFilters(new InputFilter[]{filter});
		}

		else if (v == sat7) {
			goAdd();
			adap();
			sat7.setThreshold(1);
			sat7.setAdapter(auto);
			//sat7.setFilters(new InputFilter[]{filter});
		} else if (v == sat8) {
			goAdd();
			adap();
			sat8.setThreshold(1);
			sat8.setAdapter(auto);
			//sat8.setFilters(new InputFilter[]{filter});
		}

		else if (v == sat9) {
			goAdd();
			adap();
			sat9.setThreshold(1);
			sat9.setAdapter(auto);
			//sat9.setFilters(new InputFilter[]{filter});
		} else if (v == sat10) {
			goAdd();
			adap();
			sat10.setThreshold(1);
			sat10.setAdapter(auto);
			//sat10.setFilters(new InputFilter[]{filter});
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