// Menu.js
import React from 'react';
import {View, Text, StyleSheet, TouchableOpacity, TextInput} from 'react-native';
import MenuForAboutUs from './MenuForAboutUs'; // Assuming you've created this component
import MenuForServices from './MenuForServices';
import AddProduct from "../screens/AddProduct"; // Assuming you've created this component

const Menu = ({ setCurrentPage }) => {
    return (
        <View style={styles.container}>
            <View style={styles.row}>
                <TouchableOpacity onPress={() => setCurrentPage('home')}>
                    <Text style={styles.menuItem}>Home</Text>
                </TouchableOpacity>
                <MenuForAboutUs setCurrentPage={setCurrentPage} />
                <MenuForServices setCurrentPage={setCurrentPage} />

                <TouchableOpacity onPress={() => setCurrentPage('Products')}>
                    <Text style={styles.menuItem}>Product</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => setCurrentPage('AddProducts')}>
                    <Text style={styles.menuItem}>AddProduct</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => setCurrentPage('ProductLists')}>
                    <Text style={styles.menuItem}>ProductList</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => setCurrentPage('ProductDetails')}>
                    <Text style={styles.menuItem}>ProductDetail</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => setCurrentPage('ProductDetails1')}>
                    <Text style={styles.menuItem}>ProductDetail1</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => setCurrentPage('contactUs')}>
                    <Text style={styles.menuItem}>Contact Us</Text>
                </TouchableOpacity>
               <TextInput
                    style={styles.searchInput}
                    placeholder="Search..."
                    placeholderTextColor="gray"
                />

            </View>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        paddingVertical: 10,
        backgroundColor: '#f8f8f8',
    },
    row: {
        flexDirection: 'row',
        justifyContent: 'flex-start',
        alignItems: 'center',
    },
    menuItem: {
        fontSize: 16,
        paddingVertical: 10,
        marginHorizontal: 10,
        color: '#000',
    },
});

export default Menu;

/*// Menu.js
import React, { useState } from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';
import MenuForAboutUs from './MenuForAboutUs'; // Import the About Us menu
import MenuForServices from './MenuForServices'; // Import the Services menu

const Menu = ({ setCurrentPage }) => {
    const [aboutUsExpanded, setAboutUsExpanded] = useState(false);
    const [servicesExpanded, setServicesExpanded] = useState(false);

    const toggleAboutUs = () => setAboutUsExpanded(prev => !prev);
    const toggleServices = () => setServicesExpanded(prev => !prev);

    return (
        <View style={styles.container}>
            <View style={styles.row}>
                <TouchableOpacity onPress={() => setCurrentPage('home')}>
                    <Text style={styles.menuItem}>Home</Text>
                </TouchableOpacity>

                <TouchableOpacity onPress={toggleAboutUs}>
                    <Text style={styles.menuItem}>About Us {aboutUsExpanded ? '-' : '+'}</Text>
                </TouchableOpacity>
                {aboutUsExpanded && (
                    <MenuForAboutUs setCurrentPage={setCurrentPage} />
                )}

                <TouchableOpacity onPress={toggleServices}>
                    <Text style={styles.menuItem}>Services {servicesExpanded ? '-' : '+'}</Text>
                </TouchableOpacity>
                {servicesExpanded && (
                    <MenuForServices setCurrentPage={setCurrentPage} />
                )}

                <TouchableOpacity onPress={() => setCurrentPage('portfolio')}>
                    <Text style={styles.menuItem}>Portfolio</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => setCurrentPage('news')}>
                    <Text style={styles.menuItem}>News</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => setCurrentPage('contactUs')}>
                    <Text style={styles.menuItem}>Contact Us</Text>
                </TouchableOpacity>
            </View>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        paddingVertical: 10,
        paddingHorizontal: 15,
        backgroundColor: '#f8f8f8',
    },
    row: {
        flexDirection: 'row',
        justifyContent: 'flex-start',
        alignItems: 'center',
    },
    menuItem: {
        fontSize: 16,
        paddingVertical: 10,
        marginHorizontal: 10,
        color: '#000',
    },
});

export default Menu;*/

/*// Menu.js
import React, { useState } from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';

const Menu = ({ navigation }) => {
    const [aboutUsExpanded, setAboutUsExpanded] = useState(false);
    const [servicesExpanded, setServicesExpanded] = useState(false);

    const toggleAboutUs = () => setAboutUsExpanded(prev => !prev);
    const toggleServices = () => setServicesExpanded(prev => !prev);

    return (
        <View style={styles.container}>
            <View style={styles.row}>
                <TouchableOpacity onPress={() => navigation.navigate('Home')}>
                    <Text style={styles.menuItem}>Home</Text>
                </TouchableOpacity>

                <TouchableOpacity onPress={toggleAboutUs}>
                    <Text style={styles.menuItem}>About Us {aboutUsExpanded ? '-' : '+'}</Text>
                </TouchableOpacity>
                {aboutUsExpanded && (
                    <View style={styles.submenu}>
                        <TouchableOpacity onPress={() => navigation.navigate('AboutUsPage')}>
                            <Text style={styles.submenuItem}>About Us</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => navigation.navigate('CareersPage')}>
                            <Text style={styles.submenuItem}>Careers</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => navigation.navigate('OurTeamPage')}>
                            <Text style={styles.submenuItem}>Our Team</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => navigation.navigate('OurClientsPage')}>
                            <Text style={styles.submenuItem}>Our Clients</Text>
                        </TouchableOpacity>
                    </View>
                )}

                <TouchableOpacity onPress={toggleServices}>
                    <Text style={styles.menuItem}>Services {servicesExpanded ? '-' : '+'}</Text>
                </TouchableOpacity>
                {servicesExpanded && (
                    <View style={styles.submenu}>
                        <TouchableOpacity onPress={() => navigation.navigate('RoboticsTraining')}>
                            <Text style={styles.submenuItem}>Robotics Training</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => navigation.navigate('SoftwareDevelopment')}>
                            <Text style={styles.submenuItem}>Software Development</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => navigation.navigate('WebsiteDevelopment')}>
                            <Text style={styles.submenuItem}>Website Development</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => navigation.navigate('RoboticsMaintenance')}>
                            <Text style={styles.submenuItem}>Robotics Maintenance</Text>
                        </TouchableOpacity>
                    </View>
                )}

                <TouchableOpacity onPress={() => navigation.navigate('Portfolio')}>
                    <Text style={styles.menuItem}>Portfolio</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => navigation.navigate('News')}>
                    <Text style={styles.menuItem}>News</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => navigation.navigate('ContactUs')}>
                    <Text style={styles.menuItem}>Contact Us</Text>
                </TouchableOpacity>
            </View>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        paddingVertical: 10,
        paddingHorizontal: 15,
        backgroundColor: '#f8f8f8',
    },
    row: {
        flexDirection: 'row',
        justifyContent: 'flex-start',
        alignItems: 'center',
    },
    menuItem: {
        fontSize: 16,
        paddingVertical: 10,
        marginHorizontal: 10,
        color: '#000',
    },
    submenu: {
        paddingLeft: 15,
    },
    submenuItem: {
        fontSize: 14,
        paddingVertical: 5,
        color: '#555',
    },
});

export default Menu;*/
/*// Menu.js
import React, { useState } from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';

const Menu = ({ setCurrentPage }) => {
    const [aboutUsExpanded, setAboutUsExpanded] = useState(false);
    const [servicesExpanded, setServicesExpanded] = useState(false);

    const toggleAboutUs = () => setAboutUsExpanded(prev => !prev);
    const toggleServices = () => setServicesExpanded(prev => !prev);

    return (
        <View style={styles.container}>
            <View style={styles.row}>
                <TouchableOpacity onPress={() => setCurrentPage('home')}>
                    <Text style={styles.menuItem}>Home</Text>
                </TouchableOpacity>

                <TouchableOpacity onPress={toggleAboutUs}>
                    <Text style={styles.menuItem}>About Us {aboutUsExpanded ? '-' : '+'}</Text>
                </TouchableOpacity>
                {aboutUsExpanded && (
                    <View style={styles.submenu}>
                        <TouchableOpacity onPress={() => setCurrentPage('aboutUs')}>
                            <Text style={styles.submenuItem}>About Us</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => setCurrentPage('careers')}>
                            <Text style={styles.submenuItem}>Careers</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => setCurrentPage('ourTeam')}>
                            <Text style={styles.submenuItem}>Our Team</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => setCurrentPage('ourClients')}>
                            <Text style={styles.submenuItem}>Our Clients</Text>
                        </TouchableOpacity>
                    </View>
                )}

                <TouchableOpacity onPress={toggleServices}>
                    <Text style={styles.menuItem}>Services {servicesExpanded ? '-' : '+'}</Text>
                </TouchableOpacity>
                {servicesExpanded && (
                    <View style={styles.submenu}>
                        <TouchableOpacity onPress={() => setCurrentPage('roboticsTraining')}>
                            <Text style={styles.submenuItem}>Robotics Training</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => setCurrentPage('softwareDevelopment')}>
                            <Text style={styles.submenuItem}>Software Development</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => setCurrentPage('websiteDevelopment')}>
                            <Text style={styles.submenuItem}>Website Development</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => setCurrentPage('roboticsMaintenance')}>
                            <Text style={styles.submenuItem}>Robotics Maintenance</Text>
                        </TouchableOpacity>
                    </View>
                )}

                <TouchableOpacity onPress={() => setCurrentPage('portfolio')}>
                    <Text style={styles.menuItem}>Portfolio</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => setCurrentPage('news')}>
                    <Text style={styles.menuItem}>News</Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => setCurrentPage('contactUs')}>
                    <Text style={styles.menuItem}>Contact Us</Text>
                </TouchableOpacity>
            </View>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        paddingVertical: 10,
        paddingHorizontal: 15,
        backgroundColor: '#f8f8f8',
    },
    row: {
        flexDirection: 'row',
        flexWrap: 'wrap', // Allows wrapping of submenu items if needed
        justifyContent: 'flex-start', // Aligns items to the left
    },
    menuItem: {
        fontSize: 16,
        paddingVertical: 10,
        marginHorizontal: 10,  // Space between main menu items
        color: '#000',
    },
    submenu: {
        position: 'absolute',
        backgroundColor: '#f8f8f8',
        padding: 10,
        zIndex: 1,
        marginLeft: 10, // Align submenu under the parent item
    },
    submenuItem: {
        fontSize: 14,
        paddingVertical: 5,
        color: '#555',
    },
});

export default Menu;*/
/*// Menu.js
import React, { useState } from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';

const Menu = ({ setCurrentPage }) => {
    const [aboutUsExpanded, setAboutUsExpanded] = useState(false);
    const [servicesExpanded, setServicesExpanded] = useState(false);

    const toggleAboutUs = () => setAboutUsExpanded(prev => !prev);
    const toggleServices = () => setServicesExpanded(prev => !prev);

    return (
        <View style={styles.container}>
            <TouchableOpacity onPress={() => setCurrentPage('home')}>
                <Text style={styles.menuItem}>Home</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={toggleAboutUs}>
                <Text style={styles.menuItem}>About Us {aboutUsExpanded ? '-' : '+'}</Text>
            </TouchableOpacity>
            {aboutUsExpanded && (
                <View style={styles.submenu}>
                    <TouchableOpacity onPress={() => setCurrentPage('aboutUs')}>
                        <Text style={styles.submenuItem}>About Us</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={() => setCurrentPage('careers')}>
                        <Text style={styles.submenuItem}>Careers</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={() => setCurrentPage('ourTeam')}>
                        <Text style={styles.submenuItem}>Our Team</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={() => setCurrentPage('ourClients')}>
                        <Text style={styles.submenuItem}>Our Clients</Text>
                    </TouchableOpacity>
                </View>
            )}

            <TouchableOpacity onPress={toggleServices}>
                <Text style={styles.menuItem}>Services {servicesExpanded ? '-' : '+'}</Text>
            </TouchableOpacity>
            {servicesExpanded && (
                <View style={styles.submenu}>
                    <TouchableOpacity onPress={() => setCurrentPage('roboticsTraining')}>
                        <Text style={styles.submenuItem}>Robotics Training</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={() => setCurrentPage('softwareDevelopment')}>
                        <Text style={styles.submenuItem}>Software Development</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={() => setCurrentPage('websiteDevelopment')}>
                        <Text style={styles.submenuItem}>Website Development</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={() => setCurrentPage('roboticsMaintenance')}>
                        <Text style={styles.submenuItem}>Robotics Maintenance</Text>
                    </TouchableOpacity>
                </View>
            )}

            <TouchableOpacity onPress={() => setCurrentPage('portfolio')}>
                <Text style={styles.menuItem}>Portfolio</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => setCurrentPage('news')}>
                <Text style={styles.menuItem}>News</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => setCurrentPage('contactUs')}>
                <Text style={styles.menuItem}>Contact Us</Text>
            </TouchableOpacity>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        paddingVertical: 10,
        paddingHorizontal: 15,
        backgroundColor: '#f8f8f8',
    },
    menuItem: {
        fontSize: 16,
        paddingVertical: 10,
        marginHorizontal: 10, // Space between menu items
        color: '#000',
    },
    submenu: {
        paddingLeft: 15, // Indentation for submenu items
    },
    submenuItem: {
        fontSize: 14,
        paddingVertical: 5,
        color: '#555',
    },
});

export default Menu;*/

/*// Menu.js
import React, { useState } from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';

const Menu = ({ navigation }) => {
    const [aboutUsExpanded, setAboutUsExpanded] = useState(false);
    const [servicesExpanded, setServicesExpanded] = useState(false);

    const toggleAboutUs = () => setAboutUsExpanded(prev => !prev);
    const toggleServices = () => setServicesExpanded(prev => !prev);

    return (
        <View style={styles.container}>
            <View style={styles.row}>
                <TouchableOpacity onPress={() => navigation.navigate('Home')}>
                    <Text style={styles.menuItem}>Home</Text>
                </TouchableOpacity>

                <TouchableOpacity onPress={toggleAboutUs}>
                    <Text style={styles.menuItem}>About Us {aboutUsExpanded ? '-' : '+'}</Text>
                </TouchableOpacity>
                {aboutUsExpanded && (
                    <View style={styles.submenu}>
                        <TouchableOpacity onPress={() => navigation.navigate('AboutUs')}>
                            <Text style={styles.submenuItem}>About Us</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => navigation.navigate('Careers')}>
                            <Text style={styles.submenuItem}>Careers</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => navigation.navigate('OurTeam')}>
                            <Text style={styles.submenuItem}>Our Team</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => navigation.navigate('OurClients')}>
                            <Text style={styles.submenuItem}>Our Clients</Text>
                        </TouchableOpacity>
                    </View>
                )}

                <TouchableOpacity onPress={toggleServices}>
                    <Text style={styles.menuItem}>Services {servicesExpanded ? '-' : '+'}</Text>
                </TouchableOpacity>
                {servicesExpanded && (
                    <View style={styles.submenu}>
                        <TouchableOpacity onPress={() => navigation.navigate('RoboticsTraining')}>
                            <Text style={styles.submenuItem}>Robotics Training</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => navigation.navigate('SoftwareDevelopment')}>
                            <Text style={styles.submenuItem}>Software Development</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => navigation.navigate('WebsiteDevelopment')}>
                            <Text style={styles.submenuItem}>Website Development</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => navigation.navigate('RoboticsMaintenance')}>
                            <Text style={styles.submenuItem}>Robotics Maintenance</Text>
                        </TouchableOpacity>
                    </View>
                )}

                <TouchableOpacity onPress={() => navigation.navigate('Portfolio')}>
                    <Text style={styles.menuItem}>Portfolio</Text>
                </TouchableOpacity>

                <TouchableOpacity onPress={() => navigation.navigate('News')}>
                    <Text style={styles.menuItem}>News</Text>
                </TouchableOpacity>

                <TouchableOpacity onPress={() => navigation.navigate('ContactUs')}>
                    <Text style={styles.menuItem}>Contact Us</Text>
                </TouchableOpacity>
            </View>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        paddingVertical: 10,
        paddingHorizontal: 15,
        backgroundColor: '#f8f8f8',
    },
    row: {
        flexDirection: 'row',
        justifyContent: 'space-between', // Adjusts space between items
        alignItems: 'center',
    },
    menuItem: {
        fontSize: 16,
        paddingVertical: 10,
        marginHorizontal: 10, // Space between menu items
        color: '#000',
    },
    submenu: {
        position: 'absolute',
        backgroundColor: '#f8f8f8',
        padding: 10,
        zIndex: 1,
    },
    submenuItem: {
        fontSize: 14,
        paddingVertical: 5,
        paddingLeft: 20, // Indentation for submenu items
        color: '#555',
    },
});

export default Menu;*/

/*
// Menu.js
import React, { useState } from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';

const Menu = ({ navigation }) => {
    const [aboutUsExpanded, setAboutUsExpanded] = useState(false);
    const [servicesExpanded, setServicesExpanded] = useState(false);

    const toggleAboutUs = () => setAboutUsExpanded(prev => !prev);
    const toggleServices = () => setServicesExpanded(prev => !prev);

    return (
        <View style={styles.container}>
            <TouchableOpacity onPress={() => navigation.navigate('Home')}>
                <Text style={styles.menuItem}>Home</Text>
            </TouchableOpacity>

            <TouchableOpacity onPress={toggleAboutUs}>
                <Text style={styles.menuItem}>About Us {aboutUsExpanded ? '-' : '+'}</Text>
            </TouchableOpacity>
            {aboutUsExpanded && (
                <View style={styles.submenu}>
                    <TouchableOpacity onPress={() => navigation.navigate('AboutUs')}>
                        <Text style={styles.submenuItem}>About Us</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={() => navigation.navigate('Careers')}>
                        <Text style={styles.submenuItem}>Careers</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={() => navigation.navigate('OurTeam')}>
                        <Text style={styles.submenuItem}>Our Team</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={() => navigation.navigate('OurClients')}>
                        <Text style={styles.submenuItem}>Our Clients</Text>
                    </TouchableOpacity>
                </View>
            )}

            <TouchableOpacity onPress={toggleServices}>
                <Text style={styles.menuItem}>Services {servicesExpanded ? '-' : '+'}</Text>
            </TouchableOpacity>
            {servicesExpanded && (
                <View style={styles.submenu}>
                    <TouchableOpacity onPress={() => navigation.navigate('RoboticsTraining')}>
                        <Text style={styles.submenuItem}>Robotics Training</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={() => navigation.navigate('SoftwareDevelopment')}>
                        <Text style={styles.submenuItem}>Software Development</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={() => navigation.navigate('WebsiteDevelopment')}>
                        <Text style={styles.submenuItem}>Website Development</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={() => navigation.navigate('RoboticsMaintenance')}>
                        <Text style={styles.submenuItem}>Robotics Maintenance</Text>
                    </TouchableOpacity>
                </View>
            )}

            <TouchableOpacity onPress={() => navigation.navigate('Portfolio')}>
                <Text style={styles.menuItem}>Portfolio</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => navigation.navigate('News')}>
                <Text style={styles.menuItem}>News</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => navigation.navigate('ContactUs')}>
                <Text style={styles.menuItem}>Contact Us</Text>
            </TouchableOpacity>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        backgroundColor: '#f8f8f8',
        paddingVertical: 10,
        paddingHorizontal: 15,
    },
    menuItem: {
        fontSize: 16,
        paddingVertical: 10,
        color: '#000',
    },
    submenu: {
        paddingLeft: 15, // Indentation for submenu items
    },
    submenuItem: {
        fontSize: 14,
        paddingVertical: 5,
        color: '#555',
    },
});

export default Menu;
*/

/*// Menu.js
import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';

const Menu = ({ navigation }) => {
    return (
        <View style={styles.container}>
            <TouchableOpacity onPress={() => navigation.navigate('Home')}>
                <Text style={styles.menuItem}>Home</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => navigation.navigate('AboutUs')}>
                <Text style={styles.menuItem}>About Us</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => navigation.navigate('Services')}>
                <Text style={styles.menuItem}>Services</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => navigation.navigate('Portfolio')}>
                <Text style={styles.menuItem}>Portfolio</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => navigation.navigate('News')}>
                <Text style={styles.menuItem}>News</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => navigation.navigate('ContactUs')}>
                <Text style={styles.menuItem}>Contact Us</Text>
            </TouchableOpacity>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flexDirection: 'row',
        justifyContent: 'space-around',
        paddingVertical: 10,
        backgroundColor: '#4CAF50', // Same as header color for consistency
    },
    menuItem: {
        color: '#fff',
        fontSize: 16,
    },
});

export default Menu;*/
/*

// src/components/Menu.js
import React from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';
import { useNavigation } from '@react-navigation/native';

const Menu = () => {
    const navigation = useNavigation();

    return (
        <View style={styles.container}>
            <Text style={styles.menuHeading}>Main Menu</Text>
            <Button title="Products" onPress={() => navigation.navigate('Products')} />
            <Button title="Courses" onPress={() => navigation.navigate('Courses')} />
            <Button title="Competitions" onPress={() => navigation.navigate('Competitions')} />
            <Button title="Community" onPress={() => navigation.navigate('Community')} />
            <Button title="Profile" onPress={() => navigation.navigate('Profile')} />
            <Button title="Cart" onPress={() => navigation.navigate('Cart')} />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        padding: 20,
        backgroundColor: '#f0f0f0',
    },
    menuHeading: {
        fontSize: 20,
        fontWeight: 'bold',
        marginBottom: 10,
    },
});

export default Menu;
*/
