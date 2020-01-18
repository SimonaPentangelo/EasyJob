use EasyJob;
insert into EasyJob.Inoccupato (Username,Password,Email,Nome,Cognome,DataNascita,Residenza,Città,Curriculum)
values ("gabriele1997","ciaociao","gpisapia97@gmail.com","Gabriele","Pisapia","01/06/1997","Via napoleone 3","Battipaglia","resources\\gabriele1997\\curriculum1.pdf"),
("simona00","ciao12345","spentangelo@gmail.com","Simona","Pentangelo","12/09/1999","Via cinque maggio","Angri","resources\\simona00\\curriculum2.pdf"),
("gigi94","ciao994","gigi230294@gmail.com","Gigi Jr", "Del Monaco","23/02/1994","Via Ruta 72","Caserta","resources\\gigi94\\curriculum3.pdf");


insert into EasyJob.Azienda(Username,Password,Email,NomeAzienda,LogoAzienda,Dipendenti,DataFondazione,Indirizzo,PIva,Banned)
values ("google2019","google19","google@gmail.com","Google","resources\\google2019\\google.png",2000,"10/02/1960","Via mezzocannone,19","01234567ABC",false),
("facebook2019","facebook19","facebook@gmail.com","Facebook","resources\\facebook2019\\facebook.png",1800,"12/03/1999","Via montesi 14","91202849CDB",false),
("Amazon2019","amazon19","amazon@gmail.com","Amazon","resources\\amazon2019\\logo3.jpg",640000,"5/07/1994","Via New York 87","55206740FDH",true),
("NextSoft2019","nextsoft19","nextsoft@gmail.com","NextSoft","resources\\nextsoft2019\\logo4.jpg",5000,"12/12/2008", "Via romagnola 67","78066431JTS",false);

insert into EasyJob.Moderatore(Username,Password,Email)
values ("Moderatore1","modmod","moderatoreEJ@gmail.com"),
("Moderatore2","modmod","moderatoreEJ2@gmail.com");

insert into EasyJob.Amministratore(Username,Password,Email)
values ("Admin1", "admin00","adminEasyJob@gmail.com"),
("Admin2","admin01","adminEasyJob2@gmail.com");

insert into EasyJob.Segnalazione(Titolo,Corpo,Azienda,Moderatore)
values ("Seganalazione Amazon", "Segnalo Amazon per non aver rispettato le regole della pubblicazione.",3,1),
("Segnalazione NextSoft","Segnalazione di NextSoft per spam non consentiti",4,1);

insert into EasyJob.Annuncio (Azienda,Titolo,Descrizione,Requisiti,TipoContratto,DataPubblicazione,Città)
values(1,"Reclutamento Web Programmer","Stiamo ampliando il numero di dipendenti e abbiamo bisogno di web programmer","Laurea triennale in informatica,cerficazione inglese B2,2 anni di esperienza.","Indeterminato","23/12/2019","Salerno"),
(2,"Database Manager", "Cerchiamo un gestore di database per il nostro team di back-end","Laurea triennale in informatica,conoscenza teorica e pratica di MySQL,NoSQL,8 anni di esperienza.","Part-time","22/12/2019","Battipaglia"),
(2,"Front-end Programmer","Stiamo reclutando stagisti per sviluppo di front-end.","laurea triennale in informatica,inglese B2.","Stage","24/10/2019","Fisciano"),
(4,"Assunzione personale di ricerca","Abbiamo attrezzato un nuovo laboratorio di biologia molecolare e siamo alla ricerca di uno staff adeguato.","Dottorato in Biologia,certificazione inglese C1,","Indeterminato","28/12/2019","Cremona");

insert into EasyJob.Invito (Titolo,Corpo,Annuncio,Azienda,Inoccupato)
values("Colloquio Google","Sig. Gabriele Pisapia,dopo analisi e revisioni di tutti i curriculum che abbiamo ricevuto, abbiamo deciso che lei è la persona più adeguata per il ruolo che stiamo cercando.",1,1,1),
("Colloquio Facebook","Buongiorno Sig.na Simona Pentangelo, lei è stata tra i pochi candidati che ci ha colpito di più, le vorremmo proporre un colloquio.",2,2,2),
("Colloquio Nextsoft", "Buon pomeriggio Sig. Gigi Del Monaco, la invitiamo ad un colloquio poichè il suo profilo è risultato idoneo ai nostri requisiti. ",4,4,3);




insert into EasyJob.Tag(NomeTag,Annuncio)
values("Informatica",1),
("Ingegneria Informatica",1),
("Biologia",4),
("Informatica",2);

insert into EasyJob.Candidatura(Inoccupato, Annuncio,DataCandidatura)
values (1,1,"28/12/2019"),
(2,2,"12/12/2019"),
(3,4,"14/11/2019"),
(2,1,"17/10/2019");
