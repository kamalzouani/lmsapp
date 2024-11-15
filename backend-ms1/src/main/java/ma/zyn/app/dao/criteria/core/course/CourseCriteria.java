package  ma.zyn.app.dao.criteria.core.course;


import ma.zyn.app.dao.criteria.core.instructor.InstructorCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class CourseCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String label;
    private String labelLike;
    private String description;
    private String descriptionLike;
    private LocalDateTime startDate;
    private LocalDateTime startDateFrom;
    private LocalDateTime startDateTo;
    private LocalDateTime endDate;
    private LocalDateTime endDateFrom;
    private LocalDateTime endDateTo;
    private String duration;
    private String durationMin;
    private String durationMax;
    private String price;
    private String priceMin;
    private String priceMax;
    private String requirements;
    private String requirementsLike;
    private String learningOutcomes;
    private String learningOutcomesLike;

    private InstructorCriteria instructor ;
    private List<InstructorCriteria> instructors ;
    private CategoryCriteria category ;
    private List<CategoryCriteria> categorys ;
    private LevelCriteria level ;
    private List<LevelCriteria> levels ;
    private LanguageCriteria language ;
    private List<LanguageCriteria> languages ;
    private CurriculumCriteria curriculum ;
    private List<CurriculumCriteria> curriculums ;


    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCodeLike(){
        return this.codeLike;
    }
    public void setCodeLike(String codeLike){
        this.codeLike = codeLike;
    }

    public String getLabel(){
        return this.label;
    }
    public void setLabel(String label){
        this.label = label;
    }
    public String getLabelLike(){
        return this.labelLike;
    }
    public void setLabelLike(String labelLike){
        this.labelLike = labelLike;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }

    public LocalDateTime getStartDate(){
        return this.startDate;
    }
    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }
    public LocalDateTime getStartDateFrom(){
        return this.startDateFrom;
    }
    public void setStartDateFrom(LocalDateTime startDateFrom){
        this.startDateFrom = startDateFrom;
    }
    public LocalDateTime getStartDateTo(){
        return this.startDateTo;
    }
    public void setStartDateTo(LocalDateTime startDateTo){
        this.startDateTo = startDateTo;
    }
    public LocalDateTime getEndDate(){
        return this.endDate;
    }
    public void setEndDate(LocalDateTime endDate){
        this.endDate = endDate;
    }
    public LocalDateTime getEndDateFrom(){
        return this.endDateFrom;
    }
    public void setEndDateFrom(LocalDateTime endDateFrom){
        this.endDateFrom = endDateFrom;
    }
    public LocalDateTime getEndDateTo(){
        return this.endDateTo;
    }
    public void setEndDateTo(LocalDateTime endDateTo){
        this.endDateTo = endDateTo;
    }
    public String getDuration(){
        return this.duration;
    }
    public void setDuration(String duration){
        this.duration = duration;
    }   
    public String getDurationMin(){
        return this.durationMin;
    }
    public void setDurationMin(String durationMin){
        this.durationMin = durationMin;
    }
    public String getDurationMax(){
        return this.durationMax;
    }
    public void setDurationMax(String durationMax){
        this.durationMax = durationMax;
    }
      
    public String getPrice(){
        return this.price;
    }
    public void setPrice(String price){
        this.price = price;
    }   
    public String getPriceMin(){
        return this.priceMin;
    }
    public void setPriceMin(String priceMin){
        this.priceMin = priceMin;
    }
    public String getPriceMax(){
        return this.priceMax;
    }
    public void setPriceMax(String priceMax){
        this.priceMax = priceMax;
    }
      
    public String getRequirements(){
        return this.requirements;
    }
    public void setRequirements(String requirements){
        this.requirements = requirements;
    }
    public String getRequirementsLike(){
        return this.requirementsLike;
    }
    public void setRequirementsLike(String requirementsLike){
        this.requirementsLike = requirementsLike;
    }

    public String getLearningOutcomes(){
        return this.learningOutcomes;
    }
    public void setLearningOutcomes(String learningOutcomes){
        this.learningOutcomes = learningOutcomes;
    }
    public String getLearningOutcomesLike(){
        return this.learningOutcomesLike;
    }
    public void setLearningOutcomesLike(String learningOutcomesLike){
        this.learningOutcomesLike = learningOutcomesLike;
    }


    public InstructorCriteria getInstructor(){
        return this.instructor;
    }

    public void setInstructor(InstructorCriteria instructor){
        this.instructor = instructor;
    }
    public List<InstructorCriteria> getInstructors(){
        return this.instructors;
    }

    public void setInstructors(List<InstructorCriteria> instructors){
        this.instructors = instructors;
    }
    public CategoryCriteria getCategory(){
        return this.category;
    }

    public void setCategory(CategoryCriteria category){
        this.category = category;
    }
    public List<CategoryCriteria> getCategorys(){
        return this.categorys;
    }

    public void setCategorys(List<CategoryCriteria> categorys){
        this.categorys = categorys;
    }
    public LevelCriteria getLevel(){
        return this.level;
    }

    public void setLevel(LevelCriteria level){
        this.level = level;
    }
    public List<LevelCriteria> getLevels(){
        return this.levels;
    }

    public void setLevels(List<LevelCriteria> levels){
        this.levels = levels;
    }
    public LanguageCriteria getLanguage(){
        return this.language;
    }

    public void setLanguage(LanguageCriteria language){
        this.language = language;
    }
    public List<LanguageCriteria> getLanguages(){
        return this.languages;
    }

    public void setLanguages(List<LanguageCriteria> languages){
        this.languages = languages;
    }
    public CurriculumCriteria getCurriculum(){
        return this.curriculum;
    }

    public void setCurriculum(CurriculumCriteria curriculum){
        this.curriculum = curriculum;
    }
    public List<CurriculumCriteria> getCurriculums(){
        return this.curriculums;
    }

    public void setCurriculums(List<CurriculumCriteria> curriculums){
        this.curriculums = curriculums;
    }
}
