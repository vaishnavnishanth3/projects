import express from "express";

const app = express();

const port = 3000;

app.use(express.static("public"));

app.get("/",(req,res) => {
    res.render("index.ejs");
})

app.get("/essays",(req,res) => {
    res.render("essays.ejs");
})

app.get("/faq",(req,res) => {
    res.render("faq.ejs");
})

app.get("/contact",(req,res) => {
    res.render("contact.ejs");
})

app.get("/about",(req,res) => {
    res.render("about.ejs");
})

app.get("/login",(req,res) => {
    res.render("login.ejs");
})

app.get("/signup",(req,res) => {
    res.render("signup.ejs");
})

app.get("/skdev",(req,res) => {
    res.render("skdev.ejs");
})

app.listen(port, () => {
    console.log(`Server is up and running on port ${port}`);
})