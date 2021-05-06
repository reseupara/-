<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<aside class="main-sidebar">
<!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
	
	<!-- /.search form -->
	
	<!-- Sidebar Menu -->
	<ul class="sidebar-menu" data-widget="tree">
		<li class="header">HEADER</li>
		<!-- Optionally, you can add icons to the links -->
		
		<li class="treeview">
			<a href="#"><i class="fa fa-link"></i>
				<span>상품관리</span>
				<span class="pull-right-container">
					<i class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
			<ul class="treeview-menu">
			<!-- ${pageContext.request.contextPath } <- 서버이름이 뭐더라도 상관 없다. 그대로 읽어옴. 없으면 / 여야 함. -->
				<li>
					<a href="${pageContext.request.contextPath }/admin/product/product_insert">상품등록</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath }/admin/product/pro_list">상품목록</a>
				</li>
			</ul>
		</li>
		<li class="treeview">
			<a href="#"><i class="fa fa-link"></i> <span>주문관리</span>
				<span class="pull-right-container">
					<i class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
			<ul class="treeview-menu">
				<li><a href="${pageContext.request.contextPath }/admin/order/order_list">주문목록</a></li>
			</ul>
		</li>
		<li class="treeview">
			<a href="#"><i class="fa fa-link"></i> <span>회원관리</span>
				<span class="pull-right-container">
					<i class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
			<ul class="treeview-menu">
				<li><a href="#">Link in level 2</a></li>
				<li><a href="#">Link in level 2</a></li>
			</ul>
		</li>
		<li class="treeview">
  			<a href="#"><i class="fa fa-link"></i> <span>통계/매출현황관리</span>
				<span class="pull-right-container">
    				<i class="fa fa-angle-left pull-right"></i>
   				</span>
			</a>
			<ul class="treeview-menu">
 					<li><a href="${pageContext.request.contextPath }/admin/order/order_sale">매출통계</a></li>
 					<li><a href="#">Link in level 2</a></li>
			</ul>
		</li>
	</ul>
<!-- /.sidebar-menu -->
</section>
<!-- /.sidebar -->
</aside>