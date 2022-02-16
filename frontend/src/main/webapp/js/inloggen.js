var login = document.getElementById('login');
var ingelogd = document.getElementById('ingelogd');
var ingelogdUsername = document.getElementById("ingelogdUsername");

document.querySelector("#registreer").addEventListener("click", registreer);
function registreer(){
	document.location.href = '/frontend/registreren.html';
}

function initPage(){
	ingelogdUsername.textContent  = sessionStorage.getItem("username");
	checkIngelogd();
}

function checkIngelogd(){
	if(sessionStorage.getItem("status") == "ingelogd"){
		login.style.display = "none";
		ingelogd.style.display = "block";
	}
}

function inloggen (form) {
    var username = form.username.value;
    
    var formData = new FormData(document.querySelector("#loginForm"));
    var encData = new URLSearchParams(formData.entries());

    fetch("/frontend/restservices/check", {method: 'POST', body: encData })
      .then(function(response) {
        if(response.ok) return response.json();
        else throw "Wrong username/password";
      })
      .then(function(myJson){
    	  if(myJson == "Admin" || myJson == "Klant"){
    		  window.sessionStorage.setItem("username", username);
    		  ingelogdUsername.textContent  = sessionStorage.getItem("username");
    		  window.sessionStorage.setItem("status", "ingelogd");
    		  window.sessionStorage.setItem("role", myJson);
    		  document.location.href = '/frontend/index.html';
    	  }
      })
      .catch(error => console.log(error));
}

function logout(){
	sessionStorage.removeItem("status");
	sessionStorage.removeItem("username");
	ingelogdUsername.textContent  = "Username";
	login.style.display = "block";
	ingelogd.style.display = "none";
	document.location.href = '/frontend/index.html';
}

window.onload = initPage;