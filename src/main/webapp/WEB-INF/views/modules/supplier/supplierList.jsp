<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商管理管理</title>
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
		<li class="active"><a href="${ctx}/supplier/supplier/">供应商管理列表</a></li>
		<shiro:hasPermission name="supplier:supplier:edit"><li><a href="${ctx}/supplier/supplier/form">供应商管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="supplier" action="${ctx}/supplier/supplier/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%--<li><label>供应商名称（supplier）：</label>--%>
				<%--<form:input path="supName" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
			<%--</li>--%>
			<%--<li><label>注册资本（万元）(registered capital )：</label>--%>
				<%--<form:input path="regCapital" htmlEscape="false" class="input-medium"/>--%>
			<%--</li>--%>
			<%--<li><label>法人：</label>--%>
				<%--<form:input path="legalPerson" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
			<%--</li>--%>
			<li><label>企业性质：</label>
				<form:select path="supNature" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sup_nature')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<%--<li><label>网址：</label>--%>
				<%--<form:input path="supUrl" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
			<%--</li>--%>
			<%--<li><label>地址：</label>--%>
				<%--<form:input path="supAddress" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
			<%--</li>--%>
			<li><label>成立时间：</label>
				<input name="beginSetTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${supplier.beginSetTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endSetTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${supplier.endSetTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<%--<li><label>邮编：</label>--%>
				<%--<form:input path="zipCode" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
			<%--</li>--%>
			<%--<li><label>联系人：</label>--%>
				<%--<form:input path="contact" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
			<%--</li>--%>
			<%--<li><label>电子邮箱：</label>--%>
				<%--<form:input path="supEmail" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
			<%--</li>--%>
			<%--<li><label>联系电话：</label>--%>
				<%--<form:input path="supTel" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
			<%--</li>--%>
			<%--<li><label>传真号码：</label>--%>
				<%--<form:input path="faxNum" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
			<%--</li>--%>
			<%--<li><label>开户银行：</label>--%>
				<%--<form:input path="bankName" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
			<%--</li>--%>
			<%--<li><label>银行卡号：</label>--%>
				<%--<form:input path="bankNum" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
			<%--</li>--%>
			<li><label>资信等级：</label>
				<form:input path="creditRating" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>经营范围：</label>
				<form:input path="businessScope" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
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
				<th>企业性质</th>
				<th>网址</th>
				<th>地址</th>
				<th>成立时间</th>
				<th>邮编</th>
				<th>联系人</th>
				<th>电子邮箱</th>
				<th>联系电话</th>
				<th>传真号码</th>
				<th>开户银行</th>
				<th>银行卡号</th>
				<th>资信等级</th>
				<th>经营范围</th>
				<th>更新时间</th>
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
					${fns:getDictLabel(supplier.supNature, 'sup_nature', '')}
				</td>
				<td>
					${supplier.supUrl}
				</td>
				<td>
					${supplier.supAddress}
				</td>
				<td>
					<fmt:formatDate value="${supplier.setTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${supplier.zipCode}
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
					${supplier.faxNum}
				</td>
				<td>
					${supplier.bankName}
				</td>
				<td>
					${supplier.bankNum}
				</td>
				<td>
					${supplier.creditRating}
				</td>
				<td>
					${supplier.businessScope}
				</td>
				<td>
					<fmt:formatDate value="${supplier.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${supplier.remarks}
				</td>
				<shiro:hasPermission name="supplier:supplier:edit"><td>
    				<a href="${ctx}/supplier/supplier/form?id=${supplier.id}">修改</a>
					<a href="${ctx}/supplier/supplier/delete?id=${supplier.id}" onclick="return confirmx('确认要删除该供应商管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>