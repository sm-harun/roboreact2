// components/ProductDetail.js

import React, { useEffect, useState } from 'react';
import { View, Text, ActivityIndicator, Button, Alert, TextInput } from 'react-native';
import { useCart } from '../context/CartContext';
import {getProductById} from "../utils/api";
/*
import { getProductById } from '../services/ApiService'; // Assumed API function
*/

const ProductDetail = ({ route, navigation }) => {
    const initialProductId = route.params?.productId; // Handle cases where route.params might be undefined
    const [productId, setProductId] = useState(initialProductId || ''); // Local state for searching different products
    const [product, setProduct] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const { addCartItem } = useCart();

    useEffect(() => {
        if (productId) {
            const loadProductDetail = async (id) => {
                try {
                    setLoading(true);
                    const data = await getProductById(id);
                    setProduct(data);
                    setError(null); // Clear previous errors
                } catch (err) {
                    setProduct(null); // Reset product state to handle not found
                    setError(err.message); // Set error message if not found
                } finally {
                    setLoading(false);
                }
            };

            loadProductDetail(productId); // Load details based on current productId
        }
    }, [productId]); // Re-run effect if productId changes

    const addToCart = () => {
        if (product) { // Ensure product is not null
            addCartItem(product);
            Alert.alert('Added to Cart', `${product.title} has been added to your cart.`);
        }
    };

    const handleSearch = () => {
        if (productId) {
            setProductId(productId); // Triggers useEffect to fetch new product details
        }
    };

    if (loading) {
        return <ActivityIndicator size="large" color="#0000ff" />;
    }

    if (error) {
        return (
            <View>
                <Text>{`Error: ${error}`}</Text>
                <Button title="Go Back" onPress={() => navigation.goBack()} />
            </View>
        );
    }

    return (
        <View style={{ padding: 20 }}>
            <TextInput
                placeholder="Enter Product ID"
                value={productId}
                onChangeText={text => setProductId(text)}
                style={{ padding: 10, borderColor: 'gray', borderWidth: 1, marginBottom: 10 }}
            />
            <Button title="Search Product ID" onPress={handleSearch} />

            {product ? ( // Check if product exists
                <>
                    <Text style={{ fontSize: 24 }}>{product.title}</Text>
                    <Text style={{ marginVertical: 10 }}>{product.description}</Text>
                    <Text>Price: ${product.price}</Text>
                    <Text>Status: {product.status}</Text>
                    <Button title="Add to Cart" onPress={addToCart} />
                    <Button title="Go Back" onPress={() => navigation.goBack()} />
                </>
            ) : (
                <Text>No product found for ID: {productId}</Text> // Display message if no product is found
            )}
        </View>
    );
};

export default ProductDetail;
