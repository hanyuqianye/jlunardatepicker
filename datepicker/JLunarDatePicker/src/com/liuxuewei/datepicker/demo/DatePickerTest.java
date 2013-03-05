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
		wannaday.set(1991, 0, 19);//ע��Calendar��set������month �Ǵ�0��ʼ������ָ������1991��9��19��
        Date date = wannaday.getTime();//��ȡָ������
        lunarDatePicker.setDate(date);//����Ҫ��ʾ��ũ������
        frame.getContentPane().add(lunarDatePicker);
        frame.setUndecorated(true);
        DatePickerMouseListener mml = new DatePickerMouseListener(lunarDatePicker);
        lunarDatePicker.addMouseListener(mml);
        lunarDatePicker.addMouseMotionListener(mml);

        frame.pack();
        frame.setVisible(true);
    }
}
