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
            //this.armarArrayConParametrosParaElCliente();
            
            //CREO UN OBJETO SERVER GENERICO:
            Server server = new Server(this);
    }
    
    //LOGICA DE ESTA APLICACION EN PARTICULAR:
    static void doWhatYouHaveToDoWithNewConexionEntrante(ConexionEntrante conexionEntrante)
    {
            //AL FINAL VOY A DEVOLVER ESTO:
            ConjuntoDevuelto conjuntoDevuelto = new ConjuntoDevuelto();
            
            //Recibo un conjunto de Jugadas:
            ConjuntoJugadas conjuntoJugadasRecibidas = (ConjuntoJugadas) conexionEntrante.recibir();
            System.out.println("RECIBÍ " + conjuntoJugadasRecibidas.getArrJugadas().size() + " JUGADAS.");
        
            /*
            //GENERO UN ARRAY DE NUMEROS "SORTEADOS".
            ArrayList<String> arrNumerosGenerados = new ArrayList<String>();
            for (int numerosSorteados = 0; numerosSorteados < cantidadNumerosGeneradosEnElSorteo; numerosSorteados++) 
            {
                    int numeroGenerado = (int) (Math.random() * MayorNumeroParaSorteo);
                    String strNumeroGenerado = "" + numeroGenerado;
                    arrNumerosGenerados.add(strNumeroGenerado);
                        // System.out.println("NUMERO GANADOR = " + numeroGenerado );
                        //System.out.println("STRNUMEROGANADOR = " + strNumeroGenerado);
            }

        for (Jugada j : conjuntoJugadasRecibidas.getArrJugadas())
        {
                for(String numeroGenerado : arrNumerosGenerados)
                {
                        RespuestaJugada respuestaJugada = new RespuestaJugada(j);
                        
                        //Si la ultima parte de algun numero sorteado es igual a el numero apostado en una jugada, entonces le pago:
                        if(     numeroGenerado.endsWith(   j.getNumero()  )         )
                        {
                               int dineroGanado = ( j.getDineroApostado() * 7 * multiplicando ) / conjuntoJugadasRecibidas.getArrJugadas().size();
                        }   
                }
        }
        
        int dineroGanado = 0;
        
        
        for (Jugada j : conjuntoJugadasRecibidas.getArrJugadas())
        {
            RespuestaJugada respuestaJugada = new RespuestaJugada(j);
            
            if(strNumeroGanador.endsWith( j.getNumero() ))
            {
                double longitudNumeroJugado = (double) j.getNumero().length();
                double diez = (double) 10;
                
                //TODO : TABLA CON VALORES DE UN ARCHIVO DE CONFIGURACION.
                int multiplicando = (int) Math.pow(longitudNumeroJugado, diez );
                
                dineroGanado = ( conjuntoJugadasRecibidas.getDineroTotalApostado() * 7 * multiplicando ) / conjuntoJugadasRecibidas.getArrJugadas().size();
                System.out.println("DEBUG: LENGTH NUMERO APOSTADO = " + j.getNumero().length());
            }
            else
            {
                dineroGanado = 0;
            }
            
            System.out.println("DINERO GANADO EN ESTA JUGADA = " + dineroGanado);
            respuestaJugada.setDineroGanadoEnEstaJugada(dineroGanado);
            conjuntoDevuelto.agregarRespuestaJugada(respuestaJugada);
        }
        
        System.out.println("CONJUNTO DEVUELTO = " + conjuntoDevuelto.toString());
        
        conexionEntrante.enviar(conjuntoDevuelto);*/
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
      public ParametrosEncapsuladosParaClientes armarArrayConParametrosParaElCliente(ParametrosEncapsuladosParaClientes parametrosEncapsuladosParaClientes)
    {
            
            parametrosEncapsuladosParaClientes = new ParametrosEncapsuladosParaClientes();
            parametrosEncapsuladosParaClientes.agregarParametro(new ClaveValor("puerto", puerto));
            parametrosEncapsuladosParaClientes.agregarParametro(new ClaveValor("importeMinimoPorApuesta", importeMinimoPorApuesta));
            parametrosEncapsuladosParaClientes.agregarParametro(new ClaveValor("importeMaximoPorApuesta", importeMaximoPorApuesta));
            parametrosEncapsuladosParaClientes.agregarParametro(new ClaveValor("importePorDefault", importePorDefault));
            parametrosEncapsuladosParaClientes.agregarParametro(new ClaveValor("alcanze", alcanze));
            parametrosEncapsuladosParaClientes.agregarParametro(new ClaveValor("importeMaximoDiario", importeMaximoDiario));
            parametrosEncapsuladosParaClientes.agregarParametro(new ClaveValor("gananciaUNDigito", gananciaUNDigito));
            parametrosEncapsuladosParaClientes.agregarParametro(new ClaveValor("gananciaDOSDigitos", gananciaDOSDigitos));
            parametrosEncapsuladosParaClientes.agregarParametro(new ClaveValor("gananciaTRESDigitos", gananciaTRESDigitos));
            parametrosEncapsuladosParaClientes.agregarParametro(new ClaveValor("cantidadNumerosGeneradosEnElSorteo", cantidadNumerosGeneradosEnElSorteo));
            parametrosEncapsuladosParaClientes.agregarParametro(new ClaveValor("MayorNumeroParaSorteo", MayorNumeroParaSorteo));
            
            return parametrosEncapsuladosParaClientes;
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

        public ParametrosEncapsuladosParaClientes getParametrosEncapsuladosParaClientes() {
                return parametrosEncapsuladosParaClientes;
        }

        public void setParametrosEncapsuladosParaClientes(ParametrosEncapsuladosParaClientes parametrosEncapsuladosParaClientes) {
                this.parametrosEncapsuladosParaClientes = parametrosEncapsuladosParaClientes;
        }
    
}
