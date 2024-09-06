package cn.lb.subject.domain.service.impl;

import cn.lb.subject.common.entity.PageResult;
import cn.lb.subject.domain.entity.SubjectLikedBO;
import cn.lb.subject.domain.service.SubjectLikedDomainService;
import org.springframework.stereotype.Service;

/**
 * 题目点赞记录领域服务实现类
 *
 * @author RainSoul
 * @create 2024-09-06
 */
@Service
public class SubjectLikedDomainServiceImpl implements SubjectLikedDomainService {
    @Override
    public void add(SubjectLikedBO subjectLikedBO) {

    }

    @Override
    public Boolean isLiked(String subjectId, String userId) {
        return null;
    }

    @Override
    public Integer getLikedCount(String subjectId) {
        return 0;
    }

    @Override
    public Boolean update(SubjectLikedBO subjectLikedBO) {
        return null;
    }

    @Override
    public Boolean delete(SubjectLikedBO subjectLikedBO) {
        return null;
    }

    @Override
    public void syncLiked() {

    }

    @Override
    public PageResult<SubjectLikedBO> getSubjectLikedPage(SubjectLikedBO subjectLikedBO) {
        return null;
    }
}
