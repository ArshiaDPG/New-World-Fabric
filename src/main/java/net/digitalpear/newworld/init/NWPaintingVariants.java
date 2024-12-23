package net.digitalpear.newworld.init;

import net.digitalpear.newworld.Newworld;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NWPaintingVariants {

    public static List<RegistryKey<PaintingVariant>> paintings = new ArrayList<>();


    public static final RegistryKey<PaintingVariant> PROVIDENCE = of("providence");


    public static void bootstrap(Registerable<PaintingVariant> registry) {
        register(registry, PROVIDENCE, 2, 3);
    }


    private static void register(Registerable<PaintingVariant> registry, RegistryKey<PaintingVariant> key, int width, int height) {
        register(registry, key, width, height, true);
    }
    private static void register(Registerable<PaintingVariant> registry, RegistryKey<PaintingVariant> key, int width, int height, boolean hasAuthor) {
        registry.register(key, new PaintingVariant(width, height, key.getValue(), Optional.of(Text.translatable(key.getValue().toTranslationKey("painting", "title")).formatted(Formatting.YELLOW)), hasAuthor ? Optional.of(Text.translatable(key.getValue().toTranslationKey("painting", "author")).formatted(Formatting.GRAY)) : Optional.empty()));
    }


    private static RegistryKey<PaintingVariant> of(String id) {
        RegistryKey<PaintingVariant> variant = RegistryKey.of(RegistryKeys.PAINTING_VARIANT, Newworld.id(id));
        paintings.add(variant);
        return variant;
    }
}
