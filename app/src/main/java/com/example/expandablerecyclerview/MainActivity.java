package com.example.expandablerecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        setRecyclerView();
    }


    private void setRecyclerView() {
//        VersionsAdapter versionsAdapter = new VersionsAdapter(versionsList);
        VersionsAdapter versionsAdapter = new VersionsAdapter(MainActivity.this,UserAgitctivity.arrayList);
        recyclerView.setAdapter(versionsAdapter);
        recyclerView.setHasFixedSize(true);


    }
}