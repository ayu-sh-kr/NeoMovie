function closeToast(id){
    let element = document.getElementById(id);
    element.classList.remove('flex');
    element.classList.add('hidden');
}
function doRegister(){
    let name = document.getElementById('name');
    let email = document.getElementById('email');
    let password = document.getElementById('password');
    let cPass = document.getElementById('cPass');

    if(cPass.value === password.value){
        register(name.value, email.value, password.value);
    }else {
        let dangerToast = document.getElementById('toast-danger');
        dangerToast.classList.remove('hidden');
        dangerToast.classList.add('flex');
    }
}

function register(name, email, password){
    let user_Details = {
        name:name,
        email:email,
        password:password
    };
    console.log(JSON.stringify(user_Details))
    console.log(user_Details);

    let url = "http://localhost:8080/register";
    fetch(url, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        redirect: 'follow',
        body: JSON.stringify(user_Details)
    })
        .then(response => {
            if(response.status === 200){
                showToast(response.status)
                wait();
                return response.json();
            }
        })
        .then(data => {
            console.log(data)
        })
        .catch(error => console.log(error));
}


function showToast(){
    let element = document.getElementById('toast-success');
    element.classList.remove("hidden");
    element.classList.add("flex");
}

const wait = async () => {
    await delay(5000);
    window.location.replace("http://localhost:8080/login");
}

const delay = ms => new Promise(res => setTimeout(res, ms));
console.log(JSON.stringify({"name": "Ayush"}))
