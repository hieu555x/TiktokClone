package com.example.tinderclone.Model.database;

import android.content.Context;
import android.util.Log;

import com.example.tinderclone.Model.FileData;
import com.example.tinderclone.Model.TinderCloneClient;
import com.example.tinderclone.Model.TinderCloneService;
import com.example.tinderclone.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUser {

    private Context context;

    public GetUser(Context context) {
        this.context = context;
    }

    public void addUserToDatabase() {
        FileData fileData = new FileData();
        TinderCloneService service = TinderCloneClient.getInstance().create(TinderCloneService.class);
        service.getBody().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String data = "";

                DatabaseTemp databaseTemp = new DatabaseTemp(context);

                try {
                    if (response.body() != null) {
                        data = response.body().string();
                        databaseTemp.insertJsonUser(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("", "Call API ERROR");
            }
        });
    }

    public String returnString() {
        return context.getString(R.string.jsonString);
    }
    public String returnString1() {
        return "";
    }

}
