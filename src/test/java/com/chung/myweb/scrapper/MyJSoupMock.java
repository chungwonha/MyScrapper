
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chung.myweb.scrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.lang.model.element.Element;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Chung
 */
public class MyJSoupMock implements MyJSoup{
    private static final Logger logger = LoggerFactory.getLogger(MyJSoupMock.class.getName());
    
    @Override
    public Elements getElements(String pUrl, String pValueForElementsByClass) {
              logger.debug("---------getElements begins-----------");
      try {
         
         File file = new File("src/test/resources/data/padmappertest.html");
                               
         Document doc = Jsoup.parse(file,null);
        return doc.getElementsByClass(pValueForElementsByClass);        
         
      } catch (FileNotFoundException ex) {
            logger.error(ex.getMessage());
      }catch(IOException ex){
            logger.error(ex.getMessage());
      }
        return null;
    }

    @Override
    public String getEntirePageHtml(String pUrl) {
       return "test";
    }
    
}
