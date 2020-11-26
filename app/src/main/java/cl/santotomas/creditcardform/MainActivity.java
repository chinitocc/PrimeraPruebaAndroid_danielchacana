package cl.santotomas.creditcardform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_nombre, et_apellido, et_tarjeta, et_mes, et_ano, et_codigo, et_calle, et_ciudad, et_estado, et_postal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nombre = (EditText) findViewById(R.id.txt_nombre);
        et_apellido = (EditText) findViewById(R.id.txt_apellido);
        et_tarjeta = (EditText) findViewById(R.id.txt_tarjeta);
        et_mes = (EditText) findViewById(R.id.txt_mes);
        et_ano = (EditText) findViewById(R.id.txt_ano);
        et_codigo = (EditText) findViewById(R.id.txt_codigo);
        et_calle = (EditText) findViewById(R.id.txt_calle);
        et_ciudad = (EditText) findViewById(R.id.txt_ciudad);
        et_estado = (EditText) findViewById(R.id.txt_estado);
        et_postal = (EditText) findViewById(R.id.txt_codigopostal);
    }

    public void Registrar (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombre = et_nombre.getText().toString();
        String apellido = et_apellido.getText().toString();
        String tarjeta = et_tarjeta.getText().toString();
        String mes = et_mes.getText().toString();
        String ano = et_ano.getText().toString();
        String codigo = et_codigo.getText().toString();
        String calle = et_calle.getText().toString();
        String ciudad = et_ciudad.getText().toString();
        String estado = et_estado.getText().toString();
        String postal = et_postal.getText().toString();

        if (!nombre.isEmpty() && !apellido.isEmpty() && !tarjeta.isEmpty() && !mes.isEmpty() && !ano.isEmpty() && !codigo.isEmpty() && !calle.isEmpty() && !ciudad.isEmpty() && !estado.isEmpty() && !postal.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("nombre", nombre);
            registro.put("apellido", apellido);
            registro.put("tarjeta", tarjeta);
            registro.put("mes", mes);
            registro.put("ano", ano);
            registro.put("codigo", codigo);
            registro.put("calle", calle);
            registro.put("ciudad", ciudad);
            registro.put("estado", estado);
            registro.put("postal", postal);

            BaseDeDatos.insert("clientes", null, registro);
            BaseDeDatos.close();

            et_nombre.setText("");
            et_apellido.setText("");
            et_tarjeta.setText("");
            et_mes.setText("");
            et_ano.setText("");
            et_codigo.setText("");
            et_calle.setText("");
            et_ciudad.setText("");
            et_estado.setText("");
            et_postal.setText("");

            Toast.makeText(this, "Metodo de Pago Ingresado", Toast.LENGTH_SHORT).show();


        } else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    public void Buscar (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null,1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String tarjeta = et_tarjeta.getText().toString();

        if (!tarjeta.isEmpty()){
            Cursor fila = BaseDeDatabase.rawQuery
                    ("select nombre, apellido, mes, ano, codigo, calle, ciudad, estado, postal from articulos where codigo =" + tarjeta, null);
            if (fila.moveToFirst()){
                et_nombre.setText(fila.getString(0));
                et_apellido.setText(fila.getString(1));
                et_mes.setText(fila.getString(2));
                et_ano.setText(fila.getString(3));
                et_codigo.setText(fila.getString(4));
                et_calle.setText(fila.getString(5));
                et_ciudad.setText(fila.getString(6));
                et_estado.setText(fila.getString(7));
                et_postal.setText(fila.getString(8));

                BaseDeDatabase.close();
            } else{
                Toast.makeText(this, "No existe el usuario", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            }

        } else{
            Toast.makeText(this, "Debes introducir el numero de tarjeta", Toast.LENGTH_SHORT).show();
        }
    }
}