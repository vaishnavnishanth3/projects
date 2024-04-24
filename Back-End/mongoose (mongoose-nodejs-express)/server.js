import express from "express";
import cors from "cors";
import mongoose from "mongoose";
import dotenv from "dotenv";
import Customer from "./models/Customer.js";

dotenv.config();

const connectionString = process.env.MONGODB_CONNECTION_STRING;

const app = express()
const port = 3001

app.use(express.json())
app.use(cors())

mongoose.connect(
    connectionString, {
     useNewUrlParser: true,
     useUnifiedTopology: true 
    }
)

const connection = mongoose.connection;

connection.once('open', () => {
    console.log("MongoDB database connection established successfully");
})

app.post('/customer', async(req, res) => {
    try{
        console.log("req.body: "+req.body)
        const newCustomer = new Customer({
            customerFirstName: req.body.customerFirstName,
            customerLastName: req.body.customerLastName
        });
        await Customer.create(newCustomer);
        res.send("Customer Added!")
    } catch(error) {
        console.log(error);
    }
})

app.listen(port, () => {
    console.log(`\nApp is listening at http://localhost:${port}`)
})