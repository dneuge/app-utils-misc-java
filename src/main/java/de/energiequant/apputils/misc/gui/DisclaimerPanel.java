package de.energiequant.apputils.misc.gui;

import static de.energiequant.apputils.misc.gui.SwingHelper.onChange;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.energiequant.apputils.misc.DisclaimerState;

public class DisclaimerPanel extends JPanel {
    private static final Logger LOGGER = LoggerFactory.getLogger(DisclaimerPanel.class);

    private final DisclaimerState state;
    private final JCheckBox checkBox;

    private final Runnable onSaveCallback;

    public DisclaimerPanel(DisclaimerState state) {
        this(state, null);
    }

    public DisclaimerPanel(DisclaimerState state, Runnable onSaveCallback) {
        super();

        this.state = state;
        this.onSaveCallback = onSaveCallback;

        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/plain");
        editorPane.setText(state.getDisclaimer());
        editorPane.setEditable(false);
        editorPane.setFont(Font.decode(Font.MONOSPACED));
        JScrollPane scrollPane = new JScrollPane(editorPane);

        checkBox = new JCheckBox(state.getDisclaimerAcceptanceText());
        state.addListener(this::updateCheckBoxState);
        updateCheckBoxState();
        onChange(checkBox, this::onCheckBoxStateChanged);

        JButton saveButton = new JButton("Save configuration");
        if (this.onSaveCallback == null) {
            saveButton.setEnabled(false);

            // if the button is visible but disabled it's misleading as it looks like the
            // user would need to perform some other action to accept the disclaimer
            saveButton.setVisible(false);
        }
        saveButton.addActionListener(this::onSaveConfigurationClicked);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        gbc.gridy++;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        add(checkBox, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        add(saveButton, gbc);
    }

    private void updateCheckBoxState() {
        checkBox.setSelected(state.isAccepted());
    }

    private void onCheckBoxStateChanged() {
        boolean newValue = checkBox.isSelected();

        boolean oldValue = state.isAccepted();
        if (oldValue == newValue) {
            return;
        }

        LOGGER.debug("Disclaimer acceptance changed to {}", newValue);
        state.setAccepted(newValue);
    }

    private void onSaveConfigurationClicked(ActionEvent event) {
        if (onSaveCallback != null) {
            onSaveCallback.run();
        }
    }
}
