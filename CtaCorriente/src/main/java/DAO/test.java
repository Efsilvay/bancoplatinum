package DAO;
public class test {
    public static void main(String[] args) {
        try {
            Conexion cn = new Conexion();
            cn.Conectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
