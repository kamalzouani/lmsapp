"use client"
import {Button} from 'primereact/button';
import {Column} from 'primereact/column';


import {DataTable} from 'primereact/datatable';
import {Dialog} from 'primereact/dialog';
import {FileUpload} from 'primereact/fileupload';
import {InputText} from 'primereact/inputtext';
import {Toast} from 'primereact/toast';
import {Toolbar} from 'primereact/toolbar';
import React, {useEffect, useRef, useState} from 'react';
import {Paginator, PaginatorPageChangeEvent} from 'primereact/paginator';
import {Card} from 'primereact/card';
import {Calendar} from 'primereact/calendar';
import {InputNumber} from 'primereact/inputnumber';
import {Dropdown} from 'primereact/dropdown';
import {format} from "date-fns";
import { useTranslation } from 'react-i18next';

import useListHook from "@/utils/zyhook/useListhook";


import {CourseAdminService} from '@/controller/service/admin/course/CourseAdminService.service';
import {CourseDto}  from '@/controller/model/course/Course.model';
import {CourseCriteria} from '@/controller/criteria/course/CourseCriteria.model';

import {InstructorDto} from '@/controller/model/instructor/Instructor.model';
import {InstructorAdminService} from '@/controller/service/admin/instructor/InstructorAdminService.service';
import {CategoryDto} from '@/controller/model/course/Category.model';
import {CategoryAdminService} from '@/controller/service/admin/course/CategoryAdminService.service';
import {LevelDto} from '@/controller/model/course/Level.model';
import {LevelAdminService} from '@/controller/service/admin/course/LevelAdminService.service';
import {LanguageDto} from '@/controller/model/course/Language.model';
import {LanguageAdminService} from '@/controller/service/admin/course/LanguageAdminService.service';
import {CurriculumDto} from '@/controller/model/course/Curriculum.model';
import {CurriculumAdminService} from '@/controller/service/admin/course/CurriculumAdminService.service';


import Edit from '@/components/admin/view/course/course/edit/course-edit-admin.component';
import Create from '@/components/admin/view/course/course/create/course-create-admin.component';
import View from '@/components/admin/view/course/course/view/course-view-admin.component';



const List = () => {

    const { t } = useTranslation();

    const emptyItem = new CourseDto();
    const emptyCriteria = new CourseCriteria();
    const service = new CourseAdminService();


    const {
        items,
        showSearch,
        deleteItemDialog,
        item,
        selectedItems,
        setSelectedItems,
        hideDeleteItemDialog,
        globalFilter,
        setGlobalFilter,
        showCreateDialog,
        setShowCreateDialog,
        showEditDialog,
        setShowEditDialog,
        showViewDialog,
        setShowViewDialog,
        selectedItem,
        setSelectedItem,
        rows,
        totalRecords,
        criteria,
        setCriteria,
        first,
        fetchItems,
        toast,
        dt,
        findByCriteriaShow,
        handleCancelClick,
        confirmDeleteSelected,
        exportCSV,
        deleteItem,
        deleteItemDialogFooter,
        leftToolbarTemplate,
        rightToolbarTemplate,
        actionBodyTemplate,
        CustomBooleanCell,
        handleValidateClick,
        onPage,
        showCreateModal,
        showEditModal,
        showViewModal,
        add,
        update,
        confirmDeleteItem,
        statusBodyTemplate,
        formateDate,
        deleteSelectedItems,
        deleteItemsDialog,
        deleteItemsDialogFooter,
        hideDeleteItemsDialog,
        filter,
        activateSecurityConstraint,
        listPremisions,
    } = useListHook<CourseDto, CourseCriteria>({ emptyItem, emptyCriteria,service, t})



    const [instructors, setInstructors] = useState<InstructorDto[]>([]);
    const [categorys, setCategorys] = useState<CategoryDto[]>([]);
    const [levels, setLevels] = useState<LevelDto[]>([]);
    const [languages, setLanguages] = useState<LanguageDto[]>([]);
    const [curriculums, setCurriculums] = useState<CurriculumDto[]>([]);

    const instructorAdminService = new InstructorAdminService();
    const categoryAdminService = new CategoryAdminService();
    const levelAdminService = new LevelAdminService();
    const languageAdminService = new LanguageAdminService();
    const curriculumAdminService = new CurriculumAdminService();

    useEffect(() => {
        activateSecurityConstraint("Course")
        instructorAdminService.getList().then(({data}) => setInstructors(data)).catch(error => console.log(error));
        categoryAdminService.getList().then(({data}) => setCategorys(data)).catch(error => console.log(error));
        levelAdminService.getList().then(({data}) => setLevels(data)).catch(error => console.log(error));
        languageAdminService.getList().then(({data}) => setLanguages(data)).catch(error => console.log(error));
        curriculumAdminService.getList().then(({data}) => setCurriculums(data)).catch(error => console.log(error));
        fetchItems(criteria);
    }, []);



    const header = (
    <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
        <h5 className="m-0">{t("course.header")}</h5>
        <span className="block mt-2 md:mt-0 p-input-icon-left"><i className="pi pi-search"/>
            <InputText type="search" onInput={(e) => filter(e)}
                       placeholder={t("search")}/> </span>
    </div>
    );
    return (
    <div className="grid crud-demo">
        {listPremisions ?(
        <div className="col-12">
            <div className="card">
                <Toast ref={toast} />
                <Toolbar className="mb-4" left={leftToolbarTemplate} right={rightToolbarTemplate}></Toolbar>
                {findByCriteriaShow && (
                <Card title={t("search")} className="mb-5">
                        <div className="grid">
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="1">{t("course.code")}</label>
                                <InputText id="1" value={criteria.code} onChange={(e) => setCriteria({ ...criteria, code: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="2">{t("course.label")}</label>
                                <InputText id="2" value={criteria.label} onChange={(e) => setCriteria({ ...criteria, label: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="3">{t("course.description")}</label>
                                <InputText id="3" value={criteria.description} onChange={(e) => setCriteria({ ...criteria, description: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="4-1">{t("course.startDateMin")}</label>
                                <Calendar id="4-1" value={criteria.startDateFrom} onChange={(e) => setCriteria({ ...criteria, startDateFrom: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="4-2">{t("course.startDateMax")}</label>
                                <Calendar id="4-2" value={criteria.startDateTo} onChange={(e) => setCriteria({ ...criteria, startDateTo: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="5-1">{t("course.endDateMin")}</label>
                                <Calendar id="5-1" value={criteria.endDateFrom} onChange={(e) => setCriteria({ ...criteria, endDateFrom: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="5-2">{t("course.endDateMax")}</label>
                                <Calendar id="5-2" value={criteria.endDateTo} onChange={(e) => setCriteria({ ...criteria, endDateTo: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="6-1">{t("course.durationMin")}</label>
                                <InputNumber id="6-1" value={criteria.durationMin} onChange={(e) => setCriteria({ ...criteria, durationMin: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="6-2">{t("course.durationMax")}  </label>
                                <InputNumber id="6-2" value={criteria.durationMax} onChange={(e) => setCriteria({ ...criteria, durationMax: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="7-1">{t("course.priceMin")}</label>
                                <InputNumber id="7-1" value={criteria.priceMin} onChange={(e) => setCriteria({ ...criteria, priceMin: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="7-2">{t("course.priceMax")}  </label>
                                <InputNumber id="7-2" value={criteria.priceMax} onChange={(e) => setCriteria({ ...criteria, priceMax: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="8">{t("course.instructorPlaceHolder")}</label>
                                <Dropdown id="8" value={criteria.instructor} options={instructors} onChange={(e) => setCriteria({ ...criteria, instructor: e.target.value })} optionLabel="email" filter showClear/>
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="9">{t("course.categoryPlaceHolder")}</label>
                                <Dropdown id="9" value={criteria.category} options={categorys} onChange={(e) => setCriteria({ ...criteria, category: e.target.value })} optionLabel="label" filter showClear/>
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="10">{t("course.levelPlaceHolder")}</label>
                                <Dropdown id="10" value={criteria.level} options={levels} onChange={(e) => setCriteria({ ...criteria, level: e.target.value })} optionLabel="label" filter showClear/>
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="11">{t("course.languagePlaceHolder")}</label>
                                <Dropdown id="11" value={criteria.language} options={languages} onChange={(e) => setCriteria({ ...criteria, language: e.target.value })} optionLabel="label" filter showClear/>
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="12">{t("course.requirements")}</label>
                                <InputText id="12" value={criteria.requirements} onChange={(e) => setCriteria({ ...criteria, requirements: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="13">{t("course.learningOutcomes")}</label>
                                <InputText id="13" value={criteria.learningOutcomes} onChange={(e) => setCriteria({ ...criteria, learningOutcomes: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="14">{t("course.curriculumPlaceHolder")}</label>
                                <Dropdown id="14" value={criteria.curriculum} options={curriculums} onChange={(e) => setCriteria({ ...criteria, curriculum: e.target.value })} optionLabel="id" filter showClear/>
                            </div>
                        </div>
                        <div style={{ marginTop : '1rem', display: 'flex', justifyContent: 'flex-end' }} >
                            <Button label={t("validate")} icon="pi pi-sort-amount-down" className="p-button-info mr-2" onClick={handleValidateClick} />
                            <Button label={t("cancel")} className="p-button-secondary mr-2"  icon="pi pi-times" onClick={handleCancelClick} />
                        </div>
                </Card>
                )}
                <DataTable ref={dt} value={items} selection={selectedItems as any} onSelectionChange={(e) => setSelectedItems(e.value as any)} dataKey="id" className="datatable-responsive" filters={globalFilter} header={header} responsiveLayout="scroll" >
                    <Column selectionMode="multiple" headerStyle={{ width: '4rem' }}> </Column>
                    
                    <Column field="code" header={t("course.code")} sortable></Column>
                    
                    
                    <Column field="label" header={t("course.label")} sortable></Column>
                    
                    
                    <Column field="startDate" header={t("course.startDate")} sortable body={formateDate("startDate")}></Column>
                    
                    
                    <Column field="endDate" header={t("course.endDate")} sortable body={formateDate("endDate")}></Column>
                    
                    
                    <Column field="duration" header={t("course.duration")} sortable></Column>
                    
                    
                    <Column field="price" header={t("course.price")} sortable></Column>
                    
                    
                    <Column field="instructor.email" header={t("course.instructor")} sortable ></Column>
                    
                    
                    <Column field="category.label" header={t("course.category")} sortable ></Column>
                    
                    
                    <Column field="level.label" header={t("course.level")} sortable ></Column>
                    
                     {/* 
                    <Column field="language.label" header={t("course.language")} sortable ></Column>
                     */} 
                     {/* 
                    <Column field="requirements" header={t("course.requirements")} sortable></Column>
                     */} 
                     {/* 
                    <Column field="learningOutcomes" header={t("course.learningOutcomes")} sortable></Column>
                     */} 
                     {/* 
                    <Column field="curriculum.id" header={t("course.curriculum")} sortable ></Column>
                     */} 
                    <Column header={t("actions")} body={actionBodyTemplate}></Column>
                </DataTable>
                <div className="p-d-flex p-ai-center p-jc-between">
                    <Paginator onPageChange={onPage} first={first} rows={rows} totalRecords={totalRecords} />
                </div>
                {showCreateDialog && <Create visible={showCreateDialog} onClose={() => setShowCreateDialog(false)} add={add} showToast={toast} list={items} service={service} t={t} />}

                {showEditDialog && <Edit  visible={showEditDialog} onClose={() =>  { setShowEditDialog(false); setSelectedItem(emptyItem); }} showToast={toast} selectedItem={selectedItem} update={update} list={items} service={service}   t={t} />}

                {showViewDialog && <View visible={showViewDialog} onClose={() =>  { setShowViewDialog(false); setSelectedItem(emptyItem); }} selectedItem={selectedItem}   t={t} />}
                <Dialog visible={deleteItemDialog} style={{width: '450px'}} header={t("confirm")} modal footer={deleteItemDialogFooter} onHide={hideDeleteItemDialog}>
                    <div className="flex align-items-center justify-content-center">
                    <i className="pi pi-exclamation-triangle mr-3" style={{fontSize: '2rem'}}/>
                    {item && (<span>{t("course.deleteCourseConfirmationMessage")} <b>{item.label}</b>?</span>)}
                    </div>
                </Dialog>

            <Dialog visible={deleteItemsDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteItemsDialogFooter} onHide={hideDeleteItemsDialog} >
                <div className="flex align-items-center justify-content-center">
                    <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                    {item && <span>{t("course.deleteCoursesConfirmationMessage")}</span>}
                </div>
            </Dialog>

            </div>
        </div>

        ):(
        <div className="card" style={{alignItems:"center",display:"flex",justifyContent:"center"}}>
            <p style={{alignItems:"center",display:"flex",justifyContent:"center"}}>
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" style={{width:"90px",height:"90px", color:"red"}}>
                <path stroke-linecap="round" stroke-linejoin="round" d="M12 9v3.75m-9.303 3.376c-.866 1.5.217 3.374 1.948 3.374h14.71c1.73 0 2.813-1.874 1.948-3.374L13.949 3.378c-.866-1.5-3.032-1.5-3.898 0L2.697 16.126ZM12 15.75h.007v.008H12v-.008Z" />
                </svg>
                Opess You don t have permission to access !
            </p>
        </div>
        )}
    </div>
    );
    };
export default List;

