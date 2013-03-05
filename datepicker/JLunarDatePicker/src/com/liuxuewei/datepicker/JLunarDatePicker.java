package com.liuxuewei.datepicker;

import javax.swing.*;

import com.liuxuewei.datepicker.utils.ImageUtil;
import com.liuxuewei.datepicker.utils.LunarDayCalendar;

import java.awt.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class JLunarDatePicker extends JPanel {
	private static final long serialVersionUID = 1L;
	protected Image backgroundImg, todayImg, daysImg;
	protected SimpleDateFormat month = new SimpleDateFormat("MMMM");
	protected SimpleDateFormat year = new SimpleDateFormat("yyyy");
	protected SimpleDateFormat day = new SimpleDateFormat("d");
	protected Date date = new Date();

	public void setDate(Date date) {
		this.date = date;
	}

	public JLunarDatePicker() {
		backgroundImg = ImageUtil.getImageIconByShortName("calendar.png")
				.getImage();
		todayImg = ImageUtil.getImageIconByShortName("highlight.png")
				.getImage();
		daysImg = ImageUtil.getImageIconByShortName("day.png").getImage();
		// this.setBackground(Color.decode("#0000"));
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(300, 280));
	}

	public JLunarDatePicker(Image backgroundImg, Image todayImg, Image daysImg) {
		this.backgroundImg = backgroundImg;
		this.todayImg = todayImg;
		this.daysImg = daysImg;
		this.setPreferredSize(new Dimension(300, 280));
	}

	public void paintComponent(Graphics g) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		long[] LunarElements = LunarDayCalendar.getLunarElements(calendar);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(backgroundImg, 0, 0, null);
		g.setColor(Color.BLACK);
		g.setFont(new Font("ËÎÌå", Font.PLAIN, 14));
		g.drawString(LunarDayCalendar.getChinaMonth((int) LunarElements[1]),
				34, 36);
		g.setColor(Color.WHITE);
		g.drawString(LunarElements[0] + "Äê", 235, 26);
		g.drawString(
				LunarDayCalendar.cyclical((int) LunarElements[0])
						+ LunarDayCalendar.AnimalsYear(Integer.parseInt(year
								.format(calendar.getTime()))) + "Äê", 225, 40);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -cal.get(Calendar.DAY_OF_WEEK) + 1);
		int row = 0, col = 0;//Position of Lunar Day
		outer: for (int week = 0; week < 6; week++) {
			for (int days = 0; days < 7; days++) {
				// only draw if it's actually in this month
				if (cal.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)) {
					if (cal.equals(calendar)) {
						row = days;
						col = week;
						break outer;
					}
				}
				cal.add(Calendar.DATE, +1);
			}
		}
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -cal.get(Calendar.DAY_OF_WEEK) + 1);
		long firstDay = LunarDayCalendar.getLunarElements(cal)[2];
		g.setColor(Color.BLACK);
		for (int week = 0; week < 6; week++) {
			for (int days = 0; days < 7; days++) {
				Image img = daysImg;
				if (week == col && days == row) {
					img = todayImg;
				}
				g.drawImage(img, days * 30 + 46, week * 29 + 81, null);
				g.drawString(LunarDayCalendar.getChinaDay((int) firstDay++),
						days * 30 + 46, week * 29 + 81 + 20);
				if (firstDay == 31) {
					firstDay = 1;
				}
				cal.add(Calendar.DATE, +1);
			}
		}
	}
}
