package com.example.pleasework.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pleasework.Adapter.EpisodesRecycleViewAdapter;
import com.example.pleasework.Entity.Episodes;
import com.example.pleasework.Entity.Series;
import com.example.pleasework.Network.Authentication;
import com.example.pleasework.Network.NetworkService;
import com.example.pleasework.Network.RequestService;


import com.example.pleasework.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;

public class SeriesActivity extends AppCompatActivity {

    public static final String SERIES_ID = "series_id";

    private Boolean isInNotification;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Call<Series> call = NetworkService.getInstance().getJSONApi().getById(getIntent().getIntExtra(SERIES_ID, 0));
        RequestService requestService = new RequestService();
        requestService.setCall(call);
        requestService.execute();
        while (requestService.getResult() == null) {
        }

        final Series series = (Series) requestService.getResult();

        ImageView seriesImage = (ImageView) findViewById(R.id.seriesImage);
        TextView seriesName = (TextView) findViewById(R.id.seriesName);
        TextView seriesGenre = (TextView) findViewById(R.id.seriesGenre);
        TextView seriesDate = (TextView) findViewById(R.id.seriesDate);
        TextView seriesDescription = (TextView) findViewById(R.id.seriesDescription);
        TextView seriesSeasons = (TextView) findViewById(R.id.seriesSeason);
        TextView seriesProducers = (TextView) findViewById(R.id.seriesProducers);
        TextView seriesActors = (TextView) findViewById(R.id.seriesActors);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        List<Episodes> episodes = series.getEpisodes();
        Collections.sort(episodes);
        EpisodesRecycleViewAdapter episodesRecycleViewAdapter = new EpisodesRecycleViewAdapter(this, episodes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(episodesRecycleViewAdapter);

        Picasso.get()
                .load(series.getImage())
                .into(seriesImage);
        seriesName.setText(series.getName());
        seriesGenre.setText(series.getGenre());
        seriesDate.setText(series.getDate());
        seriesDescription.setText(series.getDescription());
        seriesSeasons.setText(series.getSeasons());
        seriesProducers.setText(series.getProducers());
        seriesActors.setText(series.getActors());

        Call<Boolean> call1 = NetworkService.getInstance().getJSONApi().isInNotification(series.getId(), Authentication.getUser().getEmail());
        RequestService requestService1 = new RequestService();
        requestService1.setCall(call1);
        requestService1.execute();
        while (requestService1.getResult() == null) {
        }

        isInNotification = (Boolean) requestService1.getResult();

        imageButton = (ImageButton) findViewById(R.id.buttonImage);
        changeImage();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isInNotification = !isInNotification;
                Call<Boolean> req;
                String answer;
                if (isInNotification) {
                    req = NetworkService.getInstance().getJSONApi().addNotification(series.getId(), Authentication.getUser().getEmail());
                    answer = "Added into Favorite";
                } else {
                    req = NetworkService.getInstance().getJSONApi().removeNotification(series.getId(), Authentication.getUser().getEmail());
                    answer = "Deleted from Favorite";
                }
                RequestService service = new RequestService();
                service.setCall(req);
                service.execute();
                while (service.getResult() == null) {
                }

                if ((Boolean) service.getResult()) {
                    Toast.makeText(getApplicationContext(), answer, Toast.LENGTH_LONG).show();
                    changeImage();
                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }
    private void changeImage() {
        if (isInNotification)

            imageButton.setImageResource(R.drawable.ic_favorite_black_24dp);
        else
            imageButton.setImageResource(R.drawable.favorite_border);
    }
}
