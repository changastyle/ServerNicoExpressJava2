package server;

import serializable.ClaveValor;
import serializable.ParametrosEncapsuladosParaClientes;
import XMLAPI.XMLHandler;
import java.util.ArrayList;
import serializable.Jugada;
import serializable.*;

class ManejadorServer
{
        //private final String rutaArchivoConfiguracion = "config.xml";
    
        //ASIGNACION DE PARAMETROS:
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
        private ParametrosEncapsuladosParaClientes parametrosEncapsuladosParaClientes;
    
    public ManejadorServer()
    {
            parametrosEncapsuladosParaClientes = armarArrayConParametrosParaElCliente();
        
            db.DB.conectar();
            
            //CREO UN OBJETO SERVER GENERICO:
            Server server = new Server(this);
    }
    
    //LOGICA DE ESTA APLICACION EN PARTICULAR:
    static void doWhatYouHaveToDoWithNewConexionEntrante(ConexionEntrante conexionEntrante)
    {
        ConjuntoDevuelto conjuntoDevuelto = new ConjuntoDevuelto();

        //RECIBO LAS JUGADAS QUE MANDO EL USUARIO:
        
        conjuntoDevuelto = procesarJugadasEntrantes(conexionEntrante);
        
        conexionEntrante.enviar(conjuntoDevuelto);
    }
    private static ConjuntoDevuelto procesarJugadasEntrantes(ConexionEntrante conexionEntrante) 
    {
        ConjuntoDevuelto conjuntoDevuelto = new ConjuntoDevuelto();
            
        ConjuntoJugadas conjuntoJugadasRecibidas = (ConjuntoJugadas) conexionEntrante.recibir();
            //System.out.println("RECIBÍ " + conjuntoJugadasRecibidas.getArrJugadas().size() + " JUGADAS.");
        
        //GENERO UN ARRAY DE NUMEROS GANADORES Y LOS INSERTO EN DB:".
        ArrayList<String> arrNumerosGanadores = sortear(); 
        System.out.println("RECIBI UN NUEVO CONJUNTO DE JUGADAS ENTRANTE DE :" + conexionEntrante.getSocket().getInetAddress());
        System.out.println("VOY A INSERTAR ESTO EN DB:" + conjuntoJugadasRecibidas.toString());
        
        int id = insercionesDB(conexionEntrante, conjuntoJugadasRecibidas , arrNumerosGanadores);

        conjuntoDevuelto = resolverGanancia(arrNumerosGanadores, conjuntoJugadasRecibidas);
        conjuntoDevuelto.setArrNumerosSorteados(arrNumerosGanadores);
        
        //RESUELVE LA GANANCIA TOTAL:
        int gananciaTotal = 0;
        
        for(RespuestaJugada respuestaJugada : conjuntoDevuelto.getArrRespuestasJugada())
        {
            gananciaTotal += respuestaJugada.getDineroGanadoEnEstaJugada();
        }
        
        System.out.println("GANANCIA TOTAL: $" + gananciaTotal +",00.");
        //FIN RESUELVE GANANCIA TOTAL.

        System.out.println("ESTOY RESPONDIENDO ESTO AL CLIENTE:" + conjuntoDevuelto.toString());
        return conjuntoDevuelto;
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
        System.out.println("ESTOY INSERTANDO EN DB:");
        //INSERTO UNA CONEXION ENTRANTE:
        int id = db.DB.insert("INSERT INTO  `nicoExpress`.`conexionEntrantes` VALUES (NULL ,  '" + conexionEntrante.getSocket().getInetAddress() + "', CURRENT_TIMESTAMP)");
            System.out.println("CONEXION ENTRANTE CON ID: "+ id);
        
        //INSERTO LAS JUGADAS RECIBIDAS EN DB:
        for(Jugada jugadaRecibida : conjuntoJugadasRecibidas.getArrJugadas())
        {
            String sqlInsertaJugadasRecibidas ="";
                
            if(jugadaRecibida != null)
            {
                sqlInsertaJugadasRecibidas = "INSERT INTO `nicoExpress`.`JugadasRecibidas`VALUES (NULL, '" + id +"', '" + jugadaRecibida.getNumero() +"', '" + jugadaRecibida.getDineroApostado() + "');";
                
                System.out.println("ID CONEXION ENTRANTE: "+ id + ", NUMERO JUGADO:" + jugadaRecibida + ".");
            }else
            {
                sqlInsertaJugadasRecibidas = "INSERT INTO `nicoExpress`.`JugadasRecibidas`VALUES (NULL, '" + id +"', '---', '" + 0 + "');";
                
                System.out.println("ID CONEXION ENTRANTE: "+ id + ", JUGADA VACIA");
            }
            db.DB.insert(sqlInsertaJugadasRecibidas);
            
        }
        
        int i = 0 ;
        for(String numeroSorteado : arrNumerosGanadores)
        {
            String sql = "INSERT INTO `nicoExpress`.`numerosGenerados` VALUES (NULL, '"+ id + "', '" + numeroSorteado + "', '" + i + "');";
            db.DB.insert(sql);
            System.out.println( i + " - NUMERO SORTEADO N°: " + numeroSorteado);
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

                            System.out.println("GANO");
                            System.out.println("" + numeroGanador +" -> " + jugadaDelUsuario.getNumero());

                    }
                }
                respuestaJugada.setDineroGanadoEnEstaJugada(dineroGanadoEstaJugada);
                conjuntoDevuelto.agregarRespuestaJugada(respuestaJugada);
                //dineroTotal += dineroGanadoEstaJugada;

                if(dineroGanadoEstaJugada > 0 )
                {
                        System.out.println("DINERO ESTA JUGADA = $" + dineroGanadoEstaJugada);
                }
            }    
            
        }
            
        //System.out.println("DINERO TOTAL = $" + dineroTotal);
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
    public ParametrosEncapsuladosParaClientes armarArrayConParametrosParaElCliente()
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

        return pepc;
    }
    public void enviarConjuntoParametros(ConexionEntrante conexionEntrante)
    {
        System.out.println("PASE PARAMETROS A IP : "+ conexionEntrante.getSocket().getInetAddress());
        conexionEntrante.enviar(parametrosEncapsuladosParaClientes);
    }
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
    
    
    
    
    
    /*GYS*/
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
    public void setParametrosEncapsuladosParaClientes(ParametrosEncapsuladosParaClientes parametrosEncapsuladosParaClientes) 
    {
            this.parametrosEncapsuladosParaClientes = parametrosEncapsuladosParaClientes;
    }
}
