import {MongoClient} from "mongodb";
import dotenv from "dotenv";

dotenv.config();

let dbConnection;

const URI = process.env.DB_URI;

export function connectToDatabase(cb) {
    MongoClient.connect(URI)
    .then((client) => {
        dbConnection = client.db()
        return cb()
    })
    .catch(error => {
        console.log(error)
        return cb(error)
    })
}

export function getDB() {
    return dbConnection
}