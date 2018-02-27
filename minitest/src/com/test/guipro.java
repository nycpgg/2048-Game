package com.test;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class guipro extends JFrame{
	
	public static void main(String[] args) {
		
		JFrame mf = new JFrame();
		mf.setBounds(300, 300, 500, 500);
		mf.setLayout(new BorderLayout());
		//기본값이기때문에생략가능
		JButton north = new JButton("북");
		JButton south = new JButton("남");
		JButton east = new JButton("동");
		JButton west = new JButton("서");
		JButton center = new JButton("가운데");
		mf.add(north, "North"); //대소문자주의
		//mf.add(south, "South"); //순서상관없음
		//mf.add(east, "East");
		//mf.add(west, "West");
		mf.add(center, "Center");
		mf.setVisible(true);
		
		mf.setDefaultCloseOperation(mf.EXIT_ON_CLOSE);
		mf.setVisible(true);
	}

}
