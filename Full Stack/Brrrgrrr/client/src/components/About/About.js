import React from "react";

import "./About.css";

import burgerImage from "./assets/burger-image.jpeg";
import cityImage from "./assets/cities.jpeg";

function About() {
    
    return (
        <div className="about">
            <h1 className="title"> About <span className="app-name"> Brrrgrrr </span> </h1>
            
            <div className="content">
                <p>Welcome to Brrrgrrr Burger Company, where passion for great taste meets dedication to quality ingredients! At Brrrgrrr, we believe that every bite should be an experience worth savoring. That's why we're committed to crafting the finest gourmet gurgers that tantalize your taste buds and leave you craving for more. Our journey began with a simple vision: to reinvent the classic gurger, elevating it to new heights with innovative flavors and premium ingredients. Every gurger we serve is a masterpiece, carefully handcrafted with love and attention to detail. What sets us apart is our unwavering commitment to quality. We source| only the freshest, locally-sourced ingredients, ensuring that every bite bursts with flavor and goodness. From our succulent beef patties to our artisanal buns and signature sauces, every component is thoughtfully selected to deliver a culinary experience like no other. But it's not just about the food—it's about the experience. </p>
                
                <img className="burger" src={burgerImage} alt="burger"/>
                
                <p>With branches citywide, enjoy our gourmet gurgers conveniently no matter where you are. Consistent quality and service await you at every visit. Whether you're in the bustling streets of New York City, the sunny beaches of Los Angeles, the historic neighborhoods of London, the vibrant avenues of Tokyo, or the charming districts of Paris, there's a Brrrgrrr branch nearby ready to satisfy your cravings. From our flagship location in the heart of the city to our cozy neighborhood spots, each branch offers a unique atmosphere and dining experience. Plus, with our convenient online ordering and delivery services, you can enjoy our delicious gurgers from the comfort of your own home.</p>
                
                <img className="city" src={cityImage} alt="city"/>
                
                <p>When you step into our restaurant, you're greeted with warmth and hospitality that make you feel right at home. Whether you're dining in with friends and family or grabbing a quick bite on the go, our friendly staff are here to make your experience memorable. As a proudly independent, locally-owned business. We believe in giving back and supporting the causes that matter most. That's why we partner with local farmers and suppliers, strive to minimize our environmental footprint, and actively engage in initiatives that make a positive impact on society. So come join us on a culinary adventure unlike any other. Whether you're a longtime fan or a first-time visitor, we invite you to experience the joy of Brrrgrrr and taste the difference that passion and quality make. Thank you for choosing Brrrgrrr, where every bite is a celebration of flavor!</p>
            </div>
        </div>
    );
};

export default About;