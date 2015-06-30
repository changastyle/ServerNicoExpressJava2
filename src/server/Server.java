package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import serializable.Jugada;

public class Server 
{
    private ServerSocket serverSocket;
    private int puerto;
    private ManejadorServer manejadorServer;
    //private ArrayList<Jugada> conjuntoJugadas;
    
    public Server(ManejadorServer manejadorServer)
    {
        this.manejadorServer = manejadorServer;
        this.puerto = manejadorServer.getPuerto();

        
        //INICIO SERVER:
        try
        {
            serverSocket = new ServerSocket(puerto);
            System.out.println("|------------ SERVER INICIADO: ------------|");
            System.out.println("|   Server escuchando en puerto: " + puerto + "      |");
            System.out.println("|------------------------------------------|");
            
            
            //ACEPTO CONEXIONES ENTRANTES DE CLIENTES:
            while (true)
            {
                Socket socketAuxiliar = serverSocket.accept();
                if(socketAuxiliar != null)
                {
                    System.out.println("RECIBÍ UNA NUEVA CONEXION ENTRANTE -> DireccionIP:" + socketAuxiliar.getInetAddress());
                    ConexionEntrante  conexionEntrante = new ConexionEntrante(socketAuxiliar);
                    conexionEntrante.start();
                    conexionEntrante.join();
                    
                    //ESPERA HASTA QUE LOS CANALES IN/OUT DE CONEXION ENTRANTE ESTEN ESTABLECIDOS:
                    ManejadorServer.doWhatYouHaveToDoWithNewConexionEntrante(conexionEntrante);
                    
                    //conjuntoJugadas =   (ArrayList<Jugada>) conexionEntrante.recibir();
                }
            }

        } 
        catch (Exception e)
        {
            System.out.println("ERROR:" +  e.toString() );
        }
    }
    
}
