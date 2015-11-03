package com.ingdanielpadilla.juegodememoria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lucia on 22/10/2015.
 */
public class ScoreDBHelper extends SQLiteOpenHelper {
    String a;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MainScore.db";

    public ScoreDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTIRES =
            "CREATE TABLE " + MainScore.ScoreEntry.TABLE_NAME + " (" +
                    MainScore.ScoreEntry.COLUMN_NAME_LEVEL + TEXT_TYPE + COMMA_SEP +
                    MainScore.ScoreEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    MainScore.ScoreEntry.COLUMN_NAME_POINTS + INTEGER_TYPE +COMMA_SEP+
                    MainScore.ScoreEntry.COLUMN_NAME_APP + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES=
            "DROP TABLE IF EXISTS "+ MainScore.ScoreEntry.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTIRES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}