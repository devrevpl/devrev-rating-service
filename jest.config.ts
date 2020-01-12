module.exports = {
    preset: 'jest-expo',
    transform: {
        '\\.js$': '<rootDir>/node_modules/react-native/jest/preprocessor.js',
    },
    "transformIgnorePatterns": [
        // Change MODULE_NAME_HERE to your module that isn't being compiled
        "/node_modules/(?!MODULE_NAME_HERE).+\\.js$"
    ]
};