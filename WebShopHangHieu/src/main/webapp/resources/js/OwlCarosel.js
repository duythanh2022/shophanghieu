/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    $('.owl-carousel').owlCarousel({
    loop:true,
    margin:10,
    nav:true,
   
    responsive:{
        0:{
            items:1
        },
        200:{
            items:2
        },
        700:{
            items:3
        }
    }
    
});
});