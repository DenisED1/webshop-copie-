function goToAanbieding() {
	document.location.href = "/frontend/restservices/product/" + this.id;
}

// vul tabel met aanbiedingen
function fillTable(aanbiedingen) {
	for (var i = 0; i < aanbiedingen.length; i++) {
		var aanbieding = aanbiedingen[i];
		createTableRow(aanbieding);
	}

	// creates tablerow with data
	function createTableRow(product) {
		// create row en geef row id
		var tr = document.createElement("tr");
		tr.id = product.product.id;
		// add informatie (misschien categorie nog?)
		var tdAfbeelding = document.createElement("td");
		tdAfbeelding.innerHTML = "<img src='pictures/" + product.product.naam.replace(" ", "_") + ".jpg' height='125' width='125' />";
		var tdNaam = document.createElement("td");
		tdNaam.innerHTML = product.product.naam;
		var tdOmschrijving = document.createElement("td");
		tdOmschrijving.innerHTML = product.product.omschrijving;
		var tdReclameText = document.createElement("td");
		tdReclameText.innerHTML = product.reclametext;
		var tdPrijs = document.createElement("td");
		tdPrijs.innerHTML = "&#8364;" + product.aanbiedingsprijs;
		var tdAanbiedingTot = document.createElement("td");
		tdAanbiedingTot.innerHTML = product.totDatum;
		// add informatie aan tablerow
		tr.appendChild(tdAfbeelding);
		tr.appendChild(tdNaam);
		tr.appendChild(tdOmschrijving);
		tr.appendChild(tdReclameText);
		tr.appendChild(tdPrijs);
		tr.appendChild(tdAanbiedingTot);
		document.querySelector("#productTable").appendChild(tr);

		// add eventListener to tablerow
		tr.addEventListener("click", goToAanbieding);
	}
}

// fetch alle aanbiedingen
fetch("/frontend/restservices/aanbieding/all")
.then(function (response) {
	if (response.ok) {
		return response.json();
	} else if (response.status == 404) {
		throw response.status + " Products don't exist";
	} else {
		throw response.status + " Products not found";
	}
})
.then(function (myJson) {
	fillTable(myJson);
})
.catch(error => console.log(error));

function goToCategorie() {
	document.location.href = "/frontend/categorie.html?id=" + this.id;
}
