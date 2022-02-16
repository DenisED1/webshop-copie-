function goToCategorie() {
	document.location.href = "/frontend/categorie.html?id=" + this.id;
}

function showCategorieen() {
	var cat = document.querySelector("#categorie");
	if (cat.style.display=="none") {
		cat.style.display="list-item";
	} else {
		cat.style.display="none";
	}
	document.querySelector("#winkelwagenDropdown").style.display="none";
}

// vul categorieën dropdown
function fillDropdown(categorieen){
	for (var i = 0; i < categorieen.length; i++) {
		var categorie = categorieen[i];
		createCategorieListItem(categorie);
	}

	function createCategorieListItem(categorie) {
		var li = document.createElement("li");
		li.id = categorie.id;
		li.innerHTML = categorie.naam;

		li.addEventListener("click", goToCategorie);

		document.querySelector("#categorieList").appendChild(li);
	}
}

// fetch naam categorieën
fetch("/frontend/restservices/categorie/all")
.then(function (response) {
	if (response.ok) {
		return response.json();
	} else if (response.status == 404) {
		throw response.status + " Categorie does not exist";
	} else {
		throw response.status + " Categorie not found";
	}
})
.then(function (myJson) {
	fillDropdown(myJson);
})
.catch(error => console.log(error));

function showWinkelwagen() {
	var cat = document.querySelector("#winkelwagenDropdown");
	if (cat.style.display=="none") {
		cat.style.display="list-item";
	} else {
		cat.style.display="none";
	}
	document.querySelector("#categorie").style.display="none";
}

// vul categorieën dropdown
function fillDropdownWinkelwagen(){
	var winkelwagenList = document.querySelector("#winkelwagenList");
	while(winkelwagenList.firstChild){
		winkelwagenList.removeChild(winkelwagenList.firstChild);
	}
	var totaalPrijs = 0;

	var li = document.createElement("li");
	li.id = "titelBalk";

	var winkelwagenHeaderNaam = document.createElement("p")
	winkelwagenHeaderNaam.id = "winkelwagenHeaderNaam";
	winkelwagenHeaderNaam.textContent = "Naam";
	winkelwagenHeaderNaam.style = "font-weight: bold";
	li.appendChild(winkelwagenHeaderNaam);

	var winkelwagenHeaderAantal = document.createElement("p")
	winkelwagenHeaderAantal.id = "winkelwagenHeaderAantal";
	winkelwagenHeaderAantal.textContent = "Aantal";
	winkelwagenHeaderAantal.style = "font-weight: bold";
	li.appendChild(winkelwagenHeaderAantal);

	var winkelwagenHeaderPrijs = document.createElement("p")
	winkelwagenHeaderPrijs.id = "winkelwagenHeaderPrijs";
	winkelwagenHeaderPrijs.textContent = "Prijs";
	winkelwagenHeaderPrijs.style = "font-weight: bold";
	li.appendChild(winkelwagenHeaderPrijs);

	var winkelwagenHeaderDelete = document.createElement("p")
	winkelwagenHeaderDelete.id = "winkelwagenHeaderDelete";
	winkelwagenHeaderDelete.textContent = "Verwijder";
	winkelwagenHeaderDelete.style = "font-weight: bold";
	li.appendChild(winkelwagenHeaderDelete);

	document.querySelector("#winkelwagenList").appendChild(li);

	if(sessionStorage.getItem("winkelWagen") != null) {
    var winkelWagen = JSON.parse(sessionStorage.getItem("winkelWagen"));
    for(var item in winkelWagen){
			createWinkelwagenListItem(winkelWagen[item]);
    }
  }

	function createWinkelwagenListItem(artikel) {
		console.log(artikel);
		var li = document.createElement("li");
		li.id = artikel.id;

		var naam = document.createElement("p");
		naam.textContent = artikel.naam;
		li.appendChild(naam);

		var aantal = document.createElement("p");
		aantal.textContent = artikel.aantal;
		li.appendChild(aantal);

		var prijs = document.createElement("p");
		var artikelTotaalPrijs = artikel.prijs * artikel.aantal;
		totaalPrijs += artikelTotaalPrijs;
		prijs.textContent = "€" + artikelTotaalPrijs + (Number.isInteger(artikelTotaalPrijs) ? ".-" : "");
		li.appendChild(prijs);

		var verwijderKnop = document.createElement("input")
		verwijderKnop.type = "button";
		verwijderKnop.id = "verwijderKnop";
		verwijderKnop.name = artikel.id;
		verwijderKnop.value = "X";
		verwijderKnop.style = "background-color: red; border-color: red; font-weight: 1000; cursor: pointer;";

		verwijderKnop.addEventListener("click", () =>{
			var id = verwijderKnop.name;
			var winkelWagen = JSON.parse(sessionStorage.getItem("winkelWagen"));
			for(var item in winkelWagen){
				if(winkelWagen[item].id == id){
					winkelWagen.splice(item, 1);
				}
			}
			sessionStorage.setItem("winkelWagen", JSON.stringify(winkelWagen));
			fillDropdownWinkelwagen();
		});

		li.appendChild(verwijderKnop);

		document.querySelector("#winkelwagenList").appendChild(li);
	}

	var li = document.createElement("li");
	li.id = "bestel";

	var totaalPrijsText = document.createElement("p");
	totaalPrijsText.textContent = "Totaalprijs: €" + totaalPrijs + (Number.isInteger(totaalPrijs) ? ".-" : "");
	li.appendChild(totaalPrijsText);

	var bestelKnop = document.createElement("input")
	bestelKnop.type = "button";
	bestelKnop.id = "bestelKnop";
	bestelKnop.value = "Bestellen";
	bestelKnop.style = "cursor: pointer;";
	li.appendChild(bestelKnop);

	document.querySelector("#winkelwagenList").appendChild(li);

	document.querySelector("#bestelKnop").addEventListener("click", () => {
		document.location.href = '/frontend/afronden.html'
	});
}

fillDropdownWinkelwagen();

document.querySelector("#categorieNavbar").addEventListener("click", showCategorieen);
document.querySelector("#winkelwagen").addEventListener("click", showWinkelwagen);
