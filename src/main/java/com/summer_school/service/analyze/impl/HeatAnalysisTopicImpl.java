package com.summer_school.service.analyze.impl;

import com.summer_school.dao.TopicDao;
import com.summer_school.pojo.dto.TopicDto;
import com.summer_school.service.analyze.HeatAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeatAnalysisTopicImpl implements HeatAnalysisService {
    @Autowired
    TopicDao topicDao;

    @Override
    public List<TopicDto> show() {

        List<TopicDto> dtoList = topicDao.selectHeat();

        int averageParticipant = 0;
        double averageActiveRate = 0;
        double maxActiveRate = 0;

        //对人数和积极参与率取均值,找出积极率最大值
        for (int i = 0; i < dtoList.size(); i++) {
            averageParticipant += dtoList.get(i).getParticipantNum();
            averageActiveRate += dtoList.get(i).getActiveRate();

            if (dtoList.get(i).getActiveRate() > maxActiveRate) {
                maxActiveRate = dtoList.get(i).getActiveRate();
            }
        }
        averageParticipant = averageParticipant / dtoList.size();
        averageActiveRate = averageActiveRate / dtoList.size();


        int participantScore = 0;
        int activeScore = 0;
        int heatScore = 0;
        //计算各自的总人数评分和积极率评分,得到热度评分
        for (int i = 0; i < dtoList.size(); i++) {

            participantScore = calculateParticipationScore(dtoList.get(0).getParticipantNum(), averageParticipant, dtoList.get(i).getParticipantNum());
            activeScore = calculateActiveRateScore(maxActiveRate, averageActiveRate, dtoList.get(i).getActiveRate());

            heatScore = (int) (participantScore * 0.7 + activeScore * 0.3);
            dtoList.get(i).setHeatScore(heatScore);
        }

        return dtoList;

    }


    //计算总人数评分
    int calculateParticipationScore(int max, int average, int current) {
        int score = 0;

        //计算0到平均值的斜率k1
        double k1 = 50 / (double) average;

        //计算平均值到最大值的斜率k2
        double k2 = 50 / (double) (max - average);

        if (current <= average) {
            score = (int) (current * k1);
        } else {
            score = 50 + (int) ((current - average) * k2);
        }

        return score;
    }

    //计算积极性评分
    int calculateActiveRateScore(double max, double average, double current) {
        int score = 0;

        //计算0到平均值的斜率k1
        double k1 = 50 / average;

        //计算平均值到最大值的斜率k2
        double k2 = 50 / (max - average);

        if (current <= average) {
            score = (int) (current * k1);
        } else {
            score = 50 + (int) ((current - average) * k2);
        }

        return score;
    }
}
