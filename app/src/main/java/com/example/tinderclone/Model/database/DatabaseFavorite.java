package com.example.tinderclone.Model.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseFavorite extends SQLiteOpenHelper {

    private static final String TAG = "TinderCloneDatabase";
    private static final String DATABASE_NAME = "TinderCloneDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PRODUCT = "FAVORITE_USER";

    public DatabaseFavorite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);

        onCreate(database);
    }

    public List<String> getAllFavoriteUser() {
        List<String> jsonUser = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT FavoriteUser FROM " + TABLE_PRODUCT, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String temp = cursor.getString(0);

            jsonUser.add(temp);
            cursor.moveToNext();
        }

        cursor.close();

        return jsonUser;
    }

    public void insertFavoriteUser(String jsonString) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("INSERT INTO " + TABLE_PRODUCT + " (FavoriteUser) VALUES ( ? ) ", new String[]{jsonString});
    }

    public void deleteFavoriteUser() {
        SQLiteDatabase database = getWritableDatabase();

        database.execSQL("DELETE FROM " + TABLE_PRODUCT);
    }
}
