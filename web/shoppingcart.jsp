<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/5/22
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>我的购物车</title>
    <jsp:include page="commonsupport.jsp">
        <jsp:param name="" value=""/>
    </jsp:include>
</head>
<body>
    <jsp:include page="head.jsp">
        <jsp:param name="" value=""/>
    </jsp:include>
    <div align="center">
        <table id="cartList"></table>
    </div>
</body>
<script type="text/javascript">
    $(function(){
        $('#cartList').datagrid({
            title:'我的购物列表',
            iconCls:'icon-edit',
            width:730,
            height:250,
            loadMsg:'正在加载数据',
            singleSelect:true,
            idField:'cartId',
            url:'/getCartServlet',
            columns:[[
                {field:'cartId',title:'',hidden:true},
                {field:'customerNum',title:'商家ID',width:100},
                {field:'goodsNum',title:'商品ID',width:100},
                {field:'number',title:'购买数量',width:100},
                {field:'payState',title:'支付状态',width:80,align:'center'},
                {field:'comments',title:'评论',width:200,editor:'text',align:'center'},
                {field:'action',title:'操作',width:150,align:'center',
                    formatter:function(value,row,index){
                        if (row.editing){
                            var s = '<a href="javascript:void(0)" onclick="saverow(this)">保存评论</a> ';
                            var c = '<a href="javascript:void(0)" onclick="cancelrow(this)">取消</a>';
                            return s+c;
                        } else {
                            var e = '<a href="javascript:void(0)" onclick="editrow(this)">编辑评论</a> ';
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
                    url: "/updateCommentsServlet",
                    async: true,
                    data: {
                        "cartId": row.cartId,
                        "comments": row.comments
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

    function getRowIndex(target){
        var tr = $(target).closest('tr.datagrid-row');
        return parseInt(tr.attr('datagrid-row-index'));
    }
    function editrow(target){
        $('#cartList').datagrid('beginEdit', getRowIndex(target));
    }
    function deleterow(target){
        $.messager.confirm('用户确认','此操作不可撤回，您确定要删除吗?',function(r){
            if (r){
                console.log($('#cartList').datagrid('getSelected'));
                var row = $('#cartList').datagrid('getSelected');
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
                            $('#cartList').datagrid('deleteRow', getRowIndex(target));
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
        $('#cartList').datagrid('endEdit', getRowIndex(target));
    }
    function cancelrow(target){
        $('#cartList').datagrid('cancelEdit', getRowIndex(target));
    }
</script>
</html>
