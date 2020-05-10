package com.example.pleasework.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pleasework.Entity.Series;
import com.example.pleasework.R;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SeriesRecycleViewAdapter extends RecyclerView.Adapter<SeriesRecycleViewAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Series> series;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Series item);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public SeriesRecycleViewAdapter(Context context, List<Series> series) {
        inflater = LayoutInflater.from(context);
        this.series = series;
    }

    @Override
    public SeriesRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_series, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SeriesRecycleViewAdapter.ViewHolder holder, int position) {
        holder.bind(series.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return series.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView image;
        final TextView seriesName;
        final TextView seriesRating;
        final TextView seriesGenre;
//        final TextView seriesDate;
        final TextView description;


        public ViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image);
            seriesName = (TextView) view.findViewById(R.id.seriesName);
            seriesRating = (TextView) view.findViewById(R.id.seriesRating);
            seriesGenre = (TextView) view.findViewById(R.id.seriesGenre);
//            seriesDate = (TextView) view.findViewById(R.id.seriesDate);
            description=(TextView) view.findViewById(R.id.description);
        }

        public void bind(final Series item, final OnItemClickListener listener) {

            seriesName.setText(item.getName());
            seriesRating.setText(item.getRating().toString());
            seriesGenre.setText(item.getGenre());
//            seriesDate.setText(item.getDate());
            description.setText(item.getDescription());
            Picasso.get()
                    .load(item.getImage())
                    .into(image);

            if (item.getRating() >= 7.0D) {
                seriesRating.setBackgroundResource(R.color.green);
            } else {
                if (item.getRating() >= 5.0D) {
                    seriesRating.setBackgroundResource(R.color.yellow);
                } else {
                    seriesRating.setBackgroundResource(R.color.red);
                }
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

        }
    }
}
