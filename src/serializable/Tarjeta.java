package serializable;

import java.io.Serializable;

/**
 * Created by nicolas on 19/07/15.
 */
public class Tarjeta implements Serializable
{
    private int ID;
    private String serial;
    private float saldo;

    public Tarjeta()
    {
        ID = 0;
        serial = "0" ;
        saldo = 0.0f;
    }
    public Tarjeta(int ID , String serial , float saldo)
    {
        this.ID = ID;
        this.serial = serial ;
        this.saldo = saldo;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getSerial()
    {
        return serial;
    }

    public void setSerial(String serial)
    {
        this.serial = serial;
    }

    public float getSaldo()
    {
        return saldo;
    }

    public void setSaldo(float dinero)
    {
        this.saldo = dinero;
    }

    public boolean estaVacia()
    {
        boolean respuesta = false;

        if(ID == 0 )
        {
            respuesta = true;
        }

        return respuesta;
    }
    @Override
    public String toString()
    {
        return "Tarjeta{" + "ID = " + ID + ", serial = " + serial + ", saldo = " + saldo + '}';
    }

}
