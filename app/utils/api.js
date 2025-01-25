import axios from 'axios';

// Create an instance of Axios
const api = axios.create({
    baseURL: 'http://localhost:8083/api', // Replace with your actual backend API URL
});

// Interceptors for adding JWT to headers
api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token'); // Adjust based on where you store your token
        if (token) {
            config.headers.Authorization = `Bearer ${token}`; // Add the token to the Authorization header
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// User Authentication
const login = (username, password) => api.post('/auth/login', { username, password });
const signup = (username, password) => api.post('/auth/signup', { username, password });
const getUserDetails = (userId) => api.get(`/users/${userId}`);
const updateUserDetails = (userId, userData) => api.put(`/users/${userId}`, userData);

// Product CRUD Operations
const getProducts = () => api.get('/products');
const createProduct = (productData) => api.post('/products', productData);
const updateProduct = (productId, productData) => api.put(`/products/${productId}`, productData);
const deleteProduct = (productId) => api.delete(`/products/${productId}`);
const getProductDetails = (productId) => api.get(`/products/${productId}`);

// Services CRUD Operations
const getServices = () => api.get('/services');
const createService = (serviceData) => api.post('/services', serviceData);
const updateService = (serviceId, serviceData) => api.put(`/services/${serviceId}`, serviceData);
const deleteService = (serviceId) => api.delete(`/services/${serviceId}`);

// Course CRUD Operations
const getCourses = () => api.get('/courses');
const createCourse = (courseData) => api.post('/courses', courseData);
const updateCourse = (courseId, courseData) => api.put(`/courses/${courseId}`, courseData);
const deleteCourse = (courseId) => api.delete(`/courses/${courseId}`);

// Event CRUD Operations
const getEvents = () => api.get('/events');
const createEvent = (eventData) => api.post('/events', eventData);
const updateEvent = (eventId, eventData) => api.put(`/events/${eventId}`, eventData);
const deleteEvent = (eventId) => api.delete(`/events/${eventId}`);

// Resource CRUD Operations
const getResources = () => api.get('/resources');
const createResource = (resourceData) => api.post('/resources', resourceData);
const updateResource = (resourceId, resourceData) => api.put(`/resources/${resourceId}`, resourceData);
const deleteResource = (resourceId) => api.delete(`/resources/${resourceId}`);

// Partnership Management
const getPartnership = () => api.get('/partnerships');
const createPartnership = (partnershipData) => api.post('/partnerships', partnershipData);
const updatePartnership = (partnershipId, partnershipData) => api.put(`/partnerships/${partnershipId}`, partnershipData);
const deletePartnership = (partnershipId) => api.delete(`/partnerships/${partnershipId}`);

// Order Management
const fetchOrders = () => api.get(`/orders`);
const updateOrderStatus = (orderId, status) => api.put(`/orders/${orderId}`, { status });

// Payment Management
const initiatePayment = (paymentData) => api.post(`/payments/initiate-payment`, paymentData);
const verifyPayment = (verificationData) => api.post(`/payments/verify-payment`, verificationData);

// Feedback CRUD Operations
const getFeedbackByCourseId = (courseId) => api.get(`/feedback/course/${courseId}`);
const submitFeedback = (feedbackData) => api.post('/feedback', feedbackData);

// Community Management
const getAllCommunityPosts = () => api.get(`/community-posts`);
const createCommunityPost =  (post) => api.post(`/community-posts`, post);
const getCommentsByPostId =  (postId) => api.post(`/community-posts/${postId}/comments`);
const postComment = (postId, comment) => api.post(`/community-posts/${postId}/comments`, comment);

// Quiz CRUD Operations
const getQuizzes = () => api.get('/quizzes');
const createQuiz = (quizData) => api.post('/quizzes', quizData);
const getQuizById = (quizId) => api.get(`/quizzes/${quizId}`);

// Notification Management
const getNotifications = (userId) => api.get(`/notifications/user/${userId}`);
const createNotification = (notificationData) => api.post('/notifications', notificationData);

// Competition Management
const getAllCompetitions = () =>  api.get(`/competitions`);
const getCompetitionById =  (competitionId) => api.get(`/competitions/${competitionId}`)
const registerForCompetition = (competitionId, userId) => api.post(`/competitions/${competitionId}/register`, userId);

// Meeting Request
const createMeetingRequest = (requestData) =>  api.post(`${BASE_URL}/meeting-requests`, requestData);
const getAllMeetingRequests = () => api.get(`${BASE_URL}/meeting-requests`);

// Course CRUD Operations
const getCoursesById = (courseId) => api.get(`${BASE_URL}/courses/${courseId}`);

// Export all methods for use in your components
export {
    createMeetingRequest, getAllMeetingRequests,
    getAllCommunityPosts, createCommunityPost, getCommentsByPostId, postComment,
    getAllCompetitions, getCompetitionById, registerForCompetition,
    login, signup, getUserDetails, updateUserDetails,
    getProducts, createProduct, updateProduct, deleteProduct, getProductDetails,
    getServices, createService, updateService, deleteService,
    getCourses, createCourse, updateCourse, deleteCourse, getCoursesById,
    getEvents, createEvent, updateEvent, deleteEvent,
    getResources, createResource, updateResource, deleteResource,
    getFeedbackByCourseId, submitFeedback,
    getQuizzes, createQuiz, getQuizById,
    getPartnership, createPartnership, updatePartnership, deletePartnership,
    fetchOrders, updateOrderStatus,
    initiatePayment, verifyPayment,
    getNotifications, createNotification
};
/*

Choosing between using the Fetch API and Axios d
epends on your specific needs and preferences, but here are some considerations for both to help you decide:

1. Fetch API
Native Support: Fetch is built into modern browsers and Node.js, meaning no additional libraries are required.
Promise-Based: Fetch uses Promises, allowing you to write concise, modern asynchronous code.
Streaming: Fetch supports streaming of request and response bodies.
Better Control: You have more control over the request settings, such as specifying mode, credentials, and cache options.
Downsides:
No Built-In Timeout: Fetch does not support timeout natively, so you would need to implement it yourself.
Error Handling: Fetch does not reject the Promise on HTTP error statuses (like 404 or 500). You have to manually check the response.ok flag.
Less Intuitive: Some users may find Fetch's API less intuitive, especially when dealing with JSON, as you need to call .json() explicitly.
2. Axios
Simplified API: Axios has a more straightforward and user-friendly API for making HTTP requests.
Automatic JSON Handling: Axios automatically transforms requests and responses into JSON, reducing boilerplate code.
Interceptors: Axios allows the use of interceptors to easily modify requests or responses before they are handled.
Built-In Timeout: You can easily set timeouts for requests.
Better Error Handling: Axios automatically rejects the Promise for HTTP error statuses, simplifying error handling.
Downsides:
Additional Library: Axios requires an additional library to be included in your project, which adds to the bundle size.
Browser Compatibility: Though Axios works in most browsers, it's essential to ensure compatibility with your target environments.
Recommendations
For Simplicity & Convenience: If you are looking for a simplified approach with powerful features, including automatic JSON handling and interceptors, Axios is recommended.
For a Lightweight Solution: If you prefer not to add an external dependency and are comfortable with handling promises and errors yourself, the Fetch API is a great choice.
import axios from 'axios';

// Create an instance of Axios
const api = axios.create({
    baseURL: 'http://localhost:8083/api', // Replace with your actual backend API URL
});

// Interceptors for adding JWT to headers
api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token'); // Adjust based on where you store your token
        if (token) {
            config.headers.Authorization = `Bearer ${token}`; // Add the token to the Authorization header
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// User Authentication
const lgnup = (username, password) => api.post('/auth/signup', { username, password });
const getogin = (username, password) => api.post('/auth/login', { username, password });
const siUserDetails = (userId) => api.get(`/users/${userId}`);
const updateUserDetails = (userId, userData) => api.put(`/users/${userId}`, userData);

// Product CRUD Operations
const getProducts = () => api.get('/products');
const createProduct = (productData) => api.post('/products', productData);
const updateProduct = (productId, productData) => api.put(`/products/${productId}`, productData);
const deleteProduct = (productId) => api.delete(`/products/${productId}`);
const getProductDetails = (productId) => api.get(`/products/${productId}`); // Fetch details of a specific product



// Event CRUD Operations
const getEvents = () => api.get('/events');
const createEvent = (eventData) => api.post('/events', eventData);
const updateEvent = (eventId, eventData) => api.put(`/events/${eventId}`, eventData);
const deleteEvent = (eventId) => api.delete(`/events/${eventId}`);

// Resource CRUD Operations
const getResources = () => api.get('/resources');
const createResource = (resourceData) => api.post('/resources', resourceData);
const updateResource = (resourceId, resourceData) => api.put(`/resources/${resourceId}`, resourceData);
const deleteResource = (resourceId) => api.delete(`/resources/${resourceId}`);

 const getPartnership  = () => api.get('/partnerships');
const createPartnership = (partnershipData) => api.post('/partnerships', partnershipsData);
const updatePartnership = (partnershipId, partnershipData) => api.put(`/partnerships/${partnershipId}`, partnershipData);
const deletePartnership = (partnershipId) => api.delete(`/partnerships/${partnershipId}`);

const fetchOrders = () => api.get(`/orders`);
const updateOrderStatus = (orderId, status) => api.put(`/orders/${orderId}`, {status});

const initiatePayment = (paymentData) => api.post(`/payments/initiate-payment`, paymentData);
const verifyPayment = (verificationData) =>api.post(`/payments/verify-payment`, verificationData);


// Feedback CRUD Operations
const getFeedbackByCourseId = (courseId) => api.get(`/feedback/course/${courseId}`);
const submitFeedback = (feedbackData) => api.post('/feedback', feedbackData);

// Quiz CRUD Operations
const getQuizzes = () => api.get('/quizzes');
const createQuiz = (quizData) => api.post('/quizzes', quizData);
const getQuizById = (quizId) => api.get(`/quizzes/${quizId}`);

// Payment Management
const createPayment = (paymentData) => api.post('/payments', paymentData);

// Notification Management
const getNotifications = (userId) => api.get(`/notifications/user/${userId}`);
const createNotification = (notificationData) => api.post('/notifications', notificationData);

// Export all methods for use in your components
export {
    login,signup,getUserDetails,updateUserDetails,
    getProducts,createProduct,updateProduct,deleteProduct,getProductDetails,
    getServices,createService,updateService,deleteService,
    getCourses,createCourse,updateCourse,deleteCourse,
    getEvents,createEvent,updateEvent,deleteEvent,
    getResources,createResource,updateResource,deleteResource,
    getFeedbackByCourseId,submitFeedback,
    getQuizzes,createQuiz,getQuizById,
    createPayment,
    getNotifications,createNotification,
    getPartnership,createPartnership,updatePartnership,deletePartnership,
    fetchOrders,updateOrderStatus,
    initiatePayment,verifyPayment
};
*/
