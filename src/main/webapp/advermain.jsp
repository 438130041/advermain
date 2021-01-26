<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>广告管理端</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="css/public.css" media="all">
</head>
<style type="text/css">
    .layui-table-cell {

    width: 100px;

    }
    .msj{
        line-height: 5rem;
    }
</style>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <fieldset class="table-search-fieldset">
            <legend>操作</legend>
            <div style="margin: 10px 10px 10px 10px">

                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">



                    </div>
                </form>
            </div>
        </fieldset>


        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加广告 </button>
<!--                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>-->
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
    </div>
</div>
<script type="text/html" id="barDemo">

    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs" lay-event="del">删除</a>


</script>
<script src="lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table',"laydate"], function () {
        var $ = layui.jquery,
            form = layui.form,
            laydate=layui.laydate,
            table = layui.table;


        table.render({
            elem: '#currentTableId',
            url: 'adver_find',
            toolbar: '#toolbarDemo',
            //cellMinWidth: 150,//全局定义常规单元格的最小宽度

            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [
                [
                    {type: 'checkbox', fixed: 'left'}
                    , {field: 'id', title: '编号'}
                    , {field: 'advername', title: '广告名称'}
                    , {field: 'adverclick', title: '广告点击量'}
                    , {field: 'advercategory', title: '广告分类'}
                    , {field: 'adverphoto', title: '图片/文字'}
                    , {field: 'adverlink', title: '链接是跳转'}
                    , {field: 'showpage', title: '显示页面'}
                    , {field: 'advertanchuang', title: '是否弹窗显示'}
                    , {field: 'advertext', title: '广告文本消息'}
                    , {field: 'islink', title: '跳转链接'}
                    ,{field:'adverimgurl',title:'广告图片',templet:'<div class="msj"><img src="{{d.adverimgurl}}" class="layui-table-cell"></div>'}
                    , {field: 'createtime', title: '操作时间',templet: '<div>{{layui.util.toDateString(d.createtime, "yyyy-MM-dd  HH:mm:ss") }}</div>',sort:true}
                    , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 260, align: 'center'}
                ]
            ],
            limits: [100, 500, 1000, 3000, 5000, 8000],
            limit: 100,
            height:700,
            page: true,
            skin: 'line'
        });


        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {

            var result = JSON.stringify(data.field);

            // alert(starttime);
            // layer.alert(result, {
            //     title: '最终的搜索信息'
            // });

            //执行搜索重载
            table.reload('currentTableId', {

                page: {
                    curr: 1
                }
                , where: {
                    searchParams: result

                }
            }, 'data');

            return false;
        });

        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                    location.href="addle";
                // var index = layer.open({
                //     title: '添加用户',
                //     type: 2,
                //     shade: 0.2,
                //     maxmin:true,
                //     shadeClose: true,
                //     area: ['100%', '100%'],
                //     content:'/addle',
                // });
                // $(window).on("resize", function () {
                //     layer.full(index);
                // });

            }
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）


            if (obj.event === 'edit') {
                   location.href="updates?id="+data.id;

            }




            if (obj.event === 'del') {
                $.ajax({
                    url: "delforit",
                    type: "POST",
                    data:{
                       'id':data.id
                    },
                    dataType: "json",
                    success:function (data) {
                        if(data.type==="yes"){
                            layer.msg("删除成功", {icon: 6},{time:4000});

                            window.location.reload();
                        }else{
                            layer.msg("删除失败", {icon: 5});
                        }
                    },
                    error:function(){
                        alert(data);

                    }
                })

        }




    });

    });
</script>

</body>
</html>