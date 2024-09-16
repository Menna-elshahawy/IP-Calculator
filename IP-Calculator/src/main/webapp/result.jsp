<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="result" class="result">
    <!-- Result will be displayed here -->
    <c:if test="${not empty result}">
    	<pre></pre>
        <p>${result}</p>
        <!-- <p><strong>Address:</strong> ${address}</p>
        <p><strong>Netmask:</strong> ${netmask}</p>
        <p><strong>Netmask for Sub/Supernet:</strong> ${subsupernet}</p> -->
    </c:if>
</div>
