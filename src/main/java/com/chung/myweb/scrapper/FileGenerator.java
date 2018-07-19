/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chung.myweb.scrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Chung
 */
public class FileGenerator {
    
    Logger logger = LoggerFactory.getLogger(FileGenerator.class);
    
    private ScrapperFileGeneratorConfig fileConfig;
    
    
    public FileGenerator(ScrapperFileGeneratorConfig pFileConfig){
        this.fileConfig=pFileConfig;
    }
    
    /**
     *
     * @param pListToWrite
     */
    
   public void write(List pListToWrite) {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        String pattern = "MMMMM_DD_yyyy_HH_mm_ss";
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        logger.debug(date);

        try {
            objectMapper.writeValue(new File(this.fileConfig.getFileLocation()+this.fileConfig.getFileName()+date), pListToWrite);
        } catch (IOException e) {
                logger.error(e.getMessage());
        }
    }
}
