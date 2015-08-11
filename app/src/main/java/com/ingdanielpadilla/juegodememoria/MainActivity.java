package com.ingdanielpadilla.juegodememoria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    Integer anterior=0;
    Integer puntos=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anterior=0;
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


        Button b=(Button)view;
        Integer codigo=b.getId();
        Integer tag=(Integer)b.getTag();
        b.setText(tag.toString());
        if(anterior==0){
            anterior=codigo;
        }else{
            Button bf=(Button) findViewById(anterior);
            if (tag==(Integer)bf.getTag()){
                Toast.makeText(this,"Exelente",Toast.LENGTH_SHORT).show();
                b.setEnabled(false);
                bf.setEnabled(false);
                puntos=puntos+1;
                TextView t1=(TextView) findViewById(R.id.t1);
                t1.setText("Jugando Puntos:"+puntos);

            }else{
                try {
                    Thread.sleep(2000);
                    b.setText("Logo");
                    bf.setText("Logo");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            anterior=0;
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
