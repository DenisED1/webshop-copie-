function registreer (form) {
	
    var formData = new FormData(document.querySelector("#registerenForm"));
    var encData = new URLSearchParams(formData.entries());

    fetch("/frontend/restservices/saveklant", {method: 'POST', body: encData })
      .then(function(response) {
        if(response.ok) return response;
        else throw "Foutmelding";
      })
      .catch(error => console.log(error));
    document.location.href = '/frontend/registreren.html';
}