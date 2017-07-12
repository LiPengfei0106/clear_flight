package com.cleartv.flight;

import java.util.List;

/**
 * Created by Lee on 2017/7/10.
 */

public class JuHeData {


    /**
     * reason : 查询成功!
     * result : [{"currencyF":"CNY","currencyF_Name":"人民币","currencyT":"USD","currencyT_Name":"美元","currencyFD":"1","exchange":"0.1470","result":"0.1470","updateTime":"2017-07-10 09:21:42"},{"currencyF":"USD","currencyF_Name":"美元","currencyT":"CNY","currencyT_Name":"人民币","currencyFD":"1","exchange":"6.8039","result":"6.8039","updateTime":"2017-07-10 09:21:42"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<FlightInfoBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<FlightInfoBean> getResult() {
        return result;
    }

}
