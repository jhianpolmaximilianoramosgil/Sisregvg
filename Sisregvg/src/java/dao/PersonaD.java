package dao;

//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.PersonaM;


public class PersonaD extends Conexion implements ICRUD<PersonaM> {

    @Override
    public void registrar(PersonaM per) throws Exception {
    
        String sql = "insert into PERSONA (NOMPER,APEPER,DNIPER,CELPER,EMAPER,SEXPER,CARPER) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, per.getNombre());
//           SimpleDateFormat forma = new SimpleDateFormat("dd/MM/yy");
//            ps.setString(3, forma.format(per.getNacimiento()));        
            ps.setString(2, per.getApellido());
            ps.setString(3, per.getDni());
            ps.setString(4, per.getCelular());
            ps.setString(5, per.getEmail());
            ps.setString(6, per.getSexo());
            ps.setString(7, per.getCargo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Ingresar Persona Dao " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(PersonaM per) throws Exception {
        String sql = "update PERSONA set NOMPER=?, APEPER=?,DNIPER=?,CELPER=?,EMAPERr=?,SEXPER=?,CARPER=?, where CODPER=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, per.getNombre());
            ps.setString(2, per.getApellido());
            ps.setString(3, per.getDni());
            ps.setString(4, per.getCelular());
            ps.setString(5, per.getEmail());
            ps.setString(6, per.getSexo());
            ps.setString(7, per.getCargo());
            ps.setInt(8, per.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Modificar PersonaD: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(PersonaM per) throws Exception {
        String sql = "delete from PERSONA where CODPER=?";
         try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);             
            ps.setInt(1, per.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en eliminarD" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }


    @Override
    public List listarTodos() throws Exception {
        List<PersonaM> listado = null;
        PersonaM pers;
        String sql = "select * from PERSONA";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pers = new PersonaM();
                pers.setCodigo(rs.getInt("CODPER"));
                pers.setNombre(rs.getString("NOMPER"));
                pers.setApellido(rs.getString("APEPER"));
                pers.setDni(rs.getString("DNIPER"));
                pers.setCelular(rs.getString("CELPER"));
                pers.setEmail(rs.getString("EMAPER"));
                pers.setSexo(rs.getString("SEXPER"));
                pers.setCargo(rs.getString("CARPER"));   
                listado.add(pers);
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println("Error en listarTodosD:" + e.getMessage());
        } finally {
            this.cerrar();
        }
        return listado;
    }

}
