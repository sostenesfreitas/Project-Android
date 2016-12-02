package com.example.sample.antriksh.retrofitrxandroidexample.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "pokemondb";
    public static final int DB_VERSION = 1;

    private static MySqlHelper instance;
    private Context ctx;

    private MySqlHelper(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
        this.ctx = ctx;
    }

    public static synchronized MySqlHelper getInstance(Context ctx){
        if(instance == null){
            instance = new MySqlHelper(ctx);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table poke (" + DatabaseConts._ID +
                " text primary key,"+  DatabaseConts.NAME +" text, "+DatabaseConts.BASICATK+" , "+DatabaseConts.CHARGEATK+
                " text, "+ DatabaseConts.CHARGEDAMAGE + " text, "+DatabaseConts.RANKOFF + " text , "+ DatabaseConts.RANKDEF +
                " text, "+ DatabaseConts.TANK + " text , "+ DatabaseConts.GYMOFF + " text , " +DatabaseConts.GYMDEF +
                " text, "+ DatabaseConts.DAMAGE + " text , "+ DatabaseConts.GYNDAMAGE + " text); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
