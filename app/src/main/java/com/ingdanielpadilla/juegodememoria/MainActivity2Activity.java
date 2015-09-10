package com.ingdanielpadilla.juegodememoria;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity2Activity extends ActionBarActivity {

    Integer[] juegos=new Integer[5];
    Float[] puntaje=new Float[5];
    TextView [] disp=new TextView[15];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        for(int i = 1; i <= 5; i++) {
            juegos[i-1] = sp.getInt("juegos"+Integer.toString(i) ,0);
            puntaje[i-1] = sp.getFloat("mejorpuntaje"+Integer.toString(i), 0);
        }
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 2; j++) {
                String res = "disp" + i+j;
                Integer cod = getResources().getIdentifier(res, "id", getPackageName());
                //Log.v("Desarrollo", cod.toString());
                disp[(i-1)*2+j] = (TextView) findViewById(cod);
            }
        }
        for (int i = 1; i <= 5; i++) {
            disp[(i-1)*2+1].setText("Numero de juegos: " + juegos[i-1].toString());
            disp[(i-1)*2+2].setText("Mejor Puntaje: " + String.format("%.0f", puntaje[i-1]));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
