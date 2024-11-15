package  ma.zyn.app.ws.converter.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.course.CategoryConverter;
import ma.zyn.app.bean.core.course.Category;
import ma.zyn.app.ws.converter.instructor.InstructorConverter;
import ma.zyn.app.bean.core.instructor.Instructor;
import ma.zyn.app.ws.converter.course.CurriculumConverter;
import ma.zyn.app.bean.core.course.Curriculum;
import ma.zyn.app.ws.converter.course.LanguageConverter;
import ma.zyn.app.bean.core.course.Language;
import ma.zyn.app.ws.converter.course.LevelConverter;
import ma.zyn.app.bean.core.course.Level;

import ma.zyn.app.bean.core.course.Curriculum;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.course.Course;
import ma.zyn.app.ws.dto.course.CourseDto;

@Component
public class CourseConverter {

    @Autowired
    private CategoryConverter categoryConverter ;
    @Autowired
    private InstructorConverter instructorConverter ;
    @Autowired
    private CurriculumConverter curriculumConverter ;
    @Autowired
    private LanguageConverter languageConverter ;
    @Autowired
    private LevelConverter levelConverter ;
    private boolean instructor;
    private boolean category;
    private boolean level;
    private boolean language;
    private boolean curriculum;

    public  CourseConverter() {
        initObject(true);
    }

    public Course toItem(CourseDto dto) {
        if (dto == null) {
            return null;
        } else {
        Course item = new Course();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLabel()))
                item.setLabel(dto.getLabel());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getStartDate()))
                item.setStartDate(DateUtil.stringEnToDate(dto.getStartDate()));
            if(StringUtil.isNotEmpty(dto.getEndDate()))
                item.setEndDate(DateUtil.stringEnToDate(dto.getEndDate()));
            if(StringUtil.isNotEmpty(dto.getDuration()))
                item.setDuration(dto.getDuration());
            if(StringUtil.isNotEmpty(dto.getPrice()))
                item.setPrice(dto.getPrice());
            if(StringUtil.isNotEmpty(dto.getRequirements()))
                item.setRequirements(dto.getRequirements());
            if(StringUtil.isNotEmpty(dto.getLearningOutcomes()))
                item.setLearningOutcomes(dto.getLearningOutcomes());
            if(this.instructor && dto.getInstructor()!=null)
                item.setInstructor(instructorConverter.toItem(dto.getInstructor())) ;

            if(this.category && dto.getCategory()!=null)
                item.setCategory(categoryConverter.toItem(dto.getCategory())) ;

            if(this.level && dto.getLevel()!=null)
                item.setLevel(levelConverter.toItem(dto.getLevel())) ;

            if(this.language && dto.getLanguage()!=null)
                item.setLanguage(languageConverter.toItem(dto.getLanguage())) ;

            if(dto.getCurriculum() != null && dto.getCurriculum().getId() != null){
                item.setCurriculum(new Curriculum());
                item.getCurriculum().setId(dto.getCurriculum().getId());
                item.getCurriculum().setId(dto.getCurriculum().getId());
            }




        return item;
        }
    }


    public CourseDto toDto(Course item) {
        if (item == null) {
            return null;
        } else {
            CourseDto dto = new CourseDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLabel()))
                dto.setLabel(item.getLabel());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(item.getStartDate()!=null)
                dto.setStartDate(DateUtil.dateTimeToString(item.getStartDate()));
            if(item.getEndDate()!=null)
                dto.setEndDate(DateUtil.dateTimeToString(item.getEndDate()));
            if(StringUtil.isNotEmpty(item.getDuration()))
                dto.setDuration(item.getDuration());
            if(StringUtil.isNotEmpty(item.getPrice()))
                dto.setPrice(item.getPrice());
            if(StringUtil.isNotEmpty(item.getRequirements()))
                dto.setRequirements(item.getRequirements());
            if(StringUtil.isNotEmpty(item.getLearningOutcomes()))
                dto.setLearningOutcomes(item.getLearningOutcomes());
            if(this.instructor && item.getInstructor()!=null) {
                dto.setInstructor(instructorConverter.toDto(item.getInstructor())) ;

            }
            if(this.category && item.getCategory()!=null) {
                dto.setCategory(categoryConverter.toDto(item.getCategory())) ;

            }
            if(this.level && item.getLevel()!=null) {
                dto.setLevel(levelConverter.toDto(item.getLevel())) ;

            }
            if(this.language && item.getLanguage()!=null) {
                dto.setLanguage(languageConverter.toDto(item.getLanguage())) ;

            }
            if(this.curriculum && item.getCurriculum()!=null) {
                curriculumConverter.setCourse(false);
                dto.setCurriculum(curriculumConverter.toDto(item.getCurriculum())) ;
                curriculumConverter.setCourse(true);

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.instructor = value;
        this.category = value;
        this.level = value;
        this.language = value;
        this.curriculum = value;
    }
	
    public List<Course> toItem(List<CourseDto> dtos) {
        List<Course> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CourseDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CourseDto> toDto(List<Course> items) {
        List<CourseDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Course item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CourseDto dto, Course t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getInstructor() == null  && dto.getInstructor() != null){
            t.setInstructor(new Instructor());
        }else if (t.getInstructor() != null  && dto.getInstructor() != null){
            t.setInstructor(null);
            t.setInstructor(new Instructor());
        }
        if(t.getCategory() == null  && dto.getCategory() != null){
            t.setCategory(new Category());
        }else if (t.getCategory() != null  && dto.getCategory() != null){
            t.setCategory(null);
            t.setCategory(new Category());
        }
        if(t.getLevel() == null  && dto.getLevel() != null){
            t.setLevel(new Level());
        }else if (t.getLevel() != null  && dto.getLevel() != null){
            t.setLevel(null);
            t.setLevel(new Level());
        }
        if(t.getLanguage() == null  && dto.getLanguage() != null){
            t.setLanguage(new Language());
        }else if (t.getLanguage() != null  && dto.getLanguage() != null){
            t.setLanguage(null);
            t.setLanguage(new Language());
        }
        if(t.getCurriculum() == null  && dto.getCurriculum() != null){
            t.setCurriculum(new Curriculum());
        }else if (t.getCurriculum() != null  && dto.getCurriculum() != null){
            t.setCurriculum(null);
            t.setCurriculum(new Curriculum());
        }
        if (dto.getInstructor() != null)
        instructorConverter.copy(dto.getInstructor(), t.getInstructor());
        if (dto.getCategory() != null)
        categoryConverter.copy(dto.getCategory(), t.getCategory());
        if (dto.getLevel() != null)
        levelConverter.copy(dto.getLevel(), t.getLevel());
        if (dto.getLanguage() != null)
        languageConverter.copy(dto.getLanguage(), t.getLanguage());
        if (dto.getCurriculum() != null)
        curriculumConverter.copy(dto.getCurriculum(), t.getCurriculum());
    }

    public List<Course> copy(List<CourseDto> dtos) {
        List<Course> result = new ArrayList<>();
        if (dtos != null) {
            for (CourseDto dto : dtos) {
                Course instance = new Course();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CategoryConverter getCategoryConverter(){
        return this.categoryConverter;
    }
    public void setCategoryConverter(CategoryConverter categoryConverter ){
        this.categoryConverter = categoryConverter;
    }
    public InstructorConverter getInstructorConverter(){
        return this.instructorConverter;
    }
    public void setInstructorConverter(InstructorConverter instructorConverter ){
        this.instructorConverter = instructorConverter;
    }
    public CurriculumConverter getCurriculumConverter(){
        return this.curriculumConverter;
    }
    public void setCurriculumConverter(CurriculumConverter curriculumConverter ){
        this.curriculumConverter = curriculumConverter;
    }
    public LanguageConverter getLanguageConverter(){
        return this.languageConverter;
    }
    public void setLanguageConverter(LanguageConverter languageConverter ){
        this.languageConverter = languageConverter;
    }
    public LevelConverter getLevelConverter(){
        return this.levelConverter;
    }
    public void setLevelConverter(LevelConverter levelConverter ){
        this.levelConverter = levelConverter;
    }
    public boolean  isInstructor(){
        return this.instructor;
    }
    public void  setInstructor(boolean instructor){
        this.instructor = instructor;
    }
    public boolean  isCategory(){
        return this.category;
    }
    public void  setCategory(boolean category){
        this.category = category;
    }
    public boolean  isLevel(){
        return this.level;
    }
    public void  setLevel(boolean level){
        this.level = level;
    }
    public boolean  isLanguage(){
        return this.language;
    }
    public void  setLanguage(boolean language){
        this.language = language;
    }
    public boolean  isCurriculum(){
        return this.curriculum;
    }
    public void  setCurriculum(boolean curriculum){
        this.curriculum = curriculum;
    }
}
