package com.example.pleasework.Activity;

import android.app.Notification;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pleasework.Adapter.TimetableRecycleViewAdapter;

import com.example.pleasework.Entity.Timetable;
import com.example.pleasework.Network.Authentication;
import com.example.pleasework.Network.NetworkService;
import com.example.pleasework.Network.RequestService;
import com.example.pleasework.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;


public class TimetableActivity extends AppCompatActivity {
    private static final int NOTIFY_ID = 101;
    private static String CHANNEL_ID = "1";
    private NotificationManagerCompat notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        notificationManager = NotificationManagerCompat.from(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Call<List<Timetable>> call = NetworkService.getInstance().getJSONApi().getTimeTable(Authentication.getUser().getEmail());
        RequestService requestService = new RequestService();
        requestService.setCall(call);
        requestService.execute();
        while (requestService.getResult() == null) {
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        TimetableRecycleViewAdapter timetableRecycleViewAdapter = new TimetableRecycleViewAdapter(this, (List<Timetable>) requestService.getResult());
        timetableRecycleViewAdapter.setListener(new TimetableRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Timetable item) {
                Intent intent = new Intent(getApplicationContext(), EpisodeNotifyActivity.class);

              Notification notification =
                        new NotificationCompat.Builder(TimetableActivity.this, CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_live_tv_black_24dp)
                                .setContentTitle(", New Episode")
                                .setContentText("555")
                                .setPriority(NotificationCompat.PRIORITY_MAX)

                                .build();

                notificationManager.notify(1, notification);
                intent.putExtra(EpisodeNotifyActivity.EPISODE_ID, item.getEpisodeId());



                startActivity(intent);
                finish();
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(timetableRecycleViewAdapter);
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
        bottomNavigationView.setSelectedItemId(R.id.timetable);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.mySeries: {
                        Intent intent = new Intent(getApplicationContext(), MySeriesActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.home: {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
                return false;
            }
        });
    }

}
