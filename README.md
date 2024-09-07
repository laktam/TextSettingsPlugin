# Text Settings Plugin

The **Text Settings Plugin** is a plugin for the [JCodeEditor](https://github.com/laktam/JCodeEditor) that allows users to easily configure text settings, particularly font size, (it is just to test the ability for plugins for JCodeEditor to give extra settings not just activation and deactivation).
It provides a slider interface to change the font size of the text dynamically and persistently saves the user's settings.

## Features

- Adjust the font size dynamically using a slider.
- Persistently saves the last chosen font size using the `PropertiesManager`.

## How to Use

1. Place the plugin in the `plugins` folder of your [JCodeEditor](https://github.com/laktam/JCodeEditor) project.
2. Launch the editor and navigate to the plugin settings to find the "Text Settings" option.
3. Adjust the font size using the slider in the plugin settings panel.
4. The font size will update dynamically in all text panes, and the setting will be saved for future sessions.
