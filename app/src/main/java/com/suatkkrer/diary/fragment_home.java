package com.suatkkrer.diary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class fragment_home extends Fragment {

    View v;
    Context thisContext;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    Adapter adapter;
    List<MemoryItems> mData;
    CoordinatorLayout coordinatorLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thisContext = container.getContext();
        v = inflater.inflate(R.layout.fragment_home,container,false);

        coordinatorLayout = v.findViewById(R.id.fragmentHomeLayout);
        recyclerView = v.findViewById(R.id.memoryRecycler);
        floatingActionButton = v.findViewById(R.id.fb_button);
        mData = new ArrayList<>();

        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.ffff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile adam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bileadam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));
        mData.add(new MemoryItems("12/28/3131","Programming","adam olana cok bile",R.drawable.fff));

        adapter = new Adapter(thisContext,mData);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thisContext,AddMemory.class);
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
