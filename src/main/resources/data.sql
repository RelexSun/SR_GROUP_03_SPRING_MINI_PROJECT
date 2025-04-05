SELECT * FROM app_users;

SELECT * FROM app_users WHERE username = 'string1';

INSERT INTO achievements (title, description, badge, xp_required)
VALUES
    ('First Habit Completed', 'Awarded when a user completes their first habit.', 'first_habit_badge.png', 50),
    ('7-Day Streak', 'Awarded for completing a habit for 7 consecutive days.', '7_day_streak_badge.png', 100),
    ('30-Day Streak', 'Awarded for completing a habit for 30 consecutive days.', '30_day_streak_badge.png', 200),
    ('Habit Master', 'Awarded for completing 10 different habits.', 'habit_master_badge.png', 500),
    ('Perfect Month', 'Awarded for completing a habit every day in a given month.', 'perfect_month_badge.png', 300),
    ('XP Novice', 'Awarded for earning your first 100 XP.', 'xp_novice_badge.png', 100),
    ('XP Champion', 'Awarded for earning 500 XP in total.', 'xp_champion_badge.png', 500),
    ('XP Overlord', 'Awarded for earning 5000 XP in total.', 'xp_overlord_badge.png', 5000),
    ('7-Day Streak Achievement', 'Awarded when a user completes a habit for 7 consecutive days.', '7_day_streak_achievement.png', 50),
    ('Level 10 Reached', 'Awarded when a user reaches level 10.', 'level_10_badge.png', 1000);