<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Profile Edit</title>
    <link rel="stylesheet" href="/resources/css/userProfileEdit.css" />
</head>

<body>
    <jsp:include page="topbar.jsp" />
    <div class="profile-edit-page">
        <div class="profile-edit-options">
            <div class="options-one">
                <form action="profile" method="get">
                    <button type="submit">User Profile</button>
                </form>
            </div>
            <div class="options-two">
                <form action="personalTracker" method="get">
                    <button type="submit">Personal Tracker</button>
                </form>
            </div>
            <div class="options-three">
                <form action="workoutPlan" method="get">
                    <button type="submit">Workout Plan</button>
                </form>
            </div>
        </div>

        <form action="profileEdit" method="post">
            <div class="profile-data">
                <div class="data-first-name">
                    <p class="text-first-name">First Name</p>
                    <input type="text" name="firstName" value="${model.firstName}"/>
                </div>
                <div class="data-last-name">
                    <p class="text-last-name">Last Name</p>
                    <input type="text" name="lastName" value="${model.lastName}"/>
                </div>
                <div class="data-email">
                    <p class="text-email-1">Email Address</p>
                    <p class="text-email-2">${model.email}</p>
                </div>
                <div class="data-dob">
                    <p class="text-dob">Date of Birth</p>
                    <input type="date" id="date" name="dateOfBirth" value="${model.dob}">
                </div>
                <div class="button-container">
                    <button type="submit" class="save-btn">Save</button>
                    <a href="profile"><button type="button" class="cancel-btn">Cancel</button></a>
                </div>
            </div>
        </form>

</div>

</body>
</html>
