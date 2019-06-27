
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/6/2
  Time: 0:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>我的易购店铺</title>
    <%--  常规所有依赖包--%>
    <jsp:include page="commonsupport.jsp">
        <jsp:param name="" value=""/>
    </jsp:include>
    <style>
        .sidebar{
            background-color: #2a3542;
            color: #bbb;
            position: fixed;
            display: block;
            /*border: 1px solid #000;*/
            width: 220px;
            min-height: 100%;
            left: 0;
            top: 50px;
        }
    </style>
</head>
<body>
<%--the top navigation bar--%>
<nav class="nav" style="height: 56px; background: #eee; padding: 5px;">
    <ul class="nav" style="left: 0; position: absolute;">
        <li class="nav-item">
            <em class="nav-link">我的店铺首页</em>
        </li>
    </ul>
    <ul class="nav" style="right: 0; position: absolute">
        <li class="nav-item">
            <a class="nav-link " href="#">联系客服 </a>
        </li>
        <li class="nav-item">
            <a class="nav-link " href="index.jsp">回到主页 </a>
        </li>
        <li class="nav-item nav">
            <div class="dropdown">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">
                    你好,${sessionScope.username}
                    <span class="caret"></span>
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="javascript:logout();">注销登录</a>
                    <a class="dropdown-item" href="#">修改信息</a>
                    <a class="dropdown-item" href="#">我的购物车</a>
                </div>
            </div>
        </li>
    </ul>
</nav>
<%--the left navigation bar--%>
<nav class="navbar" style="border: 1px;width: 200px; float: left;">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="javascript: void(0);" onclick="showMyGoodsList()">我的商品列表</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">我的店铺订单</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">物流信息列表</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="javascript:void(0);" onclick="addGoods()">添加商品</a>
        </li>
    </ul>
</nav>
<div id="myGoodsList" style=" margin-left: 20px; margin-top: 20px; width: 1000px; float: left">
    <table id="goodsList"></table>
</div>
<div style="display: none; float: left; margin-left: 20%" id="idAddGoods">
    <form action="addGoodsServlet" method="post" enctype="multipart/form-data" onsubmit="return submitCheck()">
        <table class="table">
            <tr>
                <td style="display: none"><label>
                    <input type="text" value="${sessionScope.username}">
                </label></td>
                <td>
                    <label for="goods_name">商品名称： </label>
                    <input type="text" id="goods_name" name="goods_name" placeholder="输入您的商品名称">
                </td>
            </tr>
            <tr>
                <td>
                    <label for=goods_picture>商品图像：</label>
                    <img src="" id="goods_picture" height="100px" alt="暂未选择图像"><br>
                    <input type="file" id="logo" accept="image/png,image/jpeg" name="goods_picture" style="margin-left: 10%">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="goods_type">商品类型：</label>
                    <input type="text" id="goods_type" name="goods_type">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="goods_info">商品描述：</label>
                    <input type="text" id="goods_info" name="goods_info" placeholder="输入对您的店铺描述">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="address">商店地址：</label>
                    <input type="text" id="address" name="shop_address" placeholder="输入地址 中间用空格割开">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="inventory">商品库存：</label>
                    <input type="number" id="inventory" name="inventory">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="price">商品价格：</label>
                    <input type="text" id="price" name="price">
                </td>
            </tr>
            <tr>
                <td>
                    <button type="submit">提交</button>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    function submitCheck(){
        var price = $('#price').val();
        if(price === ''){
            alert("请输入价格");
            return false;
        }
        if(!(/^\d+(\.\d+)?$/.test(price))){
            alert("价格输入不合法，至少保留一位小数");
            return false;
        } else {
            return true;
        }
    }
    $(function () {
        $("#logo").change(function () {
            var $file = $(this);
            var fileObj = $file[0];
            var windowURL = window.URL || window.webkitURL;
            var dataURL;
            var $img = $("#goods_picture");

            if (fileObj && fileObj.files && fileObj.files[0]) {
                dataURL = windowURL.createObjectURL(fileObj.files[0]);
                console.log(dataURL);
                $img.attr('src', dataURL);
            } else {
                dataURL = $file.val();
                var imgObj = document.getElementById("goods_picture");
                imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;

            }
        });
    });
    $(function(){
        $('#goodsList').datagrid({
            title:'我的商品列表',
            iconCls:'icon-edit',
            width:660,
            height:250,
            loadMsg:'正在加载数据',
            singleSelect:true,
            idField:'itemid',
            url:'/myShopGoodsServlet',
            columns:[[
                {field:'goodsNum',title:'',hidden:true},
                {field:'goodsName',title:'商品名称',width:100, editor:'text'},
                {field:'goodsType',title:'商品类型',width:100,editor:'text'},
                {field:'goodsInfo',title:'商品信息',width:160, editor:'text'},
                {field:'inventory',title:'商品库存',width:60,align:'center',editor:'numberbox'},
                {field:'price',title:'价格',width:60,editor:'numberbox',align:'center'},
                {field:'action',title:'操作',width:150,align:'center',
                    formatter:function(value,row,index){
                        if (row.editing){
                            var s = '<a href="javascript:void(0)" onclick="saverow(this)">保存</a> ';
                            var c = '<a href="javascript:void(0)" onclick="cancelrow(this)">取消</a>';
                            return s+c;
                        } else {
                            var e = '<a href="javascript:void(0)" onclick="editrow(this)">编辑</a> ';
                            var d = '<a href="javascript:void(0)" onclick="deleterow(this)">删除</a>';
                            return e+d;
                        }
                    }
                }
            ]],
            //更新某行数据
            onEndEdit:function(index,row){
                $.ajax({
                    type: "post",
                    url: "/updateGoodsServlet",
                    async: true,
                    data: {
                        "goods_num": row.goodsNum,
                        "goods_name":row.goodsName,
                        "goods_info":row.goodsInfo,
                        "goods_type":row.goodsType,
                        "inventory": row.inventory,
                        "price": row.price
                    },
                    success: function(data) {
                        alert(data);
                    },
                    error: function() {
                        alert("更新失败");
                    }
                });

        console.log(row);
            },
            onBeforeEdit:function(index,row){
                row.editing = true;
                $(this).datagrid('refreshRow', index);
            },
            onAfterEdit:function(index,row){
                row.editing = false;
                $(this).datagrid('refreshRow', index);
            },
            onCancelEdit:function(index,row){
                row.editing = false;
                $(this).datagrid('refreshRow', index);
            }
        });
    });
    function addGoods() {
        $('#myGoodsList').hide();
        $('#idAddGoods').show();
    }
    function showMyGoodsList() {
        $('#myGoodsList').show();
        $('#idAddGoods').hide();
    }
    function getRowIndex(target){
        var tr = $(target).closest('tr.datagrid-row');
        return parseInt(tr.attr('datagrid-row-index'));
    }
    function editrow(target){
        $('#goodsList').datagrid('beginEdit', getRowIndex(target));
    }
    function deleterow(target){
        $.messager.confirm('用户确认','此操作不可撤回，您确定要删除吗?',function(r){
            if (r){
                console.log($('#goodsList').datagrid('getSelected'));
                var row = $('#goodsList').datagrid('getSelected');
                $.ajax({
                    type: "post",
                    url: "/deleteGoodsServlet",
                    async: true,
                    data: {
                        "goods_num": row.goodsNum
                    },
                    success: function(data) {
                        if(data === 'true'){
                            alert('删除成功');
                            $('#goodsList').datagrid('deleteRow', getRowIndex(target));
                        } else{
                            alert("删除失败");
                        }
                    },
                    error: function() {
                        alert("删除失败");
                    }
                });
            }
        });
    }
    function saverow(target){
        //console.log($('#goodsList').datagrid('getSelected'));
        $('#goodsList').datagrid('endEdit', getRowIndex(target));
    }
    function cancelrow(target){
        $('#goodsList').datagrid('cancelEdit', getRowIndex(target));
    }
    function insert(){
        var row = $('#goodsList').datagrid('getSelected');
        if (row){
            var index = $('#goodsList').datagrid('getRowIndex', row);
        } else {
            index = 0;
        }
        $('#goodsList').datagrid('insertRow', {
            index: index,
            row:{
                status:'P'
            }
        });
        $('#goodsList').datagrid('selectRow',index);
        $('#goodsList').datagrid('beginEdit',index);
    }
</script>
</body>
</html>
