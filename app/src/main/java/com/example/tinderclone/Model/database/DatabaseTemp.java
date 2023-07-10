package com.example.tinderclone.Model.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseTemp extends SQLiteOpenHelper {
    private static final String TAG = "TinderCloneDatabase";
    private static final String DATABASE_NAME = "TinderCloneDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PRODUCT = "User";
    private static final String TABLE_PRODUCT1 = "FAVORITE_USER";

    public DatabaseTemp(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.i("TAG", "CREATE TABLE");

        String query = "CREATE TABLE " + TABLE_PRODUCT + " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "JsonUser VARCHAR (255) NOT NULL " +
                ")";

        database.execSQL(query);

        String query1 = "CREATE TABLE " + TABLE_PRODUCT1 + " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FavoriteUser VARCHAR (255) NOT NULL " +
                ")";

        database.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);

        onCreate(database);
    }

    public List<String> getAllJsonUser() {
        List<String> jsonUser = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT JsonUser FROM " + TABLE_PRODUCT, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String temp = cursor.getString(0);

            jsonUser.add(temp);
            cursor.moveToNext();
        }

        cursor.close();

        return jsonUser;
    }

    public void insertJsonUser(String jsonString) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("INSERT INTO " + TABLE_PRODUCT + " (JsonUser) VALUES ( ? ) ", new String[]{jsonString});
    }

    public void deleteJsonUser() {
        SQLiteDatabase database = getWritableDatabase();

        database.execSQL("DELETE FROM " + TABLE_PRODUCT);
    }
}
