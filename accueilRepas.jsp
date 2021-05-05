<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	     integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
		<link rel="stylesheet" href="styleRepas.css">
		<title>Suivi des repas - Accueil</title>
	</head>
	
	<body>
		<h1>ACCUEIL</h1>
		<form action="/TPSuiviDesRepas/ServletAjoutRepas" method="get">
			<button type="submit" name="choix" value="ajouter" class="btn btn-primary btn-lg">Ajouter un nouveau repas</button>
		</form>
		<form action="/TPSuiviDesRepas/ServletVisualisationRepas" method="get">
			<button type="submit" name="choix" value="visualiser" class="btn btn-primary btn-lg">Visualiser les repas</button>
		</form>
	</body>
	
</html>