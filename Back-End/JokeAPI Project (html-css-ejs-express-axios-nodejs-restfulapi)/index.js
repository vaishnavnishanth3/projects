import express from "express";
import axios from "axios";
import bodyParser from "body-parser";

const app = express();
const port = 3000;
const API_URL = "https://v2.jokeapi.dev/joke/Any";

app.use(express.static("public"));

app.use(bodyParser.urlencoded({ extended: true }));

app.get("/", async (req,res) => {
    try{
        const result = await axios.get(API_URL);
        res.render("index.ejs",{ content: result.data});
    }
    catch(error){
        res.render("index.ejs",{ content: error.message});
    }
})

app.listen(port, () => {
    console.log(`Server is up and running on port ${port}`);
})
