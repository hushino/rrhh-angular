import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SearchComponent } from './search/search.component';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'persona',
        loadChildren: () => import('./persona/persona.module').then(m => m.RrhhPersonaModule)
      },
      {
        path: 'licencia',
        loadChildren: () => import('./licencia/licencia.module').then(m => m.RrhhLicenciaModule)
      },
      {
        path: 'otros-servicios-prestados',
        loadChildren: () =>
          import('./otros-servicios-prestados/otros-servicios-prestados.module').then(m => m.RrhhOtrosServiciosPrestadosModule)
      },
      {
        path: 'penas-disciplinarias-sufridas',
        loadChildren: () =>
          import('./penas-disciplinarias-sufridas/penas-disciplinarias-sufridas.module').then(m => m.RrhhPenasDisciplinariasSufridasModule)
      },
      {
        path: 'garantia',
        loadChildren: () => import('./garantia/garantia.module').then(m => m.RrhhGarantiaModule)
      },
      {
        path: 'altas-ascensos-bajas',
        loadChildren: () => import('./altas-ascensos-bajas/altas-ascensos-bajas.module').then(m => m.RrhhAltasAscensosBajasModule)
      },
      {
        path: 'embargos',
        loadChildren: () => import('./embargos/embargos.module').then(m => m.RrhhEmbargosModule)
      },
      {
        path: 'concepto-conocimientos-especiales-clasificacion-premios',
        loadChildren: () =>
          import('./concepto-conocimientos-especiales-clasificacion-premios/concepto-conocimientos-especiales-clasificacion-premios.module').then(
            m => m.RrhhConceptoConocimientosEspecialesClasificacionPremiosModule
          )
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [SearchComponent]
})
export class RrhhEntityModule {}
