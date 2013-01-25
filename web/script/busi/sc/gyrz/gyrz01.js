/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$("#sub").click(function(){
    if(checkForm("#data_form")){
        alertMsg("校验成功！<br />模拟页面，不能提交数据！");
    }
});
