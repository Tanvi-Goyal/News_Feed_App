package com.tanya.events;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class NewsDetailActivity extends AppCompatActivity {

    ImageView iv1 ;
    TextView tvTitle ;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        iv1 = (ImageView) findViewById(R.id.imageview_news) ;
        tvTitle = (TextView) findViewById(R.id.textView_news_main) ;
        description = (TextView) findViewById(R.id.textView_news_des) ;
        NewsItem newsItem = (NewsItem) getIntent().getSerializableExtra("newsItem");

        Picasso.with(this).load(newsItem.imagePath).placeholder(R.drawable.placeholder).into(iv1) ;
        tvTitle.setText(newsItem.title);
        description.setText(newsItem.description);

    }
}
