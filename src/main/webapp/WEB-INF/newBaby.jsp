<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>Baby Tracker: Add a Baby</title>
		<script src="https://cdn.tailwindcss.com"></script>
		<link rel="stylesheet" type="text/css" href="/css/style.css">
		<script type="text/javascript" src="/js/app.js"></script>
	</head>
	<body class="bg-amber-50">
		<div class="h-24 px-20 flex justify-between items-center bg-gradient-to-r from-amber-200 to-amber-300">
			<p class="text-5xl text-amber-600">Baby Tracker</p>
			<div class="w-96 text-lg flex gap-10 rounded bg-amber-500">
				<a href="/home" class="text-center w-24 hover:rounded-l hover:bg-amber-600 hover:ring-1 hover:ring-amber-800">Home</a>
				<a href="/babies/resources" class="text-center w-24 hover:bg-amber-600 hover:ring-1 hover:ring-amber-800">Resources</a>
				<form action="/logout" method="post">
					<input type="submit" value="Sign Out" class="w-28 hover:rounded-r hover:bg-amber-600 hover:ring-1 hover:ring-amber-800" />
				</form>
			</div>
		</div>
		<div class="mx-auto px-20">
			<div class="flex justify-between my-5 w-1/2 mx-auto items-center">
				<p class="text-4xl text-center">Add a Baby</p>
				<a href="/home" class=" text-center hover:italic rounded bg-slate-300 hover:bg-slate-400 w-24 text-lg">Cancel</a>
			</div>
			<div class="my-3">
				<table class="table-auto w-1/2 my-3 mx-auto text-2xl border border-2 border-separate border-spacing-0 border-amber-400 rounded-lg">
					<form:form action="/babies/new" method="post" modelAttribute="baby" class="form" enctype="multipart/form-data">
						<tbody>
							<tr class="h-16">
								<td class="p-5">
									<form:label path="firstName">First Name:</form:label><br/> 
									<form:errors path="firstName" class="italic text-red-400 text-sm"/>
								</td>
								<td class="p-5">
									<form:input path="firstName" class="shadow-inner rounded w-full border border-amber-200 p-1 focus:shadow-lg focus:shadow-amber-300" placeholder="Please enter a first name"/>
								</td>
							</tr>
							<tr class="h-16">
								<td class="p-5">
									<form:label path="lastName">Last Name:</form:label><br/> 
									<form:errors path="lastName" class="italic text-red-400 text-sm"/>
								</td>
								<td class="p-5">
									<form:input path="lastName" class="shadow-inner rounded w-full border border-amber-200 p-1 focus:shadow-lg focus:shadow-amber-300" placeholder="Please enter a last name"/>
								</td>
							</tr>
							<tr class="h-16">
								<td class="p-5">
									<form:label path="gender">Gender:</form:label><br/> 
									<form:errors path="gender" class="italic text-red-400 text-sm"/>
								</td>
								<td class="p-5">
									<div>
										<form:radiobutton path="gender" value="Male"/>
										<form:label path="gender">Male</form:label>
									</div>
									<div>
										<form:radiobutton path="gender" value="Female"/>
										<form:label path="gender">Female</form:label>
									</div>
								</td>
							</tr>
							<tr class="h-16">
								<td class="p-5">
									<form:label path="birthday">Date of Birth:</form:label><br/> 
									<form:errors path="birthday" class="italic text-red-400 text-sm"/>
								</td>
								<td class="p-5">
									<form:input path="birthday" type="date" class="shadow-inner rounded w-full border border-amber-200 p-1 focus:shadow-lg focus:shadow-amber-300" value="${today}"/>
								</td>
							</tr>
<%-- 							<tr class="h-16">
								<td class="p-5">
									<label for="photo">Photo (Optional):</label><br/> 
									<form:errors path="photo" class="italic text-red-400 text-sm"/>
								</td>
								<td class="p-5">
									<input name="photo" type="file" class="shadow-inner rounded w-full border border-amber-200 p-1 focus:shadow-lg focus:shadow-amber-300"/>
								</td>
							</tr> --%>
							<tr class="h-16">
								<td colspan=2 class="p-5"> <input type="submit" value="Submit" class="rounded bg-blue-300 hover:text-white hover:italic hover:bg-blue-400 w-full"/></td>
							</tr>
						</tbody>
						<!-- Hidden input to attach baby to current user in session -->
						<form:input type="hidden" path="user" value="${user.id}"/>
					</form:form>
				</table>			
			</div>
		</div>
	</body>
</html>