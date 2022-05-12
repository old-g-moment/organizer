module.exports = {
  roots: ['<rootDir>/src'],
  testRegex: '(/.*\\.test)\\.(ts|tsx)$',
  setupFilesAfterEnv: ['<rootDir>/jest.setup.js'],
  testPathIgnorePatterns: ['<rootDir>/node_modules/'],
  testEnvironment: 'jsdom',
};
