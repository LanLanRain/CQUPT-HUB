package cn.lb.subject.domain.service.impl;

import cn.lb.subject.common.enums.IsDeletedFlagEnum;
import cn.lb.subject.domain.convert.SubjectCategoryConverter;
import cn.lb.subject.domain.entity.SubjectCategoryBO;
import cn.lb.subject.domain.service.SubjectCategoryDomainService;
import cn.lb.subject.infra.basic.entity.SubjectCategory;
import cn.lb.subject.infra.basic.service.SubjectCategoryService;
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

    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertDTOTOBO(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        subjectCategoryService.insert(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        return List.of();
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
