var apiURL = "http://localhost:8081/reimbursement";

//Getting all Reimbursement Tickets
fetch(apiURL)
.then(response => response.json())
.then(json => displayTable(json))
.catch(err => console.log("Request Failed", err));

//Getting all Pending Reimbursement Tickets
fetch(apiURL)
.then(response => response.json())
.then(json => pendingTable(json))
.catch(err => console.log("Request Failed", err));

//Getting all Resolved Reimbursement Tickets
fetch(apiURL)
.then(response => response.json())
.then(json => resolvedTable(json))
.catch(err => console.log("Request Failed", err));

//Getting all Reimbursement Tickets
displayTable = (response) => {
    let reimTable = document.getElementById("reim-table");
    response.forEach( row => {
        const tr = document.createElement("tr");
        for(const block in row){
            const td = document.createElement("td");
            td.textContent = row[block];
            tr.appendChild(td);
        }
        reimTable.appendChild(tr);
    })
};

//Getting all Pending Reimbursement Tickets
pendingTable = (response) => {
    let pendingTable = document.getElementById("pending-table");
    response.forEach( row => {
        console.log(row.resolved)
        const tr = document.createElement("tr");
        if(row.resolved === false){
            for(const block in row){
                const td = document.createElement("td");
                td.textContent = row[block];
                tr.appendChild(td);
            }
            pendingTable.appendChild(tr);
        }
    })
};

//Getting all Resolved Reimbursement Tickets
resolvedTable = (response) => {
    let resolvedTable = document.getElementById("resolved-table");
    response.forEach( row => {
        console.log(row.resolved)
        const tr = document.createElement("tr");
        if(row.resolved === true){
            for(const block in row){
                const td = document.createElement("td");
                td.textContent = row[block];
                tr.appendChild(td);
            }
            resolvedTable.appendChild(tr);
        }
    })
};

