/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    $('.carousel').carousel({
        interval: 3000
      });
      
//    $("button.cart-btn").click(function(){
//        $("div.cart-detail").css("display","block");
//        if($("div.cart-detail").is(":visible")){
//            $("div.cart-detail").css("display","block");
//        } else{
//            $("div.cart-detail").css("display","none");
//        }
//    });
});


function addSuccess(id,name,count,price){
    var t=false;
    var idprocart=$(".table-cart input[type='hidden']");
    for(var i=0;i<idprocart.length;i++){
        if (parseInt(idprocart[i].value)===parseInt(id)){
            var txtCount=$(".txt-count")[i];
            txtCount.value=parseInt(txtCount.value)+1;
            var txtCount=$(".txt-total")[i];
            txtCount.value=parseInt(txtCount.value)+price;
            t=true;
        }
    }
    
   if(t!==true){
       $(".table-cart>tbody").append(
        "<tr>" +
           "<td>"+
           "<input type='hidden' value='"+id+"'/>"+
            "</td>" +
           "<td>"+name+"</td>" +
           "<td>"+
           "<input type='text' value='"+count+"' style='width: 50px;background: none;border: none;' disabled='disabled' class='txt-count' />"+
           "</td> " +
           "<td>"+price+"</td>"+
           "<td>"+
           "<input type='text' value='"+price+"' style='width: 70px;background: none;border: none;' disabled='disabled' class='txt-total' />"+

            "</td>"+
           "<td>"+
               "<input type='submit' value='x'"+ 
                "accept='' class='btn btn-outline-success'"+ 
                'onclick="mojarra.ab(this,event,'+"'action'"+',0,0,{'+"'onevent'"+':deleteSuccss('+id+')});return false">'+
           "</td>"+
        "</tr>"
   );
   }
   
   var pri=$("#txt-pri").text();
   
//   console.log(pri);
   pri=parseInt(pri)+parseInt(price);
   $("#txt-pri").text(pri);
   
}
function deleteSuccss(id){
//    var tr=$(".table-cart tbody tr");
    var idprocart=$(".table-cart input[type='hidden']");
    for(var i=0;i<idprocart.length;i++){
        if (parseInt(idprocart[i].value)===parseInt(id)){
//            if(tr.length>idprocart.length)
//            {
//                i=i+1;
//            }
            var pri=$("#txt-pri").text();
            pri=parseInt(pri)-parseInt($(".txt-total")[i].value);
            $("#txt-pri").text(pri);
            $(".table-cart>tbody")[0].deleteRow(i);
            break;
        }
    }
}
     