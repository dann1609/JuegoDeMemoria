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
        Parse.initialize(this, "izD9VtSH1ueVfWX0y8Eycy4nW88Caml9LKqtKaAd", "wfxY8I330MmaPPZVr86iUpFohJ6QUyuVdY3jhKwr");
    }
}
