const activeUser = document.getElementById('activeUser');
const totalUser = document.getElementById('totalUser');
const managers = document.getElementById('managers');
const entryPoint = document.getElementById('entryPoint');
function getData(){
    let url = "http://localhost:8080/admin/dashboardData"
    setData(url);
}

function setData(uri){
    fetch(uri)
        .then(response => response.json())
        .then(data => {
            totalUser.innerText = data["Total User"];
            activeUser.innerText = data["Active User"];
            managers.innerText = data["Managers"];
        })
        .catch(error => console.log(error));
}

function getUsers(text){
    freeResultBox(entryPoint);
    let uri = "http://localhost:8080/admin/";
    if(text === 'totalUsers')
        fetchUsers(uri + 'findAll');
    else if(text === 'activeUsers')
        fetchUsers(uri + 'activeUsers')
    else if(text === 'managers')
        fetchUsers(uri + 'managers');
}

function fetchUsers(uri){
    fetch(uri)
        .then(response => response.json())
        .then(data => {
            data.forEach(value => {
                const role = getRoles(value.roleList);
                const component =  `<tr class="border-y border-gray-200 dark:border-gray-700">
                    <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white dark:bg-gray-800">
                    <a href="/admin/userProfile?email=${value.email}">${value.name}</a></th>
                    <td class="px-6 py-4">${value.email}\n</td>
                    <td class="px-6 py-4 bg-gray-50 dark:bg-gray-800\">${role}</td>
                    <td class="px-6 py-4">${value.createdOn}</td>
                    <td class="px-6 py-4 bg-gray-50 dark:bg-gray-800\">${value.active}</td>
                    </tr>`;
                entryPoint.insertAdjacentHTML("beforeend", component);
            })
        })
        .catch(error => console.log(error));
}

function getRoles(value){
    str = '';
    for(let i = 0; i < value.length; i++){
        str += value[i].name + ' ';
    }
    return str;
}