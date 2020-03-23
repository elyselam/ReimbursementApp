//https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_table_insertrow
function myFunction() {
  var table = document.getElementById("myTable");
  for (i = 0; i< ticketList.length; i++) {

  var row = table.insertRow(0);
  var cell1 = row.insertCell(0);
  var cell2 = row.insertCell(1);
  var cell3 = row.insertCell(2);
  var cell4 = row.insertCell(3);
  cell1.innerHTML = response.getsomething;
  cell2.innerHTML = response.getsomething;
  }
}var table = document.getElementById("myTable");
let ticket1 = {
  id: 1,
  fName: 'Sam',
  lName: 'Jahones',
  amount:1000,
  isApproved: 0
}
let ticket2 = {
  id: 2,
  fName: 'Sam',
  lName: 'Jahones',
  amount:1200,
  isApproved: 1
}
let ticket3 = {
  id: 3,
  fName: 'Sam',
  lName: 'Jahones',
  amount:1300,
  isApproved: 2
}


let tickets = [ticket1, ticket2, ticket3]

function makeTable(tickets) {


table.innerHTML = "";
for (i = 0; i< tickets.length; i++) {
  var row = table.insertRow(0);
  var col1 = row.insertCell(0);
  var col2 = row.insertCell(1);
  var col3 = row.insertCell(2);
  var col4 = row.insertCell(3);

  col1.innerHTML = tickets[i].id;
  col2.innerHTML = tickets[i].fName;
  col3.innerHTML = tickets[i].lName;
  col4.innerHTML = tickets[i].amount;


}
}
function getPending(tickets){
  let pending = []
  for(let tick of tickets){
    if (tick.isApproved == 0) {

    pending.push(tick);
    } 
  }
  makeTable(pending);
}

//click button to show pending
let pendingBtn = document.getElementById('pendingBtn')
pendingBtn.addEventListener('click', ()=>{
  getPending(tickets);
});


function getResolved(tickets){
  let resolved = []
  for(let tick of tickets){
    if (tick.isApproved !== 0) {

    resolved.push(tick);
    }
  }
  makeTable(resolved);
}
//click button to show resolved
let resolvedBtn = document.getElementById('resolvedBtn')
resolvedBtn.addEventListener('click', ()=>{
  getResolved(tickets);
});
