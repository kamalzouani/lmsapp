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

import {RoleDto} from "@/utils/zynerator/dto/RoleDto.model";
import RoleService from "@/utils/zynerator/service/RoleService";
import {Password} from "primereact/password";
import {InstructorAdminService} from '@/controller/service/admin/instructor/InstructorAdminService.service';
import  {InstructorDto}  from '@/controller/model/instructor/Instructor.model';
import {InstructorCriteria} from "@/controller/criteria/instructor/InstructorCriteria.model";

import {TFunction} from "i18next";
import {Toast} from "primereact/toast";
import useCreateHook from "@/utils/zyhook/useCreate.hook";



type InstructorCreateAdminType = {
    visible: boolean,
    onClose: () => void,
    add: (item: InstructorDto) => void,
    showToast: React.Ref<Toast>,
    list: InstructorDto[],
    service: InstructorAdminService,
    t: TFunction
}
const Create: React.FC<InstructorCreateAdminType> = ({visible, onClose, add, showToast, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
        return errorMessages.length == 0 ;
    }
    const emptyItem = new InstructorDto();
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
        } = useCreateHook<InstructorDto, InstructorCriteria>({list, emptyItem, onClose, add, showToast,service, isFormValid})
    const [roles, setRoles] = useState<RoleDto[]>([]);


    const roleService = new RoleService();
    useEffect(() => {
        roleService.getList().then(({data}) => setRoles(data)).catch(error => console.log(error));
    }, []);








    const itemDialogFooter = ( <>
        <Button label={t("cancel")} icon="pi pi-times" text onClick={hideDialog} />
        <Button label={t("save")} icon="pi pi-check" onClick={saveItem} /> </>
    );

return(
        <Dialog visible={visible} style={{width: '70vw'}} header={t("instructor.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("instructor.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="bio">{t("instructor.bio")}</label>
                        <span className="p-float-label">
                        <InputTextarea id="bio" value={item.bio} onChange={(e) => onInputTextChange(e, 'bio')} rows={5} cols={30}/>
                    </span>
                    </div>
                    <div className="field col-6">
                    <div  className="label-inputswitch">
                        <label htmlFor="credentialsNonExpired">{t("instructor.credentialsNonExpired")}</label>
                        <span className="p-float-label">
                        <InputSwitch  id="credentialsNonExpired" checked={item.credentialsNonExpired} onChange={(e) => onBooleanInputChange(e, 'credentialsNonExpired')} />
                        </span>
                    </div>
                    </div>
                    <div className="field col-6">
                    <div  className="label-inputswitch">
                        <label htmlFor="accountNonExpired">{t("instructor.accountNonExpired")}</label>
                        <span className="p-float-label">
                        <InputSwitch  id="accountNonExpired" checked={item.accountNonExpired} onChange={(e) => onBooleanInputChange(e, 'accountNonExpired')} />
                        </span>
                    </div>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="username">{t("instructor.username")}</label>
                        <InputText id="username" value={item.username} onChange={(e) => onInputTextChange(e, 'username')} required className={classNames({'p-invalid': submitted && !item.username})} />
                        {submitted && !item.username && <small className="p-invalid">Username is required.</small>}
                    </div>
                    <div className="field col-6">
                    <div  className="label-inputswitch">
                        <label htmlFor="passwordChanged">{t("instructor.passwordChanged")}</label>
                        <span className="p-float-label">
                        <InputSwitch  id="passwordChanged" checked={item.passwordChanged} onChange={(e) => onBooleanInputChange(e, 'passwordChanged')} />
                        </span>
                    </div>
                    </div>
                    <div className="field col-6">
                    <div  className="label-inputswitch">
                        <label htmlFor="accountNonLocked">{t("instructor.accountNonLocked")}</label>
                        <span className="p-float-label">
                        <InputSwitch  id="accountNonLocked" checked={item.accountNonLocked} onChange={(e) => onBooleanInputChange(e, 'accountNonLocked')} />
                        </span>
                    </div>
                    </div>
                        <div className="field col-6">
                            <label htmlFor="password">{t("instructor.password")}</label>
                            <Password id="password" value={item.password} onChange={(e) => onInputTextChange(e, 'password')} required className={classNames({'p-invalid': submitted && !item.password})} />
                            {submitted && !item.password && <small className="p-invalid">Password is required.</small>}
                        </div>
                    <div className="field col-6">
                        <label htmlFor="email">{t("instructor.email")}</label>
                        <InputText id="email" value={item.email} onChange={(e) => onInputTextChange(e, 'email')} required className={classNames({'p-invalid': submitted && !item.email})} />
                        {submitted && !item.email && <small className="p-invalid">Email is required.</small>}
                    </div>
                    <div className="field col-6">
                    <div  className="label-inputswitch">
                        <label htmlFor="enabled">{t("instructor.enabled")}</label>
                        <span className="p-float-label">
                        <InputSwitch  id="enabled" checked={item.enabled} onChange={(e) => onBooleanInputChange(e, 'enabled')} />
                        </span>
                    </div>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="roles">Roles</label>
                        <MultiSelect value={item.roles} options={roles} optionLabel="label" display="chip"
                                     placeholder="select roles" maxSelectedLabels={1}
                                     onChange={(e) => onMultiSelectChange(e, 'roles')}/>
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Create;
