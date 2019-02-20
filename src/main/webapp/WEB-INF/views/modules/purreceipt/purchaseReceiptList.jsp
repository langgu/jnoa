<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>进货订单管理管理</title>
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
		<li class="active"><a href="${ctx}/purreceipt/purchaseReceipt/">进货订单管理列表</a></li>
		<shiro:hasPermission name="purreceipt:purchaseReceipt:edit"><li><a href="${ctx}/purreceipt/purchaseReceipt/form">进货订单管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="purchaseReceipt" action="${ctx}/purreceipt/purchaseReceipt/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>供应商：</label>
				<form:input path="supplier" htmlEscape="false" maxlength="2000" class="input-medium"/>
			</li>
			<li><label>货物名称：</label>
				<form:input path="goodsName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>进货类型：</label>
				<form:select path="goodsType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('goods_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>货物单价（元）：</label>
				<form:input path="unitPrice" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>进货数量（个/斤）：</label>
				<form:input path="goodsNum" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>总价：</label>
				<form:input path="totalPrice" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>进货时间：</label>
				<input name="beginRecDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${purchaseReceipt.beginRecDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endRecDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${purchaseReceipt.endRecDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>采购人：</label>
				<form:input path="purchasePerson" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>支付方式：</label>
				<form:select path="payMethod" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pay_method')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>供应商</th>
				<th>货物名称</th>
				<th>进货类型</th>
				<th>货物单价（元）</th>
				<th>进货数量（个/斤）</th>
				<th>总价</th>
				<th>进货时间</th>
				<th>采购人</th>
				<th>支付方式</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="purreceipt:purchaseReceipt:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="purchaseReceipt">
			<tr>
				<td><a href="${ctx}/purreceipt/purchaseReceipt/form?id=${purchaseReceipt.id}">
					${purchaseReceipt.supplier}
				</a></td>
				<td>
					${purchaseReceipt.goodsName}
				</td>
				<td>
					${fns:getDictLabel(purchaseReceipt.goodsType, 'goods_type', '')}
				</td>
				<td>
					${purchaseReceipt.unitPrice}
				</td>
				<td>
					${purchaseReceipt.goodsNum}
				</td>
				<td>
					${purchaseReceipt.totalPrice}
				</td>
				<td>
					<fmt:formatDate value="${purchaseReceipt.recDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${purchaseReceipt.purchasePerson}
				</td>
				<td>
					${fns:getDictLabel(purchaseReceipt.payMethod, 'pay_method', '')}
				</td>
				<td>
					<fmt:formatDate value="${purchaseReceipt.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${purchaseReceipt.remarks}
				</td>
				<shiro:hasPermission name="purreceipt:purchaseReceipt:edit"><td>
    				<a href="${ctx}/purreceipt/purchaseReceipt/form?id=${purchaseReceipt.id}">修改</a>
					<a href="${ctx}/purreceipt/purchaseReceipt/delete?id=${purchaseReceipt.id}" onclick="return confirmx('确认要删除该进货订单管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>