<%--
  Created by IntelliJ IDEA.
  User: 15074
  Date: 2022/1/22
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showAll</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/javascript/bootstrap-3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/javascript/bootstrap-table/bootstrap-table.css">
    <script src="${pageContext.request.contextPath}/javascript/jquery-1.12.4.js"></script>
</head>
<script src="${pageContext.request.contextPath}/javascript/bootstrap-3.4.1/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/javascript/bootstrap-table/bootstrap-table.js"></script>
<script src="${pageContext.request.contextPath}/javascript/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<%--<script>--%>
<%--    function getJson(){--%>
<%--        $.ajax({--%>
<%--            url:"showAllCustomer",--%>
<%--            type:"post",--%>
<%--            contentType:"application/json;charset=utf-8",--%>
<%--            success:function (obj) {--%>
<%--                alert("what");--%>
<%--                console.log(obj);--%>
<%--            }--%>
<%--        });--%>
<%--    }--%>
<%--</script>--%>
<body>
    <%--<input type="button" onclick="getJson()"/>--%>
    <table id="mytable">
    </table>
</body>
<script>
    $(function (){
        let a =  new Mytable();
        a.init();
    })
    var Mytable = function (){
        let obj = new Object();
        obj.init = function (){
            $('#mytable').bootstrapTable({
                url: 'showCustomerQueryIn',         //请求后台的URL（*）
                method: 'post',                      //请求方式（*）
                // contentType: "application/json;charset=utf-8",
                striped: true,                      //是否显示行间隔色
                pagination: true,                   //是否显示分页（*）
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber:1,                       //初始化加载第一页，默认第一页
                pageSize: 5,                       //每页的记录行数（*）
                pageList: [5,10, 25, 50, 100],        //可供选择的每页的行数（*）
                strictSearch: true,
                showColumns: true,                  //是否显示所有的列
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                columns: [{
                    checkbox: true
                }, {
                    field: 'cid',
                    title: '客户ID',
                }, {
                    field: 'cusname',
                    title: '客户名称',
                }, {
                    field: 'address',
                    title: '地址',
                },{
                    field: 'contact',
                    title: '联系人',
                },{
                    field: 'tel',
                    title: '电话号码',
                },{
                    field:'email',
                    title:'电子邮件',
                },{
                    field:'cid',
                    title:'操作',
                    formatter:editAndDel
                }]
            })
        }
        return obj;
    }
    function editAndDel(value,row,index){
        return `
                        <a href="#" class="btn btn-sm btn-danger" onclick="del(`+value+`)">删除</a>
                    `;
    }
    function del(id){
        $.ajax({
            url:"doCustomerDelete",
            data:{
                "id":id
            },
            type:"get",
            success:function (msg){
                alert(msg);
                if (msg == "ok"){
                    $("#mytable").bootstrapTable("refresh");
                    alert("删除成功！");
                }else{
                    alert("对不起，该客户是我们的VIP,已经有寻访或者雇员跟进！");
                }
            }
        })
    }
</script>
</html>
