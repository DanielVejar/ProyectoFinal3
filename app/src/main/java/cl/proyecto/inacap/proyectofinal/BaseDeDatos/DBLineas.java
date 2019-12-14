package cl.proyecto.inacap.proyectofinal.BaseDeDatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.proyecto.inacap.proyectofinal.Clases.Lineas;

public class DBLineas extends SQLiteOpenHelper {


    public DBLineas(Context context) {
        super(context, "Lista", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTxt="create table LINEAS("
                +"_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"ID TEXT, "
                +"LINEA TEXT);";
        db.execSQL(sqlTxt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
/*
    public ArrayList llenar_lv(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM Lineas";
        Cursor registros = database.rawQuery(q,null);
        if(registros.moveToFirst()){
            do{
                lista.add(registros.getString(1));
            }while(registros.moveToNext());
        }
        return lista;

    }

 */

    public List<Lineas> listaProductos()
    {
        List<Lineas> productos=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query("LINEAS",
                new String[]{"ID","LINEA"},
                null,null,null,null,
                null);
        cursor.moveToFirst();

        do{
            productos.add(new Lineas(cursor.getString(0), cursor.getString(1)));
        }while(cursor.moveToNext());
        cursor.close();
        db.close();

        return productos;
    }
}
