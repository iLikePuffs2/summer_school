package com.summer_school.dao;

import com.summer_school.pojo.dto.GradeDto;
import com.summer_school.pojo.dto.ProfessionDto;
import com.summer_school.pojo.dto.SignIn;
import com.summer_school.pojo.po.AbstractUser;
import com.summer_school.pojo.po.SignUpInfo;
import com.summer_school.pojo.po.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentDao {


    /**
     * 统计男生数量
     *
     * @return
     */
    @Select("select count(*) from student_table where gender = '男'")
    public Integer countBoy();

    /**
     * 统计女生数量
     *
     * @return
     */
    @Select("select count(*) from student_table where gender = '女'")
    public Integer countGirl();

    /**
     * 根据传入的学生类别名字计数
     *
     * @param studentType
     * @return
     */
    @Select("select count(*) from student_table where studentType = #{studentType}")
    public Integer countStudentType(String studentType);

    /**
     * 根据传入的年级计数
     * @param grade
     * @return
     */
    @Select("select count(*) as num from student_table where grade = #{grade}")
    public Integer countGrade(String grade);

    /**
     * 查询所有的专业名称和对应的人数
     *
     * @return
     */
    @Select("select profession,count(*) as num from student_table group by profession")
    public List<ProfessionDto> countAllProfession();

    /**
     * 查询所有不重复的专业名称
     * @return
     */
    @Select("select distinct profession from student_table")
    public String[] selectDistinctProfession();



    /**
     * 根据学校层次查询
     *
     * @param schoolLevel
     * @return
     */
    @Select("select count(*) from student_table where schoolLevel = #{schoolLevel}")
    public Integer countByLevel(String schoolLevel);

    /**
     * 根据政治面貌查询
     *
     * @param politicalStatus
     * @return
     */
    @Select("select count(*) from student_table where politicalStatus = #{politicalStatus}")
    public Integer countByPolitic(String politicalStatus);

    /**
     * 将清洗后的报名学生表的数据存入学生表(在实现类里要根据实际数据条数循环调用)
     *
     * @param student (清洗后的报名学生表的7项原始数据+分析得到的学校层次数据+教务管理员对应的暑期学校Id)
     * @return
     */
    @Insert("insert into student_table (summerSchoolId, identity, account, password, studentName, schoolLevel, schoolName, studentType, grade, profession, gender, politicalStatus) VALUES (#{summerSchoolId},#{identity},#{account},#{password},#{studentName},#{schoolLevel},#{schoolName},#{studentType},#{grade},#{profession},#{gender},#{politicalStatus})")
    public int insertSignUp(Student student);

    /**
     * 学生登陆
     *
     * @param signIn
     * @return
     */
    @Select("select * from student_table where account = #{account} and password = #{password}")
    public Student signIn(SignIn signIn);


    /**
     * 学生注册
     *
     * @param abstractUser
     * @return
     */
    @Insert("insert into student_sign_up_table(summerSchoolName, year, account, password, name, schoolName, studentType, grade, profession, gender, politicalStatus, photo) values (#{summerSchoolName},#{year},#{account},#{password},#{name},#{schoolName},#{studentType},#{grade},#{profession},#{gender},#{politicalStatus},#{photo})")
    public int signup(AbstractUser abstractUser);

    /**
     * 学生注册验证1（学生注册表里——防止重复注册）
     *
     * @param account
     * @return
     */
    @Select("select account from student_sign_up_table where account = #{account}")
    public String signUpCheck1(String account);

    /**
     * 学生注册验证2（学生表里——防止重复注册）
     *
     * @param account
     * @return
     */
    @Select("select account from student_table where account = #{account}")
    public String signUpCheck2(String account);


    /**
     * 查出所有学生注册申请表数据
     *
     * @return
     */
    @Select("select * from student_sign_up_table")
    public List<SignUpInfo> showSignUpInfo();

    /**
     * 查出所有学生表数据
     *
     * @return
     */
    @Select("select * from student_table")
    public List<Student> showStudentInfo();

    /**
     * 查出指定暑期学校id的所有学生表数据
     *
     * @return
     */
    @Select("select * from student_table where summerSchoolId = #{summerSchoolId}")
    public List<Student> showPartStudentInfo(Integer summerSchoolId);

    /**
     * 查出指定暑期学校编号的所有学生名字
     *
     * @return
     */
    @Select("select studentName from student_table where summerSchoolId = #{summerSchoolId}")
    public List<String> selectStudentName(Integer summerSchoolId);

    /**
     * 查出指定暑期学校编号的所有学校名称
     *
     * @return
     */
    @Select("select schoolName from student_table where summerSchoolId = #{summerSchoolId}")
    public List<String> selectSchoolName(Integer summerSchoolId);

    /**
     * 注册成功，为学生表的对应那一条添加账号和密码
     *
     * @param signUpInfo
     * @return
     */
    @Insert("insert into educational_administrator_table(summerSchoolId, identity, account, password, name, post, schoolName, academy) VALUES (#{summerSchoolId},#{identity},#{account},#{password},#{name},#{post},#{schoolName},#{academy})")
    public int signUpSuccess(SignUpInfo signUpInfo);


    /**
     * 审核后（无论成功与否），都删除教务管理员的这条注册申请
     *
     * @param identity
     * @param account
     * @return
     */
    @Delete("delete from teacher_educational_administrator_sign_up_table where identity = #{identity} and account = #{account}")
    public int deleteSignUp(String identity, String account);
}
