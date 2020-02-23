/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// use URL with /localhost for testing local
var URL = "/2020CA1/api/groupmembers/all"
const table = document.getElementById("MemberTable");
const Reload = document.getElementById("reloadMembers");

fetch(URL)
        .then(res => res.json())
        .then(data =>{
        let list = data.map(function(member){
                // table with values
                return "<tr><td>" + member.name + "</td>" +
                 "<td>" + member.StudentId + "</td>" +
                 "<td>" + member.Color + "</td>" +
                "</tr>";
            }).join("");
            document.getElementById("GroupMemberTabel").innerHTML = list;
})

function reloadNewMembers() {
    fetch(URL)
        .then(res => res.json())
        .then(data =>{ 
            console.log("data", data);
        let list = data.map(function(member){
                // table with values
                return "<tr><td>" + member.name + "</td>" +
                 "<td>" + member.StudentId + "</td>" +
                 "<td>" + member.Color + "</td>" +
                "</tr>";
            }).join('');
            document.getElementById("GroupMemberTabel").innerHTML = list;
})

Reload.onclick = function (e)
{
    e.preventDefault();
        reloadNewMembers()();
};
// alternativ 
//fetch(URL)
//            .then(res => res.json())
//            .then(data => {
//                console.log("data", data);
//
//                let MemberTable = data.map(Member => "<tr><td>" + Member.
//                            + "</td><td>" + Member.make + "</td><td>" + Member.model
//                            + "</td><td>" + Member.year + "</td><td>" + Member.price
//                            + "</td><td>" + Member.color + "</td><td>" + Member.fuel
//                            + "</td></tr>");
//                MemberTable.unshift("<table><tr><th>Name</th><th>StudentId</th>\n\
//                                <th>Color</th></tr>");
//                MemberTable.push("</table");
//                MemberTable = MemberTable.join('');
//                table.innerHTML = MemberTable;
//            });

}
