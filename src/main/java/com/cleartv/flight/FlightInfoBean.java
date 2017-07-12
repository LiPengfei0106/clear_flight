package com.cleartv.flight;

/**
 * Created by Lee on 2017/7/11.
 */

public class FlightInfoBean {


    /**
     * FlightNum : CZ9378
     * AirlineCode : CZ
     * Airline : 南航
     * DepCity : 北京
     * ArrCity : 武汉
     * DepCode : PEK
     * ArrCode : WUH
     * OnTimeRate : 85.74%
     * DepTerminal : T2
     * ArrTerminal : T2
     * FlightDate : 2015-01-15
     * PEKDate : 2015-01-15
     * DepTime : 2015-01-15 08:05
     * ArrTime : 2015-01-15 10:15
     * Dexpected : 2015-01-15 08:05
     * Aexpected : 2015-01-15 10:15
     */

    private String FlightNum;
    private String DepTerminal;
    private String ArrTerminal;
    private String DepTime;
    private String ArrTime;
    private String Dexpected;
    private String Aexpected;

    public FlightInfoBean(String flightNum, String depTime, String dexpected, String arrTime, String aexpected, String depTerminal, String arrTerminal) {
        FlightNum = flightNum;
        DepTerminal = depTerminal;
        ArrTerminal = arrTerminal;
        DepTime = depTime;
        ArrTime = arrTime;
        Dexpected = dexpected;
        Aexpected = aexpected;
    }

    public String getFlightNum() {
        return FlightNum;
    }

    public void setFlightNum(String FlightNum) {
        this.FlightNum = FlightNum;
    }

    public String getDepTerminal() {
        return DepTerminal;
    }

    public void setDepTerminal(String DepTerminal) {
        this.DepTerminal = DepTerminal;
    }

    public String getArrTerminal() {
        return ArrTerminal;
    }

    public void setArrTerminal(String ArrTerminal) {
        this.ArrTerminal = ArrTerminal;
    }

    public String getDepTime() {
        return DepTime;
    }

    public void setDepTime(String DepTime) {
        this.DepTime = DepTime;
    }

    public String getArrTime() {
        return ArrTime;
    }

    public void setArrTime(String ArrTime) {
        this.ArrTime = ArrTime;
    }

    public String getDexpected() {
        return Dexpected;
    }

    public void setDexpected(String Dexpected) {
        this.Dexpected = Dexpected;
    }

    public String getAexpected() {
        return Aexpected;
    }

    public void setAexpected(String Aexpected) {
        this.Aexpected = Aexpected;
    }
}
