import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAddress, Address } from 'app/shared/model/address.model';
import { AddressService } from './address.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-address-update',
  templateUrl: './address-update.component.html'
})
export class AddressUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    streetName: [],
    complexName: [],
    city: [],
    postalCode: [],
    province: [],
    userAddressId: []
  });

  constructor(
    protected addressService: AddressService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ address }) => {
      this.updateForm(address);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(address: IAddress): void {
    this.editForm.patchValue({
      id: address.id,
      streetName: address.streetName,
      complexName: address.complexName,
      city: address.city,
      postalCode: address.postalCode,
      province: address.province,
      userAddressId: address.userAddressId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const address = this.createFromForm();
    if (address.id !== undefined) {
      this.subscribeToSaveResponse(this.addressService.update(address));
    } else {
      this.subscribeToSaveResponse(this.addressService.create(address));
    }
  }

  private createFromForm(): IAddress {
    return {
      ...new Address(),
      id: this.editForm.get(['id'])!.value,
      streetName: this.editForm.get(['streetName'])!.value,
      complexName: this.editForm.get(['complexName'])!.value,
      city: this.editForm.get(['city'])!.value,
      postalCode: this.editForm.get(['postalCode'])!.value,
      province: this.editForm.get(['province'])!.value,
      userAddressId: this.editForm.get(['userAddressId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAddress>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IUser): any {
    return item.id;
  }
}
