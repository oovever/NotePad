package com.walker.note.bean;

import com.walker.library.app.App;
import com.walker.library.utils.DateStyle;
import com.walker.library.utils.DateUtils;
import com.walker.note.Globals;
import com.walker.note.R;

import java.util.Date;

/**
 * Created by walke on 2017/6/4.
 */
public class BaseJump
{
    private int id;
//    Jump No.
//    首次使用时手动输入，之后默认为上一次跳数加一
    private int no;

//        Date
//    默认为“今天”，跟随系统日期
    private long date;

//        Location
//    首次使用手动输入，之后每一次添加新数据默认为与上次相同
    private String location;

//        Object
//    选项：Building、Antenna、Span、Earth
    private String object;

//        Altitude
//    每次都是手动输入
    private String altitude;

//            Container
//    首次使用手动输入，之后每一次添加新数据默认为与上次相同
    private String container;

//    Canopy Model & Size
//    首次使用手动输入，之后每一次添加新数据默认为与上次相同
    private String canopyModel;

//    Pilot Chute Model & Size
//    首次使用手动输入，之后每一次添加新数据默认为与上次相同
    private String pilotChuteModel;

//    Distance to Target
//    选项：0-10 m、10-20m、>20m
    private String distance;

//    Freefall Time
//    手动输入，单位为秒（s）
    private long time;

//    Total Freefall Time
//    对Freefall Time的累加，格式hh:mm:ss
    private long totalTime;

//        Detail
//    选项：PCA、Belly、Freefly、Tracking、Wingsuit
    private String detail;

//        Note
//    手动输入
    private String note;

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

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getCanopyModel() {
        return canopyModel;
    }

    public void setCanopyModel(String canopyModel) {
        this.canopyModel = canopyModel;
    }

    public String getPilotChuteModel() {
        return pilotChuteModel;
    }

    public void setPilotChuteModel(String pilotChuteModel) {
        this.pilotChuteModel = pilotChuteModel;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
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


//    @Override
//    public String toString()
//    {
//        StringBuilder text = new StringBuilder();
//        text.append(App.getAppContext().getString(R.string.no) + ":" + no + "\n");
//        text.append(App.getAppContext().getString(R.string.date) + ":" + date + "\n");
//        text.append(App.getAppContext().getString(R.string.location) + ":" + location + "\n");
//        text.append(App.getAppContext().getString(R.string.canopy) + ":" + canopyModel + "\n");
//        text.append(App.getAppContext().getString(R.string.aircraft) + ":" + aircraft + "\n");
//        text.append(App.getAppContext().getString(R.string.altitude) + ":" + altitude + "\n");
//        text.append(App.getAppContext().getString(R.string.time) + ":" + time + "\n");
//        text.append(App.getAppContext().getString(R.string.totalTime) + ":" + totalTime + "\n");
//        text.append(App.getAppContext().getString(R.string.distance) + ":" + distance + "\n");
//        text.append(App.getAppContext().getString(R.string.detail) + ":" + detail + "\n");
//        text.append(App.getAppContext().getString(R.string.note) + ":" + note);
//        return text.toString();
//    }

    @Override
    public String toString()
    {
        StringBuilder text = new StringBuilder();
        if (Globals.MODEL_LANGUAGE == Globals.MODEL_CHINA)
        {
            text.append("跳数:" + no + "\n");
            text.append("日期:" + DateUtils.dateToString(new Date(date), DateStyle.YYYY_MM_DD) + "\n");
            text.append("地点:" + location + "\n");
            text.append("起跳点:" + object + "\n");
            text.append("出舱高度:" + altitude + "\n");
            text.append("伞包:" + container + "\n");
            text.append("引导伞型号和尺寸:" + pilotChuteModel + "\n");
            text.append("主伞型号和尺寸:" + canopyModel + "\n");
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
            text.append("location:" + location + "\n");
            text.append("object:" + object + "\n");
            text.append("altitude:" + altitude + "\n");
            text.append("container:" + container + "\n");
            text.append("Pilot Chute Model & Size:" + pilotChuteModel + "\n");
            text.append("Canopy Model & Size:" + canopyModel + "\n");
            text.append("Exit Altitude:" + altitude + "\n");
            text.append("Freefall Time:" + time + "\n");
            text.append("Total Freefall Time:" + totalTime + "\n");
            text.append("Distance to Target:" + distance + "\n");
            text.append("Detail:" + detail + "\n");
            text.append("Note:" + note);
        }
        return text.toString();
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
