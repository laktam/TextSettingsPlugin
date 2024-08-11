package org.mql.textsettingsplugin;

import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.Style;

import org.mql.jcodeeditor.documentHandlers.TextPanesHandler;
import org.mql.jcodeeditor.plugins.PluginSettingsProvider;

/*
 * save default values to use them when deactivating 
 * and read from file when activating ?
 */
public class TextSettingsPlugin implements PluginSettingsProvider, TextPanesHandler {
	private List<JTextPane> textPanes;
	private int maxFontSize = 80;

	public TextSettingsPlugin() {
		textPanes = new Vector<JTextPane>();
		// TODO read properties from setting.properties
	}

	@Override
	public List<JComponent> getSettings() {
		Font textPaneFont = UIManager.getDefaults().getFont("JTextPane.font");
		int fontSize = textPaneFont.getSize();
		JSlider fontSizeSlider = new JSlider(JSlider.HORIZONTAL, 1, maxFontSize, fontSize);
		
		fontSizeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				UIManager.put("JTextPane.font", textPaneFont.deriveFont(fontSizeSlider.getValue()));
				for (JTextPane textPane : textPanes) {
					changeFontSize(textPane, fontSizeSlider.getValue());
				}
			}
		});

		List<JComponent> settingComponents = new Vector<JComponent>();
		settingComponents.add(fontSizeSlider);
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
		// TODO Auto-generated method stub
	}

}
