import { useState, useEffect } from "react";
import SockJS from "sockjs-client";
import Stomp from "stompjs";

export default function useSentenceSocket() {
  const [sentence, setSentence] = useState(null);

  useEffect(() => {
    const socket = new SockJS("http://localhost:8080/ws");
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, () => {
      console.log("✅ Connected to WebSocket");
      stompClient.subscribe("/topic/sentence", (message) => {
        console.log("📩 New sentence:", message.body);
        setSentence(message.body);
      });
    });

    return () => stompClient.disconnect();
  }, []);

  return sentence;
}
