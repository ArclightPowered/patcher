package io.izzel.arclight.patcher;

import io.izzel.arclight.api.PluginPatcher;
import org.objectweb.asm.tree.ClassNode;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class PatcherMain implements PluginPatcher {

    private final PluginPatcher delegate;

    public PatcherMain() throws Exception {
        Path path = Paths.get(".arclight", "patcher");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        Path current = path.resolve("definition.jar");
        Path newVer = path.resolve("definition_new.jar");
        if (Files.exists(newVer)) {
            Files.move(newVer, current, StandardCopyOption.REPLACE_EXISTING);
        }
        if (!Files.exists(current)) {
            Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/definition.jar")), current);
        }
        this.delegate = loadDef(current);
    }

    private PluginPatcher loadDef(Path path) throws Exception {
        URLClassLoader loader = new URLClassLoader(new URL[]{path.toUri().toURL()}, getClass().getClassLoader());
        Class<?> cl = loader.loadClass("io.izzel.arclight.patcher.definition.DefinitionMain");
        return (PluginPatcher) cl.getConstructor().newInstance();
    }

    @Override
    public void handleClass(ClassNode node, ClassRepo classRepo) {
        delegate.handleClass(node, classRepo);
    }

    @Override
    public int priority() {
        return delegate.priority();
    }

    @Override
    public String version() {
        return PluginPatcher.super.version() + "_def_" + delegate.version();
    }
}
