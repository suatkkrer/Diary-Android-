package com.suatkkrer.diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder> implements Filterable {

    Context mContext;
    List<MemoryItems> mData;
    List<MemoryItems> mDataFiltered;
    private OnNoteListener mOnNoteListener;


    public Adapter(Context mContext, List<MemoryItems> mData,OnNoteListener onNoteListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.mOnNoteListener = onNoteListener;
        this.mDataFiltered = mData;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.items,parent,false);
        return new AdapterViewHolder(layout,mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {

        holder.iconView.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition));

        holder.relativeLayout.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale));

        holder.memoryText.setText(mData.get(position).getContent());
        holder.titleText.setText(mData.get(position).getTitle());
        holder.dateText.setText(mData.get(position).getDate());
        holder.iconView.setImageResource(mData.get(position).getIcon());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView memoryText,titleText,dateText;
        ImageView iconView;
        RelativeLayout relativeLayout;
        OnNoteListener onNoteListener;

        public AdapterViewHolder(@NonNull View itemView,  OnNoteListener onNoteListener) {
            super(itemView);
            memoryText = itemView.findViewById(R.id.memoryText);
            titleText = itemView.findViewById(R.id.titleText);
            dateText = itemView.findViewById(R.id.dateText);
            iconView = itemView.findViewById(R.id.iconView);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
