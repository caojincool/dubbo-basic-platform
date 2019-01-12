package com.basic.framework.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

public class WorkDayUtils {
	
	public static void main(String[] args) {
		try {
			String strDateStart = "2016-12-6";
//			String strDateEnd = "2017-12-8";	
			String strDateEnd = "10:20:30";
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			Date dateStart = sdf.parse(strDateStart);
			Date dateEnd = sdf2.parse(strDateEnd);
			WorkDayUtils app = new WorkDayUtils();
			Calendar calStart = Calendar.getInstance();
			Calendar calEnd = Calendar.getInstance();
			
//			cal_start.setTime(date_start);
//			System.out.println(cal_start.get(Calendar.DAY_OF_WEEK));
//			cal_end.setTime(date_end);
//			System.out.println("开始日：" + cal_start.get(Calendar.YEAR) + "-" + (cal_start.get(Calendar.MONTH) + 1) 
//					+ "-" + cal_start.get(Calendar.DAY_OF_MONTH) + " " + app.getChineseWeek(cal_start));
//			System.out.println("结束日：" + cal_end.get(Calendar.YEAR) + "-" + (cal_end.get(Calendar.MONTH) + 1)
//					+ "-" + cal_end.get(Calendar.DAY_OF_MONTH) + " " + app.getChineseWeek(cal_end));
//			System.out.println("工作日：" + app.getWorkingDay(cal_start, cal_end));
//			System.out.println("休息日：" + app.getHolidays(cal_start, cal_end));
			
//			List<Calendar> list = app.getDaysBetweenCalendar(cal_start, cal_end);
//			for (Calendar c : list) {
//				System.out.println(sdf.format(c.getTime()));
//			}
			
			Date date =  app.mergeDateTime(dateStart,dateEnd);
			System.out.println(sdf3.format(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取日期之间的天数
	 * @param d1
	 * @param d2
	 * @return
	 */
	public int getDaysBetween(java.util.Calendar d1, java.util.Calendar d2) {
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(java.util.Calendar.DAY_OF_YEAR)
				- d1.get(java.util.Calendar.DAY_OF_YEAR);
		int y2 = d2.get(java.util.Calendar.YEAR);
		if (d1.get(java.util.Calendar.YEAR) != y2) {
			d1 = (java.util.Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
				d1.add(java.util.Calendar.YEAR, 1);
			} while (d1.get(java.util.Calendar.YEAR) != y2);
		}
		return days;
	}

	/**
	 * 获取工作日
	 * @param d1
	 * @param d2
	 * @return 工作日天数
	 */
	public int getWorkingDay(java.util.Calendar d1, java.util.Calendar d2) {
		int result = -1;
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		// int betweendays = getDaysBetween(d1, d2);
		// int charge_date = 0;
		int chargeStartDate = 0;// 开始日期的日期偏移量
		int chargeEndDate = 0;// 结束日期的日期偏移量
		// 日期不在同一个日期内
		int stmp;
		int etmp;
		stmp = 7 - d1.get(Calendar.DAY_OF_WEEK);
		etmp = 7 - d2.get(Calendar.DAY_OF_WEEK);
		if (stmp != 0 && stmp != 6) {// 开始日期为星期六和星期日时偏移量为0
			chargeStartDate = stmp - 1;
		}
		if (etmp != 0 && etmp != 6) {// 结束日期为星期六和星期日时偏移量为0
			chargeEndDate = etmp - 1;
		}
		// }
		result = (getDaysBetween(this.getNextMonday(d1), this.getNextMonday(d2)) / 7)
				* 5 + chargeStartDate - chargeEndDate;
		// System.out.println("charge_start_date>" + charge_start_date);
		// System.out.println("charge_end_date>" + charge_end_date);
		// System.out.println("between day is-->" + betweendays);
		return result;
	}

	/**
	 * 获取中文日期
	 * @param date
	 * @return 
	 */
	public String getChineseWeek(Calendar date) {
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		int dayOfWeek = date.get(Calendar.DAY_OF_WEEK); 
		// System.out.println(dayNames[dayOfWeek - 1]);
		return dayNames[dayOfWeek - 1];
	}

	/**
	 * 获得日期的下一个星期一的日期
	 * @param date
	 * @return 
	 */
	public Calendar getNextMonday(Calendar date) {
		Calendar result = null;
		result = date;
		do {
			result = (Calendar) result.clone();
			result.add(Calendar.DATE, 1);
		} while (result.get(Calendar.DAY_OF_WEEK) != 2);
		return result;
	}

	/**
	 * 获取休息日
	 * @param d1
	 * @param d2
	 * @return 休息日天数
	 */
	public int getHolidays(Calendar d1, Calendar d2) {
		return this.getDaysBetween(d1, d2) - this.getWorkingDay(d1, d2);
	}
	
	/**
	 * 获取日期之间间隔日期
	 * @param d1
	 * @param d2
	 * @return
	 */
	public List<Calendar> getDaysBetweenCalendar(java.util.Calendar d1, java.util.Calendar d2) {
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		List<Calendar> calendars = Lists.newArrayList();
		
		if(d1.compareTo(d2) == 0){
			return calendars;
		}
		do {
			calendars.add((Calendar) d1.clone());
			
			d1.add(Calendar.DAY_OF_YEAR, 1);
		} while (d1.compareTo(d2)<=0);
	
		return calendars;
	}
	
	/**
	 * 获取日期之间间隔日期
	 * @param dateStr1
	 * @param dateStr2
	 * @return
	 */
	public List<Calendar> getDaysBetweenCalendar(String dateStr1, String dateStr2) {
		
		Calendar d1 =Calendar.getInstance();
		Calendar d2 = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			d1.setTime(sdf.parse(dateStr1));
			d2.setTime(sdf.parse(dateStr2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return this.getDaysBetweenCalendar(d1, d2);
	}
	
	/**
	 * 获取日期之间间隔日期
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<Calendar> getDaysBetweenCalendar(Date date1, Date date2) {
		
		Calendar d1 =Calendar.getInstance();
		Calendar d2 = Calendar.getInstance();
		
		d1.setTime(date1);
		d2.setTime(date2);
		
		return this.getDaysBetweenCalendar(d1, d2);
	}
	
	/**
	 * 
	 * @date 2017年12月19日 上午11:15:35
	 * @author Kevin
	 * @Description: 获取日期之间间隔日期
	 * @param date1
	 * @param date2
	 * @return
	 *
	 */
	public List<Date> getDaysBetweenDate(Date date1, Date date2){
		Calendar d1 =Calendar.getInstance();
		Calendar d2 = Calendar.getInstance();
		
		d1.setTime(date1);
		d2.setTime(date2);
		
		List<Calendar>  list = this.getDaysBetweenCalendar(d1, d2);
		
		List<Date> dateList = Lists.newArrayList();
		
		for (Calendar c : list) {
			dateList.add(c.getTime());
		}
		
		return dateList;
	}
	
	/**
	 * 
	 * @date 2017年12月12日 下午3:34:32
	 * @author Kevin
	 * @Description: 两个日期之间间隔秒数
	 * @param d1
	 * @param d2
	 * @return
	 *
	 */
	public long getDaysBetweenSecond(Date d1, Date d2) {
		
		long start = d1.getTime();
		long end = d2.getTime();
		long swap = 0;
		long interval = 0;
		
		//交换
		if(start > end){
			swap = start;
			start = end;
			end = swap;
		}
		
		interval = (end - start) / 1000;
		
		return interval;
	}
	
	/**
	 * 
	 * @date 2017年12月12日 下午3:53:13
	 * @author Kevin
	 * @Description: 根据开始时间和间隔秒数获去结束时间
	 * @param startDate 开始日期
	 * @param intervalSecond 间隔秒数
	 * @return
	 *
	 */
	public Date getEndDate(Date startDate, long intervalSecond ){
		
		return new Date(startDate.getTime()+ intervalSecond * 1000);
	}
	
	/**
	 * 
	 * @date 2017年12月19日 下午3:52:23
	 * @author Kevin
	 * @Description: 日期 、时间合并 例如： 2017-8-12 和 10:22:33  合并为 2017-8-12 10:22:33
	 * @param tagetDate
	 * @param time
	 * @return
	 *
	 */
	public Date mergeDateTime(Date tagetDate, Date time){
		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(tagetDate);
		c2.setTime(time);
		
		int hour = c2.get(Calendar.HOUR_OF_DAY);
		int minute = c2.get(Calendar.MINUTE);
		int second = c2.get(Calendar.SECOND);
		
		c1.add(Calendar.HOUR_OF_DAY, hour);
		c1.add(Calendar.MINUTE,minute );
		c1.add(Calendar.SECOND, second);
		
		return c1.getTime();
	}
	
}
