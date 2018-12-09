/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chung.myweb.scrapper;

/**
 *
 * @author Chung
 */
public interface ScrapperFileGeneratorConfig {
    
    public String getFileName();

    public void setFileName(String fileName);

    public String getFileLocation();

    public void setFileLocation(String fileLocation);
}
