package com.ingdanielpadilla.juegodememoria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    Integer anterior=0;
    Integer codigo=0;
    Integer puntos=0;
    Integer wait=0;
    Button bi;
    Button bf;
    Button[] b=new Button[17];;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Iniciar();

      /* b1= (Button) findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setText("1");
            }
        });*/
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void VolteaBoton(View view) {

        if (wait==0){
            bi = (Button) view;
            codigo = bi.getId();
            t1.setText(getString(R.string.onplayintext)+puntos);
    }
        if (!codigo.equals(anterior)&& wait==0){
            Integer tag=(Integer)bi.getTag();
            bi.setText(tag.toString());
            if(anterior==0){
            anterior=codigo;
            }else{
                bf=(Button) findViewById(anterior);
                if (tag==(Integer)bf.getTag()){
                    Toast.makeText(this,"Exelente",1500).show();
                    bi.setEnabled(false);
                    bf.setEnabled(false);
                    puntos=puntos+1;

                    t1.setText(getString(R.string.onplayintext)+puntos);
                    if (puntos.equals(8)){
                        t1.setText(getString(R.string.finishtext)+puntos);
                    }

                }else{
                    Toast.makeText(this,"No No No..",1500).show();
                    wait=1;
                    Handler del= new Handler();
                        del.postDelayed(new Runnable() {
                            public void run() {
                                bi.setText("Logo");
                                bf.setText("Logo");
                                wait=0;
                            }
                        }, 1500);


                }
                anterior=0;
            }
        }



    }
    public void Iniciar(){
        wait=1;
        t1=(TextView) findViewById(R.id.t1);

        for (int i=1;i<=16;i++){
            String res="b"+i;
            Integer cod=getResources().getIdentifier(res, "id", getPackageName());
            Log.v("Desarrollo",cod.toString());
            b[i]=(Button) findViewById(cod);
        }


        ArrayList<Integer> number = new ArrayList<Integer>();
        for (int i = 2; i <= 17; ++i) {
            number.add(i/2);
        }
        Collections.shuffle(number);
        for (int i = 1; i <= 16; ++i) {
            b[i].setTag(number.get(i - 1));
            b[i].setText(number.get(i - 1).toString());
        }
        Toast.makeText(this,"Observa los numeros",5000).show();
        Handler del= new Handler();
        del.postDelayed(new Runnable() {
            public void run() {
                for (int i = 1; i <= 16; ++i) {
                    b[i].setText("Logo");
                }
                wait=0;
            }
        }, 5000);

    }

}
