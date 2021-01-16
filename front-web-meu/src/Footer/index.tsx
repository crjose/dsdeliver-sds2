import './styles.css';
import { ReactComponent as YouTubeIcon } from './youtube.svg';
import { ReactComponent as InstaGramIcon } from './instagram.svg';
import { ReactComponent as LinkDinIcon } from './linkedin.svg';


function Footer() {

    return (

        <footer className="main-footer">

            <h3>App desenvolvido durante a 2ª ed. do evento Semana DevSuperior</h3>

            <div className="footer-icons">
                <a href="https://www.youtube.com/devsuperior" target="_new">
                    <YouTubeIcon />
                </a>
                <a href="https://www.linkedin.com/school/devsuperior/" target="_new">
                    <LinkDinIcon />
                </a>
                <a href="https://www.instagram.com/devsuperior.ig/" target="_new">

                    <InstaGramIcon />
                </a>


            </div>

        </footer>


    )

}
export default Footer;