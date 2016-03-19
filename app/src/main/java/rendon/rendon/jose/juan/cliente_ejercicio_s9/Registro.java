package rendon.rendon.jose.juan.cliente_ejercicio_s9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Comunicacion.Comunicacion;
import Comunicacion.Mensaje;

public class Registro extends Activity {

    TextView nombre, contra, confiContra,carrera,edad;
    Button enviarReg;
    Tarea nuevaTarea;
    Mensaje msj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = (TextView) findViewById(R.id.nombre_tv);
        contra = (TextView) findViewById(R.id.contrasena_tv);
        confiContra = (TextView) findViewById(R.id.repetirContra_tv);

        carrera = (TextView) findViewById(R.id.carrera_tv);
        edad = (TextView) findViewById(R.id.edad_tv);

        enviarReg = (Button) findViewById(R.id.continuar_btn);

        enviarReg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (contra.getText().toString().equals(confiContra.getText().toString())) {
                    msj = new Mensaje("Registro", nombre.getText().toString(), contra.getText().toString(), carrera.getText().toString(), edad.getText().toString());
                    nuevaTarea = new Tarea();
                    nuevaTarea.execute(msj);
                    //-------
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        }
                    });

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
