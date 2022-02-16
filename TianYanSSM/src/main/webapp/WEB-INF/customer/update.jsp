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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>showAll</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/javascript/bootstrap-3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/javascript/bootstrap-table/bootstrap-table.css">
    <script src="${pageContext.request.contextPath}/javascript/jquery-1.12.4.js"></script>
</head>

<body>
    <%--<input type="button" onclick="getJson()"/>--%>
    <table id="mytable">
    </table>
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
                        <a href="#" class="btn btn-sm btn-success" data-toggle="modal" data-target="#myModal" onclick="edit(`+value+`)">修改</a>
                    `;
        }
        function edit(id){
            // alert("yes,you are editing-------->"+id);
            // console.log(id);
            $.ajax({
                url: "findCustomerById",
                data:{
                    "id":id
                },
                type:"get",
                dataType:"json",
                success:function (jsonObj){
                    $("#cid").val(jsonObj.cid);
                    $("#cusname").val(jsonObj.cusname);
                    $("#address").val(jsonObj.address);
                    $("#tel").val(jsonObj.tel);
                    $("#contact").val(jsonObj.contact);
                    $("#email").val(jsonObj.email);
                }

            })
        }
        function doEdit(){
            alert("Edit---->Do Edit!")
            // $.ajax({
            //     url: "../ProvidersServlet?action=doEdit",
            //     data:$("form").serialize(),
            //     type:"get",
            //     success:function (msg){
            //         if(msg == "ok"){
            //             $("#myModal").modal('hide');
            //             $("#mytable").bootstrapTable("refresh");
            //         }else{
            //             alert("修改错误，您输入的名称已存在或不合法！");
            //         }
            //     }
            // })
        }

    </script>
    <script src="${pageContext.request.contextPath}/javascript/bootstrap-3.4.1/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/bootstrap-table/bootstrap-table.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        修改客户相关数据
                    </h4>
                </div>
                <div class="modal-body">
                    <form id="updateform" class="form-horizontal">
                        <input type="text" hidden name="cid" id="cid">
                        <div class="form-group">
                            <label for="cusname" class="col-sm-2 control-label">客户姓名:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="cusname" name="cusname" placeholder="请输入客户姓名" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">电子邮件:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="email" name="email" placeholder="请输入客户电子邮件"  >
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="address" class="col-sm-2 control-label">客户地址:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="address" name="address" placeholder="请输入客户地址" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="tel" class="col-sm-2 control-label">电话号码:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="tel" name="tel" placeholder="请输入客户电话号码"  >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="contact" class="col-sm-2 control-label">联系人:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="contact" name="contact" placeholder="请输入客户联系人" >
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <span id="returnMessage" class="glyphicon"> </span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        关闭
                    </button>
                    <button id="submitBtn" type="button" class="btn btn-primary" >
                        确认修改
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</body>
<script src="https://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>
<script type='text/javascript'>
    var form = $('#updateform');
    $(function (){
        form.bootstrapValidator({
            message: '输入值不合法',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                cusname: {
                    message: '用户名不合法',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        stringLength: {
                            min: 1,
                            max: 30,
                            message: '请输入1到30个字符'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\. \u4e00-\u9fa5 ]+$/,
                            message: '用户名只能由字母、数字、点、下划线和汉字组成 '
                        }
                    }
                },
                tel: {
                    validators: {
                        notEmpty: {
                            message: '手机号不能为空'
                        },
                        regexp: {
                            regexp: /^([0-9]{11})?$/,
                            message: '手机号码格式错误'
                        }
                    }
                }, address: {
                    validators: {
                        notEmpty: {
                            message: '地址不能为空'
                        }, stringLength: {
                            min: 1,
                            max: 60,
                            message: '请输入1到60个字符'
                        }
                    }
                }, contact: {
                    validators: {
                        notEmpty: {
                            message: '联系人不能为空'
                        }, stringLength: {
                            min: 1,
                            max: 60,
                            message: '请输入1到60个字符'
                        }
                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: '邮箱地址不能为空'
                        },
                        emailAddress: {
                            message: '邮箱地址格式有误'
                        }
                    }
                }
            }
        });
    });
    $("#submitBtn").click(function () {
        //进行表单验证
        var bv = form.data('bootstrapValidator');
        bv.validate();
        if (bv.isValid()) {
            console.log(form.serialize());
            //发送ajax请求
            // $.ajax({
            //     url: "../ProvidersServlet?action=doEdit",
            //     data:$("form").serialize(),
            //     type:"get",
            //     success:function (msg){
            //         if(msg == "ok"){
            //             $("#myModal").modal('hide');
            //             $("#mytable").bootstrapTable("refresh");
            //         }else{
            //             alert("修改错误，您输入的名称已存在或不合法！");
            //         }
            //     }
            // })
            $.ajax({
                url: 'doCustomerUpdate',
                type: 'post',//PUT DELETE POST
                data: form.serialize(),
                complete: function (msg) {
                    console.log('完成了');
                },
                success: function (result) {
                    console.log(result);
                    if (result == 'ok') {
                        alert("修改成功！");
                        window.location.reload();
                    }else if (result == 'repeat') {
                        $("#returnMessage").hide().html('<label class="label label-danger">客户名称重复!</label>').show(300);
                    } else {
                        $("#returnMessage").hide().html('<label class="label label-danger">修改失败!</label>').show(300);
                    }
                }, error: function () {
                    $("#returnMessage").hide().html('<label class="label label-danger">修改失败!</label>').show(300);
                }
            })
        }
    });
</script>
</html>
