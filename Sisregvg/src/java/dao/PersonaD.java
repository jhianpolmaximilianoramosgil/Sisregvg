package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import modelo.PersonaM;

public class PersonaD extends Conexion implements ICRUD<PersonaM> {

    @Override
    public void registrar(PersonaM per) throws Exception {
        // dni_per nom_per   nac_per  tel_per asig_mes   mes_per   obs_per
        String sql = "insert into PERSONA (NOMPER,APEPER,DNIPER,CELPER,EMAPER,SEXPER,CARPER) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, per.getNombre());
            ps.setString(2, per.getApellido());
            ps.setString(3, per.getDni());
            ps.setString(4, per.getCelular());
            ps.setString(5, per.getEmail());
            ps.setString(6, per.getSexo());
            ps.setString(7, per.getCargo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Ingresar Personero Dao " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(PersonaM per) throws Exception {
        String sql = "update PERSONA set NOMPER=?, APEPER=?,DNIPER=?,CELPER=?,EMAPERr=?,SEXPER=?,CARPERr=?, where IDPER=? ";
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
            System.out.println("Error al Modificar Persona: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int codigo) throws Exception {
        String sql = "delete from PERSONA where IDPER=?";
    }
    // dni_per nom_per   nac_per  tel_per asig_mes   mes_per   obs_per

    @Override
    public List listarTodos() throws Exception {
        List<PersonaM> listado = null;
        PersonaM per;
        String sql = "select * from PERSONA";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                per = new PersonaM();
                per.setCodigo(rs.getInt("IDPER"));
                per.setNombre(rs.getString("NOMPER"));
                per.setApellido(rs.getString("APEPER"));
                per.setDni(rs.getString("DNIPER"));
                per.setCelular(rs.getString("CELPER"));
                per.setEmail(rs.getString("EMAPER"));
                per.setSexo(rs.getString("SEXPER"));
                per.setCargo(rs.getString("CARPER"));
   
                listado.add(per);
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println("Error en listarTodos Dao" + e.getMessage());
        } finally {
            this.cerrar();
        }
        return listado;
    }

}