/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chung.myweb.scrapper;


import com.chung.myweb.scrapper.model.ScrappingInfo;
import javax.activation.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Chung
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RentalScrapperTest {
    
    private static final Logger logger = LoggerFactory.getLogger(RentalScrapperTest.class);
        
    @Autowired
    RentalScrapper rentalScrapper =new RentalScrapper();
    

    @Before
    public void setup(){
//        EmbeddedDatabaseBuilder emb = new EmbeddedDatabaseBuilder();
//        emb.setType(EmbeddedDatabaseType.H2).addScript("schema.sql").build();
        
        this.rentalScrapper.setMyJsoup(new MyJSoupMock());
    }
    
    @Test
    public void testScrapAndTest(){
        ScrappingInfo scrappingInfo = new ScrappingInfo();
        scrappingInfo.setUrlToScrap("https://www.padmapper.com/apartments/arlington-va");
        scrappingInfo.setValueForElementsByClass("_24vHs _2gvFD");
        int i = this.rentalScrapper.scrapeAndSave(scrappingInfo);
        
        assertThat(i).isEqualTo(23);
        
    }
    
//    @Test
//    public void testPrint(){
//        this.rentalScrapper.print();
//        assertThat(1).isEqualTo(1);
//    }
    
}
