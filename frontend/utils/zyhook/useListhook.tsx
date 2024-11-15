import React, {useEffect, useRef, useState} from "react";
import {PaginatorPageChangeEvent} from "primereact/paginator";
import {Tag} from "primereact/tag";
import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";
import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";
import {Toast} from "primereact/toast";
import {DataTable} from "primereact/datatable";
import AbstractService from "@/utils/zynerator/service/AbstractService";
import {MessageService} from "@/utils/zynerator/service/MessageService";
import {Button} from "primereact/button";
import {TFunction} from "i18next";
import {FileUpload} from "primereact/fileupload";
import {InputText} from "primereact/inputtext";
import {format} from "date-fns";
import { FilterMatchMode } from "primereact/api";
import { ModelPermissionUserAdminService } from "@/utils/zynerator/security/service/ModelPermissionUserAdminService.service";
import { AuthService } from "../zynerator/security/Auth.service";

type ListHookType<T extends BaseDto, C extends BaseCriteria> = {
    emptyItem: T,
    emptyCriteria: C,
    service: AbstractService<T, C>,
    t: TFunction
}

const useListHook = <T extends BaseDto, C extends BaseCriteria>({
                                                                    emptyItem,
                                                                    emptyCriteria,
                                                                    service,
                                                                    t
                                                                }: ListHookType<T, C>) => {

    const [items, setItems] = useState<T[]>([]);
    const [deleteItemDialog, setDeleteItemDialog] = useState(false);
    const [deleteItemsDialog, setDeleteItemsDialog] = useState(false);
    const [item, setItem] = useState<T>(emptyItem);
    const [selectedItems, setSelectedItems] = useState<T[]>([]);
    const [globalFilter, setGlobalFilter] = useState({global: { value: null, matchMode: FilterMatchMode.CONTAINS }});
    const [showCreateDialog, setShowCreateDialog] = useState<boolean>(false);
    const [showEditDialog, setShowEditDialog] = useState<boolean>(false);
    const [showViewDialog, setShowViewDialog] = useState<boolean>(false);
    const [deletePremisions, setDeletePremision] = useState<boolean>(true);
    const [editPremisions, setEditePremision] = useState<boolean>(true);
    const [viewPremisions, setViewPremision] = useState<boolean>(true);
    const [createPremisions, setCreatePremision] = useState<boolean>(true);
    const [listPremisions, setListePremision] = useState<boolean>(true);
    const [selectedItem, setSelectedItem] = useState<T>(emptyItem);
    const [rows, setRows] = useState<number>(10);
    const [totalRecords, setTotalRecords] = useState(0);
    const [criteria, setCriteria] = useState(emptyCriteria);
    const [first, setFirst] = useState(0);
    const toast = useRef<Toast>(null);
    const dt = useRef<DataTable<T[]>>(null);
    const [findByCriteriaShow, setFindByCriteriaShow] = useState(false);
    const [isSearchTriggered, setIsSearchTriggered] = useState(false);

    const filter = (e:any)=>{
        e.currentTarget.value;
        const value = e.target.value;
        let _filters = { ...globalFilter };
        _filters['global'].value = value;
        setGlobalFilter(_filters);
    }

    const fetchItems = (criteria: C) => {
        service.findPaginatedByCriteria(criteria).then(({data}:any) => {
            setTotalRecords(data.dataSize);
            setItems(data.list);
        }).catch(error => console.log(error));
    };

    const handleCancelClick = () => {
        setCriteria(emptyCriteria);
        fetchItems(emptyCriteria);
        setIsSearchTriggered(false);
    };
    const showSearch = () => {
        setFindByCriteriaShow(!findByCriteriaShow);
    };
    const handleValidateClick = () => {
        fetchItems(criteria)
    };

    const onPage = (event: PaginatorPageChangeEvent) => {
        const updatedCriteria = {...criteria, page: event.page, maxResults: event.rows};
        setCriteria(updatedCriteria);
        setFirst(event.first);
        fetchItems(updatedCriteria);
    };


    const deleteItem = async () => {
        try {
            if(selectedItem && selectedItem.id !=null){
                service.delete(selectedItem?.id);
                setDeleteItemDialog(false);
                setItem(emptyItem);
                let _items = items.filter((val) => val.id !== selectedItem?.id);
                setItems(_items);
                MessageService.showSuccess(toast, 'Element Deleted');
            }
        } catch (error) {
            MessageService.showError(toast, 'Element Deletion Prob');
        }
    };

    const exportCSV = () => {
        dt.current?.exportCSV();
    };

    const confirmDeleteSelected = () => {
        setDeleteItemsDialog(true);
    };

    const deleteSelectedItems = async () => {
        service.deleteList(selectedItems);
        let _items = items.filter((val) => !selectedItems.includes(val));
        setItems(_items);
        setDeleteItemsDialog(false);
        setSelectedItems([]);
        MessageService.showSuccess(toast, 'Elements Deleted');
    };

    const showCreateModal = (): void => {
        setShowCreateDialog(true);
    };


    const showEditModal = (item: T) => {
        if(item && item.id!=null){
            service.findById(item.id).then(({data}) =>{
                setSelectedItem(data);
                setShowEditDialog(true);
            });
        }
    };

    const showViewModal = (item: T) => {
        if(item && item.id!=null){
            service.findById(item.id).then(({data}) =>{
                setSelectedItem(data);
                setShowViewDialog(true);
            });
        }
    };


    const add = (item: T) => {
        setItems([...items, item]);
    };

    const update = (updatedItem: T) => {
        const updatedList = items.map((item) => {
            if (item.id === updatedItem.id) {
                return updatedItem;
            }
            return item;
        });
        setItems(updatedList);
    };

    const hideDeleteItemsDialog = () => {
        setDeleteItemsDialog(false);
    };


    const confirmDeleteItem = (item: T) => {
        setSelectedItem(item);
        setDeleteItemDialog(true);
    };

    const statusBodyTemplate = (val: string, style: any) => {
        return <Tag value={val} severity={style}/>;
    };

    const hideDeleteItemDialog = () => {
        setDeleteItemDialog(false);
    };


    const formateDate = (field: string) => {
        return (rowData: any) => {
            if (rowData[field]) {
                return format(new Date(rowData[field]), "dd/MM/yyyy");
            }
        };
    };



    const CustomBooleanCell = (field: string ) => {
        return (rowData: any) => {
            return rowData[field] === true ? <i className="pi pi-check-circle" style={{ color: 'green' }}></i> : <i className="pi pi-times-circle" style={{ color: 'red' }}></i>;
        };
    };

    const deleteItemDialogFooter = (<>
            <Button label={t("no")} icon="pi pi-times" text onClick={hideDeleteItemDialog}/>
            <Button label={t("yes")} icon="pi pi-check" onClick={deleteItem}/>
        </>
    );

    const deleteItemsDialogFooter = (
        <>
            <Button label={t("no")} icon="pi pi-times" text onClick={hideDeleteItemsDialog} />
            <Button label={t("yes")} icon="pi pi-check" onClick={deleteSelectedItems} />
        </>
    );


    const leftToolbarTemplate = () => {
        return (
            <React.Fragment>
                <div className="my-2">
                    {createPremisions &&
                        <Button label={t("new")} icon="pi pi-plus" severity="info" className=" mr-2"
                                onClick={showCreateModal}/>
                    }

                    {deletePremisions &&
                        <Button label={t("delete")} icon="pi pi-trash" severity="danger" className=" mr-2"
                                onClick={confirmDeleteSelected} disabled={!selectedItems || !selectedItems.length}/>
                    }
                    <Button label={t("search")} icon={`pi pi-${findByCriteriaShow ? 'angle-down' : 'angle-right'}`}
                            className=" mr-2" severity="secondary" onClick={showSearch}/>
                </div>
            </React.Fragment>
        );
    };

    const rightToolbarTemplate = () => {
        return (
            <React.Fragment>
                <FileUpload mode="basic" accept="image/*" maxFileSize={1000000} chooseLabel={t("import")}
                            className="mr-2 inline-block"/>
                <Button label={t("export")} icon="pi pi-upload" severity="secondary" onClick={exportCSV}/>
            </React.Fragment>
        );
    };

    const actionBodyTemplate = (rowData: T) => {
        return (<div style={{ width: "140px" }}>
                {editPremisions&&
                    <Button icon="pi pi-pencil"  severity="info" className="mr-1"
                            onClick={() => showEditModal(rowData)}/>
                }
                {deletePremisions &&
                    <Button icon="pi pi-trash"  severity="danger" className="mr-1"
                            onClick={() => confirmDeleteItem(rowData)}/>
                }
                {viewPremisions &&
                    <Button icon="pi pi-eye"  severity="secondary" className="mr-1"
                            onClick={() => showViewModal(rowData)}/>
                }
            </div>
        );
    };


    const modelPermissionUserAdminService=new ModelPermissionUserAdminService   ()
    const authService = new AuthService();
    const activateSecurityConstraint=(entityName: string)=> {
        const tokenDecoded = authService.decodeJWT();
        if (tokenDecoded && typeof tokenDecoded !== 'string') {
            const { sub ,roles} = tokenDecoded;
            modelPermissionUserAdminService.findByUserUsernameAndModelPermissionReferenceAndActionPermissionReference(sub,entityName,"list").then(({data}) =>setListePremision(data)).catch(error => console.log(error));
            modelPermissionUserAdminService.findByUserUsernameAndModelPermissionReferenceAndActionPermissionReference(sub,entityName,"create").then(({data}) =>setCreatePremision(data)).catch(error => console.log(error));
            modelPermissionUserAdminService.findByUserUsernameAndModelPermissionReferenceAndActionPermissionReference(sub,entityName,"delete").then(({data}) =>setDeletePremision(data)).catch(error => console.log(error));
            modelPermissionUserAdminService.findByUserUsernameAndModelPermissionReferenceAndActionPermissionReference(sub,entityName,"edit").then(({data}) =>setEditePremision(data)).catch(error => console.log(error));
            modelPermissionUserAdminService.findByUserUsernameAndModelPermissionReferenceAndActionPermissionReference(sub,entityName,"view").then(({data}) =>setViewPremision(data)).catch(error => console.log(error));
        }
    }

    return {
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
        setDeletePremision,
        deletePremisions,
        setEditePremision,
        editPremisions,
        setViewPremision,
        setCreatePremision,
        listPremisions,
        activateSecurityConstraint
    }
};

export default useListHook;
