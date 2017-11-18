package com.tanya.events;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Tanya on 11/18/2017.
 */

public class NewsAdapter extends BaseAdapter {

    Context context ;
    ArrayList<NewsItem> newsList ;

    public NewsAdapter(Context context, ArrayList<NewsItem> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public NewsItem getItem(int i) {
        return newsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null)
        {
            view = View.inflate(context , R.layout.news_list_item_layout  ,null) ;

        }
        NewsItem cuurentNews = newsList.get(i) ;
        ImageView ivi = (ImageView) view.findViewById(R.id.imageview_news) ;
        TextView tvTitle = (TextView) view.findViewById(R.id.textView_news_main) ;
        TextView tvDate = (TextView) view.findViewById(R.id.textView_news_date) ;
        TextView tvDes = (TextView) view.findViewById(R.id.textView_news_des) ;

        Picasso.with(context).load(cuurentNews.imagePath).placeholder(R.drawable.placeholder).into(ivi) ;
        tvTitle.setText(cuurentNews.title);
        tvDate.setText(cuurentNews.date);
        tvDes.setText(cuurentNews.description);
        return view;
    }
}
