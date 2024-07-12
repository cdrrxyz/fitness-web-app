<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Personal Tracker</title>
    <link rel="stylesheet" href="/resources/css/personalTracker.css" />
</head>

<body>

<article
        id="weightOverTimeData"
        data-entries="${model.weightEntryTimes}"
        data-weights="${model.weightEntryData}">
</article>

    <jsp:include page="topbar.jsp" />

    <div class="personalTracker-page">
        <div class="personalTracker-options">
            <div class="options">
                <form action="profile" method="get">
                    <button type="submit">User Profile</button>
                </form>
            </div>
            <div class="options-clicked">
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

        <div class="personalTracker-data">

            <div class="tracker-options">
                <button id="weightButton">Weight</button>
                <button id="caloriesButton">Calories</button>
                <button id="stepsButton">Steps</button>
            </div>

            <div class="tracker-options-content">
                <div id="weightInfo" style="display: none;">
                    <div class="data-weight-current">
                        <p class="text-weight-current-1">Current Weight: ${model.currentWeight}kg</p>
<%--                        <p class="text-weight-current-2">${model.currentWeight}</p>--%>
                        <canvas id="weightChart" width="600" height="400"></canvas>
                    </div>
                    <div id="weight-input" style="display: none;">
                        <form action="weightEdit" method="post">
                            <input type="text" name="weight"/>
                            <input type="date" id="date-1" name="dateOfExercise">
                            Share with your groups?
                            <input type="checkbox" id="weight-checkbox" name="shareClicked" value="true">
<%--                            <div class="data-save-weight-button">--%>
<%--                                <input type="submit" value="Confirm"/>--%>
<%--                            </div>--%>
                            <div class="button-container">
                                <button type="submit" class="save-btn">Confirm</button>
                                <button type="button" class="cancel-btn">Cancel</button>
                            </div>
                        </form>
                    </div>
                    <div id="data-edit-weight-button" style="display: block;">
                        <button id="addWeightButton">+</button>
                    </div>
                </div>

                <div id="caloriesInfo" style="display: none;">
<%--                    calories burnt: ${model.caloriesBurnt}--%>
                    <div class="data-calories-today">
                        <p class="text-calories-today-1">Calories Burnt</p>
                        <p class="text-calories-today-2">${model.caloriesBurnt}</p>
                        <p class="text-calories-today-4">Workout Type | Calories</p>
                        <div class="text-calories-container">

                            <p class="text-calories-today-3">${model.caloriesAndExercises}</p>
                        </div>
                    </div>

                    <div id="calories-input" style="display: none;">
                        <form action="caloriesEdit" method="post">
                                <label for="workouts">Workout Type:</label>
                                <select name="workouts" id="workouts">
                                    <option value="RUNNING">RUNNING</option>
                                    <option value="WALKING">WALKING</option>
                                    <option value="SKIPPING">SKIPPING</option>
                                    <option value="SWIMMING">SWIMMING</option>
                                    <option value="CYCLING">CYCLING</option>
                                    <option value="WEIGHT TRAINING">WEIGHT TRAINING</option>
                                </select>
                            <input type="text" name="caloriesToday"/>
                            Share with your groups?
                            <input type="checkbox" id="calories-checkbox" name="shareClicked" value="true">
                            <div class="data-save-button">
                                <input type="submit" value="Confirm"/>
                            </div>
                        </form>
                        <div id="cancel-button-2">
                            <button id="cancelButton2">Cancel</button>
                        </div>
                    </div>
                    <div id="data-edit-button2" style="display: block;">
                        <button id="addCaloriesButton">+</button>
                    </div>

                </div>

                <div id="stepsInfo" style="display: none;">

                    <div class="data-steps-today">
                        <p class="text-steps-today-1">Steps: ${model.date}</p>
                        <p class="text-steps-today-2">${model.steps}</p>
                        <div class="stepDaySelect">
                            <form action="changeStepDate" method="get">
                                <input type="date" id="date-3" name="dateToView">
                                <script>
                                    document.getElementById("date-3").valueAsDate = new Date();
                                </script>
                                <div class="step-change-button">
                                    <input type="submit" value="Confirm"/>
                                </div>
                            </form>
                            <button id="stepDateDec" onclick="decDate()"> < </button>
                            <button id="stepDateInc" onclick="incDate()"> > </button>
                        </div>
                    </div>
                    <div id="steps-input" style="display: none;">
                        <form action="stepsEdit" method="post">
                            <input type="text" name="stepsToday"/>
                            <input type="date" id="date-2" name="dateOfExercise">
                            Share with your groups?
                            <input type="checkbox" id="steps-checkbox" name="shareClicked" value="true">
                            <div class="data-save-button">
                                <input type="submit" value="Confirm"/>
                            </div>
                        </form>
                        <div id="cancel-button">
                            <button id="cancelButton">Cancel</button>
                        </div>
                    </div>
                    <div id="data-edit-button" style="display: block;">
                            <button id="addStepsButton">+</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    const weightOverTime = document.querySelector("#weightOverTimeData");
    const ctx = document.getElementById('weightChart').getContext('2d');

    var entries = weightOverTime.dataset.entries.toString();
    var data = weightOverTime.dataset.weights.toString();

    data = data.split("*");
    entries = entries.split("*");

    var entries2 = entries.map(function(str) {
        return str.slice(0, str.length - 11);
    });

    var nums = data.map(function(str) {
        return parseInt(str);
    });

    new Chart(ctx, {
        type: 'line',
        data: {
            labels: entries2,
            datasets: [{
                label: 'Weight vs. Entry Time',
                data: nums,
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 2,
                fill: false
            }]
        },
        options: {
            responsive: false,
        }
    });
</script>
    <script type="text/javascript" src="/resources/javascript/personalTracker.js"></script>
</body>
</html>