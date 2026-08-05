package com.kaamconnect.theme;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public final class AppFonts {

    public static final String FONT_FAMILY = "System";
    public static final double PAGE_TITLE_SIZE = 28;
    public static final double HEADING_SIZE = 20;
    public static final double BODY_SIZE = 14;
    public static final double BUTTON_TEXT_SIZE = 14;
    public static final double SMALL_TEXT_SIZE = 12;


    public static Font pageTitle() {
        return Font.font(
                FONT_FAMILY,
                FontWeight.BOLD,
                PAGE_TITLE_SIZE
        );
    }
    public static Font heading() {
        return Font.font(
                FONT_FAMILY,
                FontWeight.SEMI_BOLD,
                HEADING_SIZE
        );
    }
    public static Font body() {
        return Font.font(
                FONT_FAMILY,
                FontWeight.NORMAL,
                BODY_SIZE
        );
    }
    public static Font button() {
        return Font.font(
                FONT_FAMILY,
                FontWeight.SEMI_BOLD,
                BUTTON_TEXT_SIZE
        );
    }
    public static Font small() {
        return Font.font(
                FONT_FAMILY,
                FontWeight.NORMAL,
                SMALL_TEXT_SIZE
        );
    }

    private AppFonts() {
    }
}