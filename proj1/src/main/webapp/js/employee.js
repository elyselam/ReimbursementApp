var kukie;

window.onload = function() {

    //fetch 'GET' tickets

    kukie=  document.cookie.split('; ').reduce((prev, current) => {
        const [name, value] = current.split('=');
        prev[name] = value;
        return prev
    }, {});

    //click listener to show pending ticks
    let pendingBtn = document.querySelector('#pendingBtn');
    pendingBtn.addEventListener('click', function(){
        getPending();
    });

    let submitBtn = document.querySelector('#submitBtn');
    submitBtn.addEventListener('click', function(){
        submitTick(event);
    });

    let updateBtn = document.querySelector('#updateBtn');
    updateBtn.addEventListener('click', function(){
        updateEm(event);
    });

let empName = "Bob"; //comes from DB/kukie.firstName;
let welcome = document.querySelector('#welcome');
this.appendUserName(empName)

};

function appendUserName(empName) {
    console.log(kukie.firstName);
    welcome.innerHTML = `Welcome back, ${empName}!`;
}

// /* Grab the table from the DOM*/
// let table = document.querySelector('#table');

function updateEm(event) {
    event.preventDefault();

    let newemp = {};
    newemp.manager = false;
    newemp.email = 'temp';
    newemp.hashedPassword = document.querySelector('#passwordy').value;
    newemp.firstName = document.querySelector('#firsty').value
    newemp.lastName = document.querySelector('#lasty').value
    newemp.employeeID = kukie.empID;
    fetch('http://localhost:8090/proj_1_redux_war_exploded/html/updateInfo.do', {
        method: 'Put',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newemp)
    })
        .then(response => response.json())
        .then(alert("Updated!"));
}

function submitTick(event) {
    event.preventDefault();
    let newtick = {};
    newtick.ticketID = 0;
    newtick.submitterID = kukie.empID;
    newtick.cost = document.querySelector('#amounty').value;
    newtick.description = document.querySelector('#descripty').value;
    newtick.reviewerID = 0;
    newtick.approved = false;
    newtick.pending = true;
    console.log(newtick);
    fetch('http://localhost:8090/proj_1_redux_war_exploded/html/submitTicket.do', {
        method: 'Put',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newtick)
    })
        .then(response => response.json())
        .then(getPending());
        
        alert("Ticket submitted!");
}

function getPending() {
    fetch('http://localhost:8090/proj_1_redux_war_exploded/html/empViewTickets.do?empID='+kukie.empID)
        .then(response => response.json())
        .then(tickets => {makePendTable(tickets)})
}

function makePendTable(reimbs){

    $("#wutang").replaceWith("<table id='wutang'>")
    $("#wutang").append(`<tr> 
        <th>Ticket ID</th>
        <th>Submitter ID</th>
        <th>Cost Size</th>
        <th>Description</th>
        <th>Pending?</th>
        <th>Approved?</th>
    </tr>`)
    for(let i = 0; i<reimbs.length; i++){
        $("#wutang").append(`<tr>
                <td>${reimbs[i].ticketID} </td>
                <td>${reimbs[i].submitterID} </td>
                <td>${reimbs[i].cost} </td>
                <td>${reimbs[i].description} </td> 
                <td>${reimbs[i].pending} </td>
                <td>${reimbs[i].approved} </td>
         </tr>`);
    }
}