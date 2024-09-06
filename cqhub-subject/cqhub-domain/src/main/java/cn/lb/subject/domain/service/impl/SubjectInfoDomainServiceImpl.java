package cn.lb.subject.domain.service.impl;

import cn.lb.subject.common.entity.PageResult;
import cn.lb.subject.common.enums.IsDeletedFlagEnum;
import cn.lb.subject.domain.convert.SubjectInfoConverter;
import cn.lb.subject.domain.entity.SubjectInfoBO;
import cn.lb.subject.domain.service.SubjectInfoDomainService;
import cn.lb.subject.infra.basic.entity.SubjectInfo;
import cn.lb.subject.infra.basic.service.SubjectInfoService;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 题目信息领域服务实现类
 *
 * @author RainSoul
 * @create 2024-09-06
 */
@Slf4j
@Service
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoDomainServiceImpl.add.request:{}", JSON.toJSONString(subjectInfoBO));
        }

        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBOTOEntity(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        subjectInfoService.insert(subjectInfo);

    }

    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());

        int start = (subjectInfoBO.getPageNo() - 1) * subjectInfoBO.getPageSize();

        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBOTOEntity(subjectInfoBO);
        return pageResult;
    }

    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO) {

        return null;
    }

    @Override
    public List<SubjectInfoBO> getContributeList() {
        return List.of();
    }
}
