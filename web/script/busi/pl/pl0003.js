/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$("#user_table").dataTable(tableTheme);
$(".operate").each(function(){
    //设置操作文字
    if($(this).attr("zt") == "1"){
        $(this).html("删除");
    }else if($(this).attr("zt") == "0"){
        $(this).html("恢复");
    }
});

$(".operate").click(function(){
    //操作  点击事件
    var options = new AjaxOptions();
    options.put("service_code", "P11006");
    options.put("username", $(this).attr("id"));
    if($(this).attr("zt") == "1"){
        options.put("zt", "0");
    }else if ($(this).attr("zt") == "0"){
        options.put("zt", "1");
    }
    options.sus = function(){
        $("#page_form").submit();
    };
    $.ajax(options);
});
//重置密码点击事件
$(".reset_psw").click(function(){
    $("#reset_psw_username").val($(this).attr("username"));
    $("#reset_psw_dialog").dialog("open");
});

//重置密码弹出框
$( "#reset_psw_dialog" ).dialog({
    autoOpen: false,
    height: 150,
    width: 350,
    modal: true,
    buttons: {
        "重置": function() {
            var a = password("密码", "#reset_psw_dialog #password");
            if(a != 1){
                alertMsg(a);
                return;
            }
            if($("#reset_psw_dialog #password").val() != $("#reset_psw_dialog #password_again").val()){
                alertMsg("两次输入的密码不一致");
                return;
            }
            var options = new AjaxOptions();
            options.put("service_code", "P11007");
            options.put("username", $("#reset_psw_username").val());
            options.put("password", $("#reset_psw_dialog #password").val());
            options.sus = function(){
                $("#reset_psw_dialog").dialog( "close" );
                $("#reset_psw_dialog #password").val("");
                $("#reset_psw_dialog #password_again").val("");
            };
            $.ajax(options);
        },
        "取消": function() {
            $( this ).dialog( "close" );
        }
    }
});

//创建用户点击事件
$("#create_user").click(function(){
    $("#create_user_dialog").dialog("open");
});

//创建用户弹出框
$("#create_user_dialog").dialog({
    autoOpen: false,
    height: 500,
    width: 600,
    modal: true,
    buttons: {
        "创建": function() {
            if(checkForm("#create_user_form")){
                if($("#create_user_form #password").val() != $("#create_user_form #password_again").val()){
                    alertMsg("两次输入的密码不一致！");
                    return;
                }
                var options = new AjaxOptions("#create_user_form");
                options.put("service_code", "P11008");
                options.sus = function(){
                    setTimeout(function(){
                        location.reload();
                    }, 1000);
                };
                $.ajax(options);
            }
        },
        "取消": function() {
            $( this ).dialog( "close" );
        }
    }
});

$(".info").click(function(){
    $("#user_info_dialog").dialog('option', "title", $(this).attr("username") + " 的信息");
    var options = new AjaxOptions();
    options.put("service_code", "S11007");
    options.isAlert = false;
    options.put("username", $(this).attr("username"));
    options.padBackElement = "#user_info_dialog";
    options.sus = function(){
        padBackData(this.responseData, "#update_user_form");
        $("#user_info_dialog").dialog("open");
    };
    $.ajax(options);
});

//用户详细信息弹出框
$("#user_info_dialog").dialog({
    autoOpen: false,
    height: 450,
    width: 600,
    modal: true,
    buttons: {
        "修改" : function(){
            $( this ).dialog( "close" );
            $("#update_user_dialog").dialog("open");
        },
        "关闭": function() {
            $("#update_user_dialog:input").val("");
            $( this ).dialog( "close" );
        }
    }
});

//信息用户信息弹出框
$("#update_user_dialog").dialog({
    autoOpen: false,
    height: 400,
    width: 600,
    modal: true,
    buttons: {
        "保存" : function(){
            if(checkForm("#update_user_form")){
                var options = new AjaxOptions("#update_user_form");
                options.put("service_code", "P11011");
                options.put("username", $("#update_user_form #username").html());
                options.sus = function(){
                    setTimeout(function(){
                        location.reload();
                    }, 800);
                };
                $.ajax(options);
            }
        },
        "关闭": function() {
            $( this ).dialog( "close" );
        }
    }
});