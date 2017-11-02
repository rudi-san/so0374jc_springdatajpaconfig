package de.kbs.so0374jc.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Random;

public class GenerateRandom {

	private static final Random random	= new Random();
	private static final String gross = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ";
	private static final String klein = "abcdefghijklmnopqrstuvwxyzäöüß";
	
	public static long getBigInt () {
		long	l		= random.nextLong();
		l				= (l<0) ? l * -1 : l ;
		return 			l;
	}
	
	public static String getChar (int length) {
		length			= (length>0) ? length : 1;
		char[] c		= new char[length];
		Arrays.fill		(c, ' ');
		int strLength	= random.nextInt(length);
		c[0]			= gross.charAt(random.nextInt(gross.length()));
		for (int i=1;i<strLength;i++) 
			c[i]			= klein.charAt(random.nextInt(klein.length()));
		return			new String(c);
	}
	
	public static Date getDate () {
		int von 		= (int)(new GregorianCalendar(1990, 0, 1).getTimeInMillis()/1000);
		int bis 		= (int)(new GregorianCalendar(2020, 11, 31).getTimeInMillis()/1000);
		int time		= random.nextInt(bis - von)+von;
		return 			new Date(time*1000L);
	}
	
	public static BigDecimal getBigDecimal (int length, int nachkomma) {
		double d		= Math.pow(10.,	length)-1;
		int len			= new Double(d).intValue();
		return 			new BigDecimal(random.nextInt(len)).movePointLeft(nachkomma);
	}
	
	public static double getDouble () {
		return			random.nextDouble();
	}
	
	public static int getInt () {
		int i			= random.nextInt();
		i				= (i<0) ? i*-1 : i;
		return			i;
	}
	
	public static short getShort () {
		return			(short)random.nextInt(Short.MAX_VALUE);
	}
	
	public static Time getTime (int hour, int minute, int second) {
		long min		= hour*60 + minute;
		long sec		= min*60 + second;
		return			new Time(sec*1000);
	}
	
	public static Time getTime () {
		return 			getTime	( getInt(), getInt(), getInt());
	}
	
	public static Timestamp getTimestamp () {
		return 			new Timestamp(getDate().getTime());
	}
	
	public static String getVarchar (int length) {
		String ret		= "";
		int wordLen		= 25;
		int len			= random.nextInt(length);
		while (ret.length()<len) {
			ret				= 	ret.trim() 	+ 
								" " 		+ 
								getChar(random.nextInt(wordLen));
		}
		return			ret.substring(0,len);
	}
	 
	public static void main (String[] args) {
		for (int i=0;i<10;i++)
		System.out.println(getVarchar(255));
	}
}
