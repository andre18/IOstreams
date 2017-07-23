package com.example.andrew.texteditor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.andrew.texteditor.R;

import java.util.List;

/**
 * Created by Andrew on 12.04.2017.
 */

public class AdapterMainActivityList extends BaseAdapter {

    private List<String> fileList;
    private Context context;

    public AdapterMainActivityList(List<String> fileList, Context context) {
        this.fileList = fileList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return fileList.size();
    }

    @Override
    public Object getItem(int position) {
        return fileList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder viewHolder;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.activity_main_item, parent, false);

            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.name.setText(fileList.get(position));

        return v;
    }

    class ViewHolder {
        private TextView name;

        public ViewHolder(View v) {
            this.name = (TextView) v.findViewById(R.id.activity_main_item_file);
        }
    }
}
