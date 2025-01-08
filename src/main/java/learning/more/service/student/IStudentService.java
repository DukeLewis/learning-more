package learning.more.service.student;

import learning.more.model.vo.StudentOverviewVO;

import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/1/4
 * @Copyright： https://github.com/DukeLewis
 */
public interface IStudentService {
    /**
     * 获取班级信息概览列表
     * @param classId 班级 id
     * @return 班级信息概览列表
     */
    List<StudentOverviewVO> listStudentOverview(Long classId);
}
