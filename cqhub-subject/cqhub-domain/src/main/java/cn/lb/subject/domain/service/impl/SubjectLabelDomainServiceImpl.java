package cn.lb.subject.domain.service.impl;

import cn.lb.subject.common.enums.CategoryTypeEnum;
import cn.lb.subject.common.enums.IsDeletedFlagEnum;
import cn.lb.subject.domain.convert.SubjectLabelConverter;
import cn.lb.subject.domain.entity.SubjectLabelBO;
import cn.lb.subject.domain.service.SubjectLabelDomainService;
import cn.lb.subject.infra.basic.entity.SubjectCategory;
import cn.lb.subject.infra.basic.entity.SubjectLabel;
import cn.lb.subject.infra.basic.entity.SubjectMapping;
import cn.lb.subject.infra.basic.service.SubjectCategoryService;
import cn.lb.subject.infra.basic.service.SubjectLabelService;
import cn.lb.subject.infra.basic.service.SubjectMappingService;
import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author RainSoul
 * @create 2024-09-06
 */
@Slf4j
@Service
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {


    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Override
    public Boolean add(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToEntity(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        int count = subjectLabelService.insert(subjectLabel);
        return count > 0;
    }

    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.update.bo:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToEntity(subjectLabelBO);
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    @Override
    public Boolean delete(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.update.bo:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToEntity(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.DELETE.getCode());
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    /**
     * 根据分类ID查询标签
     *
     * @param subjectLabelBO
     * @return
     */
    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
        // 根据分类ID查询对应的标签
        // 如果当前分类为1级分类，则查询所有标签
        SubjectCategory subjectCategory = subjectCategoryService.queryById(subjectLabelBO.getCategoryId());
        if(CategoryTypeEnum.PRIMARY.getCode() == subjectCategory.getCategoryType()){
            // 创建SubjectLabel对象并设置分类ID，用于查询
            SubjectLabel subjectLabel = new SubjectLabel();
            subjectLabel.setCategoryId(subjectLabelBO.getCategoryId());
            // 查询符合条件的标签列表
            List<SubjectLabel> labelList = subjectLabelService.queryByCondition(subjectLabel);
            // 将查询结果转换为Business Object列表并返回
            List<SubjectLabelBO> labelResultList = SubjectLabelConverter.INSTANCE.convertLabelToBoList(labelList);
            return labelResultList;
        }
        // 获取当前分类ID
        Long categoryId = subjectLabelBO.getCategoryId();
        // 创建SubjectMapping对象并设置分类ID和删除状态，用于查询映射关系
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryId);
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        // 查询标签与分类的映射关系列表
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        // 如果映射关系列表为空，则返回空列表
        if (CollectionUtils.isEmpty(mappingList)) {
            return Collections.emptyList();
        }
        // 从映射关系列表中提取标签ID列表
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        // 根据标签ID列表批量查询标签信息
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
        // 创建Business Object列表用于存储结果
        List<SubjectLabelBO> boList = new LinkedList<>();
        // 遍历标签列表，转换并添加到BO列表
        labelList.forEach(label -> {
            SubjectLabelBO bo = new SubjectLabelBO();
            bo.setId(label.getId());
            bo.setLabelName(label.getLabelName());
            bo.setCategoryId(categoryId);
            bo.setSortNum(label.getSortNum());
            boList.add(bo);
        });
        // 返回转换后的BO列表
        return boList;
    }
}
