package cn.lb.subject.infra.basic.mapper;

import cn.lb.subject.infra.basic.entity.SubjectInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目信息表(SubjectInfo)表数据库访问层
 *
 * @author RainSoul
 * @create 2024-09-05
 */
public interface SubjectInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id
     * @return
     */
    SubjectInfo queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param subjectInfo
     * @return
     */
    List<SubjectInfo> queryAllByLimit(SubjectInfo subjectInfo);

    /**
     * 统计总行数
     *
     * @param subjectInfo
     * @return
     */
    long count(SubjectInfo subjectInfo);

    /**
     * 新增数据
     *
     * @param subjectInfo
     * @return
     */
    int insert(SubjectInfo subjectInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities
     * @return
     */
    int insertBatch(@Param("entities") List<SubjectInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectInfo> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SubjectInfo> entities);

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 影响行数
     */
    int update(SubjectInfo subjectInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    int countByCondition(@Param("subjectInfo") SubjectInfo subjectInfo,
                         @Param("categoryId") Long categoryId,
                         @Param("labelId") Long labelId);

    List<SubjectInfo> queryPage(@Param("subjectInfo") SubjectInfo subjectInfo,
                                @Param("categoryId") Long categoryId,
                                @Param("labelId") Long labelId,
                                @Param("start") int start,
                                @Param("pageSize") Integer pageSize);

    /**
     * 查询贡献者数量
     *
     * @return
     */
    List<SubjectInfo> getContributeCount();

    Long querySubjectIdCursor(@Param("subjectId") Long subjectId,
                              @Param("categoryId") Long categoryId,
                              @Param("labelId") Long labelId,
                              @Param("cursor") int cursor);


}
