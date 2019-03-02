<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户信息管理管理</title>
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
		<li class="active"><a href="${ctx}/cus/customer/">客户信息管理列表</a></li>
		<shiro:hasPermission name="cus:customer:edit"><li><a href="${ctx}/cus/customer/form">客户信息管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="customer" action="${ctx}/cus/customer/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>性别：</label>
				<form:radiobuttons path="sex" items="${fns:getDictList('cus_sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>客户来源：</label>
				<form:input path="source" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>年：</label>
				<input name="year" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${customer.year}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>月：</label>
				<input name="month" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${customer.month}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>日：</label>
				<input name="day" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${customer.day}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>性别</th>
				<th>客户来源</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>年</th>
				<th>月</th>
				<th>日</th>
				<shiro:hasPermission name="cus:customer:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="customer">
			<tr>
				<td><a href="${ctx}/cus/customer/form?id=${customer.id}">
					${customer.username}
				</a></td>
				<td>
					${fns:getDictLabel(customer.sex, 'cus_sex', '')}
				</td>
				<td>
					${customer.source}
				</td>
				<td>
					<fmt:formatDate value="${customer.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${customer.remarks}
				</td>
				<td>
					${customer.year}
				</td>
				<td>
					${customer.month}
				</td>
				<td>
					${customer.day}
				</td>
				<shiro:hasPermission name="cus:customer:edit"><td>
    				<a href="${ctx}/cus/customer/form?id=${customer.id}">修改</a>
					<a href="${ctx}/cus/customer/delete?id=${customer.id}" onclick="return confirmx('确认要删除该客户信息管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>