package serializable;

import java.io.Serializable;

public class RespuestaJugada implements Serializable
{
    private Jugada jugadaRealizada;
    private float dineroGanadoEnEstaJugada;
    
    public RespuestaJugada(Jugada jugadaRealizada)
    {
        this.jugadaRealizada = jugadaRealizada;
        dineroGanadoEnEstaJugada = 0.0f ;
    }
    
    //<editor-fold desc="GYS">
    public Jugada getJugadaRealizada()
    {
        return jugadaRealizada;
    }
    public void setJugadaRealizada(Jugada jugadaRealizada)
    {
        this.jugadaRealizada = jugadaRealizada;
    }
    public float getDineroGanadoEnEstaJugada()
    {
        return dineroGanadoEnEstaJugada;
    }
    public void setDineroGanadoEnEstaJugada(float dineroGanadoEnEstaJugada)
    {
        this.dineroGanadoEnEstaJugada = dineroGanadoEnEstaJugada;
    }
    //</editor-fold>
    
    @Override
    public String toString()
    {
        return "RespuestaJugada{" +  jugadaRealizada.toString() + ", dineroGanadoEnEstaJugada=" + dineroGanadoEnEstaJugada + '}';
    }
}
