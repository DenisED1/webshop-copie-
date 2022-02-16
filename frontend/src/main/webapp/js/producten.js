function goToProduct() {
	document.location.href = "/frontend/restservices/product/" + this.parentNode.id;
}


// vul tabel met producten
function fillTable(producten) {
	var tableForDelete = document.querySelector("#productTable");
	while(tableForDelete.firstChild){
		tableForDelete.removeChild(tableForDelete.firstChild);
	}

	var verwijderHeader = document.querySelector("#verwijderHeader");
	if(verwijderHeader != null){
		verwijderHeader.parentNode.removeChild(verwijderHeader);
	}

	if(sessionStorage.getItem("role") == "Admin"){
		var th = document.createElement("th");
		th.innerHTML = "Verwijder";
		th.id = "verwijderHeader";

		document.querySelector("#tableHeadRow").appendChild(th);
	}
	for (var i = 0; i < producten.length; i++) {
		var product = producten[i];
		createTableRow(product);
	}

	// creates tablerow with data
	function createTableRow(product) {
		// create row en geef row id
		var tr = document.createElement("tr");
		tr.id = product.product.id;

		// add afbeelding
		/* if (product.product.afbeelding != null) {
			var tdAfbeelding = document.createElement("td");
			tdAfbeelding
		} */

		// add informatie (misschien categorie nog?)
		var tdAfbeelding = document.createElement("td");
		tdAfbeelding.innerHTML = "<img src='pictures/" + product.product.naam.replace(" ", "_") + ".jpg' height='125' width='125' />";
		tdAfbeelding.addEventListener("click", goToProduct);

		var tdNaam = document.createElement("td");
		tdNaam.innerHTML = product.product.naam;
		tdNaam.addEventListener("click", goToProduct);

		var tdOmschrijving = document.createElement("td");
		tdOmschrijving.innerHTML = product.product.omschrijving;
		tdOmschrijving.addEventListener("click", goToProduct);

		var tdReclameText = document.createElement("td");
		tdReclameText.innerHTML = product.reclametext;
		tdReclameText.addEventListener("click", goToProduct);

		var tdPrijs = document.createElement("td");
		tdPrijs.innerHTML = "&#8364;" + product.product.prijs;
		tdPrijs.addEventListener("click", goToProduct);

		var tdAanbiedingTot = document.createElement("td");
		tdAanbiedingTot.innerHTML = product.totDatum;
		tdAanbiedingTot.addEventListener("click", goToProduct);

		// add informatie aan tablerow
		tr.appendChild(tdAfbeelding);
		tr.appendChild(tdNaam);
		tr.appendChild(tdOmschrijving);
		tr.appendChild(tdReclameText);
		tr.appendChild(tdPrijs);
		tr.appendChild(tdAanbiedingTot);

		if(sessionStorage.getItem("role") == "Admin"){
			var tdVerwijder = document.createElement("td");

			var verwijderKnop = document.createElement("input")
			verwijderKnop.type = "button";
			verwijderKnop.id = "verwijderKnopTable";
			verwijderKnop.name = product.product.id;
			verwijderKnop.value = "X";
			verwijderKnop.style = "background-color: red; border-color: red; font-weight: 1000; z-index: 9001;";

			verwijderKnop.addEventListener("click", () =>{
				var id = verwijderKnop.name;
				fetch("/frontend/restservices/deleteproduct/" + product.product.id, { method : 'DELETE' })
				.then(function (response) {
					if (response.ok) {
						console.log("Verwijderd");
						loadData();
					} else if (response.status == 404) {
						throw response.status + " Product doesn't exist";
					} else if (response.status == 409) {
						throw response.status + response.statusText;
					} else {
						throw response.status + response.statusText;
					}
				})
				.catch(error => console.log(error));
			});

			tdVerwijder.appendChild(verwijderKnop);
			tr.appendChild(tdVerwijder);
		}
		document.querySelector("#productTable").appendChild(tr);
	}
}

function checkIfUserAdmin() {
	var role = sessionStorage.getItem("role");
	if (role == "Admin") {
		document.querySelector("#adminLink").style.display = "list-item";
	}
}


/*
function displayImage(naam)
{
    naam = naam.replace(" ", "_");
    var firstRow=document.getElementById("producten").rows[0];
    var x=firstRow.insertCell(-1);
    console.log(naam);
    x.innerHTML="<img src='pictures/" + naam + ".jpg' height='50' width='50' />";
}
*/
// fetch alle producten
function loadData(){
	fetch("/frontend/restservices/allproducten")
	.then(function (response) {
		if (response.ok) {
			return response.json();
		} else if (response.status == 404) {
			throw response.status + " Products don't exist";
		} else if (response.status == 409) {
			throw response.status + response.statusText;
		} else {
			throw response.status + response.statusText;
		}
	})
	.then(function (myJson) {
		console.log(myJson);
		fillTable(myJson);
	})
	.catch(error => console.log(error));
}

loadData();

checkIfUserAdmin();
