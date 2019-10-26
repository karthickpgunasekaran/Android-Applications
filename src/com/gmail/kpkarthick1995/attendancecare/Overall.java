package com.gmail.kpkarthick1995.attendancecare;



public class Overall {
public int present;
public int total;
public String name;
public double percent;
	public Overall(String name,int present ,int total){
		this.name=name;
		this.present=present;
		this.total=total;
	}
	
	public void setName(String name){
		this.name=name;
	}
	public void setPresent(int present){
		this.present=present;
	}
	public void setTotal(int total){
		this.total=total;
	}
	public String getName(){
		return name;
	}
	public int getPresent(){
		return present;
	}
	public int getTotal(){
		return total;
	}
	public double getPercent(){
		
		
		 percent=(float)present*100/(float)total;
		percent = Math.round(percent * 10)/10;
	
		//String percentStr = Double.toString(percent);
		return percent;
	}
}
