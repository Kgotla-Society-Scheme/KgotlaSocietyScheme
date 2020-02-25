import { Size } from 'app/shared/model/enumerations/size.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IOrders {
  id?: number;
  orderType?: string;
  description?: string;
  size?: Size;
  color?: string;
  quantity?: number;
  status?: Status;
  reason?: string;
  userLogin?: string;
  userId?: number;
}

export class Orders implements IOrders {
  constructor(
    public id?: number,
    public orderType?: string,
    public description?: string,
    public size?: Size,
    public color?: string,
    public quantity?: number,
    public status?: Status,
    public reason?: string,
    public userLogin?: string,
    public userId?: number
  ) {}
}
