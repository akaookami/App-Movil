package com.example.anger.pruebafirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DetalleTurnoNegocioActivity extends AppCompatActivity {

    TextView txtId;
    TextView txtFecha;
    TextView txtHora;
    TextView txtNegocio;
    TextView txtNo;
    TextView txtUsuario;
    TextView txtPresente;
    TextView txtActivo;
    TextView txtTipoS;
    TextView txtTiempoE;
    Button btnCheck;
    Button btnCancelar;
    String idSucursal;
    DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
    String id=  ref.push().getKey();
    String fechaHoy=getCurrentDate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        idSucursal= getIntent().getExtras().getString("id","No existe");
        setContentView(R.layout.activity_detalle_turno_negocio);
        txtId=(TextView) findViewById(R.id.txtId);
        txtFecha=(TextView) findViewById(R.id.txtFecha);
        txtHora=(TextView) findViewById(R.id.txtHora);
        txtNo=(TextView) findViewById(R.id.txtNo);
        txtUsuario=findViewById(R.id.txtUsuario);
        txtPresente=findViewById(R.id.txtPresente);
        txtActivo=findViewById(R.id.txtActivo);
        txtTipoS=findViewById(R.id.txtTipoS);
        txtTiempoE=findViewById(R.id.txtTiempoE);
        txtNegocio=(TextView) findViewById(R.id.txtNegocio);
        btnCancelar=findViewById(R.id.btnCancelar);
        btnCheck=findViewById(R.id.btnCheck);
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
        llenarEspacios();
    }

    private void crearTurno() {
        //una forma de hacerlo dentro de una tabla ya creada
        // DatabaseReference mensaje=ref.child("prueba1/Hora");
        // mensaje.setValue("HolaMundo");

        ref.child("turno").child(fechaHoy).child(id).child("no_turno").setValue("5");
        ref.child("turno").child(fechaHoy).child(id).child("fecha").setValue(getCurrentDate());
        ref.child("turno").child(fechaHoy).child(id).child("tipo_servicio").setValue("NEGOCIOS");
        ref.child("turno").child(fechaHoy).child(id).child("presente").setValue("false");
        ref.child("turno").child(fechaHoy).child(id).child("turno_activo").setValue("true");
        ref.child("turno").child(fechaHoy).child(id).child("promedio_tiempo_espera").setValue("20");
        ref.child("turno").child(fechaHoy).child(id).child("id_negocio").setValue(idSucursal);
        ref.child("turno").child(fechaHoy).child(id).child("id_usuario").setValue("pruebaUsuario1");
        ref.child("turno").child(fechaHoy).child(id).child("hora_pedido").setValue(getCurrentTime());
        txtId.setText(id);
    }



    public String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate =  mdformat.format(calendar.getTime());
        return strDate;
    }

    private void llenarEspacios(){


        DatabaseReference fecha=ref.child("turno").child(fechaHoy).child(id+"/fecha");
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

        DatabaseReference hora=ref.child("turno").child(fechaHoy).child(id+"/hora_pedido");
        hora.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                txtHora.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference negocio=ref.child("turno").child(fechaHoy).child(id+"/id_negocio");
        negocio.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                txtNegocio.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference usuario=ref.child("turno").child(fechaHoy).child(id+"/id_usuario");
        usuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                txtUsuario.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        DatabaseReference noTurno=ref.child("turno").child(fechaHoy).child(id+"/no_turno");
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

        DatabaseReference presente=ref.child("turno").child(fechaHoy).child(id+"/presente");
        presente.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                txtPresente.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        DatabaseReference espera=ref.child("turno").child(fechaHoy).child(id+"/promedio_tiempo_espera");
        espera.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                txtTiempoE.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference tipoS=ref.child("turno").child(fechaHoy).child(id+"/tipo_servicio");
        tipoS.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                txtTipoS.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        DatabaseReference activo=ref.child("turno").child(fechaHoy).child(id+"/turno_activo");
        activo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                txtActivo.setText(value);
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
        SimpleDateFormat mdformat = new SimpleDateFormat(" dd / MM / yyyy ");
        String strDate = mdformat.format(calendar.getTime());

        return strDate;
    }

    public void metodoCancelar(View view) {
        ref.child("turno").child(fechaHoy).child(id).child("turno_activo").setValue("false");
        DatabaseReference activo=ref.child("turno").child(fechaHoy).child(id+"/turno_activo");
        activo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                txtActivo.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
