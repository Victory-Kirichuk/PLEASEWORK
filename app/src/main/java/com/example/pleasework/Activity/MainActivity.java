package com.example.pleasework.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pleasework.Adapter.SeriesRecycleViewAdapter;
import com.example.pleasework.Entity.Series;
import com.example.pleasework.Network.Authentication;
import com.example.pleasework.Network.NetworkService;
import com.example.pleasework.Network.RequestService;

import com.example.pleasework.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Call<List<Series>> call = NetworkService.getInstance().getJSONApi().getAllSeries();
        RequestService requestService = new RequestService();
        requestService.setCall(call);
        requestService.execute();
        while (requestService.getResult() == null){}



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


        final EditText editText = (EditText) findViewById(R.id.search);
        final ImageButton search = (ImageButton) findViewById(R.id.searchButton);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("")){
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    intent.putExtra(SearchActivity.SEARCH, editText.getText().toString());
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(), "Input Series name", Toast.LENGTH_LONG).show();
            }
        });

        ImageButton logout = (ImageButton) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Authentication.logout();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                 startActivity(intent);
                finish();
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavig);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.mySeries: {
                        Intent intent = new Intent(getApplicationContext(), MySeriesActivity.class);
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



}
