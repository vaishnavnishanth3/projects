import React, { useState, useEffect } from "react";
import Card from "../Card/Card";

import "./Search.css";
import API from "../../constants";

function Search() {
    const [burgers, setBurgers] = useState([]);
    const [filteredBurgers, setFilteredBurgers] = useState([]);
    const [searchQuery, setSearchQuery] = useState("");

    const URL = `${API}/burgers`;

    useEffect(() => {
        fetch(URL)
            .then(response => response.json())
            .then(data => {
                setBurgers(data);
                setFilteredBurgers(data);
            })
            .catch(error => {
                console.log("Error Fetching burgers: " + error);
            });
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    function handleSearch (query) {
        setSearchQuery(query);
        const filtered = burgers.filter(burger => burger.name.toLowerCase().includes(query.toLowerCase()));
        setFilteredBurgers(filtered);
    };
    
    return (
        <div className="search1">
            <h1>Brrrgrrr for your favourite Burger!</h1>

            <div className="search-area">
                <input
                    id="browseInput"
                    type="text"
                    placeholder="Search..."
                    value={searchQuery}
                    onChange={(e) => handleSearch(e.target.value)}
                />

                <button> Brrrgrrr </button>
            </div>

            <div className="hot-ready">
                <h2> <span className="hot"> Hot </span> and Ready !! </h2>
            </div>

            <div className="burger-list">
                {filteredBurgers.map(burger =>
                    <div key={burger._id} className="cards">
                        <Card
                            title={burger.name}
                            image={burger.image}
                            price={burger.price}
                        />
                    </div>
                )}
            </div>
        </div>
    )
}

export default Search;
