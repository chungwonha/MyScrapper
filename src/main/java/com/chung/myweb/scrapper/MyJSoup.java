/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chung.myweb.scrapper;

import org.jsoup.select.Elements;

/**
 *
 * @author Chung
 */
public interface MyJSoup {
    public abstract Elements getElements(String pUrl, String pValueForElementsByClass);
}
