// screens/CourseDetailsScreen.js
import React, { useEffect, useState } from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';
import Loader from '../components/Loader';
import { getCoursesById } from "../utils/api";

const CourseDetailsScreen = ({ route }) => {
    const { courseId } = route.params;
    const [course, setCourse] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchCourse = async () => {
            const data = await getCoursesById(courseId);
            setCourse(data);
            setLoading(false);
        };
        fetchCourse();
    }, [courseId]);

    if (loading) return <Loader />;

    return (
        <View style={styles.container}>
            <Text style={styles.title}>{course.title}</Text>
            <Text>{course.description}</Text>
            <Button title="Enroll Now" onPress={() => {/* Enrollment logic */}} />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 20,
    },
    title: {
        fontSize: 28,
        fontWeight: 'bold',
        marginBottom: 10,
    },
});

export default CourseDetailsScreen;
