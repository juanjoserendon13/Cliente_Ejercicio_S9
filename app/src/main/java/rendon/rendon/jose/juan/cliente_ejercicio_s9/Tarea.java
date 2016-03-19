package rendon.rendon.jose.juan.cliente_ejercicio_s9;

import android.os.AsyncTask;

/**
 * Created by 1151960975 on 18/03/2016.
 */
import Comunicacion.Comunicacion;

public class Tarea extends AsyncTask<Object, Integer, Object> {

    private Comunicacion com;

    Tarea(){
        com= Comunicacion.getInstance();
    }

    @Override
    protected Object doInBackground(Object... params) {
        com.getInstance().enviar(params[0]);
        return null;
    }
}
