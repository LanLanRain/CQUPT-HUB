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
        if (log.isInfoEnabled()){
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
        return 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public List<SubjectCategory> queryCategory(SubjectCategory subjectCategory) {
        return List.of();
    }

    @Override
    public Integer querySubjectCount(Long id) {
        return 0;
    }
}
