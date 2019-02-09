let pendingChecked = false;
let resolvedChecked = false;

let url = "http://localhost:8080/project1_Cameron/allReqs";
function setUrl() {
	if (pendingChecked && !resolvedChecked) {
		url = "http://localhost:8080/project1_Cameron/pendingReqs";
	} else if (!pendingChecked && resolvedChecked) {
		url = "http://localhost:8080/project1_Cameron/resolvedReqs";
	} else {
		url = "http://localhost:8080/project1_Cameron/allReqs";
	}
}

document.getElementById("pending").addEventListener("change", function() {
	pendingChecked = this.checked;
	setUrl();
	sendAjaxGet(url, display);
});

document.getElementById("resolved").addEventListener("change", function() {
	resolvedChecked = this.checked;
	setUrl();
	sendAjaxGet(url, display);
});


function sendAjaxGet(url, func) {
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		if (this.readyState==4 && this.status==200) {
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}

//sendAjaxGet(url, display);

function display(xhr) {
	requests = JSON.parse(xhr.responseText);
	requestArr = requests.requests;
	let table = document.getElementById("requestTablee");
	table.removeChild(document.getElementById("requestTableBodyy"));
	let newBody = document.createElement("tbody");
	newBody.setAttribute("id", "requestTableBodyy");
	table.appendChild(newBody);
	for (i in requestArr) {
		let newRow = document.createElement("tr");
		newRow.innerHTML = 
			`<td>${requestArr[i].id}</td>
			<td>${requestArr[i].status}</td> 
			<td>$${requestArr[i].amount}</td>
			<td>${requestArr[i].employee.firstname} ${requestArr[i].employee.lastname}</td>`;
		newBody.appendChild(newRow);
	}
}

function approve(xhr){
	console.log("Array of values: " + requestArr);
	console.log("Item I wish to update: " + document.getElementById("requestNum").value);
	let table = document.getElementById("requestTablee");
	table.removeChild(document.getElementById("requestTableBodyy"));
	let newBody = document.createElement("tbody");
	newBody.setAttribute("id", "requestTableBodyy");
	table.appendChild(newBody);
	console.log(requestArr);
	for(i in requestArr){
		let newRow = document.createElement("tr");
		if(requestArr[i].id ==  document.getElementById("requestNum").value){
			console.log(requestArr[i]);
			requestArr[i].status = "approved";
		}
		newRow.innerHTML =
			`<td>${requestArr[i].id}</td>
			<td>${requestArr[i].status}</td> 
			<td>$${requestArr[i].amount}</td>
			<td>${requestArr[i].employee.firstname} ${requestArr[i].employee.lastname}</td>`;
		newBody.appendChild(newRow);
	}
}


function deny(xhr){
	console.log("Item I wish to update: " + document.getElementById("requestNum").value);
	let table = document.getElementById("requestTablee");
	table.removeChild(document.getElementById("requestTableBodyy"));
	let newBody = document.createElement("tbody");
	newBody.setAttribute("id", "requestTableBodyy");
	table.appendChild(newBody);
	console.log(requestArr);
	for(i in requestArr){
		let newRow = document.createElement("tr");
		if(requestArr[i].id ==  document.getElementById("requestNum").value){
			console.log(requestArr[i]);
			requestArr[i].status = "denied";
		}
		newRow.innerHTML =
			`<td>${requestArr[i].id}</td>
			<td>${requestArr[i].status}</td> 
			<td>$${requestArr[i].amount}</td>
			<td>${requestArr[i].employee.firstname} ${requestArr[i].employee.lastname}</td>`;
		newBody.appendChild(newRow);
	}
}

sendAjaxGet(url, display);

