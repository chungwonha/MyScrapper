/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chung.myweb.scrapper.model;

/**
 *
 * @author Chung
 */
public class ScrappingInfo {
    
    private String urlToScrap;
    private String valueForElementsByClass;
    private String siteName;
    
        
    public String getUrlToScrap() {
        return urlToScrap;
    }

    public void setUrlToScrap(String urlToScrap) {
        this.urlToScrap = urlToScrap;
    }

    public String getValueForElementsByClass() {
        return valueForElementsByClass;
    }

    public void setValueForElementsByClass(String valueForElementsByClass) {
        this.valueForElementsByClass = valueForElementsByClass;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    
    
    
    
}
