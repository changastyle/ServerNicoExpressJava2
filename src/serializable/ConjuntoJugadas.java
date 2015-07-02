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
    private ArrayList<Jugada> arrJugadas;
    private int dineroTotalApostado;
    
    public ConjuntoJugadas()
    {
        arrJugadas = new ArrayList<>();
        dineroTotalApostado = 0;
    }
    
    
    /*GYS*/

    public void agregarJugada(Jugada jugada)
    {
        this.getArrJugadas().add(jugada);
    }
    public ArrayList<Jugada> getArrJugadas()
    {
        return arrJugadas;
    }

    public void setArrJugadas(ArrayList<Jugada> arrJugadas)
    {
        this.arrJugadas = arrJugadas;
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
            salida += "\n" +  jugada.toString();
        }
        
        return salida;
    }
    @Override
    public String toString()
    {
        return "ConjuntoJugadas{" + "arrJugadas=" + imprimirArrJugadas() + ", dineroApostado=" + dineroTotalApostado + '}';
    }
    
    
}
