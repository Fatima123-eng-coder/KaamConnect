package com.kaamconnect.theme;

import javafx.scene.paint.Color;

public final class AppColors {

    private AppColors() {
    }

    public static final String PRIMARY_HEX = "#1E2D4C";
    public static final String SECONDARY_HEX = "#ACBDAA";
    public static final String TEXT_SECONDARY_HEX = "#858585";
    public static final String ACCENT_SOFT_HEX = "#CEC0BB";

    public static final String BACKGROUND_HEX = "#F7F8F5";
    public static final String SURFACE_HEX = "#FFFFFF";
    public static final String BORDER_HEX = "#DDE3DD";

    public static final String ERROR_HEX = "#C94A4A";
    public static final String SUCCESS_HEX = "#607D68";

    public static final Color PRIMARY =
            Color.web(PRIMARY_HEX);

    public static final Color SECONDARY =
            Color.web(SECONDARY_HEX);

    public static final Color TEXT_PRIMARY =
            Color.web(PRIMARY_HEX);

    public static final Color TEXT_SECONDARY =
            Color.web(TEXT_SECONDARY_HEX);

    public static final Color BACKGROUND =
            Color.web(BACKGROUND_HEX);

    public static final Color SURFACE =
            Color.web(SURFACE_HEX);

    public static final Color BORDER =
            Color.web(BORDER_HEX);

    public static final Color ACCENT =
            Color.web(ACCENT_SOFT_HEX);

    public static final Color ERROR =
            Color.web(ERROR_HEX);

    public static final Color SUCCESS =
            Color.web(SUCCESS_HEX);
}