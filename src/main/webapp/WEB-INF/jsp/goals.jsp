<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Fitness</title>
    <link rel="stylesheet" href="/resources/css/index.css" />
    <link rel="stylesheet" href="/resources/css/groups.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="/resources/javascript/goals.js"></script>
</head>
<body>
<div class="flex-col page">
    <jsp:include page="home.jsp" />
    <article id="current-user" data-current-user-id="${user_id}"></article>
    <div class="flex-row">
        <div class="flex-col items-center shrink-0 section_2 space-y-34">
            <div style="background-color: burlywood" class="section_3"></div>
            <a href="http://localhost:8080/leaderboard">
            <svg t="1696489835762" class="icon" fill="#333" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4013" width="27" height="27">
                <path d="M832 364.8h-147.2s19.2-64 32-179.2c6.4-57.6-38.4-115.2-102.4-121.6h-12.8c-51.2 0-83.2 32-102.4 76.8l-38.4 96c-32 64-57.6 102.4-76.8 115.2-25.6 12.8-121.6 12.8-128 12.8H128c-38.4 0-64 25.6-64 57.6v480c0 32 25.6 57.6 64 57.6h646.4c96 0 121.6-64 134.4-153.6l51.2-307.2c6.4-70.4-6.4-134.4-128-134.4z m-576 537.6H128V422.4h128v480z m640-409.6l-51.2 307.2c-12.8 57.6-12.8 102.4-76.8 102.4H320V422.4c44.8 0 70.4-6.4 89.6-19.2 32-12.8 64-64 108.8-147.2 25.6-64 38.4-96 44.8-102.4 6.4-19.2 19.2-32 44.8-32h6.4c32 0 44.8 32 44.8 51.2-12.8 102.4-32 166.4-32 166.4l-25.6 83.2h243.2c19.2 0 32 0 44.8 12.8 12.8 12.8 6.4 38.4 6.4 57.6z" p-id="4014"></path></svg>
            </a>
            <a href="http://localhost:8080/group">
                <svg t="1696490190358" class="icon" fill="#333" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5925" width="27" height="27"><path d="M512 64c259.2 0 469.333333 200.576 469.333333 448s-210.133333 448-469.333333 448a484.48 484.48 0 0 1-232.725333-58.88l-116.394667 50.645333a42.666667 42.666667 0 0 1-58.517333-49.002666l29.76-125.013334C76.629333 703.402667 42.666667 611.477333 42.666667 512 42.666667 264.576 252.8 64 512 64z m0 64C287.488 128 106.666667 300.586667 106.666667 512c0 79.573333 25.557333 155.434667 72.554666 219.285333l5.525334 7.317334 18.709333 24.192-26.965333 113.237333 105.984-46.08 27.477333 15.018667C370.858667 878.229333 439.978667 896 512 896c224.512 0 405.333333-172.586667 405.333333-384S736.512 128 512 128z m-157.696 341.333333a42.666667 42.666667 0 1 1 0 85.333334 42.666667 42.666667 0 0 1 0-85.333334z m159.018667 0a42.666667 42.666667 0 1 1 0 85.333334 42.666667 42.666667 0 0 1 0-85.333334z m158.997333 0a42.666667 42.666667 0 1 1 0 85.333334 42.666667 42.666667 0 0 1 0-85.333334z" fill="#333333" p-id="5926"></path></svg>
            </a>
            <a href="http://localhost:8080/notification">
                <svg t="1696490284210" class="icon" fill="#333" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="6995" width="30" height="30"><path d="M593.861938 788.582269 424.670537 788.582269c-9.444093 0-18.437931 3.931542-24.695448 10.902304-6.313799 6.970762-9.441023 16.32378-8.547677 25.675776 2.860141 29.191856 16.32378 56.238862 38.009685 76.018348 21.772886 20.016893 50.161447 31.0379 79.889515 31.0379 29.696346 0 58.084906-11.022031 79.830163-30.977525 21.714558-19.839861 35.178197-46.885843 38.068014-76.255755 0.595564-9.473769-2.534729-18.707061-8.638751-25.498744C612.299869 792.513812 603.306031 788.582269 593.861938 788.582269zM555.020304 863.825974c-25.082258 22.877033-66.604954 22.817682-91.567485 0.060375-7.596002-6.970762-13.404288-15.429411-17.157775-24.723078l125.82266 0C568.394916 848.51629 562.643935 856.914564 555.020304 863.825974z" fill="#231F20" p-id="6996"></path><path d="M818.608631 648.343271l-62.763462-82.927711 0-36.22197 0-13.046131L755.845169 410.432767c0-70.745251-24.215518-136.337131-68.182892-184.682209-26.003234-28.625968-57.310264-49.715285-93.055372-62.821791-3.306302-18.944468-12.720719-36.251645-26.926256-49.207725-32.050973-29.251208-85.104283-29.251208-117.095905 0-14.356986 13.046131-23.77038 30.382984-26.986631 49.2681-35.71441 13.046131-67.022463 34.135448-93.025697 62.791092-43.937698 48.434106-68.183915 114.025986-68.183915 184.652534l0.179079 154.686035-62.315254 82.45085c-8.757454 9.353019-13.582343 21.506826-13.582343 34.256198l0 40.331567c0 27.643594 22.460548 50.042743 50.042743 50.042743l544.812313 0c27.610848 0 50.011021-22.400173 50.011021-50.042743l0-40.331567C831.535035 669.075455 826.739822 656.921647 818.608631 648.343271zM535.776008 149.881612c-7.387247-0.655939-19.301602-1.906419-26.569122-1.906419-7.29822 0-19.689435 1.251503-27.048029 1.906419C494.578724 129.627313 526.542716 133.379777 535.776008 149.881612zM237.426992 722.156394l-0.119727-40.034808 62.315254-82.449827c8.698103-9.354042 13.524015-21.447475 13.524015-34.256198L313.146535 410.432767c0-58.056254 19.540032-111.553679 54.986335-150.634766 17.574261-19.361977 38.307468-34.374902 61.540611-44.681642 48.851615-21.745257 110.302175-21.745257 159.096485 0 23.321148 10.425444 43.99398 25.438369 61.538565 44.681642 35.449373 39.081087 54.958706 92.578512 54.958706 150.634766l0 105.715717 0 13.046131 0 36.22197c0 12.867052 4.825912 25.081235 12.95608 33.539884l62.791092 82.868359 0.508583 39.795355L237.426992 722.156394z" fill="#231F20" p-id="6997"></path></svg>
            </a>
            <a href="http://localhost:8080/goals">
            <svg t="1696490353428" class="icon" fill="#333" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="8254" width="30" height="30"><path d="M382.224 38.927l-9.965-21.056-126.592 73.085 13.258 19.169 27.762 40.126c33.577-29.595 73.012-52.695 116.361-67.306l-20.824-44.018zM765.068 110.125l13.266-19.169-126.587-73.085-9.978 21.056-20.831 44.014c43.353 14.61 82.792 37.711 116.372 67.309l27.758-40.125zM879.145 276.31l21.073-9.972-73.086-126.591-19.173 13.265-40.129 27.757c29.596 33.578 52.696 73.015 67.306 116.365l44.009-20.824zM579.202 71.798l3.976-48.563L585.084 0.007H438.907l1.907 23.228 3.974 48.565a342.514 342.514 0 0 1 67.211-6.64c23.007 0 45.476 2.294 67.203 6.638zM216.038 153.012l-19.162-13.265-73.094 126.591 21.071 9.972 44.01 20.825c14.609-43.347 37.707-82.781 67.299-116.357l-40.124-27.766zM641.777 773.232l9.97 21.057 126.587-73.085-13.266-19.164-27.757-40.13c-33.578 29.595-73.015 52.695-116.364 67.306l20.83 44.016zM144.854 535.857l-21.071 9.965 73.094 126.591 19.162-13.265 40.129-27.761c-29.592-33.575-52.69-73.007-67.3-116.352l-44.014 20.822zM807.967 659.148l19.165 13.265 73.086-126.591-21.073-9.973-44.007-20.827c-14.609 43.349-37.709 82.785-67.303 116.362l40.132 27.764zM258.925 702.04l-13.258 19.164 126.592 73.085 9.965-21.057 20.826-44.018c-43.347-14.609-82.781-37.709-116.356-67.301l-27.769 40.127zM846.279 473.294l48.566 3.971 23.227 1.899V332.996l-23.227 1.899-48.566 3.966a342.511 342.511 0 0 1 6.641 67.219 342.46 342.46 0 0 1-6.641 67.214zM177.719 338.874l-48.572-3.979-23.22-1.899v146.168l23.22-1.899 48.573-3.973a342.524 342.524 0 0 1-6.64-67.212c0-23.007 2.294-45.477 6.639-67.206zM444.785 740.359l-3.971 48.567-1.907 23.227h146.177l-1.893-23.227-3.971-48.568A342.438 342.438 0 0 1 512 747c-23.01 0-45.483-2.295-67.215-6.641z" p-id="8255"></path><path d="M620.373 860.389H403.596l-17.033-4.573L270.832 789v234.994l160.782-58.664 80.393-29.328 80.376 29.328 160.785 58.664V789.001l-115.72 66.812z" p-id="8256"></path><path d="M512 406.08m-292.92 0a292.92 292.92 0 1 0 585.84 0 292.92 292.92 0 1 0-585.84 0Z" p-id="8257"></path></svg>
            </a>
        </div>
        <div class="flex-col flex-auto self-start groups space-y-16">
            <div class="flex-col section_4">

                <!-- Goals标题 -->
                <div class="flex-row justify-between items-center">
                    <span class="font_3">Goals</span>
                </div>

                <div id="goals_container" class="flex-col justify-between items-center group_2">

                    <img src="/resources/images/avater.png" alt="Avatar" class="section_3">

                    <div class="flex-row items-center" style="flex-grow: 2;" >
                        <span class="font_3" style="margin-left: 2rem;"></span>
                        <span class="font_3" style="margin-right: 4rem; margin-left: 2rem;"></span>
                    </div>

                    <button id="editBtn" class="button" style="width: 8rem;" onclick="showEditDialog()">Edit</button>

                </div>

                <div class="divider"></div>
            </div>

            <div class="flex-col section_4">
                <div class="flex-row justify-between">
                    <span class="font_3">Make goals</span>
                    <span id="open_create_dialog" class="font_3 text_3">+</span>
                </div>
                <div class="make-goals">
                    <div id="gym_list" class="flex-row justify-between items-center group_2">
                    </div>
                    <div class="divider"></div>
                </div>
            </div>



            <!-- Dialog-->
            <div id="overlay"></div>
            <div id="dialog" class="flex-col section_8">
                <div class="flex-row justify-between group_8">
                    <span id="dialog_title" class="font_3"></span>
                    <span id="close_dialog" class="font_3 text_8">x</span>
                </div>

                <!-- Frequency Radios -->
                <div class="flex-row group_9">
                    <label>
                        <input type="radio" name="frequency" value="DAILY"> Daily
                    </label>
                    <label>
                        <input type="radio" name="frequency" value="WEEKLY"> Weekly
                    </label>
                    <label>
                        <input type="radio" name="frequency" value="MONTHLY"> Monthly
                    </label>
                    <label>
                        <input type="radio" name="frequency" value="ANNUALLY"> Annually
                    </label>
                </div>
                <%--        input_name--%>
                <input id="input_name" style="width: 100%" type="text" placeholder="Name" class="section_10 input_2">
                <input id="input_calorie" style="width: 100%" type="text" placeholder="Calories burnt" class="section_10 input_2">
                <input id="input_time" style="width: 100%" type="text" placeholder="Time" class="section_10 input_2">
                <input id="input_steps" style="width: 100%" type="text" placeholder="Steps" class="section_10 input_2">

                <div id="dialog_button" class="flex-col justify-start items-center button_2 text-wrapper_5">
                    <span class="font_5">Create</span>
                </div>

            </div>

        </div>
    </div>
</div>
</body>
</html>









