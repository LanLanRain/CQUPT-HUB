package cn.lb.subject.infra.basic.service.impl;

import cn.lb.subject.infra.basic.entity.SubjectRadio;
import cn.lb.subject.infra.basic.mapper.SubjectRadioDao;
import cn.lb.subject.infra.basic.service.SubjectRadioService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RainSoul
 *   1
 * @description 针对表【subject_radio】的数据库操作Service实现
 * @create 2024-09-06
 */
@Service
public class SubjectRadioServiceImpl implements SubjectRadioService {

    @Resource
    private SubjectRadioDao subjectRadioDao;

    @Override
    public SubjectRadio queryById(Long id) {
        return subjectRadioDao.queryById(id);
    }

    @Override
    public SubjectRadio insert(SubjectRadio subjectRadio) {
        int insert = subjectRadioDao.insert(subjectRadio);
        return subjectRadio;
    }

    @Override
    public void batchInsert(List<SubjectRadio> subjectRadioList) {
        subjectRadioDao.insertBatch(subjectRadioList);
    }

    @Override
    public SubjectRadio update(SubjectRadio subjectRadio) {
        int update = subjectRadioDao.update(subjectRadio);
        return subjectRadio;
    }

    @Override
    public boolean deleteById(Long id) {
        int i = subjectRadioDao.deleteById(id);
        return i > 0;
    }

    /**
     * 条件查询
     * @param subjectRadio
     * @return
     */
    @Override
    public List<SubjectRadio> queryByCondition(SubjectRadio subjectRadio) {
        List<SubjectRadio> subjectRadios = subjectRadioDao.queryAllByLimit(subjectRadio);
        return subjectRadios;
    }
}
