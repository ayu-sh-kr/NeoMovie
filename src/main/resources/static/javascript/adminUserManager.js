
const userLogo = document.getElementById('userLogo');

const username = document.getElementById('username');
const userEmail = document.getElementById('email');
const isActive = document.getElementById('accountStatus');
const createdOn = document.getElementById('createdOn');
const roles = document.getElementById('roles');

let userIdElement = document.getElementById('userId');
let userId = '';

function setActiveUserData(){
    let uri = 'http://localhost:8080/admin/getUser';
    let query = new URLSearchParams(window.location.search);
    userId = query.get('email');
    console.log(JSON.stringify(userId));
    setLogo();
    loadUserData(uri, userId);
}

function setLogo(){
    let uri = "http://localhost:8080/admin/currentUser"
    fetch(uri)
        .then(response => {
            console.log(response)
            return response.json()
        })
        .then(data => {
            console.log(data);
            let fLetter = data.name[0];
            userLogo.innerText = fLetter;
        })
        .catch(error => console.log(error));
}

function loadUserData(uri, useremail){
    fetch(uri, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: useremail
    })
        .then(response => response.json())
        .then(data => {
            console.log();
            let role = getRoles(data.roleList);
            userIdElement.innerText = data.id;
            username.innerText = data.name;
            userEmail.innerText = data.email;
            createdOn.innerText = data.createdOn;
            isActive.innerText = data.active;
            roles.innerText = role;
            // console.log(data[name])
        })
        .catch(error => console.log(error))
}

function sendUpdatedData(uri, datum) {
    fetch(uri, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(datum),
    })
        .then(response => {
            console.log(response.status)
            if(response.status === 201){
                sendToast("User updated successfully", 'green');
            }
            else {
                sendToast("Unexpected error occurred", 'danger');
            }
        })
        .catch(error => console.log(error));
}

function updateUser(){
    const uri = 'http://localhost:8080/admin/updateUser?id=' + userIdElement.innerText;
    let datum = {
        name: username.innerText,
        email: userEmail.innerText,
        role: roles.innerText,
        accountStatus: isActive.innerText
    };
    console.log(roles.innerText);
    sendUpdatedData(uri, datum);
}

function getRoles(value){
    let str = '';
    for(let i = 0; i < value.length; i++){
        // console.log(value[i].name);
        str += value[i].name + ' ';
    }
    return str;
}
