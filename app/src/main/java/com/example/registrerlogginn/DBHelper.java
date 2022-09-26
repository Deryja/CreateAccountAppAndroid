package com.example.registrerlogginn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper  extends SQLiteOpenHelper {


 /*
 1. Vi legger til extends SQLiteOpenHelper i public classen så holder vi inne på den for å opprette de to metodene,
  Deretter legger vi til public static final for login inn navn, og så holder vi inne igjen på classen for å opprette konsturktør for den, så
  fjerner vi alt bortsett fra context fra den, og endrer på super hvor vi legger til login.db for navn, null for fabrikk og 1 for versjon*/

    /*
    2. Vi endrer det andre sqllitedatabase til navnet MyDB i begge public voidene
     */

    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
//3. Nå legger vi til koden over

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists users");

//3. Nå legger vi til koden over
    }

    //4. Nå lager vi boolean for username og passord for å legge til brukernavn og passord i databasen

    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }


    //5. Nå sjekker vi om brukernavn som skrives inn for logg inn er i databasen, hvis ja return true, hvis nei return false
    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username =?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;

        else return false;

    }

    //6. Nå lager vi det for password også men samtidig med username

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username =? and password=?", new String[]{username, password});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }
}

//Nå er vi klare til å lage Login inn siden! Lag ny hold inne på Activity så kommer Empty activity opp også kaller vi det
//For LoginActivity