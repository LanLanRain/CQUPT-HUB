package cn.lb.subject.infra.basic.service.impl;

import cn.lb.subject.infra.basic.entity.SubjectInfo;
import cn.lb.subject.infra.basic.mapper.SubjectInfoDao;
import cn.lb.subject.infra.basic.service.SubjectInfoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 题目信息表(SubjectInfo)表服务实现类
 *
 * @author RainSoul
 * @create 2024-09-05
 */
@Service
public class SubjectInfoServiceImpl implements SubjectInfoService {

    @Resource
    private SubjectInfoDao subjectInfoDao;


    @Override
    public SubjectInfo queryById(Long id) {
        subjectInfoDao.queryById(id);
        return null;
    }

    @Override
    public SubjectInfo insert(SubjectInfo subjectInfo) {
        int insert = subjectInfoDao.insert(subjectInfo);
        return subjectInfo;
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
