package io.github.xdotdash.xerionbot.util

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class FileUtil {

    static final Path WORKING_DIR = Paths.get(".").toAbsolutePath().normalize()
    static final Path SCRIPTS_DIR = WORKING_DIR.resolve("scripts")

    static {
        try {
            Files.createDirectories(SCRIPTS_DIR)
        } catch (IOException e) {
            e.printStackTrace()
        }
    }
}
