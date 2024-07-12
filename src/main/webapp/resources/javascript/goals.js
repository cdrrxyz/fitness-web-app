let dialog_flag = 1;
let group_list = [];
let currentGoals = null;
let currentGroup = null;
let choose_group_id = 3;
let choose_group_name = "Group 3";
let choose_group_date = "2023-10-16";
let default_user_id = 0;

function doCreate() {
    var selectedOptions = [default_user_id];
    $(".checkbox:checked").each(function () {
        selectedOptions.push($(this).val());
    });

    console.log(selectedOptions);
    const steps = $("#input_steps").val();
    const calorie = $("#input_calorie").val();
    const time = $("#input_time").val();

    const frequency = $("input[name='frequency']:checked").val();

    const requestData = {
        "createdBy": default_user_id,
        "groupId": currentGroup.id,
        "steps": steps,
        "calorie": calorie,
        "time": time,
        "frequency": frequency
    };
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/goals/add",
        data: JSON.stringify(requestData),
        contentType: "application/json",
        success: function (response) {
            alert("Success!");
            hideDialog();
            refreshGoalsList();
        },
        error: function (error) {
            console.error("POST 请求失败");
            console.error(error);
        }
    });
}


function doEdit() {

    const steps = $("#input_steps").val();
    const calorie = $("#input_calorie").val();
    const time = $("#input_time").val();

    const frequency = $("input[name='frequency']:checked").val();

    const requestData = {
        "id": currentGoals.id,
        "createdBy": currentGoals.createdBy,
        "groupId": currentGoals.groupId,
        "steps": steps,
        "calorie": calorie,
        "time": time,
        "frequency": frequency
    };

    console.log(requestData);

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/goals/edit",
        data: JSON.stringify(requestData),
        contentType: "application/json",
        success: function (response) {
            alert("Success!");
            hideDialog();
            refreshGoalsList();
        },
        error: function (error) {
            console.error("POST 请求失败");
            console.error(error);
        }
    });
}




function updateGoals(goals) {
    if (typeof group === 'string') {
        try {
            group = JSON.parse(group);
        } catch (error) {
            console.error("Error parsing group:", error);
            return;
        }
    }

    // 确保此时的group是一个对象
    if (typeof group !== 'object' || group === null) {
        console.error("Invalid group:", group);
        return;
    }


    $("#input_steps").val(group.steps);
    $("#input_calorie").val(group.calorie);
    $("#input_time").val(group.time);
    $("input[name='frequency'][value='" + group.frequency + "']").prop("checked", true);

    choose_group_id = group.id;
    choose_group_name = group.name;
    choose_group_date = group.createDate;

    var goalsHtml = `
        <span class="font_3" style="margin-left: 2rem;">${group.name}</span>
        <span class="font_3" style="margin-right: 4rem; margin-left: 2rem;">${group.steps} times per week</span>
    `;

    $('#goals_container').html(goalsHtml);
}

function editGoalsFromButton(buttonElement) {
    var goalsString = buttonElement.getAttribute('data-goals');
    var goals = JSON.parse(goalsString);
    currentGoals = goals;

//    更新dialog中的内容
    $("#dialog_title").text(currentGoals.groupName)
    $("#dialog_button").text("Edit")
    // 隐藏input_name
    $("#input_name").hide();
    $("#user_list").hide();
    $("#dialog").toggle();
    $("#overlay").toggle();
    dialog_flag = 1;
    if (currentGoals != null) {

        $("#input_name").val(currentGoals.groupName)
        $("#input_steps").val(currentGoals.steps)
        $("#input_calorie").val(currentGoals.calorie)
        $("#input_time").val(currentGoals.time)
        $("input[name='frequency'][value='" + goals.frequency + "']").prop("checked", true);

    }
//    展示dialog
    $("#dialog").show();
    $("#overlay").show();
}

function createGoalsFromButton(buttonElement) {
    var groupString = buttonElement.getAttribute('data-group');
    var group = JSON.parse(groupString);

    currentGroup = group;
    console.log(currentGroup);
    $("#dialog_title").text(currentGroup.name)
    $("#dialog_button").text("Create")

    if (currentGroup != null) {
        $("#input_name").val(group.name)
        $("#input_steps").val('')
        $("#input_calorie").val('')
        $("#input_time").val('')
        $("input[name='frequency']").prop("checked", false);
        // 隐藏input_name
        $("#input_name").hide();
        $("#user_list").hide();
        $("#dialog").toggle();
        $("#overlay").toggle();
        dialog_flag = 0;
    }
    $("#dialog").show();
    $("#overlay").show();
}


function refreshGoalsList() {
    $.get("http://localhost:8080/goals/byCreator/" + default_user_id, function (data) {
        goals_list = data;
        console.log(goals_list);
        $("#goals_container").empty();
        data.forEach(function (goals) {

            var goalHtml = `
                <div class="flex-row justify-between items-center group_2" style="border: 2px solid #6F4E37;">
                    <!-- 图标 -->
                    <img src="/resources/images/avater.png" alt="Avatar" class="section_3">
    
                    <!-- 描述部分 -->
                    <div class="flex-row items-center" style="flex-grow: 2;">
                        <span class="font_3" style="margin-left: 2rem;">${goals.groupName}</span>
                        <span class="font_3" style="margin-right: 4rem; margin-left: 2rem;">Exercise ${goals.time} ${goals.frequency}</span>  <!-- Assuming you want to display the goal's ID here -->
                    </div>
    
                    <!-- 编辑按钮 -->
                    <button class="button" data-goals='${JSON.stringify(goals)}' style="width: 8rem;" onclick="editGoalsFromButton(this)">Edit</button>
                </div>
                
                <div class="divider"></div>
            `;

            $("#goals_container").append(goalHtml);

        });
    });
}


function refreshGroupList() {
    console.log("here");
    $.get("http://localhost:8080/group/all/" + default_user_id, function (data) {
        group_list = data;
        console.log(group_list);
        $("#gym_list").empty();
        data.forEach(function (group) {
            var html2 = `
            <div class="flex-row items-center space-y-11" style="align-items: center;">
                <img src="/resources/images/avater.png" alt="Avatar" class="section_3" style="width: 60px; height: 60px;">
                <span class="font_4" data-group='${JSON.stringify(group)}' onclick="createGoalsFromButton(this)" style="margin-left: 10px;">
                    ${group.name}
                </span>
                
                <div class="divider"></div>
            </div>

            `;
            $("#gym_list").append(html2);
        });
        //html3只是用来占位的
        var html3 = `
                    <div class="flex-col items-start space-y-11">
                                        <span class="font_4"></span>
                                        <div class="divider"></div>
                                    </div>
                `;
        $("#gym_list").append(html3);
    });
}


function hideDialog() {
    $("#dialog").hide();
    $("#overlay").hide();
}

function findCurrentGroup(groupId) {
    let result = null;
    group_list.forEach(function (g) {
        if (g.id === choose_group_id) {
            result = g;
        }
    });
    return result;
}

$(document).ready(function () {
    var article = document.querySelector("#current-user");
    default_user_id = article.dataset.currentUserId;
    console.log(default_user_id);
    refreshGroupList();
    refreshGoalsList();
    // Dialog
    $("#close_dialog").click(function () {
        hideDialog();
    });
    // Submit of Create Or Edit
    $("#dialog_button").click(function () {
        if (dialog_flag === 0) {
            doCreate();
        } else {
            doEdit();
        }
    });


});