package com.gmail.kpkarthick1995.attendancecare;



import android.app.Application;

public class allData extends Application {
	private int variety;
	private int date;
	private int month;
	private int year;
	private int maxDays;
	private String name;
	private int pa;
	private int overall=0;
	private int present=0;
	public void setVariety(int x)
	{
		variety = x;
	}	
	public int getVariety(){
		return variety;
	}
	public void setData(int date,int month,int year,int maxDays){
		this.date=date;
		this.month=month;
		this.year=year;
		this.maxDays=maxDays;
	}
	public int getMaxDays(){
		
		return maxDays;
	}
	public int getDate(){
	return date;	
	}
	public int getMonth(){
		return month;
	}
	public int getYear(){
		return year;
	}
	public void storeDat(String name,int i){
		this.name=name;
		pa=i;
		check();
	}
	
	public void check(){
		
		if(pa%3==1){
			present++;
			overall++;
		}
		else if(pa%3==2){
			overall++;
		}
	}
	public int getOverall(){
		return overall;
	}
}
