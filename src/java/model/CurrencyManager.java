/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author David
 */
@ManagedBean
public class CurrencyManager implements Serializable {
    
    private static final long serialVersionUID = 16247164405L;
    private CurrencyFacade currencyFacade;
    private Conversation conversation;
    private CurrencyDTO fromCurrency;
    private CurrencyDTO toCurrency;
    private Map<String, CurrencyDTO> allCurrencies= new HashMap<>();
    private double convertionResult;
    
    @PersistenceContext(unitName = "cPU")
    private EntityManager em;
    
    public void convertCurrensies(){
        if(fromCurrency!=null && toCurrency!=null){
            
        }
        if(em==null){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("cPU");
            em = emf.createEntityManager();
        }
        createCurrency("Kronor", "kr", 7);
        createCurrency("Dollar", "$", 14);
        createCurrency("Euro", "€", 9);
        
        Query query = em.createQuery("SELECT e FROM Currency e");
        Collection<CurrencyDTO> col=(Collection<CurrencyDTO>) query.getResultList();
        for(CurrencyDTO c : col){
            allCurrencies.put(c.getCurrencyName(), c);
        }
    }
    
    public void createCurrency(String currencyName, String currencyAbbreviation, double currencyValue){
        try{
            CurrencyDTO cu=new Currency(currencyName, currencyAbbreviation, currencyValue);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(cu);
        tx.commit();
        allCurrencies.put(cu.getCurrencyName(), cu);
        }
        catch(Exception e){}
    }
    
    public Map<String, CurrencyDTO> getAllCurrencies() {
        return allCurrencies;
    }

    public CurrencyDTO getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(CurrencyDTO fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public CurrencyDTO getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(CurrencyDTO toCurrency) {
        this.toCurrency = toCurrency;
    }
    
}


