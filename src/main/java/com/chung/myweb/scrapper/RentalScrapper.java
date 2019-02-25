/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chung.myweb.scrapper;

import com.chung.myweb.scrapper.entity.Scrap;
import com.chung.myweb.scrapper.model.ScrappingInfo;
import com.chung.myweb.scrapper.repository.ScrapRepository;
import java.util.ArrayList;
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
    //@Qualifier("MyJSoupProxy")
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
        logger.debug("------scrapeAndSave begins this is develop branch-------");
        if (scrappingInfo != null) {
            List<Scrap> rentalList = new ArrayList();

            Elements cardDetailsElement = myJsoup.getElements(scrappingInfo.getUrlToScrap(),
                                                              scrappingInfo.getValueForElementsByClass());
           cardDetailsElement.stream().
                    forEach((Element el)->{
                    Scrap scrap = new Scrap();
                    scrap.setData(el.text());
                    scrap.setSourceSite(scrappingInfo.getUrlToScrap());
                    scrap.setDataType(this.dataType);
                    this.scrapRepository.save(scrap);
                });

            return cardDetailsElement.toArray().length;
        } else {
            return 0;
        }
    }

    @Override
    public int scrapeAndSaveEntirePage(ScrappingInfo scrappingInfo) {
         logger.debug("------scrapeAndSave begins-------");
        if (scrappingInfo != null) {
                

                String s = myJsoup.getEntirePageHtml(scrappingInfo.getUrlToScrap());
           
                Scrap scrap = new Scrap();
                scrap.setData(s);
                scrap.setSourceSite(scrappingInfo.getUrlToScrap());
                scrap.setDataType(this.dataType); 
                scrap.setSiteName(scrappingInfo.getSiteName());
                RentalScrapperFileGeneratorConfig rentalScrapperFileGeneratorConfig = new RentalScrapperFileGeneratorConfig();
                rentalScrapperFileGeneratorConfig.setFileName(scrappingInfo.getSiteName());
                rentalScrapperFileGeneratorConfig.setFileLocation("c:\\temp\\");
                
                FileGenerator fileGenerator = new FileGenerator(rentalScrapperFileGeneratorConfig);

                /*TODO:
                  save the file metadata info to DB
                 */

                fileGenerator.write(s);
                return 1;
                
        }
        return 0;
    }

    public void save(List<Scrap> pList) {
        logger.debug("save begins");
        pList.stream().forEach(scrap->{ this.scrapRepository.save(scrap);});
        logger.debug("save ends");
    }
    
     public void save(Scrap pScrap) {
         this.scrapRepository.save(pScrap);
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
