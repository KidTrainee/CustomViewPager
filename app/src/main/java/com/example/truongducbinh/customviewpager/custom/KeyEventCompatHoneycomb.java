package com.example.truongducbinh.customviewpager.custom;
import android.view.KeyEvent;
/**
 * Implementation of key event compatibility that can call Honeycomb APIs.
 */
class KeyEventCompatHoneycomb {
    public static int normalizeMetaState(int metaState) {
        return KeyEvent.normalizeMetaState(metaState);
    }

    public static boolean metaStateHasModifiers(int metaState, int modifiers) {
        return KeyEvent.metaStateHasModifiers(metaState, modifiers);
    }
    public static boolean metaStateHasNoModifiers(int metaState) {
        return KeyEvent.metaStateHasNoModifiers(metaState);
    }
    public static boolean isCtrlPressed(KeyEvent event) {
        return event.isCtrlPressed();
    }
}
