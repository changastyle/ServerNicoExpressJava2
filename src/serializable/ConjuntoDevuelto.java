package serializable;

import java.io.Serializable;
import java.util.ArrayList;

public class ConjuntoDevuelto implements Serializable
{
    private ArrayList<RespuestaJugada> arrRespuestasJugada;
    private ArrayList<String> arrNumerosSorteados;



    public ConjuntoDevuelto()
    {
        arrRespuestasJugada = new ArrayList<RespuestaJugada>();
        arrNumerosSorteados = new ArrayList<String>();
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
        ArrayList<String> arrImpresor = new ArrayList<String>();
        arrImpresor.add("ConjuntoDevuelto");
        arrImpresor.add("Total Ganado: $ " + this.dineroTotalGanado());  
        
        arrImpresor.add("");
        arrImpresor.add("RESPUESTAS");
        arrImpresor.add("");
        
        for (RespuestaJugada respuesta : arrRespuestasJugada)
        {
            arrImpresor.add(respuesta.toString());
        }
        arrImpresor.add("");
        arrImpresor.add("SALIERON SORTEADOS:");
        arrImpresor.add("");
        
        for (String s : arrNumerosSorteados)
        {
            arrImpresor.add(s);
        }
        String salida = ImpresorFormateadoConsola.ImpresorConsola.imprimirFormateado(arrImpresor);

        return salida;
    }


}
