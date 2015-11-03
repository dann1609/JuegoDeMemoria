package com.ingdanielpadilla.juegodememoria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucia on 22/10/2015.
 */
public class ScoreDAO {
    private static final String TAG = "AndroidDataBase";
    private SQLiteDatabase mDatabase;
    private ScoreDBHelper mDbHelper;

    public ScoreDAO(Context context) {
        mDbHelper = new ScoreDBHelper(context);
        try {
            open();
        } catch (Exception e) {
            Log.e(TAG, "SQLException on opening database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws Exception {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public Long addEntry(String s, String s1,Integer s2,String s3){
        Long index;
        ContentValues values= new ContentValues();
        values.put(MainScore.ScoreEntry.COLUMN_NAME_LEVEL,s);
        values.put(MainScore.ScoreEntry.COLUMN_NAME_NAME,s1);
        values.put(MainScore.ScoreEntry.COLUMN_NAME_POINTS,s2);
        values.put(MainScore.ScoreEntry.COLUMN_NAME_APP,s3);

        index= mDatabase.insert(MainScore.ScoreEntry.TABLE_NAME,null,values);

        Log.d(TAG,"addDataEntry returned index "+index);
        return index;
    }

    public List<String> getAllEntries(int lvl){
        Log.d(TAG, "getAllEntries");
        List<String> entryList = new ArrayList<String>();
        String selectQuery="SELECT * FROM "+MainScore.ScoreEntry.TABLE_NAME+" WHERE "+MainScore.ScoreEntry.COLUMN_NAME_LEVEL+"="+lvl+" ORDER BY "+MainScore.ScoreEntry.COLUMN_NAME_POINTS+" ASC LIMIT 3";
        Cursor cursor=mDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                String entry="Aplicacion: "+cursor.getString(3)+"\nName: "+cursor.getString(1)+"\nscore: "+cursor.getString(2);
                entryList.add(entry);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return entryList;
    }

    public void close() {
        mDbHelper.close();
    }
}