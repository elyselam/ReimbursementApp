// let tickets = [
//     {
//         id: 1,
//         amount: 1000,
//         description: 'I traveled',
//         isApproved: false
//     },
//     {
//         id: 2,
//         amount: 20000,
//         description: 'I ate out',
//         isApproved: false
//     },
//     {
//         id: 3,
//         amount:23000,
//         description: 'I worked a lot',
//         isApproved: true
//     }
//
// ];
var kuku;

window.onload = function() {
    //fetch 'GET' tickets

    kuku=  document.cookie.split('; ').reduce((prev, current) => {
        const [name, value] = current.split('=');
        prev[name] = value;
        return prev
    }, {});
    console.log(kuku.firstName);

    //search listener
    let searchBtn = document.querySelector('#search-btn');
    searchBtn.addEventListener('click', function(){
       getSearchTix();

    });

    //click listener to show pending ticks
    let pendingBtn = document.querySelector('#pendingBtn');
    pendingBtn.addEventListener('click', function(){
        getPending();
    });

    let resolvedBtn = document.querySelector('#resolvedBtn');
    resolvedBtn.addEventListener('click', function(){
        getResolved();
    });

    let allEmployeeBtn = document.querySelector('#allEmployeeBtn');
    allEmployeeBtn.addEventListener('click', function(){
        getEmployees();
    });

    let submitBtn = document.querySelector('#registerbtn');
    submitBtn.addEventListener('click', function(){
        submitEmp(event);
    });

};




// /* Grab the table from the DOM*/
// let table = document.querySelector('#table');
function submitEmp(event) {
    event.preventDefault();

    let newemp = {};
    newemp.manager = false;
    newemp.email = document.querySelector('#email').value;
    newemp.hashedPassword = document.querySelector('#psw').value;
    newemp.firstName = 'updateyourname';
    newemp.lastName = 'tempy';
    newemp.employeeID = 0;
    console.log(newemp);
    fetch('http://localhost:8090/proj_1_redux_war_exploded/html/managerRegisterEmployee.do', {
        method: 'Put',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newemp)
    })
        .then(response => response.json());
        alert("Registered!");
}

function getSearchTix() {
    fetch('http://localhost:8090/proj_1_redux_war_exploded/html/managerExamineEmployee.do?targetID=' +document.getElementById('search-input').value)
        .then(response => response.json())
        .then(tickets => {makeResTable(tickets)})
}


function getEmployees() {
    fetch('http://localhost:8090/proj_1_redux_war_exploded/html/managerViewAll.do')
        .then(response => response.json())
        .then(tickets => {makeEmployeeTable(tickets)})
}

function getPending() {
    fetch('http://localhost:8090/proj_1_redux_war_exploded/html/managerViewAllPending.do')
        .then(response => response.json())
            .then(tickets => {makePendTable(tickets)})
}

function getResolved() {
    fetch('http://localhost:8090/proj_1_redux_war_exploded/html/managerViewAllResolved.do')
        .then(response => response.json())
        .then(tickets => {makeResTable(tickets)})
}

function makePendTable(reimbs){

    $("#wutang").replaceWith("<table id='wutang'>")
    $("#wutang").append(`<tr> 
        <th>Ticket ID</th>
        <th>Submitter ID</th>
        <th>Cost Size</th>
        <th>Description</th>
        <th>Take Action?</th>
    </tr>`);
    for(let i = 0; i<reimbs.length; i++){
        $("#wutang").append(`<tr>
                <td>${reimbs[i].ticketID} </td>
                <td>${reimbs[i].submitterID} </td>
                <td>${reimbs[i].cost} </td>
                <td>${reimbs[i].description} </td> 
                <td> <button onclick="approveTik(event)" data1="${reimbs[i].ticketID}" data2="true" > Approve </button></td>
                <td> <button onclick="approveTik(event)" id="${i}" data1="${reimbs[i].ticketID}" data2="false" > Deny </button></td>
         </tr>`)
    }
}

function makeResTable(reimbs){

    $("#wutang").replaceWith("<table id='wutang'>")
    $("#wutang").append(`<tr> 
        <th>Ticket ID</th>
        <th>Submitter ID</th>
        <th>Cost Size</th>
        <th>Description</th>
        <th>Reviewer ID</th>
        <th>Resolution</th>
    </tr>`)
    for(let i = 0; i<reimbs.length; i++){
        $("#wutang").append(`<tr>
                <td>${reimbs[i].ticketID} </td>
                <td>${reimbs[i].submitterID} </td>
                <td>${reimbs[i].cost} </td>
                <td>${reimbs[i].description} </td> 
                <td>${reimbs[i].reviewerID} </button></td>
                <td>${reimbs[i].approved} </td>
         </tr>`);
    }
}

function makeEmployeeTable(reimbs) {
    $("#wutang").replaceWith("<table id='wutang'>")
    $("#wutang").append(`<tr> 
        <th>Employee Number</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Manager?</th>
    </tr>`)
    for(let i = 0; i<reimbs.length; i++){
        $("#wutang").append(`<tr>
                <td>${reimbs[i].employeeID} </td>
                <td>${reimbs[i].firstName} </td>
                <td>${reimbs[i].lastName} </td>
                <td>${reimbs[i].email} </td> 
                <td>${reimbs[i].manager} </button></td>
         </tr>`);
    }
}

function approveTik(event) {
    let ticky = {};
    ticky.ticketID = event.target.attributes.data1.nodeValue;
    ticky.reviewerID = kuku.empID;
    ticky.approved = event.target.attributes.data2.nodeValue;

    console.log(ticky);


     fetch('http://localhost:8090/proj_1_redux_war_exploded/html/managerUpdateTicket.do', {
        method: 'Put',
         headers: {
             'Content-Type': 'application/json'
         },
        body: JSON.stringify(ticky)
    })
        .then(response => response.json())
         .then(tickets => {console.log(tickets)})
}
