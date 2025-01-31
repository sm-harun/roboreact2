// CareersPage.js
import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import Header from '../components/Header';
import Footer from '../components/Footer';
import MenuForAboutUs from '../components/MenuForAboutUs'; // Adjust path

const CareersPage = () => {
    return (
        <View style={styles.container}>
            {/*<Header />*/}
            <MenuForAboutUs /> {/* Render the About Us menu */}
            {/*<MenuForAboutUs setCurrentPage={setCurrentPage} /> /!* Pass down the function *!/*/}
            <View style={styles.content}>
                <Text style={styles.title}>Careers</Text>
                <Text style={styles.bodyText}>
                    Join our passionate team at the Robotics Education Platform!
                    We are always looking for innovative minds dedicated to shaping the future of robotics education.
                    Check below for our available positions:
                </Text>
                <Text style={styles.bodyText}>
                    - Robotics Instructor
                    - Software Developer
                    - Curriculum Designer
                </Text>
            </View>
            {/*<Footer />*/}
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
    bodyText: {
        fontSize: 16,
        textAlign: 'center',
        padding: 20,
    },
});

export default CareersPage;
