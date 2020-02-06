import { Employee } from './employee';
import { LogginModal } from './logginModal';

export class AttendanceLogReport {
    firstLogin: LogginModal;
    lastLogin: LogginModal;
    attendanceLog: LogginModal[];
    totalWorkingHours: number
}