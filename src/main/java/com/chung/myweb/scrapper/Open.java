/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chung.myweb.scrapper;

import com.chung.myweb.scrapper.model.ScrappingInfo;

/**
 *
 * @author Chung
 */
public class Open {
    private ScrappingInfo[] scrappigInfos;
    private String selectedSite;
    
    public Open() {
        
    }
    
    public String getSelectedSite() {
        return selectedSite;
    }

    public void setSelectedSite(String selectedSite) {
        this.selectedSite = selectedSite;
    }
    
    
    public ScrappingInfo[] getScrappigInfos() {
        return scrappigInfos;
    }

    public void setScrappigInfos(ScrappingInfo[] scrappigInfos) {
        this.scrappigInfos = scrappigInfos;
    }
    
    
}
