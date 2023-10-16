<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>Baby Tracker</title>
		<script src="https://cdn.tailwindcss.com"></script>
		<link rel="stylesheet" type="text/css" href="/css/style.css">
		<script type="text/javascript" src="/js/app.js"></script>
	</head>
	<body class="text-2xl bg-gradient-to-r from-amber-50 to-amber-100">
		<div class="h-24 px-20 flex items-center bg-gradient-to-r from-amber-200 to-amber-300">	
			<p class="my-5 text-5xl text-amber-600">Baby Tracker</p>
		</div>
		<div class="mx-auto my-5 px-20">
			<p class="italic text-4xl text-amber-500">Nurturing Every Moment, One Tap at a Time!</p>
			<div class="flex justify-between gap-20 my-5">
				<div>
					<table class="table-fixed w-full border border-separate border-4 border-spacing-1 rounded-lg border-slate-300">
						<thead>
							<tr class="bg-green-400">
								<th scope=col colspan=2 class="text-3xl text-center px-3 rounded-t-lg border border-slate-300">Register</th>
							</tr>
						</thead>
						<form:form action="/register" method="post" modelAttribute="newUser" class="form">
							<tbody class="table-group-divider">
								<tr>
									<td class="col-5 px-3 border rounded-lg border-slate-300">
										<form:label path="firstName">First Name:</form:label><br/> 
										<form:errors path="firstName" class="italic text-red-400 text-sm"/>
									</td>
									<td>
										<form:input path="firstName" class="shadow-inner rounded-lg w-full border border-slate-200 p-1 focus:shadow-lg focus:shadow-amber-300"/>
									</td>
								</tr>
								<tr>
									<td class="col-5 px-3 border rounded-lg border-slate-300">
										<form:label path="lastName">Last Name:</form:label><br/> 
										<form:errors path="lastName" class="italic text-red-400 text-sm"/>
									</td>
									<td>
										<form:input path="lastName" class="shadow-inner rounded-lg w-full border border-slate-200 p-1 focus:shadow-lg focus:shadow-amber-300"/>
									</td>
								</tr>
								<tr>
									<td class="col-5 px-3 border rounded-lg border-slate-300">
										<form:label path="email">Email:</form:label><br/> 
										<form:errors path="email" class="italic text-red-400 text-sm"/>
									</td>
									<td>
										<form:input path="email" class="shadow-inner rounded-lg w-full border border-slate-200 p-1 focus:shadow-lg focus:shadow-amber-300"/>
									</td>
								</tr>
								<tr>
									<td class="col-5 px-3 border rounded-lg border-slate-300">
										<form:label path="password">Password:</form:label><br/> 
										<form:errors path="password" class="italic text-red-400 text-sm"/>
									</td>
									<td>
										<form:input path="password" type="password" class="shadow-inner rounded-lg w-full border border-slate-200 p-1 focus:shadow-lg focus:shadow-amber-300"/>
									</td>
								</tr>
								<tr>
									<td class="col-5 px-3 border rounded-lg border-slate-300">
										<form:label path="confirm">Confirm Password:</form:label><br/> 
										<form:errors path="confirm" class="italic text-red-400 text-sm"/>
									</td>
									<td>
										<form:input path="confirm" type="password" class="shadow-inner rounded-lg w-full border border-slate-200 p-1 focus:shadow-lg focus:shadow-amber-300"/>
									</td>
								</tr>
								<tr>
									<td colspan=2> <input type="submit" value="Register" class="text-center hover:text-white hover:italic hover:no-underline rounded-b bg-green-400 hover:bg-green-500 w-full" /></td>
								</tr>
							</tbody>
						</form:form>
					</table>
				</div>
				<div>
					<table class="table-fixed w-full border border-separate border-4 border-spacing-1 rounded-lg border-slate-300">
						<thead>
							<tr class="bg-blue-300">
								<th scope=col colspan=2 class="text-3xl text-center px-3 rounded-t-lg border border-slate-300">Sign In</th>
							</tr>
						</thead>
						<form:form action="/login" method="post" modelAttribute="newLogin" class="form">
							<tbody class="table-group-divider">
								<tr>
									<td class="col-5 px-3 border rounded-lg border-slate-300">
										<form:label path="email">Email:</form:label><br/> 
										<form:errors path="email" class="italic text-red-400 text-sm"/>
									</td>
									<td>
										<form:input path="email" class="shadow-inner rounded-lg w-full border border-slate-200 p-1 focus:shadow-lg focus:shadow-amber-300"/>
									</td>
								</tr>
								<tr>
									<td class="col-5 px-3 border rounded-lg border-slate-300">
										<form:label path="password">Password:</form:label><br/> 
										<form:errors path="password" class="italic text-red-400 text-sm"/>
									</td>
									<td>
										<form:input path="password" type="password" class="shadow-inner rounded-lg w-full border border-slate-200 p-1 focus:shadow-lg focus:shadow-amber-300"/>
									</td>
								</tr>
								<tr>
									<td colspan=2> <input type="submit" value="Sign In" class="text-center hover:text-white hover:italic hover:no-underline rounded-b bg-blue-300 hover:bg-blue-400 w-full" /></td>
								</tr>
							</tbody>
						</form:form>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>