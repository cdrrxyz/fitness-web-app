<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="resources/css/logout.css">
</head>
<body>
    <jsp:include page="topbar.jsp" />
    <div class="logout-form">
        <form action="/" method="post">
            <h1>CONFIRM</h1>
            <div id="confirmationDialog" class="dialog">
                <p>Are you sure you want to log out?</p>
                <div class="buttons">
                    <button class="ok-btn" formaction="/home?logout=true">OK</button>
                    <button class="cancel-btn" formaction="/home?logout=false">Cancel</button>
                </div>
            </div>
        </form>
    </div>

</body>
</html>
