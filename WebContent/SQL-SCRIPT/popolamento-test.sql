use EasyJobtest;
DELETE FROM Inoccupato;
DELETE FROM Azienda;
DELETE FROM Moderatore;
DELETE FROM Amministratore;
DELETE FROM Segnalazione;
DELETE FROM Annuncio;
DELETE FROM Invito;
DELETE FROM Tag;
DELETE FROM Candidatura;
insert into EasyJobtest.Inoccupato (Username,Password,Email,Nome,Cognome,DataNascita,Residenza,Città,Curriculum)
values ("gabriele1997","ciaociao","gpisapia97@gmail.com","Gabriele","Pisapia","01/06/1997","Via napoleone 3","Battipaglia","resources\gabriele1997\curriculum1.pdf"),
("simona00","ciao12345","spentangelo@gmail.com","Simona","Pentangelo","12/09/1999","Via cinque maggio","Angri","resources\simona00\curriculum2.pdf");


insert into EasyJobtest.Azienda(Username,Password,Email,NomeAzienda,LogoAzienda,Dipendenti,DataFondazione,Indirizzo,PIva,Banned)
values ("google2019","google19","google@gmail.com","Google","resources\google2019\logo1.jpg",2000,"10/02/1960","Via mezzocannone,19","01234567ABC",false),
("facebook2019","facebook19","facebook@gmail.com","Facebook","resources\facebook2019\logo2.jpg",1800,"12/03/1999","Via montesi 14","91202849CDB",false);

insert into EasyJobtest.Moderatore(Username,Password,Email)
values ("Moderatore1","modmod","moderatoreEJ@gmail.com"),
("Moderatore2","modmod","moderatoreEJ2@gmail.com");

insert into EasyJobtest.Amministratore(Username,Password,Email)
values ("Admin1", "admin00","adminEasyJob@gmail.com"),
("Admin2","admin01","adminEasyJob2@gmail.com");

insert into EasyJobtest.Segnalazione(Titolo,Corpo,Azienda,Moderatore)
values ("Seganalazione esempio1", "Segnalazione per esempio su prima azienda e primo moderatore",1,1),
("Segnalazione esempio2","Segnalazione per esempio su seconda azienda e primo moderatore",2,1);

insert into EasyJobtest.Annuncio (Azienda,Titolo,Descrizione,Requisiti,TipoContratto,DataPubblicazione,Città)
values(1,"Esempio annuncio 1","Annuncio della prima azineda","req1,req2,req3,...","Indeterminato","23/12/2019","Salerno"),
(2,"Esempio annuncio 2", "Annuncio della seconda azienda","req1,req2,...","Part-time","22/12/2019","Battipaglia"),
(2,"Esempio annuncio 3","Secondo annuncio della seconda azienda","req1,req2,req3,...","Stage","24/10/2019","Fisciano"),
(1,"Test per canidad","test per la candidatura","req1,2,3","stage","28/12/2019","Battipaglia");

insert into EasyJobtest.Invito (Titolo,Corpo,Annuncio,Azienda,Inoccupato)
values("Esempio invito1","Invito per esempio su prima azienda e primo inoccupato",1,1,1),
("Esempio invito2","Invito per esempio su prima azienda e secondo inoccupato",1,1,2),
("Esempio invito 3", "Invito su seconda azienda e secondo inoccupato",2,2,2),
("Esempio invito 4", "Invito su seconda azienda e primo inoccupato",2,2,1);



insert into EasyJobtest.Tag(NomeTag,Annuncio)
values("Informatica",1),
("Ingegneria Informatica",1),
("Biologia",2),
("Informatica",4);

insert into EasyJobtest.Candidatura(Inoccupato, Annuncio,DataCandidatura)
values (1,1,"28/12/2019"),
(1,2,"12/12/2019"),
(2,2,"14/11/2019"),
(2,1,"17/10/2019");
