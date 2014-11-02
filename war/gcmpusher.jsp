<html>
<head>
<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
</head>

<body>

	<form action="/gcmpusher" method="post">
		<fieldset>
			<table>
				<tr>
					<td><label for="apiKey">API Key:</label></td>
					<td><input type="text" name="apiKey" id="apiKey" value="AIzaSyCiIdSUrS23cYs4z6u3sXxcLTXy6L32ptE" /></td>
				</tr>
				<tr>
					<td><label for="regId">Registration id:</label></td>
					<td><input type="text" name="regId" id="regId" /></td>
				</tr>
				<tr>
					<td><label for="collapseKey">Collapse key:</label></td>
					<td><input type="text" name="collapseKey" id="collapseKey" /></td>
				</tr>
				<tr>
					<td><label for="message">Push message:</label></td>
					<td><input type="text" name="message" id="message" /></td>
				</tr>
				<tr>
					<td><label for="pushCount">Push count:</label></td>
					<td><input type="number" name="pushCount" id="pushCount" value="1" /></td>
				</tr>
			</table>
			<input type="submit" value="Send push message"/>
		</fieldset>
	</form>

</body>
</html>