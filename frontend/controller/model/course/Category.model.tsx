import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";


export class CategoryDto extends BaseDto{

    public code: string;

    public label: string;

    public style: string;



    constructor() {
        super();
        this.code = '';
        this.label = '';
        this.style = '';
        }

    getClassName() {
        return "Category";
    }
}
