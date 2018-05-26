package com.someusefullApps.jobwebsites;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<SingleWebsite> singleWebsites;
    private RecyclerView recyclerView;

    //------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        createlistofWebsites();
        setAdapter();
    }

    //---------------------------------------------------------------
    private void createlistofWebsites() {
        singleWebsites = new ArrayList<>();

//website 1
        singleWebsites.add(new SingleWebsite("Daraz", "https://www.daraz.pk/"));
//website 2
        singleWebsites.add(new SingleWebsite("career jet", "https://www.careerjet.ae/"));

//website 3
        singleWebsites.add(new SingleWebsite("Bayt", "https://www.bayt.com/en/pakistan/"));
//website 4
        singleWebsites.add(new SingleWebsite("Daraz", "https://www.daraz.pk/"));

//website 5
        singleWebsites.add(new SingleWebsite("Bayt", "https://www.bayt.com/en/pakistan/"));
//website 6
        singleWebsites.add(new SingleWebsite("Daraz", "https://www.daraz.pk/"));

//website 7
        singleWebsites.add(new SingleWebsite("Bayt", "https://www.bayt.com/en/pakistan/"));
//website 8
        singleWebsites.add(new SingleWebsite("career jet", "https://www.careerjet.ae/"));

    }

    //-----------------------------------------------------
    private void setAdapter() {
        CustomAdaptor adapter = new CustomAdaptor(singleWebsites, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                String urlAsString = singleWebsites.get(position).getmUrl();
                openWebPage(urlAsString);
            }
        });
        recyclerView.setAdapter(adapter);
    }
    //-------------------------------------------------

    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    //---------------------------------------------------------

}
