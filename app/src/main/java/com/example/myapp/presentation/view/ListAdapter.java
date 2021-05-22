package com.example.myapp.presentation.view;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.presentation.view.model.Rickandmorty;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private  List<Rickandmorty> values;
    private  OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Rickandmorty item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
     TextView txtHeader;
      View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
        }
    }

    public void add(int position, Rickandmorty item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }
    public ListAdapter(List<Rickandmorty> myDataset, OnItemClickListener listener) {
        this.values = myDataset;
        this.listener = listener;
    }
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Rickandmorty character = values.get(position);
        holder.txtHeader.setText(character.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(character);
            }
        });
    }
    @Override
    public int getItemCount() {
        return values.size();
    }

}