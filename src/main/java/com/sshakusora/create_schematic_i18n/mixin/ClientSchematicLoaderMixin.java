package com.sshakusora.create_schematic_i18n.mixin;

import com.simibubi.create.content.schematics.client.ClientSchematicLoader;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

@Mixin(ClientSchematicLoader.class)
public class ClientSchematicLoaderMixin {
    @Shadow private List<Component> availableSchematics;

    @Inject(method = "refresh", at = @At(value = "INVOKE", target = "Ljava/util/List;sort(Ljava/util/Comparator;)V"), remap = false)
    private void beforeSort(CallbackInfo ci) {
        this.availableSchematics.clear();
        try {
            Files.list(Paths.get("schematics/")).filter((f) -> !Files.isDirectory(f, new LinkOption[0]) && f.getFileName().toString().endsWith(".nbt")).forEach((path) -> {
                if (!Files.isDirectory(path, new LinkOption[0])) {
                    String rawName = path.getFileName().toString();
                    String key = rawName.endsWith(".nbt") ? rawName.substring(0, rawName.length() - 4) : rawName;
                    this.availableSchematics.add(Component.translatable(key));
                }
            });
        } catch (NoSuchFileException var2) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
