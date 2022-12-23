package com.summer_school.service.data_cleaning;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 读取
 * 清洗
 * 存储
 */
public interface FormCleaningService {

    default boolean execute(){
        try {
            readToList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        clean();

        analyze();

        return save();
    }
    /**
     * 将表格里每列的数据读到一个list里
     */
    void readToList() throws Exception;

    /**
     * 调用相关清洗算法进行清洗
     */
    void clean();

    /**
     * 调用相关分析算法进行分析
     */
    void analyze();

    /**
     * 将数据存入数据库
     * @return
     */
    boolean save();

}
