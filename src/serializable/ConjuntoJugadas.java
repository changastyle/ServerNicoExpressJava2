package serializable;

import java.io.Serializable;

public class ConjuntoJugadas implements Serializable
{
    private int maximaCantidadJugadas = 5;
    private Jugada arrJugadas[];
    private float dineroTotalApostado;
    private Tarjeta tarjetaActual;
    
    public ConjuntoJugadas()
    {
        arrJugadas = new Jugada[maximaCantidadJugadas];
        for(int i = 0 ; i < maximaCantidadJugadas ; i++)
        {
            arrJugadas[i] = new Jugada();
        }
        dineroTotalApostado = 0;
        tarjetaActual = new Tarjeta();
    }
     
    //<editor-fold desc="GYS">
    public void agregarJugada(Jugada jugada , int posicion)
    {
        this.arrJugadas[posicion] = jugada;
    }
    public void quitarJugada(int posicion)
    {
        this.arrJugadas[posicion] = null;
    }
    public void remplazarJugada(Jugada jugada , int posicion)
    {
        this.arrJugadas[posicion] = jugada;
    }
    public Jugada[] getArrJugadas()
    {
        return arrJugadas;
    }
    public float getDineroTotalApostado()
    {
        return dineroTotalApostado;
    }
    public void setDineroTotalApostado(float dineroApostado)
    {
        this.dineroTotalApostado = dineroApostado;
    }
    public int getMaximaCantidadJugadas() 
    {
        return maximaCantidadJugadas;
    }
    public void setMaximaCantidadJugadas(int maximaCantidadJugadas) 
    {
        this.maximaCantidadJugadas = maximaCantidadJugadas;
    }
    public void setArrJugadas(Jugada[] arrJugadas) 
    {
        this.arrJugadas = arrJugadas;
    }
    public Tarjeta getTarjetaActual() 
    {
        return tarjetaActual;
    }
    public void setTarjetaActual(Tarjeta tarjetaActual) 
    {
        this.tarjetaActual = tarjetaActual;
    }
    //</editor-fold>
    
    //<editor-fold desc="toString">
    public String imprimirArrJugadas()
    {
        String salida = "";
        
        for (Jugada jugada : arrJugadas)
        {
            if(jugada != null)
            {
                salida += "\n" +  jugada.toString();
            }
            else
            {
                salida += "\n jugada vacia." ;
            }
        }
        
        return salida;
    }
    
    @Override
    public String toString()
    {
        return "ConjuntoJugadas{ TARJETA ID= " + tarjetaActual.toString() +" ->" + "arrJugadas=" + imprimirArrJugadas() + ", dineroApostado=" + dineroTotalApostado + '}';
    }
    //</editor-fold>
}
