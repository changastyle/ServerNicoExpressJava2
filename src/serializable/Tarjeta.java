package serializable;

import java.io.Serializable;

/**
 * Created by nicolas on 19/07/15.
 */
public class Tarjeta implements Serializable
{
    private String ID;
    private int saldo;

    public Tarjeta()
    {
        ID = "000000000000";
        saldo = 0 ;
    }
    public Tarjeta(String ID, int saldo)
    {
        this.ID = ID;
        this.saldo = saldo;
    }
    public boolean tengoSaldo(int cuanto)
    {
        boolean respuesta = false;
        if( this.saldo >= cuanto )
        {
            respuesta = true;
        }

        return respuesta;
    }

    /*GYS*/
    public int getSaldo()
    {
        return saldo;
    }
    public void setSaldo(int saldo)
    {
        this.saldo = saldo;
    }
    public String getID()
    {
        return ID;
    }
    public void setID(String ID)
    {
        this.ID = ID;
    }
    @Override
    public String toString()
    {
        return "TarjetaID:"+ ID + " -> $" + saldo;
    }
}
