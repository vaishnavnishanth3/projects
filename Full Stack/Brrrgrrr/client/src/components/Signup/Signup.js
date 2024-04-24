import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

import "./Signup.css";
import API from "../../constants";

function Signup() {

    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const URL = `${API}/account/signup`;

    function handleSignup (e) {
        e.preventDefault();
        axios.post(URL , { name, email, password})
          .then(() => { 
            document.querySelectorAll('h2')[0].innerHTML="Account Created! Login to Proceed!!";
            setEmail('');
            setPassword('');
            setName('');
            setTimeout(() => {
                navigate('/account/login');
            }, 1500);
        })
          .catch(error => { console.log(error) });
    };

  return (
      <div className="account">
          <div className='column1'>
          </div>
          
          <div className="column2">
              <div className="signup-component">
                  <h2> Sign Up <br/> to keep track of your diet history?!! </h2>
                  
                  <form>
                      <input
                          type="text"
                          placeholder="Name"
                          value={name}
                          onChange={(e) => setName(e.target.value)}
                      />
                      <input
                          type="email"
                          placeholder="Email"
                          value={email}
                          onChange={(e) => setEmail(e.target.value)}
                      />
                      <input
                          type="password"
                          placeholder="Password"
                          value={password}
                          onChange={(e) => setPassword(e.target.value)}
                      />
                      <button onClick={handleSignup}> Submit </button>
                  </form>

                  <h4> Already have an account? 
                      <Link to="/account/login">
                      <h2> Log In </h2>
                      </Link>
                  </h4>
                </div>
            </div>
        </div>
    );
};

export default Signup;
