"use client"
import { AuthService } from '@/utils/zynerator/security/Auth.service';
import { useRouter } from 'next/navigation'
import { ProgressSpinner } from 'primereact/progressspinner';
import React, { useEffect, useState } from 'react';

type AuthGuardProps = {
    children: React.ReactNode
}

const AuthGuard = ({ children }: AuthGuardProps) => {
    const authService = new AuthService();
    const [isTokenValid, setIsTokenValid] = useState<boolean | null>();
    const router = useRouter();

    useEffect(() => {
        const isTokenValid = authService.isUserLoggedIn();
        setIsTokenValid(isTokenValid)

    }, []);

    if (isTokenValid == true) {
        return <>{children}</>;
    }
    if (isTokenValid == false) {
        router.push('/auth/login')
        return (
            <div className='flex flex-col justify-center items-center h-screen gap-4' style={{alignItems:"center"}}><ProgressSpinner /></div>

        )
    }

    return  <>{children}</>
};
export default AuthGuard
