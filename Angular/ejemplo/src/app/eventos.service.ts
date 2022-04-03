import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EventosService {


  private static readonly URL_API_EVENTOS_MADRID_POR_DISTRITO:string  = "https://datos.madrid.es/egob/catalogo/206974-0-agenda-eventos-culturales-100.json?distrito_nombre="

  constructor(private http:HttpClient) { }


  public obtenerEventos(distrito:string):Observable<HttpResponse<any>>
  {
    return this.http.get<any>(EventosService.URL_API_EVENTOS_MADRID_POR_DISTRITO+distrito);
  }
}
