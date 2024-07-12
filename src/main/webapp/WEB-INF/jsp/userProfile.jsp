<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Profile Page</title>
    <link rel="stylesheet" href="/resources/css/userProfile.css" />
</head>

<body>
    <jsp:include page="topbar.jsp" />
    <div class="profile-page">
        <div class="profile-options">
            <div class="options-clicked">
                <form action="profile" method="get">
                    <button type="submit">User Profile</button>
                </form>
            </div>
            <div class="options">
                <form action="personalTracker" method="get">
                    <button type="submit">Personal Tracker</button>
                </form>
            </div>
            <div class="options">
                <form action="workoutPlan" method="get">
                    <button type="submit">Workout Plan</button>
                </form>
            </div>
        </div>

        <div class="profile-data">
            <div class="data-first-name">
                <p class="text-first-name-1">First Name</p>
                <p class="text-first-name-2"> ${model.firstName}</p>
            </div>
            <div class="data-last-name">
                <p class="text-last-name-1">Last Name</p>
                <p class="text-last-name-2">${model.lastName}</p>
            </div>
            <div class="data-email">
                <p class="text-email-1">Email Address</p>
                <p class="text-email-2">${model.email}</p>
            </div>
            <div class="data-dob">
                <p class="text-dob-1">Date of Birth</p>
                <p class="text-dob-2">${model.dob}</p>
            </div>
            <div class="data-edit-button">
                <form action="profileEdit" method="get">
                    <button>Edit</button>
                </form>
            </div>
        </div>
    </div>


</body>
</html>
