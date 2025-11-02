import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    proxy: {
      '/ws': 'http://localhost:8080', // proxy websocket
      '/api': 'http://localhost:8080', // if you add REST APIs
    },
  },
})
