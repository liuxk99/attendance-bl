package com.sj.attendance.bl;

public class StockWorkTime {
    static final String FIX_FULL_DAY = "XX集团-固定工时-全天";
    static final String FLEX_FULL_DAY = "XX集团-弹性工时-全天";
    static final String ALL_PM = "XX集团-所有工时-下午";
    static final String FIX_AM = "XX集团-固定工时-上午";
    static final String FLEX_AM = "XX集团-弹性工时-上午";

    static final String SHORT_TILE_FULL = "全天";
    static final String SHORT_TILE_AM = "上午";
    static final String SHORT_TILE_PM = "下午";

    public static final long DEF_CHECK_IN_HOUR = 9;
    public static final long DEF_CHECK_OUT_MIN = 0;
    public static final long DEF_LATEST_CHECK_IN_HOUR = 10;
    public static final long DEF_CHECK_OUT_HOUR = 18;
}
