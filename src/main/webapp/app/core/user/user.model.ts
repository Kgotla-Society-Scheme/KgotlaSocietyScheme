export interface IUser {
  id?: any;
  login?: string;
  firstName?: string;
  lastName?: string;
  idNumber?: string;
  dateOfBirth?: string;
  title?: string;
  gender?: string;
  mobileNumber?: string;
  email?: string;
  activated?: boolean;
  langKey?: string;
  authorities?: string[];
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  password?: string;
}

export class User implements IUser {
  constructor(
    public id?: any,
    public login?: string,
    public firstName?: string,
    public lastName?: string,
    public idNumber?: string,
    public dateOfBirth?: string,
    public title?: string,
    public gender?: string,
    public mobileNumber?: string,
    public email?: string,
    public activated?: boolean,
    public langKey?: string,
    public authorities?: string[],
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public password?: string
  ) {}
}
