import {Button} from 'primereact/button';
import {Column} from 'primereact/column';
import {Dropdown, DropdownChangeEvent} from 'primereact/dropdown';
import {TabView, TabPanel} from 'primereact/tabview';
import {Dialog} from 'primereact/dialog';
import {InputNumber, InputNumberChangeEvent} from 'primereact/inputnumber';
import {InputText} from 'primereact/inputtext';
import {classNames} from 'primereact/utils';
import { InputTextarea } from 'primereact/inputtextarea';
import React, {useEffect, useState} from 'react';
import {Calendar} from 'primereact/calendar';
import { format } from 'date-fns';
import { parse } from 'date-fns';
import { InputSwitch } from 'primereact/inputswitch';
import {MultiSelect, MultiSelectChangeEvent} from 'primereact/multiselect';


import {MessageService} from '@/utils/zynerator/service/MessageService';


import {TFunction} from "i18next";
import {Toast} from "primereact/toast";

import useEditHook from "@/utils/zyhook/useEdit.hook";


import {ModelPermissionUserAdminService} from '@/utils/zynerator/security/service/ModelPermissionUserAdminService.service';
import  {ModelPermissionUserDto}  from '@/utils/zynerator/security/model/ModelPermissionUser.model';
import {ModelPermissionUserCriteria} from "@/utils/zynerator/security/criteria/ModelPermissionUserCriteria.model";


import {UserDto} from '@/utils/zynerator/security/model/User.model';
import {UserAdminService} from '@/utils/zynerator/security/service/UserAdminService.service';
import {ModelPermissionDto} from '@/utils/zynerator/security/model/ModelPermission.model';
import {ModelPermissionAdminService} from '@/utils/zynerator/security/service/ModelPermissionAdminService.service';
import {ActionPermissionDto} from '@/utils/zynerator/security/model/ActionPermission.model';
import {ActionPermissionAdminService} from '@/utils/zynerator/security/service/ActionPermissionAdminService.service';


type ModelPermissionUserEditAdminType = {
    visible: boolean,
    onClose: () => void,
    showToast: React.Ref<Toast>,
    selectedItem: ModelPermissionUserDto
    update: (item: ModelPermissionUserDto) => void,
    list: ModelPermissionUserDto[],
    service: ModelPermissionUserAdminService,
    t: TFunction
}
const Edit: React.FC<ModelPermissionUserEditAdminType> = ({visible, onClose, showToast, selectedItem, update, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
        return errorMessages.length == 0 ;
    }
    const emptyItem = new ModelPermissionUserDto();


    const {
        item,
        setItem,
        submitted,
        setSubmitted,
        activeIndex,
        setActiveIndex,
        activeTab,
        setActiveTab,
        onInputTextChange,
        onInputDateChange,
        onInputNumerChange,
        onMultiSelectChange,
        onBooleanInputChange,
        onDropdownChange,
        onTabChange,
        hideDialog,
        editItem,
        formateDate,
        parseToIsoFormat,
        adaptDate
        } = useEditHook<ModelPermissionUserDto, ModelPermissionUserCriteria>({list, selectedItem, onClose, update, showToast,service, t, isFormValid})

    const [modelPermissions, setModelPermissions] = useState<ModelPermissionDto[]>([]);
    const [actionPermissions, setActionPermissions] = useState<ActionPermissionDto[]>([]);
    const [users, setUsers] = useState<UserDto[]>([]);


    const userAdminService = new UserAdminService();
    const modelPermissionAdminService = new ModelPermissionAdminService();
    const actionPermissionAdminService = new ActionPermissionAdminService();
    useEffect(() => {
    actionPermissionAdminService.getList().then(({data}) => setActionPermissions(data)).catch(error => console.log(error));
    modelPermissionAdminService.getList().then(({data}) => setModelPermissions(data)).catch(error => console.log(error));
    userAdminService.getList().then(({data}) => setUsers(data)).catch(error => console.log(error));


        }, []);







    const itemDialogFooter = ( <>
        <Button label="Cancel" icon="pi pi-times" text onClick={hideDialog} />
        <Button label="Save" icon="pi pi-check" onClick={editItem} /> </>
    );



    return(
    <Dialog visible={visible} style={{width: '70vw'}} header={t("modelPermissionUser.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog}>
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("modelPermissionUser.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <div  className="label-inputswitch">
                            <label htmlFor="value">{t("modelPermissionUser.value")}</label>
                            <span className="p-float-label">
                                <InputSwitch  id="value" checked={item.value as boolean} onChange={(e) => onBooleanInputChange(e, 'value')} />
                            </span>
                        </div>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="subAttribute">{t("modelPermissionUser.subAttribute")}</label>
                        <InputText id="subAttribute" value={item ? item.subAttribute : ''} onChange={(e) => onInputTextChange(e, 'subAttribute')} required className={classNames({'p-invalid': submitted && !item.subAttribute})} />
                        {submitted && !item.subAttribute && <small className="p-invalid">SubAttribute is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="actionPermission">{t("modelPermissionUser.actionPermission")}</label>
                        <Dropdown  id="actionPermissionDropdown"  value={item ? item.actionPermission : ''} options={actionPermissions} onChange={(e) => onDropdownChange(e, 'actionPermission')}   placeholder="Sélectionnez un actionPermission" filter filterPlaceholder="Rechercher un actionPermission" optionLabel="id" showClear />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="modelPermission">{t("modelPermissionUser.modelPermission")}</label>
                        <Dropdown  id="modelPermissionDropdown"  value={item ? item.modelPermission : ''} options={modelPermissions} onChange={(e) => onDropdownChange(e, 'modelPermission')}   placeholder="Sélectionnez un modelPermission" filter filterPlaceholder="Rechercher un modelPermission" optionLabel="id" showClear />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="user">{t("modelPermissionUser.user")}</label>
                        <Dropdown  id="userDropdown"  value={item ? item.user : ''} options={users} onChange={(e) => onDropdownChange(e, 'user')}   placeholder="Sélectionnez un user" filter filterPlaceholder="Rechercher un user" optionLabel="id" showClear />
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Edit;


