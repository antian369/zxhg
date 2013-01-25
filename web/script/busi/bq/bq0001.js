/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$("#sub").click(function(){   
    if(checkForm("#data_form")){
        alertMsg("该功能暂不可用！");
    //提交前设置内容
    //                if(editor.isEmpty()){
    //                    showErrorDialog("内容不能为空！");
    //                    return;
    //                }
    //                $("#news_content").val(editor.html());

    //清除内容
    //                editor.html('');
    }
});
