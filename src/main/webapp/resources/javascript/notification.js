let default_user_id = 0; // TODO: Make it logged in User-id
let group_list = [];

function refreshNotificationList() {
    let html = ``;
    $("#notification_list").empty();
    $("#notification_list").show();

    $.get("http://localhost:8080/notification/find/" + default_user_id, function (data) {

        data.forEach(function(notification) {
            $("#notification_list").empty();

            if (String(notification.messageAuthor) !== String(default_user_id)) {
                html += `
                        <div class="flex-row justify-between items-center section_4 view">
                          <div class="flex-col items-start space-y-11">
                            <span class="font_4">Group Name: ${notification.groupName}</span>
                            <span class="font_4">UserId: ${notification.messageAuthor}</span>
                            <span class="font_4">Message: ${notification.message}</span>
                          </div>
                          <span class="font_5"></span>
                        </div>
                    `;
                $("#notification_list").append(html);
            }
        });
    });
}

$(document).ready(function() {
    var article = document.querySelector("#current-user");
    default_user_id = article.dataset.currentUserId;
    console.log(default_user_id);
    $.get("http://localhost:8080/group/all/" + default_user_id, function(data) {
        group_list = data;
        refreshNotificationList();
    });
});