/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chung.myweb.scrapper.repository;

import com.chung.myweb.scrapper.entity.Scrap;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chung
 */
@Repository
public interface ScrapRepository extends CrudRepository<Scrap,Long>{

     List<Scrap> findBySourceSite(String sourceSite);
    
   
}
