/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chung.myweb.scrapper;

import com.chung.myweb.scrapper.model.ScrappingInfo;
import org.springframework.stereotype.Component;

/**
 *
 * @author Chung
 */
@Component
public interface Scrapper {
    
    public abstract int scrapeAndSave(ScrappingInfo scrappingInfo);
    public abstract int scrapeAndSaveEntirePage(ScrappingInfo scrappingInfo);
    
}
