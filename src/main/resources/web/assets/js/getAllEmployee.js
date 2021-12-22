var apiURL = "http://localhost:8081/employee";

fetch(apiURL)
.then(response => response.json())
.then(json => displayTable(json))
.catch(err => console.log("Request Failed", err));

displayTable = (response) => {
    let employeeTable = document.getElementById("employee-table");
    response.forEach( row => {
        const tr = document.createElement("tr");
        for(const block in row){
            const td = document.createElement("td");
            td.textContent = row[block];
            tr.appendChild(td);
        }
        employeeTable.appendChild(tr);
    });
};