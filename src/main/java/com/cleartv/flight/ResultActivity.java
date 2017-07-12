package com.cleartv.flight;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Lee on 2017/7/11.
 */

public class ResultActivity extends Activity {

    private TextView dep_port;
    private TextView arr_port;
    private String languageCode;
    private AirportBean depPort;
    private AirportBean arrPort;
    private ListView lv_content;
    private List<FlightInfoBean> datas;
    private TextView page;

//    private String URL = "http://api.avatardata.cn/FlightDynamic/Airline";
    private String URL = "http://api.avatardata.cn/FlightDynamic/A";
    private String APPKEY = "c79d876ffab34cf1a1794711d3c00171";

    private static Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        languageCode = getIntent().getStringExtra("languageCode");
        depPort = (AirportBean) getIntent().getSerializableExtra("depPort");
        arrPort = (AirportBean) getIntent().getSerializableExtra("arrPort");

        Configuration configuration = getResources().getConfiguration();
        if(TextUtils.isEmpty(languageCode) || "zh-CN".equals(languageCode)){
            languageCode = "zh-CN";
            configuration.setLocale(Locale.CHINESE);
        }else{
            languageCode = "en-US";
            configuration.setLocale(Locale.ENGLISH);
        }
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());

        setContentView(R.layout.activity_result);

        if(arrPort == null || depPort == null)
            return;
        dep_port = findViewById(R.id.dep_port);
        arr_port = findViewById(R.id.arr_port);
        dep_port.setText(depPort.getAirport().get(languageCode));
        arr_port.setText(arrPort.getAirport().get(languageCode));

        datas = new ArrayList<>();
        RequestBody body = new FormBody.Builder()
                .add("from", depPort.getAirport3())
                .add("to", arrPort.getAirport3())
                .add("key", APPKEY)
                .build();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ResultActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    Gson gson = new Gson();
                    JuHeData data = gson.fromJson(response.body().string(),JuHeData.class);
                    if(data.getError_code() == 0 && data.getResult()!=null){
                        datas = data.getResult();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                setData();
                            }
                        });
                    }else{
                        throw new Exception("data.getError_code()");
                    }
                }catch (Exception e){
                    for(int i = 0;i<60;i++)
                        datas.add(new FlightInfoBean("123"+i,"123","123","123","123","123","123"));
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            setData();
                        }
                    });
                }
            }
        });

    }

    private void setData(){

        page = findViewById(R.id.page);
        lv_content = findViewById(R.id.lv_content);

        lv_content.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return datas.size();
            }

            @Override
            public FlightInfoBean getItem(int i) {
                return datas.get(i);
            }

            @Override
            public long getItemId(int i) {
                return datas.size();
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                ViewHolder holder;
                if(view == null){
                    view = View.inflate(ResultActivity.this,R.layout.flight_item,null);
                    holder = new ViewHolder();
                    holder.flight_num = view.findViewById(R.id.flight_num);
                    holder.dep_time = view.findViewById(R.id.dep_time);
                    holder.dexpected = view.findViewById(R.id.dexpected);
                    holder.arr_time = view.findViewById(R.id.arr_time);
                    holder.aexpected = view.findViewById(R.id.aexpected);
                    holder.dep_terminal = view.findViewById(R.id.dep_terminal);
                    holder.arr_terminal = view.findViewById(R.id.arr_terminal);
                    view.setTag(holder);
                }else{
                    holder = (ViewHolder) view.getTag();
                }
                holder.flight_num.setText(getItem(i).getFlightNum());
                holder.dep_time.setText(getItem(i).getDepTime());
                holder.dexpected.setText(getItem(i).getDexpected());
                holder.arr_time.setText(getItem(i).getArrTime());
                holder.aexpected.setText(getItem(i).getAexpected());
                holder.dep_terminal.setText(getItem(i).getDepTerminal());
                holder.arr_terminal.setText(getItem(i).getArrTerminal());
                return view;
            }
        });

        lv_content.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                view.requestFocus();
                page.setText(i+1+"/"+datas.size());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    class ViewHolder{
        public TextView flight_num,dep_time,dexpected,arr_time,aexpected,dep_terminal,arr_terminal;
    }
}
