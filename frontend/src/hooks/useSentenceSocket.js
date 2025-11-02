// src/hooks/useSentenceSocket.js
import { Client } from '@stomp/stompjs';

import { useState, useEffect } from 'react';

export default function useSentenceSocket() {
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
