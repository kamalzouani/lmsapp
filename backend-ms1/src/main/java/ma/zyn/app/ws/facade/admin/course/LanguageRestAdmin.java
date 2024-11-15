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

import ma.zyn.app.bean.core.course.Language;
import ma.zyn.app.dao.criteria.core.course.LanguageCriteria;
import ma.zyn.app.service.facade.admin.course.LanguageAdminService;
import ma.zyn.app.ws.converter.course.LanguageConverter;
import ma.zyn.app.ws.dto.course.LanguageDto;
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
@RequestMapping("/api/admin/language/")
public class LanguageRestAdmin {




    @Operation(summary = "Finds a list of all languages")
    @GetMapping("")
    public ResponseEntity<List<LanguageDto>> findAll() throws Exception {
        ResponseEntity<List<LanguageDto>> res = null;
        List<Language> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<LanguageDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all languages")
    @GetMapping("optimized")
    public ResponseEntity<List<LanguageDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<LanguageDto>> res = null;
        List<Language> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<LanguageDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a language by id")
    @GetMapping("id/{id}")
    public ResponseEntity<LanguageDto> findById(@PathVariable Long id) {
        Language t = service.findById(id);
        if (t != null) {
            LanguageDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a language by label")
    @GetMapping("label/{label}")
    public ResponseEntity<LanguageDto> findByLabel(@PathVariable String label) {
	    Language t = service.findByReferenceEntity(new Language(label));
        if (t != null) {
            LanguageDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  language")
    @PostMapping("")
    public ResponseEntity<LanguageDto> save(@RequestBody LanguageDto dto) throws Exception {
        if(dto!=null){
            Language myT = converter.toItem(dto);
            Language t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                LanguageDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  language")
    @PutMapping("")
    public ResponseEntity<LanguageDto> update(@RequestBody LanguageDto dto) throws Exception {
        ResponseEntity<LanguageDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Language t = service.findById(dto.getId());
            converter.copy(dto,t);
            Language updated = service.update(t);
            LanguageDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of language")
    @PostMapping("multiple")
    public ResponseEntity<List<LanguageDto>> delete(@RequestBody List<LanguageDto> dtos) throws Exception {
        ResponseEntity<List<LanguageDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Language> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified language")
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


    @Operation(summary = "Finds a language and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<LanguageDto> findWithAssociatedLists(@PathVariable Long id) {
        Language loaded =  service.findWithAssociatedLists(id);
        LanguageDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds languages by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<LanguageDto>> findByCriteria(@RequestBody LanguageCriteria criteria) throws Exception {
        ResponseEntity<List<LanguageDto>> res = null;
        List<Language> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<LanguageDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated languages by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody LanguageCriteria criteria) throws Exception {
        List<Language> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<LanguageDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets language data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody LanguageCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<LanguageDto> findDtos(List<Language> list){
        List<LanguageDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<LanguageDto> getDtoResponseEntity(LanguageDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public LanguageRestAdmin(LanguageAdminService service, LanguageConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final LanguageAdminService service;
    private final LanguageConverter converter;





}
