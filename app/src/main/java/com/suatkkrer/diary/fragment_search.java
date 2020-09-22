package com.suatkkrer.diary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class fragment_search extends Fragment implements AdapterSearch.OnNoteListener {

    EditText searchEdit;
    View v;
    Context thisContext;
    RecyclerView recyclerView;
    AdapterSearch adapter;
    List<MemoryItems> mData;
    List<MemoryItems> mData2;
    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       thisContext = container.getContext();
        v = inflater.inflate(R.layout.fragment_search,container,false);

        searchEdit = v.findViewById(R.id.searchEditText);
        recyclerView = v.findViewById(R.id.searchRecycler);
        linearLayout = v.findViewById(R.id.searchLinear);

        try {
            SQLiteDatabase sqLiteDatabase = thisContext.openOrCreateDatabase("Memories",MODE_PRIVATE,null);

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS memories(id INTEGER PRIMARY KEY,title TEXT, memory TEXT, date TEXT)");


            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM memories",null);

            int titleIx = cursor.getColumnIndex("title");
            int memoryIx = cursor.getColumnIndex("memory");
            int dateIx = cursor.getColumnIndex("date");
            int idIx = cursor.getColumnIndex("id");

            while (cursor.moveToNext()){
                mData.add(new MemoryItems(cursor.getString(dateIx),cursor.getString(titleIx),cursor.getString(memoryIx),R.drawable.fff,cursor.getInt(idIx)));
                mData2.add(new MemoryItems(cursor.getString(dateIx),cursor.getString(titleIx),cursor.getString(memoryIx),R.drawable.fff,cursor.getInt(idIx)));
            }
            cursor.close();




        } catch (Exception e){
            e.printStackTrace();
        }

        adapter = new AdapterSearch(thisContext,mData,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                adapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) thisContext.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
            }
        });

        
        return v;
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(getContext(),AddMemory.class);
        intent.putExtra("title",mData2.get(position).getTitle());
        intent.putExtra("memory",mData2.get(position).getContent());
        intent.putExtra("id",mData2.get(position).getId());
        intent.putExtra("date",mData2.get(position).getDate());
        startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = new ArrayList<>();
        mData2 = new ArrayList<>();
    }
}
