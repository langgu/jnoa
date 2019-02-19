<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商管理</title>
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
		<li class="active"><a href="${ctx}/supplier/supplier/">供应商列表</a></li>
		<shiro:hasPermission name="supplier:supplier:edit"><li><a href="${ctx}/supplier/supplier/form">供应商添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="supplier" action="${ctx}/supplier/supplier/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>供应商名称（supplier）</th>
				<th>注册资本（万元）(registered capital )</th>
				<th>法人</th>
				<th>企业性质(suppler_nature)</th>
				<th>联系人</th>
				<th>电子邮箱</th>
				<th>联系电话</th>
				<th>备注</th>
				<shiro:hasPermission name="supplier:supplier:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="supplier">
			<tr>
				<td><a href="${ctx}/supplier/supplier/form?id=${supplier.id}">
					${supplier.supName}
				</a></td>
				<td>
					${supplier.regCapital}
				</td>
				<td>
					${supplier.legalPerson}
				</td>
				<td>
					${supplier.supNature}
				</td>
				<td>
					${supplier.contact}
				</td>
				<td>
					${supplier.supEmail}
				</td>
				<td>
					${supplier.supTel}
				</td>
				<td>
					${supplier.remarks}
				</td>
				<shiro:hasPermission name="supplier:supplier:edit"><td>
    				<a href="${ctx}/supplier/supplier/form?id=${supplier.id}">修改</a>
					<a href="${ctx}/supplier/supplier/delete?id=${supplier.id}" onclick="return confirmx('确认要删除该供应商吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>