package com.ingdanielpadilla.juegodememoria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Lucia on 22/10/2015.
 */
public class CustomAdapter extends BaseAdapter {

    private static final String TAG="AS_ListView";
    private Context context;
    private List<String> values;

    public CustomAdapter(Context context,List<String> values){
        this.context= context;
        this.values=values;
    }



    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        String s=values.get(position);
        if(view==null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.my_list_item,null);
        }

        TextView f1=(TextView) view.findViewById(R.id.dispres);
        f1.setText(s);
        view.setTag(s);
        return view;
    }
}

