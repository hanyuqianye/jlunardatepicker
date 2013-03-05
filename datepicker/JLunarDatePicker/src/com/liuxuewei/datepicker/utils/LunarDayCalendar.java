package com.liuxuewei.datepicker.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * ũ����һЩ����
 */
public class LunarDayCalendar {
	static SimpleDateFormat chineseDateFormat = new SimpleDateFormat(
			"yyyy��MM��dd��");
	final private static long[] lunarInfo = new long[] { 0x04bd8, 0x04ae0,
			0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0,
			0x055d2, 0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540,
			0x0d6a0, 0x0ada2, 0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5,
			0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970,
			0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3,
			0x092e0, 0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0,
			0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0,
			0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8,
			0x0e950, 0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570,
			0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5,
			0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0,
			0x195a6, 0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50,
			0x06d40, 0x0af46, 0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0,
			0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0,
			0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7,
			0x025d0, 0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50,
			0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954,
			0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260,
			0x0ea65, 0x0d530, 0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0,
			0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0,
			0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20,
			0x0ada0 };

	final private static int[] year20 = new int[] { 1, 4, 1, 2, 1, 2, 1, 1, 2,
			1, 2, 1 };
	final private static int[] year19 = new int[] { 0, 3, 0, 1, 0, 1, 0, 0, 1,
			0, 1, 0 };
	final private static int[] year2000 = new int[] { 0, 3, 1, 2, 1, 2, 1, 1,
			2, 1, 2, 1 };
	private final static String[] chinaMonth = new String[] { "����", "����", "����",
			"����", "����", "����", "����", "����", "����", "ʮ��", "ʮһ��", "����" };

	private final static String[] Gan = new String[] { "��", "��", "��", "��", "��",
			"��", "��", "��", "��", "��" };
	private final static String[] Zhi = new String[] { "��", "��", "��", "î", "��",
			"��", "��", "δ", "��", "��", "��", "��" };
	private final static String[] Animals = new String[] { "��", "ţ", "��", "��",
			"��", "��", "��", "��", "��", "��", "��", "��" };

	/**
	 * ����ũ�� y���������
	 * 
	 * @param y
	 * @return days in year
	 */
	final private static int YearDays(int y) {
		int i, sum = 348;
		for (i = 0x8000; i > 0x8; i >>= 1) {
			if ((lunarInfo[y - 1900] & i) != 0)
				sum += 1;
		}
		return (sum + leapDays(y));
	}

	/**
	 * ����ũ�� y�����µ�����
	 * 
	 * @param y
	 * @return
	 */
	final private static int leapDays(int y) {
		if (leapMonth(y) != 0) {
			if ((lunarInfo[y - 1900] & 0x10000) != 0)
				return 30;
			else
				return 29;
		} else
			return 0;
	}

	/**
	 * ����ũ�� y�����ĸ��� 1-12 , û�򴫻� 0
	 * 
	 * @param y
	 * @return lunar leap month
	 */
	final private static int leapMonth(int y) {
		return (int) (lunarInfo[y - 1900] & 0xf);
	}

	/**
	 * ����ũ�� y��m�µ�������
	 * 
	 * @param y
	 * @param m
	 * @return days in lunar month
	 */
	final private static int monthDays(int y, int m) {
		if ((lunarInfo[y - 1900] & (0x10000 >> m)) == 0)
			return 29;
		else
			return 30;
	}

	/**
	 * ����ũ�� y�����Ф
	 * 
	 * @param y
	 * @return animal of year
	 */
	final public static String AnimalsYear(int y) {
		return Animals[(y - 4) % 12];
	}

	/**
	 * ���� ���յ�offset ���ظ�֧,0=����
	 * 
	 * @param num
	 * @return
	 */
	final private static String cyclicalm(int num) {
		return (Gan[num % 10] + Zhi[num % 12]);
	}

	/**
	 * �������յ� offset ���ظ�֧, 0=����
	 * 
	 * @param year
	 * @return
	 */
	final public static String cyclical(int year) {
		int num = year - 1900 + 36;
		return (cyclicalm(num));
	}

	/**
	 * ����ũ��.year0 .month1 .day2 .yearCyl3 .monCyl4 .dayCyl5 .isLeap6
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	protected final long[] Lunar(int year, int month) {
		long[] nongDate = new long[7];
		int i = 0, temp = 0, leap = 0;
		Date baseDate = null;
		Date wannaDate = null;
		try {
			baseDate = chineseDateFormat.parse("1900��1��31��");
			wannaDate = chineseDateFormat.parse(year + "��" + month + "��" + 1
					+ "��");
		} catch (ParseException pex) {
			pex.printStackTrace();
		}
		long offset = (wannaDate.getTime() - baseDate.getTime()) / 86400000L;
		if (year < 2000)
			offset += year19[month - 1];
		if (year > 2000)
			offset += year20[month - 1];
		if (year == 2000)
			offset += year2000[month - 1];
		nongDate[5] = offset + 40;
		nongDate[4] = 14;

		for (i = 1900; i < 2050 && offset > 0; i++) {
			temp = YearDays(i);
			offset -= temp;
			nongDate[4] += 12;
		}
		if (offset < 0) {
			offset += temp;
			i--;
			nongDate[4] -= 12;
		}
		nongDate[0] = i;
		nongDate[3] = i - 1864;
		leap = leapMonth(i); // ���ĸ���
		nongDate[6] = 0;

		for (i = 1; i < 13 && offset > 0; i++) {
			// ����
			if (leap > 0 && i == (leap + 1) && nongDate[6] == 0) {
				--i;
				nongDate[6] = 1;
				temp = leapDays((int) nongDate[0]);
			} else {
				temp = monthDays((int) nongDate[0], i);
			}

			// �������
			if (nongDate[6] == 1 && i == (leap + 1))
				nongDate[6] = 0;
			offset -= temp;
			if (nongDate[6] == 0)
				nongDate[4]++;
		}

		if (offset == 0 && leap > 0 && i == leap + 1) {
			if (nongDate[6] == 1) {
				nongDate[6] = 0;
			} else {
				nongDate[6] = 1;
				--i;
				--nongDate[4];
			}
		}
		if (offset < 0) {
			offset += temp;
			--i;
			--nongDate[4];
		}
		nongDate[1] = i;
		nongDate[2] = offset + 1;
		return nongDate;
	}

	/**
	 * ����y��m��d�ն�Ӧ��ũ��.year0 .month1 .day2 .yearCyl3 .monCyl4 .dayCyl5 .isLeap6
	 * 
	 * @param y
	 * @param month
	 * @param day
	 * @return lunar elements
	 */
	final public static long[] lunarElements(int year, int month, int day) {
		long[] nongDate = new long[7];
		Date baseDate = null;
		Date wannaDate = null;
		try {
			baseDate = chineseDateFormat.parse("1900��1��31��");
			wannaDate = chineseDateFormat.parse(year + "��" + month + "��" + day
					+ "��");
		} catch (ParseException pex) {
			pex.printStackTrace();
		}
		int offset = (int) ((wannaDate.getTime() - baseDate.getTime()) / 86400000L);
		nongDate[5] = offset + 40;
		nongDate[4] = 14;
		int tempYear = 0, tempDaysInYear = 0, leapMonth = 0;
		for (tempYear = 1900; tempYear < 2050 && offset > 0; tempYear++) {
			tempDaysInYear = YearDays(tempYear);
			offset -= tempDaysInYear;
			nongDate[4] += 12;
		}
		if (offset < 0) {
			offset += tempDaysInYear;
			tempYear--;
			nongDate[4] -= 12;
		}
		nongDate[0] = tempYear;
		nongDate[3] = tempYear - 1864;
		leapMonth = leapMonth(tempYear); // ���ĸ���,1-12
		nongDate[6] = 0;
		// �õ��������offset,�����ȥÿ�£�ũ��������������������Ǳ��µĵڼ���
		int tempMonth, tempDaysInMonth = 0;
		for (tempMonth = 1; tempMonth < 13 && offset > 0; tempMonth++) {
			// ����
			if (leapMonth > 0 && tempMonth == (leapMonth + 1)
					&& nongDate[6] == 0) {
				--tempMonth;
				nongDate[6] = 1;
				tempDaysInMonth = leapDays((int) nongDate[0]);
			} else {
				tempDaysInMonth = monthDays((int) nongDate[0], tempMonth);
			}
			offset -= tempDaysInMonth;
			// �������
			if (nongDate[6] == 1 && tempMonth == (leapMonth + 1))
				nongDate[6] = 0;
			if (nongDate[6] == 0)
				nongDate[4]++;
		}
		// offsetΪ0ʱ�����Ҹղż�����·������£�ҪУ��
		if (offset == 0 && leapMonth > 0 && tempMonth == leapMonth + 1) {
			if (nongDate[6] == 1) {
				nongDate[6] = 0;
			} else {
				nongDate[6] = 1;
				--tempMonth;
				--nongDate[4];
			}
		}
		if (offset < 0) {
			offset += tempDaysInMonth;
			--tempMonth;
			--nongDate[4];
		}
		nongDate[1] = tempMonth;
		nongDate[2] = offset + 1;
		return nongDate;
	}

	public final static String getChinaMonth(int month) {
		switch (month) {
		case 1:
			return chinaMonth[0];
		case 2:
			return chinaMonth[1];
		case 3:
			return chinaMonth[2];
		case 4:
			return chinaMonth[3];
		case 5:
			return chinaMonth[4];
		case 6:
			return chinaMonth[5];
		case 7:
			return chinaMonth[6];
		case 8:
			return chinaMonth[7];
		case 9:
			return chinaMonth[8];
		case 10:
			return chinaMonth[9];
		case 11:
			return chinaMonth[10];
		case 12:
			return chinaMonth[11];
		default:
			return chinaMonth[0];
		}
	}

	public final static String getChinaDay(int day) {
		String a = "";
		if (day == 10)
			return "��ʮ";
		if (day == 20)
			return "��ʮ";
		if (day == 30)
			return "��ʮ";
		int two = (int) ((day) / 10);
		if (two == 0)
			a = "��";
		if (two == 1)
			a = "ʮ";
		if (two == 2)
			a = "إ";
		if (two == 3)
			a = "��";
		int one = (int) (day % 10);
		switch (one) {
		case 1:
			a += "һ";
			break;
		case 2:
			a += "��";
			break;
		case 3:
			a += "��";
			break;
		case 4:
			a += "��";
			break;
		case 5:
			a += "��";
			break;
		case 6:
			a += "��";
			break;
		case 7:
			a += "��";
			break;
		case 8:
			a += "��";
			break;
		case 9:
			a += "��";
			break;
		}
		return a;
	}

	public static long[] todayLunarElements() {
		Calendar today = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);
		long[] l = lunarElements(year, month, date);
		return l;
	}
	public static String todayLunar() {
		Calendar today = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);
		long[] l = lunarElements(year, month, date);
		return l[0]+LunarDayCalendar.cyclical((int) l[0])+"��"+getChinaMonth((int) l[1])+getChinaDay((int) l[2]);
	}
	public static String getLunar(Calendar wannaday) {
		int year = wannaday.get(Calendar.YEAR);
		int month = wannaday.get(Calendar.MONTH)+1;
		int date = wannaday.get(Calendar.DATE);
		long[] l = lunarElements(year, month, date);
		return l[0]+"��"+getChinaMonth((int) l[1])+getChinaDay((int) l[2]);
	}
	public static String todayYearMonthDayLunar() {
		Calendar today = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);
		long[] l = lunarElements(year, month, date);
		return l[0]+"-"+(l[1] > 9? l[1]:"0"+l[1])+"-"+(l[2] > 9? l[2]:"0"+l[2]);
	}
	public static String todayMonthDayLunar() {
		Calendar today = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);
		long[] l = lunarElements(year, month, date);
		return (l[1] > 9? l[1]:"0"+l[1])+"-"+(l[2] > 9? l[2]:"0"+l[2]);
	}
	public static String todayDayLunar() {
		Calendar today = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);
		long[] l = lunarElements(year, month, date);
		return ""+(l[2] > 9? l[2]:"0"+l[2]);
	}

	public static long[] getLunarElements(Calendar wannaday) {
		int year = wannaday.get(Calendar.YEAR);
		int month = wannaday.get(Calendar.MONTH) + 1;
		int date = wannaday.get(Calendar.DATE);
		long[] l = lunarElements(year, month, date);
		return l;
	}
	
	public static String getYearMonthDayLunar(Calendar wannaday) {
		int year = wannaday.get(Calendar.YEAR);
		int month = wannaday.get(Calendar.MONTH) + 1;
		int date = wannaday.get(Calendar.DATE);
		long[] l = lunarElements(year, month, date);
		return l[0]+"-"+(l[1] > 9? l[1]:"0"+l[1])+"-"+(l[2] > 9? l[2]:"0"+l[2]);
	}
	public static String getMonthDayLunar(Calendar wannaday) {
		int year = wannaday.get(Calendar.YEAR);
		int month = wannaday.get(Calendar.MONTH) + 1;
		int date = wannaday.get(Calendar.DATE);
		long[] l = lunarElements(year, month, date);
		return (l[1] > 9? l[1]:"0"+l[1])+"-"+(l[2] > 9? l[2]:"0"+l[2]);
	}
	public static String getDayLunar(Calendar wannaday) {
		int year = wannaday.get(Calendar.YEAR);
		int month = wannaday.get(Calendar.MONTH) + 1;
		int date = wannaday.get(Calendar.DATE);
		long[] l = lunarElements(year, month, date);
		return ""+(l[2] > 9? l[2]:"0"+l[2]);
	}

	/**
	 * ũ����������ʹ����ʾ
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
			
			Calendar today = Calendar.getInstance(Locale.CHINA);
			System.out.println(today.get(Calendar.YEAR)+"-"+today.get(Calendar.MONTH)+"-"+today.get(Calendar.DAY_OF_MONTH));
			System.out.println( LunarDayCalendar.todayLunar() + "-" + AnimalsYear(2013) + "��");
			
			System.out.println("-------------------------");
			
			Calendar wannaday = Calendar.getInstance(Locale.CHINA);
			wannaday.set(1991,0, 19);//ע��Calendar��set������month ��0��ʼ
			System.out.println(wannaday.get(Calendar.YEAR)+"-"+wannaday.get(Calendar.MONTH)+"-"+wannaday.get(Calendar.DAY_OF_MONTH));
			System.out.println(LunarDayCalendar.getLunar(wannaday)+cyclical(wannaday.get(Calendar.YEAR)) + "-" + AnimalsYear(wannaday.get(Calendar.YEAR)) + "��");
	}
}