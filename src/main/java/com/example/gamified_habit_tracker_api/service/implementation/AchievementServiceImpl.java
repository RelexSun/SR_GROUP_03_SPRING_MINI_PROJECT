package com.example.gamified_habit_tracker_api.service.implementation;

import com.example.gamified_habit_tracker_api.config.AppConfig;
import com.example.gamified_habit_tracker_api.model.entities.Achievement;
import com.example.gamified_habit_tracker_api.model.entities.AppUser;
import com.example.gamified_habit_tracker_api.repository.AchievementRepository;
import com.example.gamified_habit_tracker_api.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {
        private final AchievementRepository achievementRepository;
        private final AppConfig appConfig;
        public AppUser getCurrentUser() {
                return (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        @Override
        public List<Achievement> getAllAchievement(Integer page, Integer size) {
                page = (page - 1) * size;
                return achievementRepository.getAllAchievement(page,size);
        }

        @Override
        public List<Achievement> getAchievementByUserId(Integer page, Integer size) {
                page = (page - 1) * size;
                AppUser appUser = getCurrentUser();
                switch (appUser.getXp()) {
                        case 50:
                                achievementRepository.saveUserIdAndAchievementId(appUser.getAppUserId(), achievementRepository.getAchievementIdByXp(50));
                                break;
                        case 100:
                                achievementRepository.saveUserIdAndAchievementId(appUser.getAppUserId(), achievementRepository.getAchievementIdByXp(100));
                                break;
                        case 200:
                                achievementRepository.saveUserIdAndAchievementId(appUser.getAppUserId(), achievementRepository.getAchievementIdByXp(200));
                                break;
                        case 300:
                                achievementRepository.saveUserIdAndAchievementId(appUser.getAppUserId(), achievementRepository.getAchievementIdByXp(300));
                                break;
                        case 500:
                                achievementRepository.saveUserIdAndAchievementId(appUser.getAppUserId(), achievementRepository.getAchievementIdByXp(500));
                                break;
                        case 1000:
                                achievementRepository.saveUserIdAndAchievementId(appUser.getAppUserId(), achievementRepository.getAchievementIdByXp(1000));
                                break;
                        case 5000:
                                achievementRepository.saveUserIdAndAchievementId(appUser.getAppUserId(), achievementRepository.getAchievementIdByXp(5000));
                                break;
                }


                return achievementRepository.getAchievementByUserId(page,size,appUser.getAppUserId());
        }
}
