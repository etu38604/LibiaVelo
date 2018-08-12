
Drop table transport_Order;
Drop table employee;
Drop table historical;
Drop table reparation_record;
Drop table bike;
Drop table station;
Drop table workshop;
Drop table entreprise;
Drop table locality;


create table Locality (
	id int not null,
    noun varchar(45) not null,
    postalCode int not null,
    constraint idLocality_pk primary key (id),
    constraint locality_nounPostalCode_unique unique (noun,postalCode) )
    ENGINE = InnoDB ;
    
    create table Entreprise (
	id int not null,
	noun varchar(45) not null,
    location varchar(45) not null,
    constraint idEntreprise_pk primary key(id) )
    ENGINE = InnoDB ;
    
    create table Workshop (
	id int not null,
    place varchar(45) not null,
    constraint idWorkshop_pk primary key (id) )
    ENGINE = InnoDB ;

create table Station (
	id int not null,
    noun varchar(45) not null,
    nbBikeMinWarn int not null,
    nbBikeMinContr int not null,
    nbBikeMaxWarn int not null,
    nbBikeMaxContr int not null,
    dateCreation date not null,
    isCover boolean not null,
    coordGPS varchar(45),
    street varchar(45) not null,
    id_Locality int not null,
    constraint idStation_PK primary key (id),
    constraint Station_nbBikeMin_check check(nbBikeMinWarn > nbBikeMinContr),
    constraint Station_nbBikeMax_check check(nbBikeMaxWarn < nbBikeMaxContr),
    constraint Station_id_Locality_FK foreign key (id_Locality) references Locality (id))
    ENGINE = InnoDB ;


    
create table Bike (
	id int not null,
    isDamaged boolean not null,
    datePurchase date not null,
    id_Station int,
    id_Entreprise int not null,
    constraint idBike_pk primary key (id),
	constraint bike_id_Station_FK foreign key (id_Station) references Station (id),
	constraint bike_id_Entreprise_FK foreign key (id_Entreprise) references Entreprise (id))
    ENGINE = InnoDB ;
    
create table Reparation_record (
	id int not null,
    dateBegin date not null,
    dateEnd date not null,
    note varchar(45),
    workOrdrer varchar(45),
    isDownGraded boolean not null,
    id_Bike int not null,
    id_Workshop int,
    constraint idReparation_pk primary key (id),
    constraint date_check check(dateBegin < dateEnd),
    constraint reparation_id_Bike_FK foreign key (id_Bike) references Bike (id),
	constraint reparation_id_Workshop_FK foreign key (id_Workshop) references Workshop (id))
    ENGINE = InnoDB ;
	
    
create table Historical (
	dateArrival date not null,
    dateLeaving date not null,
    id_Station int not null,
    id_Bike int not null,
    constraint date_check check(dateArrival < dateLeaving),
    constraint historical_id_Bike_FK foreign key (id_Bike) references Bike (id),
	constraint Historical_id_Station_FK foreign key (id_Station) references Station (id))
    ENGINE = InnoDB ;
    
create table Employee (
	id int not null,
    lastName varchar(45) not null,
    firstName varchar(45) not null,
    initialNameSupp char(3),
    dateHiring date not null,
    phonePrivate int,
    phonePro int not null,
    mail varchar(45),
    birthday date not null,
    isPartTimeWork boolean not null,
    street varchar(45) not null,
    streetNumber int not null,
    workType varchar(45) not null,
    isDriverSpecialLicense boolean,
    isZoneInCharge boolean,
    id_Workshop int,
    id_Station int,
    id_Locality int not null,
    inCharge_Employee int,
    constraint idEmployee_pk primary key (id),
    constraint employee_id_Workshop_FK foreign key (id_Workshop) references Workshop (id),
	constraint employee_id_Station_FK foreign key (id_Station) references Station (id),
	constraint employe_id_Locality_FK foreign key (id_Locality) references Locality (id),
	constraint employe_inCharge_Employee_FK foreign key (inCharge_Employee) references Employee (id))
    ENGINE = InnoDB ;
    
create table Transport_Order (
	id int not null,
    dateTransport date not null,
    id_Bike int not null,
    id_Employee int not null,
    id_Station_issuance int not null,
    id_Station_origin int,
    id_Station_destination int,
    constraint idTransport_PK primary key(id),
    constraint transport_id_Bike_FK foreign key (id_Bike) references Bike (id),
	constraint transport_id_Station_FK foreign key (id_Station_issuance) references Station (id),
	constraint transport_id_Employee_FK foreign key (id_Employee) references Employee (id))
    ENGINE = InnoDB ;
    
