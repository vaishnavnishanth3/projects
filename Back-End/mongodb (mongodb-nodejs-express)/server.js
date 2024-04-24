import express from "express";
import { connectToDatabase, getDB } from "./db.mjs";
import { ObjectId } from "mongodb";

const app = express();
app.use(express.json());

const port = 3001;

let db = getDB();


connectToDatabase(err => {
    if (!err) {
        app.listen(port, () => {
            console.log(`Server is running on port ${port}`)
        })
        db = getDB()
    }
});

app.get('/books', (req, res) => {

    const page = req.query.page || 0;
    const limit = req.query.limit || 3;

    let books = [];
    db.collection('books')
        .find()
        .sort({ author: 1 })
        .skip(page* limit)
        .limit(limit)
        .forEach(book => books.push(book))
        .then(response => { res.status(200).json(books) })
        .catch((err) => {
            res.status(500).json({ mssg: "Error fetching books with error " + err })
        })
});

app.get('/books/:id', (req,res) => {
    if (ObjectId.isValid(req.params.id)) {
        db.collection('books')
    .findOne({_id: new ObjectId(req.params.id)})
    .then(doc => {
        res.status(200).json(doc)
    })
    .catch(err => {
        res.status(500).json({error: "Error fetching book"})
    })} else {
        res.status(500).json({error: "Invalid book id"})
    }})

app.post('/books', (req,res) => {
    const book = req.body

    db.collection('books')
    .insertOne(book)
    .then(result => {
        res.send(201).json(result)
    .catch(err=>{
        res.status(500).json({error: "Error inserting book"})
    })
    })
})

app.patch('/books/:id', (req,res) => {
    const updates = req.body
    if (ObjectId.isValid(req.params.id)) {
        db.collection('books').updateOne({_id: ObjectId(req.params.id)}, {$set: updates})
        .then(result => res.status(200).json(result))
        .catch(err => {
            res.status(500).json({error: "Error deleting book"})
        }) 
    } else {
        res.send(400).json({error: "Invalid book id"})
}})

app.delete('/books/:id', (req,res) => {
    if (ObjectId.isValid(req.params.id)) {
        db.collection('books').deleteOne({_id: ObjectId(req.params.id)})
        .then(result => res.status(200).json(result))
        .catch(err => {
            res.status(500).json({error: "Error deleting book"})
        }) 
    } else {
        res.send(400).json({error: "Invalid book id"})
}})