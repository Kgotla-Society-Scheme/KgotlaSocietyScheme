export interface IAddress {
  id?: number;
  streetName?: string;
  complexName?: string;
  city?: string;
  postalCode?: number;
  province?: string;
  userAddressLogin?: string;
  userAddressId?: number;
}

export class Address implements IAddress {
  constructor(
    public id?: number,
    public streetName?: string,
    public complexName?: string,
    public city?: string,
    public postalCode?: number,
    public province?: string,
    public userAddressLogin?: string,
    public userAddressId?: number
  ) {}
}
