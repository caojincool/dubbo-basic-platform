package com.basic.framework.common.utils.datatype;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @date 2017年7月13日 上午9:34:08
 * 
 * @Description: 传入时间和天数，得到某个日期，排除节假日
 * 
 */
public class DateTest {

	public static void main(String[] args) {
		Date date = getWorkDay(new Date(), 4L);
		System.out.println(DateUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss"));
	}
	
	public static Date getWorkDay(Date date, Long count){
		if(date == null){
			return null;
		}
		
		if(count == null){
			return null;
		}

		String holidays = "2017-07-10,2017-07-12,2017-07-13,2016-10-01,2016-10-02,2016-10-03,2016-10-04,2016-10-05,2016-10-06,2016-10-07";
		
		Date middleTime = date;
		for(long i=count; i>0; i--){
			middleTime = recursionDate(middleTime, holidays);
//			System.out.println(DateUtils.formatDate(middleTime, "yyyy-MM-dd"));
		}
		
		return middleTime;
	}

	private static Date recursionDate(Date middleTime, String holidays){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(middleTime);
		calendar.add(Calendar.DATE, -1);
		middleTime = calendar.getTime();
		String middleTimeStr = DateUtils.formatDate(middleTime, "yyyy-MM-dd");
//		System.out.println(middleTimeStr);
//		System.out.println(holidays.contains(middleTimeStr));
		if(holidays.contains(middleTimeStr)){//存在
			middleTime = recursionDate(middleTime, holidays);
		}else{
			return middleTime;
		}
		return middleTime;
	}
}
