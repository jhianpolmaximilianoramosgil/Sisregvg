package controlador;

import dao.PersonaD;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.PersonaM;


@Named(value = "personaC")
@SessionScoped
public class PersonaC implements Serializable {

    private PersonaM per;
    private PersonaD dao;
    private List<PersonaM> listadoPer;
    
    public PersonaC() {
        per = new PersonaM();
        dao = new PersonaD();
    }

    public void registrar() throws Exception {
        try {
            dao.registrar(per);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con Exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en registrarC " + e.getMessage());
        }
    }

      public void modificar() throws Exception {
        try {
            dao.modificar(per);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }
    
     public void eliminar(PersonaM pers) throws Exception{
        try {            
            dao.eliminar(per);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en eliminarC " + e.getMessage());
        }
    }
      
     
    public void limpiar() {
        per = new PersonaM();
    }

    public void listar() {
        try {
            listadoPer = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }
    }

    
    //Gnerado

    public PersonaM getPer() {
        return per;
    }

    public void setPer(PersonaM per) {
        this.per = per;
    }

    public PersonaD getDao() {
        return dao;
    }

    public void setDao(PersonaD dao) {
        this.dao = dao;
    }

    public List<PersonaM> getListadoPer() {
        return listadoPer;
    }

    public void setListadoPer(List<PersonaM> listadoPer) {
        this.listadoPer = listadoPer;
    }
  
}
