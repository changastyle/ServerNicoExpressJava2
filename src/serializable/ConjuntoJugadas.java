/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializable;

import java.io.Serializable;

/**
 *
 * @author NICOLAS
 */
public class ConjuntoJugadas implements Serializable
{
    private int maximaCantidadJugadas = 5;
    private Jugada arrJugadas[];
    private int dineroTotalApostado;
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
    
    
    /*GYS*/

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
    public int getDineroTotalApostado()
    {
        return dineroTotalApostado;
    }

    public void setDineroTotalApostado(int dineroApostado)
    {
        this.dineroTotalApostado = dineroApostado;
    }
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

    public int getMaximaCantidadJugadas() {
        return maximaCantidadJugadas;
    }

    public void setMaximaCantidadJugadas(int maximaCantidadJugadas) {
        this.maximaCantidadJugadas = maximaCantidadJugadas;
    }

    public void setArrJugadas(Jugada[] arrJugadas) {
        this.arrJugadas = arrJugadas;
    }

    public Tarjeta getTarjetaActual() {
        return tarjetaActual;
    }

    public void setTarjetaActual(Tarjeta tarjetaActual) {
        this.tarjetaActual = tarjetaActual;
    }

    @Override
    public String toString()
    {
        return "ConjuntoJugadas{ TARJETA ID= " + tarjetaActual.toString() +" ->" + "arrJugadas=" + imprimirArrJugadas() + ", dineroApostado=" + dineroTotalApostado + '}';
    }
}
