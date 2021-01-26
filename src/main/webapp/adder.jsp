<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加广告</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="css/public.css" media="all">
    <style>
        body {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<div class="layui-form layuimini-form">

    <div class="layui-form-item">
        <label class="layui-form-label required">广告名称</label>
        <div class="layui-input-block">
            <input type="text" name="advername" lay-verify="required" lay-reqtext="广告名称不能为空" placeholder="请输入广告名称名称" value="" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">广告分类</label>
        <div class="layui-input-block">
                <input type="text" name="advercategory" lay-verify="required" lay-reqtext="广告分类名称不能为空" placeholder="请输入广告分类名称" value="" class="layui-input">
                <!--            <tip>填写自己管理账号的名称。</tip>-->
            </div>
        </div>


    <div class="layui-form-item">
        <label class="layui-form-label">前端显示页面</label>
        <div class="layui-input-block">
            <select name="showpage"  lay-search>
                <option value="主页面">主页面</option>
                <option value="页面1">页面1</option>
                <option value="页面2">页面2</option>
            </select>
        </div>
    </div>



    <div class="layui-form-item">
        <label class="layui-form-label">图片/文字显示</label>
        <div class="layui-input-block">
            <select name="adverphoto" lay-verify="required"   lay-search id="sel1" >

                <option value="图片显示">图片显示</option>
                <option value="文字显示">文字显示</option>
            </select>
        </div>
        </div>


    <div class="layui-form-item">
        <label class="layui-form-label">是否跳转</label>
        <div class="layui-input-block">

            <select name="adverlink" lay-verify="required"   lay-search id="sel2">

                <option value="跳转">跳转</option>
                <option value="不可跳转">不可跳转</option>
            </select>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">跳转链接</label>
        <div class="layui-input-block">
            <input type="text" name="islink"class="layui-input" >
            <tip>不跳转则不填写。</tip>
        </div>

    </div>



    <div class="layui-form-item">
        <label class="layui-form-label">是否弹窗显示</label>
        <div class="layui-input-block">

            <select name="advertanchuang" lay-verify="required"   lay-search id="sel3">

                <option value="弹窗显示">弹窗显示</option>
                <option value="不弹窗显示">不弹窗显示</option>
            </select>
        </div>
    </div>


    <!--************这里是上传图片的代码***************-->
    <!--************这里添加的隐藏的输入框，用来传递images的参数***************-->
    <input type="hidden" name="adverimgurl" class="image">
    <div class="layui-form-item" id="imgs">
        <label class="layui-form-label ">广告图片:</label>
        <div class="layui-upload">
            <button type="button" class="layui-btn" id="test1">上传广告图片</button>

            <div class="layui-input-block">
                <tip>不显示广告文本消息则不填</tip>
            </div>

            <div class="layui-upload-list">
                <img class="layui-upload-img" id="demo1">
                <p id="demoText"></p>
            </div>
        </div>
    </div>
    <!--************上面里是上传图片的代码***************-->

  <!-- 富文本编辑器-->
    <div class="layui-form-item" id="texts">
        <label class="layui-form-label required">广告文本消息</label>

        <div class="layui-input-block">
            <tip>不显示广告文本消息则不填</tip>
              <textarea id="demo" style="display: none;" lay-verify="content"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
    </div>
</div>

<script src="lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/jquery.min.js"></script>
<script>
    $(function () {

    });
</script>
<script>


    layui.use(['form','upload','layedit','jquery'], function () {

        var form = layui.form,
            layer = layui.layer,
            upload = layui.upload,
            layedit = layui.layedit,
            $ = layui.$;


       var index=layedit.build('demo'); //建立编辑器
         //图片文字显示
//编辑器外部操作

        form.verify({
            content: function(value) {
                return layedit.sync(index);
            }
        });


        //普通图片上传
        var uploadInst = upload.render({
            elem: '#test1'
            ,url: 'upload'
            ,accept:'adverimgurl'
            ,size:50000
            ,before: function(obj){

                obj.preview(function(index, file, result){

                    $('#demo1').attr('src', result);
                });
            }
            ,done: function(res){
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功
                var demoText = $('#demoText');
                demoText.html('<span style="color: #4cae4c;">上传成功</span>');

                var fileupload = $(".image");
                fileupload.attr("value",res.data.src);
                console.log(fileupload.attr("value"));
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });




        //监听提交
        form.on('submit(saveBtn)', function (data) {
            // layer.confirm('确认提交？', function(index){
                    var datas =JSON.stringify(data.field);
                    var demo=$("#demo").val();
            //alert("demo:"+demo);

                // alert("选择sel1---："+sel1);

                    $.ajax({
                        url: "addsubmit",
                        type: "POST",
                        data:{
                            'searchParams': datas,
                            'demo':demo
                        },
                        dataType: "json",
                        success: function(data){
                            if(data.type==="yes"){
                                layer.msg("添加成功", {icon: 6},{time:4000});
                                 window.location.href="adverlist";

                            }else{
                                layer.msg("添加失败", {icon: 5});
                            }
                        },
                        error:function(){
                            alert("错误");
                        },

                    });
                });

//        });


    });
</script>
</body>
</html>