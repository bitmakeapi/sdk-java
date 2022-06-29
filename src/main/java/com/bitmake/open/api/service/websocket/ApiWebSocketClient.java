package com.bitmake.open.api.service.websocket;

import com.bitmake.open.api.domain.CandlestickInterval;
import com.bitmake.open.api.domain.event.*;
import com.bitmake.open.api.service.ApiCallback;

import java.io.Closeable;
import java.util.List;

public interface ApiWebSocketClient extends Closeable {
    /**
     * Open a new web socket to receive {@link DepthEvent depthEvents} on a callback.
     *
     * @param symbols  market (one or coma-separated) symbol(s) to subscribe to
     * @param callback the callback to call on new events
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onDepthEvent(String symbols, ApiCallback<DepthEvent> callback);

    /**
     * Open a new web socket to receive {@link CandlestickEvent candlestickEvents} on a callback.
     *
     * @param symbols  market (one or coma-separated) symbol(s) to subscribe to
     * @param interval the interval of the candles tick events required
     * @param callback the callback to call on new events
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onCandlestickEvent(String symbols, CandlestickInterval interval, ApiCallback<CandlestickEvent> callback);

    /**
     * Open a new web socket to receive {@link TickerEvent} on a callback
     *
     * @param symbols  SYMBOL must be form with exchangeId.symbol like 'BTCUSDT'
     * @param callback the callback to call on new events
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onTicker24HourEvent(String symbols, ApiCallback<TickerEvent> callback);

    /**
     * Open a new web socket to receive {@link IndexEvent} on a callback
     *
     * @param symbols  SYMBOL must be form with symbol like 'BTCUSDT'
     * @param callback the callback to call on new events
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onIndexEvent(String symbols, ApiCallback<IndexEvent> callback);

    /**
     * Open a new web socket to receive {@link TradeEvent} on a callback
     *
     * @param symbols  SYMBOL must be form with exchangeId.symbol like 'BTCUSDT'
     * @param callback the callback to call on new events
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onTradeEvent(String symbols, ApiCallback<TradeEvent> callback);

    /**
     * Open a new web socket to receive {@link BookTickerEvent bookTickerEvents} on a callback.
     *
     * @param symbols  market (one or coma-separated) symbol(s) to subscribe to
     * @param callback the callback to call on new events
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onBookTickerEvent(String symbols, ApiCallback<BookTickerEvent> callback);


    /**
     * subscribe channels
     *
     * @param requests
     * @return
     */
    Closeable onSubscribe(List<ChannelRequest> requests, ApiWebSocketListener<?> listener);
}
