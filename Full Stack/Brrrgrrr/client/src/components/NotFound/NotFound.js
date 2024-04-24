import React from "react";
import { Link } from "react-router-dom";

import burgerImage from "../Header/assets/burger.jpeg";

import "./NotFound.css";

function NotFound() 
{
    return (
        <div className="not-found">
            <div className="notfound-content">
                <img src="https://media.istockphoto.com/photos/not-found-text-for-missing-page-or-file-on-website-picture-id519363862?k=6&m=519363862&s=612x612&w=0&h=5tU5Az3KODHEI8jGepxOuznm9TN-DePx_aymVdWegqQ=" alt="Not Found"/>
                <h1> Page Not Found! Invalid URL or Item has been deleted! <br/> Let's Have some burger on brrrgrrr! </h1>
                <Link to="/">
                    <button> <img src={burgerImage} alt="burger"/> </button>
                </Link>
            </div>
        </div>
    );
};

export default NotFound;
