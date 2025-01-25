// screens/CommunityScreen.js
import React, { useEffect, useState } from 'react';
import { View, FlatList, Button, StyleSheet } from 'react-native';
import CommunityCard from '../components/CommunityCard'; // Create a CommunityCard component for display
import api from '../utils/api';
import Loader from '../components/Loader';
import ModalForm from '../components/ModalForm';

const CommunityScreen = () => {
    const [communities, setCommunities] = useState([]);
    const [loading, setLoading] = useState(true);
    const [modalVisible, setModalVisible] = useState(false);
    const [editData, setEditData] = useState(null);

    useEffect(() => {
        const fetchCommunities = async () => {
            try {
                const response = await api.get('/communities');
                setCommunities(response.data);
            } catch (error) {
                console.error(error);
            } finally {
                setLoading(false);
            }
        };

        fetchCommunities();
    }, []);

    const handleAddOrUpdate = async (community) => {
        if (editData) {
            // Update
            await api.put(`/communities/${editData.id}`, community);
        } else {
            // Create
            await api.post('/communities', community);
        }
        setModalVisible(false);
        setEditData(null);
        fetchCommunities();
    };

    return (
        <View style={styles.container}>
            <Button title="Add Community" onPress={() => setModalVisible(true)} />
            {loading ? (
                <Loader />
            ) : (
                <FlatList
                    data={communities}
                    renderItem={({ item }) => (
                        <CommunityCard
                            community={item}
                            onEdit={() => {
                                setEditData(item);
                                setModalVisible(true);
                            }}
                            onDelete={async () => {
                                await api.delete(`/communities/${item.id}`);
                                fetchCommunities();
                            }}
                        />
                    )}
                    keyExtractor={(item) => item.id.toString()}
                />
            )}
            <ModalForm
                visible={modalVisible}
                onClose={() => {
                    setModalVisible(false);
                    setEditData(null);
                }}
                initialData={editData}
                onSubmit={handleAddOrUpdate}
            />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 16,
    },
});

export default CommunityScreen;
/*

import React, { useEffect, useState } from 'react';
import { View, Text, FlatList, Button, StyleSheet } from 'react-native';
import communityService from '../services/communityService';
import Loader from '../components/Loader';

const CommunityScreen = ({ navigation }) => {
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchPosts = async () => {
            const data = await communityService.getAllPosts();
            setPosts(data);
            setLoading(false);
        };
        fetchPosts();
    }, []);

    if (loading) return <Loader />;

    return (
        <View style={styles.container}>
            <Text style={styles.header}>Community Posts</Text>
            <FlatList
                data={posts}
                keyExtractor={item => item.postId.toString()}
                renderItem={({ item }) => (
                    <View style={styles.postCard}>
                        <Text style={styles.postTitle}>{item.title}</Text>
                        <Text>{item.content}</Text>
                        <Button
                            title="View Comments"
                            onPress={() => navigation.navigate('Comments', { postId: item.postId })}
                        />
                    </View>
                )}
            />
            <Button
                title="Create New Post"
                onPress={() => navigation.navigate('CreatePost')}
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
    postCard: {
        padding: 15,
        backgroundColor: '#f0f0f0',
        marginVertical: 10,
        borderRadius: 8,
    },
    postTitle: {
        fontWeight: 'bold',
        fontSize: 18,
    },
});

export default CommunityScreen;
*/
