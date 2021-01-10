import { type } from "os";
import { useEffect } from "react";


type Props = {
    message: string;
}
function Hello({message}:Props) {

    //useEffect(() => {

        console.log('componente iniciou!')
   // } ,[] );

    return(
    <div>
<h1>Hello {message}</h1>

    </div>
    );         
        
    }


export default Hello;