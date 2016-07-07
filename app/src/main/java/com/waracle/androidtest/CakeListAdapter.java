package com.waracle.androidtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CakeListAdapter extends BaseAdapter {

    private final Context context;
    // Can you think of a better way to represent these items???
    private JSONArray mItems;
    private ImageLoader mImageLoader;

    public CakeListAdapter(Context context, JSONArray items) {
        this.context = context;
        mItems = items;
        mImageLoader = new ImageLoader();
    }

    @Override
    public int getCount() {
        return mItems.length();
    }

    @Override
    public Object getItem(int position) {
        try {
            return mItems.getJSONObject(position);
        } catch (JSONException e) {
            Log.e("", e.getMessage());
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        try {
            JSONObject object = (JSONObject) getItem(position);
            viewHolder.title.setText(object.getString("title"));
            viewHolder.desc.setText(object.getString("desc"));
            mImageLoader.load(object.getString("image"), viewHolder.image);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }

    private class ViewHolder {
        TextView title;
        TextView desc;
        ImageView image;

        public ViewHolder(View view) {
            this.title = (TextView) view.findViewById(R.id.title);
            this.desc = (TextView) view.findViewById(R.id.desc);
            this.image = (ImageView) view.findViewById(R.id.image);
        }
    }
}