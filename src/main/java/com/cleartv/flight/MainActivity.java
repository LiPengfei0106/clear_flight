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
        for(int i = 0;i<10;i++){
            startPorts.add(new AirportBean("上海","Shanghai","上海虹桥国际机场"+i,"Shanghai Hongqiao International Airport","SHA","ZSSS"));
            startPorts.add(new AirportBean("广州","Guangzhou","广州白云国际机场"+i,"Guangzhou Baiyun International Airport","CAN","ZSSS"));
        }

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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        start_port.setOnItemSelectedListener(listener);
        start_port.setAdapter(adapter);

        end_port.setOnItemSelectedListener(listener);
        end_port.setAdapter(adapter2);
        start_port.requestFocus();
        isFoucesStart = true;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onKeyLeft();
            }
        },20);
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
        if(end_port.getSelectedView()!=null)
            end_port.getSelectedView().setBackgroundResource(R.mipmap.air_port_selected);
        isFoucesStart = true;
        start_port.requestFocus();
        adapter.notifyDataSetChanged();
        page.setText(start_port.getSelectedItemPosition()+1+"/"+startPorts.size());
    }

    private void onKeyRight(){
        if(start_port.getSelectedView()!=null)
            start_port.getSelectedView().setBackgroundResource(R.mipmap.air_port_selected);
        isFoucesStart = false;
        end_port.requestFocus();
        adapter2.notifyDataSetChanged();
        page.setText(end_port.getSelectedItemPosition()+1+"/"+startPorts.size());
    }
}
