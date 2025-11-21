// src/App.jsx
import { useEffect, useState } from "react";
import useSentence, { getLanguageSentence } from "./hooks/useSentence";
import LingPopup from "./components/LingPopup";
import { Container, FormControl, InputLabel, MenuItem, Select, Typography } from "@mui/material";

function App() {
  const sentence = useSentence(); // custom hook listens to WebSocket
  const [language, setLanguage] = useState("en");

  useEffect(() => {
    const loadData = async () => {
      const data = await getLanguageSentence();
      if (data) {
        setLanguage(data.language.toLowerCase());
      }
    };
    loadData();
  }, []);

  return (
    <Container sx={{ mt: 5 }}>
      <Typography variant="h4" textAlign="center" gutterBottom>
        🌐 LingDingDong System
      </Typography>
      <Typography textAlign="center" color="text.secondary" sx={{ mb: 3 }}>
        Waiting for next sentence popup every 10 minutes...
      </Typography>

      <FormControl size="small" sx={{ minWidth: 160, mr: 2 }}>
        <InputLabel>Learning Language</InputLabel>
        <Select
          value={language}
          label="UI Language"
          onChange={(e) => setLanguage(e.target.value)}
          MenuProps={{ disablePortal: true }}
        >
          <MenuItem value="en">English</MenuItem>
          <MenuItem value="ko">Korean</MenuItem>
          <MenuItem value="vi">Vietnamese</MenuItem>
          <MenuItem value="ja">Japanese</MenuItem>
        </Select>
      </FormControl>

      <LingPopup sentence={sentence} language={language} setLanguage={setLanguage} />
    </Container>
  );
}

export default App;
