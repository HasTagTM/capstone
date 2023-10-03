import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { HeaderParamsInterceptor } from './pages/auth/interceptor/auth.interceptor';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { EditComponent } from './components/edit/edit.component';
import { CreateComponent } from './components/create/create.component';
import { DetailComponent } from './components/detail/detail.component';
import { ChiSiamoComponent } from './components/chi-siamo/chi-siamo.component';
import { FaQComponent } from './components/fa-q/fa-q.component';
import { ShoesComponent } from './components/shoes/shoes.component';
import { TShirtComponent } from './components/t-shirt/t-shirt.component';
import { JeansComponent } from './components/jeans/jeans.component';
import { SweatshirtsComponent } from './components/sweatshirts/sweatshirts.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


@NgModule({
  declarations: [
    AppComponent,
    EditComponent,
    CreateComponent,
    DetailComponent,
    ChiSiamoComponent,
    FaQComponent,
    ShoesComponent,
    TShirtComponent,
    JeansComponent,
    SweatshirtsComponent,
    HeaderComponent,
    FooterComponent,
    FaQComponent,
    ChiSiamoComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    BrowserAnimationsModule,
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: HeaderParamsInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }

