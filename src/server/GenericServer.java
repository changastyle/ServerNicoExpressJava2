package server;

import serializable.ParametrosEncapsuladosParaClientes;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import serializable.Jugada;
import serializable.Tarjeta;

public class GenericServer 
{
    private ServerSocket serverSocket;
    private int puerto;
    private ParametrosEncapsuladosParaClientes parametrosEncapsuladosParaClientes;

    //ESCUCHA PETICIONES EN MULTI-THREAD Y LE PASA UN OBJ CONEXION ENTRANTE AL MANEJADOR, PARA QUE LA MANEJE.
    public GenericServer(ParametrosEncapsuladosParaClientes parametrosEncapsuladosParaClientes)
    {
        this.parametrosEncapsuladosParaClientes = parametrosEncapsuladosParaClientes;
        this.puerto = Integer.parseInt( parametrosEncapsuladosParaClientes.getParametro("puerto").getValor()    );
        
        //INICIO SERVER:
        try
        {
            serverSocket = new ServerSocket(puerto);
            ArrayList<String> arrImpresor = new ArrayList<String>();
            arrImpresor.add("SERVER INICIADO:");
            arrImpresor.add("Server escuchando en puerto: " + puerto);
            
            System.out.println("" + ImpresorFormateadoConsola.ImpresorConsola.imprimirFormateado(arrImpresor));
            
            
            //ACEPTO CONEXIONES ENTRANTES DE CLIENTES Y LE PASO LA BOLA AL MANEJADOR:
            while (true)
            {
                Socket socketAuxiliar = serverSocket.accept();

                if(socketAuxiliar != null)
                {
                    ConexionEntrante conexionEntrante = new ConexionEntrante(socketAuxiliar);
                    ManejadorServer.recibirNuevaConexionEntrante(conexionEntrante);
                }
            }

        } 
        catch (Exception e)
        {
            System.out.println("ERROR:" +  e.toString() );
        }
    }

    private String formatearIp(InetAddress direccionIPEntrante) 
    {
            String direccionIPFormateada = "";

            direccionIPFormateada = direccionIPEntrante.toString().substring(1, direccionIPEntrante.toString().length());
            // System.out.println("dir ip formateada = " + direccionIPFormateada);

            return direccionIPFormateada;
    }
    
}
