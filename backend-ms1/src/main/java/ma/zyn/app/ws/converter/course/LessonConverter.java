package  ma.zyn.app.ws.converter.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.course.ModuleConverter;
import ma.zyn.app.bean.core.course.Module;
import ma.zyn.app.ws.converter.course.ResourceConverter;
import ma.zyn.app.bean.core.course.Resource;

import ma.zyn.app.bean.core.course.Module;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.course.Lesson;
import ma.zyn.app.ws.dto.course.LessonDto;

@Component
public class LessonConverter {

    @Autowired
    private ModuleConverter moduleConverter ;
    @Autowired
    private ResourceConverter resourceConverter ;
    private boolean module;
    private boolean resources;

    public  LessonConverter() {
        init(true);
    }

    public Lesson toItem(LessonDto dto) {
        if (dto == null) {
            return null;
        } else {
        Lesson item = new Lesson();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLabel()))
                item.setLabel(dto.getLabel());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getContent()))
                item.setContent(dto.getContent());
            if(dto.getModule() != null && dto.getModule().getId() != null){
                item.setModule(new Module());
                item.getModule().setId(dto.getModule().getId());
                item.getModule().setLabel(dto.getModule().getLabel());
            }


            if(this.resources && ListUtil.isNotEmpty(dto.getResources()))
                item.setResources(resourceConverter.toItem(dto.getResources()));


        return item;
        }
    }


    public LessonDto toDto(Lesson item) {
        if (item == null) {
            return null;
        } else {
            LessonDto dto = new LessonDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLabel()))
                dto.setLabel(item.getLabel());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getContent()))
                dto.setContent(item.getContent());
            if(this.module && item.getModule()!=null) {
                dto.setModule(moduleConverter.toDto(item.getModule())) ;

            }
        if(this.resources && ListUtil.isNotEmpty(item.getResources())){
            resourceConverter.init(true);
            resourceConverter.setLesson(false);
            dto.setResources(resourceConverter.toDto(item.getResources()));
            resourceConverter.setLesson(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.resources = value;
    }
    public void initObject(boolean value) {
        this.module = value;
    }
	
    public List<Lesson> toItem(List<LessonDto> dtos) {
        List<Lesson> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (LessonDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<LessonDto> toDto(List<Lesson> items) {
        List<LessonDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Lesson item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(LessonDto dto, Lesson t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getModule() == null  && dto.getModule() != null){
            t.setModule(new Module());
        }else if (t.getModule() != null  && dto.getModule() != null){
            t.setModule(null);
            t.setModule(new Module());
        }
        if (dto.getModule() != null)
        moduleConverter.copy(dto.getModule(), t.getModule());
        if (dto.getResources() != null)
            t.setResources(resourceConverter.copy(dto.getResources()));
    }

    public List<Lesson> copy(List<LessonDto> dtos) {
        List<Lesson> result = new ArrayList<>();
        if (dtos != null) {
            for (LessonDto dto : dtos) {
                Lesson instance = new Lesson();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ModuleConverter getModuleConverter(){
        return this.moduleConverter;
    }
    public void setModuleConverter(ModuleConverter moduleConverter ){
        this.moduleConverter = moduleConverter;
    }
    public ResourceConverter getResourceConverter(){
        return this.resourceConverter;
    }
    public void setResourceConverter(ResourceConverter resourceConverter ){
        this.resourceConverter = resourceConverter;
    }
    public boolean  isModule(){
        return this.module;
    }
    public void  setModule(boolean module){
        this.module = module;
    }
    public boolean  isResources(){
        return this.resources ;
    }
    public void  setResources(boolean resources ){
        this.resources  = resources ;
    }
}
