<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="<c:url value='/assets/bootstrap/css/bootstrap.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/bootstrap/css/bootstrap-responsive.css'/>">
		<link rel="stylesheet" href="<c:url value='/assets/css/main.css'/>">
		<script src="<c:url value="/assets/js/jquery-2.0.3.js"/>" type="text/javascript" charset="utf-8"></script>
		<script src="<c:url value="/assets/bootstrap/js/bootstrap.js"/>" type="text/javascript" charset="utf-8"></script>
		<script src="<c:url value="/assets/Highcharts-3.0.9/js/highcharts.js"/>" charset="utf-8"></script>
		<script src="<c:url value="/assets/js/highchart-theme.js"/>" type="text/javascript" charset="utf-8"></script>
		<title>Vote no Filme</title>
	</head>
	<body>
		<div id="div_top">
			<header class="navbar navbar-inverse navbar-fixed-top">
				<div class="navbar-inner">
					<div class="container">
						<c:import url="menu.jsp" />
					</div>
				</div>
			</header>
		</div>
		<div id="div_content" class="container">
			<c:import url="films.jsp"/>
		</div>
</body>
</html>