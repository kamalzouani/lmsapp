package  ma.zyn.app.ws.converter.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.course.LessonConverter;
import ma.zyn.app.bean.core.course.Lesson;
import ma.zyn.app.ws.converter.course.ModuleConverter;
import ma.zyn.app.bean.core.course.Module;
import ma.zyn.app.ws.converter.course.CourseConverter;
import ma.zyn.app.bean.core.course.Course;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.course.Curriculum;
import ma.zyn.app.ws.dto.course.CurriculumDto;

@Component
public class CurriculumConverter {

    @Autowired
    private LessonConverter lessonConverter ;
    @Autowired
    private ModuleConverter moduleConverter ;
    @Autowired
    private CourseConverter courseConverter ;
    private boolean course;
    private boolean modules;

    public  CurriculumConverter() {
        init(true);
    }

    public Curriculum toItem(CurriculumDto dto) {
        if (dto == null) {
            return null;
        } else {
        Curriculum item = new Curriculum();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(this.course && dto.getCourse()!=null)
                item.setCourse(courseConverter.toItem(dto.getCourse())) ;


            if(this.modules && ListUtil.isNotEmpty(dto.getModules()))
                item.setModules(moduleConverter.toItem(dto.getModules()));


        return item;
        }
    }


    public CurriculumDto toDto(Curriculum item) {
        if (item == null) {
            return null;
        } else {
            CurriculumDto dto = new CurriculumDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.course && item.getCourse()!=null) {
                courseConverter.setCurriculum(false);
                dto.setCourse(courseConverter.toDto(item.getCourse())) ;
                courseConverter.setCurriculum(true);

            }
        if(this.modules && ListUtil.isNotEmpty(item.getModules())){
            moduleConverter.init(true);
            moduleConverter.setCurriculum(false);
            dto.setModules(moduleConverter.toDto(item.getModules()));
            moduleConverter.setCurriculum(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.modules = value;
    }
    public void initObject(boolean value) {
        this.course = value;
    }
	
    public List<Curriculum> toItem(List<CurriculumDto> dtos) {
        List<Curriculum> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CurriculumDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CurriculumDto> toDto(List<Curriculum> items) {
        List<CurriculumDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Curriculum item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CurriculumDto dto, Curriculum t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCourse() == null  && dto.getCourse() != null){
            t.setCourse(new Course());
        }else if (t.getCourse() != null  && dto.getCourse() != null){
            t.setCourse(null);
            t.setCourse(new Course());
        }
        if (dto.getCourse() != null)
        courseConverter.copy(dto.getCourse(), t.getCourse());
        if (dto.getModules() != null)
            t.setModules(moduleConverter.copy(dto.getModules()));
    }

    public List<Curriculum> copy(List<CurriculumDto> dtos) {
        List<Curriculum> result = new ArrayList<>();
        if (dtos != null) {
            for (CurriculumDto dto : dtos) {
                Curriculum instance = new Curriculum();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public LessonConverter getLessonConverter(){
        return this.lessonConverter;
    }
    public void setLessonConverter(LessonConverter lessonConverter ){
        this.lessonConverter = lessonConverter;
    }
    public ModuleConverter getModuleConverter(){
        return this.moduleConverter;
    }
    public void setModuleConverter(ModuleConverter moduleConverter ){
        this.moduleConverter = moduleConverter;
    }
    public CourseConverter getCourseConverter(){
        return this.courseConverter;
    }
    public void setCourseConverter(CourseConverter courseConverter ){
        this.courseConverter = courseConverter;
    }
    public boolean  isCourse(){
        return this.course;
    }
    public void  setCourse(boolean course){
        this.course = course;
    }
    public boolean  isModules(){
        return this.modules ;
    }
    public void  setModules(boolean modules ){
        this.modules  = modules ;
    }
}
