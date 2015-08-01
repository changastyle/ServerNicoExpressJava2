
package serializable;

import java.io.Serializable;

public class Jugada implements Serializable
{
    private String numero;
    private float dineroApostado;
    
    
    public Jugada()
    {
        numero = "vacia" ;
        dineroApostado = 0 ;
    }
    public Jugada(String numero, int dineroApostado)
    {
        this.numero = numero;
        this.dineroApostado = dineroApostado;
    }
    
    //<editor-fold desc="GYS">
    public String getNumero()
    {
        return numero;
    }
    public void setNumero(String numero)
    {
        this.numero = numero;
    }
    public float getDineroApostado()
    {
        return dineroApostado;
    }
    public void setDineroApostado(float dineroApostado)
    {
        this.dineroApostado = dineroApostado;
    }
    //</editor-fold>
    
    public boolean estoyVacia()
    {
        boolean respuesta = false;

        if(numero == "vacia" && dineroApostado == 0.0f)
        {
            respuesta = true;
        }

        return respuesta;
    }
    @Override
    public String toString()
    {
        return "#" + numero;
    }
    
    
}
