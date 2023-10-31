let block = document.getElementById('toggle');
let value = false;
function show(){
    value = !value;
    if(value){
        block.classList.add("block");
        block.classList.remove("hidden");
    }else{
        block.classList.remove("block");
        block.classList.add("hidden");
    }
}

let filterBlock = document.getElementById('filter-code');
let filterBool = false;
function showFilters(){
    filterBool = ! filterBool;
    if(filterBool){
        filterBlock.classList.add("block");
        filterBlock.classList.remove("hidden");
    }else{
        filterBlock.classList.add("hidden");
        filterBlock.classList.remove("block");
    }
}

let name = document.getElementById('default-checkbox');
let text = document.getElementById('random-checkbox');
let resultBox = document.getElementById('result-box');

function uriBuilder(uri){
    if(name.checked){
        return uri + "actor" + "/" +  document.getElementById('search-input').value;
    }
    else if(text.checked){
        return uri + "text" + "/" + document.getElementById('search-input').value;
    }

}
function freeResultBox(resultBox){
    resultBox.innerText = "";
    let child = resultBox.lastElementChild;
    while (child){
        resultBox.removeChild(child);
        child = resultBox.lastElementChild;
    }
}
function initiateSearch(){
    freeResultBox(resultBox);
    let uri = "http://localhost:8080/search/";
    console.log('searching...')
    console.log(uri)
    uri = uriBuilder(uri);
    console.log(uri);
    fetch(uri)
        .then(res => {
            return res.json();
        })
        .then(data => {
            if(data.length === 0){
                resultBox.innerText = "Found nothing for that query"
            }else if(text.checked){
                data.forEach(value => {
                    const markup = `<div class="container text-xl shadow-green-300
                        shadow-md rounded-md mx-2 my-4 text-center w-[250px]"><a href="/movie" class="
                        w-full py-3 px-4 rounded-md no-underline ">${value.name}</a></div>`;
                    resultBox.insertAdjacentHTML("beforeend", markup);
                })
            }
            else if(name.checked){
                const markup = `<div class="container text-xl shadow-green-300
                        shadow-md rounded-md mx-2 my-4 text-center w-[250px]"><a href="/movie" th:href="@{/movie}" class="
                        w-full px-4 py-3 rounded-md no-underline ">${data.name}</a></div>`;
                if(data.name === undefined){
                    resultBox.innerText = "Not Found";
                }
                else {
                    resultBox.insertAdjacentHTML("beforeend", markup);
                }
            }
        })
        .catch((error) => {
            console.log("Error From: " + error);
        })
}