package great.android.cmu.ubiapp.metrics;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import okhttp3.Response;



public class PostMetricsTask extends AsyncTask<String, Void, Boolean> {

    public PostMetricsTask(Context context){
        this.context = context;
    }

    private Context context;
    public static final MediaType FORM_DATA_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    public static final String URL = "https://docs.google.com/forms/d/e/1FAIpQLSe9kYO3qQRmt-EgNCk5tHOv_p80Ta9cpfF0pHxZODWtdIotPg/formResponse";
    public static final String WAT_KEY = "entry.1011940443";
    public static final String TA_KEY = "entry.527634828";
    public static final String RULES_EVAL_KEY = "entry.1685283592";

    @Override
    protected Boolean doInBackground(String... metrics) {
        Boolean result = true;
        String wat = metrics[0];
        String ta = metrics[1];
        String rulesEval = metrics[2];
        String postBody = "";

        try {
            //all values must be URL encoded to make sure that special characters like & | ",etc. do not cause problems
            postBody = WAT_KEY + "=" + URLEncoder.encode(wat,"UTF-8") +
                    "&" + TA_KEY + "=" + URLEncoder.encode(ta,"UTF-8") +
                    "&" + RULES_EVAL_KEY + "=" + URLEncoder.encode(rulesEval,"UTF-8");
        } catch (UnsupportedEncodingException ex) {
            result = false;
        }

        try{
            //Create OkHttpClient for sending request
            OkHttpClient client = new OkHttpClient();
            //Create the request body with the help of Media Type
            RequestBody body = RequestBody.create(FORM_DATA_TYPE, postBody);
            Request request = new Request.Builder().url(URL).post(body).build();
            //Send the request
            Response response = client.newCall(request).execute();
        }catch (IOException exception){
            result = false;
        }


        return result;
    }

    @Override
    protected void onPostExecute(Boolean result){
        //Toast.makeText(this.context, "Result: " + result, Toast.LENGTH_LONG).show();
    }
}
