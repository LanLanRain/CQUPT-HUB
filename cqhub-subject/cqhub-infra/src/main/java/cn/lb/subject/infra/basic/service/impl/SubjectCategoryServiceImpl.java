package cn.lb.subject.infra.basic.service.impl;

import cn.lb.subject.infra.basic.entity.SubjectCategory;
import cn.lb.subject.infra.basic.mapper.SubjectCategoryDao;
import cn.lb.subject.infra.basic.service.SubjectCategoryService;
import com.alibaba.druid.support.json.JSONUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 题目分类(SubjectCategory)表服务实现类
 *
 * @author RainSoul
 * @create 2024-09-05
 */
@Slf4j
@Service
public class SubjectCategoryServiceImpl implements SubjectCategoryService {

    @Resource
    private SubjectCategoryDao subjectCategoryDao;


    /**
     * 插入一个新的学科类别
     *
     * @param subjectCategory 要插入的学科类别对象
     * @return 插入后的学科类别对象
     */
    @Override
    public SubjectCategory insert(SubjectCategory subjectCategory) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.add.subjectCategory:{}", JSONUtils.toJSONString(subjectCategory));
        }

        subjectCategoryDao.insert(subjectCategory);

        return subjectCategory;
    }

    @Override
    public SubjectCategory queryById(Long id) {
        return null;
    }

    @Override
    public int update(SubjectCategory subjectCategory) {
        return subjectCategoryDao.update(subjectCategory);
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    /**
     * 根据条件查询主题分类列表
     *
     * @param subjectCategory 查询条件，包含要查询的主题分类的相关信息
     * @return 返回一个包含所有匹配给定查询条件的主题分类的列表
     */
    @Override
    public List<SubjectCategory> queryCategory(SubjectCategory subjectCategory) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.queryCategory.subjectCategory:{}", JSONUtils.toJSONString(subjectCategory));
        }
        List<SubjectCategory> subjectCategoryList = subjectCategoryDao.queryCategory(subjectCategory);
        return subjectCategoryList;
    }

    @Override
    public Integer querySubjectCount(Long id) {
        return 0;
    }
}
