package httpUtil;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetHttpURLConnection {
    public static HttpURLConnection getHttpURLConnection(String url){
        HttpURLConnection httpURLConnection=null;
        try {
            URL url1=new URL(url);
            httpURLConnection=(HttpURLConnection) url1.openConnection();
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setReadTimeout(20000);
            //添加Header
            httpURLConnection.setRequestProperty("Connection","Keep-Alive");
            httpURLConnection.setDoInput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }return httpURLConnection;
    }
}
