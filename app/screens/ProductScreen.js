import React, { useEffect, useState } from 'react';
import { View, FlatList, StyleSheet } from 'react-native';
import api from '../utils/api2';
import ProductCard from '../components/ProductCard'; // Import the ProductCard component
import { useContext } from 'react';
import { CartContext } from '../context/CartContext'; // Assume you have CartContext to manage cart state

const ProductsScreen = () => {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const { addToCart } = useContext(CartContext); // Access cart context

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await api.get('/products'); // Fetch products
                setProducts(response.data);
            } catch (error) {
                console.error(error);
            } finally {
                setLoading(false);
            }
        };

        fetchProducts();
    }, []);

    const handleAddToCart = (product) => {
        addToCart(product); // Call the function to add product to the cart
    };

    return (
        <View style={styles.container}>
            {loading ? (
                <Text>Loading...</Text>
            ) : (
                <FlatList
                    data={products}
                    renderItem={({ item }) => (
                        <ProductCard product={item} onAddToCart={handleAddToCart} />
                    )}
                    keyExtractor={(item) => item.id.toString()}
                    numColumns={2} // Display two products per row
                />
            )}
        </View>
    );
};
/*
 return (
        <View style={styles.container}>
            {loading ? (
                <Loader />
            ) : (
                <FlatList
                    data={products}
                    renderItem={({ item }) => (
                        <ProductCard
                            product={item}
                            onPress={() => navigation.navigate('ProductDetails', { productId: item.id })}
                        />
                    )}
                    keyExtractor={(item) => item.id.toString()}
                />
            )}
        </View>
    );
};
 */
const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 16,
    },
});

export default ProductsScreen;


/*
import React, { useEffect, useState } from 'react';
import { View, Text, FlatList, ActivityIndicator, TouchableOpacity, StyleSheet } from 'react-native';
import Header from '../components/Header';
import Footer from '../components/Footer';
//import api from '../services/api'; // Importing the API utility
import {getAllProducts} from "../api/api";

const ProductsScreen = ({ navigation }) => {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const data = await getAllProducts(); // Fetch product data
                console.log("Fetched Products: ", data); // Log to check the data structure
                setProducts(data); // Set data to products
            } catch (err) {
                setError('Failed to load products: ' + err.message); // Set error message
            } finally {
                setLoading(false); // End loading
            }
        };

        fetchProducts();
    }, []);

    const renderItem = ({ item }) => (
        <TouchableOpacity
            style={styles.productItem}
            onPress={() => navigation.navigate('ProductDetails', {
                id: item.id, // Pass the product ID to the details screen
                title: item.title,
                description: item.description,
                price: item.price,
            })}
        >
            <Text style={styles.productTitle}>{item.title}</Text>
            <Text>{item.description}</Text>
            <Text style={styles.productPrice}>${item.price}</Text>
        </TouchableOpacity>
    );

    if (loading) {
        return <ActivityIndicator size="large" color="#4CAF50" style={styles.loader} />;
    }

    if (error) {
        return <Text style={styles.errorText}>Error: {error}</Text>;
    }

    return (
        <View style={styles.container}>
            {/!*<Header />*!/}
            <Text style={styles.title}>Products</Text>
            <FlatList
                data={products}
                keyExtractor={item => item.id ? item.id.toString() : Math.random().toString()} // Ensure each item has a unique ID
                renderItem={renderItem} // Render each product item
            />
            {/!*<Footer />*!/}
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 20,
    },
    title: {
        fontSize: 24,
        textAlign: 'center',
        marginVertical: 20,
    },
    productItem: {
        padding: 15,
        marginVertical: 8,
        backgroundColor: '#f9f9f9',
        borderRadius: 5,
    },
    productTitle: {
        fontSize: 18,
        fontWeight: 'bold',
    },
    productPrice: {
        color: '#4CAF50',
        marginTop: 5,
    },
    loader: {
        flex: 1,
        justifyContent: 'center',
    },
    errorText: {
        textAlign: 'center',
        marginTop: 20,
        color: 'red',
    },
});

export default ProductsScreen;*/
/*
// ProductsScreen.js
import React, { useEffect, useState } from 'react';
import { View, Text, FlatList, ActivityIndicator, TouchableOpacity, StyleSheet } from 'react-native';
import Header from '../components/Header';
import Footer from '../components/Footer';
import {getAllProducts} from "../api/api";
/!*import getAllProducts from '../services/api';
import api from "../services/api";*!/
// import productService from "../services/api"; // Import the product service

const ProductsScreen = ({ navigation }) => {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const data = await getAllProducts(); // Fetch product data
                setProducts(data);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        fetchProducts();
    }, []);
    const renderItem = ({ item }) => (
        <TouchableOpacity
            style={styles.productItem}
            onPress={() => navigation.navigate('ProductDetails', {
               title: item.title,
                description: item.description,
                price: item.price,
                // Add any additional properties as needed
            })}
        >
            <Text style={styles.productTitle}>{item.title}</Text>
            <Text>{item.description}</Text>
            <Text style={styles.productPrice}>${item.price}</Text>
        </TouchableOpacity>
    );

    if (loading) {
        return <ActivityIndicator size="large" color="#4CAF50" style={styles.loader} />;
    }

    if (error) {
        return <Text style={styles.errorText}>Error: {error}</Text>;
    }

    return (
        <View style={styles.container}>
            {/!*<Header />*!/}
            {/!*<Text style={styles.title}>Products</Text>*!/}
            <FlatList
                data={products}
                keyExtractor={item => item.productId.toString()} // Ensure each item has a unique ID
                renderItem={renderItem}
            />
            {/!*<Footer />*!/}
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 20,
    },
    title: {
        fontSize: 24,
        textAlign: 'center',
        marginVertical: 20,
    },
    productItem: {
        padding: 15,
        marginVertical: 8,
        backgroundColor: '#f9f9f9',
        borderRadius: 5,
        shadowColor: '#000',
        shadowOffset: {
            width: 0,
            height: 1,
        },
        shadowOpacity: 0.2,
        shadowRadius: 1.5,
        elevation: 3,
    },
    productTitle: {
        fontSize: 18,
        fontWeight: 'bold',
    },
    productPrice: {
        color: '#4CAF50',
        marginTop: 5,
    },
    loader: {
        flex: 1,
        justifyContent: 'center',
    },
    errorText: {
        textAlign: 'center',
        marginTop: 20,
        color: 'red',
    },
});

export default ProductsScreen;*/
/*
// ProductsScreen.js
import React, { useEffect, useState } from 'react';
import { View, Text, FlatList, StyleSheet, ActivityIndicator, TouchableOpacity } from 'react-native';
import Header from '../components/Header';
import Footer from '../components/Footer';
import productService from '../services/api'; // Assuming you have a service for fetching products

const ProductsScreen = () => {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const data = await productService.getProducts(); // Fetch product data from service
                setProducts(data);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        fetchProducts();
    }, []);

    const renderItem = ({ item }) => (
        <TouchableOpacity style={styles.productItem}>
            <Text style={styles.productTitle}>{item.title}</Text>
            <Text>{item.description}</Text>
            <Text style={styles.productPrice}>${item.price}</Text>
        </TouchableOpacity>
    );

    if (loading) {
        return <ActivityIndicator size="large" color="#4CAF50" style={styles.loader} />;
    }

    if (error) {
        return <Text style={styles.errorText}>Error: {error}</Text>;
    }

    return (
        <View style={styles.container}>
            <Header />
            <Text style={styles.title}>Products</Text>
            <FlatList
                data={products}
                keyExtractor={(item) => item.id.toString()} // Assuming each item has an 'id' property
                renderItem={renderItem}
            />
            <Footer />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 20,
    },
    title: {
        fontSize: 24,
        textAlign: 'center',
        marginVertical: 20,
    },
    productItem: {
        padding: 15,
        marginVertical: 8,
        backgroundColor: '#f9f9f9',
        borderRadius: 5,
        shadowColor: '#000',
        shadowOffset: {
            width: 0,
            height: 1,
        },
        shadowOpacity: 0.2,
        shadowRadius: 1.5,
        elevation: 3,
    },
    productTitle: {
        fontSize: 18,
        fontWeight: 'bold',
    },
    productPrice: {
        color: '#4CAF50',
        marginTop: 5,
    },
    loader: {
        flex: 1,
        justifyContent: 'center',
    },
    errorText: {
        textAlign: 'center',
        marginTop: 20,
        color: 'red',
    },
});

export default ProductsScreen;*/
// Example Filtered View (ProductsScreen.js):

/*
// Add state for selected category
const [selectedCategory, setSelectedCategory] = useState('All');

// Example categories array (could be fetched from an API or database)
const categories = ['All', 'Robotics', 'Programming', 'Mechanical', 'Electronics'];

const filteredProducts = products.filter(product =>
    selectedCategory === 'All' || product.category === selectedCategory
);

// In the return statement, include a Picker for categories:
<Picker
    selectedValue={selectedCategory}
    onValueChange={(itemValue) => setSelectedCategory(itemValue)}
>
    {categories.map(category => (
        <Picker.Item label={category} value={category} key={category} />
    ))}
</Picker>
*/
