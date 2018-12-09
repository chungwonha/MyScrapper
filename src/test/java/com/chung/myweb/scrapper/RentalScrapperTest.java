/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chung.myweb.scrapper;


import com.chung.myweb.scrapper.model.ScrappingInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    
    @Mock
    MyJSoupProxy myJsoup;
    

//    @Before
//    public void setup(){
//        this.rentalScrapper.setMyJsoup(new MyJSoupMock());
//    }
    
    @Test
    public void testScrapAndTest(){
        ScrappingInfo scrappingInfo = new ScrappingInfo();
        scrappingInfo.setUrlToScrap("https://www.padmapper.com/apartments/arlington-va");
        scrappingInfo.setValueForElementsByClass("_24vHs _2gvFD");
        Elements elements = this.getElements("_24vHs _2gvFD");
        when(myJsoup.getElements("https://www.padmapper.com/apartments/arlington-va", "_24vHs _2gvFD")).thenReturn(elements);
        this.rentalScrapper.setMyJsoup(myJsoup);
        int i = this.rentalScrapper.scrapeAndSave(scrappingInfo);
        
        assertThat(i).isEqualTo(23);
        
    }
    
    public Elements getElements(String pValueForElementsByClass){
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
    
//    @Test
//    public void testPrint(){
//        this.rentalScrapper.print();
//        assertThat(1).isEqualTo(1);
//    }
    
}
