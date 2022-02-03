let userURL = "http://localhost:49152/ERS/userSession";

function getUser() {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if(this.readyState == 4 && this.status == 200) {
            let r = JSON.parse(this.responseText);
            console.log(r);
            printUser(r);
        }
    }

    xhr.open('GET', userURL, true);
    xhr.send();
}

function printUser(u) {

    document.body.style.backgroundColor = "lightgreen";
    document.write(`<h3>${u.username}</h3>`);
    document.write("<br><br>");
    document.write(`Name: ${u.first_name} ${u.last_name}<br>`);
    document.write(`Email: ${u.email}<br>`);
    document.write(`Role: ${u.role}<br>`);
    document.write(`Available Funds: ${u.funds}<br>`);
}