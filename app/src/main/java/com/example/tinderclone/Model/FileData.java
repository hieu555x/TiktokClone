package com.example.tinderclone.Model;

import android.content.Context;
import android.content.ContextWrapper;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileData {

    public void ghiFile(String fileName, String filePath, Context context, String data) throws IOException {
        ContextWrapper contextWrapper = new ContextWrapper(context);

        File directory = contextWrapper.getDir(filePath, Context.MODE_PRIVATE);

        File myTempUser = new File(directory, fileName);

        FileOutputStream fileOutputStream = new FileOutputStream(myTempUser);
        fileOutputStream.write(data.getBytes());
        fileOutputStream.close();
    }

    public String docFile(String fileName, String filePath, Context context) throws IOException {
        ContextWrapper contextWrapper = new ContextWrapper(context);

        File directory = contextWrapper.getDir(filePath, Context.MODE_PRIVATE);

        File myTempUser = new File(directory, fileName);

        FileInputStream fileInputStream = new FileInputStream(myTempUser);
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
        StringBuilder data = new StringBuilder();
        String strLine;
        while ((strLine = bufferedReader.readLine()) != null) {
            data.append(strLine);
        }

        dataInputStream.close();

        return data.toString();
    }

    public void xoaFile(String fileName, String filePath, Context context) {
        ContextWrapper contextWrapper = new ContextWrapper(context);

        File directory = contextWrapper.getDir(filePath, Context.MODE_PRIVATE);

        File myTempUser = new File(directory, fileName);

        myTempUser.delete();
    }

    public boolean checkExit(String fileName, String filePath, Context context) {
        ContextWrapper contextWrapper = new ContextWrapper(context);

        File directory = contextWrapper.getDir(filePath, Context.MODE_PRIVATE);

        File myTempUser = new File(directory, fileName);

        return myTempUser.exists();
    }

    public void createFile(String fileName, String filePath, Context context) {
        ContextWrapper contextWrapper = new ContextWrapper(context);

        File directory = contextWrapper.getDir(filePath, Context.MODE_PRIVATE);

        File myTempUser = new File(directory, fileName);

        myTempUser.mkdir();
    }
}
