package httpUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

public class BuildParams {
    public static void buildParams(OutputStream outputStream, Map<String,String> params) throws IOException {
        StringBuilder builder=new StringBuilder();
        for (Map.Entry<String,String> entry:params.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
        bufferedWriter.write(builder.toString());

        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
