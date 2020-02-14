package com.example.prueba2agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.prueba2agenda.utilidades.Utilidades;

import java.util.Calendar;

public class RegistrarFechas extends AppCompatActivity implements View.OnClickListener {

    Button btn3,btn4;
    EditText txt_f,txt_h,txt_d;
    private  int dia, mes, ano, hora, minutos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_fechas);

        btn3=(Button)findViewById(R.id.btn_fecha);
        btn4=(Button)findViewById(R.id.btn_hora);
        txt_f=(EditText)findViewById(R.id.txt_fecha);
        txt_h=(EditText)findViewById(R.id.txt_hora);
        txt_d=(EditText)findViewById(R.id.txt_desc);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

    }
    
    public void Registrar(View view){
        String a_fecha= txt_f.getText().toString();
        String a_hora=txt_h.getText().toString();
        String a_des= txt_d.getText().toString();
        if (!a_fecha.isEmpty() && !a_hora.isEmpty() && !a_des.isEmpty()){
            registrarFechas();
        }else {
            Toast.makeText(this, "llena todos los textos weón", Toast.LENGTH_SHORT).show();
        }

    }

    private void registrarFechas() {
        ConnectSQL conn=new ConnectSQL(this, "db_fechas",null,1);
        SQLiteDatabase db=conn.getReadableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_FECHA, txt_f.getText().toString());
        values.put(Utilidades.CAMPO_HORA, txt_h.getText().toString());
        values.put(Utilidades.CAMPO_DESC, txt_d.getText().toString());

        long idResultante=db.insert(Utilidades.TABLA_AGENDA,Utilidades.CAMPO_FECHA,values);

        Toast.makeText(getApplicationContext(),"TAREA GUARDADA:"+idResultante,Toast.LENGTH_SHORT).show();
        Intent dos = new Intent(this, MainActivity.class);
        startActivity(dos);

    }

    @Override
    public void onClick(View v) {
        if(v==btn3){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    txt_f.setText(dayOfMonth+"/"+(month)+"/"+year);
                }
            }
                    ,dia,mes,ano);
            datePickerDialog.show();
        }if(v==btn4){
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minutos=c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    txt_h.setText(hourOfDay+":"+minute);
                }
            },hora,minutos,false);
            timePickerDialog.show();

        }
    }
}
