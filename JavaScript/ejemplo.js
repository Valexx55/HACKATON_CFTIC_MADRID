const URL_API_EVENTOS_MADRID_POR_DISTRITO = "https://datos.madrid.es/egob/catalogo/206974-0-agenda-eventos-culturales-100.json?distrito_nombre="
const DISTRITO = "ARGANZUELA";
let array_eventos;// = [];//array eventos

let selectdistritos = document.getElementById('selectdistritos'); //Capturo el select
selectdistritos.addEventListener('ionChange', obtenerEventos);
let lc;//loading controller variable que representa el diáglogo de cargando...

async function obtenerEventos() {
    let distrito = selectdistritos.value;
    console.log("Distrito seleccionado = " + distrito);
    let url_get = URL_API_EVENTOS_MADRID_POR_DISTRITO + distrito;
    let url_get_normalizada = encodeURI(url_get);
    console.log(url_get_normalizada);
    //borrarlista con datos anteriores
    borrarlista();
    array_eventos = [];//inicializo el array
    //mostramos el circulito de espera
    lc = await loadingController.create({
        message: 'Cargando...',
        spinner: 'bubbles'
    });
    await lc.present();//muestro

    fetch(url_get_normalizada)
        .then(respuesta => respuesta.json())
        .then(eventos => {
            console.log(eventos);
            mostrarEventos(eventos);
           
        }).catch(error => {
           
            console.log("error " + error);     
            mostrarAvisoFallo();
        }).finally( lc.dismiss());

}

async function mostrarAvisoFallo() {
    const toast = document.createElement('ion-toast');
    toast.message = 'NO SE HA PODIDO RECUPERAR LOS DATOS';
    toast.duration = 5000;
    toast.color = "danger";
    toast.position = "middle";
  
    document.body.appendChild(toast);
    return toast.present();
  }

  async function mostrarSinEventos() {
    const toast = document.createElement('ion-toast');
    toast.message = 'NO HAY EVENTOS PREVISTOS ACTUALMENTE';
    toast.duration = 5000;
    toast.color = "secondary";
    toast.position = "middle";
  
    document.body.appendChild(toast);
    return toast.present();
  }
//TODO 
//1Controlar fallos (HTTP STATUS fetch)
//2Controlar que venga vacía la lista o no hay eventos /graph es cero
//bolita del tiempo
//no haya conexión a internet
//botón hacia atrás de salir

function borrarlista ()
{
    let elementopadre_lista = document.getElementById("lista_eventos");
    elementopadre_lista.innerHTML="";

}


//

//console.log("Titiulo " + eventos['@graph'][0].title);// FORMA DE ACCESO POR EN EL NOMBRE DEL ATRIBUTO EN VEZ POR PUNTO BY JOSE LUIS
function mostrarEventos(eventos) {
    
    //recorriendo y creando elementos
    let elementopadre_lista = document.getElementById("lista_eventos");
    console.log("eventos recibidos = " + eventos['@graph'].length);
    //recorrer y para cada recorrido, crear un item y añadirlo

    let nuevo_item;
    let nuevo_label;

    if (eventos['@graph'].length==0)
    {
        mostrarSinEventos();
    }
    else {

        for (let i = 0; i < eventos['@graph'].length; i++) {
            nuevo_item = document.createElement('ion-item');
            nuevo_label = document.createElement('ion-label');
            nuevo_label.innerHTML = eventos['@graph'][i].title;
            nuevo_item.appendChild(nuevo_label);
            elementopadre_lista.appendChild(nuevo_item);
            //TENGO QUE AÑADIR EL EVENTO ON CLICK
            nuevo_item.addEventListener('click', detalleEvento);
            nuevo_item.setAttribute('id', i);
            array_eventos.push(eventos['@graph'][i]);//guardo eventos
    
        }
    }
   
}

function detalleEvento ()
{
    console.log("El usuario quiere ir a detalle");
    console.log(this.firstChild.innerHTML);//THIS es el elemento tocado
    console.log(this.id);//THIS es el elemento tocado
    console.log(array_eventos[this.id].title);
    console.log(array_eventos[this.id].location.latitude);
    console.log(array_eventos[this.id].location.longitude);
    console.log(array_eventos[this.id]['@id']);
    

}
