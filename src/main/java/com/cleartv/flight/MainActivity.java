package com.cleartv.flight;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity {

    private ListView start_port;
    private ListView end_port;
    private List<AirportBean> startPorts;

    View rootView;

    private boolean isFoucesStart = true;

    String languageCode = "zh-CN";//en-US
    private TextView page;
    private BaseAdapter adapter;
    private BaseAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().hasExtra("languageCode"))
            languageCode = getIntent().getStringExtra("languageCode");
        Configuration configuration = getResources().getConfiguration();
        if(TextUtils.isEmpty(languageCode) || "zh-CN".equals(languageCode)){
            languageCode = "zh-CN";
            configuration.setLocale(Locale.CHINESE);
        }else{
            languageCode = "en-US";
            configuration.setLocale(Locale.ENGLISH);
        }
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());

        setContentView(R.layout.activity_main);
        rootView =((ViewGroup)findViewById(android.R.id.content)).getChildAt(0);
        start_port = findViewById(R.id.start_port);
        end_port = findViewById(R.id.end_port);
        page = findViewById(R.id.page);

        startPorts = new ArrayList<>();
        startPorts.add(new AirportBean("上海","Shanghai","上海虹桥国际机场","Shanghai Hongqiao International Airport","SHA","ZSSS"));
        startPorts.add(new AirportBean("上海","Shanghai","上海浦东国际机场","Shanghai Pudong International Airport","PVG","ZSPD"));
        startPorts.add(new AirportBean("北京","Beijing","北京首都国际机场","Beijing-Capital International Airport","PEK","ZBAA"));
        startPorts.add(new AirportBean("广州","Guangzhou","广州白云国际机场","Guangzhou Baiyun International Airport","CAN","ZSSS"));
        startPorts.add(new AirportBean("深圳","Shenzhen","深圳宝安国际机场","Shenzhen Bao'an International Airport","SZX","ZGSZ"));
        startPorts.add(new AirportBean("武汉","Wuhan","武汉天河国际机场","Wuhan Tianhe International Airport","WUH","ZHHH"));
        startPorts.add(new AirportBean("南京","Shanghai","南京禄口国际机场","Shanghai Hongqiao International Airport","NKG","ZSNJ"));
        startPorts.add(new AirportBean("长沙","Changsha","长沙黄花国际机场","Changsha Huanghua International Airport","CSX","ZGHA"));
        startPorts.add(new AirportBean("成都","Chengdu","成都双流国际机场","Shanghai Hongqiao International Airport","CTU","ZUUU"));
        startPorts.add(new AirportBean("重庆","Chongqin","重庆江北国际机场","Chongqing Jiangbei International Airport","CKG","ZUCK"));
        startPorts.add(new AirportBean("杭州","Hangzhou","杭州萧山国际机场","Hangzhou International Airport","HGH","ZSHC"));
        startPorts.add(new AirportBean("宁波","Ningbo","宁波栎社国际机场","Ningbo Lishe International Airport","NGB","ZSNB"));
        startPorts.add(new AirportBean("厦门","Xiamen","厦门高崎国际机场","Xiamen Gaoqi International Airport","XMN","ZSAM"));
        startPorts.add(new AirportBean("天津","Tianjin","天津滨海国际机场","Tianjin-Binhai International Airport","TSN","ZBTJ"));
        startPorts.add(new AirportBean("石家庄","ShiJiazhuang","石家庄正定国际机场","ShiJiazhuang Zhengding International Airport","SJW","ZBSJ"));
        startPorts.add(new AirportBean("郑州","Zhengzhou","郑州新郑国际机场","Zhengzhou Xinzheng International Airport","CGO","ZHCC"));
        startPorts.add(new AirportBean("济南","Jinan","济南遥墙国际机场","Jinan Yaoqiang International Airport","TNA","ZSJN"));
        startPorts.add(new AirportBean("合肥","Hefei","合肥新桥国际机场","Hefei Xinqiao International Airport","HFE","ZSOF"));
        startPorts.add(new AirportBean("南昌","NanChang","南昌昌北国际机场","Nanchang Changbei International Airport","KHN","ZSCN"));
        startPorts.add(new AirportBean("兰州","Lanzhou","兰州中川国际机场","Lanzhou Zhongchuan International Airport","LHW","ZLLL"));
        startPorts.add(new AirportBean("西宁","Xining","西宁曹家堡机场","Xining caojiapu Airport","XNN","ZLXN"));
        startPorts.add(new AirportBean("西安","Xi'an","西安咸阳国际机场","Xi'an Xianyang International Airport","XIY","ZLXY"));
        startPorts.add(new AirportBean("贵阳","Guiyang","贵阳龙洞堡国际机场","Guiyang Longdongbao International Airport","KWE","ZUGY"));
        startPorts.add(new AirportBean("南宁","NanNing","南宁吴圩国际机场","Nanning Wuxu International Airpor","NNG","ZGNN"));
        startPorts.add(new AirportBean("呼和浩特","Huhehaote","呼和浩特白塔国际机场","Hohhot Baita International Airport","HET","ZBHH"));
        startPorts.add(new AirportBean("沈阳","Shenyang","沈阳桃仙国际机场","Shenyang Taoxian International Airport","SHE","ZYTX"));
        startPorts.add(new AirportBean("哈尔滨","Harbin","哈尔滨太平国际机场","Harbin Taiping International Airport","HRB","ZYHB"));
        startPorts.add(new AirportBean("长春","Changchun","长春龙嘉国际机场","Changchun Longjia International Airport","CGQ","ZYCC"));
        startPorts.add(new AirportBean("太原","Taiyuan","太原武宿国际机场","Taiyuan Wu Su International Airport","TYN","ZBYN"));
        startPorts.add(new AirportBean("乌鲁木齐","Urumchi","乌鲁木齐地窝堡国际机场","Urumchi Diwopu International Airport","URC","ZWWW"));
        startPorts.add(new AirportBean("三亚","Sanya","三亚凤凰国际机场","Sanya Phoenix International Airport","SYX","ZJSY"));
        startPorts.add(new AirportBean("海口","Haikou","海口美兰国际机场","Haikou Meilan International Airport","HAK","ZJHK"));
        startPorts.add(new AirportBean("香港","Hong Kong","香港国际机场","Hong Kong International Airport","HKG","VHHH"));
        startPorts.add(new AirportBean("澳门","Macao","澳门国际机场","Macao International Airport","MFM","VMMC"));
        startPorts.add(new AirportBean("台北","Taibei","台北松山机场","Taipei Songshan Airport","TSA","RCSS"));

        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return startPorts.size();
            }

            @Override
            public AirportBean getItem(int i) {
                return startPorts.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                View v = View.inflate(MainActivity.this, R.layout.air_port_item,null);
                TextView textView = v.findViewById(R.id.air_port_name);
                textView.setText(getItem(i).getAirport().get(languageCode));
                return v;
            }
        };

        adapter2 = new BaseAdapter() {
            @Override
            public int getCount() {
                return startPorts.size();
            }

            @Override
            public AirportBean getItem(int i) {
                return startPorts.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                View v = View.inflate(MainActivity.this, R.layout.air_port_item,null);
                TextView textView = v.findViewById(R.id.air_port_name);
                textView.setText(getItem(i).getAirport().get(languageCode));
                return v;
            }
        };

        AdapterView.OnItemSelectedListener listener =  new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                page.setText(i+1+"/"+startPorts.size());
                view.requestFocus();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

//        start_port.setFocusable(false);
//        end_port.setFocusable(false);
        end_port.setOnItemSelectedListener(listener);
        end_port.setAdapter(adapter2);

        start_port.setOnItemSelectedListener(listener);
        start_port.setAdapter(adapter);




        start_port.requestFocus();
        isFoucesStart = true;

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                onKeyLeft();
//            }
//        },50);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch (event.getKeyCode()){
                case KeyEvent.KEYCODE_DPAD_CENTER:
                case KeyEvent.KEYCODE_ENTER:
                    if(start_port.getSelectedItem() == end_port.getSelectedItem())
                        Toast.makeText(MainActivity.this,"起飞降落不能为同一机场！",Toast.LENGTH_SHORT).show();
                    else{
                        Intent intent = new Intent(this,ResultActivity.class);
                        intent.putExtra("languageCode",languageCode);
                        intent.putExtra("depPort",(AirportBean)start_port.getSelectedItem());
                        intent.putExtra("arrPort",(AirportBean)end_port.getSelectedItem());
                        startActivity(intent);
                    }
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("lpf","keycode:"+ keyCode);
        switch (keyCode){
            case KeyEvent.KEYCODE_DPAD_LEFT:
                onKeyLeft();
                return true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                onKeyRight();
                return true;
            case KeyEvent.KEYCODE_DPAD_DOWN:
            case KeyEvent.KEYCODE_DPAD_UP:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        rootView.setVisibility(View.VISIBLE);
        if(isFoucesStart)
            start_port.requestFocus();
        else
            end_port.requestFocus();
    }

    @Override
    protected void onPause() {
        super.onPause();
        rootView.setVisibility(View.GONE);

    }

    private void onKeyLeft(){
        if(end_port.getSelectedView()!=null){
            end_port.getSelectedView().setBackgroundResource(R.mipmap.air_port_selected);
        }
        isFoucesStart = true;
        start_port.requestFocus();
        adapter.notifyDataSetChanged();
        page.setText(start_port.getSelectedItemPosition()+1+"/"+startPorts.size());
    }

    private void onKeyRight(){
        if(start_port.getSelectedView()!=null){
            start_port.getSelectedView().setBackgroundResource(R.mipmap.air_port_selected);
        }
        isFoucesStart = false;
        end_port.requestFocus();
        adapter2.notifyDataSetChanged();
        page.setText(end_port.getSelectedItemPosition()+1+"/"+startPorts.size());
    }
}
