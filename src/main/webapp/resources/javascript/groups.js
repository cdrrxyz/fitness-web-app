let dialog_flag = 1;
let group_list = [];
let choose_group_id = 3;
let default_user_id = 0;

function doCreate() {
    var selectedOptions = [default_user_id];
    $(".checkbox:checked").each(function() {
        selectedOptions.push($(this).val());
    });

    console.log(selectedOptions);
    const name = $("#input_name").val()
    const steps = $("#input_steps").val()
    const calorie = $("#input_calorie").val()
    const time = $("#input_time").val()

    const requestData = {
        "createdBy": default_user_id,
        "name": name,
        "steps": steps,
        "calorie": calorie,
        "time": time,
        "userIds": selectedOptions
    };

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/group/add",
        data: JSON.stringify(requestData),
        contentType: "application/json",
        success: function(response) {
            alert("Success!")
            hideDialog();
            refreshGroupList();
        },
        error: function(error) {
            console.error("POST 请求失败");
            console.error(error);
        }
    });
}

function doEdit() {
    const name = $("#input_name").val()
    const steps = $("#input_steps").val()
    const calorie = $("#input_calorie").val()
    const time = $("#input_time").val()
    const requestData = {
        "id": choose_group_id,
        "createdBy": default_user_id,
        "name": name,
        "steps": steps,
        "calorie": calorie,
        "time": time,
    };
    console.log(requestData);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/group/edit",
        data: JSON.stringify(requestData),
        contentType: "application/json",
        success: function(response) {
            alert("Success!")
            hideDialog();
            refreshGroupList();
        },
        error: function(error) {
            console.error("POST 请求失败");
            console.error(error);
        }
    });
}

function refreshChat(groupId) {
    $("#chat_div").show();
    choose_group_id = groupId;
    $.get("http://localhost:8080/chat/find/" + groupId, function(data) {
        console.log(data);
        const cGroup = findCurrentGroup(choose_group_id);
        console.log(cGroup);
        if (cGroup == null) return;

        $("#chat_group_title").text(cGroup.name);
        $("#chat_list").empty();
        data.forEach(function(chat) {
            let html = ``;
            console.log(chat.createdBy);
            console.log(default_user_id);
            if (String(chat.createdBy) === String(default_user_id)) {
                html += `
                  <div style="margin-top: 1rem" class="flex-row group_6">
                    <div style="flex: 1"></div>
                    <div style="flex: 0; padding-left: 1rem; padding-right: 0.5rem; white-space: nowrap" class="flex-col justify-start items-end text-wrapper_4 button">
                      <span class="font_4 text_6">${chat.message}</span>
                    </div>
                    <div style="background-color: burlywood"  class="shrink-0 section_6 view"></div>
                  </div>
                `;
            } else {
                html += `
                  <div class="flex-row group_4">
                    <div class="shrink-0 section_6"></div>
                    <div style="flex: 0; padding-left: 0.5rem; padding-right: 1rem; white-space: nowrap" class="flex-col justify-start items-start input text-wrapper_3">
                      <span class="font_4 text_5">${chat.message}</span>
                    </div>
                    <div style="flex: 1"></div>
                  </div>
                `;
            }
            $("#chat_list").append(html);
        });
    });
    deleteNotifications(groupId);
}

function refreshGroupList() {
    console.log("here");
    $.get("http://localhost:8080/group/all/" + default_user_id, function(data) {
        group_list= data;
        console.log(group_list);
        $("#group_list").empty();
        // 遍历返回的数据并渲染到容器中
        data.forEach(function(group) {
            var html = `
                    <div class="flex-row justify-between items-center group_2" onclick="refreshChat(${group.id})">
                        <div class="flex-col items-start space-y-11">
                            <span class="font_4">Name: ${group.name}</span>
                            <span class="font_4">Step: ${group.steps}</span>
                        </div>
                        <span class="text_4">Calorie: ${group.calorie}</span>
                    </div>
                    <div class="divider"></div>
                `;
            $("#group_list").append(html);
        });
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

function createNotificatons(response) {
    console.log(response.id);
    var requestData = {
        groupId: response.groupId,
        createdBy: response.createdBy,
        message: response.message
    };
    $.ajax({
        url: "http://localhost:8080/notification/add",
        type: "POST",
        data: JSON.stringify(requestData),
        contentType: "application/json",
        success: function(response) {
            console.log("Notifications set");
        }
    });
}

function deleteNotifications(groupId) {
    $.ajax({
        url: "http://localhost:8080/notification/delete/" + groupId + "/" + default_user_id,
        type: "GET",
        success: function(response) {
            console.log("Notifications deleted");
        }
    });
}

$(document).ready(function() {
    var article = document.querySelector("#current-user");
    default_user_id = article.dataset.currentUserId;
    console.log(default_user_id);

    refreshGroupList();

    // Dialog
    $("#close_dialog").click(function() {
        hideDialog();
    });
    // Create
    $("#open_create_dialog").click(function() {
        $("#dialog_title").text("Create group")
        $("#dialog_button").text("Create")
        $("#user_list").show();
        $("#dialog").toggle();
        $("#overlay").toggle();
        dialog_flag  = 0;
        $.get("http://localhost:8080/user/all", function(data) {
            $("#user_list").empty();
            data.forEach(function(user) {
                if (String(user.id) !== String(default_user_id)) {
                    var html = `
                        <div style="margin-top: 0.3rem" class="flex-row items-center">
                          <div class="section_11 section_6"></div>
                          <span style="margin-left: 0.3rem; flex: 1" class="font_4 text_9">${user.firstName} ${user.lastName}</span>
                          <input style="margin-left: auto" type="checkbox" class="checkbox" value="${user.id}">
                        </div>
                    `;
                    $("#user_list").append(html);
                }
            });
        });
    });
    // Edit
    $("#open_edit_dialog").click(function() {
        $("#dialog_title").text("Edit group")
        $("#dialog_button").text("Edit")
        $("#user_list").hide();
        $("#dialog").toggle();
        $("#overlay").toggle();
        dialog_flag  = 1;
        let currentGroup = findCurrentGroup(choose_group_id);
        if (currentGroup != null) {
            $("#input_name").val(currentGroup.name)
            $("#input_steps").val(currentGroup.steps)
            $("#input_calorie").val(currentGroup.calorie)
            $("#input_time").val(currentGroup.time)
        }
    });

    // Submit of Create Or Edit
    $("#dialog_button").click(function() {
        if (dialog_flag === 0) {
            doCreate();
        } else {
            doEdit();
        }
    });

    $("#btn_send").click(function () {
        var requestData = {
            groupId: choose_group_id,
            createdBy: default_user_id,
            message: $("#input_send").val()
        };
        console.log(requestData);

        $.ajax({
            url: "http://localhost:8080/chat/send",
            type: "POST",
            data: JSON.stringify(requestData),
            contentType: "application/json",
            success: function(response) {
                createNotificatons(response)
                refreshChat(choose_group_id);
            }
        });

    });
});