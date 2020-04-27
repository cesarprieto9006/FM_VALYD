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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransaccionAdapter extends RecyclerView.Adapter<TransaccionAdapter.OrdenHolder> {

    private ArrayList<Data_Artist> dataArtist;
    private Context context;

    public TransaccionAdapter(ArrayList<Data_Artist> dataArtists, Context context) {
        this.dataArtist = dataArtists;
        this.context = context;
    }

    public void setData(ArrayList<Data_Artist> dataArtist) {
        this.dataArtist = dataArtist;
        notifyDataSetChanged();
    }

    @Override
    public OrdenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_artist, parent, false);
        OrdenHolder mh = new OrdenHolder(v);
        return mh;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(OrdenHolder holder, int position) {
        try {
            holder.lblListener.setText(dataArtist.get(position).getListeners());
            holder.lblName.setText(dataArtist.get(position).getName());
            Picasso.with(context).load(dataArtist.get(position).getImage().get(2).getText()).
                    placeholder(R.drawable.ic_launcher_background).into(holder.imgFoto);
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
        return dataArtist.size();
    }

    public class OrdenHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imgFoto)
        ImageView imgFoto;
        @BindView(R.id.lblListener)
        TextView lblListener;
        @BindView(R.id.lblName)
        TextView lblName;

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
