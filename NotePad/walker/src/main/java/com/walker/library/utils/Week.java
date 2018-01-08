package com.walker.library.utils;

public enum Week
{
	MONDAY("周一", "Monday", "Mon.", 1),
	TUESDAY("周二", "Tuesday", "Tues.", 2),
	WEDNESDAY("周三", "Wednesday", "Wed.", 3),
	THURSDAY("周四", "Thursday", "Thur.", 4),
	FRIDAY("周五", "Friday", "Fri.", 5),
	SATURDAY("周六", "Saturday", "Sat.", 6),
	SUNDAY("周日", "Sunday", "Sun.", 7);
	
	String nameCn;
	String nameEn;
	String nameEnShort;
	int number;
	
	Week(String nameCn, String nameEn, String nameEnShort, int number)
	{
		this.nameCn = nameCn;
		this.nameEn = nameEn;
		this.nameEnShort = nameEnShort;
		this.number = number;
	}
	
	public String getChineseName()
	{
		return nameCn;
	}

	public String getName()
	{
		return nameEn;
	}

	public String getShortName()
	{
		return nameEnShort;
	}

	public int getNumber()
	{
		return number;
	}
}