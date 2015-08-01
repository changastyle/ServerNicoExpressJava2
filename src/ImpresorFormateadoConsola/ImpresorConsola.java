package ImpresorFormateadoConsola;

import java.util.ArrayList;

public class ImpresorConsola
{
    public static String imprimirFormateado(ArrayList<String> arrRecibido)
    {
        String salida = "";
        int largoTotal = 80;
        String vacio = "";
        String lleno = "";
        for(int i = 0 ; i < largoTotal ; i++)
        {
            vacio += " ";
            lleno += "-";
        }
        int contador = 0;
        for(String stringActual : arrRecibido)
        {
            int numeroDeTabulaciones = (largoTotal - stringActual.length())/2;
            if (contador == 0 )
            {
                salida += "\n|" + lleno.substring(0,numeroDeTabulaciones) +" "+ stringActual + " "+lleno.substring(0,numeroDeTabulaciones-1)+"|";
            }
            else if(stringActual.trim().equalsIgnoreCase(""))
            {
                salida += "\n|";
                
                for(int i = 0; i < (numeroDeTabulaciones)*2 ; i++)
                {
                    salida += "-";
                }
                
                       
                
                salida += "|";
            }
            else
            {
                salida += "\n|" + vacio.substring(0,numeroDeTabulaciones) + stringActual + vacio.substring(0,numeroDeTabulaciones)+"|";
            }  
            contador++;
        }
        salida += "\n|" + lleno +"|";
        return salida;
    }
}
