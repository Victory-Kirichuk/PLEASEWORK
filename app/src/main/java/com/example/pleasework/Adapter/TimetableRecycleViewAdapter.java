package com.example.pleasework.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pleasework.Entity.Timetable;
import com.example.pleasework.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TimetableRecycleViewAdapter extends RecyclerView.Adapter<TimetableRecycleViewAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Timetable> timetables;
    private TimetableRecycleViewAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Timetable item);
    }

    public void setListener(TimetableRecycleViewAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


    public TimetableRecycleViewAdapter(Context context, List<Timetable> timetables) {
        inflater = LayoutInflater.from(context);
        this.timetables = timetables;
    }

    @Override
    public TimetableRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_timetable, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimetableRecycleViewAdapter.ViewHolder holder, int position) {
        holder.bind(timetables.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return timetables.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView seriesName;
        final TextView seriesSeason;
        final TextView seriesEpisodes;
        final TextView seriesData;
        final TextView episodeName;
        final ImageView image;

        public ViewHolder(View view) {
            super(view);
            seriesName = (TextView) view.findViewById(R.id.seriesName);
            seriesSeason = (TextView) view.findViewById(R.id.seriesSeason);
            seriesEpisodes = (TextView) view.findViewById(R.id.seriesEpisode);
            seriesData = (TextView) view.findViewById(R.id.seriesData);
            episodeName=(TextView) view.findViewById(R.id.epName);
            image=(ImageView) view.findViewById(R.id.imageView);
        }

        public void bind(final Timetable item, final TimetableRecycleViewAdapter.OnItemClickListener listener) {
            android.text.format.DateFormat dateFormat=new android.text.format.DateFormat();
            seriesName.setText(item.getSeriesName());
            seriesSeason.setText("S."+item.getEpisodesSeason().toString());
            seriesEpisodes.setText("Ep."+item.getEpisodesNumber().toString());
            seriesData.setText(dateFormat.format("dd MMM yyyy",item.getEpisodesDate()));
            episodeName.setText(item.getEpisodeName());
            Picasso.get()
                    .load(item.getImage())
                    .into(image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
