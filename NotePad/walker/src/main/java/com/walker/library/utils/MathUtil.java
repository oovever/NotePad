package com.walker.library.utils;

import android.text.TextUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MathUtil
{
    /**
     * @Description:如果输入为12.010000，返回 12.01 ;如果输入为12，返回12.00
     * @param:
     */
    public static String formatPrice(String price)
    {
        String tempPrice = "0.00";
        double d = 0;
        if (null != price && ! "".equals(price))
        {
            try
            {
                d = Double.parseDouble(price);
            } catch (NumberFormatException e)
            {
            }

            if (d != 0)
            {
                DecimalFormat df = new DecimalFormat("##0.00");
                tempPrice = df.format(Double.parseDouble(price));
            }
        }
        return tempPrice;
    }

    /**
     * @Description:如果输入为12.010000，返回 12.01 ;如果输入为12，返回12.00
     * @param:
     */
    public static String formatPrice(double price)
    {
        String tempPrice = "0.00";
        double d = price;
        if (d != 0)
        {
            DecimalFormat df = new DecimalFormat("##0.00");
            tempPrice = df.format(d);
        }
        return tempPrice;
    }

    /**
     * @Description:如果输入为12.010000，返回 12.01 ;如果输入为12，返回12.00
     * @param:
     */
    public static String formatThreeDecimal(double price)
    {
        String tempPrice = "0.00";
        double d = price;
        if (d != 0)
        {
            DecimalFormat df = new DecimalFormat("##0.000");
            tempPrice = df.format(d);
        }
        return tempPrice;
    }

    public static String formatNewsNum(String countStr)
    {
        return formatNewsNum(MathUtil.str2Int(countStr));
    }

    public static String formatNewsNum(int count)
    {
        if (count >= 10000)
        {
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(1);
            df.setGroupingSize(0);
            df.setRoundingMode(RoundingMode.FLOOR);
            return df.format(Float.valueOf(count) / 10000.0f) + "W";
        }
        return String.valueOf(count);
    }

    public static Double str2Double(String number)
    {
        return str2Double(number, 0);
    }

    public static Double str2Double(String number, double def)
    {
        if (TextUtils.isEmpty(number)) return def;

        try
        {
            return Double.valueOf(number);
        } catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        return def;
    }

    public static long str2Long(String number)
    {
        if (TextUtils.isEmpty(number)) return - 1;

        try
        {
            return Long.valueOf(number);
        } catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        return - 1;
    }

    public static Integer str2Int(String number)
    {
        if (TextUtils.isEmpty(number)) return - 1;

        try
        {
            return Integer.valueOf(number);
        } catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        return - 1;
    }

    public static Integer str2Int(String number, int defInt)
    {
        if (TextUtils.isEmpty(number)) return defInt;

        try
        {
            return Integer.valueOf(number);
        } catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        return defInt;
    }
}
