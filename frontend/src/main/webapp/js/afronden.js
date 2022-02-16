var currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab
var adresID = null;
var succes = document.getElementById('succesmelding');
var form = document.getElementById('regForm');

function showTab(n) {
  // This function will display the specified tab of the form ...
  var x = document.getElementsByClassName("tab");
  x[n].style.display = "block";
  // ... and fix the Previous/Next buttons:
  if (n == 0) {
    document.getElementById("prevBtn").style.display = "none";
  } else {
    document.getElementById("prevBtn").style.display = "inline";
  }
  if (n == (x.length - 1)) {
    document.getElementById("nextBtn").innerHTML = "Submit";
  } else {
    document.getElementById("nextBtn").innerHTML = "Next";
  }
  // ... and run a function that displays the correct step indicator:
  fixStepIndicator(n)
}

function nextPrev(n) {
  // This function will figure out which tab to display
  var x = document.getElementsByClassName("tab");
  // Exit the function if any field in the current tab is invalid:
  if (n == 1 && !validateForm()) return false;
  // Hide the current tab:
  x[currentTab].style.display = "none";
  // Increase or decrease the current tab by 1:
  currentTab = currentTab + n;
  // if you have reached the end of the form... :
  if (currentTab >= x.length) {
    // ...the form gets submitted:
	stuurBestelling();
    return false;
  }
  // Otherwise, display the correct tab:
  showTab(currentTab);
}

function stuurBestelling(){
	var formData = new FormData();

	 formData.append("username", sessionStorage.getItem("username"));
	 formData.append("adresid", adresID);
	 
	 if(sessionStorage.getItem("winkelWagen") != null) {
		    var winkelWagen = JSON.parse(sessionStorage.getItem("winkelWagen"));
		    var totaal = 0;
		    for(var item in winkelWagen){
		    	var artikel = winkelWagen[item];
		    	var prijsTotaal = artikel.prijs*artikel.aantal;
		    	
		    	formData.append("regels", "{'productid': "+artikel.id+", 'aantal': "+artikel.aantal+", 'prijs': "+prijsTotaal+"}");
		    }}

	 var encData = new URLSearchParams(formData.entries());

	 fetch("/frontend/restservices/savebestelling", { method: "POST", body: encData })
	 	.then(function(response){
	 		if(response.ok) console.log("Besteld!");
	 		else throw "Er ging iets mis";
	    })
	    .then(function(){
	    	succes.style.display = "block";
	    	form.style.display = "none";
	    	sessionStorage.removeItem("winkelWagen");
	    })
	    .catch(error => console.log(error));
}

function validateForm() {
  // This function deals with validation of the form fields
  var x, y, i, valid = true;
  /*
	 * x = document.getElementsByClassName("tab"); y =
	 * x[currentTab].getElementsByTagName("input"); // A loop that checks every
	 * input field in the current tab: for (i = 0; i < y.length; i++) { // If a
	 * field is empty... if (y[i].value == "") { // add an "invalid" class to
	 * the field: y[i].className += " invalid"; // and set the current valid
	 * status to false: valid = false; } }
	 */
  // If the valid status is true, mark the step as finished and valid:
  if (valid) {
    document.getElementsByClassName("step")[currentTab].className += " finish";
  }
  return valid; // return the valid status
}

function fixStepIndicator(n) {
  // This function removes the "active" class of all steps...
  var i, x = document.getElementsByClassName("step");
  for (i = 0; i < x.length; i++) {
    x[i].className = x[i].className.replace(" active", "");
  }
  // ... and adds the "active" class to the current step:
  x[n].className += " active";
}

function initPage(){
	var username = sessionStorage.getItem("username");
	
	fillKlantGegevens(username);
	fillProducten();
}

function fillKlantGegevens(username){
	fetch('/frontend/restservices/klant/gegevens/'+username, {method: 'GET'})
	.then(response => response.json())
	.then(function(myJson){
		console.log(myJson)
		adresID = myJson.adres.id;
		document.getElementById("naam").value = myJson.naam;
		document.getElementById("straat").value = myJson.adres.straat;
		document.getElementById("huisnummer").value = myJson.adres.straatnummer;
		if(myJson.adres.toevoeging != null){
			document.getElementById("toevoeging").value = myJson.adres.toevoeging;
		}
		document.getElementById("postcode").value = myJson.adres.postcode;
		document.getElementById("plaats").value = myJson.adres.plaats;
	})
}

function fillProducten(){
	if(sessionStorage.getItem("winkelWagen") != null) {
	    var winkelWagen = JSON.parse(sessionStorage.getItem("winkelWagen"));
	    var totaal = 0;
	    for(var item in winkelWagen){
	    	var artikel = winkelWagen[item];
	    	console.log(artikel);
	    	var tr = document.createElement("tr");
			tr.id = artikel.id;
			
			var tdNaam = document.createElement("td");
			tdNaam.innerHTML = artikel.naam;
			var tdAantal = document.createElement("td");
			tdAantal.innerHTML = artikel.aantal;
			var tdPrijs = document.createElement("td");
			var prijsTotaal = artikel.prijs*artikel.aantal;
			tdPrijs.innerHTML = "&#8364;" + prijsTotaal;
			totaal += prijsTotaal;

			tr.appendChild(tdNaam);
			tr.appendChild(tdAantal);
			tr.appendChild(tdPrijs);
			
			document.querySelector("#productTable").appendChild(tr);
	    }
	    document.getElementById("totaal").innerHTML = "&#8364;" + totaal;
	  }
}

window.onload = initPage;