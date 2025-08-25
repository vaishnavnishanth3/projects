import express from 'express';
import dotenv from "dotenv";
import mongoose from "mongoose";
import productRoute from './routes/product.route.js';

dotenv.config();

const app = express();
const port = 3001;

app.use(express.json());
app.use(express.urlencoded({extended:false}));

const connectionString = process.env.MONGODB_URI;

mongoose.connect(connectionString,
    {
        useNewUrlParser: true,
        useUnifiedTopology: true,
    })
    .then(() => console.log("Connected to database\n"))
    .catch(err => console.log(err));

app.get('/', (req, res) => {
    res.send("In Order Page");
});

app.use("/api/products", productRoute);

app.listen(port, () => {
    console.log(`\nServer is running on http://localhost:${port}`);
});
