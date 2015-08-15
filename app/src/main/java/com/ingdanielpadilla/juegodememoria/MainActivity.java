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

        t1=(TextView) findViewById(R.id.t1);

        for (int i=1;i<=16;i++){
            String res="b"+i;
            Integer cod=getResources().getIdentifier(res, "id", getPackageName());
            Log.v("Desarrollo",cod.toString());
            b[i]=(Button) findViewById(cod);
        }


        ArrayList<Integer> number = new ArrayList<Integer>();
        for (int i = 1; i <= 16; ++i) {
            number.add(i/2);
            b[i].setTag(1);
        }
        Collections.shuffle(number);
        for (int i = 1; i <= 16; ++i) {
            b[i].setTag(number.get(i-1));
        }




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
        }
        if (!codigo.equals(anterior)&& wait==0){
            Integer tag=(Integer)bi.getTag();
            bi.setText(tag.toString());
            if(anterior==0){
            anterior=codigo;
            }else{
                bf=(Button) findViewById(anterior);
                if (tag==(Integer)bf.getTag()){
                    Toast.makeText(this,"Exelente",Toast.LENGTH_SHORT).show();
                    bi.setEnabled(false);
                    bf.setEnabled(false);
                    puntos=puntos+1;

                    t1.setText("Jugando Parejas encontradas:"+puntos);

                }else{
                    Toast.makeText(this,"No No No..",Toast.LENGTH_SHORT).show();
                    t1.setText(getString(R.string.onplayintext)+puntos);
                    wait=1;
                    Handler del= new Handler();
                        del.postDelayed(new Runnable() {
                            public void run() {
                                bi.setText("Logo");
                                bf.setText("Logo");
                                wait=0;
                            }
                        }, 2000);


                }
                anterior=0;
            }
        }



    }
    /*public void AsignaNumero{
        for (int i = 1; i < 16; i++) {
            Vector cartas;
            cartas = Vector(new);
            cartas(1)=findViewById(R.id.b"1");

        }
    }*/
}
