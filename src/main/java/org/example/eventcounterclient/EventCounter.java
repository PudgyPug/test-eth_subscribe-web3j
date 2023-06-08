package com.example.eventcounterclient;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.9.8.
 */
@SuppressWarnings("rawtypes")
public class EventCounter extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5061017d806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c80632a1afcd91461003b57806360fe47b114610059575b600080fd5b61004361006e565b6040516100509190610104565b60405180910390f35b61006c6100673660046100ec565b610074565b005b60005481565b6000819055610084816002610123565b61008f826001610123565b827fe17fd3920d01fd5e94390b54b8612d51d3e0ececece27bb5a2e8708cd62ab5496100bc826003610123565b6100c7866004610123565b6100d2876005610123565b6040516100e19392919061010d565b60405180910390a450565b6000602082840312156100fd578081fd5b5035919050565b90815260200190565b9283526020830191909152604082015260600190565b6000821982111561014257634e487b7160e01b81526011600452602481fd5b50019056fea2646970667358221220feef3d8d72006a8711a8bc5f8f0648e4249be609541207842d9d127b94c5624464736f6c63430008000033";

    public static final String FUNC_SET = "set";

    public static final String FUNC_STOREDDATA = "storedData";

    public static final Event EVENTCOUNTER_EVENT = new Event("eventCounter", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected EventCounter(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EventCounter(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected EventCounter(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected EventCounter(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<EventCounterEventResponse> getEventCounterEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(EVENTCOUNTER_EVENT, transactionReceipt);
        ArrayList<EventCounterEventResponse> responses = new ArrayList<EventCounterEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EventCounterEventResponse typedResponse = new EventCounterEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.a = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.b = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.c = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.d = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.e = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.f = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static EventCounterEventResponse getEventCounterEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(EVENTCOUNTER_EVENT, log);
        EventCounterEventResponse typedResponse = new EventCounterEventResponse();
        typedResponse.log = log;
        typedResponse.a = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.b = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.c = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.d = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.e = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.f = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<EventCounterEventResponse> eventCounterEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getEventCounterEventFromLog(log));
    }

    public Flowable<EventCounterEventResponse> eventCounterEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EVENTCOUNTER_EVENT));
        return eventCounterEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> set(BigInteger x) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(x)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> storedData() {
        final Function function = new Function(FUNC_STOREDDATA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static EventCounter load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new EventCounter(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static EventCounter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EventCounter(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static EventCounter load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new EventCounter(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static EventCounter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new EventCounter(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<EventCounter> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EventCounter.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EventCounter> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EventCounter.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<EventCounter> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EventCounter.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EventCounter> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EventCounter.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class EventCounterEventResponse extends BaseEventResponse {
        public BigInteger a;

        public BigInteger b;

        public BigInteger c;

        public BigInteger d;

        public BigInteger e;

        public BigInteger f;
    }
}
