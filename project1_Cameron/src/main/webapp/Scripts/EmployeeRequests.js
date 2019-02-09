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

sendAjaxGet(url, display);

function display(xhr) {
	requests = JSON.parse(xhr.responseText);
	requestArr = requests.requests;
	console.log(requestArr);
	let table = document.getElementById("requestTable");
	table.removeChild(document.getElementById("requestTableBody"));
	let newBody = document.createElement("tbody");
	newBody.setAttribute("id", "requestTableBody");
	table.appendChild(newBody);
	for (i in requestArr) {
		if (requestArr[i].manager) {
			man = `${requestArr[i].manager.firstname} ${requestArr[i].manager.lastname}`;
		} else {
			man = "n/a";
		}
		let newRow = document.createElement("tr");
		newRow.innerHTML = 
			`<td>${requestArr[i].id}</td>
			<td>${requestArr[i].status}</td> 
			<td>$${requestArr[i].amount}</td>
			<td>${man}</td>`;
		newBody.appendChild(newRow);
	}
}