/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$("#sub").click(function(){
    if(checkForm("#data_form")){
        alertMsg("该功能暂不可用！");
    }
});
$(".sb:eq(0)").show();
$("#sbfc").change(function(){
    $(".sb").hide().eq($(this).val()).fadeIn(500);
});
