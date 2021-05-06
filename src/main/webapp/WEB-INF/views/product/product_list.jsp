<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.80.0">
    <title>포트폴리오 참고용 Bootstrap v4.6</title>
    
<%@include file="/WEB-INF/views/common/config.jsp" %>



<meta name="theme-color" content="#563d7c">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      
      #main {
         display:flex;
         flex-wrap : wrap;
         
      }
      
    </style>

  </head>
  <body>
    
<!-- nav.jsp -->
<%@include file="/WEB-INF/views/common/nav.jsp" %>

<main role="main" class=".container-fluid">
	
	<div class="row">
		<div class="col-2">
			<%@include file="/WEB-INF/views/common/category_list.jsp" %>
		</div>
		
		<div class="col-10">
			<c:if test="${empty productVOList}">
	   			<tr>
	   				<td colspan="7">
	   					<p style="color:red;">등록된 상품이 없습니다.</p>
	   				</td>
	   			</tr>
	   		</c:if>
			
			
			<div id="main" class="text-center">
				<c:forEach items="${productVOList}" var="productVO">
				
				    <div class="card shadow-sm" style="width : 33.3%">
				      	<div class="card-body">
				      		<a href="/product/product_read?gds_num_pk=${productVO.gds_num_pk }">
				        		<img src="/product/displayFile?fileName=${productVO.gds_img}" />
				        	</a>
				        	<br>
				        	<input type="hidden" name="gds_num_pk" value="${productVO.gds_num_pk }">
				        	<span><c:out value="${productVO.gds_name }" /></span><br>
				        	<span><fmt:formatNumber type="currency" value="${productVO.gds_price }"></fmt:formatNumber></span><br>
				        	<input type="number" style="width: 50px;" name="odr_amount" value="1"> 개<br>
				        	<button type="button" name="btn_direct_buy" class="btn btn-link">즉시구매</button>
				        	<button type="button" name="btn_cart_add" class="btn btn-link">장바구니</button>
				      	</div>
					</div>
				</c:forEach>
				<form id="order_direct_form" method="post" action="/order/order">
					<input type="hidden" name="type" value="1">
				</form>
			</div>
		</div>
	</div>


</main><!-- /.container -->
  
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
			
	<script id="subCGListTemplate" type="text/x-handlebars-template">
  		{{#each .}}
   			<li><a href="2차카테고리 참조하는 상품목록?cg_code={{cg_code}}">{{cg_name}}></a></li>
  		{{/each}}
	</script>
  
	<script>
	  $(document).ready(function(){
	
	    $(".mainCategory").on("click", function(){
	
	        var mainCategory = $(this);
	        var cg_code = $(this).find("a").attr("data-code");
	                  // 주소 UserProductController에서 가져옴
	        var url = "/product/subCategoryList/" + cg_code;
	
	        //alert(url);
	        // ajax요청작업 1차카테고리를 참조하는 2차카테고리 정보
	        $.getJSON(url, function(data){
	          subCGList(data, mainCategory, $("#subCGListTemplate"));
	        });
	    });



		// 즉시구매 클릭
		$("button[name='btn_direct_buy']").on("click", function(){

			console.log("즉시구매");

			// 상품코드, 구매수량
			var gds_num_pk = $(this).parent().find("input[name='gds_num_pk']").val();
			var odr_amount = $(this).parent().find("input[name='odr_amount']").val();

			console.log("상품코드: " + gds_num_pk);
			console.log("상품코드: " + odr_amount);

			

			var order_direct_form = $("#order_direct_form");
			order_direct_form.append("<input type='hidden' name='gds_num_pk' value='" + gds_num_pk + "'>");
			order_direct_form.append("<input type='hidden' name='odr_amount' value='" + odr_amount + "'>");
			//return;
			order_direct_form.submit();
			

		});
		
		// 장바구니 클릭
		$("button[name='btn_cart_add']").on("click", function(){

			console.log("장바구니 클릭");

			// 상품코드, 구매수량
			var gds_num_pk = $(this).parent().find("input[name='gds_num_pk']").val();
			var odr_amount = $(this).parent().find("input[name='odr_amount']").val();

			$.ajax({
				url : "/cart/add",		// 주소는 cartController쪽 참고
				type: "post",
				data: {gds_num_pk : gds_num_pk, gds_amount: odr_amount },
				dataType : "text",
				success: function(data){
					if(data == "SUCCESS"){
						if(confirm("장바구니가 추가되었습니다. \n 확인 하시겠습니까?")){
							location.href = "/cart/cart_list";
						}
					}else if(data == "LoginRequired"){
						alert("로그인이 안되어있습니다.");
						location.href = "/member/login";
					}
				}
			});
			

		});


	  });
	
	  var subCGList = function(subCGData, targetSubCategory, templateObject) {
	
	    var template = Handlebars.compile(templateObject.html());
	    var subCGLi = template(subCGData);
	
	    $(".mainCategory .subCategory").empty(); //remove는 자신까지 지워버리니 안됨.
	                                              //empty는 자기 아래 하위수준만 지움
	
	    targetSubCategory.find(".subCategory").append(subCGLi);
	
	  }
	</script>
	
  </body>
  
</html>
