package com.titlebarui.lijie.titlebarui.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarNow {

	/** 获取当前时间 */
	public static String nowDate() {
		Date dt = new Date();// 如果不需要格式,可直接用dt,dt就是当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置显示格式
		String nowTime = "";
		nowTime = df.format(dt);
		return nowTime;
	}

	/** 计算年龄 */
	public static String getAge(String birthDay_) throws Exception {

		// Date birthDay = new Date(birthDay_);
		Date birthDay = stringToDate(birthDay_);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(birthDay);
		Calendar cal = Calendar.getInstance();
		if (cal.before(calendar)) {
			return 0 + "";
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}

		return age + "";
	}

	/** String 转化为时间 */
	public static Date stringToDate(String dateString) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
		return date;
	}
}
