import React, { useState, useEffect } from "react";
import {
  Snackbar,
  Card,
  CardContent,
  Typography,
  IconButton,
  Button,
  Stack,
  Select,
  MenuItem,
  TextField,
} from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import VolumeUpIcon from "@mui/icons-material/VolumeUp";
import InfoIcon from "@mui/icons-material/Info";
import { changeLanguage } from "../hooks/useSentence";

const LANG_MAP = {
  ko: "ko-KR",
  en: "en-US",
  vi: "vi-VN",
  ja: "ja-JP",
};

export default function LingPopup({ sentence, language, setLanguage }) {
  const [open, setOpen] = useState(false);

  useEffect(() => {
    if (sentence) {
      setOpen(true);
      const timer = setTimeout(() => setOpen(false), 60000); // Auto-close after 15 sec
      return () => clearTimeout(timer);
    }
  }, [sentence]);

  const handleClose = () => setOpen(false);
  const handleSpeak = () => {
    const utter = new SpeechSynthesisUtterance(sentence);
    const fullLangCode = LANG_MAP[language]; 
    const voices = speechSynthesis.getVoices();
    const voice = voices.find((v) => v.lang.startsWith(fullLangCode));

    if (voice) {
      utter.voice = voice;
    } else {
      console.warn("No matching voice found for", fullLangCode);
    }

    speechSynthesis.speak(utter);
  };

  const handleLanguageChange = async (lang) => {
    setLanguage(lang);
    try {
      await changeLanguage(lang);
      console.log("Language changed:", lang);
    } catch (error) {
      console.error("Failed to change language:", error);
    }
  };

  if (!sentence) return null;

  return (
    <Snackbar
      open={open}
      onClose={handleClose}
      anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
      sx={{ mb: 3, mr: 3 }}
    >
      <Card
        sx={{
          minWidth: 300,
          maxWidth: 400,
          p: 2,
          boxShadow: 6,
          borderRadius: 3,
          backgroundColor: "#fff",
        }}
      >
        <CardContent>
          <Typography
            variant="h6"
            fontWeight="bold"
            gutterBottom
            color="primary"
          >
            LingDingDong 💬
          </Typography>

          <Typography variant="body1" sx={{ mb: 2 }}>
            {sentence}
          </Typography>

          <Stack direction="row" justifyContent="space-between" gap={1}>
            <IconButton color="primary" onClick={handleSpeak}>
              <VolumeUpIcon />
            </IconButton>

            <IconButton
              color="secondary"
              onClick={() =>
                window.open("https://example.com/info", "_blank")
              }
            >
              <InfoIcon />
            </IconButton>

            <Select
              value={language}
              onChange={(e) => handleLanguageChange(e.target.value)}
              size="small"
              sx={{ minWidth: 120 }}
              MenuProps={{ disablePortal: true }}
            >
              <MenuItem value="en">English</MenuItem>
              <MenuItem value="ko">Korean</MenuItem>
              <MenuItem value="vi">Vietnamese</MenuItem>
              <MenuItem value="ja">Japanese</MenuItem>
            </Select>

            <Button onClick={handleClose} color="error" variant="outlined">
              <CloseIcon size="small"/>
            </Button>
          </Stack>
        </CardContent>
      </Card>
    </Snackbar>
  );
}
