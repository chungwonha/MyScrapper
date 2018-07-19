/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Chung
 * Created: Jul 16, 2018
 */


-- drop table scrap;

create table scrap

(

   id integer not null,

   source_site varchar(255) not null,

   data varchar(1000),

   data_type varchar2(50),
 
   primary key(id)

);

alter table scrap alter column data varchar2(1000);