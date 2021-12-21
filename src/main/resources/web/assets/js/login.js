var apiURL = "http://localhost:8081/login";
var username = document.getElementById("username").value;
var password = document.getElementById("password").value;
var loginButton = document.getElementById("login-employee");
let employeeId;
let employeeName;

fetch(`${apiURL} ${username} ${password}`)
.then(response => response.json())
.then(json => employeeData(json))
.catch(err => console.log("Request Failed", err));

employeeData = (response) => {
    employeeId = response.userId;
    employeeName = response.firstName;
};

