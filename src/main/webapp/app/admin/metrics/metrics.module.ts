import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { KgotlaSocietySchemeSharedModule } from 'app/shared/shared.module';

import { MetricsComponent } from './metrics.component';

import { metricsRoute } from './metrics.route';

@NgModule({
  imports: [KgotlaSocietySchemeSharedModule, RouterModule.forChild([metricsRoute])],
  declarations: [MetricsComponent]
})
export class MetricsModule {}
