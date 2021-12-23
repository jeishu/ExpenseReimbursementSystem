var apiURL = "http://localhost:8081/employee";

fetch(apiURL)
.then(response => response.json())
.then(json => displayProfile(json))
.catch(err => console.log("Request Failed", err));

displayProfile = (response) => {
   let profile = document.getElementById("profile");
   console.log(response);
   
};