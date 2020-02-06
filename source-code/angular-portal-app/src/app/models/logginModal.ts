import { Employee } from './employee';

export class LogginModal{
    id: string;
    punchingTime: Date;
    punchingType: string;
    employee?: Employee;
}