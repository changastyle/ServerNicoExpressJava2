package serializable;

import java.io.Serializable;
import java.util.ArrayList;

public class ConjuntoDevuelto implements Serializable
{
    private ArrayList<RespuestaJugada> arrRespuestasJugada;
    private ArrayList<String> arrNumerosSorteados;
    
    
    
    public ConjuntoDevuelto()
    {
        arrRespuestasJugada = new ArrayList<>();
        arrNumerosSorteados = new ArrayList<>();
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

    public ArrayList<String> getArrNumerosSorteados() {
        return arrNumerosSorteados;
    }

    public void setArrNumerosSorteados(ArrayList<String> arrNumerosSorteados) {
        this.arrNumerosSorteados = arrNumerosSorteados;
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
        for (String s : arrNumerosSorteados)
        {
             salida += "\n" + s;
        }
        
        return salida;
    }
    
    
}
