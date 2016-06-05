package com.Baloot.util;



import java.text.SimpleDateFormat;
import java.util.Date;



public class DateHandle {
	public DateHandle(){
		
	}
	
	
	public Boolean validateTime(String time){
		String beginTime=time.substring(0,time.indexOf(":"));		
		String endTime=time.substring(time.indexOf(":")+1,time.length());		
		if(Integer.valueOf(beginTime)<0 || Integer.valueOf(beginTime)>23)
			return false;
		if(Integer.valueOf(endTime)<0 || Integer.valueOf(endTime)>59)
			return false;	
		return true;
	}

	public Boolean validateDate(String date){
		String year=date.substring(0,date.indexOf("/"));
		String month=date.substring(date.indexOf("/")+1,date.lastIndexOf("/"));
		String day=date.substring(date.lastIndexOf("/")+1,date.length());
		if(Integer.parseInt(year)==0)
			return false;
		if(Integer.parseInt(month)<=0 || Integer.parseInt(month)>12)
			return false;
		if(Integer.parseInt(month)>=1 && Integer.parseInt(month)<=6){
			if(Integer.parseInt(day)<1 || Integer.parseInt(day)>31)
				return false;
		}
		if(Integer.parseInt(month)>6 && Integer.parseInt(month)<12){
			if(Integer.parseInt(day)<1 || Integer.parseInt(day)>30)
				return false;
		}
		if(Integer.parseInt(month)==12){
			if(IsLeapYear(Integer.parseInt(year))){
				if(Integer.parseInt(day)<1 || Integer.parseInt(day)>30)
					return false;
			}
			else if(Integer.parseInt(day)<1 || Integer.parseInt(day)>29)
				return false;
		}
		return true;
	}
	
	/**
	 * @author azadeh
	 * this function uses in domain when want to conform date that get from xhtml to string for save in DB
	 * @param date Date
	 */
	public String DateToString(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd"); 
		if(date!=null)
			return format.format(date); 
		else
			return null;
	}
	
	/**
	 * @author azadeh
	 * this function uses in domain when want to conform String for date that get from DB to
	 *  Date for show in calendar of primefaces
	 *  @param date string
	 */
	public Date StringToDate(String date){
		try{			
			SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
			if(date!=null && date!="")			
				return format.parse(date);
			}catch(Exception ex)
			{
				System.err.println("eeeeeeeeeeeerrrrrrrrrrrrrrrrrroooooooorrrrrrrrrrinStringToDate"+ex);
			}
			return null ;
	}
	
	public Date StringToDateNew(String date){
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm");
			if(date!=null && date!="")
				return format.parse(date);
			}catch(Exception ex)
			{
				System.err.println("eeeeeeeeeeeerrrrrrrrrrrrrrrrrroooooooorrrrrrrrrrinStringToDateNew"+ex);
			}
			return null ;
	}
		
	
	/**
	 * @author azadeh
	 * this function uses in component when for create range for date in calendar 
	 * create rangi between today and 80 year ago
	 */	
	public String yearRange(){		
		PersianCalendar pc=new PersianCalendar();		
		Integer year=pc.getIranianYear();
		Integer mindate=year-80;		
		return (mindate.toString()+":"+year.toString());
	}
	
	public Boolean IsLeapYear(int y)
	{
	    int[] matches = { 1, 5, 9, 13, 17, 22, 26, 30 };
	    int modulus = y - ((y / 33) * 33);
	    Boolean K = false;
	    for (int n = 0; n != 8; n++) if (matches[n] == modulus) K = true;
	    return K;
	}

	public Date createMiladiDate(String Date,String time){
		String[] dates=Date.split("/");
		DateConverter dc=new DateConverter();
		dc.PersianToGregorian(Integer.parseInt(dates[0]) , Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
		String gDate=dc.toString();
		gDate=gDate.replaceAll("-", "/");
		return StringToDateNew(gDate+" "+time);
	}

	public Boolean validateEndDate(Date start,Date end) {
            
		PersianCalendar pc = new PersianCalendar();
		DateHandle datehandler = new DateHandle();
		if (end == null)
			return true;
		if (((datehandler.DateToString(start))
				.compareTo(datehandler.DateToString(end)) >= 1)) {
			return false;
		}
		return true;
	}


}
