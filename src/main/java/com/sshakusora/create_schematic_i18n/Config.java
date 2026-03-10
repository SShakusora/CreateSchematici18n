package com.sshakusora.create_schematic_i18n;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;

public class Config {
    public static boolean enableRecursiveSchematicLoading = true;

    public static void loadEarlyConfig() {
        File configFile = FMLPaths.CONFIGDIR.get().resolve(CSI18N.MODID +"-client.toml").toFile();

        CommentedFileConfig config = CommentedFileConfig.builder(configFile)
                .sync()
                .autosave()
                .build();
        config.load();

        String path = "enableRecursiveSchematicLoading";
        if (!config.contains(path)) {
            config.setComment(path,
                    " [Feature]: Allows recursive scanning of subfolders within the 'schematics' directory.\n" +
                            " [Compatibility Warning]: Enabling this will clear the schematic list before scanning,\n" +
                            " which causes 'Create: Connected' category features to stop working.\n" +
                            " [Requirement]: If 'Create: Connected' is installed and this is set to true, please ensure\n" +
                            " that 'schematicNestingDepth' in the Create: Connected server config is set high enough\n" +
                            " to allow the server to recognize files in deep subdirectories.");
            config.set(path, true);
        }

        enableRecursiveSchematicLoading = config.get(path);
        config.close();
    }
}
