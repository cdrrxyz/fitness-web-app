<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Add Post</title>
    <link rel="stylesheet" href="resources/css/addPost.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <jsp:include page="topbar.jsp" />
    <div class="add-post-form">
        <form action="addPost" method="POST">
            <h1>Create A New Post</h1>
            <label for="content">Title</label>
            <input type="text" name="title" required>

            <label for="content">Content</label>
            <textarea id="content" name="content" rows="4" required></textarea>
            <div class="drop-down-list">
                <label for="category">Category</label>
                <select id="category" name="category" class="options" required>
                    <option name="category" value="boxing">Boxing</option>
                    <option name="category" value="cycling">Cycling</option>
                    <option name="category" value="running">Running</option>
                    <option name="category" value="weightTraining">Weight Training</option>
                </select>
            </div>

            <button type="submit">Post</button>
            <a href="discussion"><button type="button" class="cancel-btn">Cancel</button></a>
        </form>
    </div>
</body>