insert into locality
	values (1,'Namur',5000),(2,'Jambes',5100),(3,'Gembloux',5030),(4,'Godinne',5530);
    
insert into zone
	values (1,'Namur-Centre'),(2,'Bomel'),(3,'Jambes');
    
insert into station
	values (1,'Grognon',5,2,10,12,str_to_date('10-oct-2002','%e-%b-%Y'),true,null,'Rue Fernand Golenvaux',1,1),
    (2,'Maurice Servais',4,2,8,10,str_to_date('22-apr-2005','%e-%b-%Y'),false,null,'Rue des Brasseurs',1,1),
    (3,'Piscine',5,3,12,14,str_to_date('01-jul-2012','%e-%b-%Y'),true,null,'Rue de Enhaive',2,3);

insert into entreprise
	values (1,'Scott','Scotland'),(2,'Merckx','Belgium'),(3,'La Pierre','USA');

insert into employee 
	values (1,'Minet','Martin',null,str_to_date('01-jan-2000','%e-%b-%Y'),0499872360,081452653,'martin.minet@gmail.be',str_to_date('14-jun-1992','%e-%b-%Y'),false,'rue du chateau',5,'Préposé',null,true,null,2,4,1,null),
    (2,'Morre','Anthony','M',str_to_date('06-aug-2012','%e-%b-%Y'),0491549679,081648511,'anthony.morre@gmail.be',str_to_date('02-dec-1992','%e-%b-%Y'),false,'rue olympique',13,'Transporteur',True,null,null,null,3,null,1);
    
    
insert into bike
	values (1,false,str_to_date('01-dec-2003','%e-%b-%Y'),1,2),(2,true,str_to_date('10-jun-2008','%e-%b-%Y'),2,3),(3,false,str_to_date('20-aug-2018','%e-%b-%Y'),3,3);
    
insert into transport_order
	values (1,str_to_date('12-dec-2012','%e-%b-%Y'),1,2,1,2,null),(2,str_to_date('13-dec-2012','%e-%b-%Y'),2,2,3,null,2);
    
insert into workshop
	values (1,'Atelier caserne'),(2,'Atelier jambes'); 