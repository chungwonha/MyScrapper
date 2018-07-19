/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chung.myweb.scrapper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 *
 * @author Chung
 */
@Component
@Primary
public class MyJSoupProxy implements MyJSoup{
    private static final Logger logger = LoggerFactory.getLogger(MyJSoupProxy.class.getName());
    
    @Override
    public Elements getElements(String pUrl,String pValueForElementsByClass) {
        try {
            Document doc = Jsoup.connect(pUrl).get();
            //logger.debug(doc.body().html());
            return doc.getElementsByClass(pValueForElementsByClass);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }
    
}
