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
        ConjuntoJugadas conjuntoJugadasRecibidas = (ConjuntoJugadas) conexionEntrante.recibir();
        conjuntoDevuelto = procesarJugadasEntrantes(conjuntoJugadasRecibidas, conexionEntrante);
        
        conexionEntrante.enviar(conjuntoDevuelto);
    }
    private static ConjuntoDevuelto procesarJugadasEntrantes(ConjuntoJugadas conjuntoJugadasRecibidas, ConexionEntrante conexionEntrante) 
    {
        ConjuntoDevuelto conjuntoDevuelto = new ConjuntoDevuelto();
         ///System.out.println("RECIBÍ " + conjuntoJugadasRecibidas.getArrJugadas().size() + " JUGADAS.");
        
        int id = insercionesDB(conjuntoJugadasRecibidas,conexionEntrante);
        
        //GENERO UN ARRAY DE NUMEROS GANADORES Y LOS INSERTO EN DB:".
        ArrayList<String> arrNumerosGanadores = sortear(id);
        
        int gananciaTotal = resolverGanancia(arrNumerosGanadores, conjuntoJugadasRecibidas);
        
        System.out.println("GANANCIA TOTAL: $" + gananciaTotal +",00.");
        
        
        return conjuntoDevuelto;
    }
    private static int resolverGanancia(ArrayList<String> arrNumerosGanadores, ConjuntoJugadas conjuntoJugadasRecibidas) 
    {
        int dineroTotal = 0;
        
        
        for(Jugada jugadaDelUsuario :conjuntoJugadasRecibidas.getArrJugadas())
        {
            int dineroGanadoEstaJugada = 0 ;
            for(String numeroGanador: arrNumerosGanadores)
            {
                if(numeroGanador.endsWith(jugadaDelUsuario.getNumero()))
                {
                    int dineroCorrespondiente = 0;
                    
                    int multiplicador = resolverMultiplicador(jugadaDelUsuario);
                    
                    dineroCorrespondiente = ( jugadaDelUsuario.getDineroApostado() * 7 * multiplicador ) / conjuntoJugadasRecibidas.getArrJugadas().size();
                    
                    dineroGanadoEstaJugada += dineroCorrespondiente;
                    
                    System.out.println("GANO");
                    System.out.println("" + numeroGanador +" -> " + jugadaDelUsuario.getNumero());
                    
                }
            }
            dineroTotal += dineroGanadoEstaJugada;
            if(dineroGanadoEstaJugada > 0 )
            {
                System.out.println("DINERO ESTA JUGADA = $" + dineroGanadoEstaJugada);
            }
        }
            System.out.println("DINERO TOTAL = $" + dineroTotal);
            return dineroTotal;
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
    private static ArrayList<String> sortear(int idConexionEntrante) 
    {
        ArrayList<String> arrNumerosGanadores = new ArrayList<String>();
        
        for (int numerosSorteados = 0; numerosSorteados < cantidadNumerosGeneradosEnElSorteo; numerosSorteados++) 
        {
            int numeroGenerado = (int) (Math.random() * MayorNumeroParaSorteo);String strNumeroGenerado = "" + numeroGenerado;
            arrNumerosGanadores.add(strNumeroGenerado);
                
            String sql = "INSERT INTO `nicoExpress`.`numerosGenerados` VALUES (NULL, '"+ idConexionEntrante + "', '" + strNumeroGenerado + "', '" + numerosSorteados + "');";
            db.DB.insert(sql);
                System.out.println( numerosSorteados + " - NUMERO SORTEADO N°: " + strNumeroGenerado);
        }
        
        return arrNumerosGanadores;
    }

    private static int insercionesDB(ConjuntoJugadas conjuntoJugadasRecibidas, ConexionEntrante conexionEntrante) 
    {
                //INSERTO UNA CONEXION ENTRANTE:
        int id = db.DB.insert("INSERT INTO  `nicoExpress`.`conexionEntrantes` VALUES (NULL ,  '" + conexionEntrante.getSocket().getInetAddress() + "', CURRENT_TIMESTAMP)");
            System.out.println("CONEXION ENTRANTE CON ID: "+ id);
        
        //INSERTO LAS JUGADAS RECIBIDAS EN DB:
        for(Jugada jugadaRecibida : conjuntoJugadasRecibidas.getArrJugadas())
        {
            String sqlInsertaJugadasRecibidas = "INSERT INTO `nicoExpress`.`JugadasRecibidas`VALUES (NULL, '" + id +"', '" + jugadaRecibida.getNumero() +"', '" + jugadaRecibida.getDineroApostado() + "');";
            db.DB.insert(sqlInsertaJugadasRecibidas);
                System.out.println("ID CONEXION ENTRANTE: "+ id + ", NUMERO JUGADO:" + jugadaRecibida + ".");
        }
        return id;
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
