function formdataTest(){
  var formData = new FormData();

  formData.append("accountid", 1);
  formData.append("adresid", 1);

  formData.append("regels", "{'productid': 1, 'aantal': 1, 'prijs': 20.00}");
  formData.append("regels", "{'productid': 5, 'aantal': 1, 'prijs': 1000.98}");

  console.log(formData);

  var encData = new URLSearchParams(formData.entries());

  fetch("../restservices/savebestelling", { method: "POST", body: encData })
    .then(function(response){
      if(response.ok) console.log("gelukt");
      else throw "Er ging iets mis";
    })
    .catch(error => console.log(error));
}
