/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADRIANA
 */
public class MedicamentosLiquidos {
    int cod_Liquidos;
    int cod_Lugares;
    String nombre;
    int cantidad_Frasco;
    float precio_Frasco;
    String descripcion;

    public MedicamentosLiquidos() {
    }

    public MedicamentosLiquidos(int cod_Liquidos, int cod_Lugares, String nombre, int cantidad_Frasco, float precio_Frasco, String descripcion) {
        this.cod_Liquidos = cod_Liquidos;
        this.cod_Lugares = cod_Lugares;
        this.nombre = nombre;
        this.cantidad_Frasco = cantidad_Frasco;
        this.precio_Frasco = precio_Frasco;
        this.descripcion = descripcion;
    }

    public int getCod_Liquidos() {
        return cod_Liquidos;
    }

    public int getCod_Lugares() {
        return cod_Lugares;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad_Frasco() {
        return cantidad_Frasco;
    }

    public float getPrecio_Frasco() {
        return precio_Frasco;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCod_Liquidos(int cod_Liquidos) {
        this.cod_Liquidos = cod_Liquidos;
    }

    public void setCod_Lugares(int cod_Lugares) {
        this.cod_Lugares = cod_Lugares;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad_Frasco(int cantidad_Frasco) {
        this.cantidad_Frasco = cantidad_Frasco;
    }

    public void setPrecio_Frasco(float precio_Frasco) {
        this.precio_Frasco = precio_Frasco;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Override
    public String toString(){
        return "Medicamento liquido{ Nombre: " +nombre+ ", descripcion: "+ descripcion+
                ", Cantidad: "+cantidad_Frasco +", Precio frasco: "+ precio_Frasco + ", Codigo: "+cod_Liquidos+"Codigo lugar:"+cod_Lugares+"}";
    }
    
    public boolean GuardarLiq() throws SQLException{
        ConexionBD conex = new ConexionBD();
        String sql = "INSERT INTO tbl_medicamentos_liquidos(Nombre, Descripcion, Cantidad_frasco, Precio_frasco, Cod_lugares)"
                + "VALUES(\"" + this.nombre+"\", \""+this.descripcion +"\", '"+ this.cantidad_Frasco+"', '"+this.precio_Frasco+"', '"+
                this.cod_Lugares+"');";
        if (conex.setAutoCommitBD(false)){
            if(conex.insertarBD(sql)){
                conex.commitBD();
                conex.cerrarconexion();
                return true;
            } else{
                conex.rollbackBD();
                conex.cerrarconexion();
                return false;
            }
            } else{
            conex.cerrarconexion();
            return false;
        }
    }
    public boolean ActualizarLiq(){
        ConexionBD conex = new ConexionBD();
        String sql = "UPDATE tbl_medicamentos_liquidos SET Nombre = \""+this.nombre+"\", Cantidad_frasco = '"+this.cantidad_Frasco+"', Precio_frasco= '"+this.precio_Frasco
                +"', Cod_lugares = '"+this.cod_Lugares+"' WHERE Cod_liquidos = '"+this.cod_Liquidos+"';";
        System.out.println(sql);
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
    public boolean EliminarLiq(){
        String sql="DELETE FROM tbl_medicamentos_liquidos WHERE Cod_liquidos = '"+this.cod_Liquidos+"'";
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
