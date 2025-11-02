import React, { useState, useEffect } from "react";
import {
  Snackbar,
  Card,
  CardContent,
  Typography,
  IconButton,
  Button,
  Stack,
} from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import VolumeUpIcon from "@mui/icons-material/VolumeUp";
import InfoIcon from "@mui/icons-material/Info";

export default function LingPopup({ sentence }) {
  const [open, setOpen] = useState(false);

  useEffect(() => {
    if (sentence) {
      setOpen(true);
      const timer = setTimeout(() => setOpen(false), 15000); // Auto-close after 15 sec
      return () => clearTimeout(timer);
    }
  }, [sentence]);

  const handleClose = () => setOpen(false);
  const handleSpeak = () =>
    speechSynthesis.speak(new SpeechSynthesisUtterance(sentence));

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

          <Stack direction="row" justifyContent="space-between">
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

            <Button onClick={handleClose} color="error" variant="outlined">
              <CloseIcon />
            </Button>
          </Stack>
        </CardContent>
      </Card>
    </Snackbar>
  );
}
