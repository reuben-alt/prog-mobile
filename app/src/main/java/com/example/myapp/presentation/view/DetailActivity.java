package com.example.myapp.presentation.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.Singletons;
import com.example.myapp.presentation.view.controlleur.MainControlleur;
import com.example.myapp.presentation.view.model.Rickandmorty;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private TextView txtDetail;
    private TextView txtDetail1;
    private TextView txtDetail2;
    private TextView txtDetail3;
    String samba = null;
         @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
             Button createNotificationButton = findViewById(R.id.show);

             createNotificationButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     addNotification();
                 }
             });


             txtDetail = findViewById(R.id.detail_view);
             txtDetail1 = findViewById(R.id.firstdetail);
             txtDetail2 = findViewById(R.id.thirdet);
             txtDetail3 = findViewById(R.id.forthdet);
             Intent intent = getIntent();
             String rickandmortyJson = intent.getStringExtra("key");
             Rickandmorty rickandmorty = Singletons.getGson().fromJson(rickandmortyJson,Rickandmorty.class);
             showDetail(rickandmorty);
     }



    private void showDetail(Rickandmorty rickandmorty) {
             txtDetail.setText(rickandmorty.getName());
        txtDetail1.setText(rickandmorty.getSpecies());
        txtDetail2.setText(rickandmorty.getGender());
        txtDetail3.setText(rickandmorty.getStatus());

    }
    private void addNotification() {
        // Builds your notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.rm)
                .setContentTitle("my notif")
                .setContentText("check other characters");

        // Creates the intent needed to show the notification
        Intent notificationIntent = new Intent(this, DetailActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}



