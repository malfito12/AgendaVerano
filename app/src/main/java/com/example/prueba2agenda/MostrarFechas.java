package com.example.prueba2agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.prueba2agenda.entidades.Fecha;
import com.example.prueba2agenda.utilidades.Utilidades;

import java.util.ArrayList;

public class MostrarFechas extends AppCompatActivity {

    ListView listaFecha;
    ArrayList<String> listainformacion;
    ArrayList<Fecha> listafechas;

    ConnectSQL conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_fechas);

        conn=new ConnectSQL(getApplicationContext(),"db_fechas",null,1);

        listaFecha=(ListView)findViewById(R.id.listviewFechas);

        consultarListaFechas();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listainformacion);
        listaFecha.setAdapter(adaptador);

    }


    private void consultarListaFechas() {
        SQLiteDatabase db= conn.getReadableDatabase();

        Fecha tiempo = null;
        listafechas=new ArrayList<Fecha>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_AGENDA,null);

        while (cursor.moveToNext()){
            tiempo=new Fecha();
            tiempo.setFecha(cursor.getString(0));
            tiempo.setHora(cursor.getString(1));
            tiempo.setDescripsion(cursor.getString(2));

            listafechas.add(tiempo);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listainformacion=new ArrayList<String>();
        for (int i=0;i<listafechas.size();i++){
            listainformacion.add(listafechas.get(i).getFecha()+" - "+listafechas.get(i).getHora()+" - "+listafechas.get(i).getDescripsion());

        }
    }
}
