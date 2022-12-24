package com.summer_school.service.data_cleaning;

import com.summer_school.pojo.dto.CleanSignUp;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 读取
 * 清洗
 * 存储
 */
public interface FormCleaningService {

    @Transactional(rollbackFor = Exception.class)
    default boolean execute(CleanSignUp cleanSignUp){
        try {
            readToList(cleanSignUp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        clean();

        analyze();

        return save(cleanSignUp);
    }
    /**
     * 将表格里每列的数据读到一个list里
     * 传入参数为excel表格文件的URL
     */
    void readToList(CleanSignUp cleanSignUp) throws Exception;

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

    boolean save(CleanSignUp cleanSignUp);

}
