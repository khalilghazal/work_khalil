package com.example.khalil.work_khalil;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Investimento_adapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] names;
    private final String[] values;

    static class ViewHolder {
        public TextView text;
        public TextView value;
    }

    public Investimento_adapter(Activity context, String[] names,String [] values) {
        super(context, R.layout.fund_item, names);
        this.context = context;
        this.names = names;
        this.values=values;
        Log.d("test",values.length+names.length+"");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.fund_item, null);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) rowView.findViewById(R.id.textname);
            viewHolder.value = (TextView) rowView.findViewById(R.id.textvalue);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();

        holder.text.setText( names[position]);
        holder.value.setText( values[position]);


        return rowView;
    }

}
