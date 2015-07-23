package server;

import java.util.ArrayList;
import serializable.Tarjeta;

public class MainServer
{
    public static void main(String[] args)
    {
        
        db.DB.conectar();
        
        db.DB.mapear("SELECT * FROM `tarjetas`",Tarjeta.class);
        /*ArrayList<Object> arrTarjetasFromDB = db.DB.query("SELECT * FROM  `tarjetas` WHERE  `serial` LIKE  '" + 4545878 +"'");
        for(Object o : arrTarjetasFromDB)
        {
            System.out.println("" + o.toString());
            Tarjeta t = (Tarjeta) o;
        }
        //ManejadorServer manejadorServer = new ManejadorServer();      */  
    }
}
