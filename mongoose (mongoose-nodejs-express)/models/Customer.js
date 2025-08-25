import mongoose from "mongoose";
const Schema = mongoose.Schema;

const customerSchema = new Schema({
    customerFirstName: {
        type: String,
        required: true
    },
    customerLastName: {
        type: String,
        required: false
    }
});

const Customer = mongoose.model("Customer", customerSchema);

export default Customer;