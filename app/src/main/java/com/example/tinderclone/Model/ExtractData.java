package com.example.tinderclone.Model;

import android.content.Context;
import android.content.ContextWrapper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExtractData {
    String data = "", seed, version;
    JSONObject jsonObject = null, user, name, location;
    JSONArray result;
    JSONParser parser = new JSONParser();

    public ExtractData(String reader) throws ParseException {
        jsonObject = (JSONObject) parser.parse(reader);
        System.out.println(jsonObject);

        result = (JSONArray) jsonObject.get("results");

        JSONObject temp = (JSONObject) result.get(0);

        seed = (String) temp.get("seed");//Get seed value
        version = (String) temp.get("version"); //Get version value
        user = (JSONObject) temp.get("user");//Get user Json Object
        name = (JSONObject) user.get("name");//Get name Json Object
        location = (JSONObject) user.get("location");//Get location Json Object
    }

    public String upFirstLetter(String s) {
        String firstLetter = s.substring(0, 1);
        String remainingLetter = s.substring(1, s.length());
        firstLetter = firstLetter.toUpperCase();
        s = firstLetter + remainingLetter;
        return s;
    }

    public String correctLink(String s) {
        String firstLink = s.substring(0, 4);
        String remainingLink = s.substring(4, s.length());
        s = firstLink + "s" + remainingLink;
        return s;
    }

    public String getSeed() {
        return seed;
    }

    public String getVersion() {
        return version;
    }

    public String getGender() {
        return (String) user.get("gender");
    }

    public String getTitle() {
        return (String) name.get("title");
    }

    public String getFirst() {
        return (String) name.get("first");
    }

    public String getLast() {
        return (String) name.get("last");
    }

    public String getStreet() {
        return (String) location.get("street");
    }

    public String getCity() {
        return (String) location.get("city");
    }

    public String getState() {
        return (String) location.get("state");
    }

    public String getZip() {
        return (String) location.get("zip");
    }

    public String getEmail() {
        return (String) user.get("email");
    }

    public String getUsername() {
        return (String) user.get("username");
    }

    public String getPassword() {
        return (String) user.get("password");
    }

    public String getSalt() {
        return (String) user.get("salt");
    }

    public String getMd5() {
        return (String) user.get("md5");
    }

    public String getRegistered() {
        return (String) user.get("registered");
    }

    public String getPhone() {
        return (String) user.get("phone");
    }

    public String getPicture() {
        return (String) user.get("picture");
    }

    public String getCell() {
        return (String) user.get("cell");
    }


}
