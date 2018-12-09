/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chung.myweb.scrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Chung
 */
public class FileGenerator {

    Logger logger = LoggerFactory.getLogger(FileGenerator.class);

    private ScrapperFileGeneratorConfig fileConfig;

    public FileGenerator(ScrapperFileGeneratorConfig pFileConfig) {
        this.fileConfig = pFileConfig;
    }

    /**
     *
     * @param pListToWrite
     */
    public void write(List pListToWrite) {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String pattern = "MMMMM_DD_yyyy_HH_mm_ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        logger.debug(date);

        try {
            objectMapper.writeValue(new File(this.fileConfig.getFileLocation() + this.fileConfig.getFileName() + date), pListToWrite);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void write(Object obj) {
        ArrayList al = new ArrayList();
        al.add(obj);
        this.write(al);
    }

    public void write(String s) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(this.fileConfig.getFileLocation()+"/"+this.fileConfig.getFileName()));
            writer.write(s);

            writer.close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(FileGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
