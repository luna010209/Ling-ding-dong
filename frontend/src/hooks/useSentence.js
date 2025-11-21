// src/hooks/useSentenceSocket.js
import { Client } from '@stomp/stompjs';
import axios from 'axios';

import { useState, useEffect } from 'react';

export default function useSentence() {
  const [sentence, setSentence] = useState("Welcome to LingDingDong! 🌐");

  useEffect(() => {
    const client = new Client({
      brokerURL: 'ws://localhost:8080/ws', // your Spring Boot STOMP endpoint
      reconnectDelay: 5000, // auto-reconnect
    });

    client.onConnect = () => {
      console.log('✅ Connected to WebSocket');
      client.subscribe('/topic/sentence', (message) => {
        setSentence(message.body);
      });
    };

    client.activate(); // connect

    return () => client.deactivate();
  }, []);

  return sentence;
}

export const changeLanguage = async (lang) => {
  try {
    await axios.post("/api/language", null, {
      params: { language: lang },   // MUST match backend
    });
  } catch (error) {
    console.error("Failed to update language", error);
  }
};

export const getLanguageSentence = async () => {
  try {
    const res = await axios.get("/api/language");
    return res.data; 
  } catch (error) {
    console.error("Failed to update language", error);
  }
};

