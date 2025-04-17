package com.bryanvalc.pluginTemplateKt.utils


object LegacyMessage { //TODO: should check adventure-text-serializer-legacy
    val REPLACES: MutableMap<String?, String?>

    init {
        REPLACES = HashMap<String?, String?>()

        REPLACES.put("0", "<black>")
        REPLACES.put("1", "<dark_blue>")
        REPLACES.put("2", "<dark_green>")
        REPLACES.put("3", "<dark_aqua>")
        REPLACES.put("4", "<dark_red>")
        REPLACES.put("5", "<dark_purple>")
        REPLACES.put("6", "<gold>")
        REPLACES.put("7", "<gray>")
        REPLACES.put("8", "<dark_gray>")
        REPLACES.put("9", "<blue>")
        REPLACES.put("a", "<green>")
        REPLACES.put("b", "<aqua>")
        REPLACES.put("c", "<red>")
        REPLACES.put("d", "<light_purple>")
        REPLACES.put("e", "<yellow>")
        REPLACES.put("f", "<white>")
        REPLACES.put("k", "<magic>")
        REPLACES.put("l", "<bold>")
        REPLACES.put("m", "<strikethrough>")
        REPLACES.put("n", "<underline>")
        REPLACES.put("o", "<italic>")
        REPLACES.put("r", "<reset>")
    }

    /**
     * Converts legacy message to MiniMessage
     *
     * @param legacy Legacy message
     * @param character Character used for legacy formatting
     * @return MiniMessage-compatible format
     */
    /**
     * Converts legacy message to MiniMessage using § for colors
     *
     * @param legacy Legacy message
     * @return MiniMessage-compatible format
     */
    @JvmOverloads
    fun fromLegacy(legacy: String, character: String? = "&"): String {
        var legacy = legacy
        for (entry in REPLACES.entries) {
            legacy = legacy.replace(character + entry.key, entry.value!!)
        }

        //FIXME: Maybe use regex for this?
        var lastMatch = 0

        do {
            val index = legacy.indexOf(character + "#", lastMatch)

            if (index == -1) break

            lastMatch++

            legacy =
                legacy.replace(legacy.substring(index, index + 8), "<" + legacy.substring(index + 1, index + 8) + ">")
        } while (true)

        return legacy
    }
}