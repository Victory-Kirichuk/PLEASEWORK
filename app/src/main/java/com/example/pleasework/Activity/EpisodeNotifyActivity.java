package com.example.pleasework.Activity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pleasework.Entity.Timetable;
import com.example.pleasework.Network.NetworkService;
import com.example.pleasework.Network.RequestService;
import com.example.pleasework.R;
import com.squareup.picasso.Picasso;

import java.util.Date;

import retrofit2.Call;

public class EpisodeNotifyActivity extends AppCompatActivity {


    private static final int NOTIFY_ID = 101;
    private static String CHANNEL_ID = "1";
    public static final String EPISODE_ID = "episodeId";

    private NotificationManagerCompat notificationManager;
    private static final String TAG = "MyNotiry";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_episode_notify);

        Call<Timetable> call = NetworkService.getInstance().getJSONApi().getEpByEpisodeId(getIntent().getIntExtra(EPISODE_ID, 0));
        RequestService requestService = new RequestService();
        requestService.setCall(call);
        requestService.execute();
        while (requestService.getResult() == null) {
        }

        /*    final Episodes episodes = (Episodes) requestService.getResult();
         */
        final Timetable timetable = (Timetable) requestService.getResult();
        ImageView serImage = (ImageView) findViewById(R.id.seriesImage1);
        TextView episodeName = (TextView) findViewById(R.id.epName);
        TextView episodeId = (TextView) findViewById(R.id.EpId);
        TextView episodeDate = (TextView) findViewById(R.id.datee);
        TextView seriesId = (TextView) findViewById(R.id.seriesId);
        TextView episodeSeason = (TextView) findViewById(R.id.episodeSeason);
        TextView episodeNumber = (TextView) findViewById(R.id.epiodenumb);


        Button button = findViewById(R.id.notify);
        Picasso.get()
                .load(timetable.getImage())
                .into(serImage);

        episodeNumber.setText(timetable.getEpisodesNumber().toString());
        DateFormat dateFormat = new DateFormat();
        episodeName.setText(timetable.getEpisodeName().toString());
        episodeDate.setText(dateFormat.format("dd MMM yyyy", timetable.getEpisodesDate()));
        episodeId.setText(timetable.getEpisodeId().toString());
        episodeSeason.setText(timetable.getEpisodesSeason().toString());
        seriesId.setText(timetable.getSeriesId().toString());
        // Picasso.with(EpisodeNotifyActivity.this).load(timetable.getImage()).into(R.drawable.picture)
        /*  List<Episodes> episodes = series.getEpisodes();*/
        /*    Collections.sort(episodes);*/
       /* EpisodesRecycleViewAdapter episodesRecycleViewAdapter = new EpisodesRecycleViewAdapter(this, episodes);
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
        seriesActors.setText(series.getActors());*/
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification(timetable.getSeriesName().toUpperCase() + ", New Episode", timetable.getEpisodesSeason().toString() + "x" + timetable.getEpisodesNumber() + " " + timetable.getEpisodeName());
               /* Intent notificationIntent = new Intent(EpisodeNotifyActivity.this, EpisodeNotifyActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivity(EpisodeNotifyActivity.this,
                        0, notificationIntent,
                        PendingIntent.FLAG_CANCEL_CURRENT);
                Notification notification =
                        new NotificationCompat.Builder(EpisodeNotifyActivity.this, CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_live_tv_black_24dp)
                                .setContentTitle(", New Episode")
                                .setContentText("555")
                                .setPriority(NotificationCompat.PRIORITY_MAX)

                                .build();

                notificationManager.notify(NOTIFY_ID, notification);
                Log.i(TAG,"HI");*/
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        Intent intent = new Intent(this, MySeriesActivity.class);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }


    private void sendNotification(String messageTitle, String messageBody) {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, EpisodeNotifyActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant")
            NotificationChannel notificationChannel = new NotificationChannel("my_notification", "n_channel", NotificationManager.IMPORTANCE_MAX);
            notificationChannel.setDescription("description");
            notificationChannel.setName("Channel Name");
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);


            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_live_tv_black_24dp)
                    .setContentTitle(messageTitle)
                 //   .setLargeIcon()
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                    .setSound(soundUri)
                    .setContentIntent(pendingIntent)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setOnlyAlertOnce(true)
                    .setChannelId("my_notification")
                    .setColor(Color.parseColor("#3F5996"));

            //.setProgress(100,50,false);
            assert notificationManager != null;
            int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
            notificationManager.notify(m, notificationBuilder.build());
        }


    }}
