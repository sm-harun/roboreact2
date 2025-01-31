// screens/CoursesScreen.js
import React, { useEffect, useState } from 'react';
import { View, Text, FlatList, Button, StyleSheet } from 'react-native';
import Loader from '../components/Loader';
import { getCourses } from '../utils/api';

const CoursesScreen = () => {
    const [courses, setCourses] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchCourses = async () => {
            const data = await getCourses();
            setCourses(data);
            setLoading(false);
        };
        fetchCourses();
    }, []);

    if (loading) return <Loader />;

    return (
        <View style={styles.container}>
            <Text style={styles.header}>Available Courses</Text>
            <FlatList
                data={courses}
                keyExtractor={item => item.courseId.toString()}
                renderItem={({ item }) => (
                    <View style={styles.courseCard}>
                        <Text>{item.title}</Text>
                        <Button title="View Details" onPress={() => navigation.navigate('CourseDetails', { courseId: item.courseId })} />
                    </View>
                )}
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
    courseCard: {
        padding: 15,
        backgroundColor: '#f0f0f0',
        marginVertical: 10,
        borderRadius: 8,
    },
});

export default CoursesScreen;
