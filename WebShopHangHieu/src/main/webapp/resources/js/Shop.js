/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
   
   $(".begin").keydown(function (e) {
    if (Number.isInteger(e)) {
        e.preventDefault();
        return false;
    }
});
});