import mongoose from "mongoose";

const customerSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true
    },
    email: {
        type: String,
        required: true
    },
    password: {
        type: String,
        required: true
    },
    orders: [{
        name: {
            type: String,
            required: true
        },
        quantity: {
            type: Number,
            required: true
        },
        price: {
            type: Number,
            required: true
        },
        image: {
            type: String,
            require: true
        }
    }],
    customizedBurgers: [{
        name: {
            type: String,
            required: true
        },
        price: {
            type: Number,
            required: true,
            default: 200
        },
        quantity:{
            type: Number,
            required: true
        },
        ingredients: [{
            type: String,
            required: true
        }]
    }],
});

const UserRegisterInformation = mongoose.model("userregisters", customerSchema);

export default UserRegisterInformation;
