package com.example.networktext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private List<Text> text = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetHttpURLConnection();
        Log.d("Main",""+text.size());
//        RecyclerView recyclerView = findViewById(R.id.recycler_main);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        TextAdapter adapter = new TextAdapter(text);
//        recyclerView.setAdapter(adapter);

    }


    public void SetHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection;
                BufferedReader reader;
                try {
                    Log.d("MainA","hhhhh");
                    URL url = new URL("http://gank.io/api/data/Android/10/1");
                    connection =(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);
                    Log.d("MainA","hhhhh_2");
                    if(connection.getResponseCode()!=200){
                        Log.d("MainA",""+connection.getResponseCode());
                    }
                    Log.d("MainA","hhhhh_1");
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine())!= null){
                        response.append(line);
                    }
//                    responseTo = response.toString();
//                    Log.d("MainA",""+responseTo);

                    SetText(response.toString());
                    if(reader != null){
                        reader.close();
                    }
                    if(connection != null)
                        connection.disconnect();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void SetText(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i=0;i<=9;i++){
                    JSONObject object = new JSONObject(response);/*解析大括号中的东西{}*/
                   JSONArray array = object.getJSONArray("results");/*通过关键字得到一个具体项，由于这里的项是一个数组所以用JSONArray*/
                   JSONObject object1 = array.getJSONObject(i);/*这里到达数组内部,再通过数组内部的项数得到内部的{}*/
                   String str = object1.getString("who");/*这里通过关键字直接得到一个字符串*/
                    String str_1 = object1.getString("desc");
                   String str_2 = object1.getString("url");
                    Text aText = new Text(str,"\n       "+str_1,"\n"+str_2);
//                    Log.d("MainA",""+response);
                    Log.d("MainA",""+str);
                    text.add(aText);
                    }
                    RecyclerView recyclerView = findViewById(R.id.recycler_main);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    TextAdapter adapter = new TextAdapter(text);
                    recyclerView.setAdapter(adapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
