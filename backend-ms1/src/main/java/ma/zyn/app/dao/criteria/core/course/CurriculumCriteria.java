package  ma.zyn.app.dao.criteria.core.course;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class CurriculumCriteria extends  BaseCriteria  {


    private CourseCriteria course ;
    private List<CourseCriteria> courses ;



    public CourseCriteria getCourse(){
        return this.course;
    }

    public void setCourse(CourseCriteria course){
        this.course = course;
    }
    public List<CourseCriteria> getCourses(){
        return this.courses;
    }

    public void setCourses(List<CourseCriteria> courses){
        this.courses = courses;
    }
}
