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
import ma.zyn.app.ws.converter.course.CurriculumConverter;
import ma.zyn.app.bean.core.course.Curriculum;
import ma.zyn.app.ws.converter.course.ResourceConverter;
import ma.zyn.app.bean.core.course.Resource;

import ma.zyn.app.bean.core.course.Curriculum;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.course.Module;
import ma.zyn.app.ws.dto.course.ModuleDto;

@Component
public class ModuleConverter {

    @Autowired
    private LessonConverter lessonConverter ;
    @Autowired
    private CurriculumConverter curriculumConverter ;
    @Autowired
    private ResourceConverter resourceConverter ;
    private boolean curriculum;
    private boolean lessons;

    public  ModuleConverter() {
        init(true);
    }

    public Module toItem(ModuleDto dto) {
        if (dto == null) {
            return null;
        } else {
        Module item = new Module();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLabel()))
                item.setLabel(dto.getLabel());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(dto.getCurriculum() != null && dto.getCurriculum().getId() != null){
                item.setCurriculum(new Curriculum());
                item.getCurriculum().setId(dto.getCurriculum().getId());
                item.getCurriculum().setId(dto.getCurriculum().getId());
            }


            if(this.lessons && ListUtil.isNotEmpty(dto.getLessons()))
                item.setLessons(lessonConverter.toItem(dto.getLessons()));


        return item;
        }
    }


    public ModuleDto toDto(Module item) {
        if (item == null) {
            return null;
        } else {
            ModuleDto dto = new ModuleDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLabel()))
                dto.setLabel(item.getLabel());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.curriculum && item.getCurriculum()!=null) {
                dto.setCurriculum(curriculumConverter.toDto(item.getCurriculum())) ;

            }
        if(this.lessons && ListUtil.isNotEmpty(item.getLessons())){
            lessonConverter.init(true);
            lessonConverter.setModule(false);
            dto.setLessons(lessonConverter.toDto(item.getLessons()));
            lessonConverter.setModule(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.lessons = value;
    }
    public void initObject(boolean value) {
        this.curriculum = value;
    }
	
    public List<Module> toItem(List<ModuleDto> dtos) {
        List<Module> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ModuleDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ModuleDto> toDto(List<Module> items) {
        List<ModuleDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Module item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ModuleDto dto, Module t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCurriculum() == null  && dto.getCurriculum() != null){
            t.setCurriculum(new Curriculum());
        }else if (t.getCurriculum() != null  && dto.getCurriculum() != null){
            t.setCurriculum(null);
            t.setCurriculum(new Curriculum());
        }
        if (dto.getCurriculum() != null)
        curriculumConverter.copy(dto.getCurriculum(), t.getCurriculum());
        if (dto.getLessons() != null)
            t.setLessons(lessonConverter.copy(dto.getLessons()));
    }

    public List<Module> copy(List<ModuleDto> dtos) {
        List<Module> result = new ArrayList<>();
        if (dtos != null) {
            for (ModuleDto dto : dtos) {
                Module instance = new Module();
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
    public CurriculumConverter getCurriculumConverter(){
        return this.curriculumConverter;
    }
    public void setCurriculumConverter(CurriculumConverter curriculumConverter ){
        this.curriculumConverter = curriculumConverter;
    }
    public ResourceConverter getResourceConverter(){
        return this.resourceConverter;
    }
    public void setResourceConverter(ResourceConverter resourceConverter ){
        this.resourceConverter = resourceConverter;
    }
    public boolean  isCurriculum(){
        return this.curriculum;
    }
    public void  setCurriculum(boolean curriculum){
        this.curriculum = curriculum;
    }
    public boolean  isLessons(){
        return this.lessons ;
    }
    public void  setLessons(boolean lessons ){
        this.lessons  = lessons ;
    }
}
