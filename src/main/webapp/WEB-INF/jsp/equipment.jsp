<%@ page import="com.example.elec5619fitnesswebapp.model.Equipment" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Equipment</title>
    <link rel="stylesheet" type="text/css" href="resources/css/equipment.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <jsp:include page="topbar.jsp" />


    <div class="equipment-container">
        <%
            List<Equipment> equipments = (List<Equipment>) request.getAttribute("equipments");
            for (Equipment equipment : equipments) {
        %>
            <div class="equipment-card">
                <img src="resources/images/<%= equipment.getImgURL() %>.png" alt="Image">
                <a href="equipmentDetails?name=<%= equipment.getName() %>" style="text-decoration: none; color: inherit;">
                    <button><%= equipment.getName() %></button>
                </a>
            </div>
        <%
            }
        %>
    </div>
</body>
</html>

