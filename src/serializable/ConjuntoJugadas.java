/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author NICOLAS
 */
public class ConjuntoJugadas implements Serializable
{
    private Jugada arrJugadas[];
    private int dineroTotalApostado;
    
    public ConjuntoJugadas()
    {
        arrJugadas = new Jugada[5];
        dineroTotalApostado = 0;
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
    @Override
    public String toString()
    {
        return "ConjuntoJugadas{" + "arrJugadas=" + imprimirArrJugadas() + ", dineroApostado=" + dineroTotalApostado + '}';
    }
}
