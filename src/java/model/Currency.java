/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author David
 */
@Entity
public class Currency implements Serializable, CurrencyDTO {

    private static final long serialVersionUID = 1L;
    @Id
    private String currencyName;
    private String currencyAbbreviation;
    private double currencyValue;
    
    public Currency(){}
    
    public Currency(String currencyName, String currencyAbbreviation, double currencyValue){
        this.currencyName=currencyName;
        this.currencyAbbreviation=currencyAbbreviation;
        this.currencyValue=currencyValue;
    }
    
    @Override
    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    @Override
    public String getCurrencyAbbreviation() {
        return currencyAbbreviation;
    }

    public void setCurrencyAbbreviation(String currencyAbbreviation) {
        this.currencyAbbreviation = currencyAbbreviation;
    }

    @Override
    public double getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(double currencyValue) {
        this.currencyValue = currencyValue;
    }
    
    
}
