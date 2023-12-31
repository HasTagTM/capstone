import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './pages/auth/guard/guard.guard';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { ShoesComponent } from './components/shoes/shoes.component';
import { JeansComponent } from './components/jeans/jeans.component';
import { SweatshirtsComponent } from './components/sweatshirts/sweatshirts.component';
import { TShirtComponent } from './components/t-shirt/t-shirt.component';
import { EditComponent } from './components/edit/edit.component';
import { AuthComponent } from './pages/auth/auth/auth.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { CreateComponent } from './components/create/create.component';
import { FaQComponent } from './components/fa-q/fa-q.component';
import { ChiSiamoComponent } from './components/chi-siamo/chi-siamo.component';
import { PagamentoComponent } from './components/pagamento/pagamento.component';
import { DetailComponent } from './components/detail/detail.component';

const routes: Routes = [
  // { path: '', redirectTo: 'auth', pathMatch: 'full' },
  { path: '', component: AuthComponent },

  {
    path: 'auth', loadChildren: () => import('./pages/auth/auth/auth.module')
    .then(m => m.AuthModule)
  },
  {
    path: 'dashboard',
    loadChildren: () => import('./pages/dashboard/dashboard/dashboard.module')
    .then(m => m.DashboardModule),
    canActivate: [AuthGuard],
},
{
  path: 'categories/shoes' , component: ShoesComponent, canActivate: [AuthGuard]
},
{
  path: 'categories/jeans' , component: JeansComponent, canActivate: [AuthGuard]
},
{
  path: 'categories/sweatshirt' , component: SweatshirtsComponent, canActivate: [AuthGuard]
},
{
  path: 'categories/t_shirt' , component: TShirtComponent, canActivate: [AuthGuard]
},
{
  path: 'create' , component: CreateComponent, canActivate: [AuthGuard]
},
{ path: 'login', component: LoginComponent },
{ path: 'faqs', component: FaQComponent },
{ path: 'chi-siamo', component: ChiSiamoComponent },
{ path: 'carrello/pagamento', component: PagamentoComponent },



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
