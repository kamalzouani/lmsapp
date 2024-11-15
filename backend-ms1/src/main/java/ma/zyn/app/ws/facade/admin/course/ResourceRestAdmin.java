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

import ma.zyn.app.bean.core.course.Resource;
import ma.zyn.app.dao.criteria.core.course.ResourceCriteria;
import ma.zyn.app.service.facade.admin.course.ResourceAdminService;
import ma.zyn.app.ws.converter.course.ResourceConverter;
import ma.zyn.app.ws.dto.course.ResourceDto;
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
@RequestMapping("/api/admin/resource/")
public class ResourceRestAdmin {




    @Operation(summary = "Finds a list of all resources")
    @GetMapping("")
    public ResponseEntity<List<ResourceDto>> findAll() throws Exception {
        ResponseEntity<List<ResourceDto>> res = null;
        List<Resource> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ResourceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a resource by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ResourceDto> findById(@PathVariable Long id) {
        Resource t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ResourceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  resource")
    @PostMapping("")
    public ResponseEntity<ResourceDto> save(@RequestBody ResourceDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Resource myT = converter.toItem(dto);
            Resource t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ResourceDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  resource")
    @PutMapping("")
    public ResponseEntity<ResourceDto> update(@RequestBody ResourceDto dto) throws Exception {
        ResponseEntity<ResourceDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Resource t = service.findById(dto.getId());
            converter.copy(dto,t);
            Resource updated = service.update(t);
            ResourceDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of resource")
    @PostMapping("multiple")
    public ResponseEntity<List<ResourceDto>> delete(@RequestBody List<ResourceDto> dtos) throws Exception {
        ResponseEntity<List<ResourceDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Resource> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified resource")
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

    @Operation(summary = "find by lesson id")
    @GetMapping("lesson/id/{id}")
    public List<ResourceDto> findByLessonId(@PathVariable Long id){
        return findDtos(service.findByLessonId(id));
    }
    @Operation(summary = "delete by lesson id")
    @DeleteMapping("lesson/id/{id}")
    public int deleteByLessonId(@PathVariable Long id){
        return service.deleteByLessonId(id);
    }

    @Operation(summary = "Finds a resource and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ResourceDto> findWithAssociatedLists(@PathVariable Long id) {
        Resource loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ResourceDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds resources by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ResourceDto>> findByCriteria(@RequestBody ResourceCriteria criteria) throws Exception {
        ResponseEntity<List<ResourceDto>> res = null;
        List<Resource> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ResourceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated resources by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ResourceCriteria criteria) throws Exception {
        List<Resource> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ResourceDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets resource data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ResourceCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ResourceDto> findDtos(List<Resource> list){
        converter.initObject(true);
        List<ResourceDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ResourceDto> getDtoResponseEntity(ResourceDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ResourceRestAdmin(ResourceAdminService service, ResourceConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ResourceAdminService service;
    private final ResourceConverter converter;





}
