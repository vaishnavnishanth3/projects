import Product from "../models/product.model.js";

async function getProducts (req,res) {
    try
    {
        const products = await Product.find({})
        if (!products){
            return res.status(404).json({message: "No Products Found!"})
        }
        res.status(200).json(products)
    }
    catch(error)
    {
        res.status(500).json({message: error.message})
    }
}

async function getProduct (req,res) {
    try
    {
        const product = await Product.findById(req.params.id);
        res.status(200).json(product);
    }
    catch(error)
    {
        res.status(500).json({message: error.message})
    }
}

async function postProduct (req, res) {
    {
        try
        {
            const product = await Product.create(req.body)
            res.status(200).json(product);
        }
        catch(error)
        {
            res.status(500).json({message: error.message});
            console.log(error);
        }
    }
}

async function putProduct (req,res) {
    try
    {
        const product = await Product.findByIdAndUpdate(req.params.id, req.body);
        if (!product) {
            return res.status(404).json({message:"Product Not Found"})
        }

        const updatedProduct = await Product.findById(req.params.id);
        res.status(200).json(updatedProduct);
    }   
    catch(error)
    {
        res.status(500).json({messgae: error.message})
    }
}

async function deleteProduct (req,res) {
    try
    {
        const product = await Product.findByIdAndDelete(req.params.id);
        if (!product) {
            return res.status(404).json({message:"Product Not Found"})
        }
        res.status(200).json({message:"Product deleted successfully!"});
    }
    catch(error)
    {
        res.status(500).json({message:error.message})
    }
}

export  { getProducts, getProduct, postProduct, putProduct, deleteProduct };