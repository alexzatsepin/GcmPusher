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
					<td colspan="2">For sending the message to several devices use comma separator</td>
				</tr>
				<tr>
					<td><label for="regId">Registration ids:</label></td>
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
				<tr>
					<td><input type="submit" value="Send push message"/></td>
				</tr>
			</table>
			</p>You can donwload android GCM client app 
			<a href="/clientapk/gcm_client.apk"> here</a>
		</fieldset>
	</form>

</body>
</html>