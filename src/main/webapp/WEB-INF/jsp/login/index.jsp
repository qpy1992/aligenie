<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<meta charset="utf-8"/>
	<meta name="author" content="" />
	<meta name="keywords" content="" />
	<meta name="viewport" content="width=device-width,initial-scale=1" />
	<title></title>

	<!-- main JS libs -->
	<script src="${pageContext.request.contextPath}/vanilla-cream-css/js/libs/modernizr.min.js"></script>
	<script src="${pageContext.request.contextPath}/vanilla-cream-css/js/libs/jquery-1.10.0.js"></script>
	<script src="${pageContext.request.contextPath}/vanilla-cream-css/js/libs/jquery-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/vanilla-cream-css/js/libs/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/vanilla-cream-css/js/libs/common.js" ></script>

	<script  src="${pageContext.request.contextPath}/vanilla-cream-css/layer-2.1/layer.js"></script>
	<script  src="${pageContext.request.contextPath}/vanilla-cream-css/laypage1.3/laypage.js"></script>
	<script  src="${pageContext.request.contextPath}/vanilla-cream-css/laydate-1.1/laydate.js"></script>

	<!-- Style CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vanilla-cream-css/css/bootstrap.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vanilla-cream-css/style.css" />
	<!-- scripts -->
	<script src="${pageContext.request.contextPath}/vanilla-cream-css/js/general.js"></script>
	<script type="text/javascript">
		var baseurl = "";
		$(function() {
			baseurl = $('#baseUrl').val();

			searchTable(1);  //查询表格

		});

		function searchTable(pageIndex) {
			var index = layer.load(2);
			$('#btnQuery').attr("disabled", true); //attr() 方法设置或返回被选元素的属性值。

			var num = pageIndex;
			if (num <= 0) {
				num = 1;
			}
			var obj = {};
			obj.pageIndex = num;
			obj.pageSize = 10;

			var table = $('#tables');
			$("#tables tr:gt(0)").remove(); //移除除表头外的其他tr
			$.ajax({
				type: "post",
				url: "query",
				data: {"message": json2str(obj)},
				dataType: 'json',
				success: function (d) {
					if (d.total == 0) {
						//empty() 方法从被选元素移除所有内容，包括所有文本和子节点。
						$('.pagin').empty();
						//common.noRecord=无记录
						var trs = '<tr><td colspan="4" align="center">无记录!</td></tr>';
						table.append(trs);
					}
					$.each(d.rows, function (i, e) {
						var trs = '';
						trs += '<tr>';
						trs += '<td>' + (i + 1) + '</td>';
						trs += '<td>' + e.username + '</td>';
						trs += '<td>' + e.truename + '</td>';
						trs += '<td>' + e.rolename + '</td>';
						trs += '</tr>';
						table.append(trs);
					});

					$('#tables tr:odd').addClass('odd');  //隔行色
					//laypage分页
					laypage({
						cont: $('div.pagin'), //容器,支持id名,原生dom对象,jquery对象
						pages: parseInt(d.pages), //通过后台拿到的总页数
						//skin: 'molv',
						//skin: '#AF0000',
						skin: 'yahei',//皮肤颜色
						skip: true,  //开启跳转
						curr: pageIndex, //当前页
						groups: 10, //连续x显示页数
						jump: function (e, first) { //触发分页的回调
							if (!first) { //加此判断,以免初始时无限刷新
								searchTable(e.curr);
							}
						}
					});
					layer.close(index);
//					$('#btnQuery').attr("disabled", false);//attr() 方法设置或返回被选元素的属性值。
				},
				error: function() {
					alert("123");
				}
			});
		}
	</script>

</head>
<body style="background-image: none;">
<input id="baseUrl" type="hidden" value="<%=request.getContextPath() %>"/>
<div class="body_wrap">
	<div class="container">
		<div class="alert alert-success text-center" role="alert">Sptring Boot学习</div>
		<table id="tables" class="table table-striped table-bordered">
			<tr>
				<td>no</td>
				<td>username</td>
				<td>truename</td>
				<td>rolename</td>
			</tr>
		</table>

		<div class="pagin">
		</div>
	</div>
</div>
</body>


</html>