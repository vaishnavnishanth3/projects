import { useEffect, useState } from "react";
import axios from "axios";

import Context from "./context";
import API from "../constants";


function ContextProvider(props) {
    
    const [userData , setUserData] = useState(null);
    
    const contextValues = {
        userData,
        setUserData
    }

    useEffect(() => {
        if(localStorage.getItem('user'))
            axios.get(`${API}/orders/${JSON.parse(localStorage.getItem("user")).userData.userId}`)
            .then(res=>{
                const user = JSON.parse(localStorage.getItem("user"))
                setUserData({userData:{...user.userData,orders:res.data.order,customizedBurgers:res.data.customizedBurgers}})
            })
    // eslint-disable-next-line react-hooks/exhaustive-deps
    },[localStorage.getItem('user')])

    return <Context.Provider value={contextValues}>
                {props.children}
    </Context.Provider>
}

export default ContextProvider;
