package server;

import serializable.ClaveValor;
import serializable.ParametrosEncapsuladosParaClientes;
import XMLAPI.XMLHandler;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import serializable.Jugada;
import serializable.*;

class ManejadorServer
{
    //<editor-fold desc="ASIGNACION DE PARAMETROS:">
    //private final String rutaArchivoConfiguracion = "config.xml";
    private static int puerto = XMLHandler.leerInt("puerto");
    private final static int importeMinimoPorApuesta = XMLHandler.leerInt("importeMinimoPorApuesta");
    private final static int importeMaximoPorApuesta = XMLHandler.leerInt("importeMaximoPorApuesta");
    private final static int importePorDefault = XMLHandler.leerInt("importePorDefault");
    private final static int alcanze = XMLHandler.leerInt("alcanze");
    private final static int importeMaximoDiario = XMLHandler.leerInt("importeMaximoDiario");
    private final static int gananciaUNDigito = XMLHandler.leerInt("gananciaUNDigito");
    private final static int gananciaDOSDigitos = XMLHandler.leerInt("gananciaDOSDigitos");
    private final static int gananciaTRESDigitos= XMLHandler.leerInt("gananciaTRESDigitos");
    private final static int cantidadNumerosGeneradosEnElSorteo = XMLHandler.leerInt("cantidadNumerosGeneradosEnElSorteo");
    private final static int MayorNumeroParaSorteo = XMLHandler.leerInt("MayorNumeroParaSorteo");
    private final static ParametrosEncapsuladosParaClientes parametrosEncapsuladosParaClientes = armarArrayConParametrosParaElCliente();
    //</editor-fold>
    
    public ManejadorServer()
    {
        db.DB.conectar();                                                                                 //Me conecto a la Base de Datos:
        
        GenericServer server = new GenericServer(parametrosEncapsuladosParaClientes);                      //Abro un Server Generico:
    }
    
    //<editor-fold desc="QUE HAGO CUANDO RECIBO UNA NUEVA CONEXION ENTRANTE:">
    //LOGICA DE ESTA APLICACION EN PARTICULAR (NO UN GENERIC SERVER):
    public static void recibirNuevaConexionEntrante(ConexionEntrante conexionEntrante)
    {
        ArrayList<String> arrImpresor = new ArrayList<String>();
        arrImpresor.add("RECIBI UNA NUEVA CONEXION ENTRANTE!");
        arrImpresor.add("Direccion IP: " + conexionEntrante.getSocket().getInetAddress());

        //RECIBO LA CONEXION ENTRANTE Y SEGUN EL STATUS DE CONEXION RECIBIDO, LA TRATO DIFERENTE:
        try                       
        {
            conexionEntrante.start();
            conexionEntrante.join();
            
            int statusConexion = (Integer) conexionEntrante.recibir();
            arrImpresor.add("STATUS CONEXION = " + statusConexion);
            
            switch(statusConexion)
            {
                case 1:
                    arrImpresor.add("Envio Parametros al cliente.");
                    System.out.println("" + ImpresorFormateadoConsola.ImpresorConsola.imprimirFormateado(arrImpresor));
                    
                    enviarConjuntoParametros(conexionEntrante);
                    
                    break;
                case 2:
                    arrImpresor.add("Recibo Jugadas y Respondo al cliente");
                    System.out.println("" + ImpresorFormateadoConsola.ImpresorConsola.imprimirFormateado(arrImpresor));
                    
                    recibirJugadas(conexionEntrante);
                    
                    break;
                case 3:
                    arrImpresor.add("Recibo nro de Tarjeta y Respondo al cliente");
                    System.out.println("" + ImpresorFormateadoConsola.ImpresorConsola.imprimirFormateado(arrImpresor));
                    
                    pedirDatosTarjeta(conexionEntrante);
                    
                    break;
                default:
                    arrImpresor.add("STATUS CONEXION NO VALIDO");
                    System.out.println("" + ImpresorFormateadoConsola.ImpresorConsola.imprimirFormateado(arrImpresor));
                    break;
            }   
        } 
        catch (Exception e)
        {
            System.out.println("ERROR: Al recibir Nueva conexion entrante.");
            e.printStackTrace();
        }
    }
    //</editor-fold>
    
    //<editor-fold desc="metodos PARAMETROS">
    public static ParametrosEncapsuladosParaClientes armarArrayConParametrosParaElCliente()
    {
        ParametrosEncapsuladosParaClientes pepc = new ParametrosEncapsuladosParaClientes();

        pepc = new ParametrosEncapsuladosParaClientes();
        pepc.agregarParametro(new ClaveValor("puerto", puerto));
        pepc.agregarParametro(new ClaveValor("importeMinimoPorApuesta", importeMinimoPorApuesta));
        pepc.agregarParametro(new ClaveValor("importeMaximoPorApuesta", importeMaximoPorApuesta));
        pepc.agregarParametro(new ClaveValor("importePorDefault", importePorDefault));
        pepc.agregarParametro(new ClaveValor("alcanze", alcanze));
        pepc.agregarParametro(new ClaveValor("importeMaximoDiario", importeMaximoDiario));
        pepc.agregarParametro(new ClaveValor("gananciaUNDigito", gananciaUNDigito));
        pepc.agregarParametro(new ClaveValor("gananciaDOSDigitos", gananciaDOSDigitos));
        pepc.agregarParametro(new ClaveValor("gananciaTRESDigitos", gananciaTRESDigitos));
        pepc.agregarParametro(new ClaveValor("cantidadNumerosGeneradosEnElSorteo", cantidadNumerosGeneradosEnElSorteo));
        pepc.agregarParametro(new ClaveValor("MayorNumeroParaSorteo", MayorNumeroParaSorteo));

        if(pepc != null)
        {
            System.out.println("" + pepc.toString() );
        }
        else
        {
            System.out.println("PEPC = NULL");
        }       
        return pepc;
    }
    public static void enviarConjuntoParametros(ConexionEntrante conexionEntrante)
    {
        conexionEntrante.enviar(parametrosEncapsuladosParaClientes);
    }
    //</editor-fold>
    
    //<editor-fold desc="metodos TARJETAS:">
    public boolean existeTarjeta( int numeroSerieTarjeta )
    {
        boolean respuesta = false;
        
        //if ( pedir())
        
        return respuesta;
    }
    public Tarjeta pedir(ConexionEntrante conexionEntrante)
    {
        int idRecibido = (Integer) conexionEntrante.recibir();

        Tarjeta tarjetaRespuesta = new Tarjeta();

        ArrayList<Object> arrTarjetasFromDB = db.DB.query("SELECT * FROM  `tarjetas` WHERE  `serial` LIKE  '" + idRecibido+"'");

        for(Object objetoTarjetaActual : arrTarjetasFromDB)
        {
            Tarjeta tarjetaActual = (Tarjeta) objetoTarjetaActual;
            
            if (tarjetaActual != null)
            {
                tarjetaRespuesta = tarjetaActual;
            }
        }
        
        return tarjetaRespuesta;
    }    
    private static void pedirDatosTarjeta(ConexionEntrante conexionEntrante)
    {
        Tarjeta tarjetaRespuesta = new Tarjeta();

        int numeroRecibidoDeSupuestaTarjeta = (int)conexionEntrante.recibir();
        
        ArrayList<Object> arrTarjetasFromDB = db.DB.query("SELECT * FROM  `tarjetas` WHERE  `serial` LIKE  '" + numeroRecibidoDeSupuestaTarjeta +"'");

        if(arrTarjetasFromDB.size() > 0)
        {
            conexionEntrante.enviar(true);
            tarjetaRespuesta = (Tarjeta) arrTarjetasFromDB.get(0);
            conexionEntrante.enviar(tarjetaRespuesta);
        }
        else
        {
            conexionEntrante.enviar(false);
        }
        
        
        
        System.out.println("" + numeroRecibidoDeSupuestaTarjeta);
    }
    //</editor-fold>
    
    //<editor-fold desc="METODOS DE PROCESAR JUGADAS:">
    private static void recibirJugadas(ConexionEntrante conexionEntrante)
    {
        ConjuntoDevuelto conjuntoRespuesta = new ConjuntoDevuelto();                         

        conjuntoRespuesta = procesarJugadasEntrantes(conexionEntrante);                      
        
        conexionEntrante.enviar(conjuntoRespuesta);  
    }
    private static ConjuntoDevuelto procesarJugadasEntrantes(ConexionEntrante conexionEntrante) 
    {
        ConjuntoDevuelto conjuntoRespuesta = new ConjuntoDevuelto();
            
        //Recibo las jugadas realizadas por el cliente:
        ConjuntoJugadas conjuntoJugadasRecibidas = (ConjuntoJugadas) conexionEntrante.recibir();

        //GENERO UN ARRAY DE NUMEROS GANADORES Y LOS INSERTO EN DB:".
        ArrayList<String> arrNumerosGanadores = sortear(); 
        
        //INSERTO EN LA BASE DE DATOS: LAS JUGADAS RECIBIDAS Y NUMEROS GENERADOS ASOCIADOS AL PK DE LA CONEXION ENTRANTE.
        int id = insercionesDB(conexionEntrante, conjuntoJugadasRecibidas , arrNumerosGanadores);

        conjuntoRespuesta = resolverGanancia(arrNumerosGanadores, conjuntoJugadasRecibidas);
        conjuntoRespuesta.setArrNumerosSorteados(arrNumerosGanadores);
        
        //<editor-fold desc="RESUELVE LA GANANCIA TOTAL:">
        int gananciaTotal = 0;
        
        for(RespuestaJugada respuestaJugada : conjuntoRespuesta.getArrRespuestasJugada())
        {
            gananciaTotal += respuestaJugada.getDineroGanadoEnEstaJugada();
        }
        //</editor-fold>

        System.out.println( conjuntoRespuesta.toString());
        return conjuntoRespuesta;
    }
    private static ArrayList<String> sortear() 
    {
        ArrayList<String> arrSalida = new ArrayList<String>();
        
        for (int numerosSorteados = 0; numerosSorteados < cantidadNumerosGeneradosEnElSorteo; numerosSorteados++) 
        {
            int numeroGenerado = (int) (Math.random() * MayorNumeroParaSorteo);
            String strNumeroGenerado ="";
            
            if(numeroGenerado < 10 )
            {
                strNumeroGenerado = "00" + numeroGenerado;
            }
            else if(numeroGenerado < 100)
            {
                strNumeroGenerado = "0" + numeroGenerado;
            }
            else
            {
                strNumeroGenerado = "" + numeroGenerado;
            }

            arrSalida.add(strNumeroGenerado);
        }
        
        return arrSalida;
    }
    private static int insercionesDB(ConexionEntrante conexionEntrante,ConjuntoJugadas conjuntoJugadasRecibidas,ArrayList<String> arrNumerosGanadores) 
    {
        //INSERTO UNA CONEXION ENTRANTE:
        int id = db.DB.insert("INSERT INTO  `nicoExpress`.`conexionEntrantes` VALUES (NULL ,  '" + conexionEntrante.getSocket().getInetAddress() + "', CURRENT_TIMESTAMP)");
        
        //INSERTO LAS JUGADAS RECIBIDAS EN DB:
        for(Jugada jugadaRecibida : conjuntoJugadasRecibidas.getArrJugadas())
        {
            String sqlInsertaJugadasRecibidas ="";
                
            if(jugadaRecibida != null)
            {
                sqlInsertaJugadasRecibidas = "INSERT INTO `nicoExpress`.`JugadasRecibidas`VALUES (NULL, '" + id +"', '" + jugadaRecibida.getNumero() +"', '" + jugadaRecibida.getDineroApostado() + "');";
                
                //System.out.println("ID CONEXION ENTRANTE: "+ id + ", NUMERO JUGADO:" + jugadaRecibida + ".");
            }
            else
            {
                sqlInsertaJugadasRecibidas = "INSERT INTO `nicoExpress`.`JugadasRecibidas`VALUES (NULL, '" + id +"', '---', '" + 0 + "');";
                
                //System.out.println("ID CONEXION ENTRANTE: "+ id + ", JUGADA VACIA");
            }
            db.DB.insert(sqlInsertaJugadasRecibidas); 
        }
        
        int i = 0 ;
        for(String numeroSorteado : arrNumerosGanadores)
        {
            String sql = "INSERT INTO `nicoExpress`.`numerosGenerados` VALUES (NULL, '"+ id + "', '" + numeroSorteado + "', '" + i + "');";
            db.DB.insert(sql);
            i++;
        }       
        return id;
    }
    private static ConjuntoDevuelto resolverGanancia(ArrayList<String> arrNumerosGanadores, ConjuntoJugadas conjuntoJugadasRecibidas) 
    {
        //int dineroTotal = 0;
        ConjuntoDevuelto conjuntoDevuelto = new ConjuntoDevuelto();
        
        for(Jugada jugadaDelUsuario :conjuntoJugadasRecibidas.getArrJugadas())
        {
            if(jugadaDelUsuario != null)
            {
                RespuestaJugada respuestaJugada = new RespuestaJugada(jugadaDelUsuario);

                int dineroGanadoEstaJugada = 0 ;
                
                for(String numeroGanador: arrNumerosGanadores)
                {
                    if(numeroGanador.endsWith(jugadaDelUsuario.getNumero()))
                    {
                        int dineroCorrespondiente = 0;

                        int multiplicador = resolverMultiplicador(jugadaDelUsuario);

                        dineroCorrespondiente = ( jugadaDelUsuario.getDineroApostado() * multiplicador ) / conjuntoJugadasRecibidas.getArrJugadas().length;

                        dineroGanadoEstaJugada += dineroCorrespondiente;

                            //System.out.println("Ganador: (SORTEADO)" + numeroGanador +" -> (JUGADO)" + jugadaDelUsuario.getNumero());

                    }
                }
                respuestaJugada.setDineroGanadoEnEstaJugada(dineroGanadoEstaJugada);
                conjuntoDevuelto.agregarRespuestaJugada(respuestaJugada);
                //dineroTotal += dineroGanadoEstaJugada;

                if(dineroGanadoEstaJugada > 0 )
                {
                        //System.out.println("    DINERO GANADO POR APOSTAR AL NUMERO " + jugadaDelUsuario.getNumero() + " = $" + dineroGanadoEstaJugada +".");
                }
            }     
        }
        return conjuntoDevuelto;
    }
    private static int resolverMultiplicador(Jugada jugadaDelUsuario) 
    {
        int multiplicador = 0;
        
        switch(jugadaDelUsuario.getNumero().length())
        {
            case 1: multiplicador = gananciaUNDigito; 
                 break;
            case 2: multiplicador = gananciaDOSDigitos; 
                 break; 
            case 3: multiplicador = gananciaTRESDigitos; 
                 break;
        }
          
        return multiplicador;
    }
    //</editor-fold>
   
    //<editor-fold desc="GYS:">
    public int getPuerto()
    {
        return puerto;
    }
    public void setPuerto(int puerto)
    {
        this.puerto = puerto;
    }
    public ParametrosEncapsuladosParaClientes getParametrosEncapsuladosParaClientes()
    {
            return parametrosEncapsuladosParaClientes;
    }
    //</editor-fold>
}
