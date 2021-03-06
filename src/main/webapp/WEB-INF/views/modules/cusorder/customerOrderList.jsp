<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订货管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cusorder/customerOrder/">订货管理列表</a></li>
		<shiro:hasPermission name="cusorder:customerOrder:edit"><li><a href="${ctx}/cusorder/customerOrder/form">订货管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="customerOrder" action="${ctx}/cusorder/customerOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>客户姓名：</label>
				<form:input path="cusname" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>货物名称：</label>
				<form:input path="goods" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>单价：</label>
				<form:input path="price" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>进货量：</label>
				<form:select path="goodsnum" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('order_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>日期：</label>
				<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${customerOrder.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${customerOrder.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>客户姓名</th>
				<th>单价</th>
				<th>进货量</th>
				<th>总价</th>
				<th>日期</th>
				<th>更新日期</th>
				<th>备注</th>
				<th>支付类型</th>
				<shiro:hasPermission name="cusorder:customerOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="customerOrder">
			<tr>
				<td><a href="${ctx}/cusorder/customerOrder/form?id=${customerOrder.id}">
					${customerOrder.cusname}
				</a></td>
				<td>
					${customerOrder.price}
				</td>
				<td>
					${fns:getDictLabel(customerOrder.goodsnum, 'order_type', '')}
				</td>
				<td>
					${customerOrder.sumprice}
				</td>
				<td>
					<fmt:formatDate value="${customerOrder.date}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${customerOrder.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${customerOrder.remarks}
				</td>
				<td>
					${customerOrder.ordertype}
				</td>
				<shiro:hasPermission name="cusorder:customerOrder:edit"><td>
    				<a href="${ctx}/cusorder/customerOrder/form?id=${customerOrder.id}">修改</a>
					<a href="${ctx}/cusorder/customerOrder/delete?id=${customerOrder.id}" onclick="return confirmx('确认要删除该订货管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>