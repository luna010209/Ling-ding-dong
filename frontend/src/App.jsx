// src/App.jsx
import React from "react";
import useSentenceSocket from "./hooks/useSentenceSocket";
import LingPopup from "./components/LingPopup";
import { Container, Typography } from "@mui/material";

function App() {
  const sentence = useSentenceSocket(); // custom hook listens to WebSocket

  return (
    <Container sx={{ mt: 5 }}>
      <Typography variant="h4" textAlign="center" gutterBottom>
        🌐 LingDingDong System
      </Typography>
      <Typography textAlign="center" color="text.secondary" sx={{ mb: 3 }}>
        Waiting for next sentence popup every 10 minutes...
      </Typography>

      <LingPopup sentence={sentence} />
    </Container>
  );
}

export default App;
