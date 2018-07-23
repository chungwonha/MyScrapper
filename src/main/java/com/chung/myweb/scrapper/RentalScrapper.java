/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chung.myweb.scrapper;

import com.chung.myweb.scrapper.entity.Scrap;
import com.chung.myweb.scrapper.model.ScrappingInfo;
import com.chung.myweb.scrapper.repository.ScrapRepository;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Chung
 */
@Component
public class RentalScrapper implements Scrapper {

    private static final Logger logger = LoggerFactory.getLogger(RentalScrapper.class.getName());

    private String url;
    private String valueForElementsByClass;
    private String website;
    private String dataType = "RENTAL LISTING";

    @Autowired
    ScrapRepository scrapRepository;

    @Autowired
    private MyJSoup myJsoup;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public MyJSoup getMyJsoup() {
        return myJsoup;
    }

    public void setMyJsoup(MyJSoup myJsoup) {
        this.myJsoup = myJsoup;
    }

    @Override
    public int scrapeAndSave(ScrappingInfo scrappingInfo) {
        logger.debug("------scrapeAndSave begins-------");
        if (scrappingInfo != null) {
            List<Scrap> rentalList = new ArrayList();

            Elements cardDetailsElement = myJsoup.getElements(scrappingInfo.getUrlToScrap(),
                    scrappingInfo.getValueForElementsByClass());

            Iterator iter = cardDetailsElement.iterator();
            logger.debug("cardDetailsElement.size(): {}", cardDetailsElement.size());

            while (iter.hasNext()) {

                Element e1 = (Element) iter.next();
                logger.info(e1.text());
                Scrap scrap = new Scrap();
                scrap.setData(e1.text());
                scrap.setSourceSite(this.website);
                scrap.setDataType(this.dataType);
                rentalList.add(scrap);
            }
            this.save(rentalList);

            logger.debug("rentalList.size()", rentalList.size());

            return rentalList.size();
        } else {
            return 0;
        }
    }

    public void save(List<Scrap> pList) {
        logger.debug("save begins");
        Iterator iter = pList.iterator();
        while (iter.hasNext()) {

            Scrap scrap = (Scrap) iter.next();
            logger.debug("scrap.id: {}", scrap.getData());
            this.scrapRepository.save(scrap);
        }

        logger.debug("save ends");

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getValueForElementsByClass() {
        return valueForElementsByClass;
    }

    public void setValueForElementsByClass(String valueForElementsByClass) {
        this.valueForElementsByClass = valueForElementsByClass;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public static void main(String[] s) {

        RentalScrapper rentalScrapper = new RentalScrapper();
        //rentalScrapper.print();
//        rentalScrapper.setUrl("https://hotpads.com/loudoun-county-va/townhomes-for-rent");
//        rentalScrapper.setValueForElementsByClass("ListingCard-content-container");
//        rentalScrapper.scrapeAndSave("chunghaster@gmail.com");
//        
//        rentalScrapper.setUrl("https://www.padmapper.com/apartments/arlington-va");
//        rentalScrapper.setValueForElementsByClass("_24vHs _2gvFD");
//        rentalScrapper.scrapeAndSave("chunghaster@gmail.com");
//        
//        
//        rentalScrapper.setUrl("https://washingtondc.craigslist.org/search/nva/apa");
//        rentalScrapper.setValueForElementsByClass("result-row");
//        rentalScrapper.scrapeAndSave("chunghaster@gmail.com");

//        rentalScrapper.setUrl("https://www.doorsteps.com/search/herndon-va");
//        rentalScrapper.setValueForElementsByClass("listing-item__info-container");
//        rentalScrapper.scrapeAndSave("chunghaster@gmail.com");
//          rentalScrapper.setUrl("https://www.trulia.com/for_rent/Virginia_Beach,VA/SINGLE-FAMILY_HOME,TOWNHOUSE_type/");
//        rentalScrapper.setValueForElementsByClass("backgroundBasic");//srp-item-price");
//        rentalScrapper.scrapeAndSave("chunghaster@gmail.com");
//        
//         rentalScrapper.setUrl("https://www.realtor.com/apartments/Herndon_VA");
//        rentalScrapper.setValueForElementsByClass("srp-item-body");//srp-item-price");
//        rentalScrapper.scrapeAndSave("chunghaster@gmail.com");
    }
}
