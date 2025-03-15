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
import learning.more.model.vo.CourseOverviewVO;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.SchoolOverviewVO;
import learning.more.service.school.ISchoolService;
import org.springframework.stereotype.Service;

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
                new LambdaQueryWrapper<School>().select(School::getId, School::getName, School::getPosition,
                        School::getQuantity).like(School::getName, searchString));
        List<SchoolOverviewVO> schoolOverviewVOS = schoolPage.getRecords()
                .stream()
                .map(school -> new SchoolOverviewVO(school.getId(),
                        school.getName(), school.getPosition(), school.getQuantity())).toList();
        return new PageItem<>(schoolPage.getTotal(), schoolPage.getPages(), pageNum, schoolOverviewVOS);
    }
}
