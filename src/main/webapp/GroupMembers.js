/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function addMembersToTable(groupMembers) {
    var tableinfo = groupMembers.map(x => "<tr><td>" + x.name + "</td><td>" + x.studentId + "</td><td>" + x.color + "</td></tr>");

    tableinfo.unshift("<table id=\"indextable\" class=\"table\"><tr><th onclick=\"sortByLetters(0)\">Name</th>\n\
    <th onclick=\"sortByNumbers(1)\">Student Id</th>\n\
    <th onclick=\"sortByLetters(2)\">Color</th></tr>");

    tableinfo.push("</table>");
    return tableinfo.join('');
}