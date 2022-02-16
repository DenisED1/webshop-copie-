function getCategorieInfo(){
	var categorieID = getUrlVars()["id"];
	
	fetch("/frontend/restservices/categorie/id/"+categorieID, {method: 'GET'})
	.then(response => response.json())
	.then(function(myJson){
		console.log(myJson)
		document.getElementById("categorieNaam").textContent  = myJson.naam;
		document.getElementById("categorieOmschrijving").textContent  = myJson.omschrijving;
	})
}

function goToProduct() {
	document.location.href = "/frontend/restservices/product/" + this.id;
}

// haalt parameters uit de URL
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function fillCategorieProducts(){
	var categorieID = getUrlVars()["id"];
	
	fetch('/frontend/restservices/categorieProduct/id/'+categorieID, {method: 'GET'})
	.then(response => response.json())
	.then(function(myJson){
		console.log(myJson)
		for (const product of myJson){
			var tr = document.createElement("tr");
			tr.id = product.id;
			
			var tdAfbeelding = document.createElement("td");
			tdAfbeelding.innerHTML = "<img src='pictures/" + product.naam.replace(" ", "_") + ".jpg' height='125' width='125' />";
			var tdNaam = document.createElement("td");
			tdNaam.innerHTML = product.naam;
			var tdOmschrijving = document.createElement("td");
			tdOmschrijving.innerHTML = product.omschrijving;
			var tdPrijs = document.createElement("td");
			tdPrijs.innerHTML = "&#8364;" + product.prijs;

			tr.appendChild(tdAfbeelding);
			tr.appendChild(tdNaam);
			tr.appendChild(tdOmschrijving);
			tr.appendChild(tdPrijs);
			
			document.querySelector("#categorieProductTable").appendChild(tr);
			
			tr.addEventListener("click", goToProduct);
		}
	})
}

function initPage(){
	getCategorieInfo();
	fillCategorieProducts();
}

window.onload = initPage;