#!/bin/bash

curl -XPOST -H "Content-type: application/json" -d '{"intitule":"dev python", "dateDeb":"01/04/2018", "dateFin":"28/04/2018", "listeMots":"dev?python?projet"}' http://5.135.83.124/v1/offre
curl -XPOST -H "Content-type: application/json" -d '{"intitule":"expert python", "dateDeb":"10/04/2018", "dateFin":"23/06/2018", "listeMots":"dev?java?projet"}' http://5.135.83.124/v1/offre
curl -XPOST -H "Content-type: application/json" -d '{"intitule":"dev php symphony", "dateDeb":"10/09/2018", "dateFin":"31/12/2018", "listeMots":"dev?php?symphony"}' http://5.135.83.124/v1/offre
