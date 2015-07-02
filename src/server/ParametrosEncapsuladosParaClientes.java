package server;

import java.io.Serializable;
import java.util.ArrayList;

class ParametrosEncapsuladosParaClientes implements Serializable
{
        private ArrayList<ClaveValor> arrConParametros ; 
        
         public ParametrosEncapsuladosParaClientes()
        {
                this.arrConParametros = new ArrayList<ClaveValor>();
        }
        public ParametrosEncapsuladosParaClientes(ArrayList<ClaveValor> arrConParametros)
        {
                this.arrConParametros = arrConParametros;
        }
        public void agregarParametro(String clave, String valor)
        {
                this.arrConParametros.add(new ClaveValor(clave,valor));
        }
        public void agregarParametro(String clave, int valor)
        {
                this.arrConParametros.add(new ClaveValor(clave,valor));
        }
        public void agregarParametro(ClaveValor claveValor)
        {
                this.arrConParametros.add( claveValor );
        }
        
        /*GYS*/

        public ArrayList<ClaveValor> getArrConParametros() {
                return arrConParametros;
        }

        public void setArrConParametros(ArrayList<ClaveValor> arrConParametros) {
                this.arrConParametros = arrConParametros;
        }

        
}
