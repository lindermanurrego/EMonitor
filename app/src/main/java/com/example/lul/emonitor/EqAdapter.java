package com.example.lul.emonitor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 10/11/2017.
 */

public class EqAdapter extends ArrayAdapter<Earthquake>{
    private ArrayList<Earthquake> eqList;
    private int layoutId;
    private Context context;

    public EqAdapter(@NonNull Context context, int resource, @NonNull List<Earthquake> earthQuakes) {
        super(context, resource, earthQuakes);
        this.context = context;
        this.layoutId = resource;
        eqList = new ArrayList<>(earthQuakes);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if ( convertView == null ) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.layoutId,null);
            holder = new ViewHolder();
            holder.magnitudeTextView = convertView.findViewById(R.id.eq_list_item_magnitude);
            holder.placeTextView = convertView.findViewById(R.id.eq_list_item_place);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Earthquake earthquake = eqList.get(position);
        holder.magnitudeTextView.setText(String.valueOf(earthquake.getMagnitude()));
        holder.placeTextView .setText(earthquake.getPlace());

        return convertView;
    }

    private class ViewHolder{
           public   TextView magnitudeTextView;
        public  TextView placeTextView;

    }
}

