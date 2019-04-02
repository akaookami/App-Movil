package com.example.anger.pruebafirebase;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import static com.example.anger.pruebafirebase.R.layout.activity_donde;

public class DondeActivity extends AppCompatActivity {

    Button btn2;
    Spinner spnBancos;
    final String[] keyBanco = new String[1];
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(activity_donde);
        spnBancos = (Spinner) findViewById(R.id.spnBancos);
        btn2 = (Button) findViewById(R.id.button2);
        llenarSpn();


    }

    private void seleccionSpinner() {
        Toast.makeText(DondeActivity.this, "Seleccion Spinner", Toast.LENGTH_LONG).show();
        final String s = spnBancos.getSelectedItem().toString();
        Toast.makeText(DondeActivity.this, "s " + s, Toast.LENGTH_LONG).show();

        FirebaseDatabase.getInstance()
                .getReference()
                .child("pruebas")
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Toast.makeText(DondeActivity.this, "on data change", Toast.LENGTH_LONG).show();
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            Toast.makeText(DondeActivity.this, "child" + childSnapshot.getKey(), Toast.LENGTH_LONG).show();
                            if (s == childSnapshot.child("nombre").getValue(String.class)) {

                                keyBanco[0] = childSnapshot.getKey();
                            }
                        }


                        // usuarios ahora contiene los nombres de todos los usuarios.
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    private void llenarSpn() {
        final ArrayList<String> listaBancos = new ArrayList<>();
        final ArrayList<String> listaKey = new ArrayList<>();
        listaBancos.add("");

        FirebaseDatabase.getInstance()
                .getReference()
                .child("pruebas")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren())
                            listaBancos.add(childSnapshot.child("nombre").getValue(String.class));


                        // usuarios ahora contiene los nombres de todos los usuarios.
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        Log.d(">>>>>>>>1", String.valueOf(listaBancos));

        spnBancos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaBancos));

    }

    public void btnQuieroTurno(View v) {

        seleccionSpinner();
        Toast.makeText(DondeActivity.this, keyBanco[0], Toast.LENGTH_LONG).show();
        Toast.makeText(DondeActivity.this, "vamos a la sucursal", Toast.LENGTH_LONG).show();
        Intent intt = new Intent(this, SucursalActivity.class);
        intt.putExtra("key", keyBanco[0]);
        startActivity(intt);
      /*  Toast.makeText(DondeActivity.this, "Un momento por favor.", Toast.LENGTH_LONG).show();
        Intent intt = new Intent(this, SucursalActivity.class);
        startActivity(intt);*/

    }

}
