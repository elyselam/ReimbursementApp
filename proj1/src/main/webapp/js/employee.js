var kukie;

window.onload = function() {
    //fetch 'GET' tickets

    kukie=  document.cookie.split('; ').reduce((prev, current) => {
        const [name, value] = current.split('=');
        prev[name] = value;
        return prev
    }, {});

    console.log("bop")
    //click listener to show pending ticks
    let pendingBtn = document.querySelector('#pendingBtn');
    pendingBtn.addEventListener('click', function(){
        getPending();
    });
    console.log("bop")

    let submitBtn = document.querySelector('#submitBtn');
    submitBtn.addEventListener('click', function(){
        submitTick(event);
    });
    console.log("bop")

}





// /* Grab the table from the DOM*/
// let table = document.querySelector('#table');

function submitTick(event) {
    event.preventDefault();
    fetch('http://localhost:8090/proj_1_redux_war_exploded/html/submitTicket.do?empID='+kukie.empID+'&cost='+document.getElementById("amounty").value+'&description='+document.getElementById('descripty').value)
        .then(response => response.json())
        .then(getPending())
}

function getData() {
    return false
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