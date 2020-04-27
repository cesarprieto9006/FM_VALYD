package com.example.fina.fm_valid.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fina.fm_valid.R;
import com.example.fina.fm_valid.model.Data_Artist;
import com.example.fina.fm_valid.model.Data_Track;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.OrdenHolder> {

    private ArrayList<Data_Track> dataTracks;
    private Context context;

    public TrackAdapter(ArrayList<Data_Track> dataTracks, Context context) {
        this.dataTracks = dataTracks;
        this.context = context;
    }

    public void setData(ArrayList<Data_Track> dataTracks) {
        this.dataTracks = dataTracks;
        notifyDataSetChanged();
    }

    @Override
    public OrdenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_track, parent, false);
        OrdenHolder mh = new OrdenHolder(v);
        return mh;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(OrdenHolder holder, int position) {
        try {
            Picasso.with(context).load(dataTracks.get(position).getImageUrl()).
                    placeholder(R.drawable.ic_launcher_background).into(holder.imgFoto);
            holder.lblName.setText(dataTracks.get(position).getName());
            holder.lblDuration.setText(dataTracks.get(position).getDuration());
            holder.lblListener.setText(dataTracks.get(position).getListeners());
            holder.lblNameArtist.setText(dataTracks.get(position).getNameArtist());
            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataTracks.size();
    }

    public class OrdenHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imgFoto)
        ImageView imgFoto;
        @BindView(R.id.lblName)
        TextView lblName;
        @BindView(R.id.lblDuration)
        TextView lblDuration;
        @BindView(R.id.lblListener)
        TextView lblListener;
        @BindView(R.id.lblNameArtist)
        TextView lblNameArtist;

        private ItemClickListener itemClickListener;

        public OrdenHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try {
                itemClickListener.onClick(view, getAdapterPosition(), false);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }
}
