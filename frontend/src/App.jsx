// src/App.jsx
import LingPopup from "./components/LingPopup";
import { Button, Container, FormControl, InputLabel, MenuItem, Select, Typography } from "@mui/material";

function App() {
  // const sentence = useSentence(setIsLoading); // custom hook listens to WebSocket

  return (
    <Container sx={{ mt: 5 }}>
      <Typography variant="h4" textAlign="center" gutterBottom>
        🌐 LingDingDong System
      </Typography>
      <Typography textAlign="center" color="text.secondary" sx={{ mb: 3 }}>
        Waiting for next sentence popup every 10 minutes...
      </Typography>

      <LingPopup />
    </Container>
  );
}

export default App;
