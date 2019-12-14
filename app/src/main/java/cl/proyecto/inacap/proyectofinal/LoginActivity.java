package cl.proyecto.inacap.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import cl.proyecto.inacap.proyectofinal.BaseDeDatos.DatabaseHelper;
import cl.proyecto.inacap.proyectofinal.Validacion.Validacion;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity =LoginActivity.this;
    private EditText textEmail;
    private EditText textPass;
    private Button login;
    private Validacion validacion;
    private DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initListeners();
        initObjects();

    }
    //metodo para iniciar los componentes del xml
    private void initViews(){
        textEmail =(EditText) findViewById(R.id.txt_email);
        textPass =(EditText) findViewById(R.id.txt_pass);
        login =(Button) findViewById(R.id.btn_ingresar);
    }

    //metodo para iniciar los listener
    private void initListeners(){
        login.setOnClickListener(this);
    }

    //metodo para iniciar los objetos para utilizarlos
    private void initObjects(){
        db = new DatabaseHelper(activity);
        validacion = new Validacion(activity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ingresar:
                verificacion();
                break;
        }
    }

    /**
     * metodo para validar los campos y el usuario de la DB
     */
    private void verificacion() {

        if (!validacion.isRellenado(textEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!validacion.isEditTextEmail(textEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!validacion.isRellenado(textPass, getString(R.string.error_message_email))) {
            return;
        }

        if (db.checkUser(textEmail.getText().toString().trim(), textPass.getText().toString().trim())) {


            Intent accountsIntent = new Intent(activity, UnoActivity.class);
            accountsIntent.putExtra("EMAIL", textEmail.getText().toString().trim());
            startActivity(accountsIntent);


        } else {

            Toast.makeText(this,getString(R.string.error_valid_email_password), Toast.LENGTH_LONG).show();
        }
    }






}
