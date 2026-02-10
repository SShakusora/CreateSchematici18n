package com.sshakusora.create_schematic_i18n.mixin;

import com.simibubi.create.content.schematics.ServerSchematicLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Mixin(ServerSchematicLoader.class)
public class ServerSchematicLoaderMixin {
    @Inject(method = "handleNewUpload", at = @At(value = "INVOKE", target = "Ljava/nio/file/Files;deleteIfExists(Ljava/nio/file/Path;)Z"), locals = LocalCapture.CAPTURE_FAILHARD, remap = false)
    private void beforeDeleteAndWrite(ServerPlayer player, String schematic, long size, BlockPos pos, CallbackInfo ci, String var1, String var2, Path var3, Path uploadPath) throws IOException {
        Path parentFolder = uploadPath.getParent();
        if (parentFolder != null && !Files.exists(parentFolder)) {
            Files.createDirectories(parentFolder);
        }
    }
}

