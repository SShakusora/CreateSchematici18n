package com.sshakusora.create_schematic_i18n.mixin;

import com.simibubi.create.content.schematics.table.SchematicTableScreen;
import com.simibubi.create.foundation.gui.widget.Label;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SchematicTableScreen.class)
public class SchematicTableScreenMixin {
    @Shadow private Label schematicsLabel;

    @Inject(method = "containerTick", at = @At("TAIL"))
    private void onContainerTick(CallbackInfo ci) {
        if (this.schematicsLabel != null && this.schematicsLabel.text != null) {
            String currentText = this.schematicsLabel.text.getString();

            if (currentText.endsWith(".nbt")) {
                String key = currentText.substring(0, currentText.length() - 4);
                this.schematicsLabel.text = Component.translatable(key);
            }
        }
    }

    @Redirect(method = "lambda$init$0", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;getString()Ljava/lang/String;"))
    private String redirectGetFileName(Component instance) {
        if (instance.getContents() instanceof TranslatableContents trans) {
            String key = trans.getKey();
            return key + ".nbt";
        }
        return instance.getString();
    }
}
