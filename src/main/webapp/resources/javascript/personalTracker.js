var weightButton = document.getElementById("weightButton");
var caloriesButton = document.getElementById("caloriesButton");
var stepsButton = document.getElementById("stepsButton");

var addWeightButton = document.getElementById("addWeightButton");
var cancelAddWeightButton = document.getElementById("cancelButtonWeight");

var addStepsButton = document.getElementById('addStepsButton');
var cancelAddStepsButton = document.getElementById("cancelButton");

var addCaloriesButton = document.getElementById("addCaloriesButton");
var cancelAddCaloriesButton = document.getElementById("cancelButton2");

var weightInfo = document.getElementById("weightInfo");
var caloriesInfo = document.getElementById("caloriesInfo");
var stepsInfo = document.getElementById("stepsInfo");

weightButton.addEventListener("click", () => {
    weightInfo.style.display = "block";
    caloriesInfo.style.display = "none";
    stepsInfo.style.display = "none";
});

caloriesButton.addEventListener("click", () => {
    caloriesInfo.style.display = "block";
    weightInfo.style.display = "none";
    stepsInfo.style.display = "none";
});

stepsButton.addEventListener("click", () => {
    stepsInfo.style.display = "block";
    weightInfo.style.display = "none";
    caloriesInfo.style.display = "none";
});

addStepsButton.addEventListener("click", () => {
    document.getElementById("data-edit-button").style.display = "none";
    document.getElementById("steps-input").style.display = "block";
});

cancelAddStepsButton.addEventListener("click", () => {
    document.getElementById("data-edit-button").style.display = "block";
    document.getElementById("steps-input").style.display = "none";
});

addCaloriesButton.addEventListener("click", () => {
   document.getElementById("data-edit-button2").style.display = "none";
   document.getElementById("calories-input").style.display = "block";
});

cancelAddCaloriesButton.addEventListener("click", () => {
    document.getElementById("data-edit-button2").style.display = "block";
    document.getElementById("calories-input").style.display = "none";
});

addWeightButton.addEventListener("click", () => {
   document.getElementById("data-edit-weight-button").style.display = "none";
   document.getElementById("weight-input").style.display = "block";
});

cancelAddWeightButton.addEventListener("click", () => {
    document.getElementById("data-edit-weight-button").style.display = "block";
    document.getElementById("weight-input").style.display = "none";
});

function decDate() {
    document.getElementById("date-3").stepDown();
}

function incDate() {
    document.getElementById("date-3").stepUp();
}