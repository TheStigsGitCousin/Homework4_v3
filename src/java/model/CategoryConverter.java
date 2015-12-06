/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author David
 */
@ManagedBean(name = "categoryConverterBean") 
@FacesConverter(value = "categoryConverter")
public class CategoryConverter implements Converter {

    @PersistenceContext(unitName = "cPU")
    // I include this because you will need to 
    // lookup  your entities based on submitted values
    private transient EntityManager em;
    
    public CategoryConverter(){
        if(em==null){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("cPU");
            em = emf.createEntityManager();
        }
    }

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component,
            String value) {
      // This will return the actual object representation
      // of your Category using the value (in your case 52) 
      // returned from the client side
      return em.find(CurrencyDTO.class, value);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        //This will return view-friendly output for the dropdown menu
        return ((CurrencyDTO) o).getCurrencyName(); 
    }
}
