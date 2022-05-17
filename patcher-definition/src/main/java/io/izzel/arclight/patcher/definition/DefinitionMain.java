package io.izzel.arclight.patcher.definition;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.objectweb.asm.tree.ClassNode;

import io.izzel.arclight.api.PluginPatcher;

public class DefinitionMain implements PluginPatcher {

    private static final Map<String, BiConsumer<ClassNode, ClassRepo>> SPECIFIC = Map.of(
            "com/sk89q/worldedit/bukkit/BukkitAdapter", WorldEdit::handleBukkitAdapter,
            "com/sk89q/worldedit/bukkit/adapter/Refraction", WorldEdit::handlePickName);
    private static final List<BiConsumer<ClassNode, ClassRepo>> GENERAL = List.of(
            WorldEdit::handleGetProperties, WorldEdit::handleWatchdog);

    @Override
    public void handleClass(ClassNode node, ClassRepo classRepo) {
        var consumer = SPECIFIC.get(node.name);
        if (consumer != null) {
            consumer.accept(node, classRepo);
        } else {
            for (var general : GENERAL) {
                general.accept(node, classRepo);
            }
        }
    }
}
