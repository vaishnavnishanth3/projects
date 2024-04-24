import User from "../models/userregister.model.js";

async function signinUser(req,res) {
    try {
        const { name, email, password } = req.body;
        const existingUser = await User.findOne({ email });
        
        if (existingUser) {
            return res.status(400).json({ message: "User already exists" });
        } else {  
            const newUser = new User({ name, email, password });
            await newUser.save();
        };
        res.status(201).json({ message: "User created successfully" });
    } catch (error) {
            res.status(500).json({ message: "Internal server error" });
        }
};

async function loginUser (req, res) {
    const { email, password } = req.body
    
    try {
        const user = await User.findOne({ email });
        if (!user) {
            return res.status(404).json({ message: "User not found" });
        }
        
        if (user.password !== password) {
            return res.status(401).json({ message: "Invalid password" });
        }

        const { name, orders, customizedBurgers,_id } = user;
        res.status(200).json({ message: "Login successful", name, orders, customizedBurgers, userId: _id });
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

async function getOrders (req,res) {
    try{
        const userID = req.params.id
        const user = await User.findById({ _id: userID })

        if(!user){
            return res.status(404).json({message: "User Not Found"})
        }
        const orders = user.orders;
        const customizedBurgers = user.customizedBurgers;
        res.status(200).json({order: orders, customizedBurgers});
    } catch(error) {
        res.status(500).json({message: error.message});
    };
};

async function saveOrder(req, res) {
    try {
        const { title, price, quantity, image } = req.body;
        const user = await User.findById(req.params.id);
        
        var newOrder;
        if (!user) {
            return res.status(404).json({ message: "User not found" });
        }

        const existingOrder = user.orders.find(order => order.name === title);
        
        if (existingOrder) {
            if (quantity){
                existingOrder.quantity += quantity;        
            } else {
                existingOrder.quantity += 1;
            }; 
            existingOrder.price += price;
        } else {
            if (quantity){
                newOrder = { name: title, quantity: quantity, price: price, image: image };
                user.orders.push(newOrder);
            } else {
                newOrder = { name: title, quantity: 1, price: price, image: image };    
                user.orders.push(newOrder);
            };
        };
        await user.save();
        res.status(200).json({ message: "Order submitted successfully", orders: user.orders });
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
};

async function saveCustomized (req, res) {
    try {
        const { burgerName, ingredients, quantity } = req.body;
        const user = await User.findById(req.params.id);
        if (!user){
            return res.status(404).json({ message: "User Not Found" });
        }
    
        const existingCustomizedOrder = user.customizedBurgers.find(order => order.name === burgerName);
        if (existingCustomizedOrder) {
            return res.status(409).json({ message: "Burger already customized" });
        }

        const newCustomizedOrder = { name: burgerName, ingredients: ingredients , price: 200*quantity, quantity: quantity};
        user.customizedBurgers.push(newCustomizedOrder);
        await user.save();
    
        res.status(200).json({ message: "Order submitted successfully",custom:user.customizedBurgers });
        } catch (error) {
            res.status(500).json({ message: error.message });
    };
};

async function cancelOrder (req,res) {
    try {
        const userId = req.params.userID;
        const orderId = req.params.orderId;

        const user = await User.findById(userId);
        if (!user) {
            return res.status(404).json({ message: "User not found" });
        }
        user.orders = user.orders.filter(order => {
            return order._id.toString() !== orderId;
        });
    
        await user.save();

        res.status(200).json({ message: "Order deleted successfully", order:user.orders });
    } catch (error) {
        res.status(500).json({message:error.message})
    };
};

async function cancelCustomized (req,res) {
    try {
        const userId = req.params.userID;
        const orderId = req.params.orderID;

        const user = await User.findById(userId);
        if (!user) {
            return res.status(404).json({ message: "User not found" });
        }
        user.customizedBurgers = user.customizedBurgers.filter(order => {
            return order._id.toString() !== orderId;
        });
    
        await user.save();

        res.status(200).json({ message: "Order deleted successfully", order:user.orders });
    } catch (error) {
        res.status(500).json({message:error.message})
    };
};

export { signinUser, loginUser, getOrders, saveOrder, saveCustomized, cancelOrder, cancelCustomized };
