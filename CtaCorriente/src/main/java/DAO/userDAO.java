package DAO;

import Entity.Persona;
import Entity.Rol;
import Entity.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userDAO extends Conexion{
    
    public boolean validarUser(Usuario us)throws Exception{
        boolean pase = false;
        try {
            this.Conectar();
            String query = "select count(usuario) conta from dbouser where usuario = ?";
            PreparedStatement smt = this.getCnx().prepareStatement(query);
            smt.setString(1, us.getUsuario());
            ResultSet rt = smt.executeQuery();
            int contador = 0;
            while(rt.next()){
                contador = rt.getInt("conta");
            }
            if(contador > 0){
                pase = true;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }finally{
            this.Desconectar();
        }
        return pase;
    }
    
    public boolean validarPass(Usuario us)throws Exception{
        boolean pase = false;
        try {
            this.Conectar();
            String query = "select count(pass) conta from dbouser where pass = ?";
            PreparedStatement smt = this.getCnx().prepareStatement(query);
            smt.setString(1, us.getPass());
            ResultSet rt = smt.executeQuery();
            int contador = 0;
            while(rt.next()){
                contador = rt.getInt("conta");
            }
            if(contador > 0){
                pase = true;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }finally{
            this.Desconectar();
        }
        return pase;
    }
    public boolean validarTodo(Usuario us)throws Exception{
        boolean pase = false;
        try {
            this.Conectar();
            String query = "select count(*) conta from dbouser where pass = ? and usuario=?";
            PreparedStatement smt = this.getCnx().prepareStatement(query);
            smt.setString(1, us.getPass());
            smt.setString(2, us.getUsuario());
            ResultSet rt = smt.executeQuery();
            int contador = 0;
            while(rt.next()){
                contador = rt.getInt("conta");
            }
            if(contador > 0){
                pase = true;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }finally{
            this.Desconectar();
        }
        return pase;
    }
    public Usuario llenarUser(Usuario us)throws Exception{
        Usuario usu = new Usuario();
        try {
            this.Conectar();
            String query = "select p.nombre,p.apellido,r.rol from dbouser u inner join dbopersona p on u.id_persona = p.id_persona inner join dborol r on p.id_rol = r.id_rol where u.usuario = ? and u.pass = ?";
            PreparedStatement smt = this.getCnx().prepareStatement(query);
            smt.setString(1, us.getUsuario());
            smt.setString(2, us.getPass());
            ResultSet rt = smt.executeQuery();
            
            while(rt.next()){
                Persona p = new Persona();
                Rol r = new  Rol();
                p.setNombre(rt.getString("p.nombre"));
                p.setApellido(rt.getString("p.apellido"));
                r.setRol(rt.getString("r.rol"));
                p.setRol(r);
                usu.setPersona(p);
            }
            
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }finally{
            this.Desconectar();
        }
        return usu;
    }
    
}
