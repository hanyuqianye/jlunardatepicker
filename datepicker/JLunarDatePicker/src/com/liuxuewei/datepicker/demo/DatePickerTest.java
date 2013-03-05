package com.liuxuewei.datepicker.demo;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;

import com.liuxuewei.datepicker.JLunarDatePicker;
import com.liuxuewei.datepicker.actionlistener.DatePickerMouseListener;

public class DatePickerTest {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JLunarDatePicker lunarDatePicker = new JLunarDatePicker();
        Calendar wannaday = Calendar.getInstance(Locale.CHINA);
		wannaday.set(1991, 0, 19);//注意Calendar的set方法中month 是从0开始，这里指定的是1991年9月19日
        Date date = wannaday.getTime();//获取指定日期
        lunarDatePicker.setDate(date);//设置要显示的农历日期
        frame.getContentPane().add(lunarDatePicker);
        frame.setUndecorated(true);
        DatePickerMouseListener mml = new DatePickerMouseListener(lunarDatePicker);
        lunarDatePicker.addMouseListener(mml);
        lunarDatePicker.addMouseMotionListener(mml);

        frame.pack();
        frame.setVisible(true);
    }
}
