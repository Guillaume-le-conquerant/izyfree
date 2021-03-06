function getUser(name) {
	getUserGeneric(name, "v1/user/");
}

function getUserGeneric(name, url) {
	$.getJSON(url + name, function(data) {
		afficheUser(data);
	});
}

function login() {
	/*getWithAuthorizationHeader("v1/login", function(data){
	    $("#connexion").hide();
	    afficheUser(data);
	});*/
	$.getJSON("/v1/freelance", function(data) {
		for(index=0; index < data.length ; ++index){
			if(data[index].email == $("#userlogin").val()){
				alert("connecté");
				$('#login').hide();
				$('#inscription').hide();
				$('#formulaire_entreprise').hide();
				$('#inscrire').hide();
				$('#connexion').hide();
				$('#isfreelance').hide();
				$('#isentreprise').hide();
				$('#simplificateur').hide();
				$('#formulaire_freelance').hide();
				$('#dernieres_offres').hide();
				$('#deconnexion').show();
				document.getElementById("nom2").value=data[index].name;
				document.getElementById("prenom2").value=data[index].firstname;
				document.getElementById("mail2").value=data[index].email;
				console.log(data[index].job);
				if(data[index].job != null){ document.getElementById("metier2").value=data[index].job; } else{document.getElementById("metier2").value=""; }
				if(data[index].mots != null){ document.getElementById("skills2").value=data[index].mots; } else{document.getElementById("skills2").value=""; }
				if(data[index].localisation != null){ document.getElementById("ville2").value=data[index].localisation; } else{document.getElementById("ville2").value=""; }
				if(data[index].phone != null){ document.getElementById("tel2").value=data[index].phone; } else{document.getElementById("tel2").value=""; }
				if(data[index].champLibre != null){ document.getElementById("champlibre2").value=data[index].champLibre; } else{document.getElementById("champlibre2").value=""; }
				if(data[index].conditions != null){ document.getElementById("conditions2").value=data[index].conditions; } else{document.getElementById("conditions2").value=""; }
				if(data[index].tarif != null){ document.getElementById("prix2").value=data[index].tarif; } else{document.getElementById("prix2").value=""; }
				$('#formulaire_moncompte').show();
				return;
			}	
		}
		alert("identifiant ou mot de passe incorrect");
	});
}

function loginAdmin() {
	/*getWithAuthorizationHeader("v1/login", function(data){
	    $("#connexion").hide();
	    afficheUser(data);
	});*/
	$.getJSON("/v1/staff", function(data) {
		for(index=0; index < data.length ; ++index){
			if(data[index].email == $("#userlogin").val() && data[index].isGoodPassword($("#passwdlogin"))){
				alert("connecté en tant qu'administrateur");
				$('#login').hide();
				$('#inscription').hide();
				$('#formulaire_entreprise').hide();
				$('#inscrire').hide();
				$('#connexion').hide();
				$('#isfreelance').hide();
				$('#isentreprise').hide();
				$('#simplificateur').hide();
				$('#formulaire_freelance').hide();
				$('#dernieres_offres').hide();
				$('#deconnexion').show();
				document.getElementById("nom2").value=data[index].name;
				document.getElementById("prenom2").value=data[index].firstname;
				document.getElementById("mail2").value=data[index].email;
				$('#formulaire_moncompte').show();
				return;
			}	
		}
		alert("identifiant ou mot de passe incorrect");
	});
}

function profile() {
	getWithAuthorizationHeader("v1/profile", function (data) {afficheUser(data);});
}

/*function getWithAuthorizationHeader(url, callback) {
 if($("#userlogin").val() != "") {
     $.ajax
     ({
       type: "GET",
       url: url,
       dataType: 'json',
       beforeSend : function(req) {
        req.setRequestHeader("Authorization", "Basic " + btoa($("#userlogin").val() + ":" + $("#passwdlogin").val()));
       },
       success: alert("succes"),
       error : function(jqXHR, textStatus, errorThrown) {
       			alert('error: ' + textStatus);
       		}
     });
     } else {
     $.getJSON(url, function(data) {
     	    afficheUser(data);
        });
     }
 }*/

/*function postUser(name, firstname, email, pwd) {
        postFreelance(firstname.toLowerCase(), name.toLowerCase(),email.toLowerCase(), pwd)
}*/

function postUserGeneric(name, alias, email, pwd, url) {
	console.log("postUserGeneric " + url)
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : url,
		dataType : "json",
		data : JSON.stringify({
			"name" : name,
			"alias" : alias,
			"email" : email,
			"password" : pwd,
			"id" : 0
		}),
		success : function(data, textStatus, jqXHR) {
			afficheUser(data);
			$('#inscription').hide();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('postUser error: ' + textStatus);
		}
	});
}

function postFreelance(name, firstname, email, pwd) {
	$.getJSON("/v1/freelance", function(data) {
		for(index=0; index < data.length ; ++index){
			if(data[index].email == email){
				alert("EMAIL DEJA UTILISÉ");
				return;
			}
		}

		console.log("postUserGeneric " + 'v1/freelance')
		$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : 'v1/freelance/',
			dataType : "json",
			data : JSON.stringify({
				"name" : name,
				"firstname" : firstname,
				"email" : email,
				"password" : pwd
			}),
			success : function(data, textStatus, jqXHR) {
				alert("Tu es inscris");
				$('#inscription').hide();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log('postUser error: ' + textStatus);
			}
		});
	});
}

function postFreelanceForm(name, firstname, email, job, mots, localisation, phone, ref, champLibre, conditions, prix) {
	console.log("postUserGeneric " + 'v1/freelance')
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : 'v1/freelance/',
		dataType : "json",
		data : JSON.stringify({
			"name" : name,
			"firstname" : firstname,
			"email" : email,
			"job" : job,
			"mots" : mots,
			"localisation" : localisation,
			"phone" : phone,
			"ref" : ref,
			"champLibre" : champLibre,
			"conditions" : conditions,
			"tarif": prix
		}),
		success : function(data, textStatus, jqXHR) {
			afficheUser(data);
			$('#formulaire_freelance').hide();
			alert("Tu es inscrit");
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('postUser error: ' + textStatus);
		}
	});
}

function postOffre(intitule, dateDeb, dateFin, nomEntreprise, champLibre){
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : 'v1/offre/',
		dataType : "json",
		data : JSON.stringify({
			"intitule":intitule,
			"dateDeb": dateDeb,
			"dateFin": dateFin,
			"nomEntreprise": nomEntreprise,
			"champLibre": champLibre
		}),
		success : function(data, textStatus, jqXHR) {
			$("#formulaire_entreprise").hide();
		},
		error : function(jqXHR, textStatus, errorThrown){
			console.log('postOffre error: ' + textStatus);
		}
		
	})
}

function postEntreprise(name, nomContact, prenomContact, tel, email, fonctionsContact, profilRecherche, ville, champLibre) {
	console.log("postUserGeneric " + 'v1/entreprise')
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : 'v1/entreprise/',
		dataType : "json",
		data : JSON.stringify({
			"name" : name,
			"nomContact" : nomContact,
			"prenomContact" : prenomContact,
			"tel": tel,
			"email" : email,
			"fonctionsContact": fonctionsContact,
			"profilRecherche": profilRecherche,
			"ville": ville,
			"champLibre": champLibre
		}),
		success : function(data, textStatus, jqXHR) {
			alert("Offre Enregistré");
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('postUser error: ' + textStatus);
		}
	});
}

function postStaff(name, alias, email, pwd, url) {
	console.log("postUserGeneric " + 'v1/staff')
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : 'v1/staff',
		dataType : "json",
		data : JSON.stringify({
			"name" : name,
			"alias" : alias,
			"email" : email,
			"password" : pwd
		}),
		success : function(data, textStatus, jqXHR) {
			afficheUser(data);
			$('#inscription').hide();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('postUser error: ' + textStatus);
		}
	});
}

function listUsers() {
	listUsersGeneric("v1/user/");
}

function listUsersGeneric(url) {
	$.getJSON(url, function(data) {
		afficheListUsers(data)
	});
}

function afficheUser(data) {
	console.log(data);
	$("#reponse").html(userStringify(data));
}

function afficheListUsers(data) {
	var ul = document.createElement('ul');
	ul.className = "list-group";
	var index = 0;
	for (index = 0; index < data.length; ++index) {
		var li = document.createElement('li');
		li.className = "list-group-item";
		li.innerHTML = userStringify(data[index]);
		ul.appendChild(li);
	}
	$("#reponse").html(ul);
}

function userStringify(user) {
	return user.id + ". " + user.name + " &lt;" + user.email + "&gt;" + " (" + user.alias + ")";
}

function listFreelance() {
	listFreelanceGeneric("v1/freelance/");
}

function listFreelanceGeneric(url) {
	$.getJSON(url, function(data) {
		affichelistFreelance(data);
	});
}

function affichelistFreelance(data) {
	var ul = document.createElement('ul');
	ul.className = "list-group";

	var index=0;
	for(index = 0; index<data.length; ++index){
		var li = document.createElement('li');
		li.className = "list-group-item";
		li.innerHTML = freelanceStringify(data[index]);
		ul.appendChild(li);
	}
	$("#reponse").html(ul);
}

function list3lastFreelance() {
	list3LastFreelanceGeneric("v1/freelance/");
}

function list3LastFreelanceGeneric(url) {
	$.getJSON(url, function(data) {
		affiche3LastFreelance(data);
	});
}

function affiche3LastFreelance(data) {
	var ul = document.createElement('ul');
	ul.className = "list-group";

	var index=0;
	if(data.length > 3){
		for(index = data.length-3; index<data.length; ++index){
			var li = document.createElement('li');
			li.className = "list-group-item";
			li.innerHTML = freelanceStringify(data[index]);
			ul.appendChild(li);
		}		
	} else {
		for(index = 0; index<=data.length-1; ++index){
			var li = document.createElement('li');
			li.className = "list-group-item";
			li.innerHTML = freelanceStringify(data[index]);
			ul.appendChild(li);
		}		
	}
	$(".derniers_profils").append(ul);
}

function freelanceStringify(freelance) {
	return freelance.name + ". " + freelance.firstname + " &lt;" + freelance.email + "&gt;" + " (" + freelance.id + ")";
}

function listMissions() {
	listMissionGeneric("v1/offre/");
}

function listMissionGeneric(url) {
	$.getJSON(url, function(data) {
		afficheListMissions(data);
	});
}

function afficheListMissions(data) {
	var ul = document.createElement('ul');
	ul.className = "list-group";
	var index = data.length;

	if(data.length < 3){
		for(index = data.length-1; index>=0; --index){
			var li = document.createElement('li');
			li.className = "list-group-item";
			li.innerHTML = missionsStringify(data[index]);
			ul.appendChild(li);
		}
	} else {
		for(index = data.length-1; index>data.length-3; --index){
			var li = document.createElement('li');
			li.className = "list-group-item";
			li.innerHTML = missionsStringify(data[index]);
			ul.appendChild(li);
		}	
	}
	$(".dernieres_missions").append(ul);
}

function listAllMission() {
	listAllMissionGeneric("v1/offre/");
}

function listAllMissionGeneric(url) {
	$.getJSON(url, function(data) {
		afficheAllMission(data);
	});
}

function afficheAllMission(data) {
	var ul = document.createElement('ul');
	ul.className = "list-group";

	var index=0;
	for(index = 0; index<data.length; ++index){
		var li = document.createElement('li');
		li.className = "list-group-item";
		li.innerHTML = missionsStringify(data[index]);
		ul.appendChild(li);
	}
	$("#reponsemission").html(ul);
}

function listMissionsSelonRecherche(recherche) {
	listMissionGenericSelonRecherche("v1/offre/", recherche);
}

function listMissionGenericSelonRecherche(url, recherche) {
	$.getJSON(url, function(data) {
		afficheMissionsSelonRecherche(data, recherche);
	});
}

function afficheMissionsSelonRecherche(data, recherche){
	var ul = document.createElement('ul');
	ul.className = "list-group";

	var index=0;
	for(index = 0; index<data.length; ++index){
		console.log(data.length);
		var li = document.createElement('li');
		li.className = "list-group-item";
		
		var list = data[index].champLibre;
		var nom = data[index].intitule;
		console.log("INTITULE : "+ nom);
		console.log("LIST : " + list);
		if(list===undefined){
		}
		else if(nom===undefined){}
		else if(list.includes(recherche) || nom.includes(recherche)){
			li.innerHTML = missionsStringify(data[index]);
			ul.appendChild(li);
		}
	}
	$("#reponsemission").html(ul);
}


function missionsStringify(mission) {

	return mission.intitule + ". " + mission.nomEntreprise + " (" + mission.id + ")" + "[" + mission.champLibre +"]";

}

function enregistrer(){
	$.getJSON("/v1/freelance", function(data) {
		for(index=0; index < data.length ; ++index){
			if(data[index].email == $("#userlogin").val()){
				$.ajax({
					type : 'PUT',
					contentType : 'application/json',
					url : 'v1/freelance/id/'+data[index].id,
					dataType : "json",
					data : JSON.stringify({
						"name" : $("#nom2").val(),
						"firstname" : $("#prenom2").val(),
						"email" : $("#mail2").val(),
						"job" : $("#metier2").val(),
						"mots" : $("#skills2").val(),
						"localisation" : $("#ville2").val(),
						"phone" : $("#tel2").val(),
						"champLibre" : $("#champlibre2").val(),
						"conditions" : $("#conditions2").val(),
						"tarif": $("#prix2").val()
					}),
					success : function(data, textStatus, jqXHR) {
						alert("enregistré");
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert("erreur d'enregistrement");
					}
				});
			}
		}
	});
}
