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

        }
    }

    public void limpiar() {
        per = new PersonaM();
    }

    public void listar() {
        try {
            listadoPer = dao.listarTodos();
        } catch (Exception e) {

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
