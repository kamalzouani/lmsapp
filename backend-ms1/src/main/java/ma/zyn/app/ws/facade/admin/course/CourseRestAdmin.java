package  ma.zyn.app.ws.facade.admin.course;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.course.Course;
import ma.zyn.app.dao.criteria.core.course.CourseCriteria;
import ma.zyn.app.service.facade.admin.course.CourseAdminService;
import ma.zyn.app.ws.converter.course.CourseConverter;
import ma.zyn.app.ws.dto.course.CourseDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/course/")
public class CourseRestAdmin {




    @Operation(summary = "Finds a list of all courses")
    @GetMapping("")
    public ResponseEntity<List<CourseDto>> findAll() throws Exception {
        ResponseEntity<List<CourseDto>> res = null;
        List<Course> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<CourseDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all courses")
    @GetMapping("optimized")
    public ResponseEntity<List<CourseDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CourseDto>> res = null;
        List<Course> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<CourseDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a course by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CourseDto> findById(@PathVariable Long id) {
        Course t = service.findById(id);
        if (t != null) {
            converter.init(true);
            CourseDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a course by label")
    @GetMapping("label/{label}")
    public ResponseEntity<CourseDto> findByLabel(@PathVariable String label) {
	    Course t = service.findByReferenceEntity(new Course(label));
        if (t != null) {
            converter.init(true);
            CourseDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  course")
    @PostMapping("")
    public ResponseEntity<CourseDto> save(@RequestBody CourseDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Course myT = converter.toItem(dto);
            Course t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CourseDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  course")
    @PutMapping("")
    public ResponseEntity<CourseDto> update(@RequestBody CourseDto dto) throws Exception {
        ResponseEntity<CourseDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Course t = service.findById(dto.getId());
            converter.copy(dto,t);
            Course updated = service.update(t);
            CourseDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of course")
    @PostMapping("multiple")
    public ResponseEntity<List<CourseDto>> delete(@RequestBody List<CourseDto> dtos) throws Exception {
        ResponseEntity<List<CourseDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Course> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified course")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }

    @Operation(summary = "find by instructor id")
    @GetMapping("instructor/id/{id}")
    public List<CourseDto> findByInstructorId(@PathVariable Long id){
        return findDtos(service.findByInstructorId(id));
    }
    @Operation(summary = "delete by instructor id")
    @DeleteMapping("instructor/id/{id}")
    public int deleteByInstructorId(@PathVariable Long id){
        return service.deleteByInstructorId(id);
    }
    @Operation(summary = "find by category code")
    @GetMapping("category/code/{code}")
    public List<CourseDto> findByCategoryCode(@PathVariable String code){
        return findDtos(service.findByCategoryCode(code));
    }
    @Operation(summary = "delete by category code")
    @DeleteMapping("category/code/{code}")
    public int deleteByCategoryCode(@PathVariable String code){
        return service.deleteByCategoryCode(code);
    }
    @Operation(summary = "find by level code")
    @GetMapping("level/code/{code}")
    public List<CourseDto> findByLevelCode(@PathVariable String code){
        return findDtos(service.findByLevelCode(code));
    }
    @Operation(summary = "delete by level code")
    @DeleteMapping("level/code/{code}")
    public int deleteByLevelCode(@PathVariable String code){
        return service.deleteByLevelCode(code);
    }
    @Operation(summary = "find by language code")
    @GetMapping("language/code/{code}")
    public List<CourseDto> findByLanguageCode(@PathVariable String code){
        return findDtos(service.findByLanguageCode(code));
    }
    @Operation(summary = "delete by language code")
    @DeleteMapping("language/code/{code}")
    public int deleteByLanguageCode(@PathVariable String code){
        return service.deleteByLanguageCode(code);
    }
    @Operation(summary = "find by curriculum id")
    @GetMapping("curriculum/id/{id}")
    public List<CourseDto> findByCurriculumId(@PathVariable Long id){
        return findDtos(service.findByCurriculumId(id));
    }
    @Operation(summary = "delete by curriculum id")
    @DeleteMapping("curriculum/id/{id}")
    public int deleteByCurriculumId(@PathVariable Long id){
        return service.deleteByCurriculumId(id);
    }

    @Operation(summary = "Finds a course and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CourseDto> findWithAssociatedLists(@PathVariable Long id) {
        Course loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        CourseDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds courses by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CourseDto>> findByCriteria(@RequestBody CourseCriteria criteria) throws Exception {
        ResponseEntity<List<CourseDto>> res = null;
        List<Course> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<CourseDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated courses by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CourseCriteria criteria) throws Exception {
        List<Course> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<CourseDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets course data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CourseCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CourseDto> findDtos(List<Course> list){
        converter.initObject(true);
        List<CourseDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CourseDto> getDtoResponseEntity(CourseDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public CourseRestAdmin(CourseAdminService service, CourseConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CourseAdminService service;
    private final CourseConverter converter;





}
