/* eslint-disable @next/next/no-img-element */
'use client';
import { useRouter } from 'next/navigation';
import React, { useContext, useState,useRef } from 'react';
import { Checkbox } from 'primereact/checkbox';
import { Button } from 'primereact/button';
import { Password } from 'primereact/password';
import { LayoutContext } from '../../../../layout/context/layoutcontext';
import { InputText } from 'primereact/inputtext';
import { classNames } from 'primereact/utils';
import { Toast } from 'primereact/toast';
import { AuthService } from '@/utils/zynerator/security/Auth.service';
import { MessageService } from '@/utils/zynerator/service/MessageService';

const LoginPage = () => {
    const [password, setPassword] = useState('');
    const [username, setUsername] = useState("");
    const [checked, setChecked] = useState(false);
    const { layoutConfig } = useContext(LayoutContext);
    const [userService, setUserService] = useState(new AuthService());
    const toast = useRef<Toast|null>(null);
    const router = useRouter();
    const containerClassName = classNames('surface-ground flex align-items-center justify-content-center min-h-screen min-w-screen overflow-hidden', { 'p-input-filled': layoutConfig.inputStyle === 'filled' });
    const handleAuthFormClick = () => {
        console.log(password);
        userService.signIn(username, password).then(
            data => {
                const jwt = data.headers['authorization'];

                //({data}) => {
                //    const jwt = data;
                if (jwt) {
                    userService.saveToken(jwt)
                    router.push("/")
                }else{
                    MessageService.showError(toast,'Probléme de connexion');
                }
            }).catch(() => {
            MessageService.showError(toast,'Vos identifiants sont incorrects');
        })
    }
    return (
        // <div className={containerClassName}>
        //     <div className="flex flex-column align-items-center justify-content-center">
        //         {/* <img src={`/layout/images/logo-${layoutConfig.colorScheme === 'light' ? 'dark' : 'white'}.svg`} alt="Sakai logo" className="mb-5 w-6rem flex-shrink-0" /> */}

        //         <div
        //             style={{
        //                 borderRadius: '56px',
        //                 padding: '0.3rem',
        //                 background: 'linear-gradient(180deg, var(--primary-color) 10%, rgba(33, 150, 243, 0) 30%)'
        //             }}
        //         >
        //             <div className="w-full surface-card py-8 px-5 sm:px-8" style={{ borderRadius: '53px' }}>
        //                 <div className="text-center mb-5">
        //                     <img src="/demo/images/login/avatar.png" alt="Image" height="50" className="mb-3" />
        //                     <div className="text-900 text-3xl font-medium mb-3">Welcome, Isabel!</div>
        //                     <span className="text-600 font-medium">Sign in to continue</span>
        //                 </div>

        //                 <div>
        //                     <label htmlFor="email1" className="block text-900 text-xl font-medium mb-2">
        //                         Email
        //                     </label>
        //                     <InputText id="email1" type="text" placeholder="Email address" value={username} className="w-full md:w-30rem mb-5" style={{ padding: '1rem' }}
        //                                onChange={(e) => setUsername(e.target.value)} />

        //                     <label htmlFor="password1" className="block text-900 font-medium text-xl mb-2">
        //                         Password
        //                     </label>
        //                     <Password inputId="password1" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Password" toggleMask className="w-full mb-5" inputClassName="w-full p-3 md:w-30rem"></Password>

        //                     <div className="flex align-items-center justify-content-between mb-5 gap-5">
        //                         <div className="flex align-items-center">
        //                             <Checkbox inputId="rememberme1" checked={checked} onChange={(e) => setChecked(e.checked ?? false)} className="mr-2"></Checkbox>
        //                             <label htmlFor="rememberme1">Remember me</label>
        //                         </div>
        //                         <a className="font-medium no-underline ml-2 text-right cursor-pointer" style={{ color: 'var(--primary-color)' }}>
        //                             Forgot password?
        //                         </a>
        //                     </div>
        //                     <Button label="Sign In" className="w-full p-3 text-xl" onClick={handleAuthFormClick}></Button>
        //                 </div>
        //             </div>
        //         </div>
        //     </div>
        //     <Toast ref={toast}/>
        // </div>
        <div style={{display:"flex",height:"100vh"}}>
    <div style={{width:"55%", backgroundImage: `url('/authbg.png')`,backgroundPositionX:"center",backgroundSize:"cover",position:"sticky",top:"0"}}>
        {/* <img src='public/demo/images/avatar/amyelsner.png'/> */}
    </div>
        <div style={{height:"100%",  marginInline: "auto", paddingTop:"160px",display:"flex",flexDirection:"column", maxWidth:"400px" , width:"50%",overflow:"non"}}>
        <h2 className="mb-5">Saisissez vos identifiants</h2>
        <span className="mb-5">Votre mail et mot de passe seront utilisés pour vous connecter ou alors pour vous inviter à créer un compte.</span>

        <div>
            <label htmlFor="email" className="block text-900 text-base font-medium mb-2">
                Email
            </label>
            <InputText id="email" type="text" placeholder="Saisissez votre email"
                       onChange={(e) => setUsername(e.target.value)} className="w-full mb-5"
                       style={{padding: '1rem'}}/>

            <label htmlFor="password" className="block text-900 font-medium text-base mb-2">
                Mot de passe
            </label>
            <Password inputId="password" value={password} onChange={(e) => setPassword(e.target.value)}
                      feedback={false} placeholder="Saisissez votre email" toggleMask className="w-full mb-5"
                      inputClassName="w-full p-3 md:w-30rem"></Password>

            <div className="flex align-items-center justify-content-between mb-5 gap-5">
                <div className="flex align-items-center">
                    <Checkbox inputId="rememberMe" checked={checked}
                              onChange={(e) => setChecked(e.checked ?? false)} className="mr-2"></Checkbox>
                    <label htmlFor="rememberMe">Remember me</label>
                </div>
            </div>
            <Button label="Se connecter" className="w-full p-3 text-large" onClick={handleAuthFormClick}></Button>
        </div>
        <Toast ref={toast}/>
    </div>
    </div>
    );
};

export default LoginPage;
