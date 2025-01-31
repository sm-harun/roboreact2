// App.js
import React, { useState, useEffect } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import HomeScreen from "./screens/HomeScreen";
import ShopScreen from './screens/ShopScreen';
import LoginScreen from './screens/LoginScreen';
import SignUpScreen from "./screens/SignUpScreen";
import MyAccountScreen from './screens/MyAccountScreen';
import AboutScreen from './screens/AboutScreen';
import CoursesScreen from "./screens/CoursesScreen";
import CourseDetailsScreen from "./screens/CourseDetailsScreen";
import ProductsScreen from "./screens/ProductScreen";
import ProductDetailsScreen from "./screens/ProductDetailsScreen";
import CompetitionsScreen from "./screens/CompetitionsScreen";
import CompetitionDetailsScreen from "./screens/CompetitionDetailsScreen";
import CartScreen from "./screens/CartScreen";
import EventDetailsScreen from "./screens/EventDetailsScreen";
import ResourcesListScreen from "./screens/ResourcesListScreen";
import EventListScreen from "./screens/EventListScreen";
import SupportScreen from "./screens/SupportScreen";
import ResourceDetailsScreen from "./screens/ResourceDetailsScreen";
import CareersScreen from "./screens/CareersScreen";
import PartnerScreen from "./screens/PartnerScreen";
import {Text} from "react-native"; // New About Screen

const Stack = createStackNavigator();

const App = () => {
        <NavigationContainer>
            <Stack.Navigator initialRouteName="About">
                <Stack.Screen name="Home" component={HomeScreen} />
                <Stack.Screen name="About" component={AboutScreen} />
                <Stack.Screen name="Shop" component={ShopScreen} />
                <Stack.Screen name="Login" component={LoginScreen} />
                <Stack.Screen name="Signup" component={SignUpScreen} />
                <Stack.Screen name="MyAccount" component={MyAccountScreen} />
                <Stack.Screen name="Courses" component={CoursesScreen} />
                <Stack.Screen name="CourseDetails" component={CourseDetailsScreen} />
                <Stack.Screen name="Events" component={EventListScreen} />
                <Stack.Screen name="EventDetails" component={EventDetailsScreen} />
                <Stack.Screen name="Resources" component={ResourcesListScreen} />
                <Stack.Screen name="ResourceDetails" component={ResourceDetailsScreen} />
                <Stack.Screen name="Products" component={ProductsScreen} />
                <Stack.Screen name="ProductDetails" component={ProductDetailsScreen} />
                <Stack.Screen name="Cart" component={CartScreen} />
                <Stack.Screen name="Competitions" component={CompetitionsScreen} />
                <Stack.Screen name="CompetitionDetails" component={CompetitionDetailsScreen} />
                <Stack.Screen name="Careers" component={CareersScreen} />
                <Stack.Screen name="Partners" component={PartnerScreen} />
                <Stack.Screen name="Support" component={SupportScreen} />
            </Stack.Navigator>
        </NavigationContainer>
};

export default App;


/*
/*import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { UserProvider } from './context/UserContext';
import AppNavigator from './navigation/AppNavigator';
import AboutScreen from './screens/AboutScreen';
import HomeScreen from "./screens/HomeScreen";

import { createStackNavigator } from '@react-navigation/stack';
import {AuthProvider} from "./context/AuthContext";
import {CartProvider} from "./context/CartContext";
const Stack = createStackNavigator();


const App = () => {
    return (
   *//*     <AuthProvider>
            <CartProvider>
                <UserProvider>*//*
            <NavigationContainer initialRouteName="Home">
                               <Stack.Screen name="Home" component={HomeScreen} />
                               <Stack.Screen name="About" component={AboutScreen} />
                               <Stack.Screen name="Shop" component={ShopScreen} />
                               <Stack.Screen name="Login" component={LoginScreen} />
                               <Stack.Screen name="Signup" component={SignUpScreen} />
                               <Stack.Screen name="MyAccount" component={MyAccountScreen} />

            </NavigationContainer>
     *//*           </UserProvider>
            </CartProvider>
        </AuthProvider>*//*
    );
};

export default App;*/
/*
import React, { useState, useEffect } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { AuthProvider } from './context/AuthContext'; // Ensure this context manages authentication state
import AppNavigator from './navigation/AppNavigator'; // Import the navigator you created

const App = () => {
    // State for user authentication
    const [user, setUser] = useState(null); // or set initial state according to your logic

    useEffect(() => {
        // Simulate fetching user data (replace with your actual authentication logic)
        const fetchUser = async () => {
            // Here you might want to check if the user is logged in, e.g., check local storage
            const token = localStorage.getItem('jwt'); // Example of fetching JWT
            if (token) {
                // Fetch user details based on token
                // for example:
                const fetchedUser = await api.getUserDetails(token);
                setUser(fetchedUser); // Adjust according to how you store user data
            }
        };

        fetchUser();
    }, []);

    return (
        <AuthProvider value={{ user, setUser }}>
            <NavigationContainer>
                <AppNavigator />
            </NavigationContainer>
        </AuthProvider>
    );
};

export default App;
*/



/*import React, {useState} from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import HomeScreen from './screens/HomeScreen'; // Adjust import paths as necessary
import AboutUsPage from './screens/AboutScreen'; // Page for About Us
import CareersPage from './screens/CareersPage'; // New page for Careers
import OurTeamPage from './screens/OurTeamPage'; // New page for Our Team
import OurClientsPage from './screens/OurClientsPage'; // New page for Our Clients
import ServicesPage from './screens/ServicesPage';
import ProductScreen from "./screens/ProductScreen";
import ProductDetailsScreen from "./screens/ProductDetailsScreen";
import AddProduct from "./screens/AddProduct";
/!*import PortfolioPage from './components/PortfolioPage'; // Page for Portfolio
import NewsPage from './components/NewsPage'; // Page for News
import ContactUsPage from './components/ContactUsPage'; // Page for Contact Us*!/

const Stack = createStackNavigator();

const App = () => {
  //  const [ProductDetailsScreen, setProductDetailsScreen] = useState()

    return (
        <NavigationContainer>
            <Stack.Navigator initialRouteName="Home">
                <Stack.Screen name="Home" component={HomeScreen} options={{ headerShown: false }} />
             {/!*   <Stack.Screen name="AboutUs" component={AboutUsPage} options={{ headerShown: false }} />
                <Stack.Screen name="Careers" component={CareersPage} options={{ headerShown: false }} />
                <Stack.Screen name="OurTeam" component={OurTeamPage} options={{ headerShown: false }} />
                <Stack.Screen name="OurClients" component={OurClientsPage} options={{ headerShown: false }} />*!/}
              <Stack.Screen name="Products" component={ProductScreen} options={{ headerShown: false }} />
                <Stack.Screen name="ProductDetails" component={ProductDetailsScreen} options={{ headerShown: false }} />
                <Stack.Screen name="AddProducts" component={AddProduct} options={{ headerShown: false }} />
                {/!*<Stack.Screen name="Portfolio" component={PortfolioPage} options={{ headerShown: false }} />
                <Stack.Screen name="News" component={NewsPage} options={{ headerShown: false }} />
                <Stack.Screen name="ContactUs" component={ContactUsPage} options={{ headerShown: false }} />*!/}
            </Stack.Navigator>
        </NavigationContainer>
    );
};

export default App;*/
/*
// App.js
import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import Header from './components/Header';
import Menu from './components/Menu';
import HomeScreen from './components/HomeScreen';
import AboutUsPage from './components/AboutUsPage';
import ServicesPage from "./components/ServicesPage";
// import other screens...const Stack = createStackNavigator();
const Stack = createStackNavigator(); // Initialize Stack Navigator

    const App = () => {
        return (
            <NavigationContainer>
                <Stack.Navigator initialRouteName="Home">
                    <Stack.Screen name="Home" component={HomeScreen} options={{ headerShown: false }} />
                    <Stack.Screen name="AboutUsPage" component={AboutUsPage} options={{ headerShown: false }} />
                    <Stack.Screen name="Services" component={ServicesPage} options={{ headerShown: false }} />
                 {/!*   <Stack.Screen name="Portfolio" component={PortfolioPage} options={{ headerShown: false }} />
                    <Stack.Screen name="News" component={NewsPage} options={{ headerShown: false }} />
                    <Stack.Screen name="ContactUs" component={ContactUsPage} options={{ headerShown: false }} />*!/}
                </Stack.Navigator>
            </NavigationContainer>
        );
    };

export default App;
*/

/*import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { UserProvider } from './context/UserContext';
import { CartProvider } from './context/CartContext';
import AppNavigator from './navigation/AppNavigator';

const App = () => {
    return (
        <UserProvider>
            <CartProvider>
                <NavigationContainer>
                    <AppNavigator />
                </NavigationContainer>
            </CartProvider>
        </UserProvider>
    );
};

export default App;*/

/*import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import AuthStack from './navigation/AuthStack';
if (typeof setImmediate === 'undefined') {
    global.setImmediate = (fn) => setTimeout(fn, 0);
}
const App = () => {
    return (
        <NavigationContainer>
            <AuthStack />
        </NavigationContainer>
    );
};

export default App;*/
/*
import React, { useEffect, useState } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import AuthStack from './navigation/AuthStack';
import MainStack from './navigation/MainStack';
import { checkUserAuthentication } from './api/api'; // Import a function to check user authentication status

const App = () => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const getUserAuthenticationStatus = async () => {
            try {
                const user = await checkUserAuthentication(); // Call the function to check logged-in user
                if (user) {
                    setIsAuthenticated(true); // User is authenticated
                } else {
                    setIsAuthenticated(false); // User is not authenticated
                }
            } catch (error) {
                console.error('Error checking authentication status:', error);
                setIsAuthenticated(false); // Assume not authenticated on error
            } finally {
                setLoading(false); // Stop loading
            }
        };

        getUserAuthenticationStatus(); // Check authentication status on app load
    }, []);

    if (loading) {
        return <Text>Loading...</Text>; // Optionally, you can show a loading spinner
    }

    return (
        <NavigationContainer>
            {isAuthenticated ? <MainStack /> : <AuthStack />} /!* Render MainStack or AuthStack based on authentication state *!/
        </NavigationContainer>
    );
};

export default App;*/
/*

// App.js
import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import HomeScreen from './screens/HomeScreen';
import TrackingScreen from './screens/TrackingScreen';
import ChatScreen from './screens/ChatScreen';
import OrderHistoryScreen from './screens/OrderHistoryScreen';
import OrderDetailsScreen from './screens/OrderDetailsScreen';
import SettingsScreen from './screens/SettingsScreen';
import NotificationPreferencesScreen from './screens/NotificationPreferencesScreen';
import PaymentMethodsScreen from './screens/PaymentMethodsScreen';
import AddressBookScreen from './screens/AddressBookScreen';
import PrivacySettingsScreen from './screens/PrivacySettingsScreen';
import FAQScreen from './screens/FAQScreen';
import ContactSupportScreen from './screens/ContactSupportScreen';

const Stack = createStackNavigator();

const App = () => {
    return (
        <NavigationContainer>
            <Stack.Navigator initialRouteName="Home">
                <Stack.Screen name="Home" component={HomeScreen} />
                <Stack.Screen name="Tracking" component={TrackingScreen} />
                <Stack.Screen name="Chat" component={ChatScreen} />
                <Stack.Screen name="OrderHistory" component={OrderHistoryScreen} />
                <Stack.Screen name="OrderDetails" component={OrderDetailsScreen} />
                <Stack.Screen name="Settings" component={SettingsScreen} />
                <Stack.Screen name="NotificationPreferences" component={NotificationPreferencesScreen} />
                <Stack.Screen name="PaymentMethods" component={PaymentMethodsScreen} />
                <Stack.Screen name="AddressBook" component={AddressBookScreen} />
                <Stack.Screen name="PrivacySettings" component={PrivacySettingsScreen} />
                <Stack.Screen name="FAQ" component={FAQScreen} />
                <Stack.Screen name="ContactSupport" component={ContactSupportScreen} />
            </Stack.Navigator>
        </NavigationContainer>
    );
};

export default App;

import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { Provider as PaperProvider } from 'react-native-paper';
import HomeScreen from './screens/HomeScreen';
import RestaurantDetailsScreen from './screens/RestaurantDetailsScreen';
import MenuManagementScreen from './screens/MenuManagementScreen';
import OrdersManagementScreen from './screens/OrdersManagementScreen';
import SalesReportsScreen from './screens/SalesReportsScreen';
import UserActivityReportsScreen from './screens/UserActivityReportsScreen';
import firebase from '@react-native-firebase/app';

const Stack = createStackNavigator();

const App = () => {
    return (
        <PaperProvider>
            <NavigationContainer>
                <Stack.Navigator initialRouteName="Home">
                    <Stack.Screen name="Home" component={HomeScreen} />
                    <Stack.Screen name="RestaurantDetails" component={RestaurantDetailsScreen} />
                    <Stack.Screen name="MenuManagement" component={MenuManagementScreen} />
                    <Stack.Screen name="OrdersManagement" component={OrdersManagementScreen} />
                    <Stack.Screen name="SalesReports" component={SalesReportsScreen} />
                    <Stack.Screen name="UserActivityReports" component={UserActivityReportsScreen} />
                </Stack.Navigator>
            </NavigationContainer>
        </PaperProvider>
    );
};

export default App;
*/

/*import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import HomeScreen from './screens/HomeScreen';
import TrackingScreen from './screens/TrackingScreen';
import ChatScreen from './screens/ChatScreen';
import OrderHistoryScreen from './screens/OrderHistoryScreen';
import OrderDetailsScreen from './screens/OrderDetailsScreen';
import SettingsScreen from './screens/SettingsScreen';
import NotificationPreferencesScreen from './screens/NotificationPreferencesScreen';
import PaymentMethodsScreen from './screens/PaymentMethodsScreen';
import AddressBookScreen from './screens/AddressBookScreen';
import PrivacySettingsScreen from './screens/PrivacySettingsScreen';
import FAQScreen from './screens/FAQScreen';
import ContactSupportScreen from './screens/ContactSupportScreen';
import firebase from '@react-native-firebase/app';
import messaging from '@react-native-firebase/messaging';
import PushNotification from 'react-native-push-notification';

const Stack = createStackNavigator();

const App = () => {
    React.useEffect(() => {
        const unsubscribe = messaging().onMessage(async remoteMessage => {
            PushNotification.localNotification({
                title: remoteMessage.notification.title,
                message: remoteMessage.notification.body,
            });
        });

        return unsubscribe;
    }, []);

    return (
        <NavigationContainer>
            <Stack.Navigator initialRouteName="Home">
                <Stack.Screen name="Home" component={HomeScreen} />
                <Stack.Screen name="Tracking" component={TrackingScreen} />
                <Stack.Screen name="Chat" component={ChatScreen} />
                <Stack.Screen name="OrderHistory" component={OrderHistoryScreen} />
                <Stack.Screen name="OrderDetails" component={OrderDetailsScreen} />
                <Stack.Screen name="Settings" component={SettingsScreen} />
                <Stack.Screen name="NotificationPreferences" component={NotificationPreferencesScreen} />
                <Stack.Screen name="PaymentMethods" component={PaymentMethodsScreen} />
                <Stack.Screen name="AddressBook" component={AddressBookScreen} />
                <Stack.Screen name="PrivacySettings" component={PrivacySettingsScreen} />
                <Stack.Screen name="FAQ" component={FAQScreen} />
                <Stack.Screen name="ContactSupport" component={ContactSupportScreen} />
            </Stack.Navigator>
        </NavigationContainer>
    );
};

export default App;*/

/*// App.js
import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { Provider as PaperProvider } from 'react-native-paper';
import HomeScreen from './screens/HomeScreen';
import RestaurantDetailsScreen from './screens/RestaurantDetailsScreen';
import MenuManagementScreen from './screens/MenuManagementScreen';
import OrdersManagementScreen from './screens/OrdersManagementScreen';
import SalesReportsScreen from './screens/SalesReportsScreen';
import UserActivityReportsScreen from './screens/UserActivityReportsScreen';
import firebase from '@react-native-firebase/app';
/!*
import { requestUserPermission, notificationListener } from './firebase-messaging';
React.useEffect(() => {
    requestUserPermission();
    notificationListener();
}, []);*!/
const Stack = createStackNavigator();


const App = () => {
    return (
        <PaperProvider>
            <NavigationContainer>
                <Stack.Navigator initialRouteName="Home">
                    <Stack.Screen name="Home" component={HomeScreen} />
                    <Stack.Screen name="RestaurantDetails" component={RestaurantDetailsScreen} />
                    <Stack.Screen name="MenuManagement" component={MenuManagementScreen} />
                    <Stack.Screen name="OrdersManagement" component={OrdersManagementScreen} />
                    <Stack.Screen name="SalesReports" component={SalesReportsScreen} />
                    <Stack.Screen name="UserActivityReports" component={UserActivityReportsScreen} />
                </Stack.Navigator>
            </NavigationContainer>
        </PaperProvider>
    );
};

export default App;*/
/*
// App.js
import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import HomeScreen from './screens/HomeScreen';
import TrackingScreen from './screens/TrackingScreen';
import ChatScreen from './screens/ChatScreen';
import OrderHistoryScreen from './screens/OrderHistoryScreen';
import OrderDetailsScreen from './screens/OrderDetailsScreen';
import SettingsScreen from './screens/SettingsScreen';
import NotificationPreferencesScreen from './screens/NotificationPreferencesScreen';
import PaymentMethodsScreen from './screens/PaymentMethodsScreen';
import AddressBookScreen from './screens/AddressBookScreen';
import PrivacySettingsScreen from './screens/PrivacySettingsScreen';
import FAQScreen from './screens/FAQScreen';
import ContactSupportScreen from './screens/ContactSupportScreen';

const Stack = createStackNavigator();

const App = () => {
    return (
        <NavigationContainer>
            <Stack.Navigator initialRouteName="Home">
                <Stack.Screen name="Home" component={HomeScreen} />
                <Stack.Screen name="Tracking" component={TrackingScreen} />
                <Stack.Screen name="Chat" component={ChatScreen} />
                <Stack.Screen name="OrderHistory" component={OrderHistoryScreen} />
                <Stack.Screen name="OrderDetails" component={OrderDetailsScreen} />
                <Stack.Screen name="Settings" component={SettingsScreen} />
                <Stack.Screen name="NotificationPreferences" component={NotificationPreferencesScreen} />
                <Stack.Screen name="PaymentMethods" component={PaymentMethodsScreen} />
                <Stack.Screen name="AddressBook" component={AddressBookScreen} />
                <Stack.Screen name="PrivacySettings" component={PrivacySettingsScreen} />
                <Stack.Screen name="FAQ" component={FAQScreen} />
                <Stack.Screen name="ContactSupport" component={ContactSupportScreen} />
            </Stack.Navigator>
        </NavigationContainer>
    );
};

export default App;*/
/*


import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import HomeScreen from './screens/HomeScreen';
import TrackingScreen from './screens/TrackingScreen';
import ChatScreen from './screens/ChatScreen';
import OrderHistoryScreen from './screens/OrderHistoryScreen';
import OrderDetailsScreen from './screens/OrderDetailsScreen';
import SettingsScreen from './screens/SettingsScreen';
import NotificationPreferencesScreen from './screens/NotificationPreferencesScreen';
import PaymentMethodsScreen from './screens/PaymentMethodsScreen';
import AddressBookScreen from './screens/AddressBookScreen';
import PrivacySettingsScreen from './screens/PrivacySettingsScreen';
import FAQScreen from './screens/FAQScreen';
import ContactSupportScreen from './screens/ContactSupportScreen';
import firebase from '@react-native-firebase/app';
import messaging from '@react-native-firebase/messaging';
import PushNotification from 'react-native-push-notification';

const Stack = createStackNavigator();

const App = () => {
    /!*  React.useEffect(() => {
          const unsubscribe = messaging().onMessage(async remoteMessage => {
              PushNotification.localNotification({
                  title: remoteMessage.notification.title,
                  message: remoteMessage.notification.body,
              });
          });

          return unsubscribe;
      }, []);*!/

    return (
        <NavigationContainer>
            <Stack.Navigator initialRouteName="Home">
                <Stack.Screen name="Home" component={HomeScreen} />
                <Stack.Screen name="Tracking" component={TrackingScreen} />
                <Stack.Screen name="Chat" component={ChatScreen} />
                <Stack.Screen name="OrderHistory" component={OrderHistoryScreen} />
                <Stack.Screen name="OrderDetails" component={OrderDetailsScreen} />
                <Stack.Screen name="Settings" component={SettingsScreen} />
                <Stack.Screen name="NotificationPreferences" component={NotificationPreferencesScreen} />
                <Stack.Screen name="PaymentMethods" component={PaymentMethodsScreen} />
                <Stack.Screen name="AddressBook" component={AddressBookScreen} />
                <Stack.Screen name="PrivacySettings" component={PrivacySettingsScreen} />
                <Stack.Screen name="FAQ" component={FAQScreen} />
                <Stack.Screen name="ContactSupport" component={ContactSupportScreen} />
            </Stack.Navigator>
        </NavigationContainer>
    );
};

export default App;

*/


/*
import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { View, Text, Button } from 'react-native';
import { auth } from './firebase'; // Import the firebase authentication

const Stack = createNativeStackNavigator();
if (typeof setImmediate === 'undefined') {
    global.setImmediate = (fn) => setTimeout(fn, 0);
}
const SignInScreen = ({ navigation }) => {
    const handleSignIn = () => {
        // Replace with your actual sign-in logic
        auth().signInWithEmailAndPassword('hakim@example.com', 'Hakim@14')
            .then(() => navigation.navigate('Profile'))
            .catch(error => alert(error.message));
    };

    return (
        <View>
            <Text>Sign In</Text>
            <Button title="Sign In" onPress={handleSignIn} />
            <Button title="Go to Sign Up" onPress={() => navigation.navigate('SignUp')} />
        </View>
    );
};

const SignUpScreen = ({ navigation }) => {
    const handleSignUp = () => {
        // Replace with your actual sign-up logic
        auth() .createUserWithEmailAndPassword('hakim@example.com', 'Hakim@14')
            .then(() => navigation.navigate('Profile'))
            .catch(error => alert(error.message));
    };

    return (
        <View>
            <Text>Sign Up</Text>
            <Button title="Sign Up" onPress={handleSignUp} />
            <Button title="Go to Sign In" onPress={() => navigation.navigate('SignIn')} />
        </View>
    );
};

const ProfileScreen = () => {
    return (
        <View>
            <Text>Welcome to your Profile!</Text>
        </View>
    );
};

const App = () => {
    return (
        <NavigationContainer>
            <Stack.Navigator initialRouteName="SignIn">
                <Stack.Screen name="SignIn" component={SignInScreen} />
                <Stack.Screen name="SignUp" component={SignUpScreen} />
                <Stack.Screen name="Profile" component={ProfileScreen} />
            </Stack.Navigator>
        </NavigationContainer>
    );
};

export default App;

*/

/*





/*

// App.js
import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import HomeScreen from './screens/HomeScreen';
import TrackingScreen from './screens/TrackingScreen';
import ChatScreen from './screens/ChatScreen';
import OrderHistoryScreen from './screens/OrderHistoryScreen';
import OrderDetailsScreen from './screens/OrderDetailsScreen';
import firebase from '@react-native-firebase/app';
import messaging from '@react-native-firebase/messaging';
import AsyncStorage from '@react-native-async-storage/async-storage';
import PushNotification from 'react-native-push-notification';

const Stack = createStackNavigator();

const App = () => {
    React.useEffect(() => {
        const unsubscribe = messaging().onMessage(async remoteMessage => {
            PushNotification.localNotification({
                title: remoteMessage.notification.title,
                message: remoteMessage.notification.body,
            });
        });

        return unsubscribe;
    }, []);

    return (
        <NavigationContainer>
            <Stack.Navigator initialRouteName="Home">
                <Stack.Screen name="Home" component={HomeScreen} />
                <Stack.Screen name="Tracking" component={TrackingScreen} />
                <Stack.Screen name="Chat" component={ChatScreen} />
                <Stack.Screen name="OrderHistory" component={OrderHistoryScreen} />
                <Stack.Screen name="OrderDetails" component={OrderDetailsScreen} />
            </Stack.Navigator>
        </NavigationContainer>
    );
};

export default App;
*/

/*

// App.js
import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import HomeScreen from './screens/HomeScreen';
import TrackingScreen from './screens/TrackingScreen';
import ChatScreen from './screens/ChatScreen';

const Stack = createStackNavigator();

const App = () => {
    return (
        <NavigationContainer>
            <Stack.Navigator initialRouteName="Home">
                <Stack.Screen name="Home" component={HomeScreen} />
                <Stack.Screen name="Tracking" component={TrackingScreen} />
                <Stack.Screen name="Chat" component={ChatScreen} />
            </Stack.Navigator>
        </NavigationContainer>
    );
};

export default App;
*/



/*

import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';

export default function App() {
  return (
    <View style={styles.container}>
      <Text>Open up App.js to start working on your app!</Text>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
*/
