package cl.proyecto.inacap.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import cl.proyecto.inacap.proyectofinal.fragments.LineasFragment;
import cl.proyecto.inacap.proyectofinal.fragments.MapaFragment;
import cl.proyecto.inacap.proyectofinal.fragments.UsuarioFragment;

public class UnoActivity extends AppCompatActivity {

    BottomNavigationView mBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uno);

        showSelectedFragment(new MapaFragment());

        mBottomNavigation = (BottomNavigationView) findViewById(R.id.botonNavigation);

        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.menu_mapa){
                    showSelectedFragment(new MapaFragment());
                }

                if (menuItem.getItemId() == R.id.menu_lineas){
                    showSelectedFragment(new LineasFragment());
                }

                if (menuItem.getItemId() == R.id.menu_perfil){
                    showSelectedFragment(new UsuarioFragment());
                }

                return true;
            }
        });

    }

    private void showSelectedFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }
}
