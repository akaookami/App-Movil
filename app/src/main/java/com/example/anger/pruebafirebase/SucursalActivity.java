package com.example.anger.pruebafirebase;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class SucursalActivity extends AppCompatActivity {

    Button btnSucursal;
    String bancoKey;
    Spinner spnSucursal;
    ArrayList<String> nombreNegocios = new ArrayList<>();
    String idNegocio;

    public SucursalActivity() {
        idNegocio = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursal);
        btnSucursal = (Button) findViewById(R.id.btnsucursal);
      //  spnSucursal = (Spinner) findViewById(R.id.spnSucursal);
        Intent intt = new Intent(this, TipoServicioActivity.class);
       // bancoKey = intt.getStringExtra("key");
       // Toast.makeText(SucursalActivity.this, bancoKey, Toast.LENGTH_LONG).show();


        opciones();
    }

    public void opciones() {
        //es final no se cambia


        FirebaseDatabase.getInstance()
                .getReference()
                .child("sucursal")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            addArray(childSnapshot.getKey());

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
/*
        String [] objectList = (String[]) nombreNegocios.toArray();
       for(int i=0;i<nombreNegocios.size();i++){
           objectList[i]=nombreNegocios.get(i);
           Toast.makeText(SucursalActivity.this, nombreNegocios.get(i), Toast.LENGTH_LONG).show();
       }*/
       final String[] opc ={"prueba1"};
               //Arrays.copyOf(objectList,objectList.length,String[].class);

        final  AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccione un Negocio");
        builder.setItems(opc, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on colors[which]
               saltar( opc[which]);
            }
        });
        builder.show();

    }


    public void addArray(String s){
//
        nombreNegocios.add(s);
       // Toast.makeText(SucursalActivity.this, nombreNegocios.get(0), Toast.LENGTH_LONG).show();
    }


   public void saltar(String i) {
        Toast.makeText(SucursalActivity.this, "Un momento por favor.", Toast.LENGTH_LONG).show();
        Intent intt = new Intent(this, TipoServicioActivity.class);
        intt.putExtra("id",i);
        startActivity(intt);
    }

    public void Saltar(View view) {
        Toast.makeText(SucursalActivity.this, "Un momento por favor.", Toast.LENGTH_LONG).show();
        Intent intt = new Intent(this, TipoServicioActivity.class);
        startActivity(intt);
    }
}
