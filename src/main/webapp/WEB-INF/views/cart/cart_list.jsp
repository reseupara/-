<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
	   		<div class="panel panel-default">
	   			<div class="panel-heading text-right">
	   				<button id="btn_cart_check_del" type="button" class="btn btn-primary pull-right">선택삭제</button>
	   			</div>
	   			
	   			<div class="panel-body">
	   				<!-- 리스트 -->
	   				<table class="table table-striped">
						<thead>
					    	<tr>
					    		<th scope="col"><input type="checkbox" id="check_all"></th> <!--선택하면 모두 선택될 수 있도록 id-->
					    		<th scope="col">번호</th>
					    		<th scope="col">사진</th>
					    		<th scope="col">상품명</th>
					    		<th scope="col">수량</th>
					    		<th scope="col">금액</th>
					    		<th scope="col">배송비</th>
					    		<th scope="col">취소</th>
					  		</tr>
				  		</thead>
				 		<tbody>
				 		<%--데이터가 존재하지 않는 경우 --%>
				 			<c:if test="${empty cartVOList }">
				    			<tr>
				    				<td colspan="7">
				    					<p style="color:red;">장바구니가 비어있습니다.</p>
				    				</td>
				    			</tr>
				    		</c:if>
				    		
				    		<c:set var="i" value="1"></c:set>
				    		<c:set var="price" value="0"></c:set>
				    		<c:forEach items="${cartVOList }" var="cartList">
				    			<c:set var="price" value="${ cartList.gds_price * cartList.cart_amount}"></c:set>
				    			<tr>
				    				<td>											<%-- value에 장바구니 코드값을 숨겨놓는다 --%>
				    					<input type="checkbox" name="cart_check" value="${cartList.cart_code_pk }"> 
				    				</td>
				    				<th scope="row">${i }</th>
				    				<td>
				      					<img src="/cart/displayFile?fileName=${cartList.gds_img}">
				      				</td>
				      				<td>
				      					<c:out value="${cartList.gds_name }"></c:out>
				      				</td>
				      				<td>
				      					<input type="number" value="<c:out value="${cartList.cart_amount }"></c:out>" name="cart_amount">
				      					<button type="button" name="btnCartEdit" data-cart_code_pk="${cartList.cart_code_pk }" class="btn btn-link">Edit</button>
				      				</td>
			      					<td data-price="${price}">
										<fmt:formatNumber type="currency" value="${price}"></fmt:formatNumber>
			      					</td>
				      				<td>[기본배송조건]</td>
				      				<td>
				      					<button type="button" name="btnCartDel" data-cart_code_pk="${cartList.cart_code_pk }" class="btn btn-link">Delete</button>
				      				</td>
				      			</tr>
				      			<c:set var="i" value="${1+1 }"></c:set>
				      			<c:set var="sum" value="${sum + price }"></c:set>
				      		</c:forEach>
				      	</tbody>
					</table>
				</div>
				<div id="sum_price" class="panel-body">
					<table class="table table-striped">
						<tr>
				      		<td>총 금액</td>
				      		<td data-sum="${sum}"><fmt:formatNumber type="currency" value="${sum}"></fmt:formatNumber></td>
				      	</tr>
					</table>
				</div>
				<div id="sum_price" class="panel-footer">
					<table class="table table-striped">
						<tr>
							<td>
								<button name="btn_cart_clear" type="button" class="btn btn-link">장바구니 비우기</button>
								<button name="btn_order" type="button" class="btn btn-link">전체상품 주문</button>
								<button name="btn_chk_order" type="button" class="btn btn-link">선택상품 주문</button>
							</td>
						</tr>	
					</table>
				</div>
			</div>
		</div>
	</div>

</main><!-- /.container -->

<script>

	$(document).ready(function(){
		
		// 작업1 장바구니폼 개별작업
		$("button[name='btnCartDel']").on("click", function(){
			// console.log("장바구니 삭제");
			
			var cart_code_pk = $(this).attr("data-cart_code_pk");
			
			var cart_tr = $(this).parents("tr");
									//find를 써서 자식들 중에 찾음
			var cart_price = cart_tr.find("td[data-price]").attr("data-price");

			console.log(cart_price);

			var sum_price = $("div#sum_pirce td[data-sum]").attr("data-sum");

			console.log(sum_price);

			$.ajax({
            url: '/cart/delete',
            type: 'post',
            dataType: 'text',
            data: {cart_code_pk : cart_code_pk},
            success : function(data){
                if(data== 'SUCCESS'){
                    
                    alert("장바구니 상품:" + cart_code_pk + "삭제되었습니다.");
					// location.href = "/cart/cart_list";

                  //행을 화면에서 제거
                    cart_tr.remove();

					//합계 계산
					// 삭제했을 때 총 금액 - 삭제한 데이터 금액 하는

					sum_price = parseInt(sum_price) - parseInt(cart_price);
					$("div#sum_pirce td[data-sum]").attr("data-sum", sum_price); // 삭제시 값 변경이 지속적으로 처리되어야한다.
					$("div#sum_pirce td[data-sum]").text(sum_price);
					
					$('#sum_price').load(location.fref + '#sum_price');

					location.href = "/cart/cart_list";
					
                }
            }
		});
		});
	


		// 수량텍스트박스 변경이벤트 작업
		$("inputp[name='cart_amount']").on("change", function(){
			
			console.log("수량변경");
		});

			//	작업2> 수량변경수정버튼 클릭시
			//	<button type="button" name="btnCartEdit"

		$("button[name='btnCartEdit']").on("click", function(){

			console.log("수량버튼변경");
			
			// 주요 파라미터 : 장바구니코드, 변경된 수량
			var cart_code_pk = $(this).attr("data-cart_code_pk");
			var cart_amount = $(this).parent().find("input[name='cart_amount']").val();
			//var qty = $(this).siblings("input[name='cart_amount']").val(); //cart_tr.find("input[name='cart_amount']").val();

			console.log(cart_code_pk);
			console.log(cart_amount);
			var cart_tr = $(this).parents("tr");

			$.ajax({
            url: '/cart/cart_amount_update',
            type: 'post',
			data: {cart_code_pk : cart_code_pk, cart_amount : cart_amount},
            dataType: 'text',
            success : function(data){
                if(data == 'SUCCESS'){
					alert("수량이 변경되었습니다");
					location.href = "/cart/cart_list";

					
				}
			}

		});

	});


		// 작업3> 장바구니 비우기 처리
		
		$("button[name='btn_cart_clear']").on("click", function(){
			
			location.href = "/cart/delete_all";
		});
		
		
		
		
		
		//주문하기 이동 .  <button name="btn_order"
		$("button[name='btn_order']").on("click", function(){
			
			location.href = "/order/order?type=2";

		});
		
		// <button name="btn_chk_order" type="button" class="btn btn-link">선택상품 주문</button>
		// 장바구니 선택구매
		$("button[name='btn_chk_order']").on("click", function(){

			if($("input[name='cart_check']:checked").length == 0){
				alert("구매할 상품을 선택하세요.");
				return;
			}

			var result = confirm("선택한 상품을 구매하시겠습니까?");

			if(result){
				var checkArr = [];

				$("input[name='cart_check']:checked").each(function(){
					var cart_code_pk = $(this).val();
					checkArr.push(cart_code_pk); // 배열의 개수만큼 데이터가 들어오게 된다.
				});

				$.ajax({
				url: "/cart/cart_check_order",
				type: "post",
				data: {checkArr : checkArr},
				dataType: "text",
				success : function(data){
					if(data == "SUCCESS"){
						
						location.href = "/order/order?type=2";

						
					}
				}

			});
			}
		});
		
		


		//<input type="checkbox" name="cart_check" value="${cartList.cart_code_pk }"> 
		//
		// 장바구니 선택삭제
		$("#btn_cart_check_del").on("click", function(){

			if($("input[name='cart_check']:checked").length == 0){
				alert("삭제할 상품을 선택하세요.");
				return;
			}

			var result = confirm("선택한 상품을 삭제하시겠습니까?");

			if(result){
				var checkArr = [];

				$("input[name='cart_check']:checked").each(function(){
					var cart_code = $(this).val();
					checkArr.push(cart_code); // 배열의 개수만큼 데이터가 들어오게 된다.
				});

				$.ajax({
				url: "/cart/cart_check_delete",
				type: "post",
				data: {checkArr : checkArr},
				dataType: "text",
				success : function(data){
					if(data == "SUCCESS"){
						alert("선택한 상품이 삭제되었습니다.");
						location.href = "/cart/cart_list";

						
					}
				}

			});
			}
		});

		// 제목행의 체크박스 선택시 전체선택기능
		$("#check_all").on("click", function(){
			$("input[name='cart_check']").prop("checked", this.checked);
		});

		// 데이터 행의 체크박스 클릭
		$("input[name='cart_check']").on("click", function(){
			//$("#check_all").prop("checked", false);
			if($("input[name='cart_check']:checked").length == $("input[name='cart_check']").length){
				$("#check_all").prop("checked", true);
			}else {
				$("#check_all").prop("checked", false);
			}
		});

	});
</script>
	</body>
</html>
