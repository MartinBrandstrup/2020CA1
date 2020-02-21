/* global fetch */

//var buttonPrice = document.getElementById("buttonPrice");
//var buttonYear = document.getElementById("buttonYear");
//var buttonMake = document.getElementById("buttonMake");
//var buttonAll = document.getElementById("buttonAll");
//var buttonID = document.getElementById("buttonId");
//var buttonSortPrice = document.getElementById("buttonPriceSort");
//var buttonSortYear = document.getElementById("buttonYearSort");
//var buttonSortMake = document.getElementById("buttonMakeSort");

jokeSingleButton.onclick = function (e)
{
    e.preventDefault();
    const carInput = document.getElementById("carInput").value;
    let urlSingle = "/2020CA1/api/car/" + carInput;

    fetch(urlSingle)
            .then(res => res.json())
            .then(data => {
                console.log("data", data);
                paragraph.innerHTML = data.body;
            });
    table.innerHTML = "";
};

//jokeRandomButton.onclick = function (e)
//{
//    e.preventDefault();
//    const jokeInput = document.getElementById("jokeInput").value;
//    let urlRandom = "/2020CA1/api/car/random";
//
//    fetch(urlRandom)
//            .then(res => res.json())
//            .then(data => {
//                console.log("data", data);
//                paragraph.innerHTML = data.body;
//            });
//    table.innerHTML = "";
//};

jokeAllButton.onclick = function (e)
{
    e.preventDefault();
    populateTable();
};

function populateTable()
{
    let urlAll = "/2020CA1/api/car/all";

    fetch(urlAll)
            .then(res => res.json())
            .then(data => {
                console.log("data", data);
        
                let carTable = data.map(n => "<tr><td>" + n.id + "</td><td>" + n.title
                            + "</td><td>" + n.reference + "</td><td>" + n.type + "</td></tr>");
                carTable.unshift("<table><tr><th>id#</th><th>Title</th><th>Reference</th><th>Type</th></tr>");
                carTable.push("</table");
                carTable = carTable.join('');
                table.innerHTML = carTable;
            });
            paragraph.innerHTML = "";
}

populateTable();