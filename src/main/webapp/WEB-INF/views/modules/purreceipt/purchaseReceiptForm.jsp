<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>进货订单管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/purreceipt/purchaseReceipt/">进货订单管理列表</a></li>
		<li class="active"><a href="${ctx}/purreceipt/purchaseReceipt/form?id=${purchaseReceipt.id}">进货订单管理<shiro:hasPermission name="purreceipt:purchaseReceipt:edit">${not empty purchaseReceipt.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="purreceipt:purchaseReceipt:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="purchaseReceipt" action="${ctx}/purreceipt/purchaseReceipt/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">供应商：</label>
			<div class="controls">
				<sys:treeselect id="supplier" name="supplier.id" value="${purchaseReceipt.supplier.id}" labelName="supplier.name" labelValue="${purchaseReceipt.supplier.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货物名称：</label>
			<div class="controls">
				<form:input path="goodsName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">进货类型：</label>
			<div class="controls">
				<form:select path="goodsType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('goods_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货物单价（元）：</label>
			<div class="controls">
				<form:input path="unitPrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">进货数量（个/斤）：</label>
			<div class="controls">
				<form:input path="goodsNum" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总价：</label>
			<div class="controls">
				<form:input path="totalPrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">进货时间：</label>
			<div class="controls">
				<input name="recDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${purchaseReceipt.recDate}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购人：</label>
			<div class="controls">
				<form:input path="purchasePerson" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付方式：</label>
			<div class="controls">
				<form:input path="payMethod" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="purreceipt:purchaseReceipt:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>