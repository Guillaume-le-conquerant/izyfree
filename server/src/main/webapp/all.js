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
				return;
			}	
		}
		alert("non");
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
			afficheUser(data);
			$('#inscription').hide();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('postUser error: ' + textStatus);
		}
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
            "prix": prix
		}),
		success : function(data, textStatus, jqXHR) {
			afficheUser(data);
			$('#formulaire_freelance').hide();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('postUser error: ' + textStatus);
		}
	});
}

function postEntreprise(name, firstname, email, pwd) {
	console.log("postUserGeneric " + 'v1/entreprise')
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : 'v1/entreprise/',
		dataType : "json",
		data : JSON.stringify({
			"name" : name,
			"firstname" : firstname,
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
		afficheListFreelance(data);
	});
}

function afficheListFreelance(data) {
	var ul = document.createElement('ul');
	ul.className = "list-group";
	var index = 0;
	for (index = 0; index < data.length; ++index) {
	    var li = document.createElement('li');
	    li.className = "list-group-item";
		li.innerHTML = data[index].email;
		ul.appendChild(li);
	}
	$("#reponse").html(ul);
}

function freelanceStringify(freelance) {
    return freelance.id + ". " + freelance.name + " &lt;" + freelance.email + "&gt;" + " (" + freelance.alias + ")";
}
