package com.example.sample.antriksh.retrofitrxandroidexample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sample.antriksh.retrofitrxandroidexample.api.PokeId;
import com.example.sample.antriksh.retrofitrxandroidexample.api.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokeDAO {
    MySqlHelper mySqlHelper;

    public PokeDAO(Context ctx){
         mySqlHelper = MySqlHelper.getInstance(ctx);
    }

    public void inserir(Pokemon pokemon){
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = mySqlHelper.getWritableDatabase();

            cv.put(DatabaseConts._ID, pokemon.get_id().getId());
            cv.put(DatabaseConts.NAME,pokemon.getName());
            cv.put(DatabaseConts.BASICATK,pokemon.getBasicAtk());
            cv.put(DatabaseConts.CHARGEATK,pokemon.getChargeAtk());
            cv.put(DatabaseConts.CHARGEDAMAGE,pokemon.getChargeDamage());
            cv.put(DatabaseConts.RANKOFF,pokemon.getRankOff());
            cv.put(DatabaseConts.RANKDEF ,pokemon.getRankDef());
            cv.put(DatabaseConts.TANK,pokemon.getTank());
            cv.put(DatabaseConts.GYMOFF,pokemon.getGymOff());
            cv.put(DatabaseConts.GYMDEF,pokemon.getGymDef());
            cv.put(DatabaseConts.DAMAGE,pokemon.getDamage());
            cv.put(DatabaseConts.GYNDAMAGE,pokemon.getGynDamage());

            db.insert("poke",null,cv);
        Log.d("eu", "inserir: botei mesm");
            db.close();
    }

    public Pokemon getPoke(String nome){
        Pokemon pokemon = new Pokemon();

        SQLiteDatabase banco = mySqlHelper.getWritableDatabase();
        Cursor cursor = banco.rawQuery("SELECT * FROM poke WHERE " +
                DatabaseConts.NAME + " = ? ", new String[]{nome});

        if(cursor.moveToNext()) {
            pokemon = getPokeFromCursor(cursor);
        }
        banco.close();
        return pokemon;
    }

    public List<Pokemon> getPokes(){
        List<Pokemon> lista = new ArrayList<Pokemon>();
        String selectQuery =
                "SELECT * FROM poke ";

        SQLiteDatabase banco = mySqlHelper.getWritableDatabase();
        Cursor cursor = banco.rawQuery(selectQuery, null);

        while(cursor.moveToNext()) {
            lista.add(getPokeFromCursor(cursor));
        }
            banco.close();
            return lista;
        }

    public void delPoke(String name){
        SQLiteDatabase banco = mySqlHelper.getWritableDatabase();
        banco.delete(" poke ", " name = ?",
                new String[]{ name });
        Log.d("apagou mesmo", "delPoke: apaguei doid");
        banco.close();
    }

    private Pokemon getPokeFromCursor(Cursor cursor) {
        Pokemon pokemon = new Pokemon();
        PokeId id = new PokeId();
        if(cursor != null){
            id.setId(cursor.getString(cursor.getColumnIndex(DatabaseConts._ID)));
            pokemon.set_id(id);
            pokemon.setName(cursor.getString(cursor.getColumnIndex(DatabaseConts.NAME)));
            pokemon.setBasicAtk(cursor.getString(cursor.getColumnIndex(DatabaseConts.BASICATK)));
            pokemon.setChargeAtk(cursor.getString(cursor.getColumnIndex(DatabaseConts.CHARGEATK)));
            pokemon.setChargeDamage(cursor.getString(cursor.getColumnIndex(DatabaseConts.CHARGEDAMAGE)));
            pokemon.setBasicAtk(cursor.getString(cursor.getColumnIndex(DatabaseConts.BASICATK)));
            pokemon.setDamage(cursor.getString(cursor.getColumnIndex(DatabaseConts.DAMAGE)));
            pokemon.setGymDef(cursor.getString(cursor.getColumnIndex(DatabaseConts.GYMDEF)));
            pokemon.setGymOff(cursor.getString(cursor.getColumnIndex(DatabaseConts.GYMOFF)));
            pokemon.setGynDamage(cursor.getString(cursor.getColumnIndex(DatabaseConts.GYNDAMAGE)));
            pokemon.setRankDef(cursor.getString(cursor.getColumnIndex(DatabaseConts.RANKDEF)));
            pokemon.setRankOff(cursor.getString(cursor.getColumnIndex(DatabaseConts.RANKOFF)));
            pokemon.setTank(cursor.getString(cursor.getColumnIndex(DatabaseConts.TANK)));
    }
    return pokemon;
}
}
