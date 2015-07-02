package serializable;

import java.io.Serializable;

public class ClaveValor implements Serializable
{
        private String clave;
        private String valor;
        
        public ClaveValor()
        {
                clave = "";
                valor ="";
        }
        public ClaveValor(String clave, String valor)
        {
                this.clave = clave;
                this.valor = valor;
        }
        public ClaveValor(String clave, int valor)
        {
                this.clave = clave;
                this.valor = "" + valor;
        }
        
        /*GYS:*/

        public String getClave() {
                return clave;
        }

        public void setClave(String clave) {
                this.clave = clave;
        }

        public String getValor() {
                return valor;
        }

        public void setValor(String valor) {
                this.valor = valor;
        }
        
}
