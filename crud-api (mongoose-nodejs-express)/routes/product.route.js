import express from "express";
import { getProducts, getProduct, postProduct, putProduct, deleteProduct } from "../controllers/product.controller.js" 

const router = express.Router();

router.get('/', getProducts);
router.get('/:id', getProduct);
router.post('/', postProduct);
router.put('/:id', putProduct);
router.delete('/:id', deleteProduct);

export default router;