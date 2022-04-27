package com.example.ubs;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ubs.ui.exchange.ExchangeFragment;

public class exchangeActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    int[] images;
    String[] placeNames;
    String[] placeGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_exchange);
        toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        recyclerView =findViewById(R.id.recyclerview);

        images = new int[] {R.drawable.utae1,R.drawable.utae2,R.drawable.utae3,R.drawable.utae4};

        placeNames = new String[] {"Homecoming Bash","Commencement Ceremony","UTA Rodeo","TEDxUTA event"};

        placeGuide= new String[]{"https://events.uta.edu/event/homecoming_bash",
                "https://www.utacollegepark.com/events/events-calendar/index.php",
                "https://securelb.imodules.com/s/1909/giving/19/form.aspx?sid=1909&gid=2&pgid=1006&cid=2175&authkey=lE%2FT0fb6wuOW9soHgZdrxsr1tcIDNTx6pE1piEHNjRlMUE8yJSKmkA%3D%3D",
                "https://www.uta.edu/news/news-releases/2021/01/04/excel-program"};

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(exchangeActivity.this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ExchangeFragment.MyAdapter myAdapter= new ExchangeFragment.MyAdapter (exchangeActivity.this,images,placeNames,placeGuide);
        recyclerView.setAdapter(myAdapter);
        }
    }


