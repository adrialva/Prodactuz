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
public class Administradores {
    int codAdmin;
    int codLugares;
    String nombre;
    String apellido;
    String td;
    int numDoc;
    String correo;
    int telMovl;

    public Administradores() {
    }

    public Administradores(int codAdmin, int codLugares, String nombre, String apellido, String td, int numDoc, String correo, int telMovl) {
        this.codAdmin = codAdmin;
        this.codLugares = codLugares;
        this.nombre = nombre;
        this.apellido = apellido;
        this.td = td;
        this.numDoc = numDoc;
        this.correo = correo;
        this.telMovl = telMovl;
    }

    public int getCodAdmin() {
        return codAdmin;
    }

    public int getCodLugares() {
        return codLugares;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTd() {
        return td;
    }

    public int getNumDoc() {
        return numDoc;
    }

    public String getCorreo() {
        return correo;
    }

    public int getTelMovl() {
        return telMovl;
    }

    public void setCodAdmin(int codAdmin) {
        this.codAdmin = codAdmin;
    }

    public void setCodLugares(int codLugares) {
        this.codLugares = codLugares;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTd(String td) {
        this.td = td;
    }

    public void setNumDoc(int numDoc) {
        this.numDoc = numDoc;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelMovl(int telMovl) {
        this.telMovl = telMovl;
    }
    
    @Override
    public String toString(){
        return "Administradores{ Nombre: " +nombre+ ", apellido: "+ apellido+", cod adm:"+codAdmin+
                ", Tip_doc: "+ td +", num doc: "+ numDoc +", correo"+correo+", tel movil: "+telMovl+", cod lugar: "+codLugares+"}";
    }
    public List<Administradores> listarTdsAdms(){
        ConexionBD conex =new ConexionBD();
        List<Administradores> listaAdms = new ArrayList<>();
        String sql = "select * from tbl_admin";
        try{
            ResultSet registros = conex.consultarBD(sql);
            Administradores a;
            while (registros.next()){
                a= new Administradores();
                a.setCodLugares(registros.getInt("Cod_lugares"));
                a.setCodAdmin(registros.getInt("Cod_admin"));
                a.setNombre(registros.getString("Nombre"));
                a.setApellido(registros.getString("Apellido"));
                a.setTd(registros.getString("Tipo_doc"));
                a.setNumDoc(registros.getInt("Num_doc"));
                a.setCorreo(registros.getString("Correo"));
                a.setTelMovl(registros.getInt("Tel_movil"));
                listaAdms.add(a);
            }
        }catch(SQLException ex){
            System.out.println("Error en consulta");
            
        }finally{
            conex.cerrarconexion();
        }
        return listaAdms;
    }
    public boolean GuardarAdmins(){
        ConexionBD conex = new ConexionBD();
         String sql = "INSERT INTO tbl_admin(Cod_lugares, Cod_admin, Nombre, Apellido, Tipo_doc, Num_doc, correo, tel_movil)"
                + "VALUES('"+this.codLugares+"', '"+this.codAdmin+"',\"" + this.nombre+"\", \""+this.apellido +"\", \""+ this.td+"\", '"+
                this.numDoc+"', \""+this.correo+"\", '"+this.telMovl+"');";
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
    public boolean ActualizarAdmins(){
        ConexionBD conex = new ConexionBD();
        String sql = "UPDATE tbl_admin SET nombre=\""
                +this.nombre+"\", apellido= \""+this.apellido+"\",Tipo_doc = \""+this.td+"\", Num_doc = '"+this.numDoc+"', correo = \""
                +this.correo+"\", Cod_lugares= '"+this.codLugares+"', Tel_movil='"+this.telMovl+"' where Cod_admin= '"+this.codAdmin+"';";
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
    public boolean EliminarAdmins(){
        String sql="DELETE FROM tbl_admin WHERE Cod_admin = '"+this.codAdmin+"'";
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
