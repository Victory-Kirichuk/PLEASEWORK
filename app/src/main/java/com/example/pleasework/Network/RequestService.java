package com.example.pleasework.Network;

import android.os.AsyncTask;

import java.io.IOException;

import retrofit2.Call;

public class RequestService extends AsyncTask<Void, Void, Void> {

    private Call call;
    private Object result;

    @Override
    protected Void doInBackground(Void... voids) {
        if (call == null)
            throw new NullPointerException("");

        try {
            result = call.execute().body();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    public Object getResult() {
        return result;
    }

    public void setCall(Call call) {
        this.call = call;
    }
}
