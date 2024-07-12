<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Discussion</title>
    <link rel="stylesheet" href="resources/css/discussion.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <jsp:include page="topbar.jsp" />
    <div class="top-container">
        <form action="discussion" method="GET">
            <div class="horizontal-container">
                <input type="text" name="search" id="searchInput" placeholder="Search..." class="search-box">
                <button class="search-btn">
                    <i class="fas fa-search"></i>
                </button>
                <div class="drop-down-list">
                    <p>Sort By:</p>
                    <select name="sorting" class="options" id="sortSelect">
                        <option value="all">All</option>
                        <option value="recentlyCreated">Recently Created</option>
                        <option value="mostLiked">Most Liked</option>
                    </select>
                </div>
            </div>
            <div class="category-container">
                <div class="btn-group">
                    <button class="category-button" name="category" value="all">All</button>
                    <button class="category-button" name="category" value="boxing">Boxing</button>
                    <button class="category-button" name="category" value="cycling">Cycling</button>
                    <button class="category-button" name="category" value="pilates">Pilates</button>
                    <button class="category-button" name="category" value="running">Running</button>
                    <button class="category-button" name="category" value="weightTraining">Weight Training</button>
                    <button class="category-button" name="category" value="yoga">Yoga</button>
                </div>
            </div>
        </form>
    </div>
    <a href="post"><button class="add-btn">Create New Post</button></a>

    <%
        List<Map> posts = (List<Map>) request.getAttribute("posts");
        for (Map post : posts) {
    %>
    <div class="post-container">
        <div class="post">
            <div class="post-header">
                <img src="resources/images/avatar.png" alt="Avatar">
                <div>
                    <h1 class="post-title"><%= post.get("title") %></h1>
                    <p class="post-author"><%= post.get("name") %></p>
                </div>
            </div>
            <p class="post-content"><%= post.get("content") %></p>
        </div>
        <hr>
        <div class="comments">
            <form action="addComment" method="POST" class="add-comment-container">
                <img src="resources/images/avatar.png" alt="Avatar">
                <input type="hidden" name="post_id" value=<%= post.get("id") %>>
                <input type="text" name="comment" placeholder="Type your comment..."/>
                <button type="submit" ><i class="fa fa-paper-plane"></i></button>
            </form>
            <%
                List<Map> comments = (List<Map>) post.get("comments");
                for (Map comment : comments) {
            %>
            <div class="comments-container">
                <div class="comment">
                    <img src="resources/images/avatar.png" alt="Avatar">
                    <div>
                        <p class="comment-author"><%= comment.get("name") %></p>
                        <p class="comment-content"><%= comment.get("comment") %></p>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
    <%
        }
    %>
    <script>
        
        const sortSelect = document.getElementById("sortSelect");
        const form = document.querySelector("form");
        sortSelect.addEventListener("change", function() {
            form.submit();
        });

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
