
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
