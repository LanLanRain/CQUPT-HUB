package cn.lb.subject.infra.basic.service.impl;

import cn.lb.subject.infra.basic.entity.SubjectInfo;
import cn.lb.subject.infra.basic.service.SubjectInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RainSoul
 * @create 2024-09-05
 */
@Service
public class SubjectInfoServiceImpl implements SubjectInfoService {



    @Override
    public SubjectInfo queryById(Long id) {
        return null;
    }

    @Override
    public SubjectInfo insert(SubjectInfo subjectInfo) {
        return null;
    }

    @Override
    public SubjectInfo update(SubjectInfo subjectInfo) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public int countByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId) {
        return 0;
    }

    @Override
    public List<SubjectInfo> queryPage(SubjectInfo subjectInfo, Long categoryId, Long labelId, int start, Integer pageSize) {
        return List.of();
    }

    @Override
    public List<SubjectInfo> getContributeCount() {
        return List.of();
    }

    @Override
    public Long querySubjectIdCursor(Long subjectId, Long categoryId, Long labelId, int cursor) {
        return 0L;
    }
}
