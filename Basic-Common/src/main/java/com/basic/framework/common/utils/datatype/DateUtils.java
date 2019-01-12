/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2015年12月1日 下午4:59:30
 * @author lzj
 * @Description: Date 工具集
 * 
 */
package com.basic.framework.common.utils.datatype;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.PropertiesUtils;
import com.basic.framework.common.utils.time.DateFormatUtil;

/**
 *
 * @date 2015年12月1日 下午4:59:30
 * @author lzj
 * @Description: Date 工具集
 * 
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static final String HOLIDAYS_PROPERTIES_PATH = "/config/properties/holidays.properties";
	
	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	public static Date now() {
		long t0 = System.currentTimeMillis();
		Date now = new Date(t0);
		return now;
	}
	
	/**
	 * 返回指定格式的时间字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		String str = null;
		
		if(date != null && StringUtils.isNotBlank(pattern)){
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			str = sdf.format(date);
		}
		
		return str;
	}
	
	/**
	 * 返回默认格式的时间字符串，默认格式为：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 */
	public static String defaultFormatDate(Date date) {
		String str = null;
		
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			str = sdf.format(date);
		}
		
		return str;
	}
	
	/**
	 * 比较两个日期大小
	 * <br>
	 * 0：相等、正数date1大、负数date2大
	 * @param date1
	 * @param date2
	 * @return 时间差
	 */
	public static long compare(Date date1, Date date2) {
		if(date1 == null && date2 == null) {
			return 0;
		}
		if(date1 == null) {
			return -1;
		}
		if(date2 == null) {
			return 1;
		}
		return date1.getTime() - date2.getTime();
	}
	
	/**
	 * 两个日期相减得到年龄
	 * @param Date birthDay
	 * @param Date buyDay
	 * @return 年龄
	 */
	public static int getAge(Date birthDay,Date buyDay) throws Exception {
    	int age;
    	long birth=birthDay.getTime();
    	long buy=buyDay.getTime();
    	if(birth>=buy){
    		 age=0;
    	}else{
    	        Calendar cal1 = Calendar.getInstance(); 
    	        cal1.setTime(birthDay);
    	        int yearBirth = cal1.get(Calendar.YEAR);  
    	        int monthBirth = cal1.get(Calendar.MONTH)+1;  
    	        int dayOfMonthBirth = cal1.get(Calendar.DAY_OF_MONTH); 
    	        
    	        Calendar cal2 = Calendar.getInstance(); 
	       	     cal2.setTime(buyDay);
	       	     int yearNow = cal2.get(Calendar.YEAR);  
    	        int monthNow = cal2.get(Calendar.MONTH)+1;  
    	        int dayOfMonthNow = cal2.get(Calendar.DAY_OF_MONTH);
    	        
    	         age = yearNow - yearBirth;  
    	  
    	        if (monthNow <= monthBirth) {  
    	            if (monthNow == monthBirth) {  
    	                if (dayOfMonthNow < dayOfMonthBirth) {
                            age--;
                        }
    	            }else{  
    	                age--;  
    	            }  
    	        } 
    	}
    	return age;
    }
	
	public static Date parse(String strDate, String pattern){  
        try {
			return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
		} catch (Exception e) {
			return now();
		}  
    }  
	
	/**
	 * 
	 * @date 2017年7月13日 上午11:49:19
	 * @author jie
	 * @Description: 传入时间和天数，得到某个日期，排除自定义的节假日和周六日
	 * @param date
	 * @param count
	 * @return
	 *
	 */
	public static Date getWorkDay(Date date, Long count){
		if(date == null){
			return null;
		}
		
		if(count == null){
			return null;
		}

//		String holidays = "2017-07-10,2017-07-12,2017-07-13,2016-10-01,2016-10-02,2016-10-03,2016-10-04,2016-10-05,2016-10-06,2016-10-07";
		Properties properties = PropertiesUtils.loadProperties(HOLIDAYS_PROPERTIES_PATH);//读取自定义日期
		String holidays = properties.getProperty("holidays");
		
		int everyDay = 0;
		long length = 0;
		Date middleTime = date;
		if(count.intValue() > 0){//正数
			everyDay = 1;
			length = count.intValue();
		}else{//负数
			everyDay = -1;
			length = -count.intValue();
		}
		for(long i=length; i>0; i--){
			middleTime = recursionDate(middleTime, holidays, everyDay);
//			System.out.println(DateUtils.formatDate(middleTime, "yyyy-MM-dd"));
		}
		
		return middleTime;
	}
	
	/**
	 * 
	 * @date 2017年7月13日 下午12:03:03
	 * @author jie
	 * @Description: 递归时间，直到得到不是自定义排除日期的那天
	 * @param middleTime
	 * @param holidays
	 * @param everyDay：一般是1或者-1
	 * @return
	 *
	 */
	private static Date recursionDate(Date middleTime, String holidays, int everyDay){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(middleTime);
//		calendar.add(Calendar.DATE, -1);
		calendar.add(Calendar.DATE, everyDay);
		middleTime = calendar.getTime();
		String middleTimeStr = DateUtils.formatDate(middleTime, "yyyy-MM-dd");
//		System.out.println(middleTimeStr);
//		System.out.println(holidays.contains(middleTimeStr));
		if(holidays.contains(middleTimeStr)){//存在
			middleTime = recursionDate(middleTime, holidays, everyDay);
		}else{
			return middleTime;
		}
		return middleTime;
	}

	/**
	 * 日志转为时间戳
	 * @param paramDate
	 * @return
	 */
	public static Timestamp dateToTimeStamp(java.util.Date paramDate)
	{
		if (paramDate == null) {
			return null;
		}
		return new Timestamp(paramDate.getTime());
	}

	/**
	 * 
	 * @date 2018年1月11日 下午5:16:26
	 * @author jie
	 * @Description: 两个时间之差的秒数
	 * 结束时间-开始时间
	 * @param startDay
	 * @param endDay
	 * @return
	 *
	 */
	public static long getSecond(Date startDay, Date endDay){
		long second = 0;
		if(startDay == null || endDay == null){
			return second;
		}
		
		second = (endDay.getTime() - startDay.getTime()) / 1000;
		return second;
	}
	
	
	/**
	 * 将传入时间初始化为当天的最初时间（即00时00分00秒）
	 * 
	 * @param date
	 *            时间
	 * @return 当天最初时间
	 */
	public static Date setAsBegin(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.clear(Calendar.MILLISECOND);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 将传入时间初始化为当天的结束时间（即23时59分59秒）
	 * 
	 * @param date
	 *            时间
	 * @return 当天结束时间
	 */
	public static Date setAsEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.clear(Calendar.MILLISECOND);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}
	
	/**
	 * 得到两日期间所有日期，含起始和结束日期
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 * @throws Exception
	 */
	public static Date[] getDaysBetween(Date startDate, Date endDate) {
		// 计算之间有多少天
		long day = (startDate.getTime() - endDate.getTime())
				/ (24 * 60 * 60 * 1000) > 0 ? (startDate.getTime() - endDate
				.getTime()) / (24 * 60 * 60 * 1000)
				: (endDate.getTime() - startDate.getTime())
						/ (24 * 60 * 60 * 1000);
		// 声明日期数组
		Date[] dateArr = new Date[Integer.valueOf(String.valueOf(day + 1))];
		// 将日期加进去
		for (int i = 0; i < dateArr.length; i++) {
			if (i == 0) {
				dateArr[i] = setAsBegin(startDate);
			} else {
				Date nextDay = DateUtils.addDays(startDate, 1);
				startDate = DateUtils.setAsBegin(nextDay);
				dateArr[i] = startDate;
			}
		}
		return dateArr;
	}
	
	/**
	 * 取得指定年月的天数
	 * 
	 * @param year
	 *            实际年份
	 * @param mon
	 *            实际月份 数值范围是1~12
	 * @return
	 */
	public static int getDaysOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);

	}
	
	/**
	 * 根据长整形的毫秒数返回字符串类型的时间段
	 * 
	 * @param millseconds
	 *            毫秒数
	 * @return
	 */
	public static String getTime(Long millseconds) {
		StringBuffer time = new StringBuffer();
		if (millseconds == null) {
            return "";
        }
		int days = (int) (long) millseconds / 1000 / 60 / 60 / 24;
		if (days > 0) {
            time.append(days).append("天");
        }
		long hourMillseconds = millseconds - days * 1000 * 60 * 60 * 24;
		int hours = (int) hourMillseconds / 1000 / 60 / 60;
		if (hours > 0) {
            time.append(hours).append("小时");
        }
		long minuteMillseconds = millseconds - days * 1000 * 60 * 60 * 24
				- hours * 1000 * 60 * 60;
		int minutes = (int) minuteMillseconds / 1000 / 60;
		if (minutes > 0) {
            time.append(minutes).append("分钟");
        }
		return time.toString();
	}
	
	/**
	 * 获取多少天以后的日期
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getNextDays(Date date, int days) {
		if(BeanUtils.isEmpty(date)){
			date = new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
	
	/**
	 * 计算两个日期相隔的年限
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getYears(Date startDate, Date endDate) {
		if(BeanUtils.isEmpty(startDate) || BeanUtils.isEmpty(endDate)){
			return 0;
		}
		Calendar startC = Calendar.getInstance();    
		startC.setTime(startDate);   
	    int sYear = startC.get(Calendar.YEAR);
	    
	    Calendar endC = Calendar.getInstance();    
	    endC.setTime(endDate);   
	    int eYear = endC.get(Calendar.YEAR);
	    return eYear - sYear;
	}
	
	/** 
     * 获取某年第一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getCurrYearFirst(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        Date currYearFirst = calendar.getTime();  
        return currYearFirst;  
    }
    
    /** 
     * 获取某年最后一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getCurrYearLast(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        calendar.roll(Calendar.DAY_OF_YEAR, -1);  
        Date currYearLast = calendar.getTime();  
          
        return currYearLast;  
    }
    
    /**
     * 获取两个日期之间的天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static double getDays(Date startDate, Date endDate) {
		// 计算之间有多少天
		long day = (startDate.getTime() - endDate.getTime())
				/ (24 * 60 * 60 * 1000) > 0 ? (startDate.getTime() - endDate
				.getTime()) / (24 * 60 * 60 * 1000)
				: (endDate.getTime() - startDate.getTime())
						/ (24 * 60 * 60 * 1000);
				
		return (double)Math.round(day);
    }
    
    /**
   	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
   	 */
   	public static String formatDate(Date date, Object... pattern) {
   		String formatDate = null;
   		if (pattern != null && pattern.length > 0) {
   			formatDate = DateFormatUtil.format(date, pattern[0].toString());
   		} else {
   			formatDate = DateFormatUtil.format(date, "yyyy-MM-dd");
   		}
   		return formatDate;
   	}
   	
    /**
     * 获取当月第一天
     * @return
     */
    public static Date getCurrMonthFirst(){  
    	Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.DAY_OF_MONTH, 1); 
        calendar.clear(Calendar.HOUR);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();
    }
    /**
     * 获取当月最后一天
     * @return
     */
    public static Date getCurrMonthLast(){  
    	Calendar calendar = Calendar.getInstance();  
        calendar.add(Calendar.MONTH, 1);  
        calendar.set(Calendar.DAY_OF_MONTH, 0);  
        calendar.clear(Calendar.HOUR);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();
    }

}