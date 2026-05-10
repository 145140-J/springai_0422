package com.llsstt.lstai.CourseTools;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.llsstt.lstai.entity.po.Course;
import com.llsstt.lstai.entity.po.CourseReservation;
import com.llsstt.lstai.entity.po.School;
import com.llsstt.lstai.entity.query.CourseQuery;
import com.llsstt.lstai.service.ICourseReservationService;
import com.llsstt.lstai.service.ICourseService;
import com.llsstt.lstai.service.ISchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CourseTools {
    private final ICourseReservationService reservationService;
    private final ICourseService courseService;
    private final ISchoolService schoolService;
    @Tool(description = "根据条件查询课程")
    public List<Course> queryCourse(@ToolParam(description = "查询条件", required = false) CourseQuery query){

        if (query == null){
            //return List.of();
            return courseService.list();// 查询所有课程
        }
        QueryChainWrapper<Course> wrapper = courseService.query()
                .eq(query.getType() != null, "type",query.getType())
                .le(query.getEdu() != null, "edu",query.getEdu());
        if (query.getSorts() != null && query.getSorts().isEmpty()){
            for (CourseQuery.Sort sort : query.getSorts()) {

                wrapper.orderBy(sort.getField() != null, sort.getAsc(), sort.getField());
            }
        }
        return wrapper.list();
    }

    @Tool(description = "生成预约单,返回预约单号")
    public List<School> querySchool(){
        return schoolService.list();
    }

    @Tool(description = "创建课程预约")
    public Integer createCourseReservation(
            @ToolParam(description = "预约课程") String course,
            @ToolParam(description = "预约校区") String school,
            @ToolParam(description = "学生姓名") String studentName,
            @ToolParam(description = "联系电话") String contactInfo,
            @ToolParam(description = "备注", required = false) String remark){
        CourseReservation reservation = new CourseReservation();
        reservation.setCourse(course);
        reservation.setSchool(school);
        reservation.setStudentName(studentName);
        reservation.setContactInfo(contactInfo);
        reservation.setRemark(remark);
        reservationService.save(reservation);
        return reservation.getId();
    }
}
