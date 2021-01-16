import axios from 'axios';

const API_URL = 'http://localhost:8080';
//atribuindo à variável mapboxToken , o seu token do mapBox
const mapboxToken = process.env.REACT_APP_ACCESS_TOKEN_MAP_BOX;

export function fetchProducts(){

return axios(`${API_URL}/products`);

}

//api para comunicação com o mapbox. 
//local : endereço digitado pelo  usuário passado como parâmetro
export function fetchLocalMapBox(local:string){

    //retorna a busca do axios , baseada no parâmetro digitado pelo usuário
    return axios(`https://api.mapbox.com/geocoding/v5/mapbox.places/${local}.json?access_token=${mapboxToken}`)
}