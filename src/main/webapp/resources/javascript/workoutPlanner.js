var addWorkoutButton = document.getElementById('addWorkoutButton');
var cancelAddWorkoutButton = document.getElementById("cancelButton");

addWorkoutButton.addEventListener("click", () => {
    document.getElementById("data-edit-button").style.display = "none";
    document.getElementById("workout-input").style.display = "block";
});

cancelAddWorkoutButton.addEventListener("click", () => {
    document.getElementById("data-edit-button").style.display = "block";
    document.getElementById("workout-input").style.display = "none";
});

function decDate() {
    document.getElementById("date-1").stepDown();
}

function incDate() {
    document.getElementById("date-1").stepUp();
}