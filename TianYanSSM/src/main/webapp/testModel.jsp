<%--
  Created by IntelliJ IDEA.
  User: 15074
  Date: 2022/1/25
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Carousel Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!--  IE10 viewport hack for Surface/desktop Windows 8 bug
    <link href="http://v3.bootcss.com/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
     HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries
    [if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container text-center">
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">修改信息</button>
</div>
<div class="modal fade " id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">用户信息修改</h4>
            </div>

            <div class="modal-body">
                <form id="updateform">
                    <div class="form-group">
                        <label for="loginname" class="control-label">用户名:</label>
                        <input type="text" class="form-control" id="loginname" name="loginname">
                    </div>
                    <div class="form-group">
                        <label for="email" class="control-label">Email:</label>
                        <input type="text" class="form-control" id="email" name="email">
                    </div>
                    <div class="form-group">
                        <label for="phone" class="control-label">电话:</label>
                        <input type="text" class="form-control" id="phone" name="phone">
                    </div>
                    <div class="form-group">
                        <label for="address" class="control-label">收货地址:</label>
                        <textarea class="form-control" id="address" name="address"></textarea>
                    </div>
                    <div class="text-right">
                        <span id="returnMessage" class="glyphicon"> </span>
                        <button type="button" class="btn btn-default right" data-dismiss="modal">关闭</button>
                        <button id="submitBtn" type="button" class="btn btn-primary">修改</button>

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>

<script type='text/javascript'>
    var form = $('#updateform');
    $(document).ready(function () {

        form.bootstrapValidator({
            message: '输入值不合法',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                loginname: {
                    message: '用户名不合法',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        stringLength: {
                            min: 3,
                            max: 30,
                            message: '请输入3到30个字符'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\. \u4e00-\u9fa5 ]+$/,
                            message: '用户名只能由字母、数字、点、下划线和汉字组成 '
                        }
                    }
                }
                , email: {
                    validators: {
                        notEmpty: {
                            message: '邮箱地址不能为空'
                        },
                        emailAddress: {
                            message: '邮箱地址格式有误'
                        }
                    }
                }, phone: {
                    validators: {
                        notEmpty: {
                            message: '手机号不能为空'
                        },
                        regexp: {
                            regexp: "^([0-9]{11})?$",
                            message: '手机号码格式错误'
                        }
                    }
                }, address: {
                    validators: {
                        notEmpty: {
                            message: '地址不能为空'
                        }, stringLength: {
                            min: 8,
                            max: 60,
                            message: '请输入5到60个字符'
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
            $.ajax({
                url: 'validator.json',
                async: false,//同步，会阻塞操作
                type: 'GET',//PUT DELETE POST
                data: form.serialize(),
                complete: function (msg) {
                    console.log('完成了');
                },
                success: function (result) {
                    console.log(result);
                    if (result) {
                        window.location.reload();
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
</body>
</html>
