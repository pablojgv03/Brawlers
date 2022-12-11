package com.example.brawl;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home_Act extends AppCompatActivity{
    Button btnbrawler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnbrawler = (Button) findViewById(R.id.brawlersButton_home);

        btnbrawler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home_Act.this, Brawlers_Act.class );
                Log.d("H","Entra");
                startActivity(i);
            }
        });
    }

}