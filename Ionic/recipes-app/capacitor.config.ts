import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.example.recipes.app',
  appName: 'recipes-app',
  webDir: 'www',
  server: {
    androidScheme: 'https',
    cleartext: true
  }
};

export default config;
