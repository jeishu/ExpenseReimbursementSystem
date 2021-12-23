var apiURL = "http://localhost:8081/login/login";

fetch(apiURL)
.then(response => response.json())
.then(json => displayProfile(json))
.catch(err => console.log("Request Failed", err));

//Rendering HTML code for profile
displayProfile = (response) => {
   let profile = document.getElementById("profile");
   console.log(response);
   let string = `<p>First Name: <span>${response.firstName}</span> </p>
      <p>Last Name: <span>${response.lastName}</span></p>
      <p>Email: <span>${response.email}</span> </p>
      <p>User ID: <span>${response.userId}</span></p>
      <p>Position: <span>${response.userRole}</span></p>
      <p>Username: <span>${response.username}</span></p>`;
   profile.innerHTML = string;
};