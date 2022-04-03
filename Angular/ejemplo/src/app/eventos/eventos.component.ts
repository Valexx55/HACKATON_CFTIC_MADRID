import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { EventosService } from '../eventos.service';

@Component({
  selector: 'app-eventos',
  templateUrl: './eventos.component.html',
  styleUrls: ['./eventos.component.css']
})
export class EventosComponent implements OnInit {

  lista_eventos : Array<string>;

  constructor(private eventos_service:EventosService) { 
    this.lista_eventos = [];
  }

  distritoSeleccionado(evento:Event)
  {
    let elemento_select = <HTMLSelectElement>evento.target;

    console.log(elemento_select.value);

    let distrito_seleccionado:string = elemento_select.value;

    this.lista_eventos = [];
    this.eventos_service.obtenerEventos(distrito_seleccionado).subscribe
    (
      {
        //complete se invoca siempre
        complete: () => {console.log("comunicaión completada");},
        error: (error_rx) => {this.mostrarError(error_rx)},
        next: (eventos) => {
          //quiero mostrar los ids de los alumnos rx
          //actualizar la lista visible
          //eliminar de la vista el alumno que se ha eliminado
          console.log(eventos);

          eventos['@graph'].forEach(element => {
            console.log(element.title)
            this.lista_eventos.push(element.title);
          });
          //console.log(eventos['@graph'][i].title
          
         
        }
      }
    );


  }
  mostrarError (error:any)
  {
    //STATUS ERROR
    console.log(error.status);
    //MENSAJE ERROR
    console.log(error.message);
  }

  ngOnInit(): void {
    
  }


}
