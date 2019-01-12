package com.basic.framework.common.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;


public abstract class  StaticCommon {

	private static Logger logger = Logger.getLogger(StaticCommon.class);
	
	/**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";
     
    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
     
    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
     
    /**
     * 中文简写  如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";
     
    /**
     * 中文全称  如：2010年12月01日  23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
     
    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

	/**
	 * 获取分页的查询Sql
	 * 
	 * @param sql
	 * @param startIndex
	 * @param pageSize
	 * @return liudecheng 2016-4-12 16:49:33
	 */
	public  static final String  getSqlForPage(String sql, int startIndex, int pageSize) { 
		String sqlT ="";
		if(0==startIndex){
			sqlT="SELECT * FROM (SELECT T.* FROM (" + sql
					+ ")T ) limitPageT  limit " + startIndex + "," + pageSize;
		}else{
			sqlT = sql + " limit "+(startIndex-1)*pageSize+","+pageSize;
		}
		//logger.info("整理后sql="+sqlT);
		return sqlT;
	}
	/**
	 * 统计一共有多少条数据
	 * @param sql
	 * @param startIndex
	 * @param pageSize
	 * @return liudecheng 2016-4-12 16:49:33
	 */
	public  static final String  getSqlForPageTotal(String sql) { 
		String sqlT = "SELECT count(0) FROM (" + sql
				+ ")T  ";
		//logger.info("整理后sql="+sqlT);
		return sqlT;
	}
	
	/**
	 * 分页查询
	 * @author he.junwei 2016-4-19 11:05:21
	 * @param sql
	 * @param startIndex
	 * @param pageSize
	 * @return 
	 */
	
	public static String getSqlForPageNew(String sql, int startIndex, int pageSize){
		String sql4Page = sql + " limit "+(startIndex-1)*pageSize+","+pageSize;
		//logger.info("整理后sql="+sql4Page);
		return sql4Page;
	}
	
	
	/**
	 * 计算总的分页数
	 * @author he.junwei 2016-4-19 16:09:09
	 * @param total
	 * @param pageSize
	 * @return
	 */
	public static int getTotalPage(int total, int pageSize){
		if(total % pageSize == 0){
			return total / pageSize;
		}else{
			return total / pageSize +1 ;
		}
	}
	
	
	/**
	 * 计算两个时间差多少分钟
	 * add by liudecheng
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public static long calculationDateMinu(String beginDate,String endDate) throws Exception{
		
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数    
        long nh = 1000 * 60 * 60;// 一小时的毫秒数    
        long nm = 1000 * 60;// 一分钟的毫秒数    
        long diff;    
        long day = 0;    
        long hour = 0;    
        long min = 0;   
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 diff = sd.parse(endDate).getTime() - sd.parse(beginDate).getTime();    
         day = diff / nd;// 计算差多少天    
         hour = diff % nd / nh + day * 24;// 计算差多少小时    
         min = diff % nd % nh / nm +  hour * 60;// 计算差多少分钟    
         logger.info("计算两个时间差多少分钟beginDate="+beginDate+"=====endDate"+endDate+"===="+min);
		return min;
	}
	
	
	/**
	 * 获得传入时间的开始时间
	 *add by liudecheng 2016-6-17 15:50:50
	 * @param String startDateStr
	 * @throws ParseException
	 * @return String
	 */
	public static String getStartTime(String startDateStr) throws ParseException {
		// DAY HOR YEAR MIN
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");  
        Date d2=format.parse(startDateStr);  
        //返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。  
        long curMillisecond=d2.getTime();//当天的毫秒  
        DateFormat format2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date curMillisecondDate=new Date(curMillisecond);  
        return format2.format(curMillisecondDate);
	}
	/**
	 * 获得传入时间的结束时间
	 *add by liudecheng 2016-6-17 15:50:50
	 * @param String endDateStr
	 * @throws ParseException
	 * @return String
	 */
	public static String getEndTime(String endDateStr) throws ParseException {
		// DAY HOR YEAR MIN
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");  
		Date d2=format.parse(endDateStr);  
		int dayMis=1000*60*60*24;//一天的毫秒-1  
		//返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。  
		long curMillisecond=d2.getTime();//当天的毫秒  
		DateFormat format2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		long resultMis=curMillisecond+(dayMis-1); //当天最后一秒  
        //得到我需要的时间    当天最后一秒  
        Date resultDate=new Date(resultMis);  
		return format2.format(resultDate);
	}
  /** 使用预设格式将字符串转为Date */
    public static Date stringToDate(String strDate,String pattern) throws ParseException {
    	DateFormat format1 = new SimpleDateFormat(pattern);
    	return format1.parse(strDate);
    	/*SimpleDateFormat sdf=new SimpleDateFormat(pattern);//小写的mm表示的是分钟  
    	return  sdf.parse(strDate);*/  
    }  
    /** 使用预设格式将字符串转为Date */
    public static String stringDateFormat(String strDate,String pattern) throws ParseException {
    	DateFormat format1 = new SimpleDateFormat(pattern);
    	Date date = format1.parse(strDate);
    	return StaticCommon.dateTOString(date, pattern);
    }  
    /** 使用预设格式将字符串转为Date */
    public static Date dateFormat(Date strDate,String pattern) throws ParseException {
    	String dateStr = StaticCommon.dateTOString(strDate, pattern);
    	DateFormat format1 = new SimpleDateFormat(pattern);
    	return format1.parse(dateStr);
    }  
    /** 使用预设格式将字符串转为Date */
    public static String dateTOString(Date strDate,String pattern){  
    	SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.format(strDate);
    }
    
    /**
       * 获取时间戳
       */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }
   
    /**
     * 功能描述：返回月
     *
     * @param date
     *            Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }
     
    /**
     * 功能描述：返回日
     *
     * @param date
     *            Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    
    /**
     * @author liudecheng 
    * 功能描述：返回小
    *
    * @param date
    *            日期
    * @return 返回小时
    */
   public static int getHour(Date date) {
	   Calendar  calendar = Calendar.getInstance();
       calendar.setTime(date);
       return calendar.get(Calendar.HOUR_OF_DAY);
   }
   /**
    * @author liudecheng 
    * 功能描述：返回分
    *
    * @param date
    *            日期
    * @return 返回分钟
    */
   public static int getMinute(Date date) {
	   Calendar calendar = Calendar.getInstance();
       calendar.setTime(date);
       return calendar.get(Calendar.MINUTE);
   }
    
   /**
    * @author liudecheng 
    * 返回秒钟
    *
    * @param date
    *            Date 日期
    * @return 返回秒钟
    */
   public static int getSecond(Date date) {
	   Calendar calendar = Calendar.getInstance();
       calendar.setTime(date);
       return calendar.get(Calendar.SECOND);
   }
   /**
    * @author liudecheng 
    *加减天数
    *
    * @param date Date 日期
    * @param int dateNum 天数
    * @return 日期
    */
   public static Date addDate(Date date , int dateNum,String pattern) {
	    Calendar   calendar   =   new   GregorianCalendar(); 
	    calendar.setTime(date); 
	    calendar.add(Calendar.DATE,dateNum);//把日期往后增加一天.整数往后推,负数往前移动
	     date=calendar.getTime();   //这个时间就是日期往后推一天的结果
	     if(null != pattern){
	    	 SimpleDateFormat sdf=new SimpleDateFormat(pattern);//小写的mm表示的是分钟  
	    	 String dateString = sdf.format(date);
    	     try {
				return StaticCommon.stringToDate(dateString, pattern);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	     }else{
	    	 return date;
	     }
   }
   /**
    * @author liudecheng 
    *加减月份
    *
    * @param date Date 日期
    * @param int monthNum 月数
    * @return 日期
    */
   public static Date addMonth(Date date , int monthNum) {
	   Calendar   calendar   =   new   GregorianCalendar(); 
	   calendar.setTime(date); 
	   calendar.add(Calendar.MONTH,monthNum);
	   date=calendar.getTime();
	   return date;
   }
   /**
    * @author liudecheng 
    *加减年份
    *
    * @param date Date 日期
    * @param int year 年数
    * @return 日期
    */
   public static Date addYear(Date date , int yearNum) {
	   Calendar   calendar   =   new   GregorianCalendar(); 
	   calendar.setTime(date); 
	   calendar.add(Calendar.YEAR,yearNum);
	   date=calendar.getTime();
	   return date;
   }
   /**
    * @author liudecheng 
    *加减秒数
    *
    * @param date Date 日期
    * @param int dateNum 天数
    * @return 日期
    */
   public static Date addSecond(Date date , int secondNum) {
	   Calendar   calendar   =   new   GregorianCalendar();
	   calendar.setTime(date);
	   calendar.add(Calendar.SECOND,secondNum);
	   date=calendar.getTime();
	   return date;
   }

   /**
    * 根据日期得到星期几
    * "星期日","星期一","星期二","星期三","星期四","星期五","星期六"
    * 1,2,3,4,5,6,7
    * @param date
    * @return
    */
   public static int getWeek(Date date){
       String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};  
       Calendar cal = Calendar.getInstance();  
       cal.setTime(date);  
       int weekIndex = cal.get(Calendar.DAY_OF_WEEK) - 1;
       if(weekIndex<0){
           weekIndex = 0;
       }
       if( weekIndex == 0){
    	   weekIndex =7;
       }
       return weekIndex;
   }
   /**
    * @author liudecheng 2016-6-27 14:55:35
    * 计算两个日期之间相差的天数  
    * @param smdate 较小的时间 
    * @param bdate  较大的时间 
    * @return 相差天数 
    * @throws ParseException  
    */    
   public static int daysBetween(Date smdate,Date bdate) throws ParseException{    
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
       smdate=sdf.parse(sdf.format(smdate));  
       bdate=sdf.parse(sdf.format(bdate));  
       Calendar cal = Calendar.getInstance();    
       cal.setTime(smdate);    
       long time1 = cal.getTimeInMillis();                 
       cal.setTime(bdate);    
       long time2 = cal.getTimeInMillis();         
       long betweenDays=(time2-time1)/(1000*3600*24);
           
      return Integer.parseInt(String.valueOf(betweenDays));
   }
   /**
    * @author liudecheng 2016-6-27 14:55:35
    * 计算两个日期之间相差的天数  字符串的日期格式的计算 
    * @param smdate 较小的时间 
    * @param bdate  较大的时间 
    * @return 相差天数 
    * @throws ParseException  
    */
   public static int daysBetween(String smdate,String bdate) throws ParseException{  
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
       Calendar cal = Calendar.getInstance();    
       cal.setTime(sdf.parse(smdate));    
       long time1 = cal.getTimeInMillis();                 
       cal.setTime(sdf.parse(bdate));    
       long time2 = cal.getTimeInMillis();         
       long betweenDays=(time2-time1)/(1000*3600*24);
           
      return Integer.parseInt(String.valueOf(betweenDays));
   }
   
   /**
    * @author liudecheng 2016-8-19 16:19:20
    * 计算两个日期之间相差的分钟  字符串的日期格式的计算 
    * @param smdate 较小的时间 
    * @param bdate  较大的时间 
    * @return 相差分钟
    * @throws ParseException  
    */
   public static long daysMinute(String smdate,String bdate) throws ParseException{ 
	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   java.util.Date now = df.parse(bdate);
	   java.util.Date date=df.parse(smdate);
	   long l=now.getTime()-date.getTime();
	  /* long day=l/(24*60*60*1000);
	   long hour=(l/(60*60*1000)-day*24);
	   long min=((l/(60*1000))-day*24*60-hour*60);
	   long s=(l/1000-day*24*60*60-hour*60*60-min*60);*/
	   long minute = l/(60*1000);
	   return minute;
	  
   }
   
   public static long daysMinute(Date smdate,Date bdate) throws ParseException{ 
	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   //java.util.Date now = df.parse(bdate);
	   //java.util.Date date=df.parse(smdate);
	   long l=bdate.getTime()-smdate.getTime();
	  /* long day=l/(24*60*60*1000);
	   long hour=(l/(60*60*1000)-day*24);
	   long min=((l/(60*1000))-day*24*60-hour*60);
	   long s=(l/1000-day*24*60*60-hour*60*60-min*60);*/
	   long minute = l/(60*1000);
	   return minute;
	  
   }
   
   /**
    * @author liudecheng 2016-8-19 16:19:20
    * 计算两个日期之间相差的分钟  字符串的日期格式的计算 
    * @param smdate 较小的时间 
    * @param bdate  较大的时间 
    * @return 相差分钟
    * @throws ParseException  
    */
   public static long daysHour(String smdate,String bdate) throws ParseException{ 
	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   java.util.Date now = df.parse(bdate);
	   java.util.Date date=df.parse(smdate);
	   long l=now.getTime()-date.getTime();
	  // long day=l/(24*60*60*1000);
	   long hour=(l/(60*60*1000));
	  // long min=((l/(60*1000))-day*24*60-hour*60);
	   //long s=(l/1000-day*24*60*60-hour*60*60-min*60);
	   return hour;
	   
   }

   /**
    * 获得当月的最后一天
    * @author liudecheng
    * @param date
    * @return
    */
   public static String getMonthLastDay(Date date){
	   Calendar cal = Calendar.getInstance(); 
	   cal.setTime(date); 
	   cal.set(Calendar.DAY_OF_MONTH, 1); 
	   System.out.println (new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime())); 
	   cal.roll(Calendar.DAY_OF_MONTH, -1);  
	   return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
   }

   /** 
    * 判断给定日期是否为月末的一天 
    * @author liudecheng
    * @param date 
    * @return true:是|false:不是 
    */ 
    public static boolean isLastDayOfMonth(Date date) { 
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(date); 
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1)); 
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) { 
            return true; 
        } 
        return false; 
    }
    /** 
     * 判断给定日期是否为月初
     * @author LGK
     * @param date 
     * @return true:是|false:不是 
     */ 
    public static boolean isFirstDayOfMonth(Date date) { 
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(date);
        int first  = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        return first == calendar.get(Calendar.DAY_OF_MONTH)?true:false; 
    }
    
    /**
     * 
     * @date 2018年1月30日 上午10:34:54
     * @author LGK
     * @Description: 当月第一天
     * @return
     *
     */
	public  static Date getStartMonth(){
	    Calendar calendar = Calendar.getInstance();  
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.getActualMinimum(Calendar.DAY_OF_MONTH), 0, 0, 0);  
        Date startOfDate = calendar.getTime();
        return startOfDate;
	}
	
	/**
	 * 
	 * @date 2018年1月30日 上午10:34:40
	 * @author LGK
	 * @Description: 当月最后一天 23:59:59
	 * @return
	 *
	 */
	public  static Date getEndMonth(){
	    Calendar calendar = Calendar.getInstance();  
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);  
        Date beginOfDate = calendar.getTime();
        return beginOfDate;
	}
	
	/**
	 * 
	 * @date 2018年1月30日 上午10:34:40
	 * @author LGK
	 * @Description: 当月最后一天 0:0:0
	 * @return
	 *
	 */
	public  static Date getEndMonthDawn(){
	    Calendar calendar = Calendar.getInstance();  
        calendar.set(calendar.get(Calendar.YEAR), 
        		calendar.get(Calendar.MONTH),calendar.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);  
        Date beginOfDate = calendar.getTime();
        return beginOfDate;
	}
	
	/**
	 * 得到当天零点时间
	 * @return
	 */
	
	public static Date getStartDate(){
	    Calendar calendar = Calendar.getInstance();  
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND,0);
	    calendar.set(Calendar.MILLISECOND,0);
        Date beginOfDate = calendar.getTime();
        return beginOfDate;
	}
	
	/**
	 * 得到以今天为标准的偏移量的 0点
	 * @return
	 */
	
	public static Date getStartDate(Integer dayOffset){
	    Calendar calendar = Calendar.getInstance();  
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND,0);
	    calendar.set(Calendar.MILLISECOND,0);
	    calendar.add(Calendar.DATE,dayOffset);
        Date beginOfDate = calendar.getTime();
        return beginOfDate;
	}
	
	
	/**
	 *  得到以今天为标准的偏移量的 23.59.59
	 * @return
	 */
	public  static Date getEndDay(Integer dayOffset){
	    Calendar calendar = Calendar.getInstance();  
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),  
                23, 59, 59);  
        calendar.add(Calendar.DATE,dayOffset);
        Date beginOfDate = calendar.getTime();
        return beginOfDate;
	}
	
	/**
	 * 得到当天23.59.59
	 * @return
	 */
	public  static Date getEndDay(){
	    Calendar calendar = Calendar.getInstance();  
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),  
                23, 59, 59);  
        Date beginOfDate = calendar.getTime();
        return beginOfDate;
	}
	/**
	 * 
	 * @date 2018年1月31日 下午8:30:24
	 * @author LGK
	 * @Description:查询补全 秒数 ->针对datetime 2018-01-31 -> 2018-01-31 23:59:59
	 * @param end
	 * @return
	 *
	 */
	public static Date addMs(Date end){
		if(end == null){
			return null;
		}
		Calendar cal = Calendar.getInstance(); 
	   cal.setTime(end);
	   cal.set(Calendar.HOUR_OF_DAY,23);
	   cal.set(Calendar.MINUTE,59);
	   cal.set(Calendar.SECOND,59);
	   return cal.getTime();
		 
	}
	
	/**
	 * 
	 * @date 2018年3月29日 下午2:20:16
	 * @author LGK
	 * @Description: 凌晨算昨日
	 * @param date
	 * @return
	 *
	 */
	public static Boolean isTodayBeforeDawn(Date date){
		
		return isBetween(getStartDate(-1),getEndDay(-1),date);
	}
	
	/**
	 * 
	 * @date 2018年3月29日 下午2:20:16
	 * @author LGK
	 * @Description: 当月
	 * @param date
	 * @return
	 *
	 */
	public static Boolean isMoth(Date date){
		return isBetween(getStartMonth(),getEndMonth(),date);
	}
	
	public static Boolean isToday(Date date){
		
		return isBetween(getStartDate(),getEndDay(),date);
	}
	
	
	public static Boolean isBetween(Date startTime,Date endTime,Date date){
		
		if(startTime.getTime() <= date.getTime() && endTime.getTime() >= date.getTime()){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	
	
    
    
    public static void main(String[] args) {
    	System.out.println(isMoth(new Date()));;
    /*	System.out.println(isLastDayOfMonth(getEndMonth()));
    	System.out.println(addMs(new Date()));
    	System.out.println(getEndDay(-1));*/
    	/*System.out.println(isBetween(getStartDate(),getEndDay(),getStartMonth()));;*/
    	
	}
}
