<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Training</title>
    <link rel="stylesheet" type="text/css" href="resources/css/training.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <jsp:include page="topbar.jsp" />
    <form action="training" method="GET">
        <div class="search-bar">
            <input type="text" name="search" id="searchInput" placeholder="Search..." class="search-box">
            <button class="search-btn">
                <i class="fas fa-search"></i>
            </button>
        </div>
        <div class="category-container">
            <div class="btn-group">
                <button class="category-button" name="type" value="all">All</button>
                <button class="category-button" name="type" value="beginner">Beginner</button>
                <button class="category-button" name="type" value="intermediate">Intermediate</button>
                <button class="category-button" name="type" value="advanced">Advanced</button>
            </div>
        </div>
    </form>
    <div class="video-container">
        <%
            List<Map> guides = (List<Map>) request.getAttribute("guides");
            for (Map guide : guides) {
        %>
        <a href="videoPlayer?videoId=<%= guide.get("videoId") %>" style="text-decoration: none; color: inherit;">
            <div class="video-card">
                <img src="https://img.youtube.com/vi/<%= guide.get("videoId") %>/mqdefault.jpg" alt="Video Image" class="video-image">
                <div class="video-info">
                    <h3><%= guide.get("title") %></h3>
                    <p><%= guide.get("description") %></p>
                </div>
            </div>
        </a>
        <%
            }
        %>
    </div>

    <script>

        document.addEventListener("DOMContentLoaded", function () {
            const urlParams = new URLSearchParams(window.location.search);
            const searchTerm = urlParams.get("search");

            if (searchTerm) {
                document.getElementById("searchInput").value = searchTerm;
            }
        });
    </script>

</body>
</html>

