package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class ThreadEnviador extends Thread
{
    public ObjectOutputStream out;
    private Object object;
    
    public ThreadEnviador(ObjectOutputStream out,Object object)
    {
        this.out = out ;
        this.object = object;
    }
    public void run()
    {
        try
        {
            out.writeObject(object);
        }
        catch (Exception e)
        {
            System.out.println("ERROR: Al enviar " + object.toString() );
        }
    }
}
