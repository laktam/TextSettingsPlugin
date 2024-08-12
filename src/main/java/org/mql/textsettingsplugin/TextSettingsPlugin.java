package org.mql.textsettingsplugin;

import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.Style;

import org.mql.jcodeeditor.documentHandlers.TextPanesHandler;
import org.mql.jcodeeditor.plugins.Plugin;
import org.mql.jcodeeditor.plugins.PluginSettingsProvider;

/*
 * save default values to use them when deactivating 
 * and read from file when activating ?
 */
public class TextSettingsPlugin implements Plugin, PluginSettingsProvider, TextPanesHandler {
	private List<JTextPane> textPanes;
	private int maxFontSize = 80;

	public TextSettingsPlugin() {
		textPanes = new Vector<JTextPane>();
		// TODO read properties from setting.properties
	}

	@Override
	public List<JComponent> getSettings() {
		// font size
		JPanel fontSizePanel = new JPanel();
		Font textPaneFont = new JTextPane().getFont();
		int fontSize = textPaneFont.getSize();
		JSlider fontSizeSlider = new JSlider(JSlider.HORIZONTAL, 1, maxFontSize, fontSize);
		JLabel label = new JLabel("Font size : " + fontSize);

		fontSizeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				UIManager.put("TextPane.font", textPaneFont.deriveFont((float) fontSizeSlider.getValue()));
				for (JTextPane textPane : textPanes) {
					changeFontSize(textPane, fontSizeSlider.getValue());
				}
				label.setText("Font size : " + fontSizeSlider.getValue());
			}
		});
		fontSizePanel.add(label);
		fontSizePanel.add(fontSizeSlider);
		
		List<JComponent> settingComponents = new Vector<JComponent>();
		settingComponents.add(fontSizePanel);
		return settingComponents;
	}

	public void changeFontSize(JTextPane textPane, int newSize) {
		StyledDocument doc = textPane.getStyledDocument();
		Style style = textPane.addStyle("SizeStyle", null);
		StyleConstants.setFontSize(style, newSize);
		doc.setCharacterAttributes(0, doc.getLength(), style, true);
	}

	@Override
	public void setTextPanes(List<JTextPane> textPanes) {
	}

	@Override
	public void addTextPane(JTextPane textPane) {
		textPanes.add(textPane);
	}

	@Override
	public void execute() {
	}

	@Override
	public String getName() {
		return "Text Settings";
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
