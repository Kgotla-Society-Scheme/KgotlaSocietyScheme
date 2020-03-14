import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';

@Component({
  selector: 'jhi-settings',
  templateUrl: './settings.component.html'
})
export class SettingsComponent implements OnInit {
  account!: Account;
  success = false;
  settingsForm = this.fb.group({
    firstName: [undefined, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
    lastName: [undefined, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
    idNumber: [undefined, [Validators.required, Validators.minLength(13), Validators.maxLength(13)]],
    dateOfBirth: ['', [Validators.required, Validators.minLength(8)]],
    title: ['', [Validators.required, Validators.minLength(6)]],
    gender: ['', [Validators.required, Validators.minLength(6)]],
    mobileNumber: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]],
    email: [undefined, [Validators.required, Validators.minLength(5), Validators.maxLength(254), Validators.email]]
  });

  constructor(private accountService: AccountService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.accountService.identity().subscribe(account => {
      if (account) {
        this.settingsForm.patchValue({
          firstName: account.firstName,
          lastName: account.lastName,
          idNumber: account.idNumber,
          dateOfBirth: account.dateOfBirth,
          title: account.title,
          gender: account.gender,
          mobileNumber: account.mobileNumber,
          email: account.email
        });

        this.account = account;
      }
    });
  }

  save(): void {
    this.success = false;

    this.account.firstName = this.settingsForm.get('firstName')!.value;
    this.account.lastName = this.settingsForm.get('lastName')!.value;
    this.account.idNumber = this.settingsForm.get('idNumber')!.value;
    this.account.dateOfBirth = this.settingsForm.get('dateOfBirth')!.value;
    this.account.title = this.settingsForm.get('title')!.value;
    this.account.gender = this.settingsForm.get('gender')!.value;
    this.account.mobileNumber = this.settingsForm.get('mobileNumber')!.value;
    this.account.email = this.settingsForm.get('email')!.value;

    this.accountService.save(this.account).subscribe(() => {
      this.success = true;

      this.accountService.authenticate(this.account);
    });
  }
}
