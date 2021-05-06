<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
<!-- css file -->
<%@include file="/WEB-INF/views/admin/include/head_inc.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

<!-- Main Header -->
<%@include file="/WEB-INF/views/admin/include/main-header.jsp" %>
	

<!-- Left side column. contains the logo and sidebar -->
<%@include file="/WEB-INF/views/admin/include/main-sidebar.jsp" %>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Page Header <small>Optional description</small>
		</h1>
		<ol class="breadcrumb">
			<li>
				<a href="#"><i class="fa fa-dashboard"></i> Level</a>
			</li>
			<li class="active">Here</li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content container-fluid">
	
	<div class="row">
		<div class="col-lg-12">
			<form id="searchForm" action="/admin/order/order_list" method="get">
				<select name="type" id="type">		<!-- pageMaker -> PageDTO, cri -> Criteria 를 참조  Criteria는 PageDTO에 포함되어있다.-->
					<option value="" <c:out value="${pageMaker.cri.type == null ? 'selected':'' }" />>--</option>
					<option value="N" <c:out value="${pageMaker.cri.type == 'N' ? 'selected':'' }" />>주문번호</option>
					<option value="D" <c:out value="${pageMaker.cri.type == 'D' ? 'selected':'' }" />>주문자</option>
					<option value="C" <c:out value="${pageMaker.cri.type == 'C' ? 'selected':'' }" />>받는분</option>
					<option value="ND" <c:out value="${pageMaker.cri.type == 'ND' ? 'selected':'' }" />>주문번호 or 주문자</option>
					<option value="NC" <c:out value="${pageMaker.cri.type == 'NC' ? 'selected':'' }" />>주문자 or 받는분</option>
					<option value="NDC" <c:out value="${pageMaker.cri.type == 'NDC' ? 'selected':'' }" />>주문번호 or 주문자 or 받는분</option>
				</select>
				<input type="text" name="keyword" value="${ pageMaker.cri.keyword}">
				<input type="hidden" name="pageNum" value="${ pageMaker.cri.pageNum}">
				<input type="hidden" name="amount" value="${ pageMaker.cri.amount}">
				<button id="btnSearch" type="button" class="btn btn-primary">검색</button>
			</form>
		</div>
	</div>

	<!-- 상품리스트 -->
	<div class="row">
	   	<div class="col-lg-12">
	   		<div class="panel panel-default">
	   			
	   			
	   			<div class="panel-body">
	   				<!-- 리스트 -->
	   				<table class="table table-striped">
						<thead>
					    	<tr>
					    		<th scope="col">선택</th>
					    		<th scope="col">번호</th>
					    		<th scope="col">주문일시</th>
					    		<th scope="col">주문번호</th>
					    		<th scope="col">주문자</th>
					    		<th scope="col">받는분</th>
					    		<th scope="col">금액</th>
					    		<th scope="col">처리상태</th>
					  		</tr>
				  		</thead>
				 		<tbody>
				 		
				 			<c:forEach items="${order_list }" var="orderVO" varStatus="status">
				    		<tr>
				      			<th scope="row">
				      				<input type="checkbox" value="${orderVO.odr_code_pk }">
				      			</th>
				      			<td>
				      			
				      				${(cri.pageNum - 1) * cri.amount + status.count}
				      			</td>
				      			<td>
				      				<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${orderVO.odr_reg }"/>
				      			</td>
				      			<td>
				      				${orderVO.odr_code_pk }
				      				<button type="button" name="btn_order_detail" data-odr_code_pk="${orderVO.odr_code_pk }" class="btn btn-link">Order Detail</button>
				      			</td>
				      			<td>
				      				${orderVO.mbme_id }
				      			</td>
				      			<td>
				      				${orderVO.odr_name }
				      			</td>
				      			<td>
				      				<fmt:formatNumber type="currency" value="${orderVO.odr_total_price }"></fmt:formatNumber>
				      			</td>
				      			<td>
				      				처리상태
				      				
				      			</td>
				    		</tr>
				   			</c:forEach>
				   		</tbody>
					</table>
	   			</div>
	   		</div>
		</div>
	</div>

    <div class="row">
    	<div class="col-lg-12">
    	<!-- 페이징 표시 -->
    			<div class="panel-footer">
    			   <ul class="pagination">
    			   <c:if test="${pageMaker.prev }">
					    <li class="page-item">
					      <a href="${pageMaker.startPage - 1 }" class="page-link" href="#" tabindex="-1">Prev</a>
					    </li>
				    </c:if>
				    <c:forEach begin="${pageMaker.startPage }" end="${ pageMaker.endPage}" var="num">
				    	<li class="page-item ${pageMaker.cri.pageNum == num ? "active" : ""}">
				    		<a href="${num }" class="page-link" href="#">${num }</a>
				    	</li>
				    </c:forEach>
				    <c:if test="${pageMaker.next }">
					    <li class="page-item">
					      <a href="${pageMaker.endPage + 1 }" class="page-link" href="#">Next</a>
					    </li>
				    </c:if>
				  </ul>
		
		    				<hr>
		    				${pageMaker }
    			</div>
    	</div>
    </div>
    
    <!-- 페이지번호 클릭시, 수정클릭시 상품코드정보추가, 삭제클릭시 상품코드정보추가 -->
    <form id="actionForm" action="/admin/order/order_list" method="get">
		<input type="hidden" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum }" />'>
		<input type="hidden" name="amount" value='<c:out value="${pageMaker.cri.amount }" />'>
		<input type="hidden" name="type" value='<c:out value="${pageMaker.cri.type }" />'>
		<input type="hidden" name="keyword" value='<c:out value="${pageMaker.cri.keyword }" />'>
	</form>

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
			<%@include file="/WEB-INF/views/admin/include/main-footer.jsp" %>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Create the tabs -->
			<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
				<li class="active">
					<a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a>
				</li>
				<li>
					<a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a>
				</li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<!-- Home tab content -->
				<div class="tab-pane active" id="control-sidebar-home-tab">
					<h3 class="control-sidebar-heading">Recent Activity</h3>
					<ul class="control-sidebar-menu">
						<li>
							<a href="javascript:;"> <i class="menu-icon fa fa-birthday-cake bg-red"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

									<p>Will be 23 on April 24th</p>
								</div>
							</a>
						</li>
					</ul>
					<!-- /.control-sidebar-menu -->

					<h3 class="control-sidebar-heading">Tasks Progress</h3>
					<ul class="control-sidebar-menu">
						<li>
							<a href="javascript:;">
								<h4 class="control-sidebar-subheading">
									Custom Template Design
									<span class="pull-right-container">
										<span class="label label-danger pull-right">70%</span>
									</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-danger" style="width: 70%"></div>
								</div>
							</a>
						</li>
					</ul>
					<!-- /.control-sidebar-menu -->

				</div>
				<!-- /.tab-pane -->
				<!-- Stats tab content -->
				<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
				<!-- /.tab-pane -->
				<!-- Settings tab content -->
				<div class="tab-pane" id="control-sidebar-settings-tab">
					<form method="post">
						<h3 class="control-sidebar-heading">General Settings</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading">
								Report panel usage
								<input type="checkbox" class="pull-right" checked>
							</label>

							<p>Some information about this general settings option</p>
						</div>
						<!-- /.form-group -->
					</form>
				</div>
				<!-- /.tab-pane -->
			</div>
		</aside>
		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
  immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED JS SCRIPTS -->

	<%@include file="/WEB-INF/views/admin/include/scripts.jsp" %>

	<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
<script>

	$(document).ready(function(){
		
		// <button type="button" name="btn_order_detail" data-odr_code_pk="${orderVO.odr_code_pk }"
		$("button[name='btn_order_detail']").on("click", function(){

			console.log("상품상세");

			// 파라미터 : 주문번호 확보
			// $(this) = 클릭한 버튼 자신을 가리킴
			var odr_code_pk = $(this).attr("data-odr_code_pk");

			console.log(odr_code_pk);

			var current_tr = $(this).parents("tr");

			$.ajax({
				url : "/admin/order/order_detail_list",
				type : "get",
				data : {
					odr_code_pk : odr_code_pk	// <input type="text" name="좌측" value="우측"
												// 좌측은 안되고 오른쪽이 변수로서 해석된다.
				},
				dataType : "json",
				success : function(data){

					// alert(data.length);
					// 함수호출 subCategoryList
					orderDetailView(data, current_tr, $("#orderDetailTemplate"));
				}
			});

			// ajax호출작업 : url? 컨트롤러 메핑주소 생성하고, Postman 테스트
					
			
		});

	});

</script>
<script>					//	orderDetailData		orderTarget tr정보가 들어와야함		orderDetailTemplate
	var orderDetailView = function(orderDetailData, orderCodeTarget, orderDetailTemplate){
		var detailTemplate = Handlebars.compile(orderDetailTemplate.html());
		var details = detailTemplate(orderDetailData);

		console.log(details);

		$(".dy_order_detail").remove(); // 상세출력된 태그 제거해주는 작업
		orderCodeTarget.after(details);
	}
</script>

<!--주문상세 데이터와 결합될 테그를 구성--> <!-- tr 행을 ajax로 추가 -->
<script id="orderDetailTemplate" type="text/x-handlebars-template">
	
	
	<tr class="dy_order_detail"><td colspan="8">주문상세내역</td></tr>
	<tr class="dy_order_detail">
		<th>선택</th><th>번호</th><th>상품명</th><th>수량</th><th>상품가격</th><th>합계</th><th colspan="2">비고</th>
	</tr>
	{{#each .}}
	<tr class="dy_order_detail">
		<td>선택</td>
		<td>번호</td>
		<td>
			<img src="/admin/order/displayFile?fileName={{gds_img}}">
			{{gds_name}}
		</td>
		<td>{{odr_amount}}</td>
		<td>{{odr_price}}</td>
		<td>{{total_price odr_price odr_amount}}</td>
		<td colspan="2">비고</td>
	</td>
	{{/each}}
</script>

<script>
	// 핸들바의 사용자정의 함수
	Handlebars.registerHelper("total_price", function(odr_price, odr_amount){
		
		return odr_price * odr_amount;
		
	});
</script>


<script>
	$(document).ready(function(){
		
		var actionForm = $("#actionForm");
		//페이지번호 클릭
		$(".page-item a").on("click", function(e){
			e.preventDefault();

			console.log("click");

			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();


		});

		//수정클릭시

		$("table td .btn-edit").on("click", function(){
			console.log('수정');

			// 상품번호값을 필드로 추가작업
			actionForm.append("<input type='hidden' name='pdt_num' value='" + $(this).attr("data-pdt_num") + "'>");
			
			actionForm.attr("action", "/admin/product/modify");
			actionForm.submit();


		});

		//삭제 클릭시
		$("table td .btn-del").on("click", function(){
			console.log('삭제');

			// 상품번호값을 필드로 추가작업
			actionForm.append("<input type='hidden' name='pdt_num' value='" + $(this).attr("data-pdt_num") + "'>");
			actionForm.append("<input type='hidden' name='pdt_img' value='" + $(this).attr("data-pdt_img") + "'>");
			
			actionForm.attr("action", "/admin/product/delete");
			actionForm.submit();


		});



		var searchForm = $("#searchForm");

		$("#searchForm #btnSearch").on("click", function(e){
			if(!searchForm.find("option:selected").val()){
				alert("검색종류를 선택하세요");
				return false;
			}

			if(!searchForm.find("input[name='keyword']").val()){
				alert("검색어를 입력하세요");
				return false;
			}

			searchForm.find("input[name='pageNum']").val("1");

			searchForm.submit();

		});
		
	});
</script>
</body>
</html>