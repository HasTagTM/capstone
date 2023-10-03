import { NgModule, createComponent } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { EditComponent } from 'src/app/components/edit/edit.component';
import { CreateComponent } from 'src/app/components/create/create.component';
import { DetailComponent } from 'src/app/components/detail/detail.component';
import { ShoesComponent } from 'src/app/components/shoes/shoes.component';
import { JeansComponent } from 'src/app/components/jeans/jeans.component';
import { SweatshirtsComponent } from 'src/app/components/sweatshirts/sweatshirts.component';
import { TShirtComponent } from 'src/app/components/t-shirt/t-shirt.component';
import { HeaderComponent } from 'src/app/components/header/header.component';
import { FooterComponent } from 'src/app/components/footer/footer.component';

const routes: Routes = [
  {
  path: '', component: DashboardComponent
},
  {
    path: 'edit/:id' , component: EditComponent
  },
  {
    path: 'create' , component: CreateComponent
  },
  {
    path: 'detail/:id' , component: DetailComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
