import React from "react";

const Context = React.createContext({
    orders:[],
    setOrders:()=>{}
});

export default Context;