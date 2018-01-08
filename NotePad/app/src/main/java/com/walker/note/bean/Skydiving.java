package com.walker.note.bean;

import com.walker.library.utils.DateStyle;
import com.walker.library.utils.DateUtils;
import com.walker.note.Globals;

import java.util.Date;

/**
 * Created by walke on 2017/6/4.
 */
public class Skydiving
{
    private int id;
//    Jump No.
//    首次使用时手动输入，之后默认为上一次跳数加一
    private int no;

//        Date
//    默认为“今天”，跟随系统日期
    private long date;

//    Location
//    首次使用手动输入，之后每一次添加新数据默认为与上次相同
    private String location;

//    Canopy Model & Size
//    首次使用手动输入，之后每一次添加新数据默认为与上次相同
    private String canopyModel;

//        Aircraft
//    首次使用手动输入，之后每一次添加新数据默认为与上次相同
    private String aircraft;

//    Exit Altitude
//    首次使用手动输入，之后每一次添加新数据默认为与上次相同
    private String altitude;

//    Freefall Time
//    手动输入，单位为秒（s）
    private long time;

//    Total Freefall Time
//    对Freefall Time的累加，格式hh:mm:ss
    private long totalTime;

//    Distance to Target
//    选项：0-10 m、10-20m、>20m
    private String distance;

//        Detail
//    选项：Belly、Freefly、Tracking、Wingsuit、Hop-N-Pop
    private String detail;

//        Note
//    手动输入
    private String note;

    //（1）跳数 Jump No.
//    （2）日期 Date
//    （3）地点 Location
//    （4）主伞型号和尺寸 Canopy Model & Size
//    （5）飞机 Aircraft
//    （6）出舱高度 Exit Altitude
//    （7）自由落体时间 Freefall Time
//    （8）总自由落体时间 Total Freefall Time
//    （9）降落点距离 Distance to Target
//    （10）跳伞类型：Detail：Belly、Tracking、Freefly、wingsuit、Hop-N-Pop
//    （11）备注 Note

    @Override
    public String toString()
    {
        StringBuilder text = new StringBuilder();
        if (Globals.MODEL_LANGUAGE == Globals.MODEL_CHINA)
        {
            text.append("跳数:" + no + "\n");
            text.append("日期:" + DateUtils.dateToString(new Date(date), DateStyle.YYYY_MM_DD) + "\n");
            text.append("地点:" + location + "\n");
            text.append("主伞型号和尺寸:" + canopyModel + "\n");
            text.append("飞机:" + aircraft + "\n");
            text.append("出舱高度:" + altitude + "\n");
            text.append("自由落体时间:" + time + "\n");
            text.append("总自由落体时间:" + totalTime + "\n");
            text.append("降落点距离:" + distance + "\n");
            text.append("跳伞类型:" + detail + "\n");
            text.append("备注:" + note);
        }
        else
        {
            text.append("Jump No:" + no + "\n");
            text.append("Date:" + DateUtils.dateToString(new Date(date), DateStyle.YYYY_MM_DD) + "\n");
            text.append("Location:" + location + "\n");
            text.append("Canopy Model & Size:" + canopyModel + "\n");
            text.append("Aircraft:" + aircraft + "\n");
            text.append("Exit Altitude:" + altitude + "\n");
            text.append("Freefall Time:" + time + "\n");
            text.append("Total Freefall Time:" + totalTime + "\n");
            text.append("Distance to Target:" + distance + "\n");
            text.append("Detail:" + detail + "\n");
            text.append("Note:" + note);
        }
        return text.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int no) {
        this.id = no;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCanopyModel() {
        return canopyModel;
    }

    public void setCanopyModel(String canopyModel) {
        this.canopyModel = canopyModel;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getNo()
    {
        return no;
    }

    public void setNo(int no)
    {
        this.no = no;
    }
}
