/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADRIANA
 */
package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pkgfinal.Final;
import pkgfinal.Vista3;

/**
 *
 * @author ADRIANA
 */
public class ConexionBD {

    String sql;
    String driver = "com.mysql.cj.jdbc.Driver";
    String URL_bd = "jdbc:mysql://localhost:3306/prodactuz";
    String usuario = "root";
    String clave = "";
    Connection con = null;
    Statement comando = null;
    ResultSet registros = null;
    int filas_afectadas;
    String lugares[] = null;
    String medicamentos[] = null;
    int codigos[] = null;
    String item = "";
    String sql2, sql3, sql4, sql5, sql6, sql7;
    String codigo, nombrel, codigo2 = "";
    int longitud, longcod, codAdmin;
    int i = 0;

    public int getI() {
        return i;
    }

    public int getCodAdmin() {
        return codAdmin;
    }

    public Connection getCon() {
        return con;
    }

    public String[] getLugares() {
        return lugares;
    }

    public String getCodigo() {
        return codigo;
    }

    public ResultSet getRegistros() {
        return registros;
    }

    public String getItem() {
        return item;
    }

    public String[] getMedicamentos() {
        return medicamentos;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public ConexionBD() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(URL_bd, usuario, clave);
            if (con != null) {
                System.out.println("Conexion Exitosa");
                comando = con.createStatement();
            } else {
                System.out.println("Conexion No Exitosa");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet consultarBD(String sentencia) {
        try {
            comando = con.createStatement();
            registros = comando.executeQuery(sentencia);
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
        } catch (RuntimeException rex) {
            System.out.println(rex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return registros;
    }

    public boolean insertarBD(String sentencia) {
        try {
            comando = con.createStatement();
            comando.execute(sentencia);
        } catch (SQLException | RuntimeException sqlex) {
            System.out.println("Error Rutina: " + sqlex);
            return false;
        }
        return true;
    }

    public boolean borrarBD(String sentencia) {
        try {
            comando = con.createStatement();
            comando.execute(sentencia);
        } catch (SQLException | RuntimeException sqlex) {
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        }
        return true;
    }

    public boolean actualizarBD(String sentencia) {
        try {
            comando = con.createStatement();
            comando.executeUpdate(sentencia);
        } catch (SQLException | RuntimeException sqlex) {
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        }
        return true;
    }

    public boolean commitBD() {
        try {
            con.commit();
            return true;
        } catch (SQLException sqlex) {
            System.out.println("Error al hacer el commit" + sqlex.getMessage());
            return false;
        }
    }

    public boolean rollbackBD() {
        try {
            con.rollback();
            return true;
        } catch (SQLException sqlex) {
            System.out.println("Error al hacer rollback " + sqlex.getMessage());
            return false;
        }
    }

    public void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void cerrarconexion() {
        closeConnection(con);
    }

    public boolean setAutoCommitBD(boolean parametro) {
        try {
            con.setAutoCommit(parametro);
        } catch (SQLException sqlex) {
            System.out.println("Error al configurar el autoCommit " + sqlex.getMessage());
            return false;
        }
        return true;
    }
    
    public void llenarCombo() {
        ResultSet registros2 = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(URL_bd, usuario, clave);
            if (con != null) {
                sql = "SELECT * FROM `tbl_lugares`  WHERE `cod_lugares` ";
                comando = con.createStatement();
                registros2 = comando.executeQuery(sql);
                while (registros2.next()) {
                    longitud = registros2.getInt("cod_lugares");
                }
                registros = comando.executeQuery(sql);

                lugares = new String[longitud];
                while (registros.next()) {
                    lugares[i] = registros.getString("nombre");
                    System.out.println(lugares[i] + i);
                    i = i + 1;
                }
                con.close();
            } else {
                System.out.println("Conexion No Exitosa");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String llenarTabla(String item) {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(URL_bd, usuario, clave);
            if (con != null) {
                sql4 = " SELECT `Cod_lugares` FROM `tbl_lugares` WHERE `Nombre` = \"" + item + " \"";
                comando = con.createStatement();

                registros = comando.executeQuery(sql4);

                while (registros.next()) {
                    codigo = registros.getString("Cod_lugares");
                    setCodigo(codigo);
                    System.out.println("codigo lugar:" + codigo);
                }
                //con.close();
                System.out.println("cod:" + codigo);
            } else {
                System.out.println("Conexion No Exitosa");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("\n" + item);
        System.out.println("Primero:"+codigo);
        return codigo;
    }

    public List<MedicamentosSolidos> listarSolidos() {
        System.out.println("ListSolidos:"+codigo);
        List<MedicamentosSolidos> listaSolidos = new ArrayList<>();
        String sql3 = "SELECT * FROM tbl_medicamentos_solidos WHERE `Cod_lugares` = " + codigo;
        try {
            ResultSet registros = consultarBD(sql3);
            MedicamentosSolidos solidos;
            while (registros.next()) {
                solidos = new MedicamentosSolidos();
                solidos.setNombre(registros.getString("Nombre"));
                solidos.setDescripcion(registros.getString("Descripcion"));
                solidos.setCantidad(registros.getInt("Cantidad"));
                solidos.setPrecio_tableta(registros.getFloat("Precio_tableta"));
                solidos.setPrecio_caja(registros.getFloat("Precio_caja"));
                solidos.setCodigoMedSol(registros.getInt("Cod_solidos"));
                listaSolidos.add(solidos);
            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println("Error en la consulta");
        }
        System.out.println(listaSolidos);
        System.out.println(codigo);
        return listaSolidos;
        
    }

    public List<MedicamentosLiquidos> listarLiquidos() {
        System.out.println(codigo);
        List<MedicamentosLiquidos> listaLiquidos = new ArrayList<>();
        String sql5 = "SELECT * FROM tbl_medicamentos_liquidos WHERE `Cod_lugares` = " + codigo;
        try {
            ResultSet registros = consultarBD(sql5);
            MedicamentosLiquidos liquidos;
            while (registros.next()) {
                liquidos = new MedicamentosLiquidos();
                liquidos.setNombre(registros.getString("Nombre"));
                liquidos.setDescripcion(registros.getString("Descripcion"));
                liquidos.setCantidad_Frasco(registros.getInt("Cantidad_frasco"));
                liquidos.setPrecio_Frasco(registros.getFloat("Precio_frasco"));
                liquidos.setCod_Liquidos(registros.getInt("Cod_liquidos"));
                listaLiquidos.add(liquidos);
            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println("Error en la consulta");
        }
        System.out.println(listaLiquidos);
        System.out.println(codigo);
        return listaLiquidos;
    }

    public int llnrTblMedSol(String item) {
        ResultSet registros4 = null;
        ResultSet registros8 = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(URL_bd, usuario, clave);
            if (con != null) {
                sql6 = "SELECT `Cod_lugares` FROM `tbl_medicamentos_solidos` WHERE `Nombre` = \"" + item + "\"";
                comando = con.createStatement();
                registros8 = comando.executeQuery(sql6);
                while (registros8.next()) {
                    longcod = registros8.getInt("Cod_lugares");
                }
                System.out.println("longi " + longcod);
                registros4 = comando.executeQuery(sql6);
                codigos = new int[longcod];
                while (registros4.next()) {
                    codigos[i] = registros4.getInt("Cod_lugares");
                    System.out.println("Num cod:" + codigos[i]); ///cod del lugar med 
                    i = i + 1;
                }
                //con.close();
                System.out.println("codi Total:" + i);
            } else {
                System.out.println("Conexion No Exitosa");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codigos[i];
    }
    public int llnrTblMedLiq(String item) {
        ResultSet registros4 = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(URL_bd, usuario, clave);
            if (con != null) {
                sql6 = "SELECT `Cod_lugares` FROM `tbl_medicamentos_liquidos` WHERE `Nombre` = \"" + item + "\"";
                comando = con.createStatement();
                registros = comando.executeQuery(sql6);
                while (registros.next()) {
                    longcod = registros.getInt("Cod_lugares");
                }
                System.out.println("longi " + longcod);
                registros4 = comando.executeQuery(sql6);
                codigos = new int[longcod];
                while (registros4.next()) {
                    codigos[i] = registros4.getInt("Cod_lugares");
                    System.out.println("Num cod:" + codigos[i]); ///cod del lugar med 
                    i = i + 1;
                }
                //con.close();
                System.out.println("codi Total:" + i);
            } else {
                System.out.println("Conexion No Exitosa");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codigos[i];
    }

    public String[] llenarCombMediSol() {
        ResultSet registros3 = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(URL_bd, usuario, clave);
            if (con != null) {
                sql = "SELECT * FROM `tbl_medicamentos_solidos`  WHERE `cod_solidos` ";
                comando = con.createStatement();
                registros3 = comando.executeQuery(sql);
                while (registros3.next()) {
                    longitud = registros3.getInt("cod_solidos");
                }
                registros = comando.executeQuery(sql);
                lugares = new String[longitud];
                while (registros.next()) {
                    lugares[i] = registros.getString("nombre");
                    System.out.println(lugares[i] + i); //bien
                    //System.out.println(registros.getString("nombre"));
                    i = i + 1;
                }
                //con.close();
            } else {
                System.out.println("Conexion No Exitosa");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lugares;
    }

    public String[] llenarCombMediLiq() {
        ResultSet registros3 = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(URL_bd, usuario, clave);
            if (con != null) {
                sql = "SELECT * FROM `tbl_medicamentos_liquidos`  WHERE `cod_liquidos` ";
                comando = con.createStatement();
                registros3 = comando.executeQuery(sql);
                while (registros3.next()) {
                    longitud = registros3.getInt("cod_liquidos");
                }
                registros = comando.executeQuery(sql);
                lugares = new String[longitud];
                while (registros.next()) {
                    lugares[i] = registros.getString("nombre");
                    System.out.println(lugares[i] + i); //bien
                    //System.out.println(registros.getString("nombre"));
                    i = i + 1;
                }
                //con.close();
            } else {
                System.out.println("Conexion No Exitosa");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lugares;
    }
    public void nombSol(String item) {
        ResultSet registros6;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(URL_bd, usuario, clave);
            if (con != null) {
                sql5 = " SELECT `Cod_lugares` FROM `tbl_medicamentos_solidos` WHERE `Cod_solidos` = " + i;
                comando = con.createStatement();
                int cod;
                registros6 = comando.executeQuery(sql5);
                System.out.println("NOM Med: " + item);
                System.out.println("COD: " + i);
                while (registros6.next()) {
                    sql5 = " SELECT `Cod_lugares` FROM `tbl_medicamentos_solidos` WHERE `Cod_solidos` =" + codigos[i];
                    comando = con.createStatement();
                    registros6 = comando.executeQuery(sql5);
                    cod = registros.getInt("Cod_lugares");
                    System.out.println("cods: " + cod);
                    i = i + 1;
                }
                System.out.println(codigos[i] + i);
                //con.close();;
            } else {
                System.out.println("Conexion No Exitosa");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Lugares> listarLugSol() {
        List<Lugares> listaLugares = new ArrayList<>();
        System.out.println(i);
        for (int j = 0; j < i; j++) {
            String sql6 = "SELECT * FROM tbl_lugares WHERE `Cod_lugares` = " + codigos[j];

            try {
                ResultSet registros5 = consultarBD(sql6);
                Lugares lug;
                while (registros5.next()) {
                    lug = new Lugares();
                    lug.setNombre(registros5.getString("Nombre"));
                    lug.setDireccion(registros5.getString("Direccion"));
                    lug.setTel_Fijo(registros5.getInt("Telefono_fijo"));
                    lug.setCodLugares(registros5.getInt("Cod_lugares"));

                    listaLugares.add(lug);
                }
                //con.close();
            } catch (SQLException ex) {
                System.out.println("Error en la consulta");
            }
        }
        System.out.println(listaLugares);
        System.out.println(codigo);
        return listaLugares;
    }

    
    public void comboCompra() {
        System.out.println(codigo);
        ResultSet registros4 = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(URL_bd, usuario, clave);
            System.out.println("CODIGO" + getCodigo());
            if (con != null) {
                sql = "SELECT * FROM `tbl_medicamentos_liquidos`  WHERE `cod_lugares` = " + codigo;
                comando = con.createStatement();
                registros4 = comando.executeQuery(sql);
                while (registros4.next()) {
                    longitud = registros4.getInt("cod_lugares");
                }
                registros = comando.executeQuery(sql);

                medicamentos = new String[longitud];
                while (registros.next()) {
                    medicamentos[i] = registros.getString("nombre");
                    System.out.println(medicamentos[i] + i);
                    i = i + 1;
                }
                //con.close();
            } else {
                System.out.println("Conexion No Exitosa");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
