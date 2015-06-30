/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializable;

import java.io.Serializable;

public class RespuestaJugada implements Serializable
{
    private Jugada jugadaRealizada;
    private int dineroGanadoEnEstaJugada;
    
    public RespuestaJugada(Jugada jugadaRealizada)
    {
        this.jugadaRealizada = jugadaRealizada;
        dineroGanadoEnEstaJugada = 0 ;
    }
    
    /*GYS*/

    public Jugada getJugadaRealizada()
    {
        return jugadaRealizada;
    }

    public void setJugadaRealizada(Jugada jugadaRealizada)
    {
        this.jugadaRealizada = jugadaRealizada;
    }

    public int getDineroGanadoEnEstaJugada()
    {
        return dineroGanadoEnEstaJugada;
    }

    public void setDineroGanadoEnEstaJugada(int dineroGanadoEnEstaJugada)
    {
        this.dineroGanadoEnEstaJugada = dineroGanadoEnEstaJugada;
    }

    @Override
    public String toString()
    {
        return "RespuestaJugada{" +  jugadaRealizada.toString() + ", dineroGanadoEnEstaJugada=" + dineroGanadoEnEstaJugada + '}';
    }
    
    
    
}
