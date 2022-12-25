package com.summer_school.service.data_cleaning;

import com.summer_school.pojo.dto.CleanInfo;
import org.springframework.transaction.annotation.Transactional;

/**
 * 读取
 * 清洗
 * 存储
 */
public interface FormCleaningService {

    @Transactional(rollbackFor = Exception.class)
    default boolean execute(CleanInfo cleanInfo){
        try {
            readToList(cleanInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        clean();

        analyze(cleanInfo);

        return save(cleanInfo);
    }
    /**
     * 将表格里每列的数据读到一个list里
     * 传入参数为excel表格文件的URL
     */
    void readToList(CleanInfo cleanInfo) throws Exception;

    /**
     * 调用相关清洗算法进行清洗
     */
    void clean();

    /**
     * 调用相关分析算法进行分析
     */
    void analyze(CleanInfo cleanInfo);

    /**
     * 将数据存入数据库
     * @return
     */

    boolean save(CleanInfo cleanInfo);

}

/**
 * 清空数据库表
 * SET FOREIGN_KEY_CHECKS=0;
 * truncate table student_table;
 * SET FOREIGN_KEY_CHECKS=1;
 */