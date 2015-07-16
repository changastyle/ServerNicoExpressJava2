
package serializable;

import java.io.Serializable;

public class Jugada implements Serializable
{
    private String numero;
    private int dineroApostado;
    
    
    public Jugada()
    {
        numero = "0" ;
        dineroApostado = 0 ;
    }
    public Jugada(String numero, int dineroApostado)
    {
        this.numero = numero;
        this.dineroApostado = dineroApostado;
    }
    
    /*GYS*/

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    public int getDineroApostado()
    {
        return dineroApostado;
    }

    public void setDineroApostado(int dineroApostado)
    {
        this.dineroApostado = dineroApostado;
    }

    @Override
    public String toString()
    {
        return "#" + numero;
    }
    
    
}
