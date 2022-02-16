<%--
  Created by IntelliJ IDEA.
  User: 15074
  Date: 2021/8/13
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>welcome</title>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery/jquery-1.9.0.min.js"></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
    * {
        margin: 0;
        border: 0;
    }

    html body {
        height: 100%;
        width: 100%;
    }

    .top {
        position: relative;
        height: 15%;
        background-color: #18324b;
        color: #a9a1a7;
        text-align: center;
        padding-top: 20px;
    }
    .down{
        height: 85%;
    }
    .top a {
        width: 70px;
        height: 30px;
        position: absolute;
        right: 0px;
        bottom: 0px;
        text-decoration: none;
        color: #4bd4ff;
    }
    .top>a:hover {
        color: white;
    }
</style>
<script>
    function tell() {
        alert("成功退出系统，即将返回登陆页面！");
    }
</script>
<body>
    <div class="top">
        <h2>欢迎用户:<shiro:principal/>,使用本系统!!!</h2>
        <a href="shiro/loginOut" onclick="tell()">退出登录</a>
    </div>
    <div class="row down">
        <div class="col-xs-2">
                <div class="panel-group" id="account" role="tablist" aria-multiselectable="true">
                    <C:forEach items="${list}" var="p" varStatus="v">
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="heading_${v.index}">
                                <h4 class="panel-title">
                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                                       href="#collapse_${v.index}" aria-expanded="false" aria-controls="collapse_${v.index}">
                                        ${p.pinfo}
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse_${v.index}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading_${v.index}">
                                <div class="panel-body">
                                    <c:forEach items="${p.list}" var="p2">
                                        <shiro:hasPermission name="${p2.pname}">
                                            <a class="btn btn-primary btn-block" href="${p2.purl}" target="showAll">
                                                ${p2.pinfo}
                                            </a>
                                        </shiro:hasPermission>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </C:forEach>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="heading_11">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                                   href="#collapse_11" aria-expanded="false" aria-controls="collapse_11">
                                    其他操作
                                </a>
                            </h4>
                        </div>
                        <div id="collapse_11" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading_11">
                            <div class="panel-body">
                                <a class="btn btn-primary btn-block" href="#" data-toggle="modal" data-target="#myModal">
                                    修改密码
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
        <div class="col-xs-10">
            <iframe name="showAll" src="http://prts.wiki/w/%E9%A6%96%E9%A1%B5" width="100%" height="100%" frameborder="0"></iframe>
        </div>
    </div>
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
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">新密码:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="password" name="password" placeholder="请输入新密码" >
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
                password: {
                    message: '密码不合法',
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 1,
                            max: 30,
                            message: '请输入1到30个字符'
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
            $.ajax({
                url: './changePassword',
                type: 'post',//PUT DELETE POST
                data: form.serialize(),
                success: function (result) {
                    console.log(result);
                    if (result == 'ok') {
                        alert("修改成功！请退出重新登录");
                        window.location.reload();
                    }else {
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
