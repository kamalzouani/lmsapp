package  ma.zyn.app.ws.converter.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.course.LessonConverter;
import ma.zyn.app.bean.core.course.Lesson;

import ma.zyn.app.bean.core.course.Lesson;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.course.Resource;
import ma.zyn.app.ws.dto.course.ResourceDto;

@Component
public class ResourceConverter {

    @Autowired
    private LessonConverter lessonConverter ;
    private boolean lesson;

    public  ResourceConverter() {
        initObject(true);
    }

    public Resource toItem(ResourceDto dto) {
        if (dto == null) {
            return null;
        } else {
        Resource item = new Resource();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getType()))
                item.setType(dto.getType());
            if(StringUtil.isNotEmpty(dto.getUrl()))
                item.setUrl(dto.getUrl());
            if(StringUtil.isNotEmpty(dto.getFile()))
                item.setFile(dto.getFile());
            if(dto.getLesson() != null && dto.getLesson().getId() != null){
                item.setLesson(new Lesson());
                item.getLesson().setId(dto.getLesson().getId());
                item.getLesson().setLabel(dto.getLesson().getLabel());
            }




        return item;
        }
    }


    public ResourceDto toDto(Resource item) {
        if (item == null) {
            return null;
        } else {
            ResourceDto dto = new ResourceDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getType()))
                dto.setType(item.getType());
            if(StringUtil.isNotEmpty(item.getUrl()))
                dto.setUrl(item.getUrl());
            if(StringUtil.isNotEmpty(item.getFile()))
                dto.setFile(item.getFile());
            if(this.lesson && item.getLesson()!=null) {
                dto.setLesson(lessonConverter.toDto(item.getLesson())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.lesson = value;
    }
	
    public List<Resource> toItem(List<ResourceDto> dtos) {
        List<Resource> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ResourceDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ResourceDto> toDto(List<Resource> items) {
        List<ResourceDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Resource item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ResourceDto dto, Resource t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getLesson() == null  && dto.getLesson() != null){
            t.setLesson(new Lesson());
        }else if (t.getLesson() != null  && dto.getLesson() != null){
            t.setLesson(null);
            t.setLesson(new Lesson());
        }
        if (dto.getLesson() != null)
        lessonConverter.copy(dto.getLesson(), t.getLesson());
    }

    public List<Resource> copy(List<ResourceDto> dtos) {
        List<Resource> result = new ArrayList<>();
        if (dtos != null) {
            for (ResourceDto dto : dtos) {
                Resource instance = new Resource();
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
    public boolean  isLesson(){
        return this.lesson;
    }
    public void  setLesson(boolean lesson){
        this.lesson = lesson;
    }
}
