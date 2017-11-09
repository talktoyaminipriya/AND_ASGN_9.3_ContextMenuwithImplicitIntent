package com.example.priya.contextmenuwithimplicitintent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Priya on 11/9/2017.
 */

public class CustomAdapter extends BaseAdapter{


        private final Context context;
        private LayoutInflater inflater;
        private List<Single> mlist;

        CustomAdapter(Context c, List<Single> list1) {
            this.context = c;
            this.mlist = list1;
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mlist.size();
        }

        @Override
        public Object getItem(int position) {
            return mlist.get(position).getName();
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        /****** Depends upon data size called for each row , Create each ListView row *****/
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.row_view, parent, false);
                holder = new ViewHolder();
                holder.bindView(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.textView1.setText(mlist.get(position).getName());
            holder.textView2.setText(mlist.get(position).getNumber());
            return convertView;
        }

        private class ViewHolder {
            TextView textView1, textView2;

            void bindView(View convertView) {
                textView1 = (TextView) convertView.findViewById(R.id.tvname);
                textView2 = (TextView) convertView.findViewById(R.id.tvphno);
            }
        }
    }

