package httpUtil;

import android.content.Context;
import android.icu.text.StringSearch;

import androidx.annotation.Nullable;

import com.example.httputil.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

public class SendRequestForResponse {

    public static void Request(String url, String method, @Nullable Map<String, String> params, HttpListener listener, Context context) {
        new Thread(
                () -> {
                    HttpURLConnection connection = GetHttpURLConnection.getHttpURLConnection(url);
                    connection.setDoOutput(true);
                    InputStream inputStream = null;
                    OutputStream outputStream = null;
                    try {
                        inputStream = connection.getInputStream();
                        outputStream = connection.getOutputStream();
                        if ("Post".equals(method)) {
                            BuildParams.buildParams(outputStream, params);
                        }
                        connection.connect();
                        int code = connection.getResponseCode();
                        String response = convertResponseToString(inputStream);
                        if (code == 200 && response != null && listener != null) {
                            listener.onSuccess(response);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (listener != null) {
                            listener.onFailed(e);
                        }
                    } finally {
                        try {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            if (connection != null) {
                                connection.disconnect();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
    }


    public static String convertResponseToString(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        String response = sb.toString();
        return response;
    }
}
