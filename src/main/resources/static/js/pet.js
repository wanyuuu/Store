/**
 * Created by samsung on 2017/12/14.
 */
var PET_PRODUCT_URL="http://localhost:8080/pet/queryp/";
var PET_ITEMS_URL="http://localhost:8080/pet/queryi/";
var PET_ITEM_URL="http://localhost:8080/pet/queryit/";
$(function () {
    var t=location.href;// .html?cate=FISH  ?pid=D-A-1
    var h=t.lastIndexOf("?");
    var path=t.substring(h+1,t.length);
    var id=path.split("=")[1];//取得FISH
//http://localhost:8080/shop/items.html?pid=
    var i=t.lastIndexOf("/");
    var j=t.lastIndexOf(".");
    var path1=t.substring(i+1,j);
    switch (path1){
        case "product": //通过宠物种类查产品
            petproByCate(id);
            break;
        case "items":
            petitemsByPid(id); //通过产品编号查项目
            break;
        case "item":
            petByItems(id); //通过项目编号查详细信息
            break;
        default:
            break;
    }

})
function petproByCate(id) {
    $.ajax({
        url:PET_PRODUCT_URL+id,
        type:"GET",
        dataType:"json",
        statusCode:{
            200:function (data) {
                $(data).each(function(i){
                    var id=$("#list>tbody>tr:last").attr('id');//取得最后一行id
                    id++;
                    var str="<tr bgcolor='#FFFF88' id='"+id+"'>" +
                        "<td><a href='items.html?pid="+data[i].productid+"'>"
                        +data[i].productid+"</a></td><td>"
                        +data[i].name+"</td></tr>"
                    $("#list").append(str);
                })
            }
        }
    })
}
function petitemsByPid(id){
    $.ajax({
        url:PET_ITEMS_URL+id,
        dataType:"json",
        type:"GET",
        statusCode:{
            200:function (data) {
                $(data).each(function (index) {
                    var i=$("#list>tbody>tr:last").attr('id');
                    i++;
                    var str="<tr bgcolor='#FFFF88'id='"+i+"'><td><a href='item.html?itemid="+data[index].itemid+"'>"+data[index].itemid+"</a></td>" +
                        "<td>"+data[index].productid+"</td>" +
                        "<td>"+data[index].product.name+"</td>" +
                        "<td>"+data[index].listprice+"</td>" +
                        "<td>"+data[index].product.descn+"&nbsp;"+data[index].attr1+"</td></tr>"
               $("#list").append(str);
                })
            }
        }
    })
}
function petByItems(id) {
    $.ajax({
        url:PET_ITEM_URL+id,
        dataType:"json",
        type:"GET",
        statusCode:{
            200:function (data) {
                $("#photo").attr("src","../images/"+data.product.pic);
                $("#itemid").html(data.itemid);
                $("#descn").html(data.attr1);
                $("#price").html(data.listprice);
            }
        }
    })
        
}