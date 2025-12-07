import { useState, useEffect } from "react";
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
  CircularProgress,
} from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import VolumeUpIcon from "@mui/icons-material/VolumeUp";
import InfoIcon from "@mui/icons-material/Info";
import sentenceSocket, { changeLanguage, getLanguageSentence } from "../hooks/useSentence";

const LANG_MAP = {
  ko: "ko-KR",
  en: "en-US",
  vi: "vi-VN",
  ja: "ja-JP",
};

export default function LingPopup() {
  const [open, setOpen] = useState(false);
  const [language, setLanguage] = useState("en");
  const [mySentence, setSentence] = useState("Welcome to LingDingDong! 🌐");
  const [isLoading, setIsLoading] = useState(false);

  // useEffect(() => {
  //   // Ask for browser notification permission ONCE
  //   if (Notification.permission === "default") {
  //     Notification.requestPermission();
  //   }
  // }, []);

  // Fetch current language from backend when component mounts
  useEffect(() => {
    const disconnect = sentenceSocket(setSentence);

    const fetchLanguage = async () => {
      setIsLoading(true);
      try {
        const data = await getLanguageSentence(); // Make sure this returns { language, mySentence }
        if (data) {
          setLanguage(data.language.toLowerCase());
          setSentence(data.mySentence);
        }
      } catch (err) {
        console.error("Failed to get language", err);
      } finally {
        setIsLoading(false);
      }
    };

    fetchLanguage();

    return () => disconnect?.();
  }, []);

  // Open popup whenever mySentence changes
  useEffect(() => {
    if (mySentence) {
      setOpen(true);
      // --- Desktop Notification ---
      // if (Notification.permission === "granted") {
      //   new Notification("LingDingDong", {
      //     body: mySentence,
      //     icon: "/icon.png",
      //   });
      // } else if (Notification.permission === "default") {
      //   Notification.requestPermission().then((permission) => {
      //     if (permission === "granted") {
      //       new Notification("LingDingDong", {
      //         body: mySentence,
      //         icon: "/icon.png",
      //       });
      //     }
      //   });
      // }

      const timer = setTimeout(() => setOpen(false), 60000); // auto-close
      return () => clearTimeout(timer);
    }
  }, [mySentence]);

  const handleClose = () => setOpen(false);
  const handleSpeak = () => {
    if (!mySentence) return;
    const utter = new SpeechSynthesisUtterance(mySentence);
    const fullLangCode = LANG_MAP[language];
    const voices = speechSynthesis.getVoices();
    const voice = voices.find((v) => v.lang.startsWith(fullLangCode));
    if (voice) utter.voice = voice;
    speechSynthesis.speak(utter);
  };

  const handleLanguageChange = async (lang) => {
    setLanguage(lang);
    setIsLoading(true);
    try {
      const mySentence = await changeLanguage(lang); // Call backend to update language
      setSentence(mySentence);
      console.log("New mySentence:", mySentence);
    } catch (err) {
      console.error("Failed to change language", err);
    } finally {
      setIsLoading(false);
    }
  };

  if (!mySentence) return null;

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

          {isLoading ? (
            <Stack justifyContent="center" alignItems="center" sx={{ my: 2 }}>
              <CircularProgress size={24} /> {/* loading spinner */}
            </Stack>
          ) : (
            <Typography variant="body1" sx={{ mb: 2 }}>
              {mySentence}
            </Typography>
          )}

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
              <CloseIcon size="small" />
            </Button>
          </Stack>
        </CardContent>
      </Card>
    </Snackbar>
  );
}
