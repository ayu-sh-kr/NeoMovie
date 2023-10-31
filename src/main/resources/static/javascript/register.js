function doRegister(){
    let name = document.getElementById('name');
    let email = document.getElementById('email');
    let password = document.getElementById('password');
    let cPass = document.getElementById('cPass');

    if(cPass.value === password.value){
        register(name.value, email.value, password.value);
    }else {
        sendToast("Password did not match", 'danger');
    }
}

function register(name, email, password){
    let user_Details = {
        name:name,
        email:email,
        password:password
    };

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
                setTimeout(() => {
                    sendToast('Account created successfully', 'green')
                },3000);
                window.location.replace('http://localhost:8080/login');
                return response.json();
            }
        })
        .then(data => {
            console.log(data)
        })
        .catch(error => console.log(error));
}

