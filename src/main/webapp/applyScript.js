let url = "http://localhost:49152/ERS/apply";

function postForm() {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if(this.readyState == 4 && this.status == 200) {
            let r = JSON.parse(this.responseText);
            console.log(r);
            printForm(r);
        }
    }

    xhr.open('POST', url, true);
    xhr.send();
}

function addForm() {

    let form = {
        let day: document.getElementById('date').value,
        let hour: document.getElementById('time').value,
        let address: document.getElementById('Address').value,
        let city: document.getElementById('City').value,
        let state: document.getElementById('State').value,
        let zip: document.getElementById('Zip').value,
        let description: document.getElementById('Description').value,
        let cost: document.getElementById('Cost').value,
        let grading_format: document.getElementById('format').value,
        let event: document.getElementById('Event').value,
        let justification: document.getElementById('Justification').value,
        let grade: document.getElementById('Grade')
    }
    console.log(form);

    let formJson = JSON.stringify(form);
    console.log(formJson);

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if(this.readyState == 4 && this.status == 200) {
            let r = this.responseText;
            console.log(r);
        }
    }

    xhr.open('POST', url, true);

    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(formJson);

/*
    let res = await fetch(url, {
        method: "POST",
        body: JSON.stringify(form),
        header: {'Content-Type': 'application/json'}
    });

    let resJson = await res.json().then((resp) => {
        console.log(resp);
    })
    .catch((error) => {
        console.log(error);
    })
*/

}