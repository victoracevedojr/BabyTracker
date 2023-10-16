<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<%@ page import="java.time.LocalDate, java.time.Period" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>Baby Tracker: Dashboard</title>
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
					<button type="submit" class="w-28 hover:rounded-r hover:bg-amber-600 hover:ring-1 hover:ring-amber-800">Sign Out</button>
				</form>
			</div>
		</div>
		<div class="mx-auto px-20 w-11/12">
			<div class="flex justify-between my-5 items-center">
				<p class="text-4xl">Welcome, <c:out value="${user.firstName}"/>!</p>
				<a href="/babies/new" class="text-center text-xl w-24 rounded bg-blue-300 hover:text-white hover:italic hover:no-underline hover:bg-blue-400">Add Baby</a>
			</div>
			<div class="my-3">
				<table class="table-auto w-full border border-separate border-spacing-0 rounded-lg border-amber-300">
					<thead>
						<tr class="text-left text-lg bg-amber-300">
							<th scope="col" class="w-1/3 px-3 border border-amber-300">Baby Name</th>
							<th scope="col" class="w-1/3 px-3 border border-amber-300">Date of Birth (Age)</th>
							<th scope="col" class="w-1/3 px-3 rounded-tr-lg border border-amber-300">Actions</th>
						</tr>
					</thead>
					<tbody >
						<c:choose>
							<c:when test="${user.babies.size()==0}">
								<tr>
									<td colspan="4" class="h-28 text-center italic text-2xl border border-amber-300">No babies being tracked yet</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="baby" items="${user.babies}">
									<tr class="h-28">
										<td class="px-3 border border-amber-300 text-xl"><c:out value="${baby.firstName} ${baby.lastName}"/></td>
										<td class="px-3 border border-amber-300 text-xl"><c:out value="${baby.birthday.format(formatter)}  (${Period.between(baby.birthday,today).getYears()}y ${Period.between(baby.birthday,today).getMonths()}m ${Period.between(baby.birthday,today).getDays()}d) "/></td>
										<td class="px-3 border border-amber-300 text-lg flex gap-6 justify-start items-center h-28">
											<a href="/babies/${baby.id}" class="text-center hover:text-white hover:italic hover:no-underline rounded bg-amber-300 hover:bg-amber-400 w-16 ">View</a>
											<a href="/babies/${baby.id}/edit" class="text-center hover:text-white hover:italic hover:no-underline rounded bg-green-400 hover:bg-green-500 w-16">Edit</a>
											<div class="rounded bg-blue-300 flex w-52">
												<a href="/babies/${baby.id}/feed/new" class="text-center w-16 hover:rounded hover:text-white hover:italic hover:no-underline hover:bg-blue-400">Feed</a>
												<a href="/babies/${baby.id}/diaper/new" class="text-center w-36 hover:rounded hover:text-white hover:italic hover:no-underline hover:bg-blue-400 ">Change Diaper</a>
											</div>
											<form action="/babies/${baby.id}/delete" method="post">
												<input type="hidden" name="_method" value="delete" />
												<button type="submit" class="hover:italic rounded bg-red-400 hover:bg-red-500 w-20 text-white">Remove</button>
											</form>
										</td>
									</tr>
								</c:forEach>							
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>