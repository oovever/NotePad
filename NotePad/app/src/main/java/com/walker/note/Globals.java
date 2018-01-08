package com.walker.note;

/**
 * Created by walke on 2017/6/4.
 */
public class Globals
{
    public static final int MODEL_CHINA = 1;
    public static final int MODEL_ENGLISH = 2;

    public static int MODEL_LANGUAGE = MODEL_CHINA;

    public static void toggle()
    {
        if (MODEL_LANGUAGE == MODEL_CHINA)
        {
            MODEL_LANGUAGE = MODEL_ENGLISH;
        }
        else
        {
            MODEL_LANGUAGE = MODEL_CHINA;
        }
    }
}
