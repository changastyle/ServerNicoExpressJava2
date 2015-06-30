package server;

import java.io.ObjectInputStream;

class ThreadRecibidor extends Thread
{
    private ObjectInputStream in ;
    private Object objetoRecibido;
    
    public ThreadRecibidor(ObjectInputStream in)
    {
        this.in = in ;
    }
    public void run()
    {
        try
        {
            System.out.println("IN = " + in.toString());
            Thread.sleep(2000);
            objetoRecibido = in.readObject();
            System.out.println("OBJETO = " +  objetoRecibido);
        } 
        catch (Exception e)
        {
            System.out.println("ERROR: recibiendo :" + e.toString() );
            e.printStackTrace();
        }
    }
    
    
    /*GYS*/

    public ObjectInputStream getIn()
    {
        return in;
    }

    public void setIn(ObjectInputStream in)
    {
        this.in = in;
    }

    public Object getObjetoRecibido()
    {
        return objetoRecibido;
    }

    public void setObjetoRecibido(Object objetoRecibido)
    {
        this.objetoRecibido = objetoRecibido;
    }
    
}
