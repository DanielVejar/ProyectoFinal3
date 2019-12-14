package cl.proyecto.inacap.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import cl.proyecto.inacap.proyectofinal.BaseDeDatos.DatabaseHelper;
import cl.proyecto.inacap.proyectofinal.Clases.Usuario;
import cl.proyecto.inacap.proyectofinal.Validacion.Validacion;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegistrarActivity.this;
    private EditText nombre;
    private EditText email;
    private EditText contraseña;
    private EditText contraseña2;
    private Button registrar;
    private Validacion validacion;
    private DatabaseHelper db;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        initViews();
        initListeners();
        initObjects();

    }

    private void initViews(){
        nombre= (EditText) findViewById(R.id.txt_nombre);
        email= (EditText) findViewById(R.id.txt_correo);
        contraseña= (EditText) findViewById(R.id.txt_contraseña);
        contraseña2= (EditText) findViewById(R.id.txt_contraseña2);
        registrar= (Button) findViewById(R.id.btn_regis);
    }

    private void initListeners(){
        registrar.setOnClickListener(this);
    }

    private void initObjects(){
        validacion= new Validacion(activity);
        db= new DatabaseHelper(activity);
        usuario= new Usuario();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_regis:
                postDataToSQLite();
                break;
        }
    }

    /**
     * valida los campos y la info de la bd
     */
    private void postDataToSQLite() {
        if (!validacion.isRellenado(nombre, getString(R.string.error_message_name))) {
            return;
        }
        if (!validacion.isRellenado(email, getString(R.string.error_message_email))) {
            return;
        }
        if (!validacion.isEditTextEmail(email, getString(R.string.error_message_email))) {
            return;
        }
        if (!validacion.isRellenado(contraseña, getString(R.string.error_message_password))) {
            return;
        }
        if (!validacion.isContraseñas(contraseña, contraseña2, getString(R.string.error_password_match))) {
            return;
        }

        if (!db.checkUsuarioLogin(email.getText().toString().trim())) {

            usuario.setName(nombre.getText().toString().trim());
            usuario.setEmail(email.getText().toString().trim());
            usuario.setPassword(contraseña.getText().toString().trim());

            db.agregarUsuario(usuario);

            Toast.makeText(this, getString(R.string.success_message), Toast.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            Toast.makeText(this, getString(R.string.error_email_exists), Toast.LENGTH_LONG).show();
        }


    }

    /**
     * Metodo para borrar los campos
     */
    private void emptyInputEditText() {
        nombre.setText(null);
        email.setText(null);
        contraseña.setText(null);
        contraseña2.setText(null);
    }
}
