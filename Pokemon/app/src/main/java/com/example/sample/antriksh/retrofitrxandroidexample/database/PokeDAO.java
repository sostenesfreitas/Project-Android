package com.example.sample.antriksh.retrofitrxandroidexample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
            Log.d("Foi Foi", "inserir: Com sucesso");
            db.close();
    }

    public List<Pokemon> getPoke(String parametro){
        List<Pokemon> lista = new ArrayList<Pokemon>();
        String selectQuery =
                "SELECT * FROM poke ";

        SQLiteDatabase banco = mySqlHelper.getWritableDatabase();
        Cursor cursor = banco.rawQuery(selectQuery, null);

        cursor.moveToFirst();

        Pokemon pokemon = cursor.getExtras("poke");

        StringBuilder conversor = new StringBuilder();
        conversor.append(nomeString);
        return conversor.toString();

            List<Carro> lista = new ArrayList<Carro>();

            String[] columns = new String[]{
                    "_id", "nome", "placa", "ano"};
            String[] args = new String[]{nome+"%"};

            db = dbHelper.getWritableDatabase();
            Cursor c = db.query("carros", columns,
                    "nome like ?", args, null, null, "nome");

            c.moveToFirst();
            while(!c.isAfterLast()){
                Carro carro = fillCarro(c);
                lista.add(carro);
                c.moveToNext();
            }
            c.close();
            db.close();
            return lista;
        }

    }
}
