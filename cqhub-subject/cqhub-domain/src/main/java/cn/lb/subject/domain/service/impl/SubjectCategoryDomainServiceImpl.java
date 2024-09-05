package cn.lb.subject.domain.service.impl;

import cn.lb.subject.common.enums.IsDeletedFlagEnum;
import cn.lb.subject.domain.convert.SubjectCategoryConverter;
import cn.lb.subject.domain.entity.SubjectCategoryBO;
import cn.lb.subject.domain.service.SubjectCategoryDomainService;
import cn.lb.subject.infra.basic.entity.SubjectCategory;
import cn.lb.subject.infra.basic.service.SubjectCategoryService;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位大类领域服务实现
 *
 * @author RainSoul
 * @create 2024-09-05
 */
@Slf4j
@Service
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Resource
    private SubjectCategoryService subjectCategoryService;

    /**
     * 添加学科分类
     *
     * @param subjectCategoryBO 学科分类的业务对象
     */
    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBOTOEntity(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        subjectCategoryService.insert(subjectCategory);
    }


    /**
     * 查询学科分类
     *
     * @param subjectCategoryBO 学科分类的业务对象，用于查询条件的封装
     * @return 返回一个列表，包含符合条件的SubjectCategoryBO对象，每个对象代表一个学科分类
     */
    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBOTOEntity(subjectCategoryBO);
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryConverter.INSTANCE.convertCategoryListTOBO(subjectCategoryList);

        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.queryPrimaryCategory.boList:{}", JSON.toJSONString(subjectCategoryBOList));
        }

        subjectCategoryBOList.forEach(bo -> {
            // 调用服务层方法，根据学科分类的ID查询该分类下的学科数量
            bo.setCount(subjectCategoryService.querySubjectCount(bo.getId()));
        });

        return subjectCategoryBOList;
    }


    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        return null;
    }

    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        return null;
    }

    @Override
    public List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO) {
        return List.of();
    }
}
