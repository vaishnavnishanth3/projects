import express from "express";

import { signinUser, loginUser, getOrders, saveOrder, saveCustomized, cancelOrder, cancelCustomized} from "../controllers/user.controller.js";

const router = express.Router();

router.post("/account/signup", signinUser);
router.post("/account/login", loginUser);
router.get("/orders/:id", getOrders);
router.post("/orders/:id", saveOrder);
router.post("/customize/:id", saveCustomized);
router.delete("/orders/cancel/:userID/:orderId", cancelOrder);
router.delete("/customize/cancel/:userID/:orderID", cancelCustomized);

export default router;