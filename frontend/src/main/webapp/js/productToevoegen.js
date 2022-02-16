function productToevoegen() {
	
	if (isNumeric(document.querySelector("#prijs").value)) {
		var formData = new FormData(document.querySelector("#productToevoegenForm"));
		var encData = new URLSearchParams(formData.entries());
		
		fetch("/frontend/restservices/saveproduct", {method: 'POST', body: encData})
		.then(function (response) {
			if (response.ok) {
				console.log("Succes");
				document.querySelector("#text").value = "";
				document.querySelector("#prijs").value = "";
				document.querySelector("#omschrijving").value = "";
				document.querySelector("#succesMessage").innerHTML = "Product is aangemaakt.";
				document.querySelector("#prijsError").innerHTML = "";
			} else {
				throw response.status + " " + response.statusText;
			}
		})
		.catch(error => console.log(error));
	} else {
		document.querySelector("#prijsError").innerHTML = "Prijs moet een numeriek getal zijn, maat."
	}
}

function isNumeric(n) {
	  return !isNaN(parseFloat(n)) && isFinite(n);
}

document.querySelector("#submit").addEventListener("click", productToevoegen);