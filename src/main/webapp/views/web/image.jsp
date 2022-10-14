<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIur" value="/api-image" />
<c:url var="NewURL" value="/admin-new" />
<html>
<head>
<title>Chỉnh sửa bài viết</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>
				
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						
						<form id="imagesubmit" enctype='multipart/form-data'>
						
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
								<div class="col-sm-9">
									
									<input type="file" class="form-control" id="thumbnail"  name="thumbnail" />
								</div>
							</div>		
							<br /> <br />
							<div class="form-group">
								<div class="col-sm-9">
										<input type="button" class="btn btn-white btn-warning btn-bold"
											value="upload" id="btnUpload" />
										
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
			

		$('#btnUpload').click(function (e) {
			e.preventDefault();
			var form = $('#imagesubmit')[0];
	        var data = new FormData(form);

			/* var data = {};
			var formData = $('#formSubmit').serializeArray();
			$.each(formData, function (i, v) {
					data[""+v.name+""] = v.value;
			}); */
			
	        $.ajax({
				url: '${APIur}',
				type: "POST",
				contentType: false,
				processData : false,
				cache : false,
				enctype: 'multipart/form-data',
				data: data,
			/* 	dataType: "json", */
				success: function(result){
					
				},
				error: function(error){
					
				},

			});
			
		});
		
		
		
	</script>
</body>
</html>
