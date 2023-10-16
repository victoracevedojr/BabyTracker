<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>Insert title here</title>
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
				<p class="text-4xl text-center">Edit Diaper Change</p>
				<a href="/babies/${baby.id}" class="text-center hover:italic rounded bg-slate-300 hover:bg-slate-400 w-24 text-lg">Cancel</a>
			</div>
			<div class="my-3">
				<table class="table-auto w-1/2 my-3 mx-auto text-2xl border border-2 border-separate border-spacing-0 border-amber-400 rounded-lg">
					<form:form action="/babies/${baby.id}/diaper/${diaperToUpdate.id}${thisDiaper.id}/edit " method="put" modelAttribute="diaperToUpdate" class="form">
						<tbody>
							<tr class="h-16">
								<td class="p-5">
									<form:label path="time">Time:</form:label><br/> 
									<form:errors path="time" class="italic text-red-400 text-sm"/>
								</td>
								<td class="p-5">
									<form:input path="time" type="datetime-local" value="${diaperToUpdate.time }" class="shadow-inner rounded w-full border border-amber-200 p-1 focus:shadow-lg focus:shadow-amber-300"/>
								</td>
							</tr>
							<tr class="h-16">
								<td class="p-5">
									<form:label path="diaperType">Diaper Type:</form:label><br/> 
									<form:errors path="diaperType" class="italic text-red-400 text-sm"/>
								</td>
								<td class="p-5">
									<c:if test="${diaperToUpdate.diaperType eq 'Wet'}">
										<div>
											<form:radiobutton path="diaperType" value="Wet" checked="true"/>
											<form:label path="diaperType">Wet</form:label>
										</div>
										<div>
											<form:radiobutton path="diaperType" value="Dirty"/>
											<form:label path="diaperType">Dirty</form:label>
										</div>
										<div>
											<form:radiobutton path="diaperType" value="Wet + Dirty"/>
											<form:label path="diaperType">Wet + Dirty</form:label>
										</div>
									</c:if>
									<c:if test="${diaperToUpdate.diaperType eq 'Dirty'}">
										<div>
											<form:radiobutton path="diaperType" value="Wet"/>
											<form:label path="diaperType">Wet</form:label>
										</div>
										<div>
											<form:radiobutton path="diaperType" value="Dirty" checked="true"/>
											<form:label path="diaperType">Dirty</form:label>
										</div>
										<div>
											<form:radiobutton path="diaperType" value="Wet + Dirty"/>
											<form:label path="diaperType">Wet + Dirty</form:label>
										</div>
									</c:if>
									<c:if test="${diaperToUpdate.diaperType eq 'Wet + Dirty'}">
										<div>
											<form:radiobutton path="diaperType" value="Wet"/>
											<form:label path="diaperType">Wet</form:label>
										</div>
										<div>
											<form:radiobutton path="diaperType" value="Dirty"/>
											<form:label path="diaperType">Dirty</form:label>
										</div>
										<div>
											<form:radiobutton path="diaperType" value="Wet + Dirty" checked="true"/>
											<form:label path="diaperType">Wet + Dirty</form:label>
										</div>
									</c:if>
								</td>
							</tr>
							<tr class="h-16">
								<td class="p-5">
									<form:label path="notes">Notes (Optional):</form:label><br/> 
									<form:errors path="notes" class="italic text-red-400 text-sm"/>
								</td>
								<td class="p-5">
									<form:textarea path="notes" rows="2" value="${diaperToUpdate.notes}" class="shadow-inner rounded w-full border border-amber-200 p-1 focus:shadow-lg focus:shadow-amber-300"/>
								</td>
							</tr>
							<tr class="h-16">
								<td colspan=2 class="p-5"> <input type="submit" value="Submit" class="rounded hover:text-white hover:italic bg-green-400 hover:bg-green-500 w-full"/></td>
							</tr>
						</tbody>
						<!-- Hidden input to attach feed to baby -->
						<form:input type="hidden" path="baby" value="${baby.id}"/>
					</form:form>
				</table>			
			</div>
		</div>
	</body>
</html>