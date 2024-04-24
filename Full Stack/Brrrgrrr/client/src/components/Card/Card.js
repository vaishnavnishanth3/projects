import React, { useContext, useState } from "react";
import axios from "axios";

import "./Card.css";
import Context from "../../contextStore/context";
import API from "../../constants"

function Card(props) {

    const { title, price, image } = props;
    
    const [count, setCount] = useState(0);
    
    const {
        // eslint-disable-next-line no-unused-vars
        userData,
        setUserData
    } = useContext(Context);

    function handleClick(e) {

        const user = JSON.parse(localStorage.getItem('user'));
        
        if (user) {
            const userID = user.userData.userId;
            const URL = `${API}/orders/${userID}`;
            
            setCount(prevCount => prevCount + 1);
    
            axios.post(URL, { id: userID, title, price, image })
                .then((res) => {
                    setUserData((prev) => {
                        return {userData:{...prev.userData,orders:res.data.orders}}
                    });
                    
                    e.target.textContent = `Ordered (${count + 1})`;
                    e.target.style.backgroundColor = "green";
                    e.target.style.color = "white";
                })
                .catch(error => console.log(error));
        } else {
            e.target.innerText = "Please login to make orders!";
            e.target.style.backgroundColor = "black";
            e.target.style.color = "red";
        };
    };

    return (
        <div className="Card">
            <div className="card-image">
                <img src={image} alt={title} />
            </div>
            <div className="card-body">
                <h5>{title}</h5>
                <p><span className="card-price">₹{price}</span></p>
                <button className="order-button" onClick={handleClick}>Order</button>
            </div>
        </div>
    );
};

export default Card;
