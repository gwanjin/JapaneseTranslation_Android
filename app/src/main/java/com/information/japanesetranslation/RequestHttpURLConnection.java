package com.information.japanesetranslation;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;

public class RequestHttpURLConnection extends AsyncTask<String, Void, String> {
    private final String APP_ID = "";
    static final String GOO_URL = "https://labs.goo.ne.jp/api/hiragana";
    TextView mResultView;

    public RequestHttpURLConnection(TextView resultView) {
        mResultView = resultView;
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url = null;
        String result = null;

        try{
            url = new URL(GOO_URL);
            URLConnection connection =url.openConnection();
            connection.setDoOutput(true);

            //ヘッダ
            connection.setRequestProperty("Content-Type", "application/json");
            //jsonの中身書く
            JSONObject json = new JSONObject();
            json.put("app_id", APP_ID);
            json.put("sentence", strings[0]);
            json.put("output_type", "hiragana");

            OutputStream os = connection.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.print(json.toString()); //データをPOSTする
            ps.close();

            InputStream is = connection.getInputStream(); //結果を取得
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String s;
            StringBuffer sb = new StringBuffer();
            while ((s = reader.readLine()) != null) {
                sb.append(s);
            }

            JSONObject resultObject = new JSONObject(sb.toString());
            result = resultObject.getString("converted");

            reader.close();

        }catch (IOException e) {
            e.printStackTrace();
        } catch ( JSONException e1 ) {

        }

        return (result==null?"":result);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mResultView.setText(s);
    }
}
