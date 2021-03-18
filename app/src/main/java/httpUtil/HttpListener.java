package httpUtil;

public interface HttpListener {
    public void onSuccess(String response);
    public void onFailed(Exception e);
}
