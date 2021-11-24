/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADRIANA
 */
public class Lugares {
    int codLugares;
    String nombre;
    String direccion;
    int tel_Fijo;

    public Lugares() {
    }

    public Lugares(int codLugares, String nombre, String direccion, int tel_Fijo) {
        this.codLugares = codLugares;
        this.nombre = nombre;
        this.direccion = direccion;
        this.tel_Fijo = tel_Fijo;
    }

    public int getCodLugares() {
        return codLugares;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTel_Fijo() {
        return tel_Fijo;
    }

    public void setCodLugares(int codLugares) {
        this.codLugares = codLugares;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTel_Fijo(int tel_Fijo) {
        this.tel_Fijo = tel_Fijo;
    }
    
    @Override
    public String toString(){
        return "Lugares{ Nombre: " +nombre+ ", direccion: "+ direccion+
                ", Tel_fijo: "+tel_Fijo +", Codigo: "+ codLugares +"}";
    }
    
    public List<Lugares> listarTodLugares(){
        ConexionBD conexion  = new ConexionBD();
        List<Lugares> listaLugares = new ArrayList<>();
        String sql = "select * from tbl_lugares";
        try{
            ResultSet registros = conexion.consultarBD(sql);
            Lugares l;
            while(registros.next()){
                l= new Lugares();
                l.setCodLugares(registros.getInt("Cod_lugares"));
                l.setNombre(registros.getString("Nombre"));
                l.setDireccion(registros.getString("Direccion"));
                l.setTel_Fijo(registros.getInt("Telefono_fijo"));
                listaLugares.add(l);
            }
        }
        catch(SQLException ex){
            System.out.println("Error en consulta de productos:" + ex.getMessage());
        }
        finally{
            conexion.cerrarconexion();
        }
        return listaLugares;
    }
    public boolean GuardarLugar(){
        ConexionBD conex = new ConexionBD();
        String sql = "INSERT INTO tbl_lugares(Nombre, Direccion, Telefono_fijo, Cod_lugares)"
                + "VALUES(\"" + this.nombre+"\", \""+this.direccion +"\", '"+ this.tel_Fijo+"', '"+
                this.codLugares+"');";
        if(conex.setAutoCommitBD(false)){
            if(conex.insertarBD(sql)){
                conex.commitBD();
                conex.cerrarconexion();
                return true;
            } else{
                conex.rollbackBD();
                conex.commitBD();
                return false;
            }
        }else{
            conex.cerrarconexion();
            return false;
        }
    }
    
    public boolean ActualizarLugar(){
        ConexionBD conex = new ConexionBD();
        String sql = "UPDATE tbl_lugares SET nombre=\""
                +this.nombre+"\", direccion= \""+this.direccion+"\",Telefono_fijo='"
                +this.tel_Fijo+"' where Cod_lugares= '"+this.codLugares+"';";
        //tener cuidado con los espacios en el String sql(SINTAXIS SQL)
        System.out.println(sql);
        if (conex.setAutoCommitBD(false)) {
            if (conex.actualizarBD(sql)) {
                conex.commitBD();
                conex.cerrarconexion();
                return true;
            } else {
                conex.rollbackBD();
                conex.cerrarconexion();
                return false;
            }
        } else {
            conex.cerrarconexion();
            return false;
        }
    }
    public boolean EliminarLug(){
        String sql="DELETE FROM tbl_lugares WHERE Cod_lugares = '"+this.codLugares+"'";
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
