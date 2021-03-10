package co.com.registropersonamovil2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.com.registropersonamovil2021.entity.Persona;
import co.com.registropersonamovil2021.persistencia.Connection;
import co.com.registropersonamovil2021.util.ActionBarUtil;

public class MainActivity extends AppCompatActivity {

   private Connection connection;
   private List<Persona> listaPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBarUtil.getInstance(this, false).setToolBar(getString(R.string.listado_persona));
        listaPersonas = new ArrayList<>();
        loadInformation();
    }

    private void loadInformation() {
        listaPersonas =  Connection.getDbMainThread(this).getPersonaDao().findAll();
        if(listaPersonas.isEmpty()){
            Toast.makeText(getApplicationContext(),R.string.sin_infomacion,Toast.LENGTH_LONG).show();
        }
    }


    public void goToRegistroPersona(View view) {
        Intent intent = new Intent(this,RegistroPersonaActivity.class);
        startActivity(intent);
    }
}