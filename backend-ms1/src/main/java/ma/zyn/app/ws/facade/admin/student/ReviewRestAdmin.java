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

import ma.zyn.app.bean.core.student.Review;
import ma.zyn.app.dao.criteria.core.student.ReviewCriteria;
import ma.zyn.app.service.facade.admin.student.ReviewAdminService;
import ma.zyn.app.ws.converter.student.ReviewConverter;
import ma.zyn.app.ws.dto.student.ReviewDto;
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
@RequestMapping("/api/admin/review/")
public class ReviewRestAdmin {




    @Operation(summary = "Finds a list of all reviews")
    @GetMapping("")
    public ResponseEntity<List<ReviewDto>> findAll() throws Exception {
        ResponseEntity<List<ReviewDto>> res = null;
        List<Review> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ReviewDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a review by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ReviewDto> findById(@PathVariable Long id) {
        Review t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ReviewDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  review")
    @PostMapping("")
    public ResponseEntity<ReviewDto> save(@RequestBody ReviewDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Review myT = converter.toItem(dto);
            Review t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ReviewDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  review")
    @PutMapping("")
    public ResponseEntity<ReviewDto> update(@RequestBody ReviewDto dto) throws Exception {
        ResponseEntity<ReviewDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Review t = service.findById(dto.getId());
            converter.copy(dto,t);
            Review updated = service.update(t);
            ReviewDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of review")
    @PostMapping("multiple")
    public ResponseEntity<List<ReviewDto>> delete(@RequestBody List<ReviewDto> dtos) throws Exception {
        ResponseEntity<List<ReviewDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Review> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified review")
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


    @Operation(summary = "Finds a review and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ReviewDto> findWithAssociatedLists(@PathVariable Long id) {
        Review loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ReviewDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds reviews by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ReviewDto>> findByCriteria(@RequestBody ReviewCriteria criteria) throws Exception {
        ResponseEntity<List<ReviewDto>> res = null;
        List<Review> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ReviewDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated reviews by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ReviewCriteria criteria) throws Exception {
        List<Review> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ReviewDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets review data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ReviewCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ReviewDto> findDtos(List<Review> list){
        converter.initObject(true);
        List<ReviewDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ReviewDto> getDtoResponseEntity(ReviewDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ReviewRestAdmin(ReviewAdminService service, ReviewConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ReviewAdminService service;
    private final ReviewConverter converter;





}
