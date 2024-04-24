import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import Header from './components/Header/Header';
import Home from "./components/Home/Home";
import About from "./components/About/About";
import Search from "./components/Search/Search";
import Customize from "./components/Customize/Customize";
import Orders from "./components/Orders/Orders";
import Account from "./components/Account/Account";
import Login from "./components/Login/Login";
import Signup from "./components/Signup/Signup";
import NotFound from "./components/NotFound/NotFound";
import Footer from './components/Footer/Footer';

import './App.css';

function App() {
    return (
        <div className="App">
            <Router>
                <div>
                    <Header />
                        <Routes>
                            <Route exact path="/" element={<Home />}/>

                            <Route path="/about" element={<About />}/>

                            <Route path="/search" element={< Search/>}/>

                            <Route path="/customize" element={<Customize />}/>

                            <Route path="/orders" element={<Orders />}/>

                            <Route path="/account" element={<Account />}/>

                            <Route path="/account/login" element={<Login />}/>

                            <Route path="/account/signup" element={<Signup />}/>

                            <Route path="*" element={<NotFound/>}/>
                        </Routes>
                    <Footer />
                </div>
            </Router>
        </div>
    );
};

export default App;
