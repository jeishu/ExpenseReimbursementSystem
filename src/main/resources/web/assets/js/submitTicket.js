var apiURL2 = "http://localhost:8081/submit";


//Submitting new reimbursement tickets
fetch(apiURL2)
.then(response => response.json())
.then(json => submitTicket(json))
.catch(err => console.log("Resquest Failed", err));


//Submitting new reimbursement tickets
submitTicket = response => {
    
}