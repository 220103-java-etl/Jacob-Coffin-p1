let url = "http://localhost:49152/ERS/userSession";

function getUser() {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if(this.readyState == 4 && this.status == 200) {
            let r = JSON.parse(this.responseText);
            console.log(r);
            printUser(r);
        }
    }

    xhr.open('GET', url, true);
    xhr.send();
}

function printUser(u) {

    let body = document.body;
    body.style.backgroundColor = "#00FF00";
    body.style.marginLeft = "100px";
    document.write(`<h2>${u.username}</h2>`);
    document.write("<br><br>");
    document.write(`Name: ${u.first_name} ${u.last_name}<br><br>`);
    document.write(`Email: ${u.email}<br><br>`);
    document.write(`Role: ${u.role}<br><br>`);
    document.write(`Available Funds: ${u.funds}<br><br>`);
    let a = document.createElement('a');
    let link = document.createTextNode("Apply for Refund");
    a.appendChild(link);
    a.title = "Apply for Refund";
    a.href = "apply.html";
    document.body.appendChild(a);
}