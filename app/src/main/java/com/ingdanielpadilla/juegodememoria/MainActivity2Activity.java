package com.ingdanielpadilla.juegodememoria;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_1:
                Log.d("Desarrollo", "op1");
                AlertDialog.Builder builder1=new AlertDialog.Builder(this);
                builder1.setMessage("El juego se basa en ir destapando las cartas para encontrar los pares. Entre menos intentos mayor puntaje.")
                        .setTitle("Como jugar");
                AlertDialog dialog1=builder1.create();
                dialog1.show();
                return true;
            case R.id.action_2:
                Log.d("Desarrollo", "op2");
                AlertDialog.Builder builder2=new AlertDialog.Builder(this);
                builder2.setMessage("Este juego fue creado por el ingeniero Daniel Jose Padilla")
                        .setTitle("Acerca de");
                AlertDialog dialog2=builder2.create();
                dialog2.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
