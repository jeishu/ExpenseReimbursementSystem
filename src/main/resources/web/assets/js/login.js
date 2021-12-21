

loginEmployee = () => {
    let apiURL = "http://localhost:8081/login/login-employee";
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    fetch(`${apiURL}${username}${password}`)
        .then(response => response.json())
        .catch( err => console.log("Request Failed", err));

}

loginManager = () => {
    let apiURL = "http://localhost:8081/login/login-manager";
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    fetch(`${apiURL}${username}${password}`)
        .then(response => response.json())
        .catch( err => console.log("Request Failed", err));
}
