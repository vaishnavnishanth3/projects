import Burgers from "../models/burgers.model.js";

async function getBurgers(req, res) {
    try {
        const burgers = await Burgers.find({});
        if (!burgers){
            res.status(404).json({ message: error.message })
        }
        res.json(burgers);
    } catch (error) {
        res.status(500).json({ message: error.message });
    };
};

async function postBurger(req,res) {
    try{
        const { name, description, price, image } = req.body;

        const existingBurger = await Burgers.findOne({ name });
        if (existingBurger) {
            return res.status(409).json({message: "Burger Already Exists"})
        } else {
            const burger = new Burgers({name, description, price, image});
            await burger.save();
        }
        res.status(201).json({message: "Burger Added Successfully"});
    } catch (error) {
        res.status(500).json({message: error.message})
    };
};

export { getBurgers, postBurger };