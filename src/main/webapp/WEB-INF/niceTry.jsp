<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>Redirecting</title>
		<script src="https://cdn.tailwindcss.com"></script>
		<link rel="stylesheet" type="text/css" href="/css/style.css">
		<script type="text/javascript" src="/js/app.js"></script>
	</head>
	<body class="bg-amber-50">
		<div class="h-24 px-20 flex justify-between items-center bg-gradient-to-r from-amber-200 to-amber-300">
			<p class="text-5xl text-amber-700">Baby Tracker</p>
		</div>
		<div class="mx-auto text-center text-2xl">
			<p class="my-5 italic">You must be signed in to access other pages</p>
			<a href="/" class="my-5 underline text-blue-400 hover:text-blue-500 hover:italic ">Return to Sign In</a>
		</div>
	</body>
</html>