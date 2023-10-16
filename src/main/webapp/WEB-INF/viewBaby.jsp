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
		<title>Baby Tracker: View Baby</title>
		<link rel="stylesheet" type="text/css" href="/css/style.css">
		<script type="text/javascript" src="/js/app.js"></script>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		<script src="https://cdn.tailwindcss.com"></script>
	</head>
	<body class="bg-amber-50">
		<div class="h-24 px-20 flex justify-between items-center bg-gradient-to-r from-amber-200 to-amber-300">
			<p class="text-5xl text-amber-600">Baby Tracker</p>
			<div class="w-96 text-lg flex gap-10 rounded bg-amber-500">
				<a href="/home" class="text-center hover:no-underline hover:text-black w-24 hover:rounded-l hover:bg-amber-600 hover:ring-1 hover:ring-amber-800">Home</a>
				<a href="/babies/resources" class="text-center w-24 hover:no-underline hover:text-black hover:bg-amber-600 hover:ring-1 hover:ring-amber-800">Resources</a>
				<form action="/logout" method="post">
					<button type="submit" class="w-28 hover:rounded-r hover:bg-amber-600 hover:ring-1 hover:ring-amber-800">Sign Out</button>
				</form>
			</div>
		</div>
		<div class="mx-auto px-20 my-3">
			<p class="text-3xl my-2"><c:out value="${baby.firstName} ${baby.lastName}"/></p>
			<p class="text-xl my-2">Date of Birth: <c:out value="${baby.birthday.format(formatter)}"/></p>
			<p class="text-xl my-2">Age: <c:out value="${Period.between(baby.birthday,today).getYears()}y ${Period.between(baby.birthday,today).getMonths()}m ${Period.between(baby.birthday,today).getDays()}d" /></p>
			<p class="text-xl my-2">Gender: <c:out value="${baby.gender}"/></p>
			<div class="my-3 flex gap-2 justify-start text-xl">
				<a href="/babies/${baby.id}/edit" class="text-center hover:text-white hover:italic hover:no-underline rounded bg-green-400 hover:bg-green-500 w-24">Edit</a>
				<div class="rounded bg-blue-300 flex w-60">
					<a href="/babies/${baby.id}/feed/new" class="text-center w-20 hover:rounded hover:text-white hover:italic hover:no-underline hover:bg-blue-400">Feed</a>
					<a href="/babies/${baby.id}/diaper/new" class="text-center w-40 hover:rounded hover:text-white hover:italic hover:no-underline hover:bg-blue-400 ">Change Diaper</a>
				</div>
				<form action="/babies/${baby.id}/delete" method="post">
					<input type="hidden" name="_method" value="delete" />
					<button type="submit" class="hover:italic rounded bg-red-400 hover:bg-red-500 w-20 text-white">Remove</button>
				</form>
			</div>
			<div class="my-3 flex grid grid-cols-2 gap-10">
				<div>
					<table class="table-auto w-full border border-separate border-spacing-0 rounded-lg border-amber-400">
						<caption class="caption-top text-2xl italic text-amber-600">Feeds</caption>
						<thead>
							<tr class="text-left text-lg bg-amber-300 rounded-t-lg">
								<th scope="col" class="px-3 rounded-tl-lg border border-amber-300">Time</th>
								<th scope="col" class="px-3 border border-amber-300">Amount</th>
								<th scope="col" class="px-3 border border-amber-300">Feed Type</th>
								<th scope="col" class="px-3 rounded-tr-lg border border-amber-300">Actions</th>
							</tr>
						</thead>
						<tbody >
							<c:choose>
								<c:when test="${baby.feeds.size()==0}">
									<tr>
										<td colspan="4" class="h-28 text-center italic text-3xl border border-amber-300">No feeds tracked for <c:out value="${baby.firstName}"/> yet</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="feed" items="${baby.feeds}">
										<tr class="h-20">
											<td class="px-3 text-xl border border-amber-300 h-20"><c:out value="${feed.time.format(formatter2)}"/></td>
											<td class="px-3 text-xl border border-amber-300 h-20"><c:out value="${feed.amount}"/> oz</td>
											<td class="px-3 text-xl border border-amber-300 h-20"><c:out value="${feed.feedType}"/></td>
											<td class="px-3 border border-amber-300 flex gap-1 justify-start items-center h-20">
												<button type="submit" class="text-center hover:text-white hover:italic hover:no-underline rounded bg-amber-300 hover:bg-amber-400 w-24 " data-toggle="modal" data-target="#myFeedModal${feed.id}">View Note</button>
												<a href="/babies/${baby.id}/feed/${feed.id}/edit" class="text-center hover:text-white hover:italic hover:no-underline rounded bg-green-400 hover:bg-green-500 w-16">Edit</a>
												<form action="/babies/${baby.id}/feed/${feed.id}/delete" method="post">
													<input type="hidden" name="_method" value="delete" />
													<button type="submit" class="hover:italic rounded bg-red-400 hover:bg-red-500 w-20 text-white">Delete</button>
												</form>
											</td>
										</tr>
										<div class="modal fade rounded" id="myFeedModal${feed.id}">
								            <div class="modal-dialog rounded">
								                <div class="modal-content bg-amber-100">
								                    <div class="modal-header bg-amber-100">
								                        <p class="modal-title text-2xl font-bold">Feed Note</p>
								                        <button type="button" class="close" data-dismiss="modal">&times;</button>
								                    </div>
								                    <div class="modal-body bg-amber-100">
								                        <p><c:out value="${feed.time.format(formatter2)}"/></p>
								                    	<c:choose>
								                    		<c:when test="${feed.notes==''}">
								                    			<p class="italic text-xl">No notes for this feed</p>
								                    		</c:when>
								                    		<c:otherwise>
								                    			<p class="italic text-xl"><c:out value="${feed.notes}"/></p>
								                    		</c:otherwise>
								                    	</c:choose>
								                    </div>
								                    <div class="modal-footer bg-amber-100">
								                        <button type="button" class="rounded bg-red-400 hover:bg-red-500 w-20 text-white" data-dismiss="modal">Close</button>
								                    </div>
								                </div>
								            </div>
								        </div>
									</c:forEach>							
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
				<div>
					<table class="table-auto w-full border border-separate border-spacing-0 rounded-lg border-amber-300">
						<caption class="caption-top text-2xl italic text-amber-600">Diaper Changes</caption>
						<thead>
							<tr class="text-left text-lg bg-amber-300">
								<th scope="col" class="w-1/3 px-3 rounded-tl-lg border border-amber-300">Time</th>
								<th scope="col" class="w-1/3 px-3 border border-amber-300">Diaper Type</th>
								<th scope="col" class="w-1/3 px-3 rounded-tr-lg border border-amber-300">Actions</th>
							</tr>
						</thead>
						<tbody >
							<c:choose>
								<c:when test="${baby.diapers.size()==0}">
									<tr>
										<td colspan="4" class="h-28 text-center italic text-3xl border border-amber-300">No diaper changes tracked for <c:out value="${baby.firstName}"/> yet</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="diaper" items="${baby.diapers}">
										<tr class="h-20">
											<td class="px-3 text-xl border border-amber-300 h-20"><c:out value="${diaper.time.format(formatter2)}"/></td>
											<td class="px-3 text-xl border border-amber-300 h-20"><c:out value="${diaper.diaperType}"/></td>
											<td class="px-3 border border-amber-300 flex gap-1 justify-start items-center h-20">
												<button type="submit" class="text-center hover:text-white hover:italic hover:no-underline rounded bg-amber-300 hover:bg-amber-400 w-24" data-toggle="modal" data-target="#myDiaperModal${diaper.id}">View Note</button>
												<a href="/babies/${baby.id}/diaper/${diaper.id}/edit" class="text-center hover:text-white hover:italic hover:no-underline rounded bg-green-400 hover:bg-green-500 w-16">Edit</a>
												<form action="/babies/${baby.id}/diaper/${diaper.id}/delete" method="post">
													<input type="hidden" name="_method" value="delete" />
													<button type="submit" class="hover:italic rounded bg-red-400 hover:bg-red-500 w-20 text-white">Delete</button>
												</form>
											</td>
										</tr>
										<div class="modal fade rounded" id="myDiaperModal${diaper.id}">
								            <div class="modal-dialog rounded">
								                <div class="modal-content bg-amber-100">
								                    <div class="modal-header bg-amber-100">
								                        <p class="modal-title text-2xl font-bold">Diaper Change Note</p>
								                        <button type="button" class="close" data-dismiss="modal">&times;</button>
								                    </div>
								                    <div class="modal-body bg-amber-100">
								                        <p><c:out value="${diaper.time.format(formatter2)}"/></p>
								                    	<c:choose>
								                    		<c:when test="${diaper.notes==''}">
								                    			<p class="italic text-xl">No notes for this diaper change</p>
								                    		</c:when>
								                    		<c:otherwise>
										                        <p class="italic text-xl"><c:out value="${diaper.notes}"/></p>
								                    		</c:otherwise>
								                    	</c:choose>
								                    </div>
								                    <div class="modal-footer bg-amber-100">
								                        <button type="button" class="rounded bg-red-400 hover:bg-red-500 w-20 text-white" data-dismiss="modal">Close</button>
								                    </div>
								                </div>
								            </div>
								        </div>
									</c:forEach>							
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>