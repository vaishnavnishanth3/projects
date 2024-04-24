import axios from "axios";
import React, { useState } from "react";
import { Link } from "react-router-dom";

import "./Home.css";
import API from "../../constants";

import bookMyShow from "./assets/bookmyshow-logo.png";


function Home() {

    const [count, setCount] = useState(0);

    function handleClick(e) {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user) {
            const userID = user.userData.userId;
            const URL = `${API}/orders/${userID}`;
            
            setCount(prevCount => prevCount + 3);
            
            e.preventDefault();

            axios.post(URL , { id: userID, title: "Classic Burger", price: 499, quantity: 3, image: "https://www.unileverfoodsolutions.com.sg/dam/global-ufs/mcos/SEA/calcmenu/recipes/SG-recipes/vegetables-&-vegetable-dishes/%E7%BB%8F%E5%85%B8%E8%8A%9D%E5%A3%AB%E6%B1%89%E5%A0%A1/main-header.jpg" })
                .then(()=> {
                    e.target.textContent = `Ordered (${count + 3})`;
                    e.target.style.backgroundColor = "green";
                    e.target.style.color = "white";
                })
                .catch(error => console.log(error))
        } else {
            e.preventDefault();
            const target = document.querySelectorAll('.buy-special')[0];
            target.innerText = "Please login to make orders!";
            target.style.backgroundColor = "black";
            target.style.color = "red";
        };
    };

    return (
        <div>
            <div id="main">
                <div className="greet">
                    <h1>Try our new reciepe on 
                        <span className="newandhot"> Search! </span>
                    </h1>
                </div>

                <div className="special-offers">
                    <h2>Special Offers!</h2>

                    <div className="card">
                        <div className="item-name">
                            <h2>Classic Burger</h2>
                        </div>

                        <div className="item-image">
                            <img src="https://th.bing.com/th/id/OIP.foUc1lQc_Z5OvIfsyd8UFgHaGo?w=209&h=187&c=7&r=0&o=5&dpr=1.3&pid=1.7" alt="burger"/>
                        </div>
                        
                        <div className="price-details">
                            <div>
                                <p>Buy 3 for <span className="price-number"> ₹499/- </span> </p>
                                
                                <p> Now only at <span className="name">Brrrgrrr</span> </p>
                                
                                <button className="buy-special" onClick={handleClick}> Buy Now! </button>
                            </div>
                        </div>
                    </div> 
                    
                    <div className="order-now">
                        <h3>Order Now using coupons</h3>
                        
                        <h3>Now available with 
                            <img className="bookmyshowlogo" src={bookMyShow} alt='bookmyshow'/>
                    
                            <a className="bookmyshowlink" href="https://in.bookmyshow.com" target="_blank" rel="noreferrer"> Bookmyshow! </a>
                        </h3>
                    </div>
                
                    <div className="customize">
                        <h1>Customize your Own Burger with our available ingredients!</h1>
                        
                        <h2>Create your own Happy snack!</h2>
                        
                        <Link to="/customize">
                            <button> Customize </button>
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Home;
