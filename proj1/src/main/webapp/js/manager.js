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
window.onload = function() {
    //fetch 'GET' tickets

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

}




// /* Grab the table from the DOM*/
// let table = document.querySelector('#table');

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
                <td> <button (click)="approveTik(${reimbs[i].ticketID, true}"> Approve </button></td>
                <td> <button (click)="approveTik(${reimbs[i].ticketID, false}"> Deny </button></td>
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

/* this filters by approved status */
function showApproved(reimbs){
    let approved = [];
    for(let tick of reimbs){
        if(tick.isApproved == true){
            approved.push(tick);
        }
    }

    fillTable(approved);
}

/* this enables searching by ID received from an input field, '#search-input'  */
function searchTickets(tickets){
    let searchTerm = document.querySelector('#search-input').value;
    let foundTicks = [];
    for(let tick of tickets){
        if(tick.id == searchTerm){
            foundTicks.push(tick);
        }
    }
    return foundTicks;
}

/* this is used internally to the JS file to yield the complete ticket whenever the submit button is clicked*/
function searchTicketsById(id){
    for(let tick of tickets){
        if(tick.id == id){
            return tick;
        }

        else {
            continue;
        }


    }
}
/* this is where you will update the status of the ticket and fetch POST the new ticket*/
 function resolve(id){
     // this grabs the select HTML element
    let selectElement = document.querySelector(`#newStatus-${id}`);
    //this gets the value of the selected HTML option
    var newStatus = selectElement.options[selectElement.selectedIndex].value;

    //finds the entire ticket by id
    let found = searchTicketsById(id);

    //sets the new status
    if(newStatus == 'approve'){
        found.isApproved = true; 
    }
    else {
        found.isApproved = false;
    }


    console.log(found);
    console.log('Send the object above in the body of a Fetch-POST request to your server!')
    // fetch('http://localhost:8080/Project1/resolve')
    // method: 'POST',
    // body: 'found'
 }