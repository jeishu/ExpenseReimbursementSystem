var apiURL = "http://localhost:8081/login/login";

//Rendering HTML code for profile
fetch(apiURL)
.then(response => response.json())
.then(json => displayProfile(json))
.catch(err => console.log("Request Failed", err));

fetch(apiURL)
.then(response => response.json())
.then(json => updateProfile(json))
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

updateProfile = (response) => {
   // let firstNameValue = document.getElementById("p-first-name");
   // let lastNameValue = document.getElementById("p-last-name");
   // let emailValue = document.getElementById("p-email");
   // let passwordValue = document.getElementById("p-password");
   // let usernameValue = document.getElementById("p-username");

   // firstNameValue.setAttribute("value", response.firstName);
   // lastNameValue.setAttribute("value", response.lastName);
   // emailValue.setAttribute("value", response.email);
   // passwordValue.setAttribute("value", response.password);
   // usernameValue.setAttribute("value", response.username);
}