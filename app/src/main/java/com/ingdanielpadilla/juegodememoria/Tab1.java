package com.ingdanielpadilla.juegodememoria;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Lucia on 31/10/2015.
 */
public class Tab1 extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    View rootView;
    ListView mListView;
    Integer base,lvl;
    String level;
    CustomAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }



    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        rootView =inflater.inflate(R.layout.tab, container, false);
        base=getArguments().getInt(ARG_SECTION_NUMBER);
        level=this.getTag();

                TextView text= (TextView)rootView.findViewById(R.id.subtext);
        text.setText(level);

        mListView=(ListView)rootView.findViewById(R.id.list_item);
        switch (level){
            case "FACIL":
                lvl=1;
                break;
            case "MEDIO":
                lvl=2;
                break;
            case "DIFICIL":
                lvl=3;
                break;
            default:
                break;
        }
        if(base==1) {
            adapter = ((HistorialActivity) getActivity()).getAdapter(lvl);
        }else{
            adapter = ((HistorialActivity) getActivity()).getAdapter(lvl+3);
        }
        mListView.setAdapter(adapter);

        return rootView;

    }
}
