package server;

import XMLAPI.XMLHandler;
import java.util.ArrayList;
import serializable.Jugada;
import serializable.*;

class ManejadorServer
{
        //private final String rutaArchivoConfiguracion = "config.xml";
    
        //ASIGNACION DE PARAMETROS:
        private int puerto = XMLHandler.leerInt("puerto");
        private final int importeMinimoPorApuesta = XMLHandler.leerInt("importeMinimoPorApuesta");
        private final int importeMaximoPorApuesta = XMLHandler.leerInt("importeMaximoPorApuesta");
        private final int importePorDefault = XMLHandler.leerInt("importePorDefault");
        private final int alcanze = XMLHandler.leerInt("alcanze");
        private final int importeMaximoDiario = XMLHandler.leerInt("importeMaximoDiario");
        private final int gananciaUNDigito = XMLHandler.leerInt("gananciaUNDigito");
        private final int gananciaDOSDigitos = XMLHandler.leerInt("gananciaDOSDigitos");
        private final int gananciaTRESDigitos= XMLHandler.leerInt("gananciaTRESDigitos");
    
    public ManejadorServer()
    {
            //CREO UN OBJETO SERVER GENERICO:
            Server server = new Server(this);
    }
    
    //LOGICA DE ESTA APLICACION EN PARTICULAR:
    static void doWhatYouHaveToDoWithNewConexionEntrante(ConexionEntrante conexionEntrante)
    {
        ConjuntoJugadas conjuntoJugadasRecibidas = (ConjuntoJugadas) conexionEntrante.recibir();
        
        System.out.println("RECIBÍ " + conjuntoJugadasRecibidas.getArrJugadas().size() + " JUGADAS.");
        
        int numeroGanador = (int) (Math.random() * 1000);
        String strNumeroGanador = "" +  numeroGanador;
        
        System.out.println("NUMERO GANADOR = " + numeroGanador );
        System.out.println("STRNUMEROGANADOR = " + strNumeroGanador);
        
        int dineroGanado = 0;
        ConjuntoDevuelto conjuntoDevuelto = new ConjuntoDevuelto();
        conjuntoDevuelto.setNumeroGanador(numeroGanador);
        
        for (Jugada j : conjuntoJugadasRecibidas.getArrJugadas())
        {
            RespuestaJugada respuestaJugada = new RespuestaJugada(j);
            
            if(strNumeroGanador.endsWith( j.getNumero() ))
            {
                double longitudNumeroJugado = (double) j.getNumero().length();
                double diez = (double) 10;
                
                //TODO : TABLA CON VALORES DE UN ARCHIVO DE CONFIGURACION.
                int multiplicando = (int) Math.pow(longitudNumeroJugado, diez );
                
                dineroGanado = ( conjuntoJugadasRecibidas.getDineroApostado() * 7 * multiplicando ) / conjuntoJugadasRecibidas.getArrJugadas().size();
                System.out.println("DEBUG: LENGTH NUMERO APOSTADO = " + j.getNumero().length());
            }
            else
            {
                dineroGanado = 0;
            }
            
            System.out.println("DINERO GANADO EN ESTA JUGADA = " + dineroGanado);
            respuestaJugada.setDineroGanadoEnEstaJugada(dineroGanado);
            conjuntoDevuelto.agregarRespuestaJugada(respuestaJugada);
        }
        
        System.out.println("CONJUNTO DEVUELTO = " + conjuntoDevuelto.toString());
        
        conexionEntrante.enviar(conjuntoDevuelto);
    }
    
    
    /*GYS*/

    public int getPuerto()
    {
        return puerto;
    }
    public void setPuerto(int puerto)
    {
        this.puerto = puerto;
    }
}
