package org.example.eventcounterclient;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.websocket.WebSocketService;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.crypto.Hash;

import java.math.BigInteger;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

  private static final String RPC_WS_URL = "ws://127.0.0.1:8080";
  // private static final String RPC_WS_URL = "wss://goerli.infura.io/ws/v3/2862867b792b4146b43f6e51ae7c6359";
  // private static final String RPC_WS_URL = "ws://3.76.8.126";

  public static void main(String[] args) {

    // Connect to the Ethereum client using Infura

    WebSocketService webSocketService = new WebSocketService(RPC_WS_URL, true);
    try {
      webSocketService.connect();
    } catch (ConnectException e) {
      e.printStackTrace();
    }

    Web3j web3j = Web3j.build(webSocketService);


    System.out.println("Websocket connection established");
    // Setup filter to only catch logs for our contract and event
    // String contractAddress = "0x900f5ccF739aA7DE6b80eeAAC30a4Bb047d30E6a";
    String contractAddress = "0x406b991e2aa4603f3da97c0928b29e529fa45695";
    EthFilter filter = new EthFilter(
      DefaultBlockParameterName.EARLIEST,
      DefaultBlockParameterName.LATEST,
      contractAddress
    );

    // Subscribe to logs emitted by the contract
    List<String> addresses = new ArrayList<>();
    addresses.add(contractAddress);
    web3j.logsNotifications(addresses, Collections.emptyList()).subscribe(log -> {
      // Decode the log data with the event signature
      // String eventSignature = Hash.sha3("eventCounter(uint256,uint256,uint256,uint256,uint256,uint256)");
      System.out.println("log: " + log.toString());
      // String eventSignature = "0xe17fd3920d01fd5e94390b54b8612d51d3e0ececece27bb5a2e8708cd62ab549";
      // if (log.getTopics().get(0).equals(eventSignature)) {
      //   // Extract the indexed values
      //   List<String> topics = log.getTopics();
      //   BigInteger a = new BigInteger(topics.get(1).substring(2), 16);
      //   BigInteger b = new BigInteger(topics.get(2).substring(2), 16);
      //   BigInteger c = new BigInteger(topics.get(3).substring(2), 16);
      //   // BigInteger d = new BigInteger(topics.get(4).substring(2), 16);
      //   // BigInteger e = new BigInteger(topics.get(5).substring(2), 16);
      //   // BigInteger f = new BigInteger(topics.get(6).substring(2), 16);

      //   // Print the values
      //   System.out.println("a: " + a);
      //   System.out.println("b: " + b);
      //   System.out.println("c: " + c);
      //   // System.out.println("d: " + d);
      //   // System.out.println("e: " + e);
      //   // System.out.println("f: " + f);
      // }

    },
      error -> {
        error.printStackTrace();
      }
    );

    // Keep the program running
    try {
      Thread.sleep(Long.MAX_VALUE);
    } catch (InterruptedException ignored) {
    }
  }
}
