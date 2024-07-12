let default_user_id = 0;

function sortObj(obj) {
    return Object.keys(obj).sort().reduce(function (result, key) {
        result[key] = obj[key];
        return result;
    }, {});
}

function refreshUserWorkoutList() {
    $.get("http://localhost:8080/leaderboard/topThree", function (data) {
        const workout_list = data;
        let ordered = sortObj(workout_list);
        console.log(ordered);
        $("#workout_container").empty();
        for (const property in ordered) {
            console.log(property);
            var goalHtml = `
                <div class="flex-row justify-between items-center group_2" style="border: 2px solid #6F4E37;">
                    <div class="flex-row items-center space-y-11" style="align-items: center;">
                        <img src="/resources/images/avater.png" alt="Avatar" class="section_3" style="width: 60px; height: 60px;">
                        <span class="font_4"  style="margin-left: 10px;">
                            ${property.substring(1)}
                        </span>
                        <div class="divider"></div>
                    </div>
                    
                    <!-- 描述部分 -->
                    <div class="flex-col items-center" style="flex-grow: 3;">
                        <span class="font_3" style="margin-left: 1rem; margin-bottom: 0.5rem;">Calories</span>
                        <span class="font_4" style="margin-right: 1rem;">${ordered[property]}</span>  <!-- Assuming you want to display the goal's ID here -->
                    </div>
                </div>
                
                <div class="divider"></div>
            `;

            $("#workout_container").append(goalHtml);
        }
    });
}

$(document).ready(function () {
    var article = document.querySelector("#current-user");
    default_user_id = article.dataset.currentUserId;
    console.log(default_user_id);
    // 发起 GET 请求获取数据
    refreshUserWorkoutList();

});