package server;

import serializable.ParametrosEncapsuladosParaClientes;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import serializable.Jugada;

public class Server 
{
    private ServerSocket serverSocket;
    private int puerto;
    private ManejadorServer manejadorServer;
    private ParametrosEncapsuladosParaClientes parametrosEncapsuladosParaClientes;
    
    //private ArrayList<Jugada> conjuntoJugadas;
    
    public Server(ManejadorServer manejadorServer)
    {
        this.manejadorServer = manejadorServer;
        this.puerto = manejadorServer.getPuerto();
        this.parametrosEncapsuladosParaClientes = this.manejadorServer.armarArrayConParametrosParaElCliente();
            System.out.println("PARAMETROS  = " +  this.parametrosEncapsuladosParaClientes.toString() );
        
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
                            InetAddress direccionIPEntrante = socketAuxiliar.getInetAddress();
                            String direccionIPEntranteFormateada = formatearIp(direccionIPEntrante);
                            System.out.println("RECIBÍ UNA NUEVA CONEXION ENTRANTE -> DireccionIP:" + direccionIPEntranteFormateada );
                            
                            //ESCRIBO LA CONEXION ENTRANTE EN EL DB:
                            db.DB.conectar();
                            db.DB.insert("INSERT INTO `serverLoteria`.`conexionesEntrantes`  VALUES (NULL, '" + direccionIPEntranteFormateada + "', CURRENT_TIMESTAMP);");
                            
                            //ESTABLESCO CANAL DE ENTRADA Y ESPERO A QUE ESTE LISTO PARA RECIBIR DATOS:
                            ConexionEntrante  conexionEntrante = new ConexionEntrante(socketAuxiliar);
                            conexionEntrante.start();
                            conexionEntrante.join();
                            
                            //ENVIO PARAMETROS DE CONFIGURACION AL CLIENTE:
                            conexionEntrante.enviar(parametrosEncapsuladosParaClientes);
                            

                            //ESPERA HASTA QUE LOS CANALES IN/OUT DE CONEXION ENTRANTE ESTEN ESTABLECIDOS:
                            //ManejadorServer.doWhatYouHaveToDoWithNewConexionEntrante(conexionEntrante);

                            //conjuntoJugadas =   (ArrayList<Jugada>) conexionEntrante.recibir();
                        }
            }

        } 
        catch (Exception e)
        {
            System.out.println("ERROR:" +  e.toString() );
        }
    }

        Server(ManejadorServer aThis, ParametrosEncapsuladosParaClientes parametrosEncapsuladosParaClientes) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private String formatearIp(InetAddress direccionIPEntrante) 
        {
                String direccionIPFormateada = "";
                
                direccionIPFormateada = direccionIPEntrante.toString().substring(1, direccionIPEntrante.toString().length());
                // System.out.println("dir ip formateada = " + direccionIPFormateada);
                
                return direccionIPFormateada;
        }
    
}
