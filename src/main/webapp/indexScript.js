let url = "http://localhost:49152/ERS/index.html";

function login() {

    let usernameInput = document.getElementById('username').value;
    let passwordInput = document.getElementById('password').value;

    let newLogin = {
        username: usernameInput,
        pass: passwordInput
    }
    console.log(newLogin);

    let loginJson = JSON.stringify(newLogin);
    console.log(loginJson);

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            let r = this.responseText;
            console.log(r);
        }
    }

    xhr.open('POST', url, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(loginJson);
}