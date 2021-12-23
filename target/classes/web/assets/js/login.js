var loginURL = "http://localhost:8081/login/login";
var reimApiURL = "http://localhost:8081/reimbursement/";
// var username = document.getElementById("username").value;
// var password = document.getElementById("password").value;
let employeeId;
let employeeName;

//Login User
fetch(loginURL)
.then(response => response.json())
.then(json => employeeDataTable(json))
.catch(err => console.log("Request Failed", err));

//getting pending reimbursement based on employee info
fetch(reimApiURL)
.then(response => response.json())
.then(json => pendingByEmployee(json))
.catch(err => console.log("Request Failed", err));

//getting resolved reimbursement based on employee info
fetch(reimApiURL)
.then(response => response.json())
.then(json => resolvedByEmployee(json))
.catch(err => console.log("Request Failed", err));

//Login User
employeeDataTable = (response) => {
    
    employeeId = response.userId;
    employeeName = response.firstName;

    console.log(employeeId)
    console.log(employeeName)
    console.log(response.authorId)
    
};


pendingByEmployee = (response) => {
    let employeePending = document.getElementById("emp-pending-table");

    response.forEach( row => {
        const tr = document.createElement("tr");
        if((row.resolved === false) && (row.authorId === employeeId)){
            for(const block in row){
                const td = document.createElement("td");
                td.textContent = row[block];
                tr.appendChild(td);
            }
            employeePending.appendChild(tr);
        }
    })
}

resolvedByEmployee = (response) => {
    let employeeResolve = document.getElementById("emp-resolved-table");

    response.forEach( row => {
        const tr = document.createElement("tr");

        if((row.resolved === true) && (row.authorId === employeeId)){
            for(const block in row){
                const td = document.createElement("td");
                td.textContent = row[block];
                tr.appendChild(td);
            }
            employeeResolve.appendChild(tr);
        }
    })
}