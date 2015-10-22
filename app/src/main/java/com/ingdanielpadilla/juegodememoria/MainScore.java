package com.ingdanielpadilla.juegodememoria;

import android.provider.BaseColumns;

/**
 * Created by Lucia on 22/10/2015.
 */
public class MainScore {
    public MainScore(){}

    public static abstract class ScoreEntry implements BaseColumns {
        public static final String TABLE_NAME = "score";
        public static final String COLUMN_NAME_LEVEL = "level";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_POINTS = "score";
    }
}
