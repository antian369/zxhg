/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$("#sub").click(function(){
    if(checkForm("#data_form")){
        alertMsg("该功能暂不可用!");
    }
});