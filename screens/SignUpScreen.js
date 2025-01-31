// src/screens/SignUpScreen.js
import React, { useState } from 'react';
import { View, TextInput, Button, Text, StyleSheet } from 'react-native';
import { signup } from "../utils/api";

const SignupScreen = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleSignup = async () => {
        try {
            await signup(username, password);
            // Optional: Redirect on successful signup
        } catch (err) {
            setError('Signup failed. Please try again.');
        }
    };

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Sign Up</Text>
            {error ? <Text style={styles.error}>{error}</Text> : null}
            <TextInput
                style={styles.input}
                placeholder="Username"
                value={username}
                onChangeText={(text) => setUsername(text)}
            />
            <TextInput
                style={styles.input}
                placeholder="Password"
                value={password}
                onChangeText={(text) => setPassword(text)}
                secureTextEntry
            />
            <Button title="Sign Up" onPress={handleSignup} />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 16,
        justifyContent: 'center',
    },
    title: {
        fontSize: 24,
        textAlign: 'center',
        marginBottom: 20,
    },
    input: {
        height: 40,
        borderColor: '#ddd',
        borderWidth: 1,
        marginBottom: 12,
        paddingHorizontal: 8,
    },
    error: {
        color: 'red',
        marginBottom: 12,
    },
});

export default SignupScreen;
/*

import React, { useState } from 'react';
import { View, TextInput, Button, Text, Alert } from 'react-native';
import { signUpUser } from '../api/api'; // Import the sign-up function from api.js

const SignUpScreen = ({ navigation }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleSignUp = async () => {
        try {
            const userData = { email, password }; // Prepare user data object
            await signUpUser(userData); // Call the sign-up API

            navigation.navigate('Home'); // Navigate to Profile upon successful signup
        } catch (error) {
            setError(error.message); // Capture any error message
            Alert.alert('Error', error.message); // Show alert with the error
        }
    };

    return (
        <View>
            <Text>Sign Up</Text>
            <TextInput
                placeholder="Email"
                value={email}
                onChangeText={setEmail}
                keyboardType="email-address"
            />
            <TextInput
                placeholder="Password"
                value={password}
                onChangeText={setPassword}
                secureTextEntry
            />
            {error ? <Text>{error}</Text> : null}
            <Button title="Sign Up" onPress={handleSignUp} />
            <Button title="Go to Sign In" onPress={() => navigation.navigate('SignIn')} />
        </View>
    );
};

export default SignUpScreen;*/
/*

// screens/SignUpScreen.js
import React, { useState } from 'react';
import { View, TextInput, Button, Text } from 'react-native';
import { auth } from '../firebase';

const SignUpScreen = ({ navigation, route }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    // const { email, password } = route.params; // Retrieve the token passed from LoginScreen    //const { username, password } = route.params; // Retrieve credentials from route params

    const handleSignUp = () => {
        auth().createUserWithEmailAndPassword(email, password)
            .then(() => {
               navigation.navigate('Profile');
             //   navigation.navigate('Home');

            })
            .catch(error => setError(error.message));
    };

    return (
        <View>
            <TextInput
                placeholder="Email"
                value={email}
                onChangeText={setEmail}
            />
            <TextInput
                placeholder="Password"
                value={password}
                onChangeText={setPassword}
                secureTextEntry
            />
            {error ? <Text>{error}</Text> : null}
            <Button title="Sign Up" onPress={handleSignUp} />

        </View>
    );
};

export default SignUpScreen;
*/
