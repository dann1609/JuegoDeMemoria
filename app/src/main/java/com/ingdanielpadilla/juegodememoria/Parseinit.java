package com.ingdanielpadilla.juegodememoria;


import android.app.Application;

import com.parse.Parse;

/**
 * Created by Lucia on 29/10/2015.
 */
public class Parseinit extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "XYbFuSLcnz3Ro5eVn2QJzvdLvmgquoFDArBfUb4N", "4S9QKHzcznA5LtoVVq11dlFE0GAHzacqYnvMCuHi");
    }
}
