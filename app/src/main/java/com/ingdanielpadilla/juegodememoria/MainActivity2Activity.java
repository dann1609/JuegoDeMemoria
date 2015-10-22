package com.ingdanielpadilla.juegodememoria;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class MainActivity2Activity extends ActionBarActivity {

    private ListView mListView1,mListView2,mListView3,mListView4,mListView5;
    private static final String TAG = "AndroidDataBase";
    ScoreDAO mScoreDAO;

    Integer[] juegos=new Integer[5];
    Float[] puntaje=new Float[5];
    TextView [] disp=new TextView[15];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        Log.d(TAG, "Reading all contacts..");
        mScoreDAO=new ScoreDAO(getApplicationContext());
        List<String> entries1=mScoreDAO.getAllEntries(1);
        List<String> entries2=mScoreDAO.getAllEntries(2);
        List<String> entries3=mScoreDAO.getAllEntries(3);
        List<String> entries4=mScoreDAO.getAllEntries(4);
        List<String> entries5=mScoreDAO.getAllEntries(5);


        mListView1=(ListView)findViewById(R.id.list_item1);
        mListView2=(ListView)findViewById(R.id.list_item2);
        mListView3=(ListView)findViewById(R.id.list_item3);
        mListView4=(ListView)findViewById(R.id.list_item4);
        mListView5=(ListView)findViewById(R.id.list_item5);
        CustomAdapter adapter1=new CustomAdapter(this,entries1);
        CustomAdapter adapter2=new CustomAdapter(this,entries2);
        CustomAdapter adapter3=new CustomAdapter(this,entries3);
        CustomAdapter adapter4=new CustomAdapter(this,entries4);
        CustomAdapter adapter5=new CustomAdapter(this,entries5);
        mListView1.setAdapter(adapter1);
        mListView2.setAdapter(adapter2);
        mListView3.setAdapter(adapter3);
        mListView4.setAdapter(adapter4);
        mListView5.setAdapter(adapter5);


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
