create schema if not exists EasyJobtest;
use EasyJobtest;

create table if not exists EasyJobtest.Inoccupato (
	idUser int not null auto_increment,
	Username varchar(20) not null,
	Password varchar(16) not null,
	Email varchar(50) not null,
	Nome varchar(50) not null,
	Cognome varchar(50) not null,
	DataNascita varchar(50) not null,
	Residenza varchar(50) not null,
	Città varchar(20) not null,
	Curriculum varchar(200) not null,
	primary key(idUser)
);

create table if not exists EasyJobtest.Azienda (
	idUser int not null auto_increment,
	Username varchar(20) not null,
	Password varchar(16) not null,
	Email varchar(50) not null,
	NomeAzienda varchar(50) not null,
	LogoAzienda varchar(200) not null,
	Dipendenti int not null,
	DataFondazione varchar(50) not null,
	Indirizzo varchar(30) not null,
	PIva varchar(11) not null,
	Banned boolean not null,
	primary key(idUser)
);

create table if not exists EasyJobtest.Amministratore (
	idUser int not null auto_increment,
	Username varchar(20) not null,
	Password varchar(16) not null,
	Email varchar(50) not null,
    primary key(idUser)
);

create table if not exists EasyJobtest.Moderatore (
	idUser int not null auto_increment,
	Username varchar(20) not null,
	Password varchar(16) not null,
	Email varchar(50) not null,
	primary key(idUser)
);

create table if not exists EasyJobtest.Segnalazione (
	Titolo varchar(60) not null,
	Corpo varchar(10000) not null,
	Azienda int not null,	
	Moderatore int not null,
	foreign key(Azienda)
	references EasyJobtest.Azienda (idUser)
	on delete cascade
	on update cascade,
	foreign key(Moderatore)
	references EasyJobtest.Moderatore (idUser)
	on delete no action
	on update no action,
	primary key(Azienda, Moderatore)
);

create table if not exists EasyJobtest.Annuncio (
	idAnnuncio int not null auto_increment,
	Azienda int not null,
	Titolo varchar(50) not null,
	Descrizione varchar(7000) not null,
	Requisiti varchar(3000) not null,
	TipoContratto varchar(20) not null,
	DataPubblicazione varchar(15)not null,
	Città varchar(50) not null,
	foreign key(Azienda)
	references EasyJobtest.Azienda (idUser)
	on delete cascade
	on update cascade,
	primary key(idAnnuncio)
);

create table if not exists EasyJobtest.Invito (
	Titolo varchar(60) not null,
	Corpo varchar(10000) not null,
	Annuncio int not null,	
	Inoccupato int not null,
	Azienda int not null,
	foreign key(Annuncio)
	references EasyJobtest.Annuncio (idAnnuncio)
	on delete cascade
	on update cascade,
	foreign key(Inoccupato)
	references EasyJobtest.Inoccupato (idUser)
	on delete cascade
	on update cascade,
	foreign key(Azienda)
	references EasyJobtest.Azienda (idUser)
	on delete cascade
	on update cascade,
	primary key(Annuncio, Inoccupato)
);



create table if not exists EasyJobtest.Tag (
	NomeTag varchar(40) not null,
	Annuncio int not null,
	foreign key(Annuncio)
	references EasyJobtest.Annuncio (idAnnuncio)
	on delete cascade
	on update cascade
);

create table if not exists EasyJobtest.Candidatura (
	Inoccupato int not null,
	Annuncio int not null,
	DataCandidatura varchar(15) not null,
	foreign key(Annuncio)
	references EasyJobtest.Annuncio (idAnnuncio)
	on delete cascade
	on update cascade,
	foreign key(Inoccupato)
	references EasyJobtest.Inoccupato (idUser)
	on delete cascade
	on update cascade,
	primary key(Annuncio, Inoccupato)
);