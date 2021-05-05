<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="java.util.List,java.util.ArrayList,fr.eni.tpsuividesrepas.bo.Repas" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="https://kit.fontawesome.com/a076d05399.js"></script>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	     integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
		<link rel="stylesheet" href="styleRepas.css">
		<title>Historique</title>
	</head>
	<body>
		
		<%
			@SuppressWarnings("unchecked")
			List<Repas> listeRepas = (List<Repas>) request.getAttribute("allRepas");
		%>
		<h1>HISTORIQUE</h1>
		
		<table class="table table-condensed container">
		    <thead>
		        <tr>
		            <th>Date</th>
		            <th>Heure</th>
		            <th>Actions</th>	         
		        </tr>
		    </thead>
		    <tbody>
		    	<% for(Repas repas : listeRepas){ %>
		    	
	    			<tr data-toggle="collapse" data-target="#repas<%=repas.getIdentifiant()%>" class="accordion-toggle">
			            <td><%=String.format("%02d", repas.getDate().getDayOfMonth())%>/<%=String.format("%02d", repas.getDate().getMonthValue())%>/<%=repas.getDate().getYear()%></td>
			            <td><%=repas.getHeure().getHour() %>h<%=repas.getHeure().getMinute() %></td>
			            <td><a class="lien">détail</a></td>
		        	</tr>
		        	<tr >
		           		<td colspan="6" class="hiddenRow">
		           			<div class="accordian-body collapse" id="repas<%=repas.getIdentifiant()%>"> 
		           			<% for(String aliment : repas.getAliments()){%>
		           				<div class="blue"><i class='fas fa-chevron-circle-right' style='font-size:12px'></i> <%=aliment%></div>
		           			<%}%> 
		           			</div>
		           		</td>
		           			
		        	</tr>
		    	<%}%>
		    </tbody>
		</table>
		
		<div class="ligne">
			<form action="/TPSuiviDesRepas/ServletAjoutRepas" method="get">
				<button type="submit" name="choix" value="ajouter" class="btn btn-primary btn-lg">Ajouter un nouveau repas</button>
			</form>
			<form action="/TPSuiviDesRepas/accueilRepas.jsp" method="get">
				<button type="submit" name="choix" value="retour" class="btn btn-primary btn-lg">Retour à l'accueil</button>
			</form>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>
	</body>
	
</html>