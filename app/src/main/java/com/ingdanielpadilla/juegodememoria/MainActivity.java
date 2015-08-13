package com.ingdanielpadilla.juegodememoria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {

    Integer anterior=0;
    Integer codigo=0;
    Integer puntos=0;
    Integer wait=0;
    Button b;
    Button bf;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView) findViewById(R.id.t1);
        Button b1=(Button) findViewById(R.id.b1);
        Button b2=(Button) findViewById(R.id.b2);
        Button b3=(Button) findViewById(R.id.b3);
        Button b4=(Button) findViewById(R.id.b4);
        Button b5=(Button) findViewById(R.id.b5);
        Button b6=(Button) findViewById(R.id.b6);
        Button b7=(Button) findViewById(R.id.b7);
        Button b8=(Button) findViewById(R.id.b8);
        Button b9=(Button) findViewById(R.id.b9);
        Button b10=(Button) findViewById(R.id.b10);
        Button b11=(Button) findViewById(R.id.b11);
        Button b12=(Button) findViewById(R.id.b12);
        Button b13=(Button) findViewById(R.id.b13);
        Button b14=(Button) findViewById(R.id.b14);
        Button b15=(Button) findViewById(R.id.b15);
        Button b16=(Button) findViewById(R.id.b16);
        b1.setTag(1);
        b2.setTag(6);
        b3.setTag(7);
        b4.setTag(6);
        b5.setTag(2);
        b6.setTag(5);
        b7.setTag(8);
        b8.setTag(4);
        b9.setTag(5);
        b10.setTag(4);
        b11.setTag(8);
        b12.setTag(3);
        b13.setTag(3);
        b14.setTag(7);
        b15.setTag(2);
        b16.setTag(1);



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
            b = (Button) view;
            codigo = b.getId();
        }
        if (!codigo.equals(anterior)&& wait==0){
            Integer tag=(Integer)b.getTag();
            b.setText(tag.toString());
            if(anterior==0){
            anterior=codigo;
            }else{
                bf=(Button) findViewById(anterior);
                if (tag==(Integer)bf.getTag()){
                    Toast.makeText(this,"Exelente",Toast.LENGTH_SHORT).show();
                    b.setEnabled(false);
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
                                b.setText("Logo");
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
