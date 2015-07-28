package serializable;

import java.io.Serializable;
import java.util.ArrayList;

public class ParametrosEncapsuladosParaClientes implements Serializable
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
        public ClaveValor getParametro(String clave)
        {
            ClaveValor salida = new ClaveValor();
            for(ClaveValor claveValor : arrConParametros)
            {
                if(claveValor.getClave().equalsIgnoreCase(clave))
                {
                    salida = claveValor;
                }
            }
            return salida;
        }
        
        /*GYS*/

        public ArrayList<ClaveValor> getArrConParametros() {
                return arrConParametros;
        }

        public void setArrConParametros(ArrayList<ClaveValor> arrConParametros) {
                this.arrConParametros = arrConParametros;
        }
        

        @Override
        public String toString() 
        {
                return "" + imprimirArrParametros() ;
        }

        private String imprimirArrParametros() 
        {
                String salida = "|------------------------ PARAMETROS ------------------------|";
                for (ClaveValor cv : this.getArrConParametros()) 
                {
                        salida += "\n" + cv.getClave() + " -> " + cv.getValor();
                }
                salida += "\n|------------------------------------------------------------------------|";
                return salida;
        }

        
        
        
}
