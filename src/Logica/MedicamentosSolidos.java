 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADRIANA
 */
public class MedicamentosSolidos {
    String nombre;
    String descripcion;
    int cantidad;
    float precio_tableta;
    float precio_caja;
    int codigoMedSol;
    int codigoLugSol;
    

    public MedicamentosSolidos() {
    }

    public MedicamentosSolidos(String nombre, String descripcion, int cantidad, float precio_tableta, float precio_caja, int codigoMedSol,int codigoLugSol) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio_tableta = precio_tableta;
        this.precio_caja = precio_caja;
        this.codigoMedSol = codigoMedSol;
        this.codigoLugSol= codigoLugSol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getPrecio_tableta() {
        return precio_tableta;
    }

    public float getPrecio_caja() {
        return precio_caja;
    }

    public int getCodigoMedSol() {
        return codigoMedSol;
    }

    public int getCodigoLugSol() {
        return codigoLugSol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio_tableta(float precio_tableta) {
        this.precio_tableta = precio_tableta;
    }

    public void setPrecio_caja(float precio_caja) {
        this.precio_caja = precio_caja;
    }

    public void setCodigoMedSol(int codigo) {
        this.codigoMedSol = codigoMedSol;
    }

    public void setCodigoLugSol(int codigoLugSol) {
        this.codigoLugSol = codigoLugSol;
    }
    
    @Override
    public String toString(){
        return "Medicamento solido{ Nombre: " +nombre+ ", descripcion: "+ descripcion+
                ", Cantidad: "+cantidad + ", Precio tableta: "+ precio_tableta+", Precio caja: "+
                precio_caja + ", Codigo medicamento: "+codigoMedSol+ ", Codigo Lugar:"+codigoLugSol+"}";
    }

    public boolean GuardaSol() throws SQLException{
       ConexionBD conex = new ConexionBD();
       String sql = "INSERT INTO tbl_medicamentos_solidos(Nombre, Descripcion, Cantidad, Precio_tableta, Precio_caja, Cod_lugares)"
               +"VALUES(\""+this.nombre+"\", \""+this.descripcion+"\", '"+this.cantidad+"', '"+this.precio_tableta+"', '"+this.precio_caja
               +"', '"+this.codigoLugSol+"');";
       if(conex.setAutoCommitBD(false)){
           if(conex.insertarBD(sql)){
               conex.commitBD();
               conex.cerrarconexion();
               return true;
           } else{
               conex.rollbackBD();
               conex.cerrarconexion();
               return false;
           }
       }else{
           conex.cerrarconexion();
           return false;
       }
    }
    
    public boolean ActualizarSol(){
        ConexionBD conex = new ConexionBD();
        String sql = "UPDATE tbl_medicamentos_solidos SET Nombre = \""+this.nombre+"\", Cantidad = \""+this.cantidad+"\", Precio_tableta= '"+this.precio_tableta
                +"', Precio_caja = '"+this.precio_caja+"', Cod_lugares = '"+this.codigoLugSol+"' WHERE Cod_solidos="+this.codigoMedSol+";";
        if (conex.setAutoCommitBD(false)){
            if(conex.actualizarBD(sql)){
                conex.commitBD();
                conex.cerrarconexion();
                return true;
            } else {
                conex.rollbackBD();
                conex.cerrarconexion();
                return false;
            }
        }else{
            conex.cerrarconexion();
            return false;
        }
    }
    public boolean EliminarSol(){
        String sql="DELETE FROM tbl_medicamentos_solidos WHERE Cod_solidos = "+this.codigoMedSol+"";
        ConexionBD conex = new ConexionBD();
        if(conex.setAutoCommitBD(false)){
            if(conex.actualizarBD(sql)){
                conex.commitBD();
                conex.cerrarconexion();
                return true;
            } else {
                conex.rollbackBD();
                conex.cerrarconexion();
                return false;
            }
        }else{
            conex.cerrarconexion();
            return false;
        }
    }
    
}
