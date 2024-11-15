import {Button} from 'primereact/button';
import {Column} from 'primereact/column';
import {TabView, TabPanel} from 'primereact/tabview';
import {Dialog} from 'primereact/dialog';
import {InputNumber, InputNumberChangeEvent} from 'primereact/inputnumber';
import {InputText} from 'primereact/inputtext';
import {classNames} from 'primereact/utils';
import { InputTextarea } from 'primereact/inputtextarea';
import React, {useEffect, useState} from 'react';
import { Calendar } from 'primereact/calendar';
import { format } from 'date-fns';
import {InputSwitch, InputSwitchChangeEvent} from 'primereact/inputswitch';
import {Dropdown, DropdownChangeEvent} from 'primereact/dropdown';
import {MultiSelect, MultiSelectChangeEvent} from 'primereact/multiselect';
import {MessageService} from '@/utils/zynerator/service/MessageService';

import {ModelPermissionUserAdminService} from '@/utils/zynerator/security/service/ModelPermissionUserAdminService.service';
import  {ModelPermissionUserDto}  from '@/utils/zynerator/security/model/ModelPermissionUser.model';
import {ModelPermissionUserCriteria} from "@/utils/zynerator/security/criteria/ModelPermissionUserCriteria.model";

import {UserDto} from '@/utils/zynerator/security/model/User.model';
import {UserAdminService} from '@/utils/zynerator/security/service/UserAdminService.service';
import {ModelPermissionDto} from '@/utils/zynerator/security/model/ModelPermission.model';
import {ModelPermissionAdminService} from '@/utils/zynerator/security/service/ModelPermissionAdminService.service';
import {ActionPermissionDto} from '@/utils/zynerator/security/model/ActionPermission.model';
import {ActionPermissionAdminService} from '@/utils/zynerator/security/service/ActionPermissionAdminService.service';
import {TFunction} from "i18next";
import {Toast} from "primereact/toast";
import useCreateHook from "@/utils/zyhook/useCreate.hook";



type ModelPermissionUserCreateAdminType = {
    visible: boolean,
    onClose: () => void,
    add: (item: ModelPermissionUserDto) => void,
    showToast: React.Ref<Toast>,
    list: ModelPermissionUserDto[],
    service: ModelPermissionUserAdminService,
    t: TFunction
}
const Create: React.FC<ModelPermissionUserCreateAdminType> = ({visible, onClose, add, showToast, list, service, t}) => {


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
            onTabChange,
            onDropdownChange,
            hideDialog,
            saveItem,
            formateDate
        } = useCreateHook<ModelPermissionUserDto, ModelPermissionUserCriteria>({list, emptyItem, onClose, add, showToast,service, isFormValid})
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
        <Button label={t("cancel")} icon="pi pi-times" text onClick={hideDialog} />
        <Button label={t("save")} icon="pi pi-check" onClick={saveItem} /> </>
    );

return(
        <Dialog visible={visible} style={{width: '70vw'}} header={t("modelPermissionUser.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
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
                        <InputText id="subAttribute" value={item.subAttribute} onChange={(e) => onInputTextChange(e, 'subAttribute')} required className={classNames({'p-invalid': submitted && !item.subAttribute})} />
                        {submitted && !item.subAttribute && <small className="p-invalid">SubAttribute is required.</small>}
                    </div>
                    <div className="field col-5">
                        <label htmlFor="actionPermission">{t("modelPermissionUser.actionPermission")}</label>
                        <Dropdown  id="actionPermissionDropdown"  value={item.actionPermission} options={actionPermissions} onChange={(e) => onDropdownChange(e, 'actionPermission')}   placeholder={t("modelPermissionUser.actionPermissionPlaceHolder")} filter filterPlaceholder={t("modelPermissionUser.actionPermissionPlaceHolderFilter")} optionLabel="id" showClear/>
                    </div>
                    <div className="field col-5">
                        <label htmlFor="modelPermission">{t("modelPermissionUser.modelPermission")}</label>
                        <Dropdown  id="modelPermissionDropdown"  value={item.modelPermission} options={modelPermissions} onChange={(e) => onDropdownChange(e, 'modelPermission')}   placeholder={t("modelPermissionUser.modelPermissionPlaceHolder")} filter filterPlaceholder={t("modelPermissionUser.modelPermissionPlaceHolderFilter")} optionLabel="id" showClear/>
                    </div>
                    <div className="field col-5">
                        <label htmlFor="user">{t("modelPermissionUser.user")}</label>
                        <Dropdown  id="userDropdown"  value={item.user} options={users} onChange={(e) => onDropdownChange(e, 'user')}   placeholder={t("modelPermissionUser.userPlaceHolder")} filter filterPlaceholder={t("modelPermissionUser.userPlaceHolderFilter")} optionLabel="id" showClear/>
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Create;
