<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Workout Planner</title>
    <link rel="stylesheet" href="/resources/css/workoutPlanner.css" />
</head>

<body>

<jsp:include page="topbar.jsp" />
<div class="workoutPlanner-page">
    <div class="workoutPlanner-options">
        <div class="options">
            <form action="profile" method="get">
                <button type="submit">User Profile</button>
            </form>
        </div>
        <div class="options">
            <form action="personalTracker" method="get">
                <button type="submit">Personal Tracker</button>
            </form>
        </div>
        <div class="options-clicked">
            <form action="workoutPlan" method="get">
                <button type="submit">Workout Plan</button>
            </form>
        </div>
    </div>

    <div class="workoutPlanner-data">
        <p class="text-plans-0">Workout Plan For ${model.date}</p>
        <p class="text-plans-1">Workout Type | Duration</p>
        <div class="text-plans-container">
        <p class="text-plans-2">${model.plans}</p>
            </div>
        <p class="text-plans-3">Select a Day to View Your Workout Plan!</p>
        <form action="changePlanDate" method="get">
            <input type="date" id="date-1" name="dateToView">
            <script>
                document.getElementById("date-1").valueAsDate = new Date();
            </script>
            <div class="plan-change-button">
                <input type="submit" value="Confirm"/>
            </div>
        </form>
        <button id="planDateDec" onclick="decDate()"> < </button>
        <button id="planDateInc" onclick="incDate()"> > </button>
        <div id="workout-input" style="display: none;">
            <form action="workoutEdit" method="post">
                <label for="workouts">Workout Type:</label>
                <select name="workouts" id="workouts">
                    <option value="RUNNING">RUNNING</option>
                    <option value="WALKING">WALKING</option>
                    <option value="SKIPPING">SKIPPING</option>
                    <option value="SWIMMING">SWIMMING</option>
                    <option value="CYCLING">CYCLING</option>
                    <option value="WEIGHT TRAINING">WEIGHT TRAINING</option>
                </select>
                <label for="dur">Duration: </label>
                <input type="text" id="dur" name="duration"/>
                <label for="date-2">Date: </label>
                <input type="date" id="date-2" name="dateOfWorkout">
                <script>
                    document.getElementById("date-2").valueAsDate = new Date();
                </script>
                <div class="data-save-button">
                    <input type="submit" value="Confirm"/>
                </div>
            </form>
            <div id="cancel-button">
                <button id="cancelButton">Cancel</button>
            </div>
        </div>
        <div id="data-edit-button" style="display: block;">
            <button id="addWorkoutButton">+</button>
        </div>
    </div>



</div>

<script type="text/javascript" src="/resources/javascript/workoutPlanner.js"></script>
</body>
</html>