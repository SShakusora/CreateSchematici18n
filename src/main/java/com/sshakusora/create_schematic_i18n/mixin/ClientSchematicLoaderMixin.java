package com.sshakusora.create_schematic_i18n.mixin;

import com.simibubi.create.content.schematics.client.ClientSchematicLoader;
import com.sshakusora.create_schematic_i18n.CSI18N;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * Mixin to override the schematic loading logic to support subdirectories.
 * * Note: This Mixin clears the 'availableSchematics' list to prevent duplicates,
 * which intentionally overrides/breaks the logic added by 'Create: Connected'.
 */
@Mixin(value = ClientSchematicLoader.class, priority = 2000)
public class ClientSchematicLoaderMixin {
    @Shadow private List<Component> availableSchematics;

    @Inject(method = "refresh", at = @At(value = "INVOKE", target = "Ljava/util/List;sort(Ljava/util/Comparator;)V"), remap = false)
    private void beforeSort(CallbackInfo ci) {
        this.availableSchematics.clear();
        Path rootPath = Paths.get("schematics/");
        Path excludePath = rootPath.resolve("uploaded");

        try (Stream<Path> stream = Files.walk(rootPath)) {
            stream.filter(path -> {
                        if (Files.isDirectory(path)) return false;
                        if (path.startsWith(excludePath)) return false;
                        return path.getFileName().toString().endsWith(".nbt");
                    })
                    .forEach(path -> {
                        Path relativePath = rootPath.relativize(path);
                        String rawName = relativePath.toString().replace("\\", "/");
                        String key = rawName.endsWith(".nbt") ? rawName.substring(0, rawName.length() - 4) : rawName;

                        this.availableSchematics.add(Component.translatable(key));
                    });
        } catch (NoSuchFileException ignored) {
        } catch (IOException e) {
            CSI18N.LOGGER.error("Failed to refresh schematics", e);
        }
    }
}
