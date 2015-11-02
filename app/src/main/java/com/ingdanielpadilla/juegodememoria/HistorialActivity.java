package com.ingdanielpadilla.juegodememoria;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class HistorialActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    private static final String TAG = "AndroidDataBase";
    private List<ParseObject> ob;
    ScoreDAO mScoreDAO;
    ProgressDialog pDialog;

    List<String> values1=new ArrayList<String>(),values2=new ArrayList<String>(),values3=new ArrayList<String>();

    Integer[] juegos=new Integer[5];
    Float[] puntaje=new Float[5];
    private static CustomAdapter adapter1,adapter2,adapter3,adapter4,adapter5,adapter6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        Log.d(TAG, "Reading all contacts..");
        mScoreDAO=new ScoreDAO(getApplicationContext());
        List<String> entries1=mScoreDAO.getAllEntries(1);
        List<String> entries2=mScoreDAO.getAllEntries(2);
        List<String> entries3=mScoreDAO.getAllEntries(3);








        adapter1=new CustomAdapter(this,entries1);
        adapter2=new CustomAdapter(this,entries2);
        adapter3=new CustomAdapter(this,entries3);
        adapter4=new CustomAdapter(this,values1);
        adapter5=new CustomAdapter(this,values2);
        adapter6=new CustomAdapter(this,values3);

        new GetData().execute();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HISTORIAL");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    private  class GetData extends AsyncTask<Void,Void,Void> {

        protected void onPreExecuted(){
            super.onPreExecute();
            ProgressDialog pDialog = new ProgressDialog(HistorialActivity.this);
            pDialog.setTitle("Cargando base de datos");
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {


            try{
                ParseQuery<ParseObject> query= new ParseQuery<ParseObject>("memoria");
                ob= query.find();
                values1.clear();
                values2.clear();
                values3.clear();
                for(ParseObject dato : ob){
                    String sw=dato.get("nivel").toString();
                    switch(sw.toUpperCase()){
                        case "FACIL":
                            values1.add("Aplicacion: "+dato.get("idname")+"\nNombre: "+dato.get("name")+"\nscore: "+dato.get("puntos"));
                            break;
                        case "MEDIO":
                            values2.add("Aplicacion: "+dato.get("idname")+"\nNombre: "+dato.get("name")+"\nscore: "+dato.get("puntos"));
                            break;
                        case "DIFICIL":
                            values3.add("Aplicacion: "+dato.get("idname")+"\nNombre: "+dato.get("name")+"\nscore: "+dato.get("puntos"));
                            break;
                        default:
                            break;
                    }

                }
            }catch(ParseException e){
                Log.e("Error", e.getMessage());
                e.printStackTrace();

            }
            return null;
        }

        protected void onPostExecute(Void result){

            adapter4.notifyDataSetChanged();

        }
    }

    public CustomAdapter getAdapter(int num){
        switch(num){
            case 1:
                return adapter1;
            case 2:
                return adapter2;

            case 3:
                return adapter3;

            case 4:
                return adapter4;

            case 5:
                return adapter5;

            case 6:
                return adapter6;

            default:
                return null;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_historial, menu);
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


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "LOCAL";
                case 1:
                    return "GLOBAL";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private FragmentTabHost tabHost;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_historial, container, false);

            tabHost= (FragmentTabHost) rootView.findViewById(android.R.id.tabhost);
            tabHost.setup(getContext(),
                    getChildFragmentManager(),android.R.id.tabcontent);
            tabHost.addTab(tabHost.newTabSpec("FACIL").setIndicator("FACIL"),
                    Tab1.class, getArguments());
            tabHost.addTab(tabHost.newTabSpec("MEDIO").setIndicator("MEDIO"),
                    Tab1.class, getArguments());
            tabHost.addTab(tabHost.newTabSpec("DIFICIL").setIndicator("DIFICIL"),
                    Tab1.class, getArguments());
            return rootView;
        }


    }

}
