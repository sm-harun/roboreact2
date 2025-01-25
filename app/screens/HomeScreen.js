// HomeScreen.js
import React, { useRef, useEffect } from 'react';
import { View, Text, Button, StyleSheet, Animated, Image, FlatList, TouchableWithoutFeedback, Easing } from 'react-native';
import * as Speech from 'expo-speech';

const robotImages = [
    { id: '1', uri: 'https://cdn.pixabay.com/photo/2017/08/30/07/44/robot-2696578_1280.png', name: 'Robot 1', description: 'This is an educational robot used in many classrooms.' },
    { id: '2', uri: 'https://cdn.pixabay.com/photo/2017/01/06/16/17/robot-1950703_1280.png', name: 'Robot 2', description: 'This robot helps teach programming to students.' },
    { id: '3', uri: 'https://cdn.pixabay.com/photo/2018/01/15/07/03/robot-3080807_1280.jpg', name: 'Robot 3', description: 'A humanoid robot designed for friendly interaction.' },
];

const HomeScreen = () => {
    const translate = useRef(new Animated.Value(0)).current; // Animation value for moving the image

    const handleRobotPress = (robot) => {
        Speech.speak(`${robot.name}: ${robot.description}`);

        // Animate the robot image on touch
        Animated.sequence([
            Animated.timing(translate, {
                toValue: -30, // Move up
                duration: 300,
                easing: Easing.ease,
                useNativeDriver: true,
            }),
            Animated.timing(translate, {
                toValue: 0, // Move back to original position
                duration: 300,
                easing: Easing.ease,
                useNativeDriver: true,
            }),
        ]).start();
    };

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Welcome to Robotics Education</Text>
            <Text style={styles.subtitle}>Learn with our Robots!</Text>

            {/!* Animated Robots Section *!/}
            <FlatList
                data={robotImages}
                horizontal
                keyExtractor={(item) => item.id}
                renderItem={({ item }) => (
                    <TouchableWithoutFeedback onPress={() => handleRobotPress(item)}>
                        <Animated.View style={{ transform: [{ translateY: translate }] }}>
                            <Image
                                source={{ uri: item.uri }}
                                style={styles.robotImage}
                            />
                        </Animated.View>
                    </TouchableWithoutFeedback>
                )}
            />

            {/!* Navigation Buttons *!/}
            <Text style={styles.title}>Welcome to the Robotic Educational and Shopping Center!</Text>
          {/*  <Button title="View Courses" onPress={() => navigation.navigate('Courses')} />
            <Button title="View Competitions" onPress={() => navigation.navigate('Competitions')} />
            <Button title="View Resources" onPress={() => navigation.navigate('Resources')} />
            <Button title="Events" onPress={() => navigation.navigate('Events')} />
            <Button title="Shop Now" onPress={() => navigation.navigate('Shop')} />
            <Button title="Login" onPress={() => navigation.navigate('Login')} />
            <Button title="Sign Up" onPress={() => navigation.navigate('Signup')} />
*/}
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 16,
    },
    title: {
        fontSize: 24,
        fontWeight: 'bold',
        marginBottom: 20,
    },
    subtitle: {
        fontSize: 20,
        fontWeight: '600',
        marginVertical: 10,
    },
    robotImage: {
        width: 100,
        height: 100,
        marginHorizontal: 10,
    },
});

export default HomeScreen;

/*
import React from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';
import { useNavigation } from '@react-navigation/native';

const HomeScreen = () => {
    const navigation = useNavigation();

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Welcome to the Robotic Shop!</Text>
            <Button title="Shop Now" onPress={() => navigation.navigate('Shop')} />
            <Button title="View Competitions" onPress={() => navigation.navigate('Competitions')} />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
    },
    title: {
        fontSize: 24,
        fontWeight: 'bold',
    },
});

export default HomeScreen;
 */
/*import React from 'react';
import { View, Text, Button, StyleSheet, FlatList } from 'react-native';
import { getTimelineEvents } from '../utils/api'; // Adjust path to your api.js

const HomeScreen = () => {
    const [events, setEvents] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const fetchTimelineData = async () => {
            setLoading(true);
            try {
                const response = await getTimelineEvents(); // Fetch events
                setEvents(response.data); // Set events state
            } catch (error) {
                console.error('Error fetching timeline data:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchTimelineData();
    }, []);

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Welcome to the Robotics Education Platform!</Text>

            <Text style={styles.subtitle}>History of Ewenet Communication</Text>
            {loading ? (
                <Text>Loading...</Text>
            ) : (
                <FlatList
                    data={events}
                    renderItem={({ item }) => (
                        <View style={styles.timelineItem}>
                            <Text style={styles.date}>{item.eventDate}</Text>
                            <Text style={styles.itemTitle}>{item.title}</Text>
                            <Text>{item.subtitle}</Text>
                            {item.description && <Text style={styles.description}>{item.description}</Text>}
                        </View>
                    )}
                    keyExtractor={(item) => item.id.toString()}
                />
            )}

            <Button title="Shop Now" onPress={() => {/!* Logic to navigate to shop *!/}} />
            <Button title="Login" onPress={() => {/!* Logic to navigate to login *!/}} />
            <Button title="Sign Up" onPress={() => {/!* Logic to navigate to sign up *!/}} />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 16,
    },
    title: {
        fontSize: 24,
        fontWeight: 'bold',
        marginBottom: 20,
    },
    subtitle: {
        fontSize: 20,
        fontWeight: '600',
        marginVertical: 10,
    },
    timelineList: {
        marginVertical: 20,
    },
    timelineItem: {
        padding: 16,
        marginVertical: 8,
        backgroundColor: '#f2f2f2',
        borderRadius: 10,
    },
    date: {
        fontWeight: 'bold',
    },
    itemTitle: {
        fontSize: 18,
        fontWeight: 'bold',
    },
    description: {
        fontSize: 14,
        color: '#666',
    },
});

export default HomeScreen;*/


/*/!*
---------------------------------------------------------------------------
import { getTimelineEvents } from '../utils/api'; // Adjust path to your api.js

const HomeScreen = () => {
    const [events, setEvents] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const fetchTimelineData = async () => {
            setLoading(true);
            try {
                const response = await getTimelineEvents(); // Fetch events
                setEvents(response.data); // Set events state
            } catch (error) {
                console.error('Error fetching timeline data:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchTimelineData();
    }, []);

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Welcome to the Robotics Education Platform!</Text>

            <Text style={styles.subtitle}>History of Ewenet Communication</Text>
            {loading ? (
                <Text>Loading...</Text>
            ) : (
                <FlatList
                    data={events}
                    renderItem={({ item }) => (
                        <View style={styles.timelineItem}>
                            <Text style={styles.date}>{item.eventDate}</Text>
                            <Text style={styles.itemTitle}>{item.title}</Text>
                            <Text>{item.subtitle}</Text>
                            {item.description && <Text style={styles.description}>{item.description}</Text>}
                        </View>
                    )}
                    keyExtractor={(item) => item.id.toString()}
                />
            )}

----------------------------------------------------------------------------
import React, { useEffect, useRef, useState } from 'react';
import { View, Text, Button, StyleSheet, Animated, Image, FlatList, Alert, TouchableOpacity } from 'react-native';
import * as Speech from 'expo-speech'; // For voice commands or descriptions

const robotImages = [
    { id: '1', uri: 'https://example.com/robot1.png', name: 'Robot 1', description: 'This is Robot 1.' }, // Replace with actual image URLs
    { id: '2', uri: 'https://example.com/robot2.png', name: 'Robot 2', description: 'This is Robot 2.' },
    { id: '3', uri: 'https://example.com/robot3.png', name: 'Robot 3', description: 'This is Robot 3.' },
];

const HomeScreen = () => {
    const translateX = useRef(new Animated.Value(0)).current; // Initialize animated value
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const moveRobots = () => {
            Animated.loop(
                Animated.sequence([
                    Animated.timing(translateX, {
                        toValue: 200, // Move 200 units to the right
                        duration: 2000,
                        useNativeDriver: true,
                    }),
                    Animated.timing(translateX, {
                        toValue: 0, // Move back to the original position
                        duration: 2000,
                        useNativeDriver: true,
                    }),
                ])
            ).start();
        };

        moveRobots();
    }, [translateX]);

    const handleRobotPress = (robot) => {
        Speech.speak(`${robot.name}: ${robot.description}`); // Read out the robot's name and description
        Alert.alert(robot.name, robot.description); // Show an alert with details
    };

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Welcome to the Robotics Education Platform!</Text>
            <Text style={styles.subtitle}>Learning Robotics is Fun!</Text>

            {/!* Animated Robots Section *!/}
<FlatList
    data={robotImages}
    horizontal
    keyExtractor={(item) => item.id}
    renderItem={({ item }) => (
        <Animated.View style={{ transform: [{ translateX }] }}>
            <TouchableOpacity onPress={() => handleRobotPress(item)}>
                <Image
                    source={{ uri: item.uri }} // Replace with actual image link
                    style={styles.robotImage}
                />
            </TouchableOpacity>
        </Animated.View>
    )}
/>

{/!* Navigation Buttons *!/}
<Button title="View Courses" onPress={() => {/!* Logic to navigate to Courses *!/}} />
<Button title="View Resources" onPress={() => {/!* Logic to navigate to Resources *!/}} />
</View>
);
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 16,
    },
    title: {
        fontSize: 24,
        fontWeight: 'bold',
        marginBottom: 20,
    },
    subtitle: {
        fontSize: 20,
        fontWeight: '600',
        marginVertical: 10,
    },
    robotImage: {
        width: 100,
        height: 100,
        marginHorizontal: 10,
    },
});

export default HomeScreen;

import React, { useEffect, useRef, useState } from 'react';
import { View, Text, Button, StyleSheet, Animated, Image, FlatList, Alert, TouchableOpacity } from 'react-native';
import * as Speech from 'expo-speech'; // For voice commands or descriptions

// Sample robotic images for children with real URLs
const robotImages = [
    { id: '1', uri: 'https://cdn.pixabay.com/photo/2017/08/30/07/44/robot-2696578_1280.png', name: 'Robot 1', description: 'This is an educational robot used in many classrooms.' },
    { id: '2', uri: 'https://cdn.pixabay.com/photo/2017/01/06/16/17/robot-1950703_1280.png', name: 'Robot 2', description: 'This robot helps teach programming to students.' },
    { id: '3', uri: 'https://cdn.pixabay.com/photo/2018/01/15/07/03/robot-3080807_1280.jpg', name: 'Robot 3', description: 'A humanoid robot designed for friendly interaction.' },
];

const HomeScreen = () => {
    const translateX = useRef(new Animated.Value(0)).current; // Initialize animated value

    useEffect(() => {
        const moveRobots = () => {
            Animated.loop(
                Animated.sequence([
                    Animated.timing(translateX, {
                        toValue: 200, // Move 200 units to the right
                        duration: 2000,
                        useNativeDriver: true,
                    }),
                    Animated.timing(translateX, {
                        toValue: 0, // Move back to the original position
                        duration: 2000,
                        useNativeDriver: true,
                    }),
                ])
            ).start();
        };

        moveRobots(); // Start the animation
    }, [translateX]);

    const handleRobotPress = (robot) => {
        Speech.speak(`${robot.name}: ${robot.description}`); // Read out the robot's name and description
        Alert.alert(robot.name, robot.description); // Show an alert with details
    };

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Welcome to the Robotics Education Platform!</Text>
            <Text style={styles.subtitle}>Learning Robotics is Fun!</Text>

            {/!* Animated Robots Section *!/}
<FlatList
    data={robotImages}
    horizontal
    keyExtractor={(item) => item.id}
    renderItem={({ item }) => (
        <Animated.View style={{ transform: [{ translateX }] }}>
            <TouchableOpacity onPress={() => handleRobotPress(item)}>
                <Image
                    source={{ uri: item.uri }} // Using real image links
                    style={styles.robotImage}
                />
            </TouchableOpacity>
        </Animated.View>
    )}
/>

{/!* Navigation Buttons *!/}
<Button title="View Courses" onPress={() => {/!* Logic to navigate to Courses *!/}} />
<Button title="View Resources" onPress={() => {/!* Logic to navigate to Resources *!/}} />
<Button title="About Ewenet Communication" onPress={() => {/!* Logic to navigate to About Screen *!/}} />
</View>
);
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 16,
    },
    title: {
        fontSize: 24,
        fontWeight: 'bold',
        marginBottom: 20,
    },
    subtitle: {
        fontSize: 20,
        fontWeight: '600',
        marginVertical: 10,
    },
    robotImage: {
        width: 100,
        height: 100,
        marginHorizontal: 10,
    },
});

export default HomeScreen;*/

/*import React, { useState } from 'react';
import {View, Text, StyleSheet, FlatList} from 'react-native';
import Header from '../components/Header'; // Ensure paths are correct
import Footer from '../components/Footer';
import Menu from '../components/Menu'; // Main menu for Home
import CareersPage from './CareersPage'; // Import Careers page
import OurTeamPage from './OurTeamPage'; // Import Our Team page
import OurClientsPage from './OurClientsPage'; // Import Our Clients page
import ServicesPage from './ServicesPage'; // Import Services page
import WebsiteDevelopmentPage from './WebsiteDevelopmentPage'; // Import Website Development page
import AndroidDevelopmentPage from './AndroidDevelopmentPage'; // Import Android Development page
import RoboticsTrainingPage from './RoboticsTrainingPage'; // Import Robotics Training page
import RoboticsMaintenancePage from './RoboticsMaintenancePage';
import AboutUsPage from "./AboutUsPage";
import HomePage from "./HomePage";
import SearchComponent from "../components/SearchComponent";
import ProductScreen from "./ProductScreen";
import ProductDetailsScreen from "./ProductDetailsScreen";
import AddProduct from "./AddProduct";
import ProductList from "./ProductList";
import ProductDetail from "./ProductDetail"; // Import Robotics Maintenance page
// Sample data for demonstration
const dataList = [
    { id: '1', title: 'Robotics Training' },
    { id: '2', title: 'Website Development' },
    { id: '3', title: 'Android Development' },
    { id: '4', title: 'Robotics Maintenance' },
    { id: '5', title: 'About Us' },
    // Add more items as needed
];
const HomeScreen = () => {
    const [currentPage, setCurrentPage] = useState('home'); // State to manage current page
    const [filteredList, setFilteredList] = useState(dataList); // State for filtered items

    const renderContent = () => {
        switch (currentPage) {
            case 'home':
                // return <Text style={styles.title}>Welcome to the Educational Robotics App!</Text>;
              //  return <HomePage />; // Pass down the setCurrentPage prop

                 return (
                <View>
                    <Text style={styles.title}>Welcome to the Educational Robotics App!</Text>
                    <FlatList
                        data={filteredList}
                        keyExtractor={item => item.id}
                        renderItem={({ item }) => (
                            <Text style={styles.listItem}>{item.title}</Text>
                        )}
                    />
                </View>
            );


            case 'aboutUs':
                // return <AboutUsPage setCurrentPage={setCurrentPage} />; // Pass down the setCurrentPage prop
                return <AboutUsPage />; // Pass down the setCurrentPage prop

            case 'careers':
                return <CareersPage />;
            case 'ourTeam':
                return <OurTeamPage />;
            case 'ourClients':
                return <OurClientsPage />;
            case 'services':
                return <ServicesPage />;
            case 'websiteDevelopment':
                return <WebsiteDevelopmentPage />;
            case 'androidDevelopment':
                return <AndroidDevelopmentPage />;
            case 'roboticsTraining':
                return <RoboticsTrainingPage />;
            case 'roboticsMaintenance':
                return <RoboticsMaintenancePage />;
            case 'Products':
                return <ProductScreen />;
            case 'ProductDetails':
                return <ProductDetailsScreen />;
            case 'AddProducts':
                return <AddProduct />;
            case 'ProductLists':
                return <ProductList />;
            case 'ProductDetails1':
                return <ProductDetail />;
            default:
                return <Text style={styles.title}>Welcome to the Educational Robotics App!</Text>;
        }
    };
    const handleSearch = (query) => {
        if (query) {
            const filteredData = dataList.filter(item =>
                item.title.toLowerCase().includes(query.toLowerCase())
            );
            setFilteredList(filteredData); // Update state with the filtered result
        } else {
            setFilteredList(dataList); // Reset list when the query is empty
        }
    };
    return (
        <View style={styles.container}>
            <Header />
            <Menu setCurrentPage={setCurrentPage} /> {/!* Render the Menu here for navigation *!/}
            <SearchComponent onSearch={handleSearch} /> {/!* Implement search functionality *!/}

            <View style={styles.content}>
                {renderContent()} {/!* Render the current page content *!/}
            </View>
            <Footer />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
    title: {
        fontSize: 24,
        textAlign: 'center',
        margin: 20,
    },
    content: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
    },
});

export default HomeScreen;*/
/*

// HomeScreen.js
import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import Header from '../components/Header'; // Ensure paths are correct
import Footer from '../components/Footer';
import Menu from '../components/Menu'; // Import your Menu component

const HomeScreen = () => {
    return (
        <View style={styles.container}>
            <Header />
            <Menu /> {/!* Render the Menu here for navigation *!/}
            <View style={styles.content}>
                <Text style={styles.title}>Welcome to the Educational Robotics App!</Text>
            </View>
            <Footer />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
    title: {
        fontSize: 24,
        textAlign: 'center',
        margin: 20,
    },
    content: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
    },
});

export default HomeScreen;
*/

/*// HomeScreen.js
import React, { useState } from 'react';
import { View, Text, StyleSheet } from 'react-native';
import Header from '../components/Header'; // Ensure paths are correct
import Footer from '../components/Footer';
import Menu from '../components/Menu'; // Import your Menu component
import AboutUsPage from './AboutUsPage'; // Import the About Us component

const HomeScreen = () => {
    const [currentPage, setCurrentPage] = useState('home'); // State to manage current page

    const renderContent = () => {
        switch (currentPage) {
            case 'home':
                return <Text style={styles.title}>Welcome to the Educational Robotics App!</Text>;
            case 'aboutUs':
                return <Text style={styles.title}>About Us Content Goes Here!</Text>; // Replace with About Us content
            case 'careers':
                return <Text style={styles.title}>Career Content Goes Here!</Text>; // Replace with Career content
            case 'ourTeam':
                return <Text style={styles.title}>Our Team Content Goes Here!</Text>; // Replace with team content
            case 'ourClients':
                return <Text style={styles.title}>Our Clients Content Goes Here!</Text>; // Replace with clients content
            case 'roboticsTraining':
                return <Text style={styles.title}>Robotics Training Content Goes Here!</Text>; // Replace with content
            case 'softwareDevelopment':
                return <Text style={styles.title}>Software Development Content Goes Here!</Text>; // Replace with content
            case 'websiteDevelopment':
                return <Text style={styles.title}>Website Development Content Goes Here!</Text>; // Replace with content
            case 'roboticsMaintenance':
                return <Text style={styles.title}>Robotics Maintenance Content Goes Here!</Text>; // Replace with content
            case 'portfolio':
                return <Text style={styles.title}>Portfolio Content Goes Here!</Text>; // Replace with portfolio content
            case 'news':
                return <Text style={styles.title}>News Content Goes Here!</Text>; // Replace with news content
            case 'contactUs':
                return <Text style={styles.title}>Contact Us Content Goes Here!</Text>; // Replace with contact us content
            default:
                return <Text style={styles.title}>Welcome to the Educational Robotics App!</Text>;
        }
    };

    return (
        <View style={styles.container}>
            <Header />
            <Menu setCurrentPage={setCurrentPage} /> {/!* Pass down the function to change the current page *!/}
            <View style={styles.content}>
                {renderContent()} {/!* Render the current page content *!/}
            </View>
            <Footer />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
    title: {
        fontSize: 24,
        textAlign: 'center',
        margin: 20,
    },
    content: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
    },
});

export default HomeScreen;*/

/*// HomeScreen.js
import React from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';
import Header from "./Header";
import Menu from "./Menu";
import Footer from "./Footer";

const HomeScreen = ({ navigation }) => {
    return (
        <View style={styles.container}>
            <Header />
            <Menu navigation={navigation} />

            {/!*<Menu />  Render the Menu here *!/}
            {/!*<View style={styles.content}>*!/}
            {/!*    <Text style={styles.title}>Welcome to the Educational Robotics App!</Text>*!/}
            {/!*</View>*!/}
            <Footer />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        padding: 20,
    },
    title: {
        fontSize: 24,
        fontWeight: 'bold',
        marginBottom: 20,
    },
});

export default HomeScreen;*/

/*import React from 'react';
import { View, StyleSheet } from 'react-native';
import Header from '../components/Header';
import Footer from '../components/Footer'; // Assuming you have a separate Footer component
import Menu from '../components/Menu'; // Assuming you have a Menu component

const HomeScreen = () => (
    <View style={styles.container}>
        <Header />
        <Menu />
        {/!* Other content can go here *!/}
        <Footer /> {/!* Include the footer at the bottom *!/}
    </View>
);

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#f9f9f9',
    },
});

export default HomeScreen;*/
