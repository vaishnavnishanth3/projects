import mongoose from "mongoose";

const burgerSchema = new mongoose.Schema(
    {   
        key:{
            type: Number,
            required: false
        },
        id:{
            type: Number,
            required: false
        },
        name:{
            type: String,
            required: true
        },
        description:{
            type: String,
            required: true
        },
        price:{
            type: Number,
            required: true
        },
        image:{
            type: String,
            required: true
        }
    }
);

const Burgers = mongoose.model("burgers-details",burgerSchema)

export default Burgers;
