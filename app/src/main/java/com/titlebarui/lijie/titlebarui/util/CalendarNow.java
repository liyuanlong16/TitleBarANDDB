package com.titlebarui.lijie.titlebarui.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarNow {

	/** ��ȡ��ǰʱ�� */
	public static String nowDate() {
		Date dt = new Date();// �������Ҫ��ʽ,��ֱ����dt,dt���ǵ�ǰϵͳʱ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// ������ʾ��ʽ
		String nowTime = "";
		nowTime = df.format(dt);
		return nowTime;
	}

	/** �������� */
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

	/** String ת��Ϊʱ�� */
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
