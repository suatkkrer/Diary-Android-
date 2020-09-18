package com.suatkkrer.diary;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment_search extends Fragment {

    EditText searchEdit;
    View v;
    Context thisContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       thisContext = container.getContext();
        v = inflater.inflate(R.layout.fragment_search,container,false);

        searchEdit = v.findViewById(R.id.searchEditText);


        return v;
    }
}
