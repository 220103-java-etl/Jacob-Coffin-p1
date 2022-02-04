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
        day: document.getElementById('date').value,
        hour: document.getElementById('time').value,
        address: document.getElementById('Address').value,
        city: document.getElementById('City').value,
        state: document.getElementById('State').value,
        zip: document.getElementById('Zip').value,
        description: document.getElementById('Description').value,
        cost: document.getElementById('Cost').value,
        grading_format: document.getElementById('format').value,
        event: document.getElementById('Event').value,
        justification: document.getElementById('Justification').value,
        grade: document.getElementById('Grade')
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

    xhr.open('GET', url, true);

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