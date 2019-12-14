package cl.proyecto.inacap.proyectofinal.Validacion;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Validacion {
    private Context context;

    public Validacion(Context context) {
        this.context = context;
    }

    public boolean isRellenado(EditText email, String message) {
        String value = email.getText().toString().trim();
        if (value.isEmpty()) {
            email.setError(message);
            hideKeyboardFrom(email);
            return false;
        }

        return true;
    }

    /**
     * method to check InputEditText has valid email .
     *
     * @param email
     * @param message
     * @return
     */
    public boolean isEditTextEmail(EditText email, String message) {
        String value = email.getText().toString().trim();
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            email.setError(message);
            hideKeyboardFrom(email);
            return false;
        }
        return true;
    }

    public boolean isContraseñas(EditText contraseña, EditText contraseña2, String message) {
        String value1 = contraseña.getText().toString().trim();
        String value2 = contraseña2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            contraseña.setError(message);
            hideKeyboardFrom(contraseña2);
            return false;
        }
        return true;
    }

    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


}
