package com.tanya.events;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class News extends AppCompatActivity {

    ListView lvNews ;
    ArrayList<NewsItem> newsItemList ;
    NewsAdapter adapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        lvNews = (ListView) findViewById(R.id.listview_news) ;

        newsItemList = new ArrayList<>() ;

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest("https://www.geo.tv/rss/1/0", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Toast.makeText(News.this, "Success", Toast.LENGTH_SHORT);
                Document doc = Jsoup.parse(response);
                Elements itemElemenets = doc.getElementsByTag("item");

                for (int i = 0; i < itemElemenets.size(); i++) {
                    Element item = itemElemenets.get(i);
                    String title = removeCData(item.child(0).text()) ;
                   // String link = removeCData(item.child(1).text()) ;
                    String pubDate = item.child(2).text();
                    String guid = item.child(3).text();
                    String description = item.child(4).text();
                    Document doc2 = Jsoup.parse(description) ;
                    String imageLink = doc2.getElementsByTag("img").first().attr("src") ;
                    String txt = doc2.getElementsByTag("p").text() ;

                    NewsItem news = new NewsItem() ;
                    news.date = pubDate ;
                    news.description = txt ;
                    news.imagePath = imageLink ;
                    news.link = guid ;
                    news.title = title ;
                    newsItemList.add(news) ;


                    Log.i("myTag" ,title) ;
                    //Log.i("myTag" , link) ;
                    Log.i("myTag" , pubDate) ;
                    Log.i("myTag" , guid) ;
                    Log.i("myTag" , description) ;
                    Log.i("myTag" , imageLink) ;
                }

                Log.i("myTag" , "items in news list " + newsItemList.size()) ;
                adapter= new NewsAdapter(News.this , newsItemList);
                lvNews.setAdapter(adapter);
                lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        NewsItem currentNews = newsItemList.get(i) ;
                        Intent intent = new Intent(News.this , NewsDetailActivity.class) ;
                        intent.putExtra("newsItem" ,currentNews) ;
                        startActivity(intent);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(News.this, "Failed ", Toast.LENGTH_SHORT);
            }
        });


        queue.add(request);
    }

        String removeCData(String data)
        {
            data = data.replace("<![CDATA[" , "") ;
            data = data.replace("]]>" , "");
            return data ;
            
        }


}
