<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light  fixed-top" >
	<div class="container px-4 px-lg-5 ">
		<a class="navbar-brand" href='<c:url value ="/trang-chu"></c:url>'>Tuổi trẻ</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse  " id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4 ">
				<li class="nav-item">
					<a class="nav-link active" aria-current="page" href='<c:url value ="/trang-chu?categoryCode=thoi-su"></c:url>'>Thời sự</a>
				</li>
				
				<li class="nav-item">
					<a class="nav-link active" aria-current="page" href='<c:url value ="/trang-chu?categoryCode=the-gioi"></c:url>'>Thế giới</a>
				</li>
				<li class="nav-item">
					<a class="nav-link active" aria-current="page" href='<c:url value ="/trang-chu?categoryCode=chinh-tri"></c:url>'>Chính trị</a>
				</li>
				<li class="nav-item">
					<a class="nav-link active" aria-current="page" href='<c:url value ="/trang-chu?categoryCode=the-thao"></c:url>'>Thể Thao</a>
				</li>
				<li class="nav-item">
					<a class="nav-link active" aria-current="page" href='<c:url value ="/trang-chu?categoryCode=goc-nhin"></c:url>'>Góc nhìn</a>
				</li>

				<!-- <li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="#!">All Products</a></li>
						<li><hr class="dropdown-divider" /></li>
						<li><a class="dropdown-item" href="#!">Popular Items</a></li>
						<li><a class="dropdown-item" href="#!">New Arrivals</a></li>
					</ul></li> -->
			</ul>
			<div class="d-flex">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4 ">
					<c:if test="${ not empty USERMODEL }">
							<c:if test="${USERMODEL.role.getCode().equals('ADMIN')}">
								<li class="nav-item"><a class="nav-link active"
							aria-current="page" href='<c:url value ="/admin-home"></c:url>'>Admin-home</a></li>
							</c:if>
					</c:if>
					
					
					<c:if test="${not empty USERMODEL }">
		
						<li class="nav-item"><a class="nav-link" href='#'>Welcome,
								${USERMODEL.fullName}</a></li>
						<li class="nav-item"><a class="nav-link"
							href='<c:url value ="/thoat?action=logout"></c:url>'>Thoát</a></li>
					</c:if>
					
					<c:if test="${empty USERMODEL }">
						<li class="nav-item"><a class="nav-link"
							href='<c:url value ="/dang-nhap?action=login"></c:url>'>Đăng
								nhập</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</nav>