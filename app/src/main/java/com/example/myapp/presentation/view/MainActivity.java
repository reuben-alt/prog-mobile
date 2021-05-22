package com.example.myapp.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.Singletons;
import com.example.myapp.presentation.view.controlleur.MainControlleur;
import com.example.myapp.presentation.view.model.Rickandmorty;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainControlleur controlleur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controlleur = new MainControlleur(
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferencesInstance(getApplicationContext())
        );
        controlleur.onStart();
     }


    public void showList(List<Rickandmorty> rickandmortyList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_View);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mAdapter = new ListAdapter(rickandmortyList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Rickandmorty item) {
                controlleur.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }
    public void showError() {
        Toast.makeText(getApplicationContext(),"API Error",Toast.LENGTH_SHORT).show();
    }
    public void navigateToDetails(Rickandmorty rickandmorty) {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        myIntent.putExtra("key", Singletons.getGson().toJson(rickandmorty)); //Optional parameters
        MainActivity.this.startActivity(myIntent);

    }

}

