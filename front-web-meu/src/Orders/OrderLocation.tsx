import { useState } from 'react';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import './styles.css';
import AsyncSelect from 'react-select/async';
import { fetchLocalMapBox } from '../api';
import { OrderLocationdata } from './types';


    //inicializando a variável  initialPosition
/* const initialPosition = {
    lat: -22.9311957,
    lng: -43.3581951
} */

const initialPosition = {
    lat: -22.9311957,
    lng: -43.3581951
};
///representação da localização selecionada
type Place = {
    label?: string; //texto que aparecerá no AsyncSelect label, opcional ao carregar(?:) 
    value?: string; //valor real do select value, opcional ao carregar(?:)
    position: {
    lat: number;
    lng: number;
    };
} 

 type  Props = {
    onChangeLocation: (location : OrderLocationdata) => void;
}  


// Orderlocation

   function OrderLocation({onChangeLocation} : Props) {
    
// estado representação do endereço selecionado
     const [address, setAddress] = useState<Place>({
        position : initialPosition
    });   
 
 //As linhas abaixo até o return , são responsáveis pelo select , ou busca de endereços
 //ao digitar (inputValue) o(s)primeiros caracteres no campo de pesquisa de endereços é chamada a função loadOptions
    const loadOptions = async (inputValue: string, callback: (places: Place[]) => void) => {
        //indo na api do MapBox  (api.ts - fetchLocalMapBox)e busca uma lista de endereços
        const response = await fetchLocalMapBox(inputValue);
        //preenchendo um objeto tipo places ao selecionar uma das sugestões de endereços 
        const places = response.data.features.map((item: any) => {
            return ({
                label: item.place_name,
                value: item.place_name,
                position: {
                    lat: item.center[1],
                    lng: item.center[0]
                }
            });
        });
    //passando os places por parâmetro para a funçao callback , e através dessa função que mostra a lista de opções iteradas acima
        callback(places);
    }; 

    // recebe um tipo place do AsyncSelect ( onChange = {value => handleChangeSelect(value as Place)})
      const handleChangeSelect = (place: Place) => {
        setAddress(place);

        // função ao mudar a localização(ex: clicar no mapa, ou escolher um endereço dentre as opções mostradas)
           onChangeLocation({
            latitude: place.position.lat,
            longitude: place.position.lng,
            address: place.label!
        }); 
    };  



    return (

        <div className="order-location-container">

            <div className="order-location-content">
                <h3 className="order-location-title">
                    Selecione onde o pedido deve ser entregue:
                </h3>

                < div className="filter-container">
                   {/*  função AsyncSelect  abaixo para filtrar os endereços digitados e mostrar as opções encontradas,
                     é parecido a pesquisa em formulári no campo de texto */}
                     < AsyncSelect
                     //texto que aparecera no filter ao carregar
                    placeholder="Digite um endereço para entrega do pedido."
                    className ="filter"
                    //carregamento das opções de endereços
                    loadOptions = {loadOptions}
                    //a linha abaixo captura a seleção feita no mapa(value) pelo mouse
                    //passando para a função handleChangeSelect , como um tipo Place
                    onChange = {value => handleChangeSelect(value as Place)}
                    /> 
                </div>
                <MapContainer 
            // centraliznado o endereço selecionado, na posição selecionada  
            center={address.position} 
            zoom={15}
            //A linha abaixo força a rehenderização do endereço digitado, para aparecer centralizado 
            key={address.position.lat}
            scrollWheelZoom={true}>
                <TileLayer
                attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />
                {/* Quando selecionada a posição no mapa, o marcador (Marker) será colocado naquela posição */} 
                <Marker position={address.position}>
                <Popup>
                    
                    {address.label}
                    Meu Mapa
                </Popup>
                </Marker>
            </MapContainer>
                
            </div>
        </div>
    )

}
export default OrderLocation;