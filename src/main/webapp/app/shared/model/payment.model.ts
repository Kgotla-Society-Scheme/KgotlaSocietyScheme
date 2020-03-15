import { Moment } from 'moment';

export interface IPayment {
  id?: number;
  paymentDate?: Moment;
  attachmentContentType?: string;
  attachment?: any;
  status?: string;
  amount?: number;
  reason?: string;
  modifiedDate?: Moment;
  modifiedBy?: string;
  userLogin?: string;
  userId?: number;
}

export class Payment implements IPayment {
  constructor(
    public id?: number,
    public paymentDate?: Moment,
    public attachmentContentType?: string,
    public attachment?: any,
    public status?: string,
    public amount?: number,
    public reason?: string,
    public modifiedDate?: Moment,
    public modifiedBy?: string,
    public userLogin?: string,
    public userId?: number
  ) {}
}
