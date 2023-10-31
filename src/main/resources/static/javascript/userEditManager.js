const nameuser = document.getElementById('username');
const emailUser = document.getElementById('email');
const activeStatus = document.getElementById('accountStatus');
const userRoles = document.getElementById('roles');

const nameInput = document.getElementById('setName');
const emailInput = document.getElementById('setEmail');
const statusInput = document.getElementById('setAccountStatus');
const roleInput = document.getElementById('setRoles');

const saveBtn = document.getElementById('save');

function loadDataToEdit(){
    console.log('editing')
    enableInput();
    nameInput.value = nameuser.innerText;
    emailInput.value = emailUser.innerText;
    statusInput.value = activeStatus.innerText;
    roleInput.value = userRoles.innerText;
    sendToast('Editing enabled...', 'green');
}

function updateDataToFront(){
    console.log('updating...');
    nameuser.innerText = nameInput.value;
    emailUser.innerText = emailInput.value;
    activeStatus.innerText = statusInput.value;
    userRoles.innerText = roleInput.value;
    dumpInput();
    disableInput();
    sendToast('Check before uploading', 'danger');
}

function dumpInput(){
    nameInput.value = '';
    emailInput.value = '';
    statusInput.value = '';
    roleInput.value = '';
}

function disableInput(){
    nameInput.disabled = true;
    emailInput.disabled = true;
    statusInput.disabled = true;
    roleInput.disabled = true;
    saveBtn.disabled = true;
}

function enableInput(){
    nameInput.disabled = false;
    emailInput.disabled = false;
    statusInput.disabled = false;
    roleInput.disabled = false;
    saveBtn.disabled = false;
}
