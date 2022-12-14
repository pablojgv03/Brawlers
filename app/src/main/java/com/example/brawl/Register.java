package com.example.brawl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity implements View.OnClickListener{
    EditText us, pas, nom, ap;
    Button reg,can;
    userDB_Class bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        us = (EditText) findViewById(R.id.userNameET_register);
        pas = (EditText) findViewById(R.id.passwordET_register);
        nom = (EditText) findViewById(R.id.nameET_register);
        ap = (EditText) findViewById(R.id.surnameET_register);
        reg = (Button) findViewById(R.id.regbutton_register);
        can = (Button) findViewById(R.id.cancelbutton_register);

        reg.setOnClickListener(this);
        can.setOnClickListener(this);

        bd = new userDB_Class(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.regbutton_register:
                User_Class u= new User_Class();
                u.setUserName(us.getText().toString());
                u.setPassword(pas.getText().toString());
                u.setName(nom.getText().toString());
                u.setSurname(ap.getText().toString());
                if(!u.isNull()){
                    Toast.makeText(this,"Error. Campos vacios", Toast.LENGTH_LONG).show();
                }else{
                    if (bd.insertUser(u)){
                        Toast.makeText(this,"Usuario registrado", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Register.this, log_reg_Act.class );
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(this,"Ya existe un usuario con ese nombre", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.cancelbutton_register:
                Intent i2 =new Intent(Register.this, log_reg_Act.class );
                startActivity(i2);
                finish();
                break;
        }
    }
}