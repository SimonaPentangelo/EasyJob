use EasyJob;
insert into EasyJob.Inoccupato (Username,Password,Email,Nome,Cognome,DataNascita,Residenza,Citt�,Curriculum)
values ("gabriele1997","ciaociao","gpisapia97@gmail.com","Gabriele","Pisapia","01/06/1997","Via napoleone 3","Battipaglia","c:/user/admin/file.pdf"),
("simona00","ciao12345","spentangelo@gmail.com","Simona","Pentangelo","12/09/1999","Via cinque maggio","Angri","c:/user/admin/file.pdf");


insert into EasyJob.Azienda(Username,Password,Email,NomeAzienda,LogoAzienda,Dipendenti,DataFondazione,Indirizzo,PIva,Banned)
values ("google2019","google19","google@gmail.com","Google","c:/user/admin/file.jpg",2000,"10/02/1960","Via mezzocannone,19","01234567ABC",false),
("facebook2019","facebook19","facebook@gmail.com","Facebook","c:/user/admin/file.jpg",1800,"12/03/1999","Via montesi 14","91202849CDB",false);

insert into EasyJob.Moderatore(Username,Password,Email)
values ("Moderatore1","modmod","moderatoreEJ@gmail.com"),
("Moderatore2","modmod","moderatoreEJ2@gmail.com");

insert into EasyJob.Amministratore(Username,Password,Email)
values ("Admin1", "admin00","adminEasyJob@gmail.com"),
("Admin2","admin01","adminEasyJob2@gmail.com");

insert into EasyJob.Segnalazione(Titolo,Corpo,Azienda,Moderatore)
values ("Seganalazione esempio1", "Segnalazione per esempio su prima azienda e primo moderatore",1,1),
("Segnalazione esempio2","Segnalazione per esempio su seconda azienda e primo moderatore",2,1);

insert into EasyJob.Invito (Titolo,Corpo,Azienda,Inoccupato)
values("Esempio invito1","Invito per esempio su prima azienda e primo inoccupato",1,1),
("Esempio invito2","Invito per esempio su prima azienda e secondo inoccupato",1,2),
("Esempio invito 3", "Invito su seconda azienda e secondo inoccupato",2,2),
("Esempio invito 4", "Invito su seconda azienda e primo inoccupato",2,1);

insert into EasyJob.Annuncio (Azienda,Titolo,Descrizione,Requisiti,TipoContratto,DataPubblicazione,Citt�)
values(1,"Esempio annuncio 1","Annuncio della prima azineda","req1,req2,req3,...","Indeterminato","23/12/2019","Salerno"),
(2,"Esempio annuncio 2", "Annuncio della seconda azienda","req1,req2,...","Part-time","22/12/2019","Battipaglia"),
(2,"Esempio annuncio 3","Secondo annuncio della seconda azienda","req1,req2,req3,...","Stage","24/10/2019","Fisciano");

insert into EasyJob.Tag(NomeTag,Annuncio)
values("Informatica",1),
("Ingegneria Informatica",1),
("Biologia",2);

insert into EasyJob.Candidatura(Inoccupato, Annuncio)
values (1,1),
(1,2),
(2,2),
(2,1);
