package com.example.tinderclone;


import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tinderclone.Model.ExtractData;
import com.example.tinderclone.Model.FileData;
import com.example.tinderclone.Model.TinderCloneClient;
import com.example.tinderclone.Model.TinderCloneService;
import com.squareup.picasso.Picasso;

import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SwipeAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;

    private ImageButton buttonInfo, buttonDate, buttonLocation, buttonPhone, buttonLock;
    private TextView txtMain, txtHearder, txtJson;
    private CircleImageView imageProfile;

    private FileData fileData = new FileData();

    public SwipeAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public List<String> getListUser() {
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_koloda, parent, false);
        } else {
            view = convertView;
        }

        ExtractData d = null;
        try {
            d = new ExtractData(list.get(position));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //region Anh Xa
        buttonInfo = (ImageButton) view.findViewById(R.id.buttonInfo);
        buttonDate = (ImageButton) view.findViewById(R.id.buttonDate);
        buttonLocation = (ImageButton) view.findViewById(R.id.buttonLocation);
        buttonPhone = (ImageButton) view.findViewById(R.id.buttonPhone);
        buttonLock = (ImageButton) view.findViewById(R.id.buttonLock);
        txtMain = (TextView) view.findViewById(R.id.txtMain);
        txtHearder = (TextView) view.findViewById(R.id.txtHearder);
        imageProfile = (CircleImageView) view.findViewById(R.id.imageProfile);
        //endregion

        //region Khoi dau
        buttonInfo.setColorFilter(Color.rgb(8, 255, 0));
        txtHearder.setText("My name is :");
        txtMain.setText(d.upFirstLetter(d.getFirst()) + " " + d.upFirstLetter(d.getLast()));
        //endregion

        //region Su kien
        suKienButton(buttonInfo,
                view,
                d.upFirstLetter(d.getFirst()) + " " + d.upFirstLetter(d.getLast()),
                "My name is :",
                "Button Info Clicked",
                buttonInfo,
                buttonDate,
                buttonLocation,
                buttonPhone,
                buttonLock);

        suKienButton(buttonDate,
                view,
                d.upFirstLetter(d.getRegistered()),
                "My registered is : ",
                "Button Date Clicked",
                buttonInfo,
                buttonDate,
                buttonLocation,
                buttonPhone,
                buttonLock);

        suKienButton(buttonLocation,
                view,
                d.upFirstLetter(d.getStreet()) + ", " + d.upFirstLetter(d.getCity()) + ", " + d.upFirstLetter(d.getState()),
                "My location is :",
                "Button Location Clicked",
                buttonInfo,
                buttonDate,
                buttonLocation,
                buttonPhone,
                buttonLock);

        suKienButton(buttonPhone,
                view,
                d.getPhone().toString(),
                "My phone is :",
                "Button Phone Clicked",
                buttonInfo,
                buttonDate,
                buttonLocation,
                buttonPhone,
                buttonLock);

        suKienButton(buttonLock,
                view,
                d.getCell(),
                "My cell is :",
                "Button Lock Clicked",
                buttonInfo,
                buttonDate,
                buttonLocation,
                buttonPhone,
                buttonLock);

        String imageUrl = d.correctLink(d.getPicture());
        Picasso.get()
                .load(imageUrl)
                .into(imageProfile);

        Log.e("Picture", d.getPicture());


        return view;
    }

    private void suKienButton(ImageButton button,
                              View view,
                              String mainString,
                              String subString,
                              String comment,
                              ImageButton button1,
                              ImageButton button2,
                              ImageButton button3,
                              ImageButton button4,
                              ImageButton button5) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View views) {
                Log.e("Clicked", comment);
                txtMain = (TextView) view.findViewById(R.id.txtMain);
                txtHearder = (TextView) view.findViewById(R.id.txtHearder);

                txtHearder.setText(subString);
                txtMain.setText(mainString);

                button1.setColorFilter(Color.rgb(0, 0, 0));
                button2.setColorFilter(Color.rgb(0, 0, 0));
                button3.setColorFilter(Color.rgb(0, 0, 0));
                button4.setColorFilter(Color.rgb(0, 0, 0));
                button5.setColorFilter(Color.rgb(0, 0, 0));
                button.setColorFilter(Color.rgb(8, 255, 0));

            }
        });

    }

    public void nextUser() throws IOException {
        FileData fileData = new FileData();
        fileData.ghiFile("TinderClone.txt", "TinderCloneFolder", context, (String) txtJson.getText());
        Log.d("TinderClone.txt", (String) txtJson.getText());
    }
}
