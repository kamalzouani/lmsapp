package  ma.zyn.app.dao.criteria.core.course;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ResourceCriteria extends  BaseCriteria  {

    private String type;
    private String typeLike;
    private String url;
    private String urlLike;
    private String file;
    private String fileLike;

    private LessonCriteria lesson ;
    private List<LessonCriteria> lessons ;


    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getTypeLike(){
        return this.typeLike;
    }
    public void setTypeLike(String typeLike){
        this.typeLike = typeLike;
    }

    public String getUrl(){
        return this.url;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrlLike(){
        return this.urlLike;
    }
    public void setUrlLike(String urlLike){
        this.urlLike = urlLike;
    }

    public String getFile(){
        return this.file;
    }
    public void setFile(String file){
        this.file = file;
    }
    public String getFileLike(){
        return this.fileLike;
    }
    public void setFileLike(String fileLike){
        this.fileLike = fileLike;
    }


    public LessonCriteria getLesson(){
        return this.lesson;
    }

    public void setLesson(LessonCriteria lesson){
        this.lesson = lesson;
    }
    public List<LessonCriteria> getLessons(){
        return this.lessons;
    }

    public void setLessons(List<LessonCriteria> lessons){
        this.lessons = lessons;
    }
}
