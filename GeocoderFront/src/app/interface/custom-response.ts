import { Server } from "./server";
import { DashBoard } from "./dashboard";

export interface CustomResponse {
    timeStamp: Date;
    data: { dataDBs?: Server[], dataDB?: Server };
    traceData: { traces?: DashBoard[], trace?: DashBoard };
    
}