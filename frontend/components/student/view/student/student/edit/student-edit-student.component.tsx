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
import {RoleDto} from "@/utils/zynerator/dto/RoleDto.model";
import RoleService from "@/utils/zynerator/service/RoleService";


import {TFunction} from "i18next";
import {Toast} from "primereact/toast";

import useEditHook from "@/utils/zyhook/useEdit.hook";


import {StudentStudentService} from '@/controller/service/student/student/StudentStudentService.service';
import  {StudentDto}  from '@/controller/model/student/Student.model';
import {StudentCriteria} from "@/controller/criteria/student/StudentCriteria.model";




type StudentEditStudentType = {
    visible: boolean,
    onClose: () => void,
    showToast: React.Ref<Toast>,
    selectedItem: StudentDto
    update: (item: StudentDto) => void,
    list: StudentDto[],
    service: StudentStudentService,
    t: TFunction
}
const Edit: React.FC<StudentEditStudentType> = ({visible, onClose, showToast, selectedItem, update, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
        return errorMessages.length == 0 ;
    }
    const emptyItem = new StudentDto();


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
        } = useEditHook<StudentDto, StudentCriteria>({list, selectedItem, onClose, update, showToast,service, t, isFormValid})

    const [roles, setRoles] = useState<RoleDto[]>([]);


    const roleService = new RoleService();
    useEffect(() => {


    roleService.getList().then(({data}) => setRoles(data)).catch(error => console.log(error));
        }, []);







    const itemDialogFooter = ( <>
        <Button label="Cancel" icon="pi pi-times" text onClick={hideDialog} />
        <Button label="Save" icon="pi pi-check" onClick={editItem} /> </>
    );



    return(
    <Dialog visible={visible} style={{width: '70vw'}} header={t("student.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog}>
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("student.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <div  className="label-inputswitch">
                            <label htmlFor="credentialsNonExpired">{t("student.credentialsNonExpired")}</label>
                            <span className="p-float-label">
                                <InputSwitch  id="credentialsNonExpired" checked={item.credentialsNonExpired} onChange={(e) => onBooleanInputChange(e, 'credentialsNonExpired')} />
                            </span>
                        </div>
                    </div>
                    <div className="field col-6">
                        <div  className="label-inputswitch">
                            <label htmlFor="accountNonExpired">{t("student.accountNonExpired")}</label>
                            <span className="p-float-label">
                                <InputSwitch  id="accountNonExpired" checked={item.accountNonExpired} onChange={(e) => onBooleanInputChange(e, 'accountNonExpired')} />
                            </span>
                        </div>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="username">{t("student.username")}</label>
                        <InputText id="username" value={item ? item.username : ''} onChange={(e) => onInputTextChange(e, 'username')} required className={classNames({'p-invalid': submitted && !item.username})} />
                        {submitted && !item.username && <small className="p-invalid">Username is required.</small>}
                    </div>
                    <div className="field col-6">
                        <div  className="label-inputswitch">
                            <label htmlFor="passwordChanged">{t("student.passwordChanged")}</label>
                            <span className="p-float-label">
                                <InputSwitch  id="passwordChanged" checked={item.passwordChanged} onChange={(e) => onBooleanInputChange(e, 'passwordChanged')} />
                            </span>
                        </div>
                    </div>
                    <div className="field col-6">
                        <div  className="label-inputswitch">
                            <label htmlFor="accountNonLocked">{t("student.accountNonLocked")}</label>
                            <span className="p-float-label">
                                <InputSwitch  id="accountNonLocked" checked={item.accountNonLocked} onChange={(e) => onBooleanInputChange(e, 'accountNonLocked')} />
                            </span>
                        </div>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="password">{t("student.password")}</label>
                        <InputNumber id="password" value={item ? item.password : 0} onChange={(e) => onInputNumerChange(e, 'password')}/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="email">{t("student.email")}</label>
                        <InputText id="email" value={item ? item.email : ''} onChange={(e) => onInputTextChange(e, 'email')} required className={classNames({'p-invalid': submitted && !item.email})} />
                        {submitted && !item.email && <small className="p-invalid">Email is required.</small>}
                    </div>
                    <div className="field col-6">
                        <div  className="label-inputswitch">
                            <label htmlFor="enabled">{t("student.enabled")}</label>
                            <span className="p-float-label">
                                <InputSwitch  id="enabled" checked={item.enabled} onChange={(e) => onBooleanInputChange(e, 'enabled')} />
                            </span>
                        </div>
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Edit;


