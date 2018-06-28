package com.example.truongducbinh.customviewpager.custom.accessibility;


import android.os.Build;
import android.view.accessibility.AccessibilityEvent;
/**
 * Helper for accessing features in {@link AccessibilityEvent}
 * introduced after API level 4 in a backwards compatible fashion.
 */
public class AccessibilityEventCompat {
    static interface AccessibilityEventVersionImpl {
        public int getRecordCount(AccessibilityEvent event);
        public void appendRecord(AccessibilityEvent event, Object record);
        public Object getRecord(AccessibilityEvent event, int index);
    }
    static class AccessibilityEventStubImpl implements AccessibilityEventVersionImpl {
        @Override
        public void appendRecord(AccessibilityEvent event, Object record) {
        }
        @Override
        public Object getRecord(AccessibilityEvent event, int index) {
            return null;
        }
        @Override
        public int getRecordCount(AccessibilityEvent event) {
            return 0;
        }
    }
    static class AccessibilityEventIcsImpl extends AccessibilityEventStubImpl {
        @Override
        public void appendRecord(AccessibilityEvent event, Object record) {
            AccessibilityEventCompatIcs.appendRecord(event, record);
        }
        @Override
        public Object getRecord(AccessibilityEvent event, int index) {
            return AccessibilityEventCompatIcs.getRecord(event, index);
        }
        @Override
        public int getRecordCount(AccessibilityEvent event) {
            return AccessibilityEventCompatIcs.getRecordCount(event);
        }
    }
    private final static AccessibilityEventVersionImpl IMPL;
    static {
        if (Build.VERSION.SDK_INT >= 14) { // ICS
            IMPL = new AccessibilityEventIcsImpl();
        } else {
            IMPL = new AccessibilityEventStubImpl();
        }
    }
    /**
     * Represents the event of a hover enter over a {@link android.view.View}.
     */
    public static final int TYPE_VIEW_HOVER_ENTER = 0x00000080;
    /**
     * Represents the event of a hover exit over a {@link android.view.View}.
     */
    public static final int TYPE_VIEW_HOVER_EXIT = 0x00000100;
    /**
     * Represents the event of starting a touch exploration gesture.
     */
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_START = 0x00000200;
    /**
     * Represents the event of ending a touch exploration gesture.
     */
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_END = 0x00000400;
    /**
     * Represents the event of changing the content of a window.
     */
    public static final int TYPE_WINDOW_CONTENT_CHANGED = 0x00000800;
    /**
     * Represents the event of scrolling a view.
     */
    public static final int TYPE_VIEW_SCROLLED = 0x00001000;
    /**
     * Represents the event of changing the selection in an {@link android.widget.EditText}.
     */
    public static final int TYPE_VIEW_TEXT_SELECTION_CHANGED = 0x00002000;
    /**
     * Represents the event of an application making an announcement.
     */
    public static final int TYPE_ANNOUNCEMENT = 0x00004000;
    /**
     * Represents the event of gaining accessibility focus.
     */
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUSED = 0x00008000;
    /**
     * Represents the event of clearing accessibility focus.
     */
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED = 0x00010000;
    /**
     * Represents the event of traversing the text of a view at a given movement granularity.
     */
    public static final int TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY = 0x00020000;
    /**
     * Mask for {@link AccessibilityEvent} all types.
     *
     * @see AccessibilityEvent#TYPE_VIEW_CLICKED
     * @see AccessibilityEvent#TYPE_VIEW_LONG_CLICKED
     * @see AccessibilityEvent#TYPE_VIEW_SELECTED
     * @see AccessibilityEvent#TYPE_VIEW_FOCUSED
     * @see AccessibilityEvent#TYPE_VIEW_TEXT_CHANGED
     * @see AccessibilityEvent#TYPE_WINDOW_STATE_CHANGED
     * @see AccessibilityEvent#TYPE_NOTIFICATION_STATE_CHANGED
     * @see #TYPE_VIEW_HOVER_ENTER
     * @see #TYPE_VIEW_HOVER_EXIT
     * @see #TYPE_TOUCH_EXPLORATION_GESTURE_START
     * @see #TYPE_TOUCH_EXPLORATION_GESTURE_END
     * @see #TYPE_WINDOW_CONTENT_CHANGED
     * @see #TYPE_VIEW_SCROLLED
     * @see #TYPE_VIEW_TEXT_SELECTION_CHANGED
     * @see #TYPE_ANNOUNCEMENT
     * @see #TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY
     */
    public static final int TYPES_ALL_MASK = 0xFFFFFFFF;
    /*
     * Hide constructor from clients.
     */
    private AccessibilityEventCompat() {
    }
    /**
     * Gets the number of records contained in the event.
     *
     * @return The number of records.
     */
    public static int getRecordCount(AccessibilityEvent event) {
        return IMPL.getRecordCount(event);
    }
    /**
     * Appends an {@link android.view.accessibility.AccessibilityRecord} to the end of
     * event records.
     *
     * @param record The record to append.
     *
     * @throws IllegalStateException If called from an AccessibilityService.
     */
    public static void appendRecord(AccessibilityEvent event, AccessibilityRecordCompat record) {
        IMPL.appendRecord(event, record.getImpl());
    }
    /**
     * Gets the record at a given index.
     *
     * @param index The index.
     * @return The record at the specified index.
     */
    public static AccessibilityRecordCompat getRecord(AccessibilityEvent event, int index) {
        return new AccessibilityRecordCompat(IMPL.getRecord(event, index));
    }
}