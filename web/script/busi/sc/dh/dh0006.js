/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
if(!outJson['dhbb']){
    $("#dhbb_table").hide();
    alertMsg("该月还未录入数据");
}else{
    $("#dhbb_table").dataTable(tableTheme);
}