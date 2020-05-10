package com.example.pleasework.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pleasework.Entity.Episodes;
import com.example.pleasework.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EpisodesRecycleViewAdapter extends RecyclerView.Adapter<EpisodesRecycleViewAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Episodes> episodes;

    public EpisodesRecycleViewAdapter(Context context, List<Episodes> episodes) {
        inflater = LayoutInflater.from(context);
        this.episodes = episodes;
    }

    @Override
    public EpisodesRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_episodes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EpisodesRecycleViewAdapter.ViewHolder holder, int position) {
        holder.bind(episodes.get(position));
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView episodesNumber;
        final TextView episodesName;
        final TextView episodesDate;
        final TextView episodesSeason;


        public ViewHolder(View view) {
            super(view);
            episodesNumber = (TextView) view.findViewById(R.id.episodesNumber);
            episodesName = (TextView) view.findViewById(R.id.episodesName);
            episodesDate = (TextView) view.findViewById(R.id.episodesDate);
            episodesSeason = (TextView) view.findViewById(R.id.episodesSeason);
        }

        public void bind(final Episodes item) {

          android.text.format.DateFormat dateFormat=new android.text.format.DateFormat();
            episodesNumber.setText("Ep."+item.getNumber());
            episodesName.setText(item.getName());
            episodesDate.setText( dateFormat.format("dd MMM yyyy",item.getDate()));
            episodesSeason.setText("S."+item.getSeason().toString());

        }
    }
}
