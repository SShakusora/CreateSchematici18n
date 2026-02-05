package com.sshakusora.create_schematic_i18n.mixin;

import com.simibubi.create.content.schematics.SchematicItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(SchematicItem.class)
public class SchematicItemMixin {
    @Redirect(method = "appendHoverText", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"), remap = false)
    private boolean redirectTooltipAdd(List<Component> list, Object element) {
        if (element instanceof Component c) {
            String rawText = c.getString();

            String fileName = rawText.strip();

            if (fileName.contains(".nbt")) {
                String cleanName = fileName.replaceAll("§[0-9a-fk-or]", "");
                String key = cleanName.endsWith(".nbt") ? cleanName.substring(0, cleanName.length() - 4) : cleanName;

                Component translated = Component.translatable(key).withStyle(ChatFormatting.GOLD);
                return list.add(translated);
            }
        }

        return list.add((Component) element);
    }
}
