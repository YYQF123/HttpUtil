package httpUtil;

import android.text.TextUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class BuildParams {
    public static void buildParams(OutputStream outputStream, Map<String,String> params) throws IOException {
        StringBuilder builder=new StringBuilder();
        for (Map.Entry<String,String> entry:params.entrySet()) {
            if (!TextUtils.isEmpty(builder)){
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey(),"UTF-8"));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
        }

        BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        bufferedWriter.write(builder.toString());
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
