package com.example.anger.pruebafirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DetalleTurnoNegocioActivity extends AppCompatActivity {
    TextView txtId;
    TextView txtFecha;
    TextView txtHora;
    TextView txtNegocio;
    TextView txtNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_turno_negocio);
        txtFecha=(TextView) findViewById(R.id.txtFecha);
        txtId=(TextView) findViewById(R.id.txtId);
        txtHora=(TextView) findViewById(R.id.txtHora);
        txtNegocio=(TextView) findViewById(R.id.txtNegocio);
        txtNo=(TextView) findViewById(R.id.txtNo);

        rellenar();
    }

    private void rellenar() {

        txtFecha.setText(getCurrentDate());
        txtId.setText(setIdentificacion());
        txtNo.setText(setNumeroTurno());
        txtNegocio.setText("Prueba NEGOCIO");
        txtHora.setText("4:00 PM");

    }

    public String setNumeroTurno() {

        int no =  (int) (20 * Math.random()) + 1;

        return Integer.toString(no);
    }

    public String setIdentificacion(){
        int id = (int) (100 * Math.random()) + 1;
        return Integer.toString(id);
    }


    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        String strDate = mdformat.format(calendar.getTime());

        return strDate;
    }
}
