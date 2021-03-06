package com.ingdanielpadilla.juegodememoria;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    ScoreDAO mScoreDAO;
    String text,USERNAME="DAN";
    Integer lvl,nivel,puntos = 0, anterior = 0, codigo = 0, parejas = 0, wait = 0, start = 0, swdelay = 0, delay = 500, startdelay = 3000,
            tage=0,totheight, totwidth, size, hmargin, wmargin, hnum=0, wnum=0;
    long startTime=0,elapsedTime=0;
    float  maxscore=10000, pluscore , lesscore;
    double ax2,ay2,az2,at;
    Button bi, bf;
    Button[] b=new Button[99];
    boolean[] state=new boolean[99];
    ArrayList<Integer> tag = new ArrayList<Integer>();
    ArrayList<Integer> id = new ArrayList<Integer>();
    TextView t1;
    LinearLayout lheight,lwidth;

    private SensorManager mSensorManager;
    private Sensor mAcelSensor;
    private boolean swstart;
    private Integer ntype1=3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScoreDAO=new ScoreDAO(getApplicationContext());
        Log.d("sql", ScoreDBHelper.SQL_CREATE_ENTIRES);
        Intent intent = getIntent();
        nivel = intent.getIntExtra("Nivel",2);
        switch (nivel) {
            case 1:
                lvl=2;
                break;
            case 2:
                lvl=3;
                break;
            case 3:
                lvl=3;
                break;

        }


        String res = "activity_main" + nivel;
        Integer cod = getResources().getIdentifier(res, "layout", getPackageName());
        setContentView(cod);

        Log.v("Desarrollo f", "Nivel: " + cod.toString());
        Log.v("Desarrollo f", "Nivel: "+R.layout.activity_main2);
        swstart = false;

        Log.v("Desarrollo", "Nivel: " + nivel.toString());

        if (!swstart) {
            startCapture(MainActivity.this, ntype1);
        }

        wait = 1;
        t1 = (TextView) findViewById(R.id.t1);
        final ViewTreeObserver observer = t1.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Cuadrastilizar();
                t1.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }
        });




      /* b1= (Button) findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setText("1");
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!swstart) {
            startCapture(MainActivity.this, ntype1);
        }
    }

    public void onStop() {
        super.onStop();
        stopCapturing();
    }

    public void onPause() {
        super.onPause();
        stopCapturing();
    }
    public void startCapture(Context context,Integer ntype1) {
        swstart = true;

        mSensorManager = (SensorManager) getSystemService(context.SENSOR_SERVICE);

        mAcelSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAcelSensor, ntype1);
    }

    public void stopCapturing() {
        swstart = false;
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this, mAcelSensor);
        } else {
            //Toast.makeText(getApplicationContext(),"mSensorManager null", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        tag.clear();
        for (int i = 1; i <= hnum*wnum; i++){
            state[i]=b[i].isEnabled();
            if((Integer)b[i].getTag()!=null) {
                tag.add((Integer) b[i].getTag());
            }
            //Log.v("Desarrollo", b[i].getTag().toString());
            id.add((Integer)b[i].getId());


        }
        text=(String) t1.getText();
        elapsedTime = System.currentTimeMillis()-startTime;
        Log.v("Desarrollo", Long.toString(elapsedTime));
        savedInstanceState.putLong("elapsedTime", elapsedTime);
        savedInstanceState.putBooleanArray("state", state);
        savedInstanceState.putIntegerArrayList("tag", tag);
        savedInstanceState.putIntegerArrayList("id", id);
        savedInstanceState.putInt("anterior", anterior);
        savedInstanceState.putInt("codigo", codigo);
        savedInstanceState.putInt("parejas", parejas);
        savedInstanceState.putInt("puntos", puntos);
        savedInstanceState.putInt("start", start);
        savedInstanceState.putInt("wait", wait);
        savedInstanceState.putInt("swdelay", swdelay);
        savedInstanceState.putString("text", text);



       /* savedInstanceState.putString("aX", aX.getText().toString());
        savedInstanceState.putString("aY", aY.getText().toString());
        savedInstanceState.putString("aZ", aZ.getText().toString());
*/

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);



        // Restore state members from saved instance
        state=savedInstanceState.getBooleanArray("state");
        tag=savedInstanceState.getIntegerArrayList("tag");
        id=savedInstanceState.getIntegerArrayList("id");
        anterior=savedInstanceState.getInt("anterior", anterior);
        codigo=savedInstanceState.getInt("codigo", codigo);
        parejas=savedInstanceState.getInt("parejas", parejas);
        puntos=savedInstanceState.getInt("puntos", puntos);
        start=savedInstanceState.getInt("start", start);
        wait=savedInstanceState.getInt("wait", wait);
        swdelay=savedInstanceState.getInt("swdelay", swdelay);
        elapsedTime=savedInstanceState.getLong("elapsedTime");
        text=savedInstanceState.getString("text");

        t1 = (TextView) findViewById(R.id.t1);
        final ViewTreeObserver observer= t1.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.v("Desarrollo", "Inicia la reasignacion de los botones");
              for (int i = 1; i <= hnum*wnum; i++){
                    b[i].setEnabled(state[i]);
                  if(tag.size()!=0) {
                      b[i].setTag(tag.get(i - 1));
                  }
                  if((Integer)b[i].getTag()!=null) {
                      Log.v("Desarrollo", b[i].getTag().toString());
                  }
                    if(!b[i].isEnabled()&&((((hnum * wnum)%2)==0)||(i!=(lvl*(lvl+1)/2+1+lvl/2)))){
                        Integer tag;
                        tag = (Integer)b[i].getTag();
                        b[i].setText(tag.toString());
                    }

                    if (codigo.equals(b[i].getId())||anterior.equals(b[i].getId())||(swdelay.equals(1)&& start.equals(0))) {
                        Log.v("Desarrollo", "Se recargo la referencia del boton");


                        tage = (Integer)b[i].getTag();
                        Log.v("Desarrollo dead", Integer.toString(i));
                        Log.v("Desarrollo dead", Integer.toString(tage));
                        b[i].setText(tage.toString());
                        if (codigo.equals(b[i].getId())||anterior.equals(b[i].getId())) {
                            b[i].setSelected(true);
                        }
                    }
                }

                if (swdelay.equals(1)&& start.equals(0)) {
                    Handler del = new Handler();

                    del.postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 1; i <= hnum*wnum; ++i) {
                                b[i].setText("Logo");
                            }

                            start = 1;
                            t1.setText(getString(R.string.onplayintext) + parejas);
                            wait = 0;
                            swdelay=0;


                        }
                    }, startdelay-elapsedTime);
                    startTime = System.currentTimeMillis();
                }
                if(start.equals(1)&&wait.equals(1)){
                    Handler del = new Handler();

                    del.postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 1; i <= hnum*wnum; ++i) {
                                b[i].setText("Logo");
                                b[i].setSelected(false);
                            }

                            t1.setText(getString(R.string.onplayintext) + parejas);
                            wait = 0;
                            codigo = 0;
                            anterior = 0;


                        }
                    }, delay-elapsedTime);
                    startTime = System.currentTimeMillis();
                }


                t1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Log.v("Desarrollo", "Se disabledizo");
            }
        });
        t1.setText(text);
        Log.v("Desarrollo", "Se recargo el texto");


       /* swstart = savedInstanceState.getBoolean("swstart");
        aX.setText(savedInstanceState.getString("aX"));
        */
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

    public void VolteaBoton(View view) {

        if (start == 0) {
            Iniciar(view);
        }
        if (wait == 0) {
            bi = (Button) view;
            codigo = bi.getId();
            Log.v("Desarrollo", "Se obtubo la referencia del boton");
            bi.setSelected(true);

            if (!codigo.equals(anterior)) {
                Log.v("Desarrollo", "es diferente del anterior");
                Integer tag;
                tag = (Integer) bi.getTag();
                bi.setText(Integer.toString(tag));
                if (anterior == 0) {
                    anterior = codigo;
                    Log.v("Desarrollo", "no hubo anterior");
                } else {
                    Log.v("Desarrollo", "Si hubo anterior");
                    bf = (Button) findViewById(anterior);
                    bf.setSelected(true);
                    puntos=puntos+1;
                    if (tag == (Integer) bf.getTag()) {
                        Log.v("Desarrollo", "hay coincidencia");
                        Toast.makeText(this, "Exelente", delay).show();
                        bi.setEnabled(false);
                        bf.setEnabled(false);

                        parejas = parejas + 1;

                        if (parejas.equals((hnum*wnum-(hnum*wnum)%2)/2)) {
                            Log.v("Desarrollo", "se acabo el juego");
                            wait = 1;
                            start = 0;
                            swdelay = 0;
                            t1.setText(getString(R.string.finishtext) + parejas + "\n" + getString(R.string.score) +  puntos);
                            Integer juegos = 0;
                            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            juegos = sp.getInt("juegos"+nivel.toString(), 0);
                            Integer mi = sp.getInt("mejorpuntaje" + nivel.toString(), 0);
                            USERNAME=sp.getString("usuario","Invitado");
                            mScoreDAO.addEntry(nivel.toString(),USERNAME,(int)(puntos),"ingdanielp");
                            new SendData().execute();
                            if (puntos > mi) {
                                mi = puntos;
                            }
                            juegos = juegos + 1;
                            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putInt("juegos"+nivel.toString(), juegos);
                            editor.putInt("mejorpuntaje" + nivel.toString(), mi);
                            editor.commit();
                            Log.d("TAG", juegos.toString());
                            Log.d("TAG", Integer.toString(puntos));
                        }
                        codigo = 0;
                        anterior = 0;
                        bi.setSelected(false);
                        bf.setSelected(false);

                    } else {
                        Log.v("Desarrollo", "no hay coincidencia");
                        wait = 1;
                        Handler del = new Handler();
                        del.postDelayed(new Runnable() {
                            public void run() {
                                if(start.equals(1)) {
                                    bi.setText("Logo");
                                    bf.setText("Logo");
                                    wait = 0;
                                    codigo = 0;
                                    anterior = 0;
                                }
                                bi.setSelected(false);
                                bf.setSelected(false);
                            }
                        }, delay);
                        startTime = System.currentTimeMillis();


                    }

                }
            }
            if(start.equals(1)){
            if (puntos>=0) {
                t1.setText(getString(R.string.onplayintext) + parejas + "\n" + getString(R.string.score) + puntos);
            } else {
                t1.setText(getString(R.string.onplayintext) + parejas + "\n" + getString(R.string.score) + 0);
            }
        }}


    }

    public void Iniciar(View view) {
        if ( wait.equals(1)){
            Log.v("Desarrollo", "Inicio");
            t1 = (TextView) findViewById(R.id.t1);
            t1.setText(getString(R.string.starttext));
            Integer a=t1.getLineHeight();
            Log.v("Desarrollo l", a.toString());
            parejas =0;
            puntos=0;

            for (int i = 1; i <= hnum; i++) {
                for (int j = 1; j <= wnum; j++) {
                    String res = "b" + i+j;
                    Integer cod = getResources().getIdentifier(res, "id", getPackageName());
                    //Log.v("Desarrollo", cod.toString());
                    b[(i-1)*wnum+j] = (Button) findViewById(cod);
                    b[(i-1)*wnum+j].setSelected(false);
                }
            }


            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = 2; i <= hnum*wnum+1; ++i) {
                number.add(i / 2);
            }
            if(((hnum * wnum)%2)==1){
                Log.v("Desarrollo", "Se quito la imparidad: " + number.get((hnum*wnum)-1));
                number.remove((hnum*wnum)-1);
            }
            Collections.shuffle(number);
            if(((hnum * wnum)%2)==1){
                number.add(0);
                Log.v("Desarrollo", "Se agrego la imparidad: " + number.get((hnum * wnum) - 1));
            }
            int j=0;
            for (int i = 1; i <= hnum*wnum; ++i) {
                if((((hnum * wnum)%2)==0)||(i!=(lvl*(lvl+1)/2+1+lvl/2))) {
                    j=j+1;
                    b[i].setTag(number.get(j - 1));
                    b[i].setText(number.get(j - 1).toString());
                    b[i].setEnabled(true);
                }
                else {b[i].setTag(0); b[i].setText("0");}
            }
            Toast.makeText(this, "Observa los numeros", startdelay).show();
            if (swdelay==0) {
                Handler del = new Handler();
                swdelay=1;

                del.postDelayed(new Runnable() {
                    public void run() {
                        for (int i = 1; i <= hnum * wnum; ++i) {
                            b[i].setText("Logo");
                        }

                        start = 1;
                        t1.setText(getString(R.string.onplayintext) + parejas);
                        wait = 0;
                        swdelay=0;
                    }
                }, startdelay);
                startTime = System.currentTimeMillis();
            }

        }
    }

    public void Reiniciar(View view){
        wait = 1;
        start = 0;
        codigo=0;
        anterior=0;
        Iniciar(view);

    }

    public void Cuadrastilizar(){
        Log.v("Desarrollo", "Inicio Cuadrastilizar");
        lheight=(LinearLayout) findViewById(R.id.lheight);
        lwidth=(LinearLayout) findViewById(R.id.lwidth);
        totheight=lheight.getHeight();
        totwidth=lwidth.getWidth();
        hnum=lheight.getChildCount();
        wnum=lwidth.getChildCount();
        Log.v("Desarrollo height", totheight.toString());
        Log.v("Desarrollo width", totwidth.toString());
        Log.v("Desarrollo num height", hnum.toString());
        Log.v("Desarrollo num width", wnum.toString());
        ArrayList<Integer> sizes=new ArrayList<Integer>();
        sizes.add(totheight/hnum);
        sizes.add(totwidth/wnum);
        size= Collections.min(sizes);
        hmargin=(totheight-hnum*size)/(2*hnum);
        wmargin=(totwidth-wnum*size)/(2*wnum);
        Log.v("Desarrollo size", size.toString());
        Log.v("Desarrollo hmargin", hmargin.toString());
        Log.v("Desarrollo wmargin", wmargin.toString());

        for (int i = 1; i <= hnum; i++) {
            for (int j = 1; j <= wnum; j++) {
                String res = "b" + i+j;
                Integer cod = getResources().getIdentifier(res, "id", getPackageName());
                //Log.v("Desarrollo", res);
                b[(i-1)*wnum+j] = (Button) findViewById(cod);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
                params.setMargins(wmargin, hmargin, wmargin, hmargin);
                b[(i-1)*wnum+j].setLayoutParams(params);
            }
        }
        pluscore=2*maxscore/(hnum*wnum-(hnum*wnum)%2);
        lesscore=2*pluscore/(hnum*wnum-(hnum*wnum)%2);
        Log.v("Desarrollo", "Finalizo Cuadrastilizar");
    }

    public void AbrirHistorial(View view) {
        Intent intent = new Intent(this, HistorialActivity.class);
        intent.putExtra("hola", "hola");
        Log.d("Desarrollo", "Se abrio el historial");
        startActivity(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {



        ax2= Math.pow(event.values[0],2);
        ay2= Math.pow(event.values[1],2);
        az2= Math.pow(event.values[2],2);
        at=Math.sqrt(ax2 + ay2 + az2);
        Log.v("Desarrollo", Double.toString(at));
        if(at>=20){
            Reiniciar(findViewById(R.id.t1));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    private class SendData extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            String tnivel;

            switch(nivel){

                case 1:
                    tnivel="Facil";
                    break;
                case 2:
                    tnivel="Medio";
                    break;
                case 3:
                    tnivel="Dificil";
                    break;
                default:
                    tnivel="";
                    break;
            }
            ParseObject testObject = new ParseObject("memoria");
            testObject.put("idname","ingdanielp");
            testObject.put("name",USERNAME);
            testObject.put("nivel", tnivel);
            testObject.put("puntos", Integer.toString(puntos));
            testObject.saveInBackground();
            return null;
        }
    }
}
