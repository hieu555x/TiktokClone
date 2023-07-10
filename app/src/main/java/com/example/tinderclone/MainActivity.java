package com.example.tinderclone;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.os.Bundle;


import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tinderclone.Model.TinderCloneService;
import com.example.tinderclone.Model.database.DatabaseFavorite;
import com.example.tinderclone.Model.database.DatabaseTemp;
import com.example.tinderclone.Model.database.GetUser;
import com.yalantis.library.Koloda;
import com.yalantis.library.KolodaListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> dataUser = new ArrayList<>();
    private List<String> dataFavorite = new ArrayList<>();

    private long pressedTime;

    private SwipeAdapter adapter;
    private FavoriteAdapter adapterFavorite;

    Koloda koloda, kolodaFavorite;

    private ImageView rightButton, leftButton;

    private TextView yeuThich, trangChu;

    private DatabaseFavorite databaseFavorite;
    private DatabaseTemp databaseTemp;

    private GetUser getUser;

    private Button lamMoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            if (getSupportActionBar().isShowing()) {
                getSupportActionBar().hide();
            }
        } catch (Exception ignored) {

        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();

        databaseTemp = new DatabaseTemp(getApplicationContext());
        databaseFavorite = new DatabaseFavorite(getApplicationContext());

        //Gọi retrofit đưa dữ liệu từ api về
        getUser = new GetUser(getApplicationContext());
        for (int i = 0; i < 100; i++) {
            getUser.addUserToDatabase();
        }

        if (databaseTemp.getAllJsonUser().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Nhấp nút làm mới ở phía dưới để cập nhật danh sách", Toast.LENGTH_SHORT).show();
        } else {
            lamMoi.setVisibility(View.GONE);
            dataUser.addAll(databaseTemp.getAllJsonUser());
        }

        //Thêm dữ liệu vừa đưa về vào mảng
//        dataUser.addAll(databaseTemp.getAllJsonUser());

        Log.d("dataUser", String.valueOf(databaseTemp.getAllJsonUser().size()));
        Log.d("dataUser", String.valueOf(dataUser.size()));
        Log.d("String", String.valueOf(getUser.returnString().split("%nothing%").length));

        databaseTemp.deleteJsonUser();

        try {
            dataFavorite.add(0, databaseFavorite.getAllFavoriteUser().get(0));
        } catch (Exception ex) {

        }

        for (int i = 1; i <= databaseFavorite.getAllFavoriteUser().size(); i++) {
            dataFavorite.add(i, databaseFavorite.getAllFavoriteUser().get(i - 1));
        }

        //Khai báo và đưa dữ liệu lên giao diện
        adapter = new SwipeAdapter(getApplicationContext(), dataUser);
        koloda = findViewById(R.id.koloda);
        koloda.setAdapter(adapter);

        adapterFavorite = new FavoriteAdapter(getApplicationContext(), dataFavorite);
        kolodaFavorite = findViewById(R.id.koloda_favorite);
        kolodaFavorite.setAdapter(adapterFavorite);
        kolodaFavorite.setVisibility(View.GONE);

        event();
    }

    private void mapping() {
        rightButton = (ImageView) findViewById(R.id.rightButton);
        leftButton = (ImageView) findViewById(R.id.leftButton);
        yeuThich = (TextView) findViewById(R.id.txtFavorite);
        trangChu = (TextView) findViewById(R.id.txtHome);
        yeuThich = (TextView) findViewById(R.id.txtFavorite);
        trangChu = (TextView) findViewById(R.id.txtHome);
        lamMoi = (Button) findViewById(R.id.btnLamMoi);
    }

    @Override
    public void onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            databaseTemp.deleteJsonUser();
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Nhấn nút trở về một lần nữa để thoát ứng dụng", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

    private void event() {
        adapter = new SwipeAdapter(getApplicationContext(), dataUser);
        koloda = findViewById(R.id.koloda);
        koloda.setAdapter(adapter);

        yeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (databaseFavorite.getAllFavoriteUser().size() > 0) {

                    Log.d("favorite", String.valueOf(databaseFavorite.getAllFavoriteUser().size()));

                    try {
                        if (dataFavorite.size() <= 1) {
                            dataFavorite.add(0, databaseFavorite.getAllFavoriteUser().get(0));
                        }
                    } catch (Exception ex) {

                    }

                    adapterFavorite = new FavoriteAdapter(getApplicationContext(), dataFavorite);
                    kolodaFavorite = findViewById(R.id.koloda_favorite);
                    kolodaFavorite.setAdapter(adapterFavorite);
                    kolodaFavorite.reloadAdapterData();

                    kolodaFavorite.setVisibility(View.VISIBLE);
                    koloda.setVisibility(View.GONE);

                    rightButton.setVisibility(View.GONE);
                    leftButton.setVisibility(View.GONE);
                    lamMoi.setVisibility(View.GONE);

                    kolodaFavorite.setKolodaListener(new KolodaListener() {
                        @Override
                        public void onNewTopCard(int i) {

                        }

                        @Override
                        public void onCardDrag(int i, @NonNull View view, float v) {

                        }

                        @Override
                        public void onCardSwipedLeft(int i) {

                        }

                        @Override
                        public void onCardSwipedRight(int i) {

                        }

                        @Override
                        public void onClickRight(int i) {

                        }

                        @Override
                        public void onClickLeft(int i) {

                        }

                        @Override
                        public void onCardSingleTap(int i) {

                        }

                        @Override
                        public void onCardDoubleTap(int i) {

                        }

                        @Override
                        public void onCardLongPress(int i) {

                        }

                        @Override
                        public void onEmptyDeck() {
                            Toast.makeText(getApplicationContext(), "Bạn đã đến cuối danh sách, tải lại danh sách yêu thích của bạn", Toast.LENGTH_SHORT).show();
                            kolodaFavorite.reloadAdapterData();
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Bạn chưa thêm người yêu thích nào", Toast.LENGTH_SHORT).show();
                }
            }
        });

        trangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    kolodaFavorite.setVisibility(View.GONE);
                    koloda.setVisibility(View.VISIBLE);
                    rightButton.setVisibility(View.VISIBLE);
                    leftButton.setVisibility(View.VISIBLE);
                    if (databaseTemp.getAllJsonUser().isEmpty()) {
                        lamMoi.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "Nhấp nút làm mới ở phía dưới để cập nhật danh sách", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {

                }
            }
        });

        lamMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUser = new GetUser(getApplicationContext());
                for (int i = 0; i < 100; i++) {
                    getUser.addUserToDatabase();
                }

                if (databaseTemp.getAllJsonUser().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nhấp nút làm mới ở phía dưới để cập nhật danh sách", Toast.LENGTH_SHORT).show();
                } else {
                    lamMoi.setVisibility(View.GONE);
                    dataUser.addAll(databaseTemp.getAllJsonUser());
                }

                adapter = new SwipeAdapter(getApplicationContext(), dataUser);
                koloda = findViewById(R.id.koloda);
                koloda.setAdapter(adapter);
            }
        });

//        rightButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                koloda.onButtonClick(true);
//            }
//        });

        koloda.setKolodaListener(new KolodaListener() {
            @Override
            public void onNewTopCard(int i) {

            }

            @Override
            public void onCardDrag(int i, @NonNull View view, float v) {

            }

            @Override
            public void onCardSwipedLeft(int i) {
                Log.d("number", String.valueOf(i + 1));
            }

            @Override
            public void onCardSwipedRight(int i) {
                Log.d("number", String.valueOf(i + 1));

                databaseFavorite.insertFavoriteUser(dataUser.get(i + 1));
                dataFavorite.add(dataUser.get(i + 1));
            }

            @Override
            public void onClickRight(int i) {

            }

            @Override
            public void onClickLeft(int i) {

            }

            @Override
            public void onCardSingleTap(int i) {

            }

            @Override
            public void onCardDoubleTap(int i) {

            }

            @Override
            public void onCardLongPress(int i) {

            }

            @Override
            public void onEmptyDeck() {

            }
        });
    }
}