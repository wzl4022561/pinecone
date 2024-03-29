/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package com.jtattoo.plaf.acryl;

import com.jtattoo.plaf.*;
import java.awt.*;
import javax.swing.JComponent;
import javax.swing.plaf.*;
import javax.swing.text.View;

/**
 * author Michael Hagen
 */
public class AcrylTabbedPaneUI extends BaseTabbedPaneUI {

    public static ComponentUI createUI(JComponent c) {
        return new AcrylTabbedPaneUI();
    }

    public void installDefaults() {
        super.installDefaults();
        tabAreaInsets.bottom = 5;
    }

    protected Color[] getTabColors(int tabIndex, boolean isSelected) {
        if ((tabIndex >= 0) && (tabIndex < tabPane.getTabCount())) {
            boolean isEnabled = tabPane.isEnabledAt(tabIndex);
            Color backColor = tabPane.getBackgroundAt(tabIndex);
            Color colorArr[] = AbstractLookAndFeel.getTheme().getTabColors();
            if ((backColor instanceof UIResource)) {
                if (isSelected) {
                    colorArr = AbstractLookAndFeel.getTheme().getDefaultColors();
                } else if (tabIndex == rolloverIndex && isEnabled) {
                    colorArr = AbstractLookAndFeel.getTheme().getRolloverColors();
                } else {
                    colorArr = AbstractLookAndFeel.getTheme().getTabColors();
                }
            } else {
                if (isSelected) {
                    //colorArr = AbstractLookAndFeel.getTheme().getDefaultColors();
                    colorArr = ColorHelper.createColorArr(ColorHelper.brighter(backColor, 60), backColor, 20);
                } else if (tabIndex == rolloverIndex && isEnabled) {
                    colorArr = AbstractLookAndFeel.getTheme().getRolloverColors();
                } else {
                    colorArr = ColorHelper.createColorArr(ColorHelper.brighter(backColor, 40), ColorHelper.darker(backColor, 10), 20);
                }
            }
            return colorArr;
        }
        return AbstractLookAndFeel.getTheme().getTabColors();
    }

    protected Color[] getContentBorderColors(int tabPlacement) {
        Color SEP_COLORS[] = {
            ColorHelper.brighter(AcrylLookAndFeel.getControlColorLight(), 20),
            AcrylLookAndFeel.getControlColorLight(),
            ColorHelper.brighter(AcrylLookAndFeel.getControlColorDark(), 20),
            AcrylLookAndFeel.getControlColorDark(),
            ColorHelper.darker(AcrylLookAndFeel.getControlColorDark(), 20)
        };
        return SEP_COLORS;
    }

    protected Color getContentBorderColor() {
        return ColorHelper.brighter(AbstractLookAndFeel.getTheme().getFrameColor(), 50);
    }

    protected Color getSelectedBorderColor(int tabIndex) {
        Color backColor = tabPane.getBackgroundAt(tabIndex);
        if (backColor instanceof UIResource) {
            return AbstractLookAndFeel.getFrameColor();
        } 
        return super.getSelectedBorderColor(tabIndex);
    }

    protected Color getGapColor(int tabIndex) {
        if ((tabIndex >= 0) && (tabIndex < tabPane.getTabCount())) {
            Color backColor = tabPane.getBackgroundAt(tabIndex);
            if ((backColor instanceof ColorUIResource) && (tabIndex == tabPane.getSelectedIndex())) {
                Color colors[] = AbstractLookAndFeel.getTheme().getDefaultColors();
                return colors[colors.length - 1];
            }
        }
        return super.getGapColor(tabIndex);
    }

    protected Font getTabFont(boolean isSelected) {
        if (isSelected) {
            return super.getTabFont(isSelected).deriveFont(Font.BOLD);
        } else {
            return super.getTabFont(isSelected);
        }
    }

    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
        Color backColor = tabPane.getBackgroundAt(tabIndex);
        if (!(backColor instanceof UIResource)) {
            super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected);
            return;
        }
        g.setFont(font);
        View v = getTextViewForTab(tabIndex);
        if (v != null) {
            // html
            Graphics2D g2D = (Graphics2D) g;
            Object savedRenderingHint = null;
            if (AbstractLookAndFeel.getTheme().isTextAntiAliasingOn()) {
                savedRenderingHint = g2D.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
                g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, AbstractLookAndFeel.getTheme().getTextAntiAliasingHint());
            }
            v.paint(g, textRect);
            if (AbstractLookAndFeel.getTheme().isTextAntiAliasingOn()) {
                g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, savedRenderingHint);
            }
        } else {
            // plain text
            int mnemIndex = -1;
            if (JTattooUtilities.getJavaVersion() >= 1.4) {
                mnemIndex = tabPane.getDisplayedMnemonicIndexAt(tabIndex);
            }

            if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
                if (isSelected) {
                    Color shadowColor = ColorHelper.darker(AcrylLookAndFeel.getWindowTitleColorDark(), 30);
                    g.setColor(shadowColor);
                    JTattooUtilities.drawStringUnderlineCharAt(tabPane, g, title, mnemIndex, textRect.x - 1, textRect.y - 1 + metrics.getAscent());
                    JTattooUtilities.drawStringUnderlineCharAt(tabPane, g, title, mnemIndex, textRect.x - 1, textRect.y + 1 + metrics.getAscent());
                    JTattooUtilities.drawStringUnderlineCharAt(tabPane, g, title, mnemIndex, textRect.x + 1, textRect.y - 1 + metrics.getAscent());
                    JTattooUtilities.drawStringUnderlineCharAt(tabPane, g, title, mnemIndex, textRect.x + 1, textRect.y + 1 + metrics.getAscent());
                    g.setColor(AbstractLookAndFeel.getTheme().getWindowTitleForegroundColor());
                } else {
                    g.setColor(tabPane.getForegroundAt(tabIndex));
                }
                JTattooUtilities.drawStringUnderlineCharAt(tabPane, g, title, mnemIndex, textRect.x, textRect.y + metrics.getAscent());

            } else { // tab disabled
                g.setColor(tabPane.getBackgroundAt(tabIndex).brighter());
                JTattooUtilities.drawStringUnderlineCharAt(tabPane, g, title, mnemIndex, textRect.x, textRect.y + metrics.getAscent());
                g.setColor(tabPane.getBackgroundAt(tabIndex).darker());
                JTattooUtilities.drawStringUnderlineCharAt(tabPane, g, title, mnemIndex, textRect.x - 1, textRect.y + metrics.getAscent() - 1);
            }
        }
    }
}