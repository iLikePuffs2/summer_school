package com.summer_school.service.data_cleaning.impl;

import com.summer_school.dao.HotSpotDao;
import com.summer_school.dao.TopicDao;
import com.summer_school.pojo.dto.CleanInfo;
import com.summer_school.pojo.po.HotSpot;
import com.summer_school.pojo.po.Topic;
import com.summer_school.service.data_cleaning.FillInDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FillInDataServiceImpl implements FillInDataService {
    /**
     * 将数据存入主题表+研究热点表
     * 填写日期(后端自动生成)、暑期学校id、第几天、主题名字、研究热点名字、研究热点会议时间、会议图片
     *
     * @param cleanInfo
     * @return
     */
    @Autowired
    HotSpotDao hotSpotDao;

    @Autowired
    TopicDao topicDao;

    @Override
    public boolean save(CleanInfo cleanInfo) {

        //自动生成当天日期
        String time = cleanInfo.getHotSpotStartTime()[0];
        time = time.split(" ")[0];

        //统计受影响的行数
        int row = 0;

        //存入主题表
        Topic topic = new Topic();
        topic.setName(cleanInfo.getTopicName());
        topic.setDate(time);
        topic.setSummerSchoolId(cleanInfo.getSummerSchoolId());
        topic.setNumDay(cleanInfo.getNumDay());


        //防止主题表插入重复内容
        Topic topicTemp = topicDao.selectBySummerIdAndNumDay(cleanInfo.getSummerSchoolId(), cleanInfo.getNumDay());
        if (topicTemp == null) {
            topicDao.add(topic);
            row++;
        }


        //得到刚刚存入/之前就有的主题表的数据所生产的主题id
        Integer TopicId = topicDao.selectId(cleanInfo.getTopicName(), cleanInfo.getSummerSchoolId());

        //存入研究热点表
        for (int i = 0; i < cleanInfo.getHotSpotName().length; i++) {
            HotSpot hotSpot = new HotSpot();
            hotSpot.setName(cleanInfo.getHotSpotName()[i]);
            hotSpot.setTopicId(TopicId);
            hotSpot.setStartTime(cleanInfo.getHotSpotStartTime()[i]);
            hotSpot.setEndTime(cleanInfo.getHotSpotEndTime()[i]);
            hotSpot.setMeetingImg(cleanInfo.getPicUrl()[i]);

            //防止插入重复内容
            List<HotSpot> hotSpotList = hotSpotDao.selectByTopicId(TopicId);
            if (hotSpotList == null) {
                hotSpotDao.add(hotSpot);
                row++;
            } else {
                boolean flag = true;
                for (int j = 0; j < hotSpotList.size(); j++) {
                    if (hotSpotList.get(j).getStartTime().equals(hotSpot.getStartTime())) {
                        flag = false;
                    }
                }
                //如果这个研究热点的起始时间和数据库里这个主题下的研究热点的起始时间都不同，说明是补充的，这才可以插入
                if (flag) {
                    hotSpotDao.add(hotSpot);
                    row++;
                }
            }
        }

        return row == (cleanInfo.getHotSpotName().length + 1) ? true : false;
    }
}
