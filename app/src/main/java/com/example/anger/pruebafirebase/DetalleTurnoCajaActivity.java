package com.example.anger.pruebafirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DetalleTurnoCajaActivity extends AppCompatActivity {

    TextView txtId;
    TextView txtFecha;
    TextView txtHora;
    TextView txtNegocio;
    TextView txtNo;
    DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
    String id=  ref.push().getKey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_caja_turno);
        txtFecha=(TextView) findViewById(R.id.txtFecha);
        txtId=(TextView) findViewById(R.id.txtId);
        txtHora=(TextView) findViewById(R.id.txtHora);
        txtNegocio=(TextView) findViewById(R.id.txtNegocio);
        txtNo=(TextView) findViewById(R.id.txtNo);
        id=id.substring(14,20  );
        rellenar();

    }

    @Override
    protected void onResume() {
        super.onResume();
       // rellenar();
    }

    private void rellenar() {


        crearTurno();
        llenarEspacios();    }

    private void crearTurno() {
        //una forma de hacerlo dentro de una tabla ya creada
       // DatabaseReference mensaje=ref.child("prueba1/Hora");
       // mensaje.setValue("HolaMundo");

      ref.child("turno").child(id).child("numeroTurno").setValue("5");
      ref.child("turno").child(id).child("fecha").setValue(getCurrentDate());
      ref.child("turno").child(id).child("tipoServicio").setValue("CAJA");
      ref.child("turno").child(id).child("presente").setValue(false);
      ref.child("turno").child(id).child("activo").setValue(true);
      ref.child("turno").child(id).child("tiempoEspera").setValue("no se");
      txtId.setText(id);
    }

    private void llenarEspacios(){

        DatabaseReference noTurno=ref.child("turno").child(id+"/numeroTurno");
        noTurno.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                txtNo.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference fecha=ref.child("turno").child(id+"/fecha");
        fecha.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                txtFecha.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference tipoS=ref.child("turno").child(id+"/tipoServicio");
        tipoS.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                txtNegocio.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








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
