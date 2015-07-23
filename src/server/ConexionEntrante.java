package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ConexionEntrante extends Thread
{
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ThreadEnviador threadEnviador;
    private ThreadRecibidor threadRecibidor;
    
    public ConexionEntrante(Socket socket)
    {
        this.socket = socket;
    }
    public void run()
    {
        try
        {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } 
        catch (Exception e)
        {
            System.out.println("ERROR:CANALES " + e.toString() );
        }

    }
    public void enviar(Object objetoAEnviar)
    {
        try
        {
            this.join();

            threadEnviador = new ThreadEnviador(out,objetoAEnviar);
            threadEnviador.start();
            threadEnviador.join();
        } catch (Exception e)
        {
            System.out.println("ERROR: OUT NO ESTABLECIDO " + e.toString());
        }
    }
    public Object recibir()
    {
        Object objetoRecibido = null;
        
        threadRecibidor = new ThreadRecibidor(in);
        
        try
        {
            threadRecibidor.start();
            threadRecibidor.join();
            objetoRecibido = threadRecibidor.getObjetoRecibido();
        } 
        catch (InterruptedException ex)
        {
            System.out.println("ERROR: JOIN RECIBIR -> " + ex.toString() );
        }
        
        return objetoRecibido;
    }
    
    /*GYS*/

    public Socket getSocket()
    {
        return socket;
    }

    public void setSocket(Socket socket)
    {
        this.socket = socket;
    }

    public ObjectOutputStream getOut()
    {
        return out;
    }

    public void setOut(ObjectOutputStream out)
    {
        this.out = out;
    }

    public ObjectInputStream getIn()
    {
        return in;
    }

    public void setIn(ObjectInputStream in)
    {
        this.in = in;
    }

    public ThreadEnviador getThreadEnviador()
    {
        return threadEnviador;
    }

    public void setThreadEnviador(ThreadEnviador threadEnviador)
    {
        this.threadEnviador = threadEnviador;
    }

    public ThreadRecibidor getThreadRecibidor()
    {
        return threadRecibidor;
    }

    public void setThreadRecibidor(ThreadRecibidor threadRecibidor)
    {
        this.threadRecibidor = threadRecibidor;
    }

    public String componenteEsNulo(Object componente)
    {
        String respuesta = "";
        
        if (componente != null)
        {
            respuesta += componente.getClass().getName() + ": 1"; 
        }
        else
        {
            respuesta += componente.getClass().getName() + ": 0";
        }
        return respuesta;
    }
    @Override
    public String toString()
    {
        String respuesta = "ConexionEntrante(" + socket.getInetAddress();
        
        respuesta += componenteEsNulo(in);
        respuesta += componenteEsNulo(out);
        respuesta += componenteEsNulo(threadEnviador);
        respuesta += componenteEsNulo(threadRecibidor);
        
        respuesta += ")";
        
    
        return respuesta;
        
    }
    
    
}
