package com.example.anger.pruebafirebase;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SucursalActivity extends AppCompatActivity {

    Button btnSucursal;
    String bancoKey;
    Spinner spnSucursal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursal);
        btnSucursal = (Button) findViewById(R.id.btnsucursal);
        spnSucursal = (Spinner) findViewById(R.id.spnSucursal);
        Intent intt = new Intent(this, TipoServicioActivity.class);
        bancoKey = intt.getStringExtra("key");
        Toast.makeText(SucursalActivity.this, bancoKey, Toast.LENGTH_LONG).show();
        llenarSpn();
    }

    public void llenarSpn() {
        final ArrayList<String> lista = new ArrayList<>();//esta lista primero toma las sucursales del banco
        final ArrayList<String> listaSpinner = new ArrayList<>();//esta lista tendra los nombres desde la tabla de sucursales
        listaSpinner.add("");
        FirebaseDatabase.getInstance()
                .getReference()
                .child("pruebas")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            if (childSnapshot.getKey() == bancoKey) {
                                for (DataSnapshot sucursal : childSnapshot.child("sucursal").getChildren()) {
                                    Toast.makeText(SucursalActivity.this, sucursal.getKey(), Toast.LENGTH_LONG).show();

                                    lista.add(sucursal.getKey());
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


        FirebaseDatabase.getInstance()
                .getReference()
                .child("sucursales")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            for (String item : lista) {
                                if (item == childSnapshot.getKey()) {
                                    listaSpinner.add(childSnapshot.child("nombre").toString());
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        spnSucursal.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaSpinner));


    }


    public void Saltar(View view) {
        Toast.makeText(SucursalActivity.this, "Un momento por favor.", Toast.LENGTH_LONG).show();
        Intent intt = new Intent(this, TipoServicioActivity.class);
        startActivity(intt);
    }
}
