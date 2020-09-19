package com.suatkkrer.diary;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import static android.content.Context.MODE_PRIVATE;

public class fragment_home extends Fragment implements Adapter.OnNoteListener {

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



        try {
            SQLiteDatabase sqLiteDatabase = thisContext.openOrCreateDatabase("Memories",MODE_PRIVATE,null);

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS memories(id INTEGER PRIMARY KEY,title VARCHAR, memory VARCHAR,date VARCHAR)");


            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM memories",null);

            int titleIx = cursor.getColumnIndex("title");
            int memoryIx = cursor.getColumnIndex("memory");
            int idIx = cursor.getColumnIndex("id");
            int dateIx = cursor.getColumnIndex("date");

            while (cursor.moveToNext()){
                mData.add(new MemoryItems(cursor.getString(dateIx),cursor.getString(titleIx),cursor.getString(memoryIx),R.drawable.fff,cursor.getInt(idIx)));
            }
            cursor.close();



        } catch (Exception e){
            e.printStackTrace();
        }



        adapter = new Adapter(thisContext,mData,this);
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

        mData = new ArrayList<>();
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(getContext(),AddMemory.class);
        intent.putExtra("title",mData.get(position).getTitle());
        intent.putExtra("memory",mData.get(position).getContent());
        intent.putExtra("id",mData.get(position).getId());
        intent.putExtra("date",mData.get(position).getDate());
        startActivity(intent);
    }
}
