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
                url: 'showCustomervisitSelectIn',         //请求后台的URL（*）
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
                    field: 'id',
                    title: '寻访ID',
                }, {
                    field: 'cusname',
                    title: '客户名称',
                }, {
                    field: 'empname',
                    title: '员工名称',
                },{
                    field: 'content',
                    title: '谈话记录',
                },{
                    field: 'date',
                    title: '谈话时间',
                }]
            })
        }
        return obj;
    }
</script>
</html>
