import { Moment } from 'moment';

export interface IPayment {
  id?: number;
  paymentDate?: Moment;
  attachmentContentType?: string;
  attachment?: any;
  status?: string;
  amount?: number;
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
    public userLogin?: string,
    public userId?: number
  ) {}
}
