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

import ma.zyn.app.bean.core.course.Category;
import ma.zyn.app.dao.criteria.core.course.CategoryCriteria;
import ma.zyn.app.service.facade.admin.course.CategoryAdminService;
import ma.zyn.app.ws.converter.course.CategoryConverter;
import ma.zyn.app.ws.dto.course.CategoryDto;
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
@RequestMapping("/api/admin/category/")
public class CategoryRestAdmin {




    @Operation(summary = "Finds a list of all categorys")
    @GetMapping("")
    public ResponseEntity<List<CategoryDto>> findAll() throws Exception {
        ResponseEntity<List<CategoryDto>> res = null;
        List<Category> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategoryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all categorys")
    @GetMapping("optimized")
    public ResponseEntity<List<CategoryDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CategoryDto>> res = null;
        List<Category> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategoryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a category by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id) {
        Category t = service.findById(id);
        if (t != null) {
            CategoryDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a category by label")
    @GetMapping("label/{label}")
    public ResponseEntity<CategoryDto> findByLabel(@PathVariable String label) {
	    Category t = service.findByReferenceEntity(new Category(label));
        if (t != null) {
            CategoryDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  category")
    @PostMapping("")
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto dto) throws Exception {
        if(dto!=null){
            Category myT = converter.toItem(dto);
            Category t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CategoryDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  category")
    @PutMapping("")
    public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto dto) throws Exception {
        ResponseEntity<CategoryDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Category t = service.findById(dto.getId());
            converter.copy(dto,t);
            Category updated = service.update(t);
            CategoryDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of category")
    @PostMapping("multiple")
    public ResponseEntity<List<CategoryDto>> delete(@RequestBody List<CategoryDto> dtos) throws Exception {
        ResponseEntity<List<CategoryDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Category> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified category")
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


    @Operation(summary = "Finds a category and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CategoryDto> findWithAssociatedLists(@PathVariable Long id) {
        Category loaded =  service.findWithAssociatedLists(id);
        CategoryDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds categorys by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CategoryDto>> findByCriteria(@RequestBody CategoryCriteria criteria) throws Exception {
        ResponseEntity<List<CategoryDto>> res = null;
        List<Category> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategoryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated categorys by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CategoryCriteria criteria) throws Exception {
        List<Category> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<CategoryDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets category data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CategoryCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CategoryDto> findDtos(List<Category> list){
        List<CategoryDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CategoryDto> getDtoResponseEntity(CategoryDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public CategoryRestAdmin(CategoryAdminService service, CategoryConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CategoryAdminService service;
    private final CategoryConverter converter;





}
