 import { useState, useEffect } from 'react';

 const useFetch = (url) => {
    const [data, setData] = useState(null)
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const abortCont = new AbortController();
        fetch(url, { signal: abortCont.signal})
        .then(res => {
            if (!res.ok) {
                throw Error('Could not fetch data for the resource!')
            }
        return res.json();
    })
        .then((data) => {
            setData(data); 
            setIsLoading(false)})
            .catch((error) => {
                if (error.name==="AbortError")
                {
                    console.log("fetch Aborted");
                }
                else{
                    setError(error.message);
                    setIsLoading(false);
                }
            })
            return () => abortCont.abort();
    },[url]);

    return { data, isLoading, error};
 }

 export default useFetch