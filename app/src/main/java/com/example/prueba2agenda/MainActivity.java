package com.example.prueba2agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn_add);
        btn2=findViewById(R.id.btn_mostrar);
        btn1.setOnClickListener(this);

        ConnectSQL conn=new ConnectSQL(this, "db_fechas",null,1);
    }

    @Override
    public void onClick(View v) {
        Intent uno = new Intent(this, RegistrarFechas.class);
        startActivity(uno);
    }
    public void mostar(View view){
        Intent d = new Intent(this, MostrarFechas.class);
        startActivity(d);
    }
}
