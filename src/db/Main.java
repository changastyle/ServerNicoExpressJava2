package db;
import java.util.ArrayList;

public class Main 
{
        public static void main(String[] args) 
        {
                //CONEXION:
                DB.conectar();
                
                
                //INSERCION:
                String sql = "INSERT INTO `pruebas`.`usuarios` VALUES (NULL, 'peter', 'capusotto', 'pCapusotto@gmail.com');";
                ////DB.insert(sql);
                //INSERCION MULTIPLE:
                ArrayList<String> arrSQLs = new ArrayList<String>();
                
                String sql2 = "INSERT INTO `pruebas`.`usuarios` VALUES (NULL, 'peter1', 'capusotto', 'pCapusotto@gmail.com');";
                String sql3 = "INSERT INTO `pruebas`.`usuarios` VALUES (NULL, 'peter2', 'capusotto', 'pCapusotto@gmail.com');";
                String sql4 = "INSERT INTO `pruebas`.`usuarios` VALUES (NULL, 'peter3', 'capusotto', 'pCapusotto@gmail.com');";
                arrSQLs.add(sql2);
                arrSQLs.add(sql3);
                arrSQLs.add(sql4);
                DB.insertMultiple(arrSQLs);
                
                
                //LECTURA:
                ArrayList<Object> arr = DB.query("SELECT * FROM usuarios");
                
                for(Object o : arr)
                {
                        System.out.println("o = "  + o.toString());
                }
        }
        
}
