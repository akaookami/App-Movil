package com.example.anger.pruebafirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TipoServicioActivity extends AppCompatActivity {

    String idSucursal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idSucursal= getIntent().getExtras().getString("id","No existe");
        setContentView(R.layout.activity_tipo_servicio);
    }

    public void metodoNegocio(View view) {
        Intent intt = new Intent (this,  DetalleTurnoNegocioActivity.class);
        intt.putExtra("id",idSucursal);
        startActivity(intt);
    }

    public void metodoCaja(View view) {

        Intent intt = new Intent (this,  DetalleTurnoCajaActivity.class);
        intt.putExtra("id",idSucursal);
        startActivity(intt);
    }
}
