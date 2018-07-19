/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chung.myweb.scrapper;

import com.chung.myweb.scrapper.model.ScrappingInfo;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Chung
 */
@Controller
@RequestMapping("/scrapper")
public class ScrapperController {

    private static final Logger logger = LoggerFactory.getLogger(ScrapperController.class.getName());
    private ScrappingInfo[] scrappigInfos;
    
    @PostConstruct
    public void init() throws IOException {
        logger.debug("init");
            this.scrappigInfos = this.getScrappingInfo();
    }

    @GetMapping("/open")
    public String greetingForm(Model model) {
        logger.debug("greetingForm");
        
        model.addAttribute("greeting", new Greeting());
        model.addAttribute("scrappingInfo",this.scrappigInfos);
        return "greeting";
    }

//    @PostMapping("/greeting")
//    public String greetingSubmit(@ModelAttribute Greeting greeting) {
//        return "greetingresult";
//    }

    @RequestMapping("/start")
    public String start(@RequestParam("scrapper") String pScrapper) {
        //Scrapper scrapper = this.scrapperFactory.getScrapper(pScrapper);
        //scrapper.scrapeAndSave("chunghaster@gmail.com");
        return "result";
    }

    public ScrappingInfo[] getScrappingInfo() {
        logger.debug("----loadScrappingInfo begins------");
        ObjectMapper mapper = new ObjectMapper();

        try {

            // Convert JSON string from file to Object
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("json/scrappingInfo.json").getFile());

            //ScrappingInfo[] scrappigInfos = mapper.readValue(new File("D:\\myjava\\MyScrapper\\src\\main\\resources\\json\\scrappingInfo.json"), ScrappingInfo[].class);
            scrappigInfos = mapper.readValue(file, ScrappingInfo[].class);

            for (ScrappingInfo scrappigInfo:scrappigInfos) {
                 logger.debug(scrappigInfo.getUrlToScrap());
            }
            logger.debug("----loadScrappingInfo ends------");
            return scrappigInfos;
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  new ScrappingInfo[0];
    }

}
