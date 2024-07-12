-- PRE SCRIPTS
-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO db_elec_project.user (id, date_of_birth, email, first_name, last_name, password) VALUES (1, '2001-08-15', 'user@email.com', 'John', 'Smith', '$2a$10$8K.bWgHHZXCjHso2w5yv8uucKfIZuOiynpRjGLFuc.aPftvwrMulO');
INSERT INTO db_elec_project.user (id, date_of_birth, email, first_name, last_name, password) VALUES (2, '2001-06-20', 'user+1@email.com', 'Bob', 'Adams', '$2a$10$42LyzVhgZO3ao./fnPux.eV5tTnKS62cwOiThWPH9Dz9SJhokl.Dm');
INSERT INTO db_elec_project.user (date_of_birth, id, email, first_name, last_name, password) VALUES (null, 3, 'user+2@email.com', 'Alan', 'Fred', 'password');
INSERT INTO db_elec_project.user (date_of_birth, id, email, first_name, last_name, password) VALUES (null, 4, 'user+3@email.com', 'George', 'Russell', 'password');
update db_elec_project.user_seq set next_val=4 limit 1;

-- ----------------------------
-- Records of UserWorkout
-- ----------------------------
INSERT INTO user_workout (calories_burnt, duration, id, steps, user_id, weight, entry_time, achievement, workout_type) VALUES (450, 10, 4, 50, 3, 70, '2023-10-22 17:34:48', null, 'CYCLING');
INSERT INTO user_workout (calories_burnt, duration, id, steps, user_id, weight, entry_time, achievement, workout_type) VALUES (400, 30, 2, 20, 2, 70, '2023-10-21 17:34:40', null, 'WALKING');
INSERT INTO user_workout (calories_burnt, duration, id, steps, user_id, weight, entry_time, achievement, workout_type) VALUES (300, 20, 3, 15, 1, 65, '2023-10-22 17:34:46', null, 'RUNNING');
INSERT INTO user_workout (calories_burnt, duration, id, steps, user_id, weight, entry_time, achievement, workout_type) VALUES (200, 20, 1, 10, 1, 60, '2023-10-22 17:34:31', null, 'RUNNING');
update user_workout_seq set next_val=5 limit 1;

-- ----------------------------
-- Records of groups
-- ----------------------------
INSERT INTO db_elec_project.`groups` (create_date, created_by_user_id, id, calorie, name, steps, time) VALUES ('2023-10-05', 1, 1, '1111', 'Group1', '1111', '11');
INSERT INTO db_elec_project.`groups` (create_date, created_by_user_id, id, calorie, name, steps, time) VALUES ('2023-10-05', 1, 2, '22', 'Group2', '22', '22');
INSERT INTO db_elec_project.`groups` (create_date, created_by_user_id, id, calorie, name, steps, time) VALUES ('2023-10-05', 1, 3, '33', 'Group3', '33', '33');
update db_elec_project.groups_seq set next_val=4 limit 1;

-- ----------------------------
-- Records of group_member
-- ----------------------------
INSERT INTO db_elec_project.group_member (group_id, id, user_id) VALUES (1, 1, 1);
INSERT INTO db_elec_project.group_member (group_id, id, user_id) VALUES (1, 2, 2);
INSERT INTO db_elec_project.group_member (group_id, id, user_id) VALUES (1, 3, 3);
INSERT INTO db_elec_project.group_member (group_id, id, user_id) VALUES (1, 4, 4);
INSERT INTO db_elec_project.group_member (group_id, id, user_id) VALUES (2, 5, 1);
INSERT INTO db_elec_project.group_member (group_id, id, user_id) VALUES (2, 6, 2);
INSERT INTO db_elec_project.group_member (group_id, id, user_id) VALUES (2, 7, 3);
INSERT INTO db_elec_project.group_member (group_id, id, user_id) VALUES (3, 8, 1);
INSERT INTO db_elec_project.group_member (group_id, id, user_id) VALUES (3, 9, 3);
INSERT INTO db_elec_project.group_member (group_id, id, user_id) VALUES (3, 10, 4);
update db_elec_project.group_member_seq set next_val=11 limit 1;

-- ----------------------------
-- Records of Goals
-- ----------------------------
INSERT INTO db_elec_project.goals (id, calorie, created_by_user_id, frequency, group_id, group_name, steps, time) VALUES (1, '10000', 1, 1, 1, 'Group1', '10000', '60mins');
INSERT INTO db_elec_project.goals (id, calorie, created_by_user_id, frequency, group_id, group_name, steps, time) VALUES (2, '1000', 1, 0, 2, 'Group2', '1000', '30mins');
update db_elec_project.goals_seq set next_val=3 limit 1;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO db_elec_project.group_messages (created_by_user_id, created_date, group_id, id, message) VALUES (3, '2023-10-05', 3, 3, 'Hello Hello');
INSERT INTO db_elec_project.group_messages (created_by_user_id, created_date, group_id, id, message) VALUES (3, '2023-10-05', 1, 4, 'Hello Hello');
INSERT INTO db_elec_project.group_messages (created_by_user_id, created_date, group_id, id, message) VALUES (1, '2023-10-05', 1, 1, 'Hello');
INSERT INTO db_elec_project.group_messages (created_by_user_id, created_date, group_id, id, message) VALUES (1, '2023-10-05', 1, 2, 'Anybody Here?');
update db_elec_project.group_messages_seq set next_val=5 limit 1;


-- ----------------------------
-- Records of Post
-- ----------------------------
INSERT INTO db_elec_project.post (id, likes, sent_at, user_id, category, content, title) VALUES (1, 0, '2023-10-15', 1, 'running', 'test text', 'test ');
INSERT INTO db_elec_project.post (id, likes, sent_at, user_id, category, content, title) VALUES (2, 0, '2023-10-15', 2, 'boxing', 'testing', 'yo');
INSERT INTO db_elec_project.post (id, likes, sent_at, user_id, category, content, title) VALUES (3, 10, '2023-10-15', 3, 'running', 'this is a test', 'blah');
update db_elec_project.post_seq set next_val=4 limit 1;


-- ----------------------------
-- Records of Comment
-- ----------------------------
INSERT INTO db_elec_project.comment (id, post_id, sent_at, user_id, content) VALUES (1, 1, '2023-10-15', 1, 'test');
INSERT INTO db_elec_project.comment (id, post_id, sent_at, user_id, content) VALUES (2, 2, '2023-10-15', 2, 'comment blah');
INSERT INTO db_elec_project.comment (id, post_id, sent_at, user_id, content) VALUES (3, 2, '2023-10-15', 3, 'reply blah');
INSERT INTO db_elec_project.comment (id, post_id, sent_at, user_id, content) VALUES (4, 3, '2023-10-15', 2, 'cool');
update db_elec_project.comment_seq set next_val=5 limit 1;


-- ----------------------------
-- Records of Equipment
-- ----------------------------
insert into db_elec_project.equipment (id, description, imgurl, name, video_id)
values (1,
        'The treadmill is a widely-used fitness device that simulates walking and running indoors. It features a continuously moving belt and adjustable speeds, allowing for customizable workouts. Ideal for cardio exercise, it tracks distance, speed, time, and calories burned, offering users a convenient and efficient way to stay active.',
        'treadmill',
        'Treadmill',
        '8i3Vrd95o2k');
insert into db_elec_project.equipment (id, description, imgurl, name, video_id)
values (2,
        'A barbell is a long metal bar used for weightlifting and bodybuilding. With adjustable weight plates on either end, it''s versatile for various strength training exercises. From bench presses to deadlifts, the barbell caters to both beginners and seasoned athletes, promoting muscle growth and endurance.',
        'barbell',
        'Barbell',
        'hJDqffL1EmQ');
insert into db_elec_project.equipment (id, description, imgurl, name, video_id)
values (3,
        'The spinning bike, often seen in cycling classes, is a stationary exercise bike designed for intense cardio workouts. With adjustable resistance and seat heights, it simulates various terrains, allowing users to challenge themselves. It offers a full-body workout, enhancing cardiovascular health and burning calories.',
        'spinning_bike',
        'Spinning Bike',
        'udhRFATkN44');
insert into db_elec_project.equipment (id, description, imgurl, name, video_id)
values (4,
        'The squat rack is a sturdy frame that supports weightlifters during squats or other heavy lifts. With adjustable safety bars, it ensures user safety while allowing for varied training regimes. Essential for building lower body strength, it''s a staple in gyms and home workout setups.',
        'squat_rack',
        'Squat Rack',
        'kaO7Yw1hlcM');
insert into db_elec_project.equipment (id, description, imgurl, name, video_id)
values (5,
        'An effective full-body workout tool, the rowing machine mimics watercraft rowing action. Engaging both upper and lower body muscles, it provides excellent cardiovascular benefits while promoting strength and endurance. Its low-impact nature ensures joint safety, making it a preferred choice for fitness enthusiasts of all ages.',
        'rowing',
        'Rowing Machine',
        '6_eLpWiNijE');
insert into db_elec_project.equipment (id, description, imgurl, name, video_id)
values (6,
        'The leg press machine targets the major leg muscles, offering both strength and toning benefits. Users sit and push weight plates using their legs, adjusting the weight as needed. With a guided motion, it ensures proper form and minimizes injury risk, making it ideal for all fitness levels.',
        'leg_press',
        'Leg Press Machine',
        'p5dCqF7wWUw');
update db_elec_project.equipment_seq set next_val=7 limit 1;


-- ----------------------------
-- Records of Training
-- ----------------------------
insert into db_elec_project.training (id, description, title, type, video_id)
values (1,
        'Transform your body in just 10 weeks and take part in the entire Body Project system!',
        'Advanced fat burning HIIT cardio workout - 30 mins.',
        'advanced',
        'yplP5cLuyf4');
insert into db_elec_project.training (id, description, title, type, video_id)
values (2,
        'Lets gooooooo! Another perfect 20 MINUTE WORKOUT FOR BEGINNERS! Certainly for gain goals!',
        'PERFECT 20 MIN FULL BODY WORKOUT FOR BEGINNERS (No Equipment)',
        'beginner',
        'iCQ2gC4DqJw');
insert into db_elec_project.training (id, description, title, type, video_id)
values (3,
        'Throughout this workout we will show you what muscles are working, how to do each exercise, and give you audio cues  to make sure your form is correct.',
        '30 Minute Full Body Beginner Dumbbell Workout [With Modifications]',
        'beginner',
        'GY1JhB9BEkk');
insert into db_elec_project.training (id, description, title, type, video_id)
values (4,
        'Do This Every Morning To Feel Fit! it''s a twenty minute full body workout that you can do ANYWHERE and at ANYTIME!',
        '20 MIN FULL BODY WORKOUT (Intense Routine, No Equipment)',
        'intermediate',
        'bTo4NrSriWw');
update db_elec_project.training_seq set next_val=5 limit 1;
