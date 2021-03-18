package com.example.httputil;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import httpUtil.HttpListener;
import httpUtil.SendRequestForResponse;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity" ;
    String url="";
    Map<String,String> mMap=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMap.put("a","b");
        setContentView(R.layout.activity_main);
        SendRequestForResponse.get(url, new HttpListener() {
            @Override
            public void onSuccess(String response) {
                //UI操作
                Log.d(TAG,response);
            }

            @Override
            public void onFailed(Exception e) {
                Log.d(TAG, "onFailed: "+ e);
            }
        },MainActivity.this);

        SendRequestForResponse.post(url, mMap, new HttpListener() {
            @Override
            public void onSuccess(String response) {
                //UI操作
                Log.d(TAG,response);
            }

            @Override
            public void onFailed(Exception e) {
                Log.d(TAG, "onFailed: "+ e);
            }
        },MainActivity.this);
    }
}