import React, { useState, useEffect, useContext } from "react";
import axios from "axios";

import "./Orders.css";
import Context from "../../contextStore/context";
import API from "../../constants"

function Orders() {

    const [isLoggedIn, setIsLoggedIn] = useState(false);
    
    const {userData} = useContext(Context);

    function handleCancel(id) {
        try {
            const userID = JSON.parse(localStorage.getItem("user")).userData.userId;
            const orderID = id;
    
            const URL = `${API}/orders/cancel/${userID}/${orderID}`;
            
            axios.delete(URL)
                .then(() => {
                    const target = document.getElementById(`order-${orderID}`);
                    target.innerHTML = "Order Canceled!"
                    target.style.backgroundColor = "red"
                    target.style.color = "black";
                })
                .catch(error => {
                    console.log("Error canceling order:", error);
                });
        } catch (error) {
            console.error("Error canceling order:", error);
        };
    };

    function handleCancelCustomize(id) {
        try {
            const userID = JSON.parse(localStorage.getItem("user")).userData.userId;
            const orderID = id;
    
            const URL = `${API}/customize/cancel/${userID}/${orderID}`;
            
            axios.delete(URL)
                .then(() => {
                    const target = document.getElementById(`order-${orderID}`);
                    target.innerHTML = "Order Canceled!";
                    target.style.backgroundColor = "red";
                    target.style.color = "black";
                })
                .catch(error => {
                    console.log("Error canceling order:", error);
                });
        } catch (error) {
            console.error("Error canceling order:", error);
        };
    };

    useEffect(() => {
        const user = JSON.parse(localStorage.getItem("user"));
        if (user && user.userData) {
            setIsLoggedIn(true);
        // eslint-disable-next-line react-hooks/exhaustive-deps
        }},[]);

    return (
        <div className="orders">
            {isLoggedIn && userData && userData.userData.orders.length > 0 ? (
                <div>
                    <h1>Order Details</h1>
                    <div>
                        <div className="orders-made">
                            {userData.userData.orders.map((order, index) => {
                                return (
                                    <div className="individual-order" key={index}>
                                        <div className="description">
                                            <h3>{order.name} ({order.quantity}) </h3>
                                            <p>₹ {order.price}</p>
                                        </div>
                                        <div className="image">
                                            <img src={order.image} alt={order.name} />
                                        </div>
                                        <button 
                                            id={`order-${order._id}`}
                                            className="cancel-button" 
                                            onClick={() => { handleCancel(order._id,"order") }}>
                                            Cancel Order
                                        </button>
                                    </div>
                                );
                            })}
                        </div>
                    </div>
                </div>
            ) : isLoggedIn && userData && userData.userData.orders.length === 0 ? (
                <div>
                    <div className="orders-listed">
                        <div className="orders-made">
                            <h3>No orders made yet!</h3>
                        </div>
                    </div>
                </div>
            ) : (
                <div>
                    <div className="orders-listed">
                        <div className="orders-made">
                            <h3>Please log in to view your orders.</h3>
                        </div>
                    </div>
                </div>
            )}

            {userData && userData.userData.customizedBurgers.length > 0 &&  (
                <div className="orders1">
                    <h1>Customized Burgers</h1>
                    <div className="orders-listed">
                        {userData.userData.customizedBurgers.map((burger, index) =>             
                            (
                            <div className="individual-order1" key={index}>
                                <div className="description">
                                    <h3>{burger.name} ({burger.quantity})</h3>
                                    <p>₹ {burger.price}</p>
                                </div>
                                
                                <div className="ingredients">
                                    {burger.ingredients.map((ingredient, i) => (
                                        <div key={i}>{ingredient}</div>
                                    ))}
                                </div>
                                <button 
                                    id={`order-${burger._id}`} 
                                    className="cancel-button" 
                                    onClick={() => handleCancelCustomize(burger._id)}> Cancel Order </button>
                            </div>
                        ))}
                    </div>
                </div>
            )}
        </div>
    );
};

export default Orders;
