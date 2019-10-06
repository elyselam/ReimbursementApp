let tickets = [
    {
        id: 1,
        amount: 1000,
        description: 'I traveled',
        isApproved: false
    },
    {
        id: 2,
        amount: 20000,
        description: 'I ate out',
        isApproved: false
    },
    {
        id: 3,
        amount:23000,
        description: 'I worked a lot',
        isApproved: true
    }

];
window.onload = function() {
    //fetch 'GET' tickets


    

    this.fillTable(tickets);

    //search listener
    let searchInput = document.querySelector('#search-input');
    let searchBtn = document.querySelector('#search-btn');
    searchBtn.addEventListener('click', function(){
        let found = searchTickets(tickets);
        fillTable(found);
        
    })

    //click listener to show pending ticks
    let pendingBtn = document.querySelector('#pendingBtn');
    pendingBtn.addEventListener('click', function(){
    showPending(tickets)
});

}




/* Grab the table from the DOM*/
let table = document.querySelector('#table');

/* A function for filling the table with tickets */
function fillTable(reimbs){
    table.innerHTML = "";

    for(let tick of reimbs){
        let row = table.insertRow(0)
    let col1 = row.insertCell(0);
    let col2 = row.insertCell(1);
    let col3 = row.insertCell(2);
    let col4 = row.insertCell(3);
    let col5 = row.insertCell(4);
    let col6 = row.insertCell(5);
    col1.innerHTML = tick.id;
    col2.innerHTML = tick.amount;
    col3.innerHTML = tick.description;
    col4.innerHTML = tick.isApproved;

    /* SubmitBtn and options will add HTML elements to the last two columns of the table. 
        Every submit button has onclick=resolve(tick.id) so that when clicked, resolve will be called on the clicked ticket

    */
    let submitBtn = `<button class='btn btn-primary' class='resolve-btn' onclick="resolve(${tick.id})"> Submit </button>`;
   
    let options = `<select id='newStatus-${tick.id}'>
                    <option value='approve' selected> Approve </option>
                    <option value='deny'> Deny </option> 
                    </select>`;
    col5.innerHTML = options;
    col6.innerHTML = submitBtn;

    }
}

/* this filters the tickets by pending status*/
function showPending(reimbs){
    let pending = [];
    for(let tick of reimbs){
        if(tick.isApproved == false){
            pending.push(tick);
        }
    }

    fillTable(pending);
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