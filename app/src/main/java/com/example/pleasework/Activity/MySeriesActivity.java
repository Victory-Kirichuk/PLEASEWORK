package com.example.pleasework.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pleasework.Adapter.SeriesRecycleViewAdapter;
import com.example.pleasework.Entity.Series;
import com.example.pleasework.Network.Authentication;
import com.example.pleasework.Network.NetworkService;
import com.example.pleasework.Network.RequestService;
import com.example.pleasework.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;

public class MySeriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_series);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Call<List<Series>> call = NetworkService.getInstance().getJSONApi().getMy(Authentication.getUser().getEmail());
        RequestService requestService = new RequestService();
        requestService.setCall(call);
        requestService.execute();
        while (requestService.getResult() == null) {
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        SeriesRecycleViewAdapter seriesRecycleViewAdapter = new SeriesRecycleViewAdapter(this, (List<Series>) requestService.getResult());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        seriesRecycleViewAdapter.setListener(new SeriesRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Series item) {
                Intent intent = new Intent(getApplicationContext(), SeriesActivity.class);
                intent.putExtra(SeriesActivity.SERIES_ID, item.getId());
                startActivity(intent);
                finish();
            }
        });
        recyclerView.setAdapter(seriesRecycleViewAdapter);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavig);
        bottomNavigationView.setSelectedItemId(R.id.mySeries);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home: {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.timetable: {
                        Intent intent = new Intent(getApplicationContext(), TimetableActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }
}
