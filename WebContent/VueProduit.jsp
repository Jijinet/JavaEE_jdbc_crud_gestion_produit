<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css" />

	<script>
	
	function confirmer(url){
		var rep=confirm("Etes vous sur de vouloire supprimer?");
		if(rep==true){
			document.location=url;
		}
	}
	
	</script>

</head>
<body>

<div>

	<form action="controleur" method="post">
	
		<table>
			<tr>
				<td>Mot Clé:</td>
				<td><input type="text" name="mc" value="${model.motCle}" /></td>
				<td><input type="submit" value="chercher" name="action" /></td>
			</tr>
			
		</table>
	
	</form>
</div>


<div>
	<form action="controleur" method="post">
	
		<input type="hidden" value="${model.mode}" name="mode" />
	
		<table>
		
		<c:if test="${model.mode=='ajouter'}">
		<tr>
				<td>Reference</td>
				<td><input type="text" name="ref" value="${model.produit.reference }" /></td>
				
			</tr>
		</c:if>
		
		<c:if test="${model.mode=='modifier'}">
		<tr>
				<td>Reference</td>
				<td>${model.produit.reference}<input type="hidden" name="ref" value="${model.produit.reference }" /></td>
				
			</tr>
		</c:if>
			
			<tr>
				<td>Designation:</td>
				<td><input type="text" name="designation" value="${model.produit.designation }" /></td>
			
			</tr>
			
			<tr>
				<td>Prix:</td>
				<td><input type="text" name="prix" value="${model.produit.prix }" /></td>
				<td>DH</td>
			</tr>
			
			<tr>
				<td>Quantité:</td>
				<td><input type="number" name="qte" value="${model.produit.quantite }" /></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="save" name="action" /></td>
				
			</tr>
		</table>
	
	</form>

</div>

	<p class="error">${model.error}</p>

<div>

<table class="table1">
			<tr>
				<th>Reference</th> <th>Designation</th> <th>Prix</th> <th>Quantite</th>
				
			</tr>
			
			<c:forEach items="${model.produits}" var="p">
				<tr>
					<td>${p.reference}</td>
					<td>${p.designation}</td>
					<td>${p.prix}</td>
					<td>${p.quantite}</td>
					<td><a href="javascript:confirmer('controleur?action=delete&ref=${p.reference}')">Supprimer</a></td>
					<td><a href="controleur?action=modifier&ref=${p.reference}">Modifier</a></td>
				</tr>
			</c:forEach>
		</table>

</div>




</body>
</html>