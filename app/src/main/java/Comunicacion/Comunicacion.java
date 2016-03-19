package Comunicacion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Observable;

/**
 * Created by 1151960975 on 18/03/2016.
 */
public class Comunicacion extends Observable implements Runnable  {

    private static final Comunicacion miClase = new Comunicacion();

    private int puerto;
    private InetAddress ip;
    private DatagramSocket socket;
    private Boolean ingresar;



    String respuesta;
    Thread hilo;

    private Comunicacion() {

        hilo= new Thread(this);
        hilo.start();
        ingresar = false;

        puerto = 5100;

        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }


        try {
            ip = InetAddress.getByName("10.0.2.2");
            //ip = InetAddress.getByName("192.168.1.14");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
    public static Comunicacion getInstance() {
        return miClase;
    }


    @Override
    public void run() {
        while (true){

            try {
                hilo.sleep(50);
                recibir();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void recibir(){
        byte[] buzon = new byte[1024];
        try {
            DatagramPacket pack = new DatagramPacket(buzon, buzon.length);
            System.out.println("esperando");
            socket.receive(pack);

            System.out.println("recibi");
            //  ingresar = Boolean.parseBoolean(pack.getData().toString());
            respuesta= new String(pack.getData(),0,pack.getLength());

            if (respuesta.equals("true")){
                ingresar=true;
                setChanged();
                notifyObservers();
                System.out.println("respuesta : " + ingresar);
            }
            if (respuesta.equals("false")){
                setChanged();
                notifyObservers();
                System.out.println("respuesta : " + ingresar);
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void enviar(Object msj){
        byte[] datos = objectByte(msj);
        DatagramPacket enviar = new DatagramPacket(datos, datos.length, ip, puerto);
        try {
            socket.send(enviar);
            System.out.println("Si lo mande");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public byte[] objectByte(Object param){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try {
            ObjectOutputStream os = new ObjectOutputStream(bytes);
            os.writeObject(param);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes.toByteArray();
    }

    public String getRespuesta() {
        return respuesta;
    }

    public Boolean getIngresar() {
        return ingresar;
    }

}
