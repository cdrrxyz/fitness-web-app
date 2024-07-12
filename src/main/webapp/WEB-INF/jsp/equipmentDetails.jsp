<%@ page import="com.example.elec5619fitnesswebapp.model.Equipment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Equipment equipment = (Equipment) request.getAttribute("equipment");%>
<html>
<head>
    <title><%=equipment.getName() %></title>
    <link rel="stylesheet" type="text/css" href="resources/css/equipmentDetails.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <jsp:include page="topbar.jsp" />
    <a href="equipment" class="back-btn"><i class="fa-solid fa-arrow-left"></i></a>
    <div class="details_container">
        <p class="title"><%=equipment.getName() %></p>
        <div>
            <img src="resources/images/<%= equipment.getImgURL() %>.png" alt="Image">
            <p><%=equipment.getDescription() %></p>
        </div>
        <iframe width="1024" height="576" src="https://www.youtube.com/embed/<%= equipment.getVideoId() %>" frameborder="0" allowfullscreen></iframe>
    </div>



</body>
</html>

