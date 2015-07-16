package db;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;


public class DB 
{
       private static Connection conexion;
       
       //LECTURA DE PARAMETROS DB EN configDB.xml:
       private final static String host = XMLAPI.XMLHandler.leer("configDB.xml", "host");
       private final static String puerto =XMLAPI.XMLHandler.leer("configDB.xml", "port");;
       private final static String usuario= XMLAPI.XMLHandler.leer("configDB.xml", "user");
       private final static String password= XMLAPI.XMLHandler.leer("configDB.xml", "password");
       private final static String schema = XMLAPI.XMLHandler.leer("configDB.xml", "schema");
      
       
       
       public static void conectar()
       {
            try 
            {
                Class.forName("com.mysql.jdbc.Driver");


                conexion = DriverManager.getConnection("jdbc:mysql://" + host +"/" +schema ,usuario ,password);
                conexion.setAutoCommit(false);
            }
            catch(ClassNotFoundException ex) 
            {
                System.out.println("Error: unable to load driver class!");
                ex.printStackTrace();
                System.exit(1);

            }
            catch (SQLException ex) 
            {
                ex.printStackTrace();
            }
       }
       public static ArrayList<Object> query(String sql)
       {
            ArrayList<Object> arrSalida = new ArrayList<>();

            Statement statement = null;
            ResultSet rs = null;

             try 
             {
                statement = conexion.createStatement();
                //rs = statement.executeQuery(sql);     

                if (statement.execute(sql)) 
                {
                    rs = statement.getResultSet();

                    int cantidadColumnas = rs.getMetaData().getColumnCount();
                    //System.out.println("Cantidad de Columnas = " + (cantidadColumnas -1) );
                    while(rs.next())
                    {
                            ArrayList<String> arrFila = new ArrayList<String>();
                            for (int i = 1; i < cantidadColumnas; i++)
                            {
                                    arrFila.add(rs.getString(i));
                            }
                            arrSalida.add(arrFila);
                    } 
                }


                //CIERRO EL RSET:
                if (rs != null) 
                {
                    rs.close();
                    rs = null;
                }

                //CIERRO EL STATEMENT:
                if (statement  != null) 
                {
                    statement.close();
                    statement = null;
                }
            } 
            catch (Exception e) 
            {
                System.out.println("ERROR:" + e.toString() );
            }
            return arrSalida;
       }
       public static int insert(String sql)
       {
            int idGenerado = 0;
            ResultSet idsGenerados = null;
            Statement statement = null;
            
            try 
             {
                 statement = conexion.createStatement();
                 int ok = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);

                 if(ok == 1)
                 {
                    idsGenerados = statement.getGeneratedKeys();
                    conexion.commit();
                     
                    //DEVUELVO EL ID DEL INSERT:
                    if(idsGenerados.next())
                    {
                        idGenerado = idsGenerados.getInt(1);
                    }
                 }
             }
            catch(Exception e)
             {
                System.out.println("ERROR: INSERT" + sql);
                e.printStackTrace();
             }
            return idGenerado;
       }
       public static ArrayList<Integer> insertMultiple(ArrayList<String> arrSqls)
       {
        ArrayList<Integer> arrIDsGenerados = new ArrayList<Integer>();
        ResultSet idsGeneradosAUX = null;
        Statement statement = null;

        try 
        {
            statement = conexion.createStatement();

            //ITERO PARA CADA SQL DEL ARRAY:
            int contador = 1;
            for(String sql : arrSqls)
            {
                int ok = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
                idsGeneradosAUX = statement.getGeneratedKeys(); 
                
                if(idsGeneradosAUX.next())
                {
                    arrIDsGenerados.add(idsGeneradosAUX.getInt(contador));
                    try
                    {
                        conexion.commit( );
                    }
                    catch(Exception e)
                    {
                        System.out.println("ERROR: INSERT MULTIPLE");
                    }
                }
                else
                {
                        try
                        {
                            conexion.rollback();
                        }
                        catch(Exception e)
                        {
                            System.out.println("ERROR: INSERT MULTIPLE");
                        }
                }
                contador++;
            }
                         
        }
        catch (Exception e) 
        {
            System.out.println("ERROR: INSERT MULTIPLE." );
            e.printStackTrace();
            try 
            {
                    conexion.rollback();
            }
            catch (SQLException ex) 
            {
                    ex.printStackTrace();
            }
        }
        return arrIDsGenerados;

    }
}
