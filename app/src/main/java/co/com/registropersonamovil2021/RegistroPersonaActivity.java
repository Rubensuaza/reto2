package co.com.registropersonamovil2021;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.registropersonamovil2021.entity.Persona;
import co.com.registropersonamovil2021.persistencia.Connection;
import co.com.registropersonamovil2021.util.ActionBarUtil;

public class RegistroPersonaActivity extends AppCompatActivity {
    @BindView(R.id.txt_documento)
    EditText txtDocumento;

    @BindView(R.id.txt_nombre)
    EditText txtNombre;

    @BindView(R.id.txt_apellido)
    EditText txtApellido;

    Persona persona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_persona);
        ButterKnife.bind(this);
        persona = new Persona();
        ActionBarUtil.getInstance(this, true).setToolBar(getString(R.string.registro_persona), getString(R.string.insertar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            cargarInformacion();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void cargarInformacion() {
        persona.setNumeroDocumentoIdentidad(txtDocumento.getText().toString());
        persona.setNombrePersona(txtNombre.getText().toString());
        persona.setApellidoPersona(txtApellido.getText().toString());
        InsertarInformacion insertarInformacion = new InsertarInformacion();
        insertarInformacion.execute(persona);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    class InsertarInformacion extends AsyncTask<Persona, Void, Void>{

        @Override
        protected Void doInBackground(Persona... personas) {
            Connection.getDb(getApplicationContext()).getPersonaDao().insert(personas[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            finish();
            super.onPostExecute(aVoid);
        }
    }


}