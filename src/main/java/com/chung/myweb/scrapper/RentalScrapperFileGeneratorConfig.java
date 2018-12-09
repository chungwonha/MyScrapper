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
public class RentalScrapperFileGeneratorConfig implements ScrapperFileGeneratorConfig{
    
    private String fileName;
    private String fileLocation;
    
    @Override
    public String getFileName() {
       return fileName;
    }

    @Override
    public void setFileName(String pFileName) {
       this.fileName = pFileName;
    }

    @Override
    public String getFileLocation() {
       return this.fileLocation;
    }

    @Override
    public void setFileLocation(String fileLocation) {
        this.fileLocation=fileLocation;
    }
    
}
