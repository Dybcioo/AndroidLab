package com.example.lab6;

import org.json.JSONException;

public interface MyJsonResponseListener {
    public void onJsonResponseChange(String string) throws JSONException;
}
