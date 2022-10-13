package com.bitmake.open.api.domain.quote;

import lombok.Data;

@Data
public class SymbolInfo {

    /**
     * symbol name
     */
    private String symbol;

    /**
     * base token name
     */
    private String baseToken;

    /**
     * quote token name
     */
    private String quoteToken;

    /**
     * 1=spot, 2=option, 3=contract
     */
    private Integer category;

    /**
     * optional when category is 3
     */
    private ContractSymbolConfig contractConfig;

    /**
     * symbol price precision
     */
    private Integer pricePrecision;

    /**
     * symbol price precision step
     */
    private Integer priceStep;

    /**
     * symbol quantity precision
     */
    private Integer quantityPrecision;

    /**
     * symbol quantity precision step
     */
    private Integer quantityStep;

    /**
     * min trade quantity
     */
    private String minQuantity;

    /**
     * amount precision (pricePrecision + quantityPrecision)
     */
    private Integer amountPrecision;

    /**
     * amount step
     */
    private Integer amountStep;

    /**
     * min trade amount
     */
    private String minAmount;

    /**
     * max merged price precision
     */
    private Integer digitMerge;

    @Data
    public static class ContractSymbolConfig {
        /**
         * indicates is it a reverse contract
         */
        private Boolean isReverse;

        /**
         * indicates is it a perpetual contract
         */
        private Boolean isPerpetual;

        /**
         * contract multiplier
         */
        private String multiplier;

        /**
         * index token name
         */
        private String displayIndexToken;

        /**
         * margin token name
         */
        private String marginToken;

        /**
         * margin token price precision
         */
        private Integer marginTokenPrecision;
    }

}
