<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@include file="/common/taglib.jsp" %>

		<c:url var="APIurl" value="/api-admin-new" />
		<c:url var="APIImageurl" value="/api-image" />
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
						<ul class="breadcrumb">
							<li><i class="ace-icon fa fa-home home-icon"></i> <a href="<c:url value ='/trang-chu'>
									</c:url>">Trang chủ</a></li>
							<li class="active">Chỉnh sửa bài viết</li>
						</ul>
						<!-- /.breadcrumb -->
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<c:if test="${not empty message}">
									<div class="alert alert-${alert}">${message}</div>
								</c:if>
								<form id="formSubmit" enctype='multipart/form-data'>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right">Thể loại </label>
										<div class="col-sm-9">
											<select class="form-control" id="categoryCode" name="categoryCode">
												<c:if test="${empty model.categoryCode }">
													<option value="">Chọn loại bài viết</option>
													<c:forEach var="item" items="${categories}">
														<option value="${item.code }">${item.name}</option>
													</c:forEach>
												</c:if>

												<c:if test="${not empty model.categoryCode }">
													<c:forEach var="item" items="${categories}">
														<option value="${item.code }" <c:if
															test="${item.code == model.categoryCode }">
															selected="selected"
												</c:if> >
												${item.name}</option>

												</c:forEach>
												<option value="">Chọn loại bài viết</option>
												</c:if>
											</select>
										</div>
									</div>
									<br /> <br /> <br />
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right">Tiêu đề</label>
										<div class="col-sm-9">
											<input type="text" class="form-control" id="title" name="title"
												value="${model.title }" />
										</div>
									</div>
									<br /> <br />
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
										<div class="col-sm-9">

											<input type="file" class="form-control" id="thumbnail" name="thumbnail" " />
											<p><span style=" color:red" id="thumbnailErorr"></span></p>
										</div>
									</div>

									<!-- <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
								<div class="col-sm-9">
									
									<input type="text" class="form-control" id="thumbnail"  name="thumbnail" />
								</div>
							</div> -->
									<br /> <br />
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
										<div class="col-sm-9">
											<input type="text" class="form-control" id="shortDescription"
												name="shortDescription" value="${model.shortDescription}" />
										</div>
									</div>
									<br /> <br />
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right">Nội dung</label>
										<div class="col-sm-9">
											<textarea rows="" cols="" id="content" name="content" "
										style=" height: 175px; width: 100%">${model.content}</textarea>

										</div>
									</div>
									<br /> <br />
									<div class="form-group">
										<div class="col-sm-9">
											<c:if test="${empty model.id }">
												<input type="button" class="btn btn-white btn-warning btn-bold"
													value="Thêm bài viết" id="btnAddOrUpdateNew" />
											</c:if>
											<c:if test="${ not empty model.id }">
												<input type="button" class="btn btn-white btn-warning btn-bold"
													value="Cập nhật bài viết" id="btnAddOrUpdateNew" />
											</c:if>

										</div>
									</div>

									<input type="hidden" value="${model.id}" id="id" name="id" />
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>

			<script type="text/javascript">
				var editor = "";
				$(document).ready(function () {
					editor = CKEDITOR.replace('content');
					//$('#thumbnail').setVal("${model.thumbnail}");
				})
				var formData = new FormData();
				$('#btnAddOrUpdateNew').click(function (e) {
					e.preventDefault();
					var logoImg = $('#thumbnail').get(0).files[0];


					var data = {};
					var formDataText = $('#formSubmit').serializeArray();
					$.each(formDataText, function (i, v) {
						data["" + v.name + ""] = v.value;
					});
					data["content"] = editor.getData();
					if(logoImg != undefined){
						var fileName = $('#thumbnail').val().split("\\").pop();
					}else{
						var fileName = "${model.thumbnail}";
					}
				
					if (isImage(fileName)) {
						data["thumbnail"] = fileName;
						formData.append('logo', logoImg);
						$('#thumbnailErorr').html("");
						$.ajax({
							url: '${APIImageurl}',
							type: "POST",
							contentType: false,
							processData: false,
							data: formData,

						});
						var id = $('#id').val();

						if (id == "") {
							addNew(data);
						} else {
							updateNew(data);
						}

					} else if(fileName == ""){
						
						$('#thumbnailErorr').html("bạn chưa chọn ảnh");

					}else{
						$('#thumbnailErorr').html("bạn nhập sai định dạng file ảnh");
					}
					
					

					
				

					//console.log(JSON.stringify(data));

					// formData.append('data',JSON.stringify(data) );

					/* var id = $('#id').val();
					console.log(formData);
					if (id == "") {
						addNew(data);
					} else {
						updateNew(data);
					} */

				});

				function addNew(data) {
					$.ajax({
						url: '${APIurl}',
						type: "POST",
						contentType: "application/json",
						// processData: false,
						data: JSON.stringify(data),
						dataType: "json",
						success: function (result) {
							window.location.href = "${Newurl}?type=edit&id=" + result.id + "&message=insert_success&alert=success";
						},
						error: function (error) {
							window.location.href = "${Newurl}?type=list&page=1&maxPageItem=2&message=error_system&alert=danger";
						},

					});
				}
				function updateNew(data) {
					$.ajax({
						url: '${APIurl}',
						type: "PUT",
						contentType: "application/json",
						data: JSON.stringify(data),
						dataType: "json",
						success: function (result) {
							window.location.href = "${Newurl}?type=edit&id=" + result.id + "&message=update_success&alert=success";
						},
						error: function (error) {
							window.location.href = "${Newurl}?type=list&page=1&maxPageItem=2&message=error_system&alert=danger";
						},

					});
				}

				function getExtension(filename) {
					var parts = filename.split('.');
					return parts[parts.length - 1];
				}

				function isImage(filename) {
					var ext = getExtension(filename);
					switch (ext.toLowerCase()) {
						case 'jpg':
						case 'gif':
						case 'bmp':
						case 'png':
							//etc
							return true;
					}
					return false;
				}
			</script>
		</body>

		</html>