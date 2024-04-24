import React from "react";
import { Link } from "react-router-dom";

import "./Account.css";

function Account() { 
    
    function handleLogout () {
        localStorage.removeItem('user');
        window.location.reload();
    };

    const user = JSON.parse(localStorage.getItem('user'));
    
    if (user) {
        return (
            <div className="account">
                <div className="action-area">
                    <div className="column1">
                        <h2> Welcome {user.userData.name}!</h2>
                        <button onClick={handleLogout}> Logout </button>
                    </div>
                    
                    <div className="column2">
                        <img src="https://th.bing.com/th/id/OIP.Li2rEtHXPADML-vULl6iowHaH0?rs=1&pid=ImgDetMain" alt="signin"/>
                    </div>
                </div>
            </div>
        );
        
    } else {
        return  (
            <div className="account">
                <div className="action-area">

                    <div className="column1">
                        <div className="login-button">
                            <Link to="/account/login"> <button> Log In </button> </Link>
                        </div> 
                        
                        <div className="signup-button">
                            <Link to="/account/signup"> <button> Sign Up </button> </Link>
                        </div>
                    </div>
                    
                    <div className="column2">
                        <img src="https://th.bing.com/th/id/OIP.Li2rEtHXPADML-vULl6iowHaH0?rs=1&pid=ImgDetMain" alt="signin"/>
                    </div>
                </div>
            </div>
        );
    };
};

export default Account;
