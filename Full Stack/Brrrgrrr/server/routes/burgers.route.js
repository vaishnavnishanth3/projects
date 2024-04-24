import express from "express";

import { getBurgers, postBurger } from "../controllers/burger.controller.js";

const router = express.Router();

router.get('/',getBurgers);
router.post('/',postBurger);

export default router;
