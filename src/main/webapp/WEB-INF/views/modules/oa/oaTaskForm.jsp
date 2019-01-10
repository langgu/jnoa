<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务信息管理</title>
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
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/oaTask/">任务信息列表</a></li>
		<li class="active"><a href="${ctx}/oa/oaTask/form?id=${oaTask.id}">任务信息<shiro:hasPermission name="oa:oaTask:edit">${not empty oaTask.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:oaTask:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaTask" action="${ctx}/oa/oaTask/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">任务标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件：</label>
			<div class="controls">
				<form:input path="files" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否是转发：</label>
			<div class="controls">
				<form:input path="forwardFlag" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务完成标记：</label>
			<div class="controls">
				<form:input path="completeFlag" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">任务下发任务：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>任务标题</th>
								<th>任务内容</th>
								<th>附件</th>
								<th>发送人</th>
								<th>接受人</th>
								<th>发送时间</th>
								<th>阅读标记</th>
								<th>阅读时间</th>
								<th>是否是转发</th>
								<th>备注信息</th>
								<th>任务完成标记</th>
								<shiro:hasPermission name="oa:oaTask:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="oaTaskRecordList">
						</tbody>
						<shiro:hasPermission name="oa:oaTask:edit"><tfoot>
							<tr><td colspan="13"><a href="javascript:" onclick="addRow('#oaTaskRecordList', oaTaskRecordRowIdx, oaTaskRecordTpl);oaTaskRecordRowIdx = oaTaskRecordRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="oaTaskRecordTpl">//<!--
						<tr id="oaTaskRecordList{{idx}}">
							<td class="hide">
								<input id="oaTaskRecordList{{idx}}_id" name="oaTaskRecordList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="oaTaskRecordList{{idx}}_delFlag" name="oaTaskRecordList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="oaTaskRecordList{{idx}}_title" name="oaTaskRecordList[{{idx}}].title" type="text" value="{{row.title}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<textarea id="oaTaskRecordList{{idx}}_content" name="oaTaskRecordList[{{idx}}].content" rows="4" maxlength="2000" class="input-small ">{{row.content}}</textarea>
							</td>
							<td>
								<input id="oaTaskRecordList{{idx}}_files" name="oaTaskRecordList[{{idx}}].files" type="text" value="{{row.files}}" maxlength="2000" class="input-small "/>
							</td>
							<td>
								<input id="oaTaskRecordList{{idx}}_sendUserId" name="oaTaskRecordList[{{idx}}].sendUserId" type="text" value="{{row.sendUserId}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="oaTaskRecordList{{idx}}_receUserId" name="oaTaskRecordList[{{idx}}].receUserId" type="text" value="{{row.receUserId}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="oaTaskRecordList{{idx}}_sendDate" name="oaTaskRecordList[{{idx}}].sendDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.sendDate}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<td>
								<input id="oaTaskRecordList{{idx}}_readFlag" name="oaTaskRecordList[{{idx}}].readFlag" type="text" value="{{row.readFlag}}" maxlength="1" class="input-small "/>
							</td>
							<td>
								<input id="oaTaskRecordList{{idx}}_readDate" name="oaTaskRecordList[{{idx}}].readDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.readDate}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<td>
								<input id="oaTaskRecordList{{idx}}_forwardFlag" name="oaTaskRecordList[{{idx}}].forwardFlag" type="text" value="{{row.forwardFlag}}" maxlength="1" class="input-small "/>
							</td>
							<td>
								<textarea id="oaTaskRecordList{{idx}}_remarks" name="oaTaskRecordList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<td>
								<input id="oaTaskRecordList{{idx}}_completeFlag" name="oaTaskRecordList[{{idx}}].completeFlag" type="text" value="{{row.completeFlag}}" maxlength="1" class="input-small "/>
							</td>
							<shiro:hasPermission name="oa:oaTask:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#oaTaskRecordList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var oaTaskRecordRowIdx = 0, oaTaskRecordTpl = $("#oaTaskRecordTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(oaTask.oaTaskRecordList)};
							for (var i=0; i<data.length; i++){
								addRow('#oaTaskRecordList', oaTaskRecordRowIdx, oaTaskRecordTpl, data[i]);
								oaTaskRecordRowIdx = oaTaskRecordRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="oa:oaTask:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>