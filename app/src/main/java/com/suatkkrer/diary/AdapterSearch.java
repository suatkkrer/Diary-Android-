package com.suatkkrer.diary;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.AdapterViewHolder> implements Filterable {

    Context mContext;
    List<MemoryItems> mData;
    List<MemoryItems> mDataFiltered;
    private OnNoteListener mOnNoteListener;
    fragment_search fragmentSearch = new fragment_search();


    public AdapterSearch(Context mContext, List<MemoryItems> mData, OnNoteListener onNoteListener) {
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
        return new AdapterViewHolder(layout,mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {

        holder.iconView.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition));

        holder.relativeLayout.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale));


        MemoryItems item = mDataFiltered.get(position);
        holder.bind(item);

    }

    @Override
    public int getItemCount() {
        return mDataFiltered.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();
                if (Key.isEmpty()){
                    mDataFiltered = mData;
                } else
                {
                    List<MemoryItems> lstFiltered = new ArrayList<>();
                    for (MemoryItems row : mData){
                        if (row.getTitle().toLowerCase().contains(Key.toLowerCase()) || row.getContent().toLowerCase().contains(Key.toLowerCase()) || row.getDate().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);
                        }
                    }
                    mDataFiltered = lstFiltered;
                    fragmentSearch.mData2 = lstFiltered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mDataFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                mDataFiltered = (List<MemoryItems>) results.values;
                notifyDataSetChanged();

            }
        };



    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView memoryText,titleText,dateText;
        ImageView iconView;
        RelativeLayout relativeLayout;
        OnNoteListener onNoteListener;
        private MemoryItems currentItem;
        Context context;

        public AdapterViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            memoryText = itemView.findViewById(R.id.memoryText);
            titleText = itemView.findViewById(R.id.titleText);
            dateText = itemView.findViewById(R.id.dateText);
            iconView = itemView.findViewById(R.id.iconView);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            itemView.setOnClickListener(this);

        }

        void bind(MemoryItems items){
            memoryText.setText(items.getContent());
            titleText.setText(items.getTitle());
            dateText.setText(items.getDate());
            iconView.setImageResource(items.getIcon());
            currentItem = items;
        }



        @Override
        public void onClick(View v) {
            Intent intent = new Intent(this.context,AddMemory.class);
            intent.putExtra("title",currentItem.getTitle());
            intent.putExtra("memory",currentItem.getContent());
            intent.putExtra("id",currentItem.getId());
            intent.putExtra("date",currentItem.getDate());
            this.context.startActivity(intent);
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
