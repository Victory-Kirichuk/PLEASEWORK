package com.example.pleasework.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pleasework.Adapter.SeriesRecycleViewAdapter;
import com.example.pleasework.Entity.Series;
import com.example.pleasework.Network.NetworkService;
import com.example.pleasework.Network.RequestService;
import com.example.pleasework.R;

import java.util.List;

import retrofit2.Call;

public class SearchActivity extends AppCompatActivity {

    public static final String SEARCH = "search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Call<List<Series>> call = NetworkService.getInstance().getJSONApi().getBySearch(getIntent().getStringExtra(SEARCH));
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
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        onBackPressed();
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }
//    @Override
//    public void onBackPressed(){
//
//    }
}
