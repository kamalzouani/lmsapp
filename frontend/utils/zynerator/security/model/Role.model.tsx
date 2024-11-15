import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";


export class RoleDto extends BaseDto{

    public authority: string;
    public libelle: string;


    constructor() {
        super();
        this.authority = '';
        this.libelle=""
        }

    getClassName() {
        return "Role";
    }
}
