/* global fetch */

const table = document.getElementById("carTable");
const buttonAll = document.getElementById("buttonAll");
const buttonSortPrice = document.getElementById("buttonSortPrice");
const buttonSortYear = document.getElementById("buttonSortYear");
const buttonSortMake = document.getElementById("buttonSortMake");

function populateTable()
{
    let urlAll = "/2020CA1/api/car/all";

    fetch(urlAll)
            .then(res => res.json())
            .then(data => {
                console.log("data", data);

                let carTable = data.map(car => "<tr><td>" + car.id
                            + "</td><td>" + car.make + "</td><td>" + car.model
                            + "</td><td>" + car.year + "</td><td>" + car.price
                            + "</td><td>" + car.color + "</td><td>" + car.fuel
                            + "</td></tr>");
                carTable.unshift("<table><tr><th>id#</th><th>Make</th>\n\
                                <th>Model</th><th>Year</th><th>Price</th>\n\
                                <th>Color</th><th>Fuel Type</th></tr>");
                carTable.push("</table");
                carTable = carTable.join('');
                table.innerHTML = carTable;
            });
}

//populateTable();

buttonAll.onclick = function (e)
{
    e.preventDefault();
    populateTable();
};

buttonSortPrice.addEventListener("click", function ()
{
    console.log("price");
    sortTable(4)
});

buttonSortYear.addEventListener("click", function ()
{
    console.log("year");
    sortTable(3)
});

buttonSortMake.addEventListener("click", function ()
{
    console.log("make");
    sortTable(1)
});

//const th = document.getElementsByTagName("th");
//
//for (var i = 0; i < th.length; i++) {
//    th[i].addEventListener("click", item(i));
//}
//
//function item(i)
//{
//    return function ()
//    {
//        console.log(i);
//    }
//}
//
//buttonAll.onclick = function (e)
//{
//    e.preventDefault();
//    populateTable();
//};

//Courtesy of https://github.com/jjrwus/youtube/blob/master/Sort_table_vanilla_JavaScript/main.js
function sortTable(c)
{
    var table, rows, switching, i, x, y, shouldSwitch;
    table = document.getElementById("carTable");
    switching = true;
    while (switching)
    {
        switching = false;
        rows = table.rows;
        for (i = 1; i < (rows.length - 1); i++)
        {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("td")[c];
            y = rows[i + 1].getElementsByTagName("td")[c];
            if (x === parseInt(x, 10))
                if (Number.isInteger(x))
                {
                    console.log("IsNumber");
                    if (parseInt(x.innerHTML, 10) < parseInt(y.innerHTML, 10))
                    {
                        shouldSwitch = true;
                        break;
                    }
                }
                else
                    (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase())
            {
                console.log("IsNotNumber");
                shouldSwitch = true;
                break;
            }
        }
        if (shouldSwitch)
        {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
        }
    }
}