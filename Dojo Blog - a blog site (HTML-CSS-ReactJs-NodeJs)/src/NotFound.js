import { Link } from 'react-router-dom';

const NotFound = () => {
    return ( 
        <div className="not-found">
            <h1>Sorry! Either the provided URL is invalid, or Blog Delete!</h1>
            <Link to="/"><button className='Home'>Return to Home page</button></Link>
        </div>
     );
}
 
export default NotFound;