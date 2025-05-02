package learning.more.service.clazz.impl;

import jakarta.annotation.Resource;
import learning.more.common.AppResult;
import learning.more.common.enums.ResultCode;
import learning.more.dao.ClassDao;
import learning.more.exception.ApplicationException;
import learning.more.model.domain.Class;
import learning.more.model.vo.ClassInfoVO;
import learning.more.model.vo.ClassOverviewVO;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.SuccessVO;
import learning.more.service.clazz.IClassService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/12/29
 * @Copyright： https://github.com/DukeLewis
 */
@Service
public class ClassServiceImpl implements IClassService {
    @Resource
    private ClassDao classDao;

    @Override
    public List<ClassOverviewVO> listClassOverview(Long schoolId) {
        return classDao.listClassOverview(schoolId);
    }

    @Override
    public ClassInfoVO getClassInfo(Long id) {
        if(id == null || id < 0) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        return classDao.getClassInfo(id);
    }

    @Override
    public SuccessVO deleteClass(Long id) {
        if(id == null || id < 0) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        boolean updated = classDao.lambdaUpdate().set(Class::getIsDeleted, 1)
                .eq(Class::getId, id).update();
        if(!updated) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED));
        }
        return SuccessVO.successNotData();
    }

    @Override
    public SuccessVO updateClass(ClassInfoVO classInfoVO) {
        if(classInfoVO == null) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        boolean updated = classDao.updateById(Class.builder()
                .id(classInfoVO.getId())
                .name(classInfoVO.getClassName())
                .grade(classInfoVO.getGrade())
                .school(classInfoVO.getSchool())
                .quantity(classInfoVO.getStudentCount())
                .updatedTime(new Date()).build());
        if(!updated) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED));
        }
        return SuccessVO.successNotData();
    }

    @Override
    public PageItem<List<ClassOverviewVO>> listClassOverviewPage(Integer page, Integer limit, Class clazz) {
        return classDao.listClassOverviewPage(page, limit, clazz);
    }
}
