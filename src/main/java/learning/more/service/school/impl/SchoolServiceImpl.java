package learning.more.service.school.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import learning.more.common.AppResult;
import learning.more.common.enums.ResultCode;
import learning.more.dao.SchoolDao;
import learning.more.exception.ApplicationException;
import learning.more.model.domain.Course;
import learning.more.model.domain.School;
import learning.more.model.dto.SchoolCreateDTO;
import learning.more.model.dto.SchoolUpdateDTO;
import learning.more.model.vo.CourseOverviewVO;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.SchoolOverviewVO;
import learning.more.model.vo.SuccessVO;
import learning.more.service.school.ISchoolService;
import learning.more.util.StringUtil;
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
public class SchoolServiceImpl implements ISchoolService {
    @Resource
    private SchoolDao schoolDao;

    @Override
    public PageItem<List<SchoolOverviewVO>> listSchoolOverviewPage(Integer pageNum, Integer pageSize, String searchString) {
        if (pageNum == null || pageSize == null || pageSize <= 0 || pageNum <= 0) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        Page<School> pageObj = new Page<>(pageNum, pageSize);
        Page<School> schoolPage = schoolDao.getBaseMapper().selectPage(pageObj,
                new LambdaQueryWrapper<School>()
                        .select(School::getId, School::getName, School::getPosition, School::getQuantity)
                        .eq(School::getIsDeleted, 0)
                        .like(!StringUtil.isNullAndEmpty(searchString), School::getName, searchString));
        List<SchoolOverviewVO> schoolOverviewVOS = schoolPage.getRecords()
                .stream()
                .map(school -> new SchoolOverviewVO(school.getId(),
                        school.getName(), school.getPosition(), school.getQuantity())).toList();
        return new PageItem<>(schoolPage.getTotal(), schoolPage.getPages(), pageNum, schoolOverviewVOS);
    }

    @Override
    public SuccessVO createSchool(SchoolCreateDTO schoolCreateDTO) {
        if (schoolCreateDTO == null) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        Date now = new Date();
        School school = School.builder()
                .name(schoolCreateDTO.getName())
                .position(schoolCreateDTO.getPosition())
                .quantity(schoolCreateDTO.getQuantity())
                .createdTime(now)
                .updatedTime(now)
                .isDeleted(0)
                .build();
        boolean saved = schoolDao.save(school);
        if (!saved) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED));
        }
        return SuccessVO.successNotData();
    }

    @Override
    public SuccessVO updateSchool(SchoolUpdateDTO schoolUpdateDTO) {
        if (schoolUpdateDTO == null || schoolUpdateDTO.getId() == null) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        School school = School.builder()
                .id(schoolUpdateDTO.getId())
                .name(schoolUpdateDTO.getName())
                .position(schoolUpdateDTO.getPosition())
                .quantity(schoolUpdateDTO.getQuantity())
                .updatedTime(new Date())
                .build();
        boolean updated = schoolDao.updateById(school);
        if (!updated) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED));
        }
        return SuccessVO.successNotData();
    }

    @Override
    public SuccessVO deleteSchool(Long schoolId) {
        if (schoolId == null) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        boolean updated = schoolDao.lambdaUpdate()
                .eq(School::getId, schoolId)
                .set(School::getIsDeleted, 1)
                .update();
        if (!updated) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED));
        }
        return SuccessVO.successNotData();
    }
}
