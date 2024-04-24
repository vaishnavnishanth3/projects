import express from "express";
import mongoose from "mongoose";
import cors from "cors";
import EmployeeModel from "./models/Employee.mjs";
import dotenv from "dotenv";

dotenv.config();

const app = express();
const port = 3000;

app.use(express.json())
app.use(cors())

URL = process.env.MONGODB_URL

mongoose.connect(URL,{
    useNewUrlParser: true,
    useUnifiedTopology: true
})
.then(() => {
    console.log("Connected to DataBase!");
})
.catch((error)=> {
    console.log(error);
})

app.post('/register', (req,res) => {
    EmployeeModel.create(req.body)
    .then(employees=> res.json(employees))
    .catch(err => res.json(err))
})

app.post('/login', (req,res) => {
    const [email, password] = req.body;
    EmployeeModel.findOne({email:email})
    .then(user => {
        if (user) {
            if (user.password === password) {
                res.json("Success")
            } else{
                res.json("Incorrect Password")
            }

        } else {
            res.json("No records!")
        }
    })
})

app.listen(3000,() => {
    console.log(`Server is Up and Running on port ${port}!`);
})



