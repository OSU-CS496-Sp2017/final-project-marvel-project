package com.example.btanner.marveldatabase;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by btanner on 6/3/2017.
 */

public class MarvelRVAdapter extends RecyclerView.Adapter<MarvelRVAdapter.MarvelItemViewHolder> {

    private OnMarvelItemClickListener mMarvelItemClickListener;
    private ArrayList<utils.MarvelCharacterItem> mMarvelItems;


    public interface OnMarvelItemClickListener {
        void onMarvellItemClick(utils.MarvelCharacterItem marvelItem);
    }

    public MarvelRVAdapter (OnMarvelItemClickListener clickListener) {
        mMarvelItemClickListener = clickListener;
    }

    public void updateMarvelItems(ArrayList<utils.MarvelCharacterItem> marvelItems) {
        mMarvelItems = marvelItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mMarvelItems != null) {
            return mMarvelItems.size();
        }
        else {
            return 0;
        }
    }


    @Override
    public MarvelItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.marvel_item, parent, false);
        return new MarvelItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MarvelItemViewHolder holder, int position) {
        holder.bind( (utils.MarvelCharacterItem) (mMarvelItems.get(position)));
    }


    class MarvelItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mMarvelItemTV;
        public MarvelItemViewHolder(View itemView) {
            super(itemView);
            mMarvelItemTV = (TextView)itemView.findViewById(R.id.tv_marvel_rc_item);
            itemView.setOnClickListener(this);
        }

        public void bind (utils.MarvelCharacterItem marvelItem) {
            mMarvelItemTV.setText(marvelItem.name);
        }

        public void onClick(View v) {
            utils.MarvelCharacterItem marvelItem = (utils.MarvelCharacterItem) mMarvelItems.get(getAdapterPosition());
            mMarvelItemClickListener.onMarvellItemClick(marvelItem);
        }
    }


}
