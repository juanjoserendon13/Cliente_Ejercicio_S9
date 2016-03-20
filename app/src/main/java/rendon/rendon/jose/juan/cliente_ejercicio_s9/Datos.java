package rendon.rendon.jose.juan.cliente_ejercicio_s9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import Comunicacion.Comunicacion;
import Comunicacion.Mensaje;

public class Datos extends Activity implements Observer {

    TextView nombre, carrera,edad;
    Tarea nuevaTarea;
    Mensaje msj;
    Comunicacion com;
    String usuarioLogin,carreraDato,edadDato;
    String tipo;



   /*
    protected void onPause(){
        Comunicacion.getInstance().deleteObserver(this);
        super.onPause();
    }
    protected void onResume(){
        Comunicacion.getInstance().addObserver(this);
        super.onResume();
    }*/

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        Intent launch = getIntent();

        usuarioLogin = launch.getStringExtra("nombreUser");
           //String usuarioLogin="Juan";
        nuevaTarea= new Tarea();
        com=Comunicacion.getInstance();
        com.addObserver(this);

        nombre = (TextView) findViewById(R.id.usuarioDash_tv);
        carrera = (TextView) findViewById(R.id.carreraDash_tv);
        edad = (TextView) findViewById(R.id.edadDash_tv);
           runOnUiThread(new Runnable() {
               @Override
               public void run() {
                   nombre.setText(usuarioLogin);

               }
           });


           ////////////


           msj = new Mensaje("datos", nombre.getText().toString(), "", "", "");
           nuevaTarea = new Tarea();
               nuevaTarea.execute(msj);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_datos, menu);
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
        carreraDato=((Comunicacion)observable).getCarreraDato();
        edadDato=((Comunicacion)observable).getEdad();
        // tipo=((Comunicacion)observable).getTipo();
        System.out.println("carrera del usuario" + " " + ((Comunicacion) observable).getCarreraDato());
        System.out.println("edad del usuario" + " " + ((Comunicacion) observable).getEdad());

      //  carrera.setText(((Comunicacion) observable).getCarreraDato());
       // edad.setText(((Comunicacion) observable).getEdad());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                carrera.setText(carreraDato);
                edad.setText(edadDato);

                // carrera.setText(carreraDato);
               // edad.setText(edadDato);

            }
        });


    }
}
