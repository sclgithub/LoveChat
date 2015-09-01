package com.techscl.lovechat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.techscl.lovechat.R;
import com.techscl.lovechat.base.News;

import java.util.List;

/**
 * Created by 宋春麟 on 15/7/30.
 */
public class RelatedListAdapter extends BaseAdapter {
    private Context context;
    private List<News> data;

    public RelatedListAdapter(Context context, List<News> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        }
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        if (data != null && data.size() > 0) {
            return data.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.relate_list_description, null);
            viewHolder = new ViewHolder();
            viewHolder.relateTitle= (TextView) view.findViewById(R.id.relateTitle);
            viewHolder.relateDate= (TextView) view.findViewById(R.id.relateDate);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (data != null && data.size() > 0) {
            News news = data.get(i);
            viewHolder.relateTitle.setText(news.getNewstitle());
            viewHolder.relateDate.setText(news.getPostdate());
        }
        return view;
    }

    public class ViewHolder {

        TextView relateTitle,relateDate;
    }
}
