package com.example.tasca2_1;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String concerts[];
    String dates[];
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String[] countryList, String[] flags) {
        this.context = context;
        this.concerts = countryList;
        this.dates = flags;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return concerts.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_list_view, null);
        TextView concert = (TextView) view.findViewById(R.id.textViewItem);
        TextView data = (TextView) view.findViewById(R.id.textViewSubitem);
        concert.setText(concerts[i]);
        data.setText(dates[i]);
        return view;
    }
}