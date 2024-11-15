import { ADMIN_URL, API_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {UserDto} from '@/utils/zynerator/security/model/User.model';
import {UserCriteria} from '@/utils/zynerator/security/criteria/UserCriteria.model';

export class UserAdminService extends AbstractService<UserDto, UserCriteria>{

    constructor() {
        super(API_URL , 'user/');
    }

};
