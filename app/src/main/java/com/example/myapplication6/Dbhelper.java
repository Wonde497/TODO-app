package com.example.myapplication6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public  class Dbhelper extends SQLiteOpenHelper {
    public Dbhelper(Context context) {

        super(context, "User_db.sqlite", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE "+"user_table"+" ( "+
                "email TEXT,"+
                "name TEXT,"+
                "password TEXT,"+
                "confirm_password TEXT,"+
                "number int)";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int older_version, int new_version) {
        db.execSQL("drop table if Exists user_table");
        onCreate(db);
    }

    public boolean insertUserData(String name, String email, String password,String confirm_password, String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("name", name);
        values.put("password", password);
        values.put("confirm_password", confirm_password);
        values.put("number", number);
        long res = db.insert("user_table", null, values);
        if (res == -1) {
            return false;
        }
        else {
            return true;
        }
    }


    public Boolean checkEmail(String email){

      SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery("select * from user_table where email =?",new String[]{email});
        if(cursor.getCount()>0) {
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean checkPassword(String password){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery("select * from user_table where password =?",new String[]{password});
        if(cursor.getCount()>0) {
            return true;
        }
        else{
            return false;
        }
    }
    public List<UserModel> getAllUsers() {
        List<UserModel> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from user_table", null);
        if (cursor.moveToFirst()) {
            do {
                UserModel user=new UserModel(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));



                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return userList;
    }
    }


