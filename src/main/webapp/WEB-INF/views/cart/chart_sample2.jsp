<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  	<%@include file="/WEB-INF/views/common/config.jsp" %>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

    var chartData = "";

      $(document).ready(function(){

        $.ajax({
				url: "/chart/chart_example",
				type: "get",
				dataType: "text",
				success : function(chartData){

          chartData = data;

          google.charts.load("current", {packages:["corechart"]});
          google.charts.setOnLoadCallback(drawChart);


        }
			});

      });
    
      function drawChart() {
        /*
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Work',     11],
          ['Eat',      2],
          ['Commute',  2],
          ['Watch TV', 2],
          ['Sleep',    7]
        ]);
        */
        function drawChart() {
        var data = google.visualization.arrayToDataTable(chartData);

        var options = {
          title: 'My Daily Activities',
          pieHole: 0.4,
        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        chart.draw(data, options);
      }
    }
    </script>
  </head>
  <body>
    <div id="donutchart" style="width: 900px; height: 500px;"></div>
  </body>
</html>