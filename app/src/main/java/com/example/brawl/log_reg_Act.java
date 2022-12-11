package com.example.brawl;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class log_reg_Act extends AppCompatActivity implements View.OnClickListener{
        EditText user, pass;
        Button btnLog, btnReg;
        userDB_Class db;
@SuppressLint("MissingInflatedId")
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.userNameET);
        pass = (EditText) findViewById(R.id.passwordET);
        btnLog = (Button) findViewById(R.id.logButton);
        btnReg = (Button) findViewById(R.id.regButton);

        btnLog.setOnClickListener(this);
        btnReg.setOnClickListener(this);

        db = new userDB_Class(this);
        }

@Override
public void onClick(View view) {
        switch(view.getId()){
        case R.id.logButton:
        String u=user.getText().toString();
        String p=pass.getText().toString();
        if(u.equals("") || p.equals("")){
        Toast.makeText(this,"Error. Campos vacios", Toast.LENGTH_LONG).show();
        }else{
        if(db.login(u,p)==1){
        User_Class ux = db.getUserByName(u,p);
        Toast.makeText(this,"Bienvenido", Toast.LENGTH_LONG).show();
        Intent i=new Intent(log_reg_Act.this, Home_Act.class );
        i.putExtra("Id", ux.getId());
        startActivity(i);
        }
        }
        break;
        case R.id.regButton:

        Intent i2=new Intent(log_reg_Act.this, Register.class );
        startActivity(i2);
        break;
        }
        }
        }