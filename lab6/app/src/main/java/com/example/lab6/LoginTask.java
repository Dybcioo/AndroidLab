package com.example.lab6;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LoginTask extends AsyncTask<String,String,String> {
    private MyJsonResponseListener mListener;

    public LoginTask(MyJsonResponseListener myJsonResponseListener) {
        mListener = myJsonResponseListener;
    }

    @Override
    protected String doInBackground(String... params) {
        URL url;
        String response = "";
        try {
            url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            String parsedParams = getRequestData(params);
            writer.write(parsedParams);
            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else {
                response="";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(response);
        return response;
    }
    private String getRequestData(String... params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean isUrl = true;
        for(String param : params){
            if (isUrl) { //pomijamy pierwszy parametr w tablicy bo jest to nasz URL
                        isUrl = false;
            }
            else {
                result.append("&");
                result.append(param);//dodajemy kolejno parametry
            }
        }
        return result.substring(1,result.length());
    }
    @Override
    protected void onPostExecute(String s) {
        try {
            mListener.onJsonResponseChange(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
