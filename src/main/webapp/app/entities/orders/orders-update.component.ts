import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrders, Orders } from 'app/shared/model/orders.model';
import { OrdersService } from './orders.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-orders-update',
  templateUrl: './orders-update.component.html'
})
export class OrdersUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    orderType: [],
    description: [],
    size: [],
    color: [],
    quantity: [],
    status: [],
    reason: [],
    userId: []
  });

  constructor(
    protected ordersService: OrdersService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orders }) => {
      this.updateForm(orders);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(orders: IOrders): void {
    this.editForm.patchValue({
      id: orders.id,
      orderType: orders.orderType,
      description: orders.description,
      size: orders.size,
      color: orders.color,
      quantity: orders.quantity,
      status: orders.status,
      reason: orders.reason,
      userId: orders.userId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const orders = this.createFromForm();
    if (orders.id !== undefined) {
      this.subscribeToSaveResponse(this.ordersService.update(orders));
    } else {
      this.subscribeToSaveResponse(this.ordersService.create(orders));
    }
  }

  private createFromForm(): IOrders {
    return {
      ...new Orders(),
      id: this.editForm.get(['id'])!.value,
      orderType: this.editForm.get(['orderType'])!.value,
      description: this.editForm.get(['description'])!.value,
      size: this.editForm.get(['size'])!.value,
      color: this.editForm.get(['color'])!.value,
      quantity: this.editForm.get(['quantity'])!.value,
      status: this.editForm.get(['status'])!.value,
      reason: this.editForm.get(['reason'])!.value,
      userId: this.editForm.get(['userId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrders>>): void {
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
