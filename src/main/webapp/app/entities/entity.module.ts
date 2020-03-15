import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'address',
        loadChildren: () => import('./address/address.module').then(m => m.KgotlaSocietySchemeAddressModule)
      },
      {
        path: 'payment',
        loadChildren: () => import('./payment/payment.module').then(m => m.KgotlaSocietySchemePaymentModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class KgotlaSocietySchemeEntityModule {}
