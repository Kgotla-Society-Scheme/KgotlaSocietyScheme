import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { KgotlaSocietySchemeSharedModule } from 'app/shared/shared.module';
import { KgotlaSocietySchemeCoreModule } from 'app/core/core.module';
import { KgotlaSocietySchemeAppRoutingModule } from './app-routing.module';
import { KgotlaSocietySchemeHomeModule } from './home/home.module';
import { KgotlaSocietySchemeEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    KgotlaSocietySchemeSharedModule,
    KgotlaSocietySchemeCoreModule,
    KgotlaSocietySchemeHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    KgotlaSocietySchemeEntityModule,
    KgotlaSocietySchemeAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent]
})
export class KgotlaSocietySchemeAppModule {}
