/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package com.jtattoo.plaf.bernstein;

import com.jtattoo.plaf.*;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.plaf.UIResource;

/**
 * @author Michael Hagen
 */
public class BernsteinIcons extends BaseIcons {

    public static Icon getRadioButtonIcon() {
        if (radioButtonIcon == null) {
            radioButtonIcon = new RadioButtonIcon();
        }
        return radioButtonIcon;
    }

    public static Icon getCheckBoxIcon() {
        if (checkBoxIcon == null) {
            checkBoxIcon = new CheckBoxIcon();
        }
        return checkBoxIcon;
    }

    public static Icon getThumbHorIcon() {
        if (thumbHorIcon == null) {
            thumbHorIcon = new LazyImageIcon("bernstein/icons/radio.gif");
        }
        return thumbHorIcon;
    }

    public static Icon getThumbVerIcon() {
        if (thumbVerIcon == null) {
            thumbVerIcon = new LazyImageIcon("bernstein/icons/radio.gif");
        }
        return thumbVerIcon;
    }

    public static Icon getThumbHorIconRollover() {
        if (thumbHorIconRollover == null) {
            thumbHorIconRollover = new LazyImageIcon("bernstein/icons/radio_rollover.gif");
        }
        return thumbHorIconRollover;
    }

    public static Icon getThumbVerIconRollover() {
        if (thumbVerIconRollover == null) {
            thumbVerIconRollover = new LazyImageIcon("bernstein/icons/radio_rollover.gif");
        }
        return thumbVerIconRollover;
    }

    public static Icon getSplitterUpArrowIcon() {
        if (splitterUpArrowIcon == null) {
            splitterUpArrowIcon = new LazyImageIcon("bernstein/icons/SplitterUpArrow.gif");
        }
        return splitterUpArrowIcon;
    }

    public static Icon getSplitterDownArrowIcon() {
        if (splitterDownArrowIcon == null) {
            splitterDownArrowIcon = new LazyImageIcon("bernstein/icons/SplitterDownArrow.gif");
        }
        return splitterDownArrowIcon;
    }

    public static Icon getSplitterLeftArrowIcon() {
        if (splitterLeftArrowIcon == null) {
            splitterLeftArrowIcon = new LazyImageIcon("bernstein/icons/SplitterLeftArrow.gif");
        }
        return splitterLeftArrowIcon;
    }

    public static Icon getSplitterRightArrowIcon() {
        if (splitterRightArrowIcon == null) {
            splitterRightArrowIcon = new LazyImageIcon("bernstein/icons/SplitterRightArrow.gif");
        }
        return splitterRightArrowIcon;
    }

    //--------------------------------------------------------------------------------------------------------
    private static class CheckBoxIcon implements Icon, UIResource {

        private static Icon checkIcon = null;
        private static Icon checkSelectedIcon = null;
        private static Icon checkPressedIcon = null;
        private static Icon checkRolloverIcon = null;
        private static Icon checkRolloverSelectedIcon = null;
        private static Icon checkDisabledIcon = null;
        private static Icon checkDisabledSelectedIcon = null;

        public CheckBoxIcon() {
            checkIcon = new LazyImageIcon("bernstein/icons/check.gif");
            checkSelectedIcon = new LazyImageIcon("bernstein/icons/check_selected.gif");
            checkPressedIcon = new LazyImageIcon("bernstein/icons/check_pressed.gif");
            checkRolloverIcon = new LazyImageIcon("bernstein/icons/check_rollover.gif");
            checkRolloverSelectedIcon = new LazyImageIcon("bernstein/icons/check_rollover_selected.gif");
            checkDisabledIcon = new LazyImageIcon("bernstein/icons/check_disabled.gif");
            checkDisabledSelectedIcon = new LazyImageIcon("bernstein/icons/check_disabled_selected.gif");
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            if (!JTattooUtilities.isLeftToRight(c)) {
                x += 2;
            }
            AbstractButton button = (AbstractButton) c;
            ButtonModel model = button.getModel();
            if (button.isEnabled()) {
                if (model.isPressed() && model.isArmed()) {
                    checkPressedIcon.paintIcon(c, g, x, y);
                } else if (model.isSelected()) {
                    if (button.isRolloverEnabled() && model.isRollover() && !model.isArmed()) {
                        checkRolloverSelectedIcon.paintIcon(c, g, x, y);
                    } else {
                        checkSelectedIcon.paintIcon(c, g, x, y);
                    }
                } else {
                    if (button.isRolloverEnabled() && model.isRollover() && !model.isArmed()) {
                        checkRolloverIcon.paintIcon(c, g, x, y);
                    } else {
                        checkIcon.paintIcon(c, g, x, y);
                    }
                }
            } else {
                if (model.isPressed() && model.isArmed()) {
                    checkPressedIcon.paintIcon(c, g, x, y);
                } else if (model.isSelected()) {
                    checkDisabledSelectedIcon.paintIcon(c, g, x, y);
                } else {
                    checkDisabledIcon.paintIcon(c, g, x, y);
                }
            }
        }

        public int getIconWidth() {
            return checkIcon.getIconWidth() + 2;
        }

        public int getIconHeight() {
            return checkIcon.getIconHeight();
        }
    }

    private static class RadioButtonIcon implements Icon, UIResource {

        private Icon radioIcon = null;
        private Icon radioSelectedIcon = null;
        private Icon radioRolloverIcon = null;
        private Icon radioRolloverSelectedIcon = null;
        private Icon radioDisabledIcon = null;
        private Icon radioDisabledSelectedIcon = null;

        public RadioButtonIcon() {
            radioIcon = new LazyImageIcon("bernstein/icons/radio.gif");
            radioSelectedIcon = new LazyImageIcon("bernstein/icons/radio_selected.gif");
            radioRolloverIcon = new LazyImageIcon("bernstein/icons/radio_rollover.gif");
            radioRolloverSelectedIcon = new LazyImageIcon("bernstein/icons/radio_rollover_selected.gif");
            radioDisabledIcon = new LazyImageIcon("bernstein/icons/radio_disabled.gif");
            radioDisabledSelectedIcon = new LazyImageIcon("bernstein/icons/radio_disabled_selected.gif");
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            if (!JTattooUtilities.isLeftToRight(c)) {
                x += 2;
            }
            AbstractButton button = (AbstractButton) c;
            ButtonModel model = button.getModel();
            if (button.isEnabled()) {
                if (model.isSelected()) {
                    if (button.isRolloverEnabled() && model.isRollover()) {
                        radioRolloverSelectedIcon.paintIcon(c, g, x, y);
                    } else {
                        radioSelectedIcon.paintIcon(c, g, x, y);
                    }
                } else {
                    if (button.isRolloverEnabled() && model.isRollover()) {
                        radioRolloverIcon.paintIcon(c, g, x, y);
                    } else {
                        radioIcon.paintIcon(c, g, x, y);
                    }
                }
            } else {
                if (model.isSelected()) {
                    radioDisabledSelectedIcon.paintIcon(c, g, x, y);
                } else {
                    radioDisabledIcon.paintIcon(c, g, x, y);
                }
            }
        }

        public int getIconWidth() {
            return radioIcon.getIconWidth() + 2;
        }

        public int getIconHeight() {
            return radioIcon.getIconHeight();
        }
    }
}
