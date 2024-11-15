package  ma.zyn.app.ws.facade.admin.student;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.student.Enrollment;
import ma.zyn.app.dao.criteria.core.student.EnrollmentCriteria;
import ma.zyn.app.service.facade.admin.student.EnrollmentAdminService;
import ma.zyn.app.ws.converter.student.EnrollmentConverter;
import ma.zyn.app.ws.dto.student.EnrollmentDto;
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
@RequestMapping("/api/admin/enrollment/")
public class EnrollmentRestAdmin {




    @Operation(summary = "Finds a list of all enrollments")
    @GetMapping("")
    public ResponseEntity<List<EnrollmentDto>> findAll() throws Exception {
        ResponseEntity<List<EnrollmentDto>> res = null;
        List<Enrollment> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<EnrollmentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a enrollment by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EnrollmentDto> findById(@PathVariable Long id) {
        Enrollment t = service.findById(id);
        if (t != null) {
            converter.init(true);
            EnrollmentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  enrollment")
    @PostMapping("")
    public ResponseEntity<EnrollmentDto> save(@RequestBody EnrollmentDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Enrollment myT = converter.toItem(dto);
            Enrollment t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EnrollmentDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  enrollment")
    @PutMapping("")
    public ResponseEntity<EnrollmentDto> update(@RequestBody EnrollmentDto dto) throws Exception {
        ResponseEntity<EnrollmentDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Enrollment t = service.findById(dto.getId());
            converter.copy(dto,t);
            Enrollment updated = service.update(t);
            EnrollmentDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of enrollment")
    @PostMapping("multiple")
    public ResponseEntity<List<EnrollmentDto>> delete(@RequestBody List<EnrollmentDto> dtos) throws Exception {
        ResponseEntity<List<EnrollmentDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Enrollment> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified enrollment")
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

    @Operation(summary = "find by student id")
    @GetMapping("student/id/{id}")
    public List<EnrollmentDto> findByStudentId(@PathVariable Long id){
        return findDtos(service.findByStudentId(id));
    }
    @Operation(summary = "delete by student id")
    @DeleteMapping("student/id/{id}")
    public int deleteByStudentId(@PathVariable Long id){
        return service.deleteByStudentId(id);
    }
    @Operation(summary = "find by enrollmentState code")
    @GetMapping("enrollmentState/code/{code}")
    public List<EnrollmentDto> findByEnrollmentStateCode(@PathVariable String code){
        return findDtos(service.findByEnrollmentStateCode(code));
    }
    @Operation(summary = "delete by enrollmentState code")
    @DeleteMapping("enrollmentState/code/{code}")
    public int deleteByEnrollmentStateCode(@PathVariable String code){
        return service.deleteByEnrollmentStateCode(code);
    }

    @Operation(summary = "Finds a enrollment and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EnrollmentDto> findWithAssociatedLists(@PathVariable Long id) {
        Enrollment loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        EnrollmentDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds enrollments by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EnrollmentDto>> findByCriteria(@RequestBody EnrollmentCriteria criteria) throws Exception {
        ResponseEntity<List<EnrollmentDto>> res = null;
        List<Enrollment> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<EnrollmentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated enrollments by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EnrollmentCriteria criteria) throws Exception {
        List<Enrollment> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<EnrollmentDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets enrollment data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EnrollmentCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EnrollmentDto> findDtos(List<Enrollment> list){
        converter.initObject(true);
        List<EnrollmentDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EnrollmentDto> getDtoResponseEntity(EnrollmentDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public EnrollmentRestAdmin(EnrollmentAdminService service, EnrollmentConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final EnrollmentAdminService service;
    private final EnrollmentConverter converter;





}
