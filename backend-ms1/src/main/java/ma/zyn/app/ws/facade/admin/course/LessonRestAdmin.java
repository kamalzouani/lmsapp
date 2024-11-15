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

import ma.zyn.app.bean.core.course.Lesson;
import ma.zyn.app.dao.criteria.core.course.LessonCriteria;
import ma.zyn.app.service.facade.admin.course.LessonAdminService;
import ma.zyn.app.ws.converter.course.LessonConverter;
import ma.zyn.app.ws.dto.course.LessonDto;
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
@RequestMapping("/api/admin/lesson/")
public class LessonRestAdmin {




    @Operation(summary = "Finds a list of all lessons")
    @GetMapping("")
    public ResponseEntity<List<LessonDto>> findAll() throws Exception {
        ResponseEntity<List<LessonDto>> res = null;
        List<Lesson> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<LessonDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all lessons")
    @GetMapping("optimized")
    public ResponseEntity<List<LessonDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<LessonDto>> res = null;
        List<Lesson> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<LessonDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a lesson by id")
    @GetMapping("id/{id}")
    public ResponseEntity<LessonDto> findById(@PathVariable Long id) {
        Lesson t = service.findById(id);
        if (t != null) {
            converter.init(true);
            LessonDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a lesson by label")
    @GetMapping("label/{label}")
    public ResponseEntity<LessonDto> findByLabel(@PathVariable String label) {
	    Lesson t = service.findByReferenceEntity(new Lesson(label));
        if (t != null) {
            converter.init(true);
            LessonDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  lesson")
    @PostMapping("")
    public ResponseEntity<LessonDto> save(@RequestBody LessonDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Lesson myT = converter.toItem(dto);
            Lesson t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                LessonDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  lesson")
    @PutMapping("")
    public ResponseEntity<LessonDto> update(@RequestBody LessonDto dto) throws Exception {
        ResponseEntity<LessonDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Lesson t = service.findById(dto.getId());
            converter.copy(dto,t);
            Lesson updated = service.update(t);
            LessonDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of lesson")
    @PostMapping("multiple")
    public ResponseEntity<List<LessonDto>> delete(@RequestBody List<LessonDto> dtos) throws Exception {
        ResponseEntity<List<LessonDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Lesson> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified lesson")
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


    @Operation(summary = "Finds a lesson and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<LessonDto> findWithAssociatedLists(@PathVariable Long id) {
        Lesson loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        LessonDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds lessons by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<LessonDto>> findByCriteria(@RequestBody LessonCriteria criteria) throws Exception {
        ResponseEntity<List<LessonDto>> res = null;
        List<Lesson> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<LessonDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated lessons by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody LessonCriteria criteria) throws Exception {
        List<Lesson> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<LessonDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets lesson data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody LessonCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<LessonDto> findDtos(List<Lesson> list){
        converter.initList(false);
        converter.initObject(true);
        List<LessonDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<LessonDto> getDtoResponseEntity(LessonDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public LessonRestAdmin(LessonAdminService service, LessonConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final LessonAdminService service;
    private final LessonConverter converter;





}
