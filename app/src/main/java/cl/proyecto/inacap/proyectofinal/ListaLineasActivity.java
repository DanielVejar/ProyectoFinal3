package cl.proyecto.inacap.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import cl.proyecto.inacap.proyectofinal.BaseDeDatos.DBLineas;
import cl.proyecto.inacap.proyectofinal.Clases.Lineas;
import cl.proyecto.inacap.proyectofinal.fragments.LineasFragment;

public class ListaLineasActivity extends ListActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        cargarLista();
    }

    private void cargarLista() {

        lista=getListView();

        DBLineas helper=new DBLineas(this);
        List<Lineas> productoList=helper.listaProductos();

        //List<Producto> productoList= ListDeCompras.getInstancia().getListaDeCompras();
        ArrayAdapter<Lineas> listaAdapter= new ArrayAdapter<Lineas>(this,
                android.R.layout.simple_expandable_list_item_1,productoList);
        lista.setAdapter(listaAdapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int posicion, long id) {
        Object o=lista.getItemAtPosition(posicion); //item seleccionado
        String linea=o.toString(); //El texto del item seleccionado
        //Obtener el nombre
        String[] separar=linea.split(":");
        //Llamar a DetallesActivity


        Intent intent=new Intent(ListaLineasActivity.this, LineasFragment.class);

        intent.putExtra("numeroLinea",separar[0]);

        //intent.putExtra("idProducto",(int)id);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                cargarLista();
            }
        }
    }
}
