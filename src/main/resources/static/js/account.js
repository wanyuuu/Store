/**
 * Created by samsung on 2017/11/30.
 */
var LOGIN_URL="http://localhost:8080/account/login";
$(function () {
    $("#login").click(function () {
        var json=JSON.stringify($("#form1").serializeObject());
        $.ajax({
            data:json,
            contentType:"application/json",
            type:"POST",
            dataType:"JSON",
            url:LOGIN_URL,
            statusCode:{
                200:function (data) {
                    var date=new Date();
                    var min=new Date(date);
                    min.setMinutes(date.getMinutes()+30);//当前时间+30分钟
                    $.cookie("pusername",data.username,{'expires':min});
                    window.location="main.html";
                },
                409:function () {
                    alert("登陆失败");
                }
            }
        })
    })
})