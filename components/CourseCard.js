import React from 'react';
import { View, Text, Button, StyleSheet } from 'react-native';

const CourseCard = ({ course, onPress }) => {
    return (
        <View style={styles.card}>
            <Text style={styles.title}>{course.title}</Text>
            <Text>{course.description}</Text>
            <Button title="View Details" onPress={onPress} />
        </View>
    );
};

const styles = StyleSheet.create({
    card: {
        padding: 16,
        marginVertical: 8,
        backgroundColor: '#f2f2f2',
        borderRadius: 10,
    },
    title: {
        fontSize: 18,
        fontWeight: 'bold',
    },
});

export default CourseCard;
