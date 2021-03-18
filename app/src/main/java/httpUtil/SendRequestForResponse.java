package httpUtil;

import android.content.Context;
import android.icu.text.StringSearch;

import com.example.httputil.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

public class SendRequestForResponse {
    String response;
    int code;
    Exception e1;
    public static void get(String url, HttpListener listener, Context context){
        SendRequestForResponse sendGetForResponse=new SendRequestForResponse();
        sendGetForResponse.sendGetForResponse(url, listener, context);
    }
    public static void post(String url,Map<String,String> params, HttpListener listener, Context context){
        SendRequestForResponse sendPostForResponse=new SendRequestForResponse();
        sendPostForResponse.sendPostForResponse(url,params, listener, context);
    }
    public void sendGetForResponse(String url, HttpListener listener, Context context){
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream=null;
                HttpURLConnection connection=GetHttpURLConnection.getHttpURLConnection(url);
                try {
                    connection.connect();
                    inputStream=connection.getInputStream();
                    code= connection.getResponseCode();
                    response=convertResponseToString(inputStream);
                    inputStream.close();
                } catch (IOException e) {
                    e1=e;
                    e1.printStackTrace();
                }
            }
        }).start();
        ((MainActivity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(code==200 && response!=null){
                    listener.onSuccess(response);
                }else listener.onFailed(e1);
            }
        });
    }
    public void sendPostForResponse(String url, Map<String,String> params, HttpListener listener, Context context){
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream=null;
                HttpURLConnection connection=GetHttpURLConnection.getHttpURLConnection(url);
                connection.setDoOutput(true);
                try {
                    BuildParams.buildParams(connection.getOutputStream(),params);
                    connection.connect();
                    inputStream=connection.getInputStream();
                    code= connection.getResponseCode();
                    response=convertResponseToString(inputStream);
                    inputStream.close();
                } catch (IOException e) {
                    e1=e;
                    e1.printStackTrace();
                }
            }
        }).start();
        ((MainActivity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(code==200 && response!=null){
                    listener.onSuccess(response);
                }else listener.onFailed(e1);
            }
        });
    }

    public String convertResponseToString(InputStream inputStream) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer sb=new StringBuffer();
        String line ;
        while ((line =reader.readLine())!=null){
            sb.append(line +"\n");
        }
        String response=sb.toString();
        return response;
    }
}
