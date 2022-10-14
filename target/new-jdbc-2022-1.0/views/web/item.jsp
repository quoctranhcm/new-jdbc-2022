<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-4">
            	<h5>${model.categoryCode}</h5>
            	<p>${model.createdDate}</p>
                ${model.content}
            </div>
        </section>
        <!-- Related items section-->
        <section class="py-5 bg-light">
            <div class="container px-4 px-lg-5 mt-5">
                <h2 class="fw-bolder mb-4">Bài viết liên quan</h2>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                  <c:forEach var ="item" items="${model.listResult}">
                  		<c:if test="${model.categoryId == item.categoryId}">
	                  		<div class="col mb-5">
		                        <div class="card h-100">
		                         	<a style="text-decoration: none; color: inherit;" href='<c:url value ="/trang-chu?idNew=${item.id}"></c:url>'>
			                            <!-- Product image-->
			                            <img class="card-img-top" src='<c:url value="/uploadFile/${item.thumbnail}"></c:url>' alt="..." />
			                            <!-- Product details-->
			                            <div class="card-body p-4">
			                                <div class="text-center">
			                                 
			                                    <h5 class="fw-bolder">${item.title }</h5>
			                                   
			                                </div>
			                            </div>
		                            </a>
		                        </div>
	                    	</div>
                  		</c:if>
                    
                    
                  </c:forEach>
                    
                    
                </div>
            </div>
        </section>
</body>
</html>