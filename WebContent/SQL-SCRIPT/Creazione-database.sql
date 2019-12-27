create schema if not exists EasyJob;
use EasyJob;

create table if not exists EasyJob.Inoccupato (
	idUser int not null auto_increment,
	Username varchar(20) not null,
	Password varchar(16) not null,
	Email varchar(50) not null,
	Nome varchar(50) not null,
	Cognome varchar(50) not null,
	DataNascita varchar(50) not null,
	Residenza varchar(50) not null,
	Città  varchar(20) not null,
	Curriculum varchar(200) not null,
	primary key(idUser)
);

create table if not exists EasyJob.Azienda (
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

create table if not exists EasyJob.Amministratore (
	idUser int not null auto_increment,
	Username varchar(20) not null,
	Password varchar(16) not null,
	Email varchar(50) not null,
    primary key(idUser)
);

create table if not exists EasyJob.Moderatore (
	idUser int not null auto_increment,
	Username varchar(20) not null,
	Password varchar(16) not null,
	Email varchar(50) not null,
	primary key(idUser)
);

create table if not exists EasyJob.Segnalazione (
	Titolo varchar(60) not null,
	Corpo varchar(10000) not null,
	Azienda int not null,	
	Moderatore int not null,
	foreign key(Azienda)
	references EasyJob.Azienda (idUser)
	on delete cascade
	on update cascade,
	foreign key(Moderatore)
	references EasyJob.Moderatore (idUser)
	on delete no action
	on update no action,
	primary key(Azienda, Moderatore)
);

create table if not exists EasyJob.Invito (
	Titolo varchar(60) not null,
	Corpo varchar(10000) not null,
	Azienda int not null,	
	Inoccupato int not null,
	foreign key(Azienda)
	references EasyJob.Azienda (idUser)
	on delete cascade
	on update cascade,
	foreign key(Inoccupato)
	references EasyJob.Inoccupato (idUser)
	on delete cascade
	on update cascade,
	primary key(Azienda, Inoccupato)
);

create table if not exists EasyJob.Annuncio (
	idAnnuncio int not null auto_increment,
	Azienda int not null,
	Titolo varchar(50) not null,
	Descrizione varchar(7000) not null,
	Requisiti varchar(3000) not null,
	TipoContratto varchar(20) not null,
	DataPubblicazione varchar(15)not null,
	CittÃ  varchar(50) not null,
	foreign key(Azienda)
	references EasyJob.Azienda (idUser)
	on delete cascade
	on update cascade,
	primary key(idAnnuncio)
);

create table if not exists EasyJob.Tag (
	NomeTag varchar(40) not null,
	Annuncio int not null,
	foreign key(Annuncio)
	references EasyJob.Annuncio (idAnnuncio)
	on delete cascade
	on update cascade
);

create table if not exists EasyJob.Candidatura (
	Inoccupato int not null,
	Annuncio int not null,
	Data varchar(15) not null,
	foreign key(Annuncio)
	references EasyJob.Annuncio (idAnnuncio)
	on delete cascade
	on update cascade,
	foreign key(Inoccupato)
	references EasyJob.Inoccupato (idUser)
	on delete cascade
	on update cascade,
	primary key(Annuncio, Inoccupato)
);