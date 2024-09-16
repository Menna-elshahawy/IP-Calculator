<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>IP Calculator</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css"> <!-- Link to external CSS -->
    <!--<script src="js/scripts.js" defer></script> -->
</head>
<body>
    <div class="title">
        <h1>IP Calculator</h1>
    </div>
    <div class="box">
		<form action="calculate" method="post">
            <div class="container">
                <div class="section">
                    <label for="address">
                        <h3>Address</h3>
                    </label>
                    <input id="address" name="address" type="text" placeholder="Enter IP address"  value="${not empty address ? address : ''}"  required>
                </div>
                <div class="section">
                    <label for="netmask">
                        <h3>Netmask</h3>
                    </label>
                    <input id="netmask" name="netmask" type="text" placeholder="Enter netmask"  value="${not empty netmask ? netmask : ''}" required>
                </div>
                <div class="section">
                    <label for="subsupernet">
                        <h3>Netmask for Sub/Supernet</h3>
                    </label>
                    <input id="subsupernet" name="subsupernet" type="text" placeholder="Enter netmask for sub/supernet" value="${not empty subsupernet ? subsupernet : ''}">
                </div>
            </div>
            <div class="button-container">
                <button class="calculate-button" type="submit">Calculate</button>
            </div>
        </form>
        <jsp:include page="result.jsp" /> <!-- Include the result display area -->
    </div>
</body>
</html>
