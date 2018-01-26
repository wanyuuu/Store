/**
 * Created by samsung on 2017/11/30.
 */
var QUERY_URL="http://localhost:8080/pet/queryc";
var REG_URL="http://localhost:8080/account/reg";
var lan=[{"id":"eng","name":"英文"},{"id":"chi","name":"中文"}];
$(function () {
    $("#lang option").remove();
    for (var index in lan){
        $("#lang").append("<option value='"+lan[index].id+"'>"+lan[index].name+"</option>");
    }

    $("#category option").remove();
    $.ajax({
        url:QUERY_URL,
        type:"GET",
        contentType:"appliaction/json",
        dataType:"JSON",
        statusCode:{
            200:function (data) {
                $(data).each(function (index) {
           $("#category").append("<option value='"+data[index].catid+"'>"+data[index].name+"</option>");
                })

            }
        }
    });
    
    $("#save").click(function () {
        if($("#username").val()==""){
            alert('用户名不能为空！');
            return ;
        }else if($("#password").val()==""){
            alert('密码不能为空！');
            return ;
        }else if($("#repassword").val()==""){
            alert('请再输入一遍密码！');
            return ;
        }else if($("#email").val()==""){
            alert('邮箱不能为空！');
            return ;
        }else if($("#xm").val()==""){
            alert('姓名不能为空！');
            return ;
        }else if($("#address").val()==""){
            alert('地址不能为空！');
            return ;
        }
        if($("#password").val()!=$("#repassword").val()){
            alert('两次密码不一致！');
            return false;
        }
        var json=JSON.stringify($("#form1").serializeObject());
        $.ajax({
            url:REG_URL,
            data:json,
            contentType:"application/json",
            type:"POST",
            statusCode:{
               200:function () {
                   window.location="login.html";
               },
                409:function () {
                 alert("该用户已被注册!")   
                }
            }
        })
    })

})
