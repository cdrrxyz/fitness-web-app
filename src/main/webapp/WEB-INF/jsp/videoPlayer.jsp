<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Video Player</title>
    <link rel="stylesheet" href="resources/css/videoPlayer.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <jsp:include page="topbar.jsp" />
    <% String videoId = request.getParameter("videoId"); %>
    <a href="training" class="back-btn"><i class="fa-solid fa-arrow-left"></i></a>
    <div class="container">
        <iframe width="896" height="504" src="https://www.youtube.com/embed/<%= videoId %>" frameborder="0" allowfullscreen></iframe>
    </div>
</body>
</html>
