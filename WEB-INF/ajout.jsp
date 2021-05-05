<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	     integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
		<link rel="stylesheet" href="styleRepas.css">
		<title>Ajout</title>
	</head>
	<body>
		<%
			//TODO Récup les erreurs ici
			/*List<Integer> listeCodesErreur = request.getAttribute("listeCodesErreur");*/
		
		%>
		<h1>AJOUT</h1>
		<form action="/TPSuiviDesRepas/ServletAjoutRepas" method="post">
			<label>date : </label><input type="date"  name="date" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" required>
			<label>heure : </label><input type="time" name="heure" required>
			<label>repas : </label><input type="text" name="repas" required>
			<button type="submit" name="choix" value="ajouter" class="btn btn-primary btn-lg">Valider</button>
		</form>
		<form action="/TPSuiviDesRepas/accueilRepas.jsp" method="get">
			<button type="submit" name="choix" value="retour" class="btn btn-primary btn-lg">Annuler</button>
		</form>
		<p>Erreurs ici</p>
	</body>
</html>