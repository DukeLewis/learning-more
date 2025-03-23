package learning.more.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import learning.more.dao.SchoolDao;
import learning.more.model.domain.Class;
import learning.more.dao.ClassDao;
import learning.more.dao.mapper.ClassMapper;
import learning.more.model.domain.School;
import learning.more.model.vo.ClassInfoVO;
import learning.more.model.vo.ClassOverviewVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ASUS
 * @description 针对表【class】的数据库操作Service实现
 * @createDate 2024-11-29 12:11:28
 */
@Service
public class ClassDaoImpl extends ServiceImpl<ClassMapper, Class>
        implements ClassDao {
    @Resource
    private SchoolDao schoolDao;

    @Override
    public List<ClassOverviewVO> listClassOverview(Long schoolId) {
        List<School> schoolList = Collections.emptyList();
        if (schoolId != null && schoolId > 0) {
            schoolList = schoolDao.lambdaQuery()
                    .select(School::getId, School::getName)
                    .eq(School::getId, schoolId)
                    .eq(School::getIsDeleted, 0)
                    .list();
        }
        List<Class> classList = this.lambdaQuery()
                .select(Class::getId, Class::getName, Class::getQuantity, Class::getCreatedTime)
                .in(!schoolList.isEmpty(), Class::getSchool, schoolList.stream().map(School::getName).toList())
                .eq(Class::getIsDeleted, 0)
                .list();
        List<ClassOverviewVO> classOverviewVOList = new ArrayList<>();
        classList.forEach(classItem -> {
            classOverviewVOList.add(new ClassOverviewVO(classItem.getId(), classItem.getName(), classItem.getQuantity(), classItem.getCreatedTime()));
        });
        return classOverviewVOList;
    }

    @Override
    public ClassInfoVO getClassInfo(Long id) {
        return this.baseMapper.getClassInfo(id);
    }
}




