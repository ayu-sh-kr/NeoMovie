const active = document.getElementById('activeUser');
const totalUser = document.getElementById('totalUser');
const managers = document.getElementById('managers');
const entryPoint = document.getElementById('entryPoint');
function getData(){
    let url = "http://localhost:8080/admin/dashboardData"
    console.log("data loading..")
    setData(url);
}

function setData(uri){
    console.log(uri);
    fetch(uri)
        .then(response => response.json())
        .then(data => {
            console.log(data)
            totalUser.innerText = data["Total User"];
        })
        .catch(error => console.log(error))
}

function getUsers(){
    freeResultBox(entryPoint);
    let uri = "http://localhost:8080/admin/findAll";
    fetchUsers(uri);
}

function fetchUsers(uri){
    fetch(uri)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            data.forEach(value => {
                role = getRoles(value.roleList);
                const component =  `<tr class="border-y border-gray-200 dark:border-gray-700">
                    <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white dark:bg-gray-800">
                    ${value.name}</th>
                    <td class="px-6 py-4">${value.email}\n</td>
                    <td class="px-6 py-4 bg-gray-50 dark:bg-gray-800\">${role}</td>
                    <td class="px-6 py-4">${value.createdOn}</td>
                    </tr>`;
                entryPoint.insertAdjacentHTML("beforeend", component);
            })
        })
        .catch(error => console.log(error));
}

function getRoles(value){
    str = '';
    for(let i = 0; i < value.length; i++){
        console.log(value[i].name);
        str += value[i].name + ' ';
    }
    return str;
}