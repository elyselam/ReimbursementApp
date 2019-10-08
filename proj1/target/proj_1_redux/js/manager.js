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

}




// /* Grab the table from the DOM*/
// let table = document.querySelector('#table');
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
    </tr>`)
    for(let i = 0; i<reimbs.length; i++){
        $("#wutang").append(`<tr>
                <td>${reimbs[i].ticketID} </td>
                <td>${reimbs[i].submitterID} </td>
                <td>${reimbs[i].cost} </td>
                <td>${reimbs[i].description} </td> 
                <td> <button onclick="approveTik(reimbs[i], true)"> Approve </button></td>
                <td> <button onclick="approveTik(${reimbs[i]}, false"> Deny </button></td>
         </tr>`);
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

function approveTik(ticky, truf) {

     ticky[approval] = truf;
     ticky[reviewerID] = kuku.employeeID;
     console.log(ticky);

     fetch('http://localhost:8090/proj_1_redux_war_exploded/html/managerUpdateTicket.do', {
        method: 'Post',
        body: ticky
    })
        .then(response => response.json())
        .then(tickets => {makeResTable(tickets)})
}
