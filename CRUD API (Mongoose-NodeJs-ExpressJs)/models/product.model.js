import mongoose from "mongoose";
    
const ProductSchema = mongoose.Schema(
    {
        name: {
            type: String,
            required: [true, "Please add a name"]
        },
        quantity : {
            type: Number,
            required: [true, "Please add a quantity"]
        },
        price: {
            type: Number,
            required: true,
            default: 0
        },
        image: {
            type: String,
            required: false
        }
    },
    {
        timestamps: true
    }
)

const Product = mongoose.model("Product", ProductSchema);

export default Product;