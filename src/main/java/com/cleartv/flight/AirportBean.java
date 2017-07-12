package com.cleartv.flight;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lee on 2017/7/11.
 */

public class AirportBean implements Serializable{


    /**
     * city : 恩施
     * airport : 许家坪机场
     * airport3 : ENH
     * airport4 : ZHES
     */

    private Map<String,String> city;
    private Map<String,String> airport;
    private String airport3;
    private String airport4;

    public AirportBean(String zh_city,String en_city, String zh_airport,String en_airport, String airport3, String airport4) {
        HashMap<String,String> cityName = new HashMap<>();
        cityName.put("zh-CN",zh_city);
        cityName.put("en-US",en_city);
        this.city = cityName;
        HashMap<String,String> airportName = new HashMap<>();
        airportName.put("zh-CN",zh_airport);
        airportName.put("en-US",en_airport);
        this.airport = airportName;
        this.airport3 = airport3;
        this.airport4 = airport4;
    }

    public Map<String,String> getCity() {
        return city;
    }

    public Map<String,String> getAirport() {
        return airport;
    }

    public String getAirport3() {
        return airport3;
    }

    public String getAirport4() {
        return airport4;
    }
}
