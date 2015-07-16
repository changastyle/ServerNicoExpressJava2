package serializable;

import java.io.Serializable;
import java.util.ArrayList;

public class ConjuntoDevuelto implements Serializable
{
    private ArrayList<RespuestaJugada> arrRespuestasJugada;
    
    
    
    public ConjuntoDevuelto()
    {
        arrRespuestasJugada = new ArrayList<>();
    }
    
    public void agregarRespuestaJugada(RespuestaJugada respuestaJugada)
    {
        this.arrRespuestasJugada.add(respuestaJugada);
    }
    
    /*GYS*/
    public ArrayList<RespuestaJugada> getArrRespuestasJugada()
    {
        return arrRespuestasJugada;
    }

    public void setArrRespuestasJugada(ArrayList<RespuestaJugada> arrRespuestasJugada)
    {
        this.arrRespuestasJugada = arrRespuestasJugada;
    }  
    public int dineroTotalGanado()
    {
        int dineroTotalGanado = 0;
        
        for (RespuestaJugada respuesta : arrRespuestasJugada)
        {
            dineroTotalGanado += respuesta.getDineroGanadoEnEstaJugada();
        }
        
        return dineroTotalGanado;
    }
    @Override
    public String toString()
    {
        String salida =  "|---------------- ConjuntoDevuelto -----------------|";
        
        salida += "\nDINERO GANADO = $ " + this.dineroTotalGanado();
        salida += "\n|---------------- DETALLADO -----------------|";
        
        for (RespuestaJugada respuesta : arrRespuestasJugada)
        {
            salida += "\n" + respuesta.toString() ;
        }
        
        return salida;
    }
    
    
}
