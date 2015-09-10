package com.ingdanielpadilla.juegodememoria;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;

public class PrincipalActivity extends AppCompatActivity {

    String[] niveles={"Muy Facil","Facil","Normal","Dificil","Muy Dificil"};
    NumberPicker pickerlvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        pickerlvl= (NumberPicker) findViewById(R.id.pickerlvl);
        pickerlvl.setMaxValue(5);
        pickerlvl.setMinValue(1);
        pickerlvl.setWrapSelectorWheel(false);
        pickerlvl.setDisplayedValues(niveles);
        pickerlvl.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
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

    public void Empezar(View view) {
        pickerlvl.getValue();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Nivel", pickerlvl.getValue());
        Log.d("Desarrollo", "Se Inicio el Nivel: "+pickerlvl.getValue());
        startActivity(intent);
    }
}
