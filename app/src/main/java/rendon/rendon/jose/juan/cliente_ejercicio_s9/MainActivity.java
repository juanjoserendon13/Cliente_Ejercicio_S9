package rendon.rendon.jose.juan.cliente_ejercicio_s9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import Comunicacion.*;
import Comunicacion.Comunicacion;

public class MainActivity extends Activity implements Observer {

    Button registrar, ingresar;
    TextView nombre, contra,feedB;
    Tarea nuevaTarea;
    Mensaje msj;
    Comunicacion com;
    Boolean ingreso=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nuevaTarea= new Tarea();
        com=Comunicacion.getInstance();
        com.addObserver(this);
        nombre = (TextView) findViewById(R.id.userName_tv);
        contra = (TextView) findViewById(R.id.contraseña_tv);
       // feedB = (TextView) findViewById(R.id.feedBackUser);


        registrar = (Button) findViewById(R.id.registro_btn);
        ingresar = (Button) findViewById(R.id.login_btn);

        registrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registro.class);
                startActivity(intent);
            }
        });

                //-------------------------------

                ingresar.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        msj = new Mensaje("Ingreso", nombre.getText().toString(), contra.getText().toString(),"prueba","prueba");
                        nuevaTarea = new Tarea();
                        nuevaTarea.execute(msj);



                    }
                });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void update(Observable observable, Object data) {
        if (((Comunicacion)observable).getIngresar()==true ) {
            System.out.println("Entro");
            ingreso=((Comunicacion)observable).getIngresar();
          //  Intent i = new Intent(getApplicationContext(), Datos.class);
          //  startActivity(i);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), Datos.class);
                    startActivity(i);
                }
            });

        }

    }
}
