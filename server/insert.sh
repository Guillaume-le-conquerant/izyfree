#!/bin/bash


#curl -XDELETE http://5.135.83.124/v1/offre/id/1
#curl -XDELETE http://5.135.83.124/v1/offre/id/2
#curl -XDELETE http://5.135.83.124/v1/offre/id/3

#curl -XDELETE http://5.135.83.124/v1/entreprise/id/1
#curl -XDELETE http://5.135.83.124/v1/entreprise/id/2
#curl -XDELETE http://5.135.83.124/v1/entreprise/id/3

curl -XPOST -H "Content-type: application/json" -d '{"intitule":"dev python", "dateDeb":"01/04/2018", "dateFin":"28/04/2018", "listeMots":"dev?python?projet"}' http://5.135.83.124/v1/offre
curl -XPOST -H "Content-type: application/json" -d '{"intitule":"expert python", "dateDeb":"10/04/2018", "dateFin":"23/06/2018", "listeMots":"dev?java?projet"}' http://5.135.83.124/v1/offre
curl -XPOST -H "Content-type: application/json" -d '{"intitule":"dev php symphony", "dateDeb":"10/09/2018", "dateFin":"31/12/2018", "listeMots":"dev?php?symphony"}' http://5.135.83.124/v1/offre
curl -XPOST -H "Content-type: application/json" -d '{"name":"google","nomContact":"googleNom", "prenomContact":"googlePrenom", "tel":"0327888888", "email":"google@google.com", "fonctionsContact":"google", "profilRecherche":"truc", "ville":"Lille", "champLibre":"ouais"}' http://5.135.83.124/v1/entreprise
curl -XPOST -H "Content-type: application/json" -d '{"name":"iut","nomContact":"iut", "prenomContact":"lille1", "tel":"0327855888", "email":"iut@iut.com", "fonctionsContact":"iut", "profilRecherche":"etudiant", "ville":"Villeneuve", "champLibre":"ouais"}' http://5.135.83.124/v1/entreprise
curl -XPOST -H "Content-type: application/json" -d '{"name":"izyfree","nomContact":"izy", "prenomContact":"free", "tel":"0327882228", "email":"izy@free.com", "fonctionsContact":"dzeigb", "profilRecherche":"dev python", "ville":"Lille", "champLibre":"ouais"}' http://5.135.83.124/v1/entreprise

