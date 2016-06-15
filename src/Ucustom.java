package com.example.sinramyoun.object;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Ucustom extends ArrayAdapter<Buser> {
    Context context;
    int layoutResourceId;
    ArrayList<Buser> data = new ArrayList<Buser>();
    String ticket="";
    String[] res = new String[100];
    String id, num;


    public Ucustom(Context context, int layoutResourceId,
                             ArrayList<Buser> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UserHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new UserHolder();
            holder.textNum = (TextView) row.findViewById(R.id.index);
            holder.textName = (TextView) row.findViewById(R.id.Rcontent);


            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }
        final Buser user = data.get(position);
        holder.textNum.setText(user.getNum());
        holder.textName.setText(user.getName());
        res[position] = user.getNum();
        return row;

    }


    static class UserHolder {
        TextView textNum;
        TextView textName;

    }

}
