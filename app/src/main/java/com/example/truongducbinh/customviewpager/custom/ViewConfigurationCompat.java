package com.example.truongducbinh.customviewpager.custom;


import android.view.ViewConfiguration;
/**
 * Helper for accessing features in {@link ViewConfiguration}
 * introduced after API level 4 in a backwards compatible fashion.
 */
public class ViewConfigurationCompat {
    /**
     * Interface for the full API.
     */
    interface ViewConfigurationVersionImpl {
        public int getScaledPagingTouchSlop(ViewConfiguration config);
    }
    /**
     * Interface implementation that doesn't use anything about v4 APIs.
     */
    static class BaseViewConfigurationVersionImpl implements ViewConfigurationVersionImpl {
        @Override
        public int getScaledPagingTouchSlop(ViewConfiguration config) {
            return config.getScaledTouchSlop();
        }
    }
    /**
     * Interface implementation for devices with at least v11 APIs.
     */
    static class FroyoViewConfigurationVersionImpl implements ViewConfigurationVersionImpl {
        @Override
        public int getScaledPagingTouchSlop(ViewConfiguration config) {
            return ViewConfigurationCompatFroyo.getScaledPagingTouchSlop(config);
        }
    }
    /**
     * Select the correct implementation to use for the current platform.
     */
    static final ViewConfigurationVersionImpl IMPL;
    static {
        if (android.os.Build.VERSION.SDK_INT >= 11) {
            IMPL = new FroyoViewConfigurationVersionImpl();
        } else {
            IMPL = new BaseViewConfigurationVersionImpl();
        }
    }
    // -------------------------------------------------------------------
    /**
     * Call {@link ViewConfiguration#getScaledPagingTouchSlop()}.
     * If running on a pre-{@link android.os.Build.VERSION_CODES#FROYO} device,
     * returns {@link ViewConfiguration#getScaledTouchSlop()}.
     */
    public static int getScaledPagingTouchSlop(ViewConfiguration config) {
        return IMPL.getScaledPagingTouchSlop(config);
    }
}