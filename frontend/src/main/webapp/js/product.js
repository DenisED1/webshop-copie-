//Strike Through normal price if there is a discount
function strikeThrough(){
  if(document.querySelector("#discountPrice").textContent != ""){
    document.querySelector("#normalPrice").style.setProperty("text-decoration", "line-through");
  }
}

function addToWinkelWagen(id, aantal, naam, prijs){
  if(sessionStorage.getItem("winkelWagen") != null) {
    var winkelWagen = JSON.parse(sessionStorage.getItem("winkelWagen"));
    for(var item in winkelWagen){
      if(winkelWagen[item].id == id){
        return "Dit artikel zit al in uw winkelwagen.";
      }
    }
    winkelWagen.push({id, aantal, naam, prijs});
    sessionStorage.setItem("winkelWagen", JSON.stringify(winkelWagen));
  }
  else {
    sessionStorage.setItem("winkelWagen", JSON.stringify([{id, aantal, naam, prijs}]));
  }
  fillDropdownWinkelwagen();
  return "Het artikel is toegevoegd aan uw winkelwagen."
}

strikeThrough();

if(document.querySelector("#aantal").value == ""){
  document.querySelector("#aantal").value = 1;
}

document.querySelector("#submit").addEventListener("click", () => {
  var aantal = document.querySelector("#aantal").value;
  if(aantal > 0){
    document.querySelector("#feedback").textContent = addToWinkelWagen(id, aantal, naam, prijs);
  }
});

document.querySelector("#aantal").oninput = function () {
    if (this.value.length > 2)
      this.value = this.value.slice(0,2);
    if (this.value === "0")
      this.value = 1;
    if (this.value.includes("-"))
      this.value = 1;
}
