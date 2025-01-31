import React, { useEffect, useState } from 'react';
import { View, Text, Button, FlatList, StyleSheet, Alert, TextInput } from 'react-native';
import api from '../utils/api'; // Your API utility
import CompetitionCard from '../components/CompetitionCard';
import competitionService from "../utils/api2"; // Service to manage competition data

const CompetitionsScreen = () => {
    const [competitions, setCompetitions] = useState([]);
    const [loading, setLoading] = useState(true);
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [price, setPrice] = useState('');
    const [dueDate, setDueDate] = useState('');
    const [website, setWebsite] = useState('');
    const [isCallForPrice, setIsCallForPrice] = useState(false); // For handling 'Call for price' option

    useEffect(() => {
        const fetchCompetitions = async () => {
            try {
                const response = await competitionService.getAllCompetitions();
                setCompetitions(response);
            } catch (error) {
                console.error(error);
                Alert.alert("Error", "Failed to fetch competitions");
            } finally {
                setLoading(false);
            }
        };

        fetchCompetitions();
    }, []);

    const handleRegister = async (competitionId) => {
        try {
            await api.post(`/competitions/${competitionId}/register`);
            Alert.alert('Success', 'Successfully registered for the competition!');
        } catch (error) {
            console.error(error);
            Alert.alert('Error', 'Registration failed!');
        }
    };

    const handleSubmit = async () => {
        const competitionData = {
            name,
            description,
            price: isCallForPrice ? null : parseFloat(price),
            dueDate,
            website,
        };

        try {
            await api.post('/competitions', competitionData);
            Alert.alert('Success', 'Competition created successfully!');
            // Clear the form after submission
            setName('');
            setDescription('');
            setPrice('');
            setDueDate('');
            setWebsite('');
            setIsCallForPrice(false);
            // Optionally, fetch competitions again to display the latest data
            const response = await competitionService.getAllCompetitions();
            setCompetitions(response);
        } catch (error) {
            console.error(error);
            Alert.alert('Error', 'Error creating competition!');
        }
    };

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Competitions</Text>
            <TextInput
                style={styles.input}
                placeholder="Competition Name"
                value={name}
                onChangeText={setName}
            />
            <TextInput
                style={styles.input}
                placeholder="Description"
                value={description}
                onChangeText={setDescription}
            />
            <TextInput
                style={styles.input}
                placeholder="Price (Leave empty for 'Call for price')"
                value={price}
                onChangeText={setPrice}
            />
            <Button
                title={isCallForPrice ? "Set Price" : "Call for Price"}
                onPress={() => setIsCallForPrice(!isCallForPrice)}
            />
            <TextInput
                style={styles.input}
                placeholder="Due Date (YYYY-MM-DD)"
                value={dueDate}
                onChangeText={setDueDate}
            />
            <TextInput
                style={styles.input}
                placeholder="Website URL"
                value={website}
                onChangeText={setWebsite}
            />
            <Button title="Submit Competition" onPress={handleSubmit} />

            {loading ? (
                <Text>Loading competitions...</Text>
            ) : (
                <FlatList
                    data={competitions}
                    renderItem={({ item }) => (
                        <CompetitionCard
                            competition={item}
                            onRegister={() => handleRegister(item.id)}
                        />
                    )}
                    keyExtractor={(item) => item.id.toString()}
                />
            )}
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
    input: {
        height: 40,
        borderColor: '#ddd',
        borderWidth: 1,
        marginBottom: 12,
        paddingHorizontal: 8,
    },
});

export default CompetitionsScreen;
/*
import React, { useEffect, useState } from 'react';
import { View, Text, Button, FlatList, StyleSheet } from 'react-native';
import api from '../utils/api'; // Your API utility
import CompetitionCard from '../components/CompetitionCard';
import competitionService from "../utils/api2"; // Component to display individual competition details

const CompetitionsScreen = () => {
    const [competitions, setCompetitions] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchCompetitions = async () => {
            try {
                // const response = await api.get('/competitions');
                const response = await competitionService.getAllCompetitions();
                setCompetitions(response);
              //  setCompetitions(response.data);
            } catch (error) {
                console.error(error);
            } finally {
                setLoading(false);
            }
        };

        fetchCompetitions();
    }, []);

    const handleRegister = async (competitionId) => {
        try {
            await api.post(`/competitions/${competitionId}/register`);
            alert('Successfully registered for the competition!');
        } catch (error) {
            console.error(error);
            alert('Registration failed!');
        }
    };

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Competitions</Text>
            {loading ? (
                <Text>Loading competitions...</Text>
            ) : (
                <FlatList
                    data={competitions}
                    renderItem={({ item }) => (
                        <CompetitionCard
                            competition={item}
                            onRegister={() => handleRegister(item.id)}
                        />
                    )}
                    keyExtractor={(item) => item.id.toString()}
                />
            )}
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
});

export default CompetitionsScreen;*/
/*

// screens/CompetitionsScreen.js
import React, { useEffect, useState } from 'react';
import { View, Text, FlatList, Button, StyleSheet } from 'react-native';
import competitionService from '../services/competitionService';
import Loader from '../components/Loader';

const CompetitionsScreen = () => {
    const [competitions, setCompetitions] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchCompetitions = async () => {
            const data = await competitionService.getAllCompetitions();

            // const CompetitionsScreen = ({ navigation }) => {
            // const response = await api.get('/competitions'); // Adjust endpoint as necessary
            // setCompetitions(response.data);
            setCompetitions(data);
            setLoading(false);
        };
        fetchCompetitions();
    }, []);

    if (loading) return <Loader />;

    return (
        <View style={styles.container}>
            <Text style={styles.header}>Upcoming Competitions</Text>
            <FlatList
                data={competitions}
                keyExtractor={item => item.id.toString()}
                renderItem={({ item }) => (
                    <View style={styles.competitionCard}>
                        <Text style={styles.title}>{item.name}</Text>
                        <Text>{item.description}</Text>
                        <Button
                            title="View Details"
                            onPress={() => navigation.navigate('CompetitionDetails', { competitionId: item.id })}
                        />
                    </View>
                )}
                // keyExtractor={(item) => item.id.toString()}
            />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 20,
        backgroundColor: '#fff',
    },
    header: {
        fontSize: 24,
        marginBottom: 10,
    },
    competitionCard: {
        padding: 15,
        backgroundColor: '#f0f0f0',
        marginVertical: 10,
        borderRadius: 8,
    },
});

export default CompetitionsScreen;
*/
