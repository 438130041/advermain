<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/1/23 0023
  Time: 上午 8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>页面1</title>
</head>
<style>
    *
    {
        margin:0px;
        padding:0px;
    }
    .zhezhao
    {
        width:100%;
        height:100%;
        background-color:#000;
        filter:alpha(opacity=50);
        -moz-opacity:0.5;
        opacity:0.8;
        position:absolute;
        left:0px;
        top:0px;
        display:none;
        z-index:1000;
    }
    .login
    {
        width:280px;
        height:100px;
        position:absolute;
        /*top:200px;*/
        left:50%;
        /*background-color:white;*/
        margin-left:-140px;
        display:none;
        z-index:1500;
    }
    .content
    {
        margin-top:50px;
        color:red;
        line-height:200px;
        height:200px;
        text-align:center;
    }
    .jiantous{
        padding-left: 15rem;
        margin-top: -3rem;
    }
    .covers{
        color: lightgoldenrodyellow;

    }
    .zhid{
        color: greenyellow;
        padding-left: 5rem;
        font-size: 24px;
    }
</style>
<body>



<div class="id" style="display: none">${shows.id}</div>
<div class="showimg"><img src="${shows.adverimgurl}" alt=""></div>
<div class="showtext">${shows.advertext}</div>
<div class="showis" style="display: none">${shows.adverphoto}</div>

<div class="linked" style="display: none">${shows.islink}</div>

<div class="showtiaozhuang">   <div class="showtiaozhuang"><a href="${shows.islink}?id=${shows.id}">点我跳转</a></div></div>
<div class="showlink" style="display: none">${shows.adverlink}</div>

<div class="showtanchuang" style="display: none">${shows.advertanchuang}</div>

<!-- 弹窗-->
<div class="zhezhao" id="zhezhao"></div>
<div class="login" id="login">

    <div class="jiantous">
        <img src="images/jiantou.png" alt="" style="width: 200%">
    </div>
    <div class="covers">
        <a  href="${shows.islink}?id=${shows.id}" style="color: white">点我跳转</a>
    </div>

    <br>

    <div class="showimgs"><img src="${shows.adverimgurl}" alt=""></div>
    <div class="showtexts">${shows.advertext}</div>

    <div id="btclose" class="zhid">我知道啦</div> </div>
</body>
<script src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script>

    $(function () {
        //
        // var id=$(".id").html();
        //
        // $.ajax({
        //     type:'post',
        //     dataType:'json',
        //     data:{
        //
        //         'id':id
        //     },
        //     url:'advertotal',
        //     success:function (data) {
        //         if (data.type==="ok"){
        //
        //         }
        //     },
        //     error:function () {
        //         alert("系统错误");
        //     }
        // });


        $(".showimg").hide();
        $(".showtext").hide();
        $(".showtiaozhuang").hide();
        var showlink=$(".showlink").html();
        var showis=$(".showis").html();
        var showtanchuang=$(".showtanchuang").html();
        if (showis==="图片显示"){
            $(".showimg").show();
            $(".showtext").hide();
        }
        if (showis==="文字显示"){
            $(".showimg").hide();
            $(".showtext").show();
        }

        if (showlink==="不可跳转"){
            $(".showtiaozhuang").hide();
        }

        if (showlink==="跳转"){
            $(".showtiaozhuang").show();
        }

        if (showtanchuang==="弹窗显示"){
            var zhezhao=document.getElementById("zhezhao");
            var login=document.getElementById("login");
            // var bt=document.getElementById("bt");
            var btclose=document.getElementById("btclose");

            // bt.onclick=function()
            // {
            zhezhao.style.display="block";
            login.style.display="block";
            //   }
            btclose.onclick=function()
            {
                zhezhao.style.display="none";
                login.style.display="none";
            }

            if (showlink==="跳转"){

                $(".covers").show();
                $(".showtiaozhuang").hide();
            }

            if (showlink==="不可跳转"){

                $(".covers").hide();
                $(".showtiaozhuang").hide();
            }

            if (showis==="图片显示"){
                $(".showimgs").show();
                $(".showtexts").hide();

                $(".showimg").hide();
                $(".showtext").hide();

            }
            if (showis==="文字显示"){
                $(".showimgs").hide();
                $(".showtexts").show();

                $(".showimg").hide();
                $(".showtext").hide();
            }



        }

        if (showtanchuang==="不弹窗显示"){
            console.log("不弹窗显示，不做任何操作");
        }

    })
    function forlink() {
        var linked=$(".linked").html();
        var id=$(".id").html();
        alert("linked:"+linked);
        location.href="linked?id="+id;
    }

</script>
</html>
