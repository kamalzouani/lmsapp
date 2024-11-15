"use client"
import {InputText} from "primereact/inputtext";
import {Button} from "primereact/button";
import React, {useEffect, useRef, useState} from "react";
import {Password} from "primereact/password";
import {Toast} from "primereact/toast";
import {UserDto} from "@/utils/zynerator/security/model/User.model";
import { AuthService } from "@/utils/zynerator/security/Auth.service";
import { UserService } from "@/utils/zynerator/security/User.service";
import { MessageService } from "@/utils/zynerator/service/MessageService";
import { useTranslation } from "react-i18next";


const Profile = () => {

    const [password, setPassword] = useState('');
    const [confirmPwd, setConfirmPwd] = useState('');
    const [connectedUser, setConnectedUser] = useState<UserDto>(new UserDto());


    const authService = new AuthService();
    const userService = new UserService();

    const showToast = useRef(null);

    const handlePwdChange = () => {
        userService.changePassword(connectedUser.username, password).then(() =>
            MessageService.showSuccess(showToast, 'Mise à jour! Opération faite avec succes.'))
            .catch(() => MessageService.showError(showToast, 'Erreur! veuillez réessayer ultérieurement.'));
    }

    const handleUpdateInfosClick = () => {
        userService.update(connectedUser).then(({ data }) => console.log(data));
        MessageService.showSuccess(showToast, 'Mise à jour! Opération faite avec succes.');
    }

    const onInputTextChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>, name: string) => {
        const value = (e.target && e.target.value) || '';
        setConnectedUser({ ...connectedUser as any, [name]: value });
    };
    const { t } = useTranslation();

    useEffect(() => {
        const tokenDecoded = authService.decodeJWT();

        if (tokenDecoded && typeof tokenDecoded !== 'string') {
            const { sub ,roles} = tokenDecoded;
            console.log(tokenDecoded);

            setConnectedUser({
                ...connectedUser as any,
                // id:userData.id,
                username: sub,
                // email: userData.email,
                //  roles:roles

                // lastName: userData.lastName,
                // firstName: userData.firstName,
                // phone: userData.phone
            });
            userService.findByusername(sub).then((userResult:any) => {
                const userData=userResult.data;
                setConnectedUser({
                    ...connectedUser as any,
                    id:userData.id,
                    username: sub,
                    email: userData.email,
                    //  roles:roles

                    lastName: userData.lastName,
                    firstName: userData.firstName,
                    phone: userData.phone
                });
            }).catch((error:any) => {console.log(error);})



        }
    }, []);

    console.log({ connectedUser })

    return (
        <div>
            <div className="card p-fluid">
                <div className="field col-12 pl-5">
                    <h5>{t("Données personnelles")}</h5>
                </div>
                <div className="formgrid grid col-12 pl-4">
                    <div className="field col-4">
                        <label htmlFor="username" className="col-12">
                        {t("Username")}
                        </label>
                        <div className="col-12">
                            <InputText id="username" value={connectedUser.username} onChange={(e) => onInputTextChange(e, 'username')} />
                        </div>
                    </div>
                    <div className="field col-4">
                        <label htmlFor="username" className="col-12">
                        {t("email")}
                        </label>
                        <div className="col-12">
                            <InputText id="email" value={connectedUser.email} onChange={(e) => onInputTextChange(e, 'email')} />
                        </div>
                    </div>
                    <div className="field col-4">
                        <label htmlFor="lastName" className="col-12">
                        {t("lastName")}
                        </label>
                        <div className="col-12">
                            <InputText id="lastName" value={connectedUser.lastName} onChange={(e) => onInputTextChange(e, 'lastName')} />
                        </div>
                    </div>
                    <div className="field col-4">
                        <label htmlFor="firstName" className="col-12">
                            {t("firstName")}
                        </label>
                        <div className="col-12">
                            <InputText id="firstName" value={connectedUser.firstName} onChange={(e) => onInputTextChange(e, 'firstName')} />
                        </div>
                    </div>
                    <div className="field col-4">
                        <label htmlFor="tel" className="col-12">
                           {t("Télephone")}
                        </label>
                        <div className="col-12">
                            <InputText id="tel" value={connectedUser.phone} onChange={(e) => onInputTextChange(e, 'phone')} />
                        </div>
                    </div>
                    <div className="field col-2">
                        <div className="field col-12" style={{ marginTop: '7px' }}>
                            <br />
                            <Button label={t("save")} onClick={handleUpdateInfosClick} />
                        </div>
                    </div>
                </div>
            </div>
            <div className="card p-fluid">
                <div className="field col-12 pl-5">
                    <h5>{t("Mot de passe")}</h5>
                </div>
                <div className="formgrid grid col-12 pl-4">
                    <div className="field col-4">
                        <label htmlFor="new_password" className="col-12">
                        {t("Nouveau Mot de passe")}
                        </label>
                        <div className="col-12">
                            <Password value={password} onChange={(event) => setPassword(event.target.value)} toggleMask />
                        </div>
                    </div>
                    <div className="field col-4">
                        <label htmlFor="new_password" className="col-12">
                        {t("Confirmer votre Mot de passe")}
                        </label>
                        <div className="col-12">
                            <Password value={confirmPwd} onChange={(event) => setConfirmPwd(event.target.value)} toggleMask />
                        </div>
                    </div>
                    <div className="field col-2">
                        <div className="field col-12" style={{ marginTop: '7px' }}>
                            <br />
                            <Button label={t("Changer")} onClick={handlePwdChange} disabled={password == "" || confirmPwd == "" || password != confirmPwd} />
                        </div>
                    </div>
                </div>
            </div>
            <Toast ref={showToast} />
        </div>

    );
};

export default Profile;
