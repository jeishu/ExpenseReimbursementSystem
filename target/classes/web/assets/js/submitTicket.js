var apiURL2 = "http://localhost:8081/reimbursement";

//Submitting new reimbursement tickets
fetch(apiURL2)
.then(response => response.json())
.catch(err => console.log("Resquest Failed", err));
