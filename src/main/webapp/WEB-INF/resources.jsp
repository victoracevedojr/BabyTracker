<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>Baby Tracker: Resources</title>
		<script src="https://cdn.tailwindcss.com"></script>
		<link rel="stylesheet" type="text/css" href="/css/style.css">
		<script type="text/javascript" src="/js/app.js"></script>
	</head>
	<body class="bg-amber-100">
		<div class="h-24 px-20 flex justify-between items-center bg-gradient-to-r from-amber-200 to-amber-300">
			<p class="text-5xl text-amber-600">Baby Tracker</p>
			<div class="w-96 text-lg flex gap-10 rounded bg-amber-500">
				<a href="/home" class="text-center w-24 hover:rounded-l hover:bg-amber-600 hover:ring-1 hover:ring-amber-800">Home</a>
				<a href="/babies/resources" class="text-center w-24 hover:bg-amber-600 hover:ring-1 hover:ring-amber-800">Resources</a>
				<form action="/logout" method="post">
					<button type="submit" class="w-28 hover:rounded-r hover:bg-amber-600 hover:ring-1 hover:ring-amber-800">Sign Out</button>
				</form>
			</div>
		</div>
		<div class="mx-auto text-center text-2xl">
			<p class="my-5 italic">Resources coming soon...</p>
		</div>
	</body>
</html>